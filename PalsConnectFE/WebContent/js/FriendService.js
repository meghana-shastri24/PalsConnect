app.factory('FriendService',function($http){
	
	
	var friendservice=this;
	var BASE_URL ="http://localhost:8080/PalsConnectBE/"
		
		
	
	friendservice.pendingrequest=function(){
		console.log('in service pending friend request');
		return $http.get(BASE_URL + "pendingRequest")
	}
	
	friendservice.updatePendingRequest=function(status, fromid){
		console.log('in service pending friend request');
		return $http.put(BASE_URL + "updateFriendRequest/"+ status+"/"+ fromid)
	}
	
	friendservice.getAllFriends=function(){
		console.log('in get my friends');
		return $http.get(BASE_URL + "getAllFriends")
	}
	
	
	return friendservice;
})