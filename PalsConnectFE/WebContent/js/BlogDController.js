app.controller('BlogDController',function($routeParams,$scope,BlogService){
	alert('entering blogdetailcontroller')
	var id=$routeParams.blogid
	$scope.blogPost={}
	$scope.comment={body:'',blogPost:{}}

	
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
	
	$scope.getComments=function(blogid){
		$scope.showComments=true;
		console.log('getcomments ' + blogid)
		$scope.comments=BlogService.getComments(blogid)
		.then(function(response){
			$scope.comments=response.data;
		},function(response){
			console.log('comments ' + response.status)
		})
		
	}
	
	$scope.addComment=function(){
		alert($scope.blogPost.id)
		$scope.comment.blogPost.id = $scope.blogPost.id;
		$scope.comment.blogPost=$scope.blogPost
		alert($scope.comment.body)
		alert($scope.comment.blogPost)
        BlogService.addComment($scope.comment)
        .then(function(response){
        	console.log(response.data)
        	console.log(response.status)
        },function(response){
        	console.log(response.status)
        })
	}
})