package meghana.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import meghana.dao.FriendDaoImpl;
import meghana.dao.UserDaoImpl;
import meghana.model.Friend;
import meghana.model.RegisterUser;
import meghana.model.error;

@Controller
public class FriendController {

	@Autowired
	private FriendDaoImpl frienddaoimpl;
	
	@Autowired
	private UserDaoImpl userdaoimpl;

		public FriendDaoImpl getFriendDao() {
			return frienddaoimpl;
		}

		public void setFriendDao(FriendDaoImpl friendDao) {
			this.frienddaoimpl = friendDao;
		}
		
	@RequestMapping(value="/getAllFriends",method=RequestMethod.GET)
		public ResponseEntity<?> getAllFriends(HttpSession session)
	{
		RegisterUser user=(RegisterUser)session.getAttribute("pal");

			if(user!=null){
			
			List<RegisterUser> k=new ArrayList<RegisterUser>();
			List<Friend> friends=frienddaoimpl.getallfriends(user.getUsername());

			if(friends!=null){
				for(Friend f:friends){
					if(f.getFromname().equals(user.getUsername()))
					k.add(userdaoimpl.getuserbyid(f.getToname()));
					else k.add(userdaoimpl.getuserbyid(f.getFromname()));

				}
			}
			return new ResponseEntity<List<RegisterUser>>(k,HttpStatus.OK);
			}
			else
				return new ResponseEntity<error>(new error(1,"Unauthorized user"),HttpStatus.UNAUTHORIZED);
		}
		

	
	
	
	@RequestMapping(value="/friendRequest",method=RequestMethod.POST)
	public ResponseEntity<?> sendFriendRequest(@RequestBody String username, HttpSession session){
		
		RegisterUser user=(RegisterUser) session.getAttribute("pal");
		if(user==null)
		{
			error e=new error(1,"Please login to continue...");

		return new ResponseEntity<error>(e,HttpStatus.UNAUTHORIZED);
		}
		
		else{
			
			frienddaoimpl.sendrequest(user.getUsername(),username);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	
	@RequestMapping(value="/pendingRequest",method=RequestMethod.GET)
	public ResponseEntity<?> getAllPendingRequest(HttpSession session){
		RegisterUser user=(RegisterUser)session.getAttribute("pal");
		
		if(user==null)
		{
			error e=new error(2,"Please login to continue...");

		return new ResponseEntity<error>(e,HttpStatus.UNAUTHORIZED);
		}
		
		else{
			List<RegisterUser> p=new ArrayList<RegisterUser>();
			List<Friend> pendingRequest=frienddaoimpl.getprequest(user.getUsername());
			if(pendingRequest!=null)
			{
				for(Friend f:pendingRequest)
				{
				p.add(userdaoimpl.getuserbyid(f.getFromname()));
			}

			}
			
			return new ResponseEntity<List<RegisterUser>>(p,HttpStatus.OK);

		}
	}
	
	
	@RequestMapping(value="/updateFriendRequest/{status}/{fromid}",method=RequestMethod.PUT)
	public ResponseEntity<?> updatePendingRequest(@PathVariable(value="status") char friendStatus,
			@PathVariable(value="fromid") String fromId,HttpSession session){
		RegisterUser user=(RegisterUser)session.getAttribute("pal");
		
		
		if(user==null)
			
		{
			error e=new error(3,"Please login to continue...");

		return new ResponseEntity<error>(e,HttpStatus.UNAUTHORIZED);
		}
		
		else{
			frienddaoimpl.updatePendingRequest(friendStatus,fromId,user.getUsername());
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	}

