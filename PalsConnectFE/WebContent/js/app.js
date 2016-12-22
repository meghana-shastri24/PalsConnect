var app=angular.module("pals",[ 'ngRoute'])
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
			
	.when('/postjob',
			{
		controller:'JobController',
		templateUrl:'views/postjob.html'
			})
	
	.when('/viewjob',
			{
		controller:'JobController',
		templateUrl:'views/viewjob.html'
			})
			
			
			
	.when('/',
	{
		templateUrl: 'views/home.html'
	})
		})
