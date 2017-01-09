package meghana.controller;

import java.util.List;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import meghana.dao.BlogDaoImpl;
import meghana.model.Blog;
import meghana.model.BlogComment;
import meghana.model.RegisterUser;
import meghana.model.error;

@RestController
@RequestMapping("/blog")
public class BlogController {
	
	
@Autowired
private BlogDaoImpl blogdaoimpl;
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public ResponseEntity<?> getBlogList(HttpSession session){
		
		RegisterUser user=(RegisterUser)session.getAttribute("pal");
		if(user==null){
			error er=new error(1,"Unauthroized user");
			return new ResponseEntity<error>(er, HttpStatus.UNAUTHORIZED);
		}
		
		List<Blog> blogPosts=blogdaoimpl.getBlogPosts();
		
		return new ResponseEntity<List<Blog>>(blogPosts,HttpStatus.OK);
	}
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getBlogPost(@PathVariable(value="id") int id,HttpSession session){
		RegisterUser user=(RegisterUser)session.getAttribute("pal");
		if(user==null){
			error error=new error(1,"Unauthroized user");
			return new ResponseEntity<error>(error,HttpStatus.UNAUTHORIZED);
		}
		Blog blogPost=blogdaoimpl.getBlogPost(id);
		return new ResponseEntity<Blog>(blogPost,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addBlogPost( @RequestBody Blog blogPost,HttpSession session) {
		RegisterUser user=(RegisterUser)session.getAttribute("pal");
		if(user==null){
			error error=new error(1,"Unauthroized user");
			return new ResponseEntity<error>(error,HttpStatus.UNAUTHORIZED);
		}
         Blog addedBlogPost= blogdaoimpl.addBlogPost(user, blogPost);
         return new ResponseEntity<Blog>(addedBlogPost,HttpStatus.OK);
    }
	@RequestMapping(value="/getcomments/{blogId}",method=RequestMethod.GET)
	public ResponseEntity<?> getBlogComments(@PathVariable(value="blogId")int blogId,HttpSession session){
		RegisterUser user=(RegisterUser)session.getAttribute("pal");
		if(user==null){
			error error=new error(1,"Unauthroized user");
			return new ResponseEntity<error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<BlogComment> blogComments=blogdaoimpl.getBlogComments(blogId);
		System.out.println("BLOGCOMMENTS::: " + blogComments.size() );
		return new ResponseEntity<List<BlogComment>>(blogComments,HttpStatus.OK);		
	}
	
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public ResponseEntity<?> addBlogComment( @RequestBody BlogComment blogComment,HttpSession session) {
		RegisterUser user=(RegisterUser)session.getAttribute("pal");
		if(user==null){
			error error=new error(1,"Unauthroized user");
			return new ResponseEntity<error>(error,HttpStatus.UNAUTHORIZED);
		}
		System.out.println("BLOG COMMENT is " + blogComment);
		System.out.println("BLOG COMMENT BODY " + blogComment.getBody());
		
		System.out.println("BLOG POST FROM BLOGCOMMENT " + blogComment.getBlogpost());
		Blog blogPost=blogdaoimpl.getBlogPost(blogComment.getBlogpost().getBlogid());
		if(blogPost==null){
			error error=new error(2,"Blogpost not found");
			return new ResponseEntity<error>(error,HttpStatus.NOT_FOUND);
		}
        Blog createdBlogPost= blogdaoimpl.addBlogPostComment(user, blogComment);
        return new ResponseEntity<Blog>(createdBlogPost,HttpStatus.OK);
    }
	
}
