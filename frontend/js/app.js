Handlebars.registerHelper('times', function(n, block) {
    var accum = '';
    for (var i = 0; i < n; ++i)
        accum += block.fn(i);
    return accum;
});

var MapView = require('./views/MapView'),
    PodsView = require('./views/PodsView'),
    PodInfoView = require('./views/PodInfoView'),
    DropDownView = require('./views/DropDownView');
var Pods = require('./collections/Pods'),
    AppState = require('./models/AppState');
var data = require('./util/data');

var cars = data.cars;
var sorts = data.sorts;
var pods = new Pods();
pods.cars = cars;
pods.sorts = sorts;
pods.listenTo(cars, 'change:current', function() {
    pods.fetch();
});
pods.listenTo(sorts, 'change:current', function() {
    pods.fetch();
});

var appState = new AppState({
    'cars': cars,
    'sorts': sorts,
});
appState.set('pods', pods);
pods.fetch();

var mapView = new MapView({
    model: appState
});
var podsView = new PodsView({
    model: appState
});
var podInfoView = new PodInfoView({
    model: appState
});
var carsDropDown = new DropDownView({
    el: $('#cars-dropdown'),
    model: cars
});
var sortsDropDown = new DropDownView({
    el: $('#sort-dropdown'),
    model: sorts
});