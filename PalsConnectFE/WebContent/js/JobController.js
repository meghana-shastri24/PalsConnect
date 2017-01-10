app.controller('JobController',function($scope,$location, JobService)
		{
	
	console.log("Entering job controller")

	$scope.job={jobid:'', title:'',description:'',skillsrequired:'',salary:'',location:''};
	$scope.jobs={};
	$scope.postjob=function(){
		
		console.log($scope.job)

		
		JobService.postjob($scope.job)
		.then(function(response)
		{
			alert("posted successfully");
			$location.path('/');
		},
		
		function(response)
		{
			console.log("failure " +response.status);
			if(response.status==401){
				$location.path('/login')
			}
			else{
			console.log(response.data.message)
			$location.path('/postjob')
			}
		})
		}
	
	function viewjob()
	{
		console.log('entering viewjob in controller')

		JobService.viewjob()
		.then(function(response)
					{
							console.log('viewjob success')
							$scope.job=response.data;
							console.log("success" + response.data)
					},
					
					function(response)
					{
						console.log('viewjob failure')

					})
	}

	viewjob();
	
	
	$scope.applied=function()
	{
		console.log("in applied");
		JobService.applied.then(
		function(response)
		{
			console.log("successfully applied");
			alert("You have successfully applied to the job")
			
		},
		
		function(response)
		{
			console.log("unable to apply for job");

		}
		
		)
	}
	
	
	
		})