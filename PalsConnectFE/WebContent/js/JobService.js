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
	
	jobservice.applied=function(id)
	{
		console.log("applied in service")
		return $http.put(BASE_URL  + "applied/"+ id);
	}
	
	jobservice.appliedjobs=function()
	{
		console.log('entering applied jibs in service')

		return $http.get(BASE_URL + "appliedjob");
	}
	
	
	jobservice.deleteajob=function(jid)
	{
		console.log('entering delete job in service')

		return $http.delete(BASE_URL + "deleteajob/" +jid);
	}
	
	
	return jobservice;
		})