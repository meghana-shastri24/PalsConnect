app.controller('BlogDController',function($routeParams,$scope,BlogService){
	console.log('entering blogdetailcontroller')
	
	var id=$routeParams.blogid
	$scope.blogPost={}
	$scope.comment={body:'',blogPost:{}}
	$scope.showComments=true;

	
	$scope.blogPost=
		BlogService.getBlogDetail(id) //calling service function directly
		.then(function(response){
			console.log(response.data);
			console.log(response.status)
			$scope.blogPost=response.data;
		},function(response){

			console.log(response.status)
		})
	
		$scope.editPost=function(){
		$scope.isEditPost=true;
		BlogService.editPost()
	}
	
	function getComments(id){
		console.log('getcomments ' + id)
		$scope.comments=BlogService.getComments(id)
		.then(function(response){
			$scope.comments=response.data;
		},function(response){
			console.log('comments ' + response.status)
		})
		
	}
	
	getComments(id);
	$scope.addComment=function(id){
		$scope.comment.blogPost.id = $scope.blogPost.blogid;
		$scope.comment.blogPost=$scope.blogPost
		
		console.log($scope.comment.blogPost)
		console.log($scope.comment)

        BlogService.addComment($scope.comment, id)
        .then(function(response){
        	console.log(response.data)
        	console.log(response.status)
        	getComments(id);
        },function(response){
        	console.log(response.status)
        })
	}
})