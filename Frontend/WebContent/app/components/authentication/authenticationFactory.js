var authenticate = angular.module('authenticationModule', ['ngCookies']);

authenticate.factory('AuthenticationFactory', ['$http', '$q', '$rootScope', '$cookies',
    function ($http, $q, $rootScope, $cookies) {

        //Linking backend project with the frontend
        var url = 'http://localhost:1112/webapp/';
        var userIsAUthenticated = false;
        var role = 'GUEST';

       return  {
            setUserIsAuthenticated : setUserIsAuthenticated,
            getUserIsAuthenticated: getUserIsAuthenticated,
            loadUserFromCookie: loadUserFromCookie,
            checkUsername : checkUsername,
            saveUser: saveUser,
            setRole: setRole,
            getRole: getRole,
            login: login,
            register: register,
            logout: logout
        };
        
        function setUserIsAuthenticated(value) {

            userIsAuthenticated = value;
        };

        function getUserIsAuthenticated() {

            return userIsAuthenticated;
        };

        //Loading user from cookies
        function loadUserFromCookie() {
            user = $cookies.getObject('user');
            if (user) {
                userIsAuthenticated = true;
                role = user.role;
            } else {
                userIsAuthenticated = false;
                role = 'GUEST';
            }
            return user;
        };

        //saving user inside cookies
        function saveUser(user) {
            debugger;
            $cookies.putObject('user', user);
            role = user.role;
            userIsAuthenticated = true;

        };

        function setRole(value) {

            role = value;
        };

        function getRole() {

            return role;
        };

        //Method for user login
        function login(credentials) {
            var deferred = $q.defer();
            $http.post(url + '/login', credentials).then (
                function(response) {
                    console.log('success');
                    deferred.resolve(response.data);
                }, function (error) {
                    deferred.reject(error);
                }
            );
            return deferred.promise;
        }   

        //Method for checking username
        function checkUsername(username) {

            var deferred = $q.defer();
            $http.post(url + '/checkuser', username).then (
                function(response) {    
                    console.log('Success');
                    deferred.resolve(response);
                }, function(error) {
                    console.log('Failed');
                    deferred.resolve(error);
                }
            );
            return deferred.promise;
        };

        //method for user registration
        function register(user) {
            console.log(user);

            var deferred = $q.defer();
            $http.post(url + '/register', user).then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while registering');
                    deferred.reject(errResponse);
                }
                );
            return deferred.promise;
        };

        //Method for user logout
        function logout(user) {
            debugger;
            var deferred = $q.defer();
            $http.post(url + '/logout', user).then(
                function (response) {
                    $cookies.putObject('user', undefined);
                    userIsAuthenticated = false;
                    role = 'GUEST';
                    deferred.resolve(response);
                    Materialize.toast('Logout successfully!', 2000);
                });
            return deferred.promise;
        }



    }]);