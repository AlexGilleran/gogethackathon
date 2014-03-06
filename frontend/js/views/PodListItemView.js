var templates = require('../templates')(Handlebars);
var data = require('../util/data');
module.exports = Backbone.View.extend({
    tagName: "a",
    className: "list-group-item",
    attributes: {
        href: '#'
    },
    initialize: function() {
        this.render();

        var self = this;
        this.$el.click(function() {
            self.model.set('isSelected', true);
            return false;
        });
        this.model.on('change', this.render());
    },
    render: function() {
        var badge;
        var currentSort = data.sorts.get('current');
        if (currentSort.key === Sort['CO2']) {
            badge = this.model.get('saveableCo2').toFixed(1) + ' kg';
        } else if (currentSort.key === Sort['PERCENTAGE']) {
            badge = Math.round(this.model.get('saveableTripFraction') * 100) + '%';
        } else if (currentSort.key === Sort['FUEL']) {
            badge = this.model.get('saveableFuel').toFixed(1) + ' L';
        }

        var html = templates['./templates/pod-link.hbs']({
            number: this.model.get('placeInOrder'),
            name: this.model.get('name'),
            'badge': badge
        });
        this.$el.empty();
        this.$el.html(html);

        if (this.model.get('isSelected')) {
            this.$el.addClass('active');
        } else {
            this.$el.removeClass('active');
        }
    }
});