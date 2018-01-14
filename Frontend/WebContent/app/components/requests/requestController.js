request.controller('requestController', ['requestFactory',
        '$timeout', '$cookies', '$routeParams', '$location', '$route', '$rootScope', function(requestFactory,
        $timeout, $cookies, $routeParams, $location, $route, $rootScope) {

    var self = this;

     //For storing list of pending users
    self.pendingUsers = [];

     //For fetching list of forumRequest with pending status
    self.forumRequest = [];

    //For list of pending blogs
    self.pendingBlogs = [];

    //For pending jobs
    self.pendingJobs = [];

    //For pending events
    self.pendingEvents = [];

    

      // calling jQuery once controller has loaded
    $timeout(function () {
        setting();
    }, 100);

      //Function to view list of all pending users
        self. pendingUserList = function() {

        // var status = "APPROVED"
        
        requestFactory.pendingUserList()
            .then (
                function(pendingUsers) {
               
                    self.pendingUsers = pendingUsers;
                    for(var birthDate in self.pendingUsers) {
                        self.pendingUsers[birthDate].birthDate = new Date(self.pendingUsers[birthDate].birthDate[0],self.pendingUsers[birthDate].birthDate[1] - 1,self.pendingUsers[birthDate].birthDate[2]);
                    }
                    console.log(self.pendingUsers); 
                    console.log(self.pendingUsers.length);
                    $rootScope.notifyUserCount = self.pendingUsers.length;                   
                },
                function(errResponse) {
                }
            );
    }

    //Function to change status of user registration
    self.changeStatus = function(id) {
        
        requestFactory.changeStatus(id)
            .then (
                function(user) {
                    $route.reload();
                },
                function(errResponse) {
                }
            );
    }
            
            //Function to fetch forum requests
            self.fetchForumRequests = function() {
                
                requestFactory.fetchForumRequests()
                    .then (
                        function(forumRequests) {
                           self.forumRequest = forumRequests;
                        },
                        function(errResponse) {
                        }
                    );

            }

            //Function to change status of forumRequests
            self.changeFRStatus = function(id) {
                
                requestFactory.changeFRStatus(id)
                    .then (
                        function(forumRequest) {
                            $route.reload();
                        },
                        function(errResponse) {
                        }
                    );
    }

     //Function to fetch pending blog list
    self.pendingBlogList = function() {
         requestFactory.pendingBlogList()
            .then (
                function(pendingBlogs) {
                    self.pendingBlogs = pendingBlogs;
                    for(var postDate in self.pendingBlogs) {
                        self.pendingBlogs[postDate].postDate = new Date(self.pendingBlogs[postDate].postDate[0],self.pendingBlogs[postDate].postDate[1] - 1,self.pendingBlogs[postDate].postDate[2]);
                    }
                },
                function(errResponse) {
                }
            );

    }

    //Function to change blog status
    self.changeBlogStatus = function(id) {
        debugger;
         requestFactory.changeBlogStatus(id)
            .then (
                function(blog) {
                    $route.reload();
                    Materialize.toast('Blog Approved!', 2000);
                    pendingBlogList();
                     
                },
                function(errResponse) {
                }
            );
    }

     //Function to view list of all pending jobs
        self. pendingJobList = function() {
        
        requestFactory.pendingJobList()
            .then (
                function(pendingJobs) {
                     self.pendingJobs = pendingJobs;
                    for(var postDate in self.pendingJobs) {
                        self.pendingJobs[postDate].postDate = new Date(self.pendingJobs[postDate].postDate[0],self.pendingJobs[postDate].postDate[1] - 1,self.pendingJobs[postDate].postDate[2]);
                    }
                },
                function(errResponse) {
                }
            );
    }

    //Function to approve job
    self.approveJob = function(id) {
        debugger;
         requestFactory.approveJob(id)
            .then (
                function(job) {
                    $route.reload();
                    Materialize.toast('Job Approved!', 2000);
                     
                },
                function(errResponse) {
                }
            );
    }

     //Function to view list of all pending events
        self.pendingEventList = function() {
        
        requestFactory.pendingEventList()
            .then (
                function(pendingEvents) {
                     self.pendingEvents = pendingEvents;
                    for(var postDate in self.pendingEvents) {
                        self.pendingEvents[postDate].postDate = new Date(self.pendingEvents[postDate].postDate[0],self.pendingEvents[postDate].postDate[1] - 1,self.pendingEvents[postDate].postDate[2]);
                    }
                     for(var startDate in self.pendingEvents) {
                        self.pendingEvents[startDate].startDate = new Date(self.pendingEvents[startDate].startDate[0],self.pendingEvents[startDate].startDate[1] - 1,self.pendingEvents[startDate].startDate[2]);
                    }
                     for(var endDate in self.pendingEvents) {
                        self.pendingEvents[endDate].endDate = new Date(self.pendingEvents[endDate].endDate[0],self.pendingEvents[endDate].endDate[1] - 1,self.pendingEvents[endDate].endDate[2]);
                    }
                },
                function(errResponse) {
                }
            );
    }

     //Function to approve event
    self.approveEvent = function(id) {
        debugger;
         requestFactory.approveEvent(id)
            .then (
                function(event) {
                    $route.reload();
                    Materialize.toast('Event Approved!', 2000);
                },
                function(errResponse) {
                }
            );
    }
        
    
}])