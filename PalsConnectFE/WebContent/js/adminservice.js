app.factory('AdminService', function($http)
		{
		
		console.log("in admin service");
		
		var BASE_URL="http://localhost:8080/PalsConnectBE/";
		var adminservice=this;
		
		adminservice.getusers=function()
		{
		console.log('entering get users in service')
		return $http.get(BASE_URL + "/getusers")
		}


		adminservice.permit=function(pals)
		{
			console.log('Entering permit in service')
			return $http.post(BASE_URL + "permit", pals)
		}

		adminservice.deny=function(pals)
		{
			console.log('Entering permit in service')
			return $http.post(BASE_URL + "deny", pals)
		}

		return adminservice;

		})