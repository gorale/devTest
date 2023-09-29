angular.module('js.controller.login', [])
    .controller('loginController', ['$scope', 'AuthService', '$location', function ($scope, AuthService, $location) {
        var loggedUser = null;
        $scope.invalidCreds = false;
        $scope.login = {
            username: null,
            password: null
        };

        $scope.login = function () {
            AuthService.login($scope.login.username, $scope.login.password).then(function (user) {
                loggedUser = user;
                $location.path("/");
            }, function (error) {
                console.error(error);
                $scope.invalidCreds = true;
            });
        };
    }]);