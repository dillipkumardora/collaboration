var user = angular.module('userModule', []);

user.directive('fileModel', ['$parse', 
    function ($parse){
        return {
            restrict : 'A',
            link : function (scope, element, attrs) {
                var model = $parse(attrs.fileModel);
                var modelSetter = model.assign;
                element.bind('change', function() {
                    scope.$apply(function () {
                        modelSetter(scope, element[0].files[0]);
                    });
                });
            }
        };
    }]);

user.factory('userFactory', ['$http', '$q', '$rootScope', '$routeParams',

    function($http, $q, $rootScope, $routeParams) {

        //For linking backend project with the frontend
        var url = 'http://localhost:1112/webapp/';
        
        return {
            userEventList : userEventList,
            uploadFile : uploadFile,
            fetchUser : fetchUser,
            fetchContain : fetchContain,
            fetchMyFriends : fetchMyFriends,
            fetchOnlineFriends : fetchOnlineFriends
        };

         //Function to fetch user event list
         
         function userEventList(id) {
            console.log('Inside factory now');
            var deferred = $q.defer();

            $http.get(url + '/user/events/list/'+ id)
                .then (
                    function(response) {
                        deferred.resolve(response.data);
                    },
                    function(errResponse) {
                        deferred.reject(errResponse);
                    }
                );
                return deferred.promise;
        }

        // uploadFile function to upload the image on the server
         function uploadFile(file) {
                 var deferred = $q.defer();
                
                 var fd = new FormData();
                 fd.append('file', file);
                fd.append('id', $rootScope.user.id);
                $http.post(url + '/upload/profile-picture', fd, {
                transformRequest: angular.identity,
                headers: { 'Content-Type': undefined }
               })
                .then(
                    function (response) {
                    deferred.resolve(response.data);
                },
                    function (error) {
                    console.log(error);
                    deferred.reject(error);
                }
            );
        return deferred.promise;
        }

        //function to fetch user and user detail
        function fetchUser(id) {
             var deferred = $q.defer();

              $http.get(url + '/user/'+ id)
                .then (
                    function(response) {
                        deferred.resolve(response.data);
                    },
                    function(errResponse) {
                        deferred.reject(errResponse);
                    }
                );
                return deferred.promise;

        }

        //function to fetch main page contain
        function fetchContain() {
             var deferred = $q.defer();

              $http.get(url + '/main/contain')
                .then (
                    function(response) {
                        deferred.resolve(response.data);
                    },
                    function(errResponse) {
                        deferred.reject(errResponse);
                    }
                );
                return deferred.promise;
        }

        // function to fetch user's friends
        function fetchMyFriends() {
             var deferred = $q.defer();

             var userId = $routeParams.id;
              $http.get(url + '/my/friends/' + userId)
                .then (
                    function(response) {
                        deferred.resolve(response.data);
                    },
                    function(errResponse) {
                        deferred.reject(errResponse);
                    }
                );
                return deferred.promise;
        }

        //function to fetch my online friends
         function fetchOnlineFriends() {
             var deferred = $q.defer();

             var userId = user.id;
              $http.get(url + '/my/online/friends/' + userId)
                .then (
                    function(response) {
                        deferred.resolve(response.data);
                    },
                    function(errResponse) {
                        deferred.reject(errResponse);
                    }
                );
                return deferred.promise;
        }

    
    }
])