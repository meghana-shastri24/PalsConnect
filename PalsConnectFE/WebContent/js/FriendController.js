app.controller('FriendController', function(FriendService, $scope, $location, $rootScope)
		{
	
	

	$scope.pal={username:'',email:'',password:'',dob:'',role:'', cpassword:'', gender:'', phno:'', isonline:'', status:''}
	$scope.pals={};
	$scope.friends1={};

	
	
	$scope.pendingrequest=
	
		FriendService.pendingrequest()
		.then(function(response){
			console.log('PENDING REQUEST')
			$scope.pals= response.data
			console.log($scope.pals)
			
		},function(response){
			console.log(response.status)
		})
		
		
	
	$scope.getfriends=function()
	{
		FriendService.getAllFriends()
		.then(function(response){
			console.log('get all Friends controller')
			console.log(response.status)
			$scope.friends1= response.data;
			$rootScope.fr=$scope.friends1;
			console.log($scope.friends1)

		},
		function(response){
			console.log(response.data)
		})
	}
	
	
	
	$scope.updatePendingRequest=function(fromId,friendStatus){
		alert('updateFriendrequest')
		FriendService.updatePendingRequest(friendStatus,fromId)
		.then(
				
				function(response)
				
				{
			console.log(response.status)
			
			if(friendStatus=='A'){
			alert('you have accepted the friend request. You and ' + fromId + " are friends");
			$location.path('/pending')
			}
			else{
				alert('You have rejected the friend requet')
			}
		},
		
		function(response){
			console.log(response.status)
		}
		
		)
	
	
		}
	if($location.path()=='/getfriends'){if($rootScope.currentUser!=null)$scope.getfriends();}
	
	})
	
	



