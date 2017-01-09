package meghana.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import meghana.dao.AdminDaoImpl;
import meghana.model.RegisterUser;

@RestController
public class AdminController {

	@Autowired
	AdminDaoImpl admindaoimpl;
	
	
	@RequestMapping(value="/getusers", method=RequestMethod.GET)
	public ResponseEntity<List<RegisterUser>> getusers()
	
	{
		System.out.println("In admin controller be");
	List<RegisterUser> pals=admindaoimpl.getusers();
	
	if(pals==null)
	{
		return new ResponseEntity<List<RegisterUser>>(HttpStatus.NO_CONTENT);
	}
	
	else
	{
		return new ResponseEntity<List<RegisterUser>>(pals, HttpStatus.OK);
	}
	}
	
	@RequestMapping(value="/permit", method=RequestMethod.POST)
	public ResponseEntity<Void> permitusers(@RequestBody RegisterUser pal)
	{
		pal.setStatus('A');
		admindaoimpl.updateuser(pal);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/deny", method=RequestMethod.POST)
	public ResponseEntity<Void> denyusers(@RequestBody RegisterUser pal)
	{
		pal.setStatus('R');
		admindaoimpl.updateuser(pal);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	
}
