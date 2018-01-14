blogComment.controller('blogCommentController', ['BlogCommentFactory', 
        '$timeout', '$cookies', '$routeParams', '$location', function(BlogCommentFactory, 
        $timeout, $cookies, $routeParams, $location) {

    var self = this;

    ///setting up the fields for creating new blog comment - should be same as fields in the entity class
    self.blogComment = {

        id : null,
        blogComment : ' ',
        commentDate : '',
        userId : '',
        username : '',
    }

    //function for adding a new blog comment
    self.addBlogComment = function () {

        //Setting the user id and username
        self.blogComment.userId = user.id;
        self.blogComment.username = user.username;
         //calling the addBlog method in the factory
         BlogCommentFactory.addBlogComment(self.blogComment)
            .then (
                function(blogComment) {
                    self.blogComment =  blogComment;
                    var bId = self.blog.id; 
                    $location.path('/blog/' + bId);
                }, function (errResponse) {
                    console.error('Failure!');
                }
            );
         
    }

        
    
}])