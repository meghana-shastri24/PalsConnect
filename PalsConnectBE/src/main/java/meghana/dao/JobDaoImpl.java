package meghana.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import meghana.model.Job;

@Repository
@Transactional
public class JobDaoImpl {

	@Autowired
private	SessionFactory sf;
	
	
	public void postjob(Job job) {
		
		System.out.println("in dao");

		Session session=sf.getCurrentSession();
		session.save(job);
		System.out.println("in dao 1");

		session.flush();
		

	}


	public List<Job> viewalljobs() {
		
		Session session=sf.getCurrentSession();
		Query query = session.createQuery("from Job");
		List<Job> job=query.list();
		session.flush();
		
		return job;
	}

	

}
