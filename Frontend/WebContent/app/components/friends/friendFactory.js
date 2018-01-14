var friend = angular.module('friendModule',  []);

friend.factory('friendFactory', ['$http', '$q', 

    function ($http, $q) {

        //For linking backend project with the frontend
        var url = 'http://localhost:1112/webapp/';

        return {
            fetchUsers : fetchUsers,
            sendRequest : sendRequest,
            fetchRequest : fetchRequest,
            approveRequest : approveRequest,
            checkUsersFriends : checkUsersFriends
        };

        //Function to fetch users 
        function fetchUsers() {
            debugger;
            var deferred = $q.defer();

            var userId = user.id;

            $http.get(url + '/user/friends/model/' + userId)
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

        //function to send friend request
        function sendRequest(id) {
            var deferred = $q.defer();

            var initId = user.id
            $http.post(url + '/user/friendRequest/' + id, initId)
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

        //function to fetch friend requests
        function fetchRequest() {
            var deferred = $q.defer();

            var userId = user.id
            $http.get(url + '/user/friendRequest/list/' + userId)
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

        //function to approve friend request
        function approveRequest(id) {
            debugger;
            var deferred = $q.defer();

            var userId = user.id
            $http.post(url + '/user/friendRequest/approve/' + id, userId)
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

        //Function to check user's friends
        function checkUsersFriends() {
             var deferred = $q.defer();

            var userId = user.id
            $http.get(url + '/user/friends/check/' + userId)
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
    

}])