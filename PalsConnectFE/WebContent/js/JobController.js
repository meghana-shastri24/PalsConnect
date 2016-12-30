app.controller('JobController',function($scope,$location, JobService)
		{
	
	console.log("Entering job controller")

	$scope.job={title:'',description:'',skillsrequired:'',salary:'',location:''}
	$scope.jobs={}
	$scope.postjob=function(){
		
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
			$location.path('/postJob')
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
					})
	}

	viewjob();
	
	
	
		})