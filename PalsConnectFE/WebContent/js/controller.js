app.controller('PalController', function($scope, PalService, $cookieStore, $location, $rootScope)
		{
	console.log('entering the controller')
	
		$scope.palregister=function(){
		
			console.log('entering the function register in pal controller')
		
		PalService.register($scope.pal).then(

					function(response){//success
						console.log("registration success" + response.status)		
						$location.path("/login");
						}
					,
					function(response){
						console.log("registration failed" + response.status)
						console.log(response.data)
						$location.path("/register")
					});
				
	}
	
	$scope.pallogin=function(){
		
		console.log('entering the function login in pal controller')
	
	PalService.login($scope.pal).then(
	
			function(response)
			{
				$scope.pal=response.data;
				$rootScope.currentUser=$scope.pal;

				$cookieStore.put('currentUser',$rootScope.currentUser)

				$location.path("/");
			},
			function(response){
				 console.log('invalid username and password')
				  $scope.message="Invalid Username and Password";
				  $location.path("/login");
			});
	
	
	}
	
	


		})