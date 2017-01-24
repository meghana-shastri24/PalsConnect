package meghana.controller;




import java.io.File;
import java.io.FileOutputStream;
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

import meghana.dao.DpDaoImpl;
import meghana.dao.UserDaoImpl;
import meghana.model.DisplayPicture;
import meghana.model.RegisterUser;
import meghana.model.error;

@RestController
public class UserController {
	
	@Autowired
	UserDaoImpl userdaoimpl;
	
	@Autowired
	DpDaoImpl dpdaoimpl;
	
	public UserDaoImpl getuserdaoimpl() { 
		return userdaoimpl;
	}


	public void setuserdaoimpl(UserDaoImpl userdaoimpl) {
		this.userdaoimpl = userdaoimpl;
	}
	
	//getallusers
	@RequestMapping(value="/pals", method=RequestMethod.GET)
	public ResponseEntity<?> getallusers(HttpSession session)
	{
		
			RegisterUser user=(RegisterUser)session.getAttribute("pal");
			
			if(user==null)
			{
				error e=new error(4,"User doesnt exist..Please register to continue");

				return new ResponseEntity<error>(e,HttpStatus.UNAUTHORIZED);
			
			}
			
			else
			{
				List<RegisterUser> users=userdaoimpl.getallusers(user);
				for(RegisterUser u:users)
					System.out.println("IsONline " + u.getIsonline());
				return new ResponseEntity<List<RegisterUser>>(users,HttpStatus.OK);
			}
		
	}
	
	

	
	
	//registeruser
	@RequestMapping(value="/pals", method=RequestMethod.POST)
	public ResponseEntity<?> register(@RequestBody RegisterUser user)
	{
		
		
		if(user.getPassword().equals(user.getCpassword()))
		{
		user.setIsonline(false);
		user.setStatus('P');
		RegisterUser pal=userdaoimpl.savePerson(user);
		if(pal.getId()==0)
		{
			error e=new error(2,"Sorry unable to insert");

			return new ResponseEntity<error>(e,HttpStatus.CONFLICT);

		}
		
		
		else{
			return new ResponseEntity<RegisterUser>(pal,HttpStatus.CREATED);

		}
		}
		
		
		else
		{
			error e=new error(3,"Sorry unable to insert");

			return new ResponseEntity<error>(e, HttpStatus.CONFLICT);
		}
		
		
	}
	
	//updateuser
	@RequestMapping(value="/pals/{id}", method=RequestMethod.POST)
	public ResponseEntity<RegisterUser> updatepal(@PathVariable int id, @RequestBody RegisterUser pals)
	{
		RegisterUser pal=userdaoimpl.updatepal(pals);
		if(pal==null)
			return new ResponseEntity<RegisterUser> (HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<RegisterUser> (pal,HttpStatus.OK);
	}

	
	//login
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody RegisterUser pal, HttpSession session){
		
		System.out.println("In User Controller BE");
		RegisterUser validatedpal=userdaoimpl.validate(pal);
		System.out.println(validatedpal);

		if(validatedpal==null)
		{
			error e=new error(1,"User doesnt exist..Please register to continue");

			return new ResponseEntity<error>(e,HttpStatus.UNAUTHORIZED);
		}
		
		if(validatedpal.getStatus()=='P')
		{
			error e=new error(3,"Admin yet to approve your registration..");
			return new ResponseEntity<error>(e,HttpStatus.CONFLICT);

			
		}
		else
			
		{	
			session.setAttribute("pal", validatedpal);
			
			System.out.println(pal);
			validatedpal.setIsonline(true);
			
			userdaoimpl.updatepal(validatedpal);
			System.out.println(validatedpal.getIsonline());
			
			  DisplayPicture getimage=dpdaoimpl.getFile(pal.getUsername());
			  if(getimage!=null){
		  	String name=getimage.getDpname();
		  	System.out.println(getimage.getDp());
		  	byte[] imagefiles=getimage.getDp();
		  	try{
		  		String path=("C:/Users/user/workspace/PalsConnectFE/WebContent/images/images/"+pal.getUsername());
		  		File file=new File(path);
		  		//file.mkdirs();
		  		FileOutputStream fos = new FileOutputStream(file);//to Write some data 
		  		fos.write(imagefiles);
		  		fos.close();
		  		}catch(Exception e){
		  		e.printStackTrace();
		  		}
			  }
			  
			return new ResponseEntity<RegisterUser>(validatedpal, HttpStatus.OK);
		}
		
	}

	//logout
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
