blog.controller('blogController', ['blogFactory', 'BlogCommentFactory',
        '$timeout', '$cookies', '$routeParams', '$location', '$route', function(blogFactory, BlogCommentFactory,
        $timeout, $cookies, $routeParams, $location, $route) {

    var self = this;

    ///setting up the fields for creating new blog - should be same as fields in the entity class
    self.blog = {

        id : null,
        name : ' ',
        description : '',
        postDate : '',
        userId : '',
        userName : '',
    }

    ///setting up the fields for creating new blog comment - should be same as fields in the entity class
    self.blogComment = {

        id : null,
        title : '',
        blogComment : ' ',
        commentDate : '',
        userId : '',
        username : '',
    }

    // For viewing single Blog
    self.singleBlog = {};
    
    // For posting date on blog
    self.postDate = {};

    self.bloglist = [];

    //calling list of user's blogs
    self.myblogs = [];

    //for displaying blog comments
    self.blogCommentList = [];

     // calling jQuery once controller has loaded
    $timeout(function () {
        setting();
    }, 100);

    //function for adding a new blog
    self.addBlog = function () {

        //Setting the user id and username
        self.blog.userId = user.id;
        self.blog.userName = user.username;
         //calling the addBlog method in the factory
         blogFactory.addBlog(self.blog)
            .then (
                function(blog) {
                    self.blog =  blog;
                    var bId = self.blog.id 
                    $location.path('/blog/' + bId);
                }, function (errResponse) {
                }
            );
         
    }

    //function for viewing single blog
    self.viewBlog = function() {
        //Assigning blog id to variable blogId
        var blogId = $routeParams.id;
        blogFactory.viewBlog(blogId)
            .then (
                function(blog) {
                    self.singleBlog = blog;
                    self.singleBlog.postDate = new Date(self.singleBlog.postDate[0],self.singleBlog.postDate[1] - 1,self.singleBlog.postDate[2]);
                    blogCommentlist();  //Fetching blog comment list
                },
                function(errResponse) {
                }
            );

    }

    //Function to add likes to blog
    self.likes = function(id) {      
        blogFactory.likes(id)
            .then (
                function(blog) {
                    $route.reload();
                },
                function(errResponse) {
                }
            );
    }

    //function for adding a new blog comment
    self.addBlogComment = function () {

        //Setting the user id and username
        self.blogComment.userId = user.id;
        self.blogComment.username = user.username;
        // self.blogComment.blogId = $routeParams.id;
         //calling the addBlog method in the factory
         BlogCommentFactory.addBlogComment(self.blogComment)
            .then (
                function(blogComment) {
                    self.blogComment =  blogComment;
                    $route.reload();
                   
                }, function (errResponse) {
                    
                }
            );
         
    }

    //Function to view list of all blogs
    self.bloglist = function() {

        // var status = "APPROVED"
        blogFactory.bloglist()
            .then (
                function(blogs) {
               
                    self.bloglist = blogs;
                    for(var [blog] in self.bloglist) {
                        self.bloglist[blog].postDate = new Date(self.bloglist[blog].postDate[0],self.bloglist[blog].postDate[1] - 1,self.bloglist[blog].postDate[2]);
                    }
                },
                function(errResponse) {
                }
            );
    }

    
    

    //Function to view list of all blog comments
    function blogCommentlist() {

        var blogId = $routeParams.id;
        blogFactory.blogCommentlist(blogId)
            .then (
                function(blogComments) {
                    debugger;
                    self.blogCommentList = blogComments;
                    for(var [blogComment] in self.blogCommentList) {
                        self.blogCommentList[blogComment].commentDate = new Date(self.blogCommentList[blogComment].commentDate[0],self.blogCommentList[blogComment].commentDate[1] - 1,self.blogCommentList[blogComment].commentDate[2]);
                    }
                },
                function(errResponse) {
                }
            );
    }
    
        }]);