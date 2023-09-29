angular.module('js.controller.logout', [])
    .controller('logoutController', ['$scope', 'AuthService', function ($scope, AuthService) {
        $scope.logout = function () {
            AuthService.logout();
        }
    }]);