package meghana.controller;

import java.util.ArrayList;

import java.util.Calendar;
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
import org.springframework.web.bind.annotation.RestController;

import meghana.dao.JobDaoImpl;
import meghana.model.AppliedJobs;
import meghana.model.Job;
import meghana.model.RegisterUser;
import meghana.model.error;

@Controller
public class JobController {
	
	@Autowired
	JobDaoImpl jobdaoimpl;
	
	
	@RequestMapping(value="/postjob",method=RequestMethod.POST)
	public ResponseEntity<?> postJob(@RequestBody Job job,HttpSession session){
		
		RegisterUser user=(RegisterUser)session.getAttribute("pal");	
		if(user==null){	
			error er=new error(1,"Unauthorized user.. login using valid credentials");
			return new ResponseEntity<error>(er,HttpStatus.UNAUTHORIZED);
		}
		else{
	                job.setPostedon(Calendar.getInstance().getTime());
					jobdaoimpl.postjob(job);
				return new ResponseEntity<Void>(HttpStatus.OK);
			
	}}
	
	@RequestMapping(value="/viewjob", method=RequestMethod.GET)
	public ResponseEntity<List<Job>> viewalljobs(HttpSession session)
	{
		System.out.println("in job controller be");


			List<Job> job=jobdaoimpl.viewalljobs();
			System.out.println(job);

			return new ResponseEntity<List<Job>> (job,HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/applied/{id}", method=RequestMethod.PUT)
	public ResponseEntity<?> applyjob(@PathVariable (value="id")int id, HttpSession session)
	{
		
		System.out.println("in apply job");
		RegisterUser user=(RegisterUser)session.getAttribute("pal");
		System.out.println(user);

		if(user==null){	
			
			error er=new error(1,"Unauthorized user.. login using valid credentials");
			return new ResponseEntity<error>(er,HttpStatus.UNAUTHORIZED);
		}
		else{
		
		Job job=jobdaoimpl.getjob(id);
		System.out.println(job);

		AppliedJobs ajob = jobdaoimpl.getAppliedJobbyJid(id,user.getId());
		
		System.out.println(ajob);

		if(ajob==null){
		AppliedJobs ja=new AppliedJobs();
		ja.setJobid(id);
		ja.setTitle(job.getTitle());
		ja.setPalid(user.getId());
		ja.setName(user.getUsername());
		jobdaoimpl.applyjob(ja);
		System.out.println("success");

		return new ResponseEntity<Void>(HttpStatus.OK);	
		
		}
		else
		{
			System.out.println("erroorr");

			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		}
	}
	
	@RequestMapping(value="/appliedjob",method=RequestMethod.GET)
	public ResponseEntity<?> Appliedjob(HttpSession session){
		RegisterUser user=(RegisterUser)session.getAttribute("pal");	
		List<AppliedJobs> job = jobdaoimpl.getAppliedJob(user.getId());
		List<Job> cjob = new ArrayList<Job>();
		for(AppliedJobs j : job){
			Job jb = jobdaoimpl.getjob(j.getJobid());
			cjob.add(jb);
		}
		return new ResponseEntity<List<Job>>(cjob,HttpStatus.OK);		
	}

	
	@RequestMapping(value="/appliedjobdetails/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> Appliedjobdetails(@PathVariable(value="id") int id){
		List<AppliedJobs> job = jobdaoimpl.getAppliedJobDetails(id);
		return new ResponseEntity<List<AppliedJobs>>(job,HttpStatus.OK);		
	}	


}
