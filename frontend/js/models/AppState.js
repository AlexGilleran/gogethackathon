module.exports = Backbone.Model.extend({
    initialize: function() {
        this.on('change:currentPod', function(appState, newPod) {
            var old = appState.previous('currentPod');
            if (old) {
                old.set('isSelected', false);
            }
            newPod.set('isSelected', true);
        }, this);

        this.on('change:pods', function(appState, pods) {
            var self = this;
            if (this.get('pods')) {
                this.listenTo(this.get('pods'), 'sync', function(pods) {
                    if (pods && pods.forEach) {
                        pods.forEach(function(pod) {
                            self.listenTo(pod, 'change:isSelected', function(pod, isSelected) {
                                if (isSelected) {
                                    self.set('currentPod', pod);
                                }
                            })
                        });
                    }
                });
            }
        }, this);
    }
});