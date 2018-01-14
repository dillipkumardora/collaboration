var event = angular.module('eventModule',  []);

blog.factory('eventFactory', ['$http', '$q', 

    function ($http, $q) {

        //For linking backend project with the frontend
        var eventUrl = 'http://localhost:1112/webapp/';
        
        return {
            addEvent : addEvent,
            eventlist : eventlist,
            joinEvent : joinEvent
        };

        //Function to add the blog 
        function addEvent(event) {
            var deferred = $q.defer();
            debugger;
            $http.post(eventUrl + '/events/new', event).then (

                function(response) {
                    deferred.resolve(response.data);
                }, 
                function (errResponse) {
                    deferred.reject(errResponse);
                }
            );
            return deferred.promise;
        }

        //Function to fetch list of events
        function eventlist() {
             console.log('Inside factory now');
            var deferred = $q.defer();
            $http.get(eventUrl + '/events/list/status')
                .then (
                    function(response) {
                        deferred.resolve(response.data);
                    },
                    function(errResponse) {
                        deferred.reject(errResponse);
                    }
                );
                return deferred.promise;
        }

            //Function to join event
            function joinEvent(id) {
                
                var deferred = $q.defer();
                var userId = user.id;
                $http.post(eventUrl + '/event/join/' + id, userId)
                    .then (
                        function(response) {
                            deferred.resolve(response.data);
                        },
                        function(errResponse) {
                            deferred.reject(errResponse);
                        }
                    );
                    return deferred.promise;
            }
    }]);