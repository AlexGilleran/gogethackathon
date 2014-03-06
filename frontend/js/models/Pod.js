var baseUrl = restBaseUrl + 'pods';
var data = require('../util/Data');
var ThriftService = require('../thrift/ThriftService');
var thriftService = new ThriftService();

module.exports = Backbone.Model.extend({
    initialize: function() {},

    // url: function() {
    //     return baseUrl + '/' + this.get('id') + '?maxTrip=' + data.cars.get('current').range;
    // }

    sync: function(method, model, options) {
        try {
            thriftService.getPod(model.get('id'), data.cars.get('current').range, function(result) {
                options.success(result);
            });
        } catch (err) {
        	options.error();
        }
    }
});