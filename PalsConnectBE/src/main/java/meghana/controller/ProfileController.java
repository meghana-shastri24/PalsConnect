package meghana.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import meghana.dao.BlogDaoImpl;
import meghana.dao.FriendDaoImpl;
import meghana.dao.UserDaoImpl;
import meghana.model.Blog;
import meghana.model.Friend;
import meghana.model.RegisterUser;
import meghana.model.error;

@Controller
public class ProfileController {

	@Autowired
	UserDaoImpl userdaoimpl;
	
	@Autowired
	FriendDaoImpl frienddaoimpl;
	
	@Autowired
	BlogDaoImpl blogdaoimpl;
	
	
	
	@RequestMapping(value="/getfriends", method=RequestMethod.GET)
	public ResponseEntity<?> getfriends(HttpSession session)
	{
		System.out.println("in fc be");
		RegisterUser user=(RegisterUser)session.getAttribute("pal");
		System.out.println(user);

		List<RegisterUser> p=new ArrayList<RegisterUser>();

			if(user!=null)
	
			{
				
			
			List<Friend> friends=frienddaoimpl.getallfriends(user.getUsername());
			
			
			System.out.println(friends);
			
			if(friends!=null)
			{
				for(Friend f:friends)
					
				{
					System.out.println(f.getFromname());
					System.out.println(user.getUsername());
					
					
					if((f.getFromname()).equals(user.getUsername()))
					{
				p.add(userdaoimpl.getuserbyid(f.getToname()));
					}	
				else
				{
					System.out.println("helloo" + f.getFromname());
						p.add(userdaoimpl.getuserbyid(f.getFromname()));

				}
			}
			}
	}
			
			else
			{
				error e=new error(0,"Please login to continue");

				return new ResponseEntity<error>(e,HttpStatus.UNAUTHORIZED);
			}
			
			return new ResponseEntity<List<RegisterUser>>(p,HttpStatus.OK);

	}
	
	
	@RequestMapping(value="/getblogs", method=RequestMethod.GET)
	public ResponseEntity<?> getblogs(HttpSession session)
	{
		System.out.println("in blog be");
		RegisterUser user=(RegisterUser)session.getAttribute("pal");
		System.out.println(user);

			if(user!=null){
				
			
			List<Blog> blogs=blogdaoimpl.getBlogPost1(user.getId());
			System.out.println(blogs);
			return new ResponseEntity<List<Blog>>(blogs,HttpStatus.OK);
			}
			else
			{
				error e=new error(0,"Please login to continue");

				return new ResponseEntity<error>(e,HttpStatus.UNAUTHORIZED);
			}
	}
	
	@RequestMapping(value = "/deleteblog/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteblog(@PathVariable(value="id") int id,HttpSession session){
		RegisterUser user=(RegisterUser)session.getAttribute("pal");
		if(user==null){
			error e=new error(1,"Unauthroized User");
			return new ResponseEntity<error>(e,HttpStatus.UNAUTHORIZED);
		}
		Blog bp = blogdaoimpl.getBlogPost(id);
		blogdaoimpl.deleteblogcomments(bp);
		blogdaoimpl.deleteblog(id);
		return new ResponseEntity<List<Blog>>(HttpStatus.OK);
	}
	
	
	
	
}
