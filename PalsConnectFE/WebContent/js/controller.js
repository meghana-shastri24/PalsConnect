app.controller('PalController',  function($scope, PalService, $cookieStore, $location, $rootScope)
		{
	
	$scope.message;

	console.log('entering the controller')
	
		$scope.palregister=function(){
		
			console.log('entering the function register in pal controller');
		
			console.log($scope.pal);
			
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

				$location.path("/welcome");
			},
			
			
			function(response){
				
				$scope.message=response.data.message;
					  $location.path("/login");
						
			
				
					
			});
	}
	
	$scope.sendrequest=function(username)
	{
		console.log("In friend controller")
		
		PalService.sendrequest(username).then(
				
		function(response)
		{
	
					alert("Friend request sent");
					getallpals();
					$location.path("allpals")
					
		},
		
		function(response)
		{
			console.log("Errorr");
		}
				
		)}
	
	
	function getallpals()
	{
	PalService.getallpals().then(
				
				function(response)
				{
					console.log("Getting all users");
					$scope.pals=response.data;
					$rootScope.apals=$scope.pals;
					console.log(response.data)
					
				},
				
				function(response)
				{
					console.log("errroorrr");
				})

	}
	
	getallpals();
		
	
	

		})