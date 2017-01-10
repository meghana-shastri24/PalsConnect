app.controller('BlogDController',function($routeParams,$scope,BlogService){
	console.log('entering blogdetailcontroller')
	
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
	
	$scope.getComments=function(id){
		$scope.showComments=true;
		console.log('getcomments ' + id)
		$scope.comments=BlogService.getComments(id)
		.then(function(response){
			$scope.comments=response.data;
		},function(response){
			console.log('comments ' + response.status)
		})
		
	}
	
	$scope.addComment=function(){
		alert($scope.blogPost.blogid)
		$scope.comment.blogPost.id = $scope.blogPost.blogid;
		$scope.comment.blogPost=$scope.blogPost
		alert($scope.comment.body)
		alert($scope.comment.blogPost)
		console.log($scope.comment.blogPost)
		console.log($scope.comment)

        BlogService.addComment($scope.comment)
        .then(function(response){
        	console.log(response.data)
        	console.log(response.status)
        },function(response){
        	console.log(response.status)
        })
	}
})