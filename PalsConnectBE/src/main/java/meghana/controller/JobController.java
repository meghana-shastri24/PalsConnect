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

import meghana.dao.JobDaoImpl;
import meghana.model.Job;
import meghana.model.RegisterUser;

@RestController
public class JobController {
	
	@Autowired
	JobDaoImpl jobdaoimpl;
	
	
	@RequestMapping(value="/postjob", method=RequestMethod.POST)
	public ResponseEntity<Void> postjob(@RequestBody Job job, HttpSession session)
	{
		
		System.out.println("in postjob method");
		jobdaoimpl.postjob(job);
			return new ResponseEntity<Void> (HttpStatus.OK);
		
		
		
	}
	
	@RequestMapping(value="/viewjob", method=RequestMethod.GET)
	public ResponseEntity<List<Job>> viewalljobs(HttpSession session)
	{
		System.out.println("in job controller be");


			List<Job> job=jobdaoimpl.viewalljobs();
			System.out.println(job);

			return new ResponseEntity<List<Job>> (job,HttpStatus.OK);
		
	}

}
