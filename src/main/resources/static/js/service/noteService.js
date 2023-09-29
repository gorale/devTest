angular.module('js.service.note', [])
    .service('NoteService', ['$http', '$q', function ($http, $q) {

        this.createNote = function (note) {
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

        this.getNoteById = function (note) {
            var deferred = $q.defer();
            let url = `api/note/${note.id}`
            $http.get(url)
                .then(function (response) {
                    deferred.resolve(response);
                })
                .catch(function (error) {
                    deferred.reject(error);
                });
            return deferred.promise;
        }

        this.updateNote = function (note) {
            var deferred = $q.defer();
            $http.put("api/note", note)
                .then(function (response) {
                    deferred.resolve(response);
                })
                .catch(function (error) {
                    deferred.reject(error);
                });
            return deferred.promise;
        }

        this.getAllNotes = function () {
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

        this.deleteNote = function (id) {
            var deferred = $q.defer();
            let url = `api/note/${id}`
            $http.delete(url)
                .then(function (response) {
                    deferred.resolve(response);
                })
                .catch(function (error) {
                    deferred.reject(error);
                });
            return deferred.promise;
        }
    }]);