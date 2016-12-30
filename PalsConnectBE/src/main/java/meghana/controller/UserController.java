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

import meghana.dao.UserDaoImpl;
import meghana.model.RegisterUser;

@RestController
public class UserController {
	
	@Autowired
	UserDaoImpl userdaoimpl;
	
	public UserDaoImpl getuserdaoimpl() { 
		return userdaoimpl;
	}


	public void setuserdaoimpl(UserDaoImpl userdaoimpl) {
		this.userdaoimpl = userdaoimpl;
	}
	
	@RequestMapping(value="/pals", method=RequestMethod.GET)
	public ResponseEntity<List<RegisterUser>> getallusers()
	{
		
		System.out.println("in pals 1");
		List<RegisterUser> r=userdaoimpl.getallusers();
		System.out.println("in pals 2");

		
		if(r.isEmpty())
		return new ResponseEntity<List<RegisterUser>> (HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<List<RegisterUser>> (r,HttpStatus.OK);
		
	}

	
	@RequestMapping(value="/pals/{id}", method=RequestMethod.GET)
	public ResponseEntity<RegisterUser> getuserbyid(@PathVariable ("id") int id)
	{
		
		RegisterUser u= userdaoimpl.getuserbyid(id);
		
		if(u==null)
		return new ResponseEntity<RegisterUser> (HttpStatus.NO_CONTENT);
		return new ResponseEntity<RegisterUser> (u,HttpStatus.OK);
	}
	
	@RequestMapping(value="/pals", method=RequestMethod.POST)
	public ResponseEntity<?> register(@RequestBody RegisterUser user)
	{
		user.setIsonline(false);
		user.setStatus(false);

		RegisterUser pal=userdaoimpl.savePerson(user);
		if(pal.getId()==0)
		{
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);

		}
		else{
			return new ResponseEntity<RegisterUser>(pal,HttpStatus.CREATED);

		}
	}
	
	@RequestMapping(value="/pals/{id}", method=RequestMethod.POST)
	public ResponseEntity<RegisterUser> updatepal(@PathVariable ("id") int id, @RequestBody RegisterUser pals)
	{
		RegisterUser pal=userdaoimpl.updatepal(pals);
		if(pal==null)
			return new ResponseEntity<RegisterUser> (HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<RegisterUser> (pal,HttpStatus.OK);
	}

	@RequestMapping(value="/pals/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletepal(@PathVariable ("id") int id)
	{
		
		RegisterUser pal=userdaoimpl.getuserbyid(id);
		if(pal==null)
			return new ResponseEntity<Void> (HttpStatus.NOT_FOUND);
		userdaoimpl.deletepal(id);
			return new ResponseEntity<Void> (HttpStatus.OK);
		

	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody RegisterUser pal, HttpSession session){
		
		System.out.println("In User Controller BE");
		RegisterUser validpal=userdaoimpl.validate(pal);
		
		System.out.println(validpal);

		if(validpal==null)
			return new ResponseEntity<Error>(HttpStatus.UNAUTHORIZED);
		
		else
			
		{	
			validpal.setIsonline(true);
			return new ResponseEntity<RegisterUser>(validpal, HttpStatus.OK);
		}
		
	}

	@RequestMapping(value="/logout",method=RequestMethod.PUT)
	public ResponseEntity<?> logout(HttpSession session){
		RegisterUser user=(RegisterUser)session.getAttribute("pal");
		if(user!=null){
			user.setIsonline(false);
			userdaoimpl.updatepal(user);
		}
		session.removeAttribute("user");
		session.invalidate();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
