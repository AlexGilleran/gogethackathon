var CO2_PER_MONTH_TREE = 1.82; //http://www.arborenvironmentalalliance.com/carbon-tree-facts.asp
var LITRES_IN_DRUM = 42;

var templates = require('../templates')(Handlebars);
module.exports = Backbone.View.extend({
    el: $('#pod-info-panel'),
    initialize: function() {
        this.render();
        this.listenTo(this.model, 'change:currentPod', this.render);
        var self = this;
        this.listenTo(this.model.get('cars'), 'change:current', function() {
            if (self.model.get('currentPod')) {
                self.listenTo(self.model.get('currentPod'), 'sync', self.render);
                self.model.get('currentPod').fetch();
            }
        });
    },
    render: function() {
        var pod = this.model.get('currentPod');
        var car = this.model.get('cars').get('current');
        if (pod) {
            var html = templates['./templates/pod-info.hbs']({
                name: pod.get('name'),
                car: {
                    name: car.shortName,
                    range: car.range
                },
                trips: {
                    totalCount: pod.get('tripCount'),
                    saveableCount: pod.get('saveableTripCount'),
                    percentage: Math.round(pod.get('saveableTripCount') / pod.get('tripCount') * 100)
                },
                savings: {
                    co2: pod.get('saveableCo2').toFixed(1),
                    treeCount: Math.floor(pod.get('saveableCo2') / CO2_PER_MONTH_TREE),
                    litres: pod.get('saveableFuel').toLocaleString(),
                    tankString: (pod.get('saveableFuel') / LITRES_IN_DRUM).toFixed(1),
                    tankCount: Math.floor(pod.get('saveableFuel') / LITRES_IN_DRUM)
                },
                litresInTank: LITRES_IN_DRUM,
                co2PerMonthTree: CO2_PER_MONTH_TREE
            });
            this.$el.html(html)
            this.$el.fadeIn();
        } else {
            this.$el.hide();
        }
    }
});