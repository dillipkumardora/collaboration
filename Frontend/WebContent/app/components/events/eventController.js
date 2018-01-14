event.controller('eventController', ['eventFactory', 
        '$timeout', '$cookies', '$routeParams', '$location', '$route', function(eventFactory, 
        $timeout, $cookies, $routeParams, $location,  $route) {

    var self = this;

    ///setting up the fields for creating new event - should be same as fields in the entity class
    self.event = {

        id : null,
        name : ' ',
        venue : '',
        description : '',
        startDate : '',
        endDate : '',
        postDate : '',
        userId : '',
        username : '',
    }

    //Fetching list of events
    self.eventlist = [];

    // calling jQuery once controller has loaded
    $timeout(function () {
        setting();
    }, 100);

     //function for adding a new event
    self.addEvent = function () {
        debugger;
         var startDate = new Date(self.event.startDate).toISOString().slice(0, 10);
          self.event.startDate = startDate;

          var endDate = new Date(self.event.endDate).toISOString().slice(0, 10);
          self.event.endDate = endDate;

        //Setting the user id and username
        self.event.userId = user.id;
        self.event.username = user.username;
         //calling the addevent method in the factory
         eventFactory.addEvent(self.event)
            .then (
                function(event) {
                    debugger;
                    self.event =  event;
                    // console.log(self.blog.id)
                    // var bId = self.blog.id 
                    $location.path('/events/list');
                }, function (errResponse) {
                    console.error('Failure!');
                }
            );
         
    }

    //calling method for eventlist
     eventlist();

    //Function to view list of all events
    function eventlist() {

        // var status = "APPROVED"
        debugger;
        eventFactory.eventlist()
            .then (
                function(events) {
                    debugger;   
                    self.eventlist = events;
                    for(var [events] in self.eventlist) {
                        self.eventlist[events].postDate = new Date(self.eventlist[events].postDate[0],self.eventlist[events].postDate[1] - 1,self.eventlist[events].postDate[2]);
                    }
                     for(var [startDate] in self.eventlist) {
                        self.eventlist[startDate].startDate = new Date(self.eventlist[startDate].startDate[0],self.eventlist[startDate].startDate[1] - 1,self.eventlist[startDate].startDate[2]);
                    }
                     for(var [endDate] in self.eventlist) {
                        self.eventlist[endDate].endDate = new Date(self.eventlist[endDate].endDate[0],self.eventlist[endDate].endDate[1] - 1,self.eventlist[endDate].endDate[2]);
                    }
                },
                function(errResponse) {
                    console.log('Failure!');
                }
            );
    }

     //Method to join event
    self.joinEvent = function(id) {
          eventFactory.joinEvent(id)
            .then (
                function(event) {
                debugger;
                 $route.reload();
                 Materialize.toast('Event joined successfully!', 3000);
                 self.appliedFor.push(id);  
                },
                function(errResponse) {
                }
            );
    }
        
    
}])
