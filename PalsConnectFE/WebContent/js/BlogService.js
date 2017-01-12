app.factory('BlogService',function($http){
	
	var blogService=this;
	
	
	blogService.addPost=function(blogPost){
		console.log('addpost in service')
		return $http.post("http://localhost:8080/PalsConnectBE/blog",blogPost);
	}
	
	blogService.getBlogPosts=function(){
		console.log('getBlogposts in service')
		return $http.get("http://localhost:8080/PalsConnectBE/blog/list")
	}
	blogService.getBlogDetail=function(id){
		console.log('getBlogDetails')
		return $http.get("http://localhost:8080/PalsConnectBE/blog/get/"+ id)
	}
	
	blogService.addComment=function(comment , id){
		console.log(comment)

		return $http.post("http://localhost:8080/PalsConnectBE/blog/comment/"+ id, comment)
	}
	blogService.getComments=function(blogId){
		console.log('getcomments -- service')
		return $http.get("http://localhost:8080/PalsConnectBE/blog/getcomments/"+blogId)
	}
	return blogService;
})