friend.controller('friendController', [
    'friendFactory', 
    '$timeout', 
    '$cookies', 
    '$routeParams', 
    '$location', 
    '$route',
    '$q', 
    function(friendFactory,$timeout, $cookies, $routeParams, $location, $route, $q) {

    var self = this;

    //For storing user list
    self.userslist = [];

    //For temporary storing list of user
    self.tempUsers = [];

    self.countUsers = {};

    self.hasSentRequest = false;

    //function to store list of friend requests
    self.friendRequests = [];

    self.myFriends = [];

    //TO count number of friend request
    self.friendRequestsCount =[]

     // calling jQuery once controller has loaded
    $timeout(function () {
        setting();
    }, 100);

    //Function to fetch site users will come here
    self.fetchusers = function() {
        debugger;
        friendFactory.fetchUsers()
            .then (
                function(user) {
                   debugger;
                    // var index = 0;  //setting up an var index as 0
                    // for (var tempuser in  list.usersToBefriend) {   //traversing through array to remove user with same id as that of logged in user
                    //     var tempId = list.usersToBefriend[tempuser].id;
                    //     if( tempId != user.id) {  //if it is not user id set it inside another array
                    //          self.tempUsers[index++] =  list.usersToBefriend[tempuser]; 
                    //     }
                    // }
                     self.userslist = user;
                     for(var birthDate in  self.userslist) {
                         self.userslist[birthDate].birthDate = new Date(self.userslist[birthDate].birthDate[0], self.userslist[birthDate].birthDate[1] - 1, self.userslist[birthDate].birthDate[2]);
                    }
                    self.countUsers = self.userslist.length;
                }, function (errResponse) {
                }
            );
    }

    //function to send friend request
    self.sendRequest = function(id) {
        debugger;
        friendFactory.sendRequest(id)
            .then (
                function(friend) {
                    debugger;
                    $route.reload();
                    // self.hasSentRequest = true;
                     Materialize.toast('FriendRequest sent successfully!', 2000);
                },function(errResponse) {

                }
            );
    }

    //function to fetch the friend list
    self.fetchRequest = function() {
        friendFactory.fetchRequest()
            .then (
                function(friendRequests) {
                     self.friendRequests = friendRequests;
                     self.friendRequestsCount = self.friendRequests.length;
                },function(errResponse) {

                }
            );
    }


    //function to approve friend request
    self.approveRequest = function(id) {
        friendFactory.approveRequest(id)
            .then (
                function(friendRequests) {
                    $route.reload();
                     console.log('friend request accepted!')
                },function(errResponse) {

                }
            );
    }

    //Function to check user's friends
    // self.checkUsersFriends = function() {
    //     debugger;

    //     var deferred = $q.defer();


    //     friendFactory.checkUsersFriends()
    //         .then (
    //             function(friendlist) {
    //                 deferred.resolve(friendlist);
    //                 // self.setFlags();

    //             },function(errResponse) {
    //                 deferred.reject(errResponse);
    //             }
    //         );

    //         return deferred.promise;
    // }

    // self.setFlags = function(id) {
        
    //     debugger;

    //     self.checkUsersFriends().then(

    //         function(friendlist) {
    //             debugger;
    //             self.usersFriends = friendlist;
    //             for (var friend in  self.usersFriends) {
    //                     if(id == friend.id) {
    //                         self.hasSentRequest = true;
    //                     }
    //                 }
    //         }
    //     )
        
    // }
        
    
}])