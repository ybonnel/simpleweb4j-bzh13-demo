
var services = angular.module('breizhcamp-demo-services', ['ngResource']);


services.factory('HelloService', function($resource) {
    return $resource('/hello/:id',
        {id: "@id"},
        {update: {method:'PUT'}}
    );
});