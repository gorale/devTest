angular.module('js.service.auth', [])
    .service('AuthService', ['$http', '$q', '$location', function ($http, $q, $location) {
        var loggedUser = loggedUser ? sessionStorage.getItem('loggedUser') : null;

        function login(username, password) {
            var deferred = $q.defer();
            $http.post("api/login", {username: username, password: password})
                .then(function (response) {
                    loggedUser = response.data;
                    sessionStorage.setItem('loggedUser', JSON.stringify(loggedUser));
                    deferred.resolve(loggedUser);
                })
                .catch(function (error) {
                    console.log('Login Error', error);
                    loggedUser = null;
                    deferred.reject(error);
                });
            return deferred.promise;
        }

        function isLoggedIn() {
            return sessionStorage.getItem('loggedUser');
        }

        function logout() {
            loggedUser = null;
            sessionStorage.removeItem('loggedUser');
            $location.path("/login");
        }

        return {
            login: login,
            isLoggedIn: isLoggedIn,
            logout: logout

        }
    }]);