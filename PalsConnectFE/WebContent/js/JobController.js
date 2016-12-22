app.controller('JobController',function($scope, JobService, $location)
		{
	$scope.job={title:'',description:'',skillsrequired:'',salary:'',location:''}
	$scope.postjob=function(){
		
		JobService.postjob($scope.job).then(
				
		function(response)
		{
			if(response.status==200)
				{
				console.log(response.status)

				$location.path("/");
				}
			else
				{
				console.log(response.status)

				$location.path("/postjob");
				}
		}
		);
		
		
		
		
	}
	
	$scope.viewjob=function()
	{
		
		JobService.viewjob().then(
				
					function(response)
					{
						if(response.status==200)
							{

							$location.path("/viewjob");
							}
						else
							{
							$location.path("/");
							}
					}		
					);
					
		
				
	}
	
	
		})