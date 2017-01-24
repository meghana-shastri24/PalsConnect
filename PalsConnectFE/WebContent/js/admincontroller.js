app.controller('AdminController', function($scope, AdminService, $cookieStore, $location, $rootScope)
		{
	$scope.pals;
console.log("in admin controller");

$scope.getusers=function()
{

	AdminService.getusers().then(
	
			function(response)
			{
				console.log("got user..yeyyyy")
				$scope.pals=response.data;
				$rootScope.pal=$scope.pals;

				console.log($scope.pals)
				$location.path("/userslist");
			},
	
			function(response)
			{
				console.log("Errooorrrr.....")
			}
	)}

$scope.permit=function(pal)
{
	$scope.p=pal;
	AdminService.permit($scope.p).then(
	
			function(response)
			
			{
				$root.reload();	
				$location.path("/userslist");
				console.log("Permitted");
				
			},
			
			function(response)
			{
				console.log("Erroorrrr");
			}
	
	)}
	
$scope.deny=function(p)
{
	$scope.pal=p;
	AdminService.deny($scope.pal).then(
	
			function(response)
			{
			$root.reload();	
			$location.path("/userslist");
			console.log("Denied");
				
			},
			
			function(response)
			{
				console.log("Erroorrrr");
			}
	
	)}

	
if($location.path()=='/userslist'){if($rootScope.currentUser.role=='ADMIN')$scope.getusers();}
	
	
	
})