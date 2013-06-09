
var services = angular.module('breizhcamp-demo-services', ['ngResource']);


services.factory('GaletteService', function($resource) {
    return $resource('/galette/:id',
        {id: "@id"},
        {update: {method:'PUT'}}
    );
});