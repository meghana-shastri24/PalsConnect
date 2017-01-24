package meghana.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import meghana.model.Blog;
import meghana.model.BlogComment;
import meghana.model.RegisterUser;

@Repository
@Transactional
public class BlogDaoImpl {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Blog> getBlogPosts() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Blog");
		List<Blog> blogPosts=query.list();
		return blogPosts;		
	}

	public Blog getBlogPost(int id) {
		Session session=sessionFactory.getCurrentSession();
		Blog blogPost=(Blog)session.get(Blog.class, id);
		return blogPost;
	}

	public Blog addBlogPost(RegisterUser user, Blog blogPost) {
		Session session=sessionFactory.getCurrentSession();
		blogPost.setPostedby(user);
		blogPost.setPostedon(new Date());
		session.save(blogPost);
		session.flush();
		Blog addedBlogPost=(Blog)session.get(Blog.class, blogPost.getBlogid());
		return addedBlogPost;
	}

	public List<BlogComment> getBlogComments(int blogId) {
		Session session=sessionFactory.getCurrentSession();
		Blog blogPost=(Blog)session.get(Blog.class, blogId);
		List<BlogComment> blogComments=blogPost.getComments();
		return blogComments;
		
		
	}

	public Blog addBlogPostComment(RegisterUser user, BlogComment blogComment, int id) {
		Session session=sessionFactory.getCurrentSession();
		 blogComment.setPostedby(user);
		 blogComment.setPostedon(new Date());
		 Blog blogPost=(Blog)session.get(Blog.class, id);
				 blogComment.setBlogpost(blogPost);
		 session.merge(blogComment);
		 session.flush();
		 return blogPost;
		 
	}

	public List<Blog> getBlogPost1(int id) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Blog where postedby=?");
		query.setInteger(0, id);
		List<Blog> blogPosts = query.list();
		return blogPosts;
	}
	
	public void deleteblogcomments(Blog bp) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("delete from BlogComment where blogpost=:id");
		query.setParameter("id", bp);
		query.executeUpdate();
		session.flush();
		
	}



	public void deleteblog(int id) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("delete from Blog where id=:bi");
		query.setParameter("bi", id);
		query.executeUpdate();
		session.flush();
		
		
	}


}
