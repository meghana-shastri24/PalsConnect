package meghana.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import meghana.model.Friend;
import meghana.model.RegisterUser;

@Repository
@Transactional
public class FriendDaoImpl {
	
	@Autowired
	private SessionFactory sessionfactory;

	
	public List<Friend> getallfriends(String username)
	{
		Session session=sessionfactory.getCurrentSession();
		Query query=session.createQuery("from Friend where (fromname=? or toname=?) and status=?");
		query.setString(0, username);
		query.setString(1, username);
		query.setCharacter(2, 'A');
		List<Friend> friendslist=query.list();
		session.flush();
		return friendslist;
		
		
	}
	
	public void sendrequest(String from, String to)
	{
		Session session=sessionfactory.getCurrentSession();
		
		Friend f=new Friend();
		f.setFromname(from);
		f.setToname(to);
		f.setStatus('P');
		session.save(f);
		session.flush();
	
	}
	
	public List<Friend> getprequest(String username)
	{
		Session session=sessionfactory.getCurrentSession();
		Query query=session.createQuery("from Friend where toname=? and status=?");
		query.setString(0, username);
		query.setCharacter(1, 'P');
		List pendinglist= query.list();
		session.flush();
		return pendinglist;
		
	}
	
	public void updatePendingRequest(char friendStatus, String fromId, String toId) {
		Session session=sessionfactory.getCurrentSession();
		Query query=session.createQuery("update Friend set status=? where fromname=? and toname=?");
		query.setCharacter(0, friendStatus);
		query.setString(1, fromId);
		query.setString(2, toId);
		int count=query.executeUpdate();
		System.out.println("Number of records updated " + count);
		session.flush();
		
		
		
	}

}
