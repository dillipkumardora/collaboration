window.routes = {

    //For home page
    "/home": {
        templateUrl : 'app/components/authentication/authentication.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: false,
        roles: ['GUEST']
    },

    //For user home page
    "/user": {
        templateUrl : 'app/components/user/profile.html',
        controller : 'userController',
        controllerAs : 'userCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

    //For user's profile
     "/user/profile/:id": {
        templateUrl : 'app/components/user/userProfile.html',
        controller : 'userController',
        controllerAs : 'userCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

    //For assigning role to the user and to update or delete user
    "/manage/users": {
        templateUrl : 'app/components/user/manageUser.html',
        controller : 'adminController',
        controllerAs : 'adminCtrl',
        requireLogin: true,
        roles: ['Super_Admin']
    },

    //For accepting requests of user
    "/requests/users": {
        templateUrl : 'app/components/user/userRequests.html',
        controller : 'requestController',
        controllerAs : 'requestCtrl',
        requireLogin: true,
        roles: ['Super_Admin', 'Admin']
    },


    //Form for creating new blog
    "/blog/new": {
        templateUrl : 'app/components/blogs/newBlog.html',
        controller : 'blogController',
        controllerAs : 'blogCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

    //For viewing single blog
    "/blog/:id": {
        templateUrl : 'app/components/blogs/blog.html',
        controller : 'blogController',
        controllerAs : 'blogCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },
     
    //For viewing list of all blogs
     "/blogs/all": {
        templateUrl : 'app/components/blogs/bloglist.html',
        controller : 'blogController',
        controllerAs : 'blogCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

    //For updating or deleting any blog
     "/manage/blogs": {
        templateUrl : 'app/components/blogs/manageBlogs.html',
        controller : 'adminController',
        controllerAs : 'adminCtrl',
        requireLogin: true,
        roles: ['Super_Admin', 'Admin']
    },

    //For accepting request of blogs
    "/requests/blogs": {
        templateUrl : 'app/components/blogs/blogRequests.html',
        controller : 'requestController',
        controllerAs : 'requestCtrl',
        requireLogin: true,
        roles: ['Super_Admin', 'Admin']
    },

     //For viewing list of forums and adding a new one
     "/forum/list": {
        templateUrl : 'app/components/forum/forumlist.html',
        controller : 'forumController',
        controllerAs : 'forCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

    //For viewing single forum topic
    "/forum/:id": {
        templateUrl : 'app/components/forum/forum.html',
        controller : 'forumController',
        controllerAs : 'forCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

    //For viewing single forum topic
    "/manage/forums": {
        templateUrl : 'app/components/forum/manageForums.html',
        controller : 'adminController',
        controllerAs : 'adminCtrl',
        requireLogin: true,
        roles: ['Super_Admin', 'Admin']
    },

    //For viewing single forum topic
    "/requests/forums": {
        templateUrl : 'app/components/forum/forumRequests.html',
        controller : 'requestController',
        controllerAs : 'requestCtrl',
        requireLogin: true,
        roles: ['Super_Admin', 'Admin']
    },

    //Form for creating new event
    "/event/new": {
        templateUrl : 'app/components/events/newEvent.html',
        controller : 'eventController',
        controllerAs : 'eventCtrl',
        requireLogin: true,
        roles: ['Super_Admin', 'Admin', 'Employer']
    },

    //For viewing event list
    "/events/list": {
        templateUrl : 'app/components/events/eventlist.html',
        controller : 'eventController',
        controllerAs : 'eventCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

    //For updating and deleting event
     "/manage/events": {
        templateUrl : 'app/components/events/manageEvents.html',
        controller : 'adminController',
        controllerAs : 'adminCtrl',
        requireLogin: true,
        roles: ['Super_Admin', 'Admin']
    },

    //For accepting new event requests 
    "/requests/events": {
        templateUrl : 'app/components/events/eventsRequests.html',
        controller : 'requestController',
        controllerAs : 'requestCtrl',
        requireLogin: true,
        roles: ['Super_Admin', 'Admin']
    },

    //Form for creating new job
    "/job/new": {
        templateUrl : 'app/components/jobs/newJob.html',
        controller : 'jobController',
        controllerAs : 'jobCtrl',
        requireLogin: true,
        roles: ['Super_Admin', 'Admin', 'Employer']
    },

    //For viewing job list
    "/jobs/list": {
        templateUrl : 'app/components/jobs/joblist.html',
        controller : 'jobController',
        controllerAs : 'jobCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

    //For updating and deleting jobs
     "/manage/jobs": {
        templateUrl : 'app/components/jobs/manageJobs.html',
        controller : 'adminController',
        controllerAs : 'adminCtrl',
        requireLogin: true,
        roles: ['Super_Admin', 'Admin']
    },

    //For accepting request of new job
     "/requests/jobs": {
        templateUrl : 'app/components/jobs/jobsRequests.html',
        controller : 'requestController',
        controllerAs : 'requestCtrl',
        requireLogin: true,
        roles: ['Super_Admin', 'Admin']
    },

    //For viewing list of all site's members
    "/member/list": {
         templateUrl : 'app/components/friends/memberslist.html',
        controller : 'friendController',
        controllerAs : 'friendCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

     //For viewing list of friend request user has received
    "/user/friendRequest": {
         templateUrl : 'app/components/friends/friendRequests.html',
        controller : 'friendController',
        controllerAs : 'friendCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

    //For viewing list of friend request user has received
    "/chat/:id/:username": {
        templateUrl : 'app/components/chat/chat.html',
        controller : 'chatController',
        controllerAs : 'chatCtrl',
        requireLogin: true,
        roles: ['User', 'Super_Admin', 'Admin', 'Employer']
    },

    //For navigating to error page
    "/error": {
        templateUrl : 'app/components/authentication/error.html',
        controller : 'authenticationController',
        controllerAs : 'authCtrl',
        requireLogin: false,
        roles: ['GUEST']
    }

    
};




codehub.config(['$routeProvider', '$httpProvider',  function($routeProvider, $httpProvider){

     

    //for page navigartion through url
    for(var path in window.routes) {
        $routeProvider.when(path, window.routes[path]); 
    }
    
    $routeProvider.otherwise({redirectTo: '/home'});

     
}]);

//Rest server from where data is coming
codehub.constant('url', 'http://localhost:1112/webapp/');

codehub.run(function ($rootScope, $location, AuthenticationFactory) {

    $rootScope.$on('$locationChangeStart', function (event, next, current) {
        if(next == current) {
                $rootScope.user = AuthenticationFactory.loadUserFromCookie();
                $rootScope.authenticated = AuthenticationFactory.getUserIsAuthenticated();
                return;            
        }                   
        // For interating through all the routes
        for (var i in window.routes) {         
            if (next.indexOf(i) != -1 || (i.indexOf("/:id") != -1 )) {
                $rootScope.user = AuthenticationFactory.loadUserFromCookie();
                $rootScope.authenticated = AuthenticationFactory.getUserIsAuthenticated();

                console.log(AuthenticationFactory.getUserIsAuthenticated());
                //if trying to access page that requires login and user is not authenticated redirect to login page
                
                if (window.routes[i].requireLogin && !AuthenticationFactory.getUserIsAuthenticated()) {
                    $location.path('/home');
                } 
                else if((AuthenticationFactory.getUserIsAuthenticated()) 
                        &&
                        (window.routes[i].roles.indexOf(AuthenticationFactory.getRole())==-1)) {
                        $location.path('/error');
                        }
                        
            }
        }
        
    });

    $rootScope.logout = function() {
    //calling the log out function created in the AuthenticationFactory
    AuthenticationFactory.logout($rootScope.user).then(
        function() {
            
            AuthenticationFactory.setUserIsAuthenticated(false);
            $rootScope.authenticated = false;
            $rootScope.message = "Logout successfully!";
            $location.path('/home');
        }
    );
};

});

