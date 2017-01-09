package meghana.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import meghana.model.DisplayPicture;

@Repository
@Transactional
public class DpDaoImpl {
	
	@Autowired
	private SessionFactory sessionfactory;

	
	
	public  DisplayPicture getFile(String username) {
		Session session = sessionfactory.getCurrentSession();
		Query query=session.createQuery("from DisplayPicture where username =?");
		query.setParameter(0, username);
		DisplayPicture image=(DisplayPicture) query.uniqueResult();
		
		
		return image;
	}


	public  void save(DisplayPicture uploadFile) {

		Session session = sessionfactory.getCurrentSession();
		session.save(uploadFile);
		session.flush();
		
		
	}


	public void update(DisplayPicture uploadFile) {
		Session session = sessionfactory.getCurrentSession();
		session.update(uploadFile);
		session.flush();
		
	}

}
