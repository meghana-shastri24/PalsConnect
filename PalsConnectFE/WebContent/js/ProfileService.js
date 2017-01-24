app.factory('ProfileService',function($http){
	
	var profileService=this;
	
	profileService.getblogs=function(){
		console.log('getBlogposts in profileservice')
		return $http.get("http://localhost:8080/PalsConnectBE/getblogs")
	}
	
	profileService.getfriends=function()
	{
		console.log('get friends in profileservice')
		return $http.get("http://localhost:8080/PalsConnectBE/getfriends")
	}
	
	profileService.deleteblog=function(id){
		return $http.delete("http://localhost:8080/PalsConnectBE/deleteblog/"+id)
	}
	

	return profileService;
})
