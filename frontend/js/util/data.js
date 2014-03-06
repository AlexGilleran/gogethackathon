var DropDownOptions = require('../models/DropDownOptions');

var cars = new DropDownOptions();
var nissanLeaf = {
    name: '2013 Nissan Leaf (135km range)',
    shortName: 'Nissan Leaf',
    range: 135
}
cars.set('current', nissanLeaf);
cars.set('options', [{
        name: '2012 Toyota Prius Plug-In (18km electric range)',
        shortName: 'Prius Plugin',
        range: 18
    }, {
        name: '2013 Chevrolet Volt (61km electric range)',
        shortName: 'Chevrolet Volt',
        range: 61
    },
    nissanLeaf, {
        name: '2013 Tesla Model S w/ Hi-Cap batteries (426km range)',
        shortName: 'Tesla Model S',
        range: 426
    }
]);

sorts = new DropDownOptions();
var co2 = {
    name: 'CO2 Saved',
    key: 'CO2'
}
sorts.set('current', co2);
sorts.set('options', [co2, {
    name: 'Fuel Saved',
    key: 'FUEL'
}, {
    name: '% of trips Saveable',
    key: 'PERCENTAGE'
}]);

module.exports = {
    'cars': cars,
    'sorts': sorts
};