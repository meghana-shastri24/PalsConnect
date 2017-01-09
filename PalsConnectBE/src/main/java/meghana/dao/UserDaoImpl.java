package meghana.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import meghana.model.RegisterUser;

@Repository
@Transactional
public class UserDaoImpl {
	
	@Autowired
	SessionFactory sf;
	
	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Transactional
	public List<RegisterUser> getallusers(RegisterUser user) {
		
		Session s=sf.getCurrentSession();
		SQLQuery query=s.createSQLQuery("select * from pal where username in (select username from pal where username!=? minus(select toname from friend where fromname=? union select fromname from friend where toname=?))");		
		
		query.setString(0, user.getUsername());
		query.setString(1, user.getUsername());
		query.setString(2, user.getUsername());
		query.addEntity(RegisterUser.class);
		
		List<RegisterUser> users=query.list();
		System.out.println(users);

		s.flush();
		return users;
		
	}

	@Transactional
	public RegisterUser getuserbyid(String string) {

		Session s=sf.getCurrentSession();
		Query query=s.createQuery("from RegisterUser where username=?");
		query.setString(0, string);
		RegisterUser u=(RegisterUser) query.uniqueResult();
		s.flush();
		return u;
	}


	@Transactional
	public RegisterUser savePerson(RegisterUser user) {
		Session s=sf.getCurrentSession();
		s.persist(user);
		s.flush();
		return user;
		
	}

	public RegisterUser updatepal(RegisterUser pals) {
		
		Session s=sf.getCurrentSession();
		int id=pals.getId();
		System.out.println(id);
		RegisterUser existingpal=s.get(RegisterUser.class, id);
		if(existingpal==null)
			return null;
		s.merge(pals);
		RegisterUser updatedpal=s.get(RegisterUser.class,id);
		s.flush();
		return updatedpal;
	}

	public void deletepal(int id) {
		Session s=sf.getCurrentSession();
		RegisterUser pal=s.get(RegisterUser.class, id);
		s.delete(pal);
		s.flush();
		
	}

	public RegisterUser validate(RegisterUser pal) {
		Session s=sf.getCurrentSession();
		Query query=s.createQuery("from RegisterUser where username=? and password=? ");
		query.setString(0, pal.getUsername());
		query.setString(1, pal.getPassword());
		
		
		RegisterUser validpal=(RegisterUser) query.uniqueResult();
		
System.out.println(validpal);
		
		return validpal;
	}

	
	
	
}
