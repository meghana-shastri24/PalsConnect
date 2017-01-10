app.factory('JobService', function($http)
		{
	
	var BASE_URL="http://localhost:8080/PalsConnectBE/";

	var jobservice=this;
	
	
	jobservice.postjob=function(job)
	{
		console.log('entering postjob in service')
		console.log(job)

	return $http.post(BASE_URL + "postjob", job);
	}	
	


	
	jobservice.viewjob=function()
	{
		console.log('entering viewjob in service')

		return $http.get(BASE_URL + "viewjob");
	}
	
	jobservice.applied=function()
	{
		console.log("applied in service")
		return $http.put(BASE_URL  + "applied");
	}
	
	return jobservice;
		})