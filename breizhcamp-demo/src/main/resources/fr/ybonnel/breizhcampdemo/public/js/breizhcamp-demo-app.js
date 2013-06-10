
angular.module('breizhcamp-demo',
        ['breizhcamp-demo-services'])
    .config(['$routeProvider',function($routeProvider) {
        $routeProvider
            .when('/main', {
                templateUrl: 'partial/main.html',
                controller: MainCtrl
            })
            .when('/carte', {
                templateUrl: 'partial/carte.html',
                controller: MainCtrl
            })
            .when('/edit/:id', {
                templateUrl: 'partial/editOrNew.html',
                controller: EditCtrl
            })
            .when('/new', {
                templateUrl: 'partial/editOrNew.html',
                controller: NewCtrl
            })
            .otherwise({
                redirectTo: '/carte'
            })
    }]);