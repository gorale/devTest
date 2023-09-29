angular.module('js.controller.notes', [])
    .controller('notesController', ['$scope', 'NoteService', function ($scope, NoteService) {

        $scope.notes = [];

        $scope.initNotes = function () {
            NoteService.getAllNotes().then(function (response) {
                $scope.notes = response.data;
            }, function (error) {
                console.error(error)
            });
        };

        $scope.isEditCreateView = false;

        $scope.newNoteView = function () {
            $scope.note = {}
            $scope.isEditCreateView = true;
        };

        $scope.deleteNote = function (id, event) {
            var isConfirmDelete = confirm("Are you sure you want to delete this note?");
            if (isConfirmDelete) {
                event.stopPropagation();
                NoteService.deleteNote(id).then(function (response) {
                    $scope.notes = $scope.notes.filter(function (note) {
                        return note.id !== id;
                    });
                    $scope.note = {}
                }, function (error) {
                    console.error(error)
                });
            }
        };

        $scope.viewNote = function (note) {
            NoteService.getNoteById(note).then(function (response) {
                $scope.note = response.data;
                $scope.isEditCreateView = true;
            }, function (error) {
                console.error(error);
            })
        }

        $scope.createNote = function (note, formOfNote) {
            if (note.id) {
                NoteService.updateNote(note).then(function (response) {
                    $scope.note = {};
                    formOfNote.$setPristine();
                    formOfNote.$setUntouched();
                    $scope.initNotes();
                }, function (error) {
                    console.error(error)
                })
            } else {
                NoteService.createNote(note).then(function (response) {
                    $scope.note = {};
                    formOfNote.$setPristine();
                    formOfNote.$setUntouched();
                    $scope.notes.push(response.data)

                }, function (error) {
                    console.error(error)
                })
            }
        }

        $scope.cancel = function (formOfNote) {
            $scope.note = {};
            formOfNote.$setPristine();
            formOfNote.$setUntouched();
        }
    }]);