
var services = angular.module('breizhcamp-demo-services', ['ngResource']);


services.factory('GaletteService', function($resource) {
    return $resource('/galette/:id?login=admin&password=admin',
        {id: "@id"},
        {update: {method:'PUT'}}
    );
});
