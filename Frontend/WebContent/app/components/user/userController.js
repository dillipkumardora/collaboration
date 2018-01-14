user.controller('userController', 
[
    'userFactory',
    'AuthenticationFactory',
    'ForumFactory',
    'blogFactory',
    'jobFactory',
    'eventFactory', 
    '$timeout', 
    '$cookies', 
    '$routeParams', 
    '$location',
    '$rootScope',
    '$route', 
    
    function(
        userFactory, 
        AuthenticationFactory, 
        ForumFactory, 
        blogFactory, 
        jobFactory, 
        eventFactory, 
        $timeout, 
        $cookies, 
        $routeParams, 
        $location,
        $rootScope,
        $route) {

    var self = this;

    //Load user from cookie
    self.user = AuthenticationFactory.loadUserFromCookie();

    self.contain = [];

    //Fetching list of events created by user
    self.myEvents = [];

    self.picture = undefined;

    self.user.profile = self.user.profile + '?decached=' + Math.random();

    self.hasApplied = false;

    self.appliedJobCount = []; 

    self.appliedFor = [];

    self.joinedEventCount = [];

    self.user = [];

    self.myFriends = [];

    self.friendsCount = [];

    self.myOnlineFriends = [];

    self.countOnlineFriends = [];

    self.myBlogCount = [];
    
     // calling jQuery once controller has loaded
    $timeout(function () {
        setting();
    }, 100);


    //fetching blogs, forum, jobs and events on the page
    self.fetchContain = function () {
        
        console.log('Method called!');
        userFactory.fetchContain()
            .then(
                function(data) {
                    debugger;
                    self.contain = data;
                     for(var [postDate] in  self.contain.top3Events) {
                        self.contain.top3Events[postDate].postDate = new Date(self.contain.top3Events[postDate].postDate[0],self.contain.top3Events[postDate].postDate[1] - 1,self.contain.top3Events[postDate].postDate[2]);   
                    }
                    for(var [startDate] in self.contain.top3Events) {
                        self.contain.top3Events[startDate].startDate = new Date(self.contain.top3Events[startDate].startDate[0],self.contain.top3Events[startDate].startDate[1] - 1,self.contain.top3Events[startDate].startDate[2]);
                    }
                    for(var [endDate] in self.contain.top3Events) {
                        self.contain.top3Events[endDate].endDate = new Date(self.contain.top3Events[endDate].endDate[0],self.contain.top3Events[endDate].endDate[1] - 1,self.contain.top3Events[endDate].endDate[2]);
                    }

                }, function(errResponse) {

                }
            )
    }

    // to upload the file    
    self.uploadFile = function () {
        if(self.picture == undefined) {
            return;
        }    
    	
        userFactory.uploadFile(self.picture)
        .then(
            function(response){
               
                $rootScope.message = 'Profile picture updated successfully!';
                self.user.profile = response.message + '?decached=' + Math.random();
                // update the controller user too
                $rootScope.user.profile = response.message + '?decached=' + Math.random();
                // need to update the cookie value too
                AuthenticationFactory.saveUser($rootScope.user);
                 $('#change-photo').modal('close');
                // hide the card panel by setting the rootScope.message as undefined
                // $timeout(function() {                    
                //     $rootScope.message = undefined;
                // },2000);

            },
            function(error){
                console.log(error);
            } 
        )
    

    }

    //Method to apply for job
    self.applyJob = function(id) {
          jobFactory.applyJob(id)
            .then (
                function(job) {
                debugger;
                 $route.reload();
                 Materialize.toast('Applied for job successfully!', 3000);
                 self.appliedFor.push(id);  
                },
                function(errResponse) {
                }
            );
    }

    //Method to join event
    self.joinEvent = function(id) {
          eventFactory.joinEvent(id)
            .then (
                function(event) {
                
                 $route.reload();
                 Materialize.toast('Event joined successfully!', 3000);
                 self.appliedFor.push(id);  
                },
                function(errResponse) {
                }
            );
    }

     //function to fetch user and user detail
     self.fetchUser = function() {
        
         var id = $routeParams.id;
          userFactory.fetchUser(id)
                .then (
                    function(user) {
                        debugger;
                        self.user = user;
                        self.user.user.birthDate = new Date( self.user.user.birthDate[0], self.user.user.birthDate[1] - 1, self.user.user.birthDate[2]);

                        self.myBlogCount = self.user.blog.length;
                        self.joinedEventCount = self.user.joinedEvents.length;
                        self.appliedJobCount = self.user.appliedJobList.length;

                        for(var [postDate] in self.user.events) {
                        self.user.events[postDate].postDate = new Date(self.user.events[postDate].postDate[0],self.user.events[postDate].postDate[1] - 1,self.user.events[postDate].postDate[2]);   
                        }
                        for(var [startDate] in self.user.events) {
                            self.user.events[startDate].startDate = new Date(self.user.events[startDate].startDate[0],self.user.events[startDate].startDate[1] - 1,self.user.events[startDate].startDate[2]);
                        }
                        for(var [endDate] in self.user.events) {
                            self.user.events[endDate].endDate = new Date(self.user.events[endDate].endDate[0],self.user.events[endDate].endDate[1] - 1,self.user.events[endDate].endDate[2]);
                        }
                         for(var [postDate] in self.user.joinedEvents) {
                        self.user.joinedEvents[postDate].postDate = new Date(self.user.joinedEvents[postDate].postDate[0],self.user.joinedEvents[postDate].postDate[1] - 1,self.user.joinedEvents[postDate].postDate[2]);   
                    }
                     for(var [startDate] in self.user.joinedEvents) {
                            self.user.joinedEvents[startDate].startDate = new Date(self.user.joinedEvents[startDate].startDate[0],self.user.joinedEvents[startDate].startDate[1] - 1,self.user.joinedEvents[startDate].startDate[2]);
                        }
                        for(var [endDate] in self.user.joinedEvents) {
                            self.user.joinedEvents[endDate].endDate = new Date(self.user.joinedEvents[endDate].endDate[0],self.user.joinedEvents[endDate].endDate[1] - 1,self.user.joinedEvents[endDate].endDate[2]);
                        }
                    
                    
                    },
                    function(errResponse) {
                        
                    }
                );
     }

     self.fetchMyFriends = function() {
         debugger;
          userFactory.fetchMyFriends()
            .then (
                function(myFriends) {
                debugger;
                 self.myFriends =  myFriends;
                 self.friendsCount =  self.myFriends.length;
                },
                function(errResponse) {
                }
            );

     }

     self.fetchOnlineFriends = function(){
         debugger;
         console.log('Showing online friends now!');
         userFactory.fetchOnlineFriends()
            .then (
                function(onlineFriends) {
                debugger;
                 self.myOnlineFriends =  onlineFriends;
                 self.countOnlineFriends =  self.myOnlineFriends.length;
                },
                function(errResponse) {
                }
            );
     }


}])