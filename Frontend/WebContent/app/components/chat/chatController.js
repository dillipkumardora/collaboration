chat.controller('chatController', ['chatFactory',
        '$timeout', '$cookies', '$routeParams', '$location', '$route', '$rootScope', function (chatFactory,
                $timeout, $cookies, $routeParams, $location, $route, $rootScope) {

var self = this;

                self.messages = [];
                self.message = "";
                self.chatter = $routeParams.username;
                //self.max = 140;

                self.addMessage = function () {
                        chatFactory.send($rootScope.user.username + " - " + self.message);
                        self.message = "";
                };

                chatFactory.receive().then(null, null, function (message) {
                        self.messages.push(message);
                });

               

        }])
