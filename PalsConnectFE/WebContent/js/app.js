var app = angular.module("pals",[ 'ngRoute', 'ngCookies']);


app.config(function($routeProvider) 
		{
	
	$routeProvider
	.when('/register',
			{
		controller:'PalController',
		templateUrl: 'views/register.html'
			})
			
	.when('/login',
			{
		controller:'PalController',
		templateUrl: 'views/login.html'
			})
			
	
	.when('/viewjob',
			{
		templateUrl:'views/viewjob.html',
		controller:'JobController'

			})
			.when('/postjob',
			{
		templateUrl:'views/postjob.html',
		controller:'JobController'
			})
	
			
			
	.when('/',
	{
		templateUrl: 'views/home.html'
	})
		})

app.run(function($cookieStore,$rootScope,$location,PalService){  //entry point
	
	if($rootScope.currentUser==undefined)
		$rootScope.currentUser=$cookieStore.get('currentUser')
		
	$rootScope.logout=function(){
		console.log('logout function')
		delete $rootScope.currentUser;
		$cookieStore.remove('currentUser')
		PalService.logout()
		.then(function(response){
			console.log("logged out successfully..");
			$rootScope.message="Logged out Successfully";
			$location.path('/login')
		},
		function(response){
			console.log(response.status);
		})
		
	}	
})