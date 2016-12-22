app.factory('JobService', function($http)
		{
	
	var BASE_URL="http://localhost:8080/PalsConnectBE/";

	var jobservice=this;
	
	jobservice.postjob=function(job)
	{
		
		return $http.post(BASE_URL + "/postjob", job)
		.then(function(response)
				{
			console.log(response.status)

			return response.status

				},
				function(response){
					console.log(response.status)
					return response.status
				}
		)

	}
	
	jobservice.viewjob=function()
	{
		return $http.get(BASE_URL + "/viewjob")
		.then(function(response)
				{
			console.log(response.status)

			return response.status

				},
				function(response){
					console.log(response.status)
					return response.status
				}
		)

	}
	return jobservice;
		})