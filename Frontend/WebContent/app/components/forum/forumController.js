forum.controller('forumController', ['ForumFactory', '$timeout', '$cookies', '$routeParams', '$location', '$route', '$q',
function(ForumFactory, 
        $timeout, $cookies, $routeParams, $location, $route, $q) {

            var self = this;

            //Setting up the field for creating new forum category
            self.Forum = {

                id : null,
                name : '',
                description : '',
                postDate : '',
                userId : '',
                userName : ''
            }

            //Setting up the field for creating new forum post
            self.ForumPost = {

                id : null,
                title : '',
                description : '',
                postDate : '',
                userId : '',
                username : ''
            }

            //array for displaying list of forum categories
            self.forums = [];

             // For viewing single forum
             self.singleForum = {};

             //Setting up creator of the forum
             self.singleForumUser = {};

             //For list of participated users
             self.participatedUsers = []; 

             //Flag to see whether user is particant or not
            self.isParticipant = false;

            //Flag to check request status
            self.isApproved = false;

            //For storing participant status
            self.participantStatus = "PENDING";

            //For list of forum posts
            self.forumPostsList = [];

            self.forumPostUser = [];

            // calling jQuery once controller has loaded
            $timeout(function () {
                setting();
            }, 100);

            //method for adding new category
            self.addForum = function() {
                
                //Setting the user id and username
                self.Forum.userId = user.id;
                self.Forum.userName = user.username;
                
                ForumFactory.addForum(self.Forum) 
                        .then(
                            function(forum) {
                                self.Forum = forum;
                                $route.reload();
                                $('#category').modal('close');
                            }, function(errResponse) {
                                
                            }
                        );
            }

           
            //method to fetch all the forum categories
             self.fetchForums = function() {
                debugger;
                
                ForumFactory.fetchForums().then(
                        function(forums) {
                            self.forums = forums;
                    }, function(errResponse) {
                        }
                    );
            }

            //function for viewing single forum
            self.viewForum = function() {
                //Fetch participated users first for this forum
                 getParticipatedUsers().then(
                        function(participatedUsers){
                            self.participatedUsers = participatedUsers; //store list of participated users in already defined array
                            for(var id in self.participatedUsers) {
                                if(user.id == self.participatedUsers[id].userId) { 
                                    self.isParticipant = true;  /*If active user is present in the list of participant set the flag as true & store its fetch its request status*/
                                    self.participantStatus = self.participatedUsers[id].status;                       
                                    break;                     
                                }
                            }
                            if(self.participantStatus == "APPROVED") {    //if user is participant
                                         self.isApproved = true;
                            }
                            //fetching single forum page here
                            //Assigning forum id to variable forumId
                            var forumId = $routeParams.id;
                            ForumFactory.viewForum(forumId)
                                .then (
                                    function(forumModel) {
                                        self.singleForum = forumModel.forum;
                                        self.singleForumUser = forumModel.user;
                                        self.singleForum.postDate = new Date(self.singleForum.postDate[0],self.singleForum.postDate[1] - 1,self.singleForum.postDate[2]);
                                        fetchBlogPosts();
                                    },
                                    function(errResponse) {
                                    }
                                );
                        } 
                 );

            }

            //Function to send forum join request
            self.joinRequest = function() {
                 ForumFactory.joinRequest()
                    .then (
                        function(forum) {
                         $route.reload();
                         Materialize.toast('Request to join the forum sent!', 3000);
                         self.viewForum();

                        },
                        function(errResponse) {
                        }
                    );
            }

            //Function to fetch the list of participated users
            function getParticipatedUsers() {
                var deferred = $q.defer();
                var forumId = $routeParams.id;
                ForumFactory.getParticipatedUsers(forumId)
                    .then (
                        function(participatedUsers) {
                            
                         deferred.resolve(participatedUsers);
                        },
                        function(errResponse) {
                        }
                    );

                    return deferred.promise;
            }

            //function for adding a new forum post
            self.addForumPost = function () {

                //Setting the user id and username
               self.ForumPost.userId = user.id;
               self.ForumPost.username = user.username;
                // self.blogComment.blogId = $routeParams.id;
                //calling the addBlog method in the factory
                ForumFactory.addForumPost(self.ForumPost)
                    .then (
                        function(ForumPost) {
                            self.ForumPost =  ForumPost;
                             Materialize.toast('Post successfully added!', 2000);
                            $route.reload();
                             $('#leaveAPost').modal('close');
                        }, function (errResponse) {
                            
                        }
                    );
                
            }

            //Method to fetch forum posts
            function fetchBlogPosts() {
                var forumId = $routeParams.id;
                ForumFactory.fetchBlogPosts(forumId)
                    .then (
                        function(forumPosts) {
                            debugger;
                            self.forumPostsList = forumPosts;
                            for(var postDate in self.forumPostsList) {
                                self.forumPostsList[postDate].postDate = new Date(self.forumPostsList[postDate].postDate[0],self.forumPostsList[postDate].postDate[1] - 1,self.forumPostsList[postDate].postDate[2]);
                            }
                        },
                        function(errResponse) {
                        }
                    );
            }

}])