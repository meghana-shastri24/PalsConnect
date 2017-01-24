app.controller("ChatController", function($routeParams, $scope,$rootScope ,ChatService) {
    $scope.messages = [];
    $scope.message = "";
    $scope.max = 140;
    var reciepent=$routeParams.username;
    
    console.log($routeParams.username);
    
    $scope.addMessage = function() {
      ChatService.send($rootScope.currentUser.username + " : " + $scope.message);
      $scope.message = "";
    };
    
    ChatService.receive().then(null, null, function(message) {
      $scope.messages.push(message);
    });
    
    
    
  });