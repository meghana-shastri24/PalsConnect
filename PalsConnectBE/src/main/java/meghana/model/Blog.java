package meghana.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Blog implements Serializable {

		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
	private int blogid;
		
		
	private Date postedon;

	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn
	private RegisterUser postedby;

	private String title;
	
	@Lob
	@Column
	private String body;

	@OneToMany(mappedBy="blogpost",fetch=FetchType.EAGER,cascade=CascadeType.ALL,orphanRemoval=true)
	@JsonIgnore
	private List<BlogComment> comments=new ArrayList<BlogComment>();

	public int getBlogid() {
		return blogid;
	}

	public void setBlogid(int blogid) {
		this.blogid = blogid;
	}

	public Date getPostedon() {
		return postedon;
	}

	public void setPostedon(Date postedon) {
		this.postedon = postedon;
	}

	public RegisterUser getPostedby() {
		return postedby;
	}

	public void setPostedby(RegisterUser postedby) {
		this.postedby = postedby;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public List<BlogComment> getComments() {
		return comments;
	}

	public void setComments(List<BlogComment> comments) {
		this.comments = comments;
	}

	
	

}
