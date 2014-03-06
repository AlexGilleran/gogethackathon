var Pod = require('../models/Pod')
var ThriftService = require('../thrift/ThriftService');
var thriftService = new ThriftService();
var baseUrl = restBaseUrl + 'pods';

module.exports = Backbone.Collection.extend({
    model: Pod,
    pageCount: 0,
    pageSize: 9,
    initialize : function() {
    },
    url: function() {
        // TODO: refer to max trip length from model.
        return baseUrl + '?maxTrip=' + this.cars.get('current').range + '&sort=' + this.sorts.get('current').key + '&from=' + this.pageFrom() + '&to=' + this.pageTo();
    },
    pageFrom: function() {
        return Math.max(0, this.pageCount * this.pageSize);
    },
    pageTo: function() {
        return Math.max(this.pageSize, this.pageCount * this.pageSize + this.pageSize);
    },
    prevPage: function() {
        if (this.pageCount > 0) {
            this.pageCount--;
        }
        this.fetch();
    },
    nextPage: function() {
        this.pageCount++;
        this.fetch();
    },
    sync: function(method, model, options) {
        try {
            //maxTrip, from, to, sort, callback
            thriftService.getPods(this.cars.get('current').range, this.pageFrom(), this.pageTo(), Sort[this.sorts.get('current').key], function(result) {
                options.success(result);
            });
        } catch (err) {
            options.error();
        }
    }
});