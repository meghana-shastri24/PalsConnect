app.controller('ProfileController',function($scope,$location,$rootScope,$route,ProfileService){
	
	
	console.log('Im in profile controller');

	$scope.friends={};
	$scope.blogs={};
	

	
	function getfriends()
	{
		console.log('Im in get friends');
		ProfileService.getfriends().then(
				
		function(response)
		{
			console.log('friends success');
			$scope.friends=response.data;
			console.log($scope.friends);


			
		},
		
		function(response)
		{
			console.log("Errorr");
			
		}
		)
		
	}
	
	getfriends();
	
	$scope.getblogs=function()
	{
		console.log('Im in get blogs');
		ProfileService.getblogs().then(
				
		function(response)
		{
			console.log('blog success');
			$scope.blogs=response.data;
			console.log($scope.blogs);


		},
		
		function(response)
		{
			console.log("Errorr");
			
		}
		)
		
	}
	
	$scope.deleteblog=function(id){
		ProfileService.deleteblog(id)
		.then(function(response){
			$route.reload();
		})
	}
	
	if($location.path()=='/MyProfile'){if($rootScope.currentUser!=null)$scope.getblogs();}

})
