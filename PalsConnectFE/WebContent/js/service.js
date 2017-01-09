app.factory('PalService', function($http){
	
var BASE_URL="http://localhost:8080/PalsConnectBE/";

var palservice=this;

palservice.register=function(pal)
	{
	console.log('entering save person in service')
	return $http.post(BASE_URL + "pals", pal)
	}

palservice.login=function(pal)
	{
	console.log('entering login in service')
	return $http.post(BASE_URL + "login", pal)
	}

palservice.logout=function(){
	console.log('entering logout service')
	return $http.put(BASE_URL + "logout")
}


palservice.sendrequest=function(username)
{
	console.log('in send request in service')
	return $http.post(BASE_URL+ "/friendRequest", username)
}

palservice.getallpals=function()
{
	console.log('get users in service')
	return $http.get(BASE_URL+ 'pals')
}



return palservice;

})
