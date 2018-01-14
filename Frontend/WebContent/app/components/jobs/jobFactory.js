var job = angular.module('jobModule', []);

job.factory('jobFactory', ['$http', '$q', 

    function ($http, $q) {

        //For linking backend project with the frontend
        var jobUrl = 'http://localhost:1112/webapp/';
        
        return {
            addJob : addJob,
            joblist : joblist,
            applyJob : applyJob
        }

        //Function to add the job 
        function addJob(job) {
            var deferred = $q.defer();

            $http.post(jobUrl + '/job/new', job).then (

                function(response) {
                    deferred.resolve(response.data);
                }, 
                function (errResponse) {
                    deferred.reject(errResponse);
                }
            );
            return deferred.promise;
        }

        //Function to fetch job list
        function joblist() {
             console.log('Inside factory now');
            var deferred = $q.defer();

            $http.get(jobUrl + '/job/list/status')
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

         //Function to job join
            function applyJob(id) {
                
                var deferred = $q.defer();
                var userId = user.id;
                $http.post(jobUrl + '/job/apply/' + id, userId)
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


    }
]
);
