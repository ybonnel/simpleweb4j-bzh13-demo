


function MainCtrl($scope, GaletteService) {
    $scope.galettes = GaletteService.query();
    $scope.delete = function(galette) {
        $('#delete' + galette.id).modal('hide');
        GaletteService.delete({id:galette.id}, function(data){
            $scope.galettes = GaletteService.query();
        });
    }
}

function EditCtrl($scope, $routeParams, $location, GaletteService) {
    $scope.galette = GaletteService.get({id:$routeParams.id});
    $scope.submitMessage = "Update Galette";
    $scope.saveGalette = function(galette) {
        if ($scope.form.$invalid) {
            $scope.form.name.$dirty = true;
        } else {
            GaletteService.update(galette, function(data) {
                $location.path('/main');
            });
        }
    };
}

function NewCtrl($scope, $location, GaletteService) {
    $scope.submitMessage = "Save galette";
    $scope.saveGalette = function(galette) {
        if ($scope.form.$invalid) {
            $scope.form.name.$dirty = true;
        } else {
            GaletteService.save(galette, function(data) {
                $location.path('/main');
            });
        }
    };
}


