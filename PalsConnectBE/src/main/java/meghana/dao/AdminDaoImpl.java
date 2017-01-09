package meghana.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import meghana.model.RegisterUser;


@Repository
@Transactional
public class AdminDaoImpl {
	
	@Autowired
	private SessionFactory sessionfactory;

	public List<RegisterUser> getusers() {
		
		Session session=sessionfactory.getCurrentSession();
		
		System.out.println("in admin dao");
		Query query=session.createQuery("from RegisterUser where status=?");
		query.setParameter(0,'P');
		List<RegisterUser> pals=query.list();
		
		session.flush();
		System.out.println(pals);
		return pals;
	}

	public void updateuser(RegisterUser pal) {

		Session session=sessionfactory.getCurrentSession();
		session.update(pal);
		session.flush();

	}



}
