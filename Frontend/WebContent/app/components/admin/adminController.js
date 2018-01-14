admin.controller('adminController', ['adminFactory',
        '$timeout', '$cookies', '$routeParams', '$location', '$route', function(adminFactory,
        $timeout, $cookies, $routeParams, $location, $route) {

    var self = this;

    //For temparary storing list of users
    self.tempUserList = [];

    //For list of approved user list
    self.approvedUserList = [];

    //For list of approved blog list
    self.approvedBlogList = [];

    //For list of approved job list
    self.approvedJobList = [];

    //For list of event list
    self.eventsList = [];

     // calling jQuery once controller has loaded
    $timeout(function () {
        console.log('Here in controller');
        setting();       

    }, 1000);

    //Function to fetch approved User List
    self.approvedUserList = function() {
        debugger;   
         adminFactory.approvedUserList()
            .then (
                function(approvedUsers) {
                    debugger;
                    var index = 0;  //setting up an var index as 0
                    for (var user in approvedUsers) {   //traversing through array to remove user with Super admin role
                        var role = approvedUsers[user].role; 
                        if( role != 'Admin') {    //if role is not super admin add the user to new list
                            self.tempUserList[index++] = approvedUsers[user]; 
                        }
                    }

                     self.approvedUserList = self.tempUserList; //assigning temp user list to approvedUserList
                    $timeout(function() {
                        angular.element('select').material_select();
                    }, 3000);
                },
                function(errResponse) {
                }
            );
    }

    //Function to fetch approved blog List
    self.approvedBlogList = function() {
        
         adminFactory.approvedBlogList()
            .then (
                function(approvedBlogs) {
                    self.approvedBlogList = approvedBlogs; 
                    for (var postDate in self.approvedBlogList) {   
                        self.approvedBlogList[postDate].postDate = new Date(self.approvedBlogList[postDate].postDate[0],self.approvedBlogList[postDate].postDate[1] - 1,self.approvedBlogList[postDate].postDate[2]);
                    }
                },
                function(errResponse) {
                }
            );
    }

    //Function to fetch approved job List
    self.manageJobs = function() {
        
         adminFactory.manageJobs()
            .then (
                function(approvedJobs) {
                    self.approvedJobList = approvedJobs; 
                    for (var postDate in self.approvedJobList) {   
                        self.approvedJobList[postDate].postDate = new Date(self.approvedJobList[postDate].postDate[0],self.approvedJobList[postDate].postDate[1] - 1,self.approvedJobList[postDate].postDate[2]);
                    }
                },
                function(errResponse) {
                }
            );
    }

    //Function to change user role
    self.changeUserRole = function(user) {
        debugger;
        console.log(user.role)
         adminFactory.changeUserRole(user)
            .then (
                function(user) {
                   Materialize.toast('User role changed successfully!', 2000);
                   $route.reload();
                },
                function(errResponse) {
                }
            );
    }

     //Function to fetch approved job List
    self.fetchEventList = function() {
        
         adminFactory.fetchEventList()
            .then (
                function(approvedEvents) {
                    self.eventsList = approvedEvents; 
                    for (var postDate in self.eventsList) {   
                        self.eventsList[postDate].postDate = new Date(self.eventsList[postDate].postDate[0],self.eventsList[postDate].postDate[1] - 1,self.eventsList[postDate].postDate[2]);
                    }
                     for(var startDate in self.eventsList) {
                        self.eventsList[startDate].startDate = new Date(self.eventsList[startDate].startDate[0],self.eventsList[startDate].startDate[1] - 1,self.eventsList[startDate].startDate[2]);
                    }
                     for(var endDate in self.eventsList) {
                        self.eventsList[endDate].endDate = new Date(self.eventsList[endDate].endDate[0],self.eventsList[endDate].endDate[1] - 1,self.eventsList[endDate].endDate[2]);
                    }
                },
                function(errResponse) {
                }
            );
    }
        
    
}])