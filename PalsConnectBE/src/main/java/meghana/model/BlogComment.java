package meghana.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BlogComment {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int bcid;
	
	
private Date postedon;


@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
@JoinColumn
private RegisterUser postedby;


@ManyToOne(fetch=FetchType.LAZY)
@JoinColumn
private Blog blogpost;


private String body;


public int getBcid() {
	return bcid;
}


public void setBcid(int bcid) {
	this.bcid = bcid;
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


public Blog getBlogpost() {
	return blogpost;
}


public void setBlogpost(Blog blogpost) {
	this.blogpost = blogpost;
}


public String getBody() {
	return body;
}


public void setBody(String body) {
	this.body = body;
}



}
