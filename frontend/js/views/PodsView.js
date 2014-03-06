var templates = require('../templates')(Handlebars);
var PodListItemView = require('./PodListItemView');
module.exports = Backbone.View.extend({
    el: $('#pod-panel'),
    initialize: function() {
        var self = this;
        this.nextButton = $(this.el).find('.next-button');
        this.prevButton = $(this.el).find('.prev-button');
        this.nextButton.click(function(e) {
            self.model.get('pods').nextPage();
            return false;
        });
        this.prevButton.click(function() {
            self.model.get('pods').prevPage();
            return false;
        });

        this.podLinkContainer = this.$el.find('#pod-link-container');

        this.render();
        this.listenTo(this.model.get('pods'), 'reset', this.onSync);
        this.listenTo(this.model.get('pods'), 'sync', this.onSync);
        this.listenTo(this.model, 'change', this.render);
    },

    onSync: function() {
        var self = this;
        this.podLinkContainer.fadeOut(200, function() {
            self.render.call(self);
            self.podLinkContainer.fadeIn(200);
        });
    },

    render: function() {
        if (this.model.get('pods').pageCount == 0) {
            this.prevButton.addClass('disabled');
        } else {
            this.prevButton.removeClass('disabled')
        }

        this.podLinkContainer.empty();
        this.model.get('pods').forEach(function(pod) {
            var podListItemView = new PodListItemView({model: pod});
            this.podLinkContainer.append(podListItemView.el);
        }, this);
    }
});