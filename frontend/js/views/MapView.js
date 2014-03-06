var CLUSTERING_ZOOM_LEVEL = 10;
var VIEW_POD_ZOOM_LEVEL = 15;

module.exports = Backbone.View.extend({
    el: $('#map'),
    initialize: function() {
        nokia.Settings.set("app_id", "p5GkEvYiBABFVkl6p381");
        nokia.Settings.set("app_code", "ZnXL6psNLGSyfX29gRaxkQ");
        nokia.Settings.set("serviceMode", "cit");

        var mapContainer = document.getElementById("mapContainer");
        this.map = new nokia.maps.map.Display(mapContainer, {
            center: [-33, 144],
            zoomLevel: 5,
            components: [
                new nokia.maps.map.component.ZoomBar(),
                new nokia.maps.map.component.Behavior(),
                new nokia.maps.map.component.Overview(),
                new nokia.maps.map.component.ScaleBar(),
                new nokia.maps.positioning.component.Positioning(),
                new nokia.maps.map.component.ZoomRectangle()
            ]
        });

        this.clusterProvider = new nokia.maps.clustering.ClusterProvider(this.map, {
            eps: 16,
            minPts: 1,
            dataPoints: []
        });

        this.markersContainer = new nokia.maps.map.Container();
        var self = this;
        this.markersContainer.addListener(
            nokia.maps.dom.Page.browser.touch ? "tap" : "click",
            function(evt) {
                this.objects.remove(evt.target);
                this.objects.add(evt.target);

                var pod = evt.target.pod;
                self.model.set('currentPod', pod);
            }
        );
        this.map.objects.add(this.markersContainer);
        this.markersContainer.addListener("mouseover", function(evt) {
            document.body.style.cursor = "pointer";
        });
        this.markersContainer.addListener("mouseout", function(evt) {
            document.body.style.cursor = "default";
        });

        var zoomObserver = function(obj, key, newValue, oldValue) {
            if ((newValue >= CLUSTERING_ZOOM_LEVEL && oldValue < CLUSTERING_ZOOM_LEVEL) || newValue < CLUSTERING_ZOOM_LEVEL && oldValue >= CLUSTERING_ZOOM_LEVEL) {
                this.render();
            }
        };
        this.map.addObserver("zoomLevel", zoomObserver, this);

        this.render();
        this.listenTo(this.model.get('pods'), 'reset', this.render);
        this.listenTo(this.model.get('pods'), 'sync', this.render);
        var self = this;
        this.listenTo(this.model, 'change:currentPod', function(model, pod) {
            if (!self.shouldRenderClusters()) {
                self.render();
            } else if (pod) {
                self.map.setZoomLevel(VIEW_POD_ZOOM_LEVEL, 'default');
            }

            if (pod) {
                var coord = new nokia.maps.geo.Coordinate(pod.get('latitude'), pod.get('longitude'));
                self.map.setCenter(coord, 'default');
            }
        });
    },
    render: function() {
        this.clusterProvider.clean();
        this.markersContainer.objects.clear()

        if (this.shouldRenderClusters()) {
            this.renderClusters();
        } else {
            this.renderMarkers();
        }
    },
    shouldRenderClusters: function() {
        return this.map.get("zoomLevel") < CLUSTERING_ZOOM_LEVEL;
    },
    renderMarkers: function() {
        var self = this;
        var pods = this.model.get('pods');
        this.markersContainer.objects.clear();
        var currentPodMarker;
        for (i = 0; i < pods.length; i++) {
            var pod = pods.at(i);
            var isCurrentPod = (pod === this.model.get('currentPod'));
            var marker = new nokia.maps.map.StandardMarker([Number(pod.get('latitude')), Number(pod.get('longitude'))], {
                text: pod.get('placeInOrder'),
                brush: {
                    color: isCurrentPod ? "orange" : "blue"
                }
            });
            marker.pod = pod;

            if (isCurrentPod) {
                currentPodMarker = marker;
            } else {
                this.markersContainer.objects.add(marker);
            }
        }

        if (currentPodMarker) {
            this.markersContainer.objects.add(currentPodMarker);
        }
    },
    renderClusters: function() {
        this.model.get('pods').forEach(function(pod) {
            this.clusterProvider.add({
                latitude: pod.get('latitude'),
                longitude: pod.get('longitude')
            });
        }, this);
        this.clusterProvider.cluster();
    }
});