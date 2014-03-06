var templates = require('../templates')(Handlebars);
module.exports = Backbone.View.extend({
    initialize: function() {
        this.render();

        var self = this;
        this.listenTo(this.model, 'change:current', function(model, current) {
            self.$el.find('.dropdown-caption').text(current.name);
        });
    },

    render: function() {
        var html = templates['./templates/dropdown.hbs']({
            current: this.model.get('current')
        });
        this.$el.html(html);
        var menu = this.$el.find('.dropdown-menu');
        var self = this;
        this.model.get('options').forEach(function(option) {
            var optionHtml = templates['./templates/dropdown-option.hbs']({
                name: option.name
            });
            menu.append(optionHtml);
            menu.find('.dropdown-option').last().click(option, function(eventObject) {
                self.model.set('current', eventObject.data);
            });
        }, this);
    }
});