var blogComment = angular.module('blogCommentModule', [])

blogComment.factory('BlogCommentFactory', ['$http', '$q', '$routeParams',   
        function($http, $q, $routeParams){

            //Linking backend project
            var url = 'http://localhost:1112/webapp/';

            return {

                addBlogComment : addBlogComment,
            };

            //function to add a new blog comment
            function addBlogComment(blogComment) {

                var deferred = $q.defer();
                var blogId = $routeParams.id;
                $http.post(url + '/blog/comment/new/' + blogId, blogComment).then(
                    function(response) {
                        debugger;
                        deferred.resolve(response.data);
                    }, function(errResponse) {
                        deferred.reject(response.data);
                    }
                );
                return deferred.promise;
            }
        
    
}])