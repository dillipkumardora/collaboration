var forum = angular.module('forumModule', []);

forum.factory('ForumFactory', ['$http', '$q', '$routeParams',  
        function($http, $q, $routeParams){

            //Linking backend project
            var url = 'http://localhost:1112/webapp/';

            return {

                addForum : addForum,
                fetchForums : fetchForums,
                viewForum : viewForum,
                joinRequest : joinRequest,
                getParticipatedUsers : getParticipatedUsers,
                addForumPost : addForumPost,
                fetchBlogPosts : fetchBlogPosts,
            };

            //function to add a new forum Forum Category
            function addForum(forum) {

                var deferred = $q.defer();

                $http.post(url + '/forum/new', forum).then(
                    function(response) {
                        debugger;
                        deferred.resolve(response.data);
                    }, function(errResponse) {
                        deferred.reject(response.data);
                    }
                );
                return deferred.promise;
            }

            //Function to fetch list of forum categories
            function fetchForums() {
                
                var deferred = $q.defer();

                $http.get(url + '/forum/list').then(
                    function(response) {
                        deferred.resolve(response.data);
                    }, function(errResponse) {
                        deferred.reject(response.data);
                    }
                );
                return deferred.promise;
            }

             //Function for viewing single blog using blog id as a parameter
            function viewForum(id) {
                
                var deferred = $q.defer();

                $http.get(url + '/forum/' + id)
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

            //Function to send forum join request
            function joinRequest() {
                
                var deferred = $q.defer();
                var id = user.id;
                var forumId =  $routeParams.id;
                $http.post(url + '/forum/request/' + id, forumId)
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

            //Function to fetch the list of ParticipatedUsers
            function getParticipatedUsers(id) {
                
                var deferred = $q.defer();
                $http.get(url + '/forum/participatedUsers/list/' + id)
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
           
            //function to add a new forum post
            function addForumPost(forumPost) {

                var deferred = $q.defer();
                var forumId = $routeParams.id;
                $http.post(url + '/forum/post/new/' + forumId, forumPost).then(
                    function(response) {
                        debugger;
                        deferred.resolve(response.data);
                    }, function(errResponse) {
                        deferred.reject(response.data);
                    }
                );
                return deferred.promise;
            }

        //Function to fetch forum post list
        function fetchBlogPosts(forumId) {
            console.log('Inside factory now');
            var deferred = $q.defer();
            
            $http.get(url + '/forum/posts/list/' + forumId)
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