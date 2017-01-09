app.controller('FriendController', function(FriendService, $scope, $location, $rootScope)
		{
	
	
	alert('entering friend controller')

	$scope.pal={username:'',email:'',password:'',dob:'',role:'', cpassword:'', gender:'', phno:'', isonline:'', status:''}
	$scope.pals={};

	
	
	$scope.pendingrequest=
	
		FriendService.pendingrequest()
		.then(function(response){
			console.log('PENDING REQUEST')
			$scope.pals= response.data
			console.log($scope.pals)
			
		},function(response){
			console.log(response.status)
		})
		
		
	
	$scope.friends= function()
	{
		FriendService.getAllFriends()
		.then(function(response){
			console.log('get all Friends controller')
			console.log(response.status)
			$scope.friends= response.data;
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
		
	})
	
	



