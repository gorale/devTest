(function(){

  var app = angular.module('notesApp',['ngRoute', 'ngMaterial']);

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

          if ($location.path() == "/login"){
             return;
          }

          if (!AuthService.isLoggedIn()) {
              event.preventDefault();
              $location.path('/login');
          }
      });
  }]);


  app.service('AuthService', function($http, $q){
        var loggedUser = null;

      function login(username, password) {
          var deferred = $q.defer();
          $http.post("api/login", { username: username, password: password })
              .then(function (response) {
                  loggedUser = response.data;
                  deferred.resolve(loggedUser);
              })
              .catch(function (error) {
                  console.log('Login Error', error);
                  loggedUser = null;
                  deferred.reject(error);
              });
          return deferred.promise;
      }

        function isLoggedIn(){
            console.log(loggedUser != null)
            return loggedUser != null;
        }
        return {
            login : login,
            isLoggedIn: isLoggedIn
        }
  });

    app.service('NoteService', function($http, $q){

        this.createNote = function (note){
            console.log('note',note)
            var deferred = $q.defer();
            $http.post("api/note", note)
                .then(function (response) {
                    deferred.resolve(response);
                })
                .catch(function (error) {
                    deferred.reject(error);
                });
            return deferred.promise;
        }

        this.getAllNotes = function (){
            var deferred = $q.defer();
            $http.get("api/note", null)
                .then(function (response) {
                    deferred.resolve(response);
                })
                .catch(function (error) {
                    deferred.reject(error);
                });
            return deferred.promise;
        }

        this.deleteNote = function (id){
            var deferred = $q.defer();
            let url = `api/note/${id}`
            $http.delete(url, null)
                .then(function (response) {
                    deferred.resolve(response);
                })
                .catch(function (error) {
                    deferred.reject(error);
                });
            return deferred.promise;
        }
    });

  app.controller('loginController', function($scope, AuthService, $location){

    $scope.invalidCreds = false;
    $scope.login = {
        username : null,
        password : null
    };

    $scope.login = function(){
        AuthService.login($scope.login.username, $scope.login.password).then(function(user){
            console.log(user);
            $location.path("/note");
        }, function(error){
            console.error(error);
            $scope.invalidCreds = true;
        });
    };
  });


    app.controller('notesController', function ($scope, NoteService) {

        $scope.notes = [];

        $scope.initNotes = function () {
            NoteService.getAllNotes().then(function (response) {
                $scope.notes = response.data;
                console.log('notes',$scope.notes)
            }, function (error) {
                console.error(error)
            });
        };

    $scope.isEditCreateView = false;

    $scope.newNoteView = function(){
        $scope.isEditCreateView = true;
    };

    $scope.deleteNote = function (id) {
      var r = confirm("Are you sure you want to delete this note?");
      if (r == true){
          NoteService.deleteNote(id).then(function (response) {
              $scope.notes = $scope.notes.filter(function(note) {
                  return note.id !== id;
              });
          }, function (error) {
              console.error(error)
          });
      }
    };

    $scope.viewNote = function(){
        //TODO
    }
  });

    app.controller('modifyNotesController', function ($scope, NoteService) {


        $scope.createNote = function (note) {
            NoteService.createNote(note).then(function (response) {
                $scope.note ={};
                $scope.formOfNote.$setPristine();
                $scope.formOfNote.$setUntouched();
                $scope.notes.push(response.data)

            }, function (error) {
                console.error(error)
            })
        }

        $scope.cancel = function (){
            $scope.note ={};
            $scope.formOfNote.$setPristine();
            $scope.formOfNote.$setUntouched();
        }

        $scope.isEditCreateView = false;

        $scope.newNoteView = function(){
            $scope.isEditCreateView = true;
        };

        $scope.deleteNote = function (i) {
            var r = confirm("Are you sure you want to delete this note?");
            if (r == true){
                //TODO delete the note
            }
        };

        $scope.viewNote = function(){
            //TODO
        }
    });

})();