


function MainCtrl($scope, HelloService) {
    $scope.hellos = HelloService.query();
    $scope.delete = function(hello) {
        $('#delete' + hello.id).modal('hide');
        HelloService.delete({id:hello.id}, function(data){
            $scope.hellos = HelloService.query();
        });
    }
}

function EditCtrl($scope, $routeParams, $location, HelloService) {
    $scope.hello = HelloService.get({id:$routeParams.id});
    $scope.submitMessage = "Update hello";
    $scope.saveHello = function(hello) {
        if ($scope.form.$invalid) {
            $scope.form.name.$dirty = true;
        } else {
            HelloService.update(hello, function(data) {
                $location.path('/main');
            });
        }
    };
}

function NewCtrl($scope, $location, HelloService) {
    $scope.submitMessage = "Save hello";
    $scope.saveHello = function(hello) {
        if ($scope.form.$invalid) {
            $scope.form.name.$dirty = true;
        } else {
            HelloService.save(hello, function(data) {
                $location.path('/main');
            });
        }
    };
}


