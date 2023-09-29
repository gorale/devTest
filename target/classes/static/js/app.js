(function () {

    var app = angular.module('notesApp', ['ngRoute',
        'ngMaterial',
        'js.controller.login',
        'js.controller.logout',
        'js.controller.notes',
        'js.service.auth',
        'js.service.note']);

    app.config(['$locationProvider', '$routeProvider',
        function ($locationProvider, $routeProvider) {

            $routeProvider
                .when('/', {
                    templateUrl: '/partials/notes-view.html',
                    controller: 'notesController'
                })
                .when('/login', {
                    templateUrl: '/partials/login.html',
                    controller: 'loginController',
                })
                .otherwise('/');
        }
    ]);

    app.run(['$rootScope', '$location', 'AuthService', function ($rootScope, $location, AuthService) {
        $rootScope.$on('$routeChangeStart', function (event) {

            if ($location.path() === "/login") {
                return;
            }

            if (!AuthService.isLoggedIn()) {
                event.preventDefault();
                $location.path('/login');
            }
        });
    }]);

})();