var admin = angular.module('adminModule',  []);

admin.factory('adminFactory', ['$http', '$q', 

    function ($http, $q) {

        //For linking backend project with the frontend
        var url = 'http://localhost:1112/webapp/';
        
        return {
            approvedUserList : approvedUserList,
            approvedBlogList : approvedBlogList,
            manageJobs : manageJobs,
            changeUserRole : changeUserRole,
            fetchEventList : fetchEventList,
        };

       
        //Function to fetch approved user list
        function approvedUserList() {
            var deferred = $q.defer();
            
            $http.get(url + '/user/manage/list')
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

        //Function to fetch approved blog list
        function approvedBlogList() {
            var deferred = $q.defer();
            
            $http.get(url + '/blog/manage/list')
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

        //Function to fetch approved job list
        function manageJobs() {
            var deferred = $q.defer();
            
            $http.get(url + '/job/manage/list')
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

         //Function tochange user role
        function changeUserRole(user) {
            var deferred = $q.defer();
            
            $http.post(url + '/user/role/manage',user)
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

        //Function to fetch approved event list
        function fetchEventList() {
            var deferred = $q.defer();
            
            $http.get(url + '/event/manage/list')
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

        
}])