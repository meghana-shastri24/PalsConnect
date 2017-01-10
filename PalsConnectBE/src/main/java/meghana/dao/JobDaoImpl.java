package meghana.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import meghana.model.AppliedJobs;
import meghana.model.Job;

@Repository
@Transactional
public class JobDaoImpl {

	@Autowired
private	SessionFactory sf;
	
	
	public void postjob(Job job) {
		Session session=sf.getCurrentSession();
		session.save(job);
		session.flush();

	}
	public List<Job> viewalljobs() {
		System.out.println("in job dao");

		Session session=sf.getCurrentSession();
		Query query = session.createQuery("from Job");
		
		List<Job> job=query.list();
		System.out.println(job);
		session.flush();
		System.out.println("in job dao end");

		return job;
	}


	public Job getjob(int id) {
		Session session=sf.getCurrentSession();
		Query query=session.createQuery("from Job where id=:un");
		query.setParameter("un", id);
		Job job=(Job) query.uniqueResult();
		session.flush();
		return job;			
	}

	public void applyjob(AppliedJobs ja) {
		Session session=sf.getCurrentSession();
		session.save(ja);
		session.flush();
		
	}

	public List<AppliedJobs> getAppliedJob(int id) {
		Session session=sf.getCurrentSession();
		Query query=session.createQuery("from AppliedJobs where palid=?");
		query.setParameter(0, id);
		List<AppliedJobs> jobs=query.list();
		session.flush();
		return jobs;
	}

	public AppliedJobs getAppliedJobbyJid(int jid, int palid) {
		Session session=sf.getCurrentSession();
		Query query=session.createQuery("from AppliedJobs where jobid=? and palid=?");
		query.setParameter(0, jid);
		query.setParameter(1, palid);
		AppliedJobs job=(AppliedJobs) query.uniqueResult();
		session.flush();
		return job;
	}

	public void removejob(int jid, int pid) {
		Session session=sf.getCurrentSession();
		Query query=session.createQuery("delete from AppliedJobs where jobid=? and palid=?");
		query.setParameter(0, jid);
		query.setParameter(1, pid);
		query.executeUpdate();
		session.flush();
		
	}

	public List<AppliedJobs> getAppliedJobDetails(int id) {
		Session session=sf.getCurrentSession();
		Query query=session.createQuery("from AppliedJobs where jobid=:jid");
		query.setParameter("jid", id);
		List<AppliedJobs> jobs=query.list();
		session.flush();
		return jobs;
	}

	

}
