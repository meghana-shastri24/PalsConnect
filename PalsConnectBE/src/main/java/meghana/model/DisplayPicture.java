package meghana.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
public class DisplayPicture {

	
	@Id
	@GeneratedValue
	private int dpid;
	
	
	private String dpname;
	
	@Lob
	private byte[] dp;

	private String username;
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getDpid() {
		return dpid;
	}

	public void setDpid(int dpid) {
		this.dpid = dpid;
	}

	public String getDpname() {
		return dpname;
	}

	public void setDpname(String dpname) {
		this.dpname = dpname;
	}

	public byte[] getDp() {
		return dp;
	}

	public void setDp(byte[] dp) {
		this.dp = dp;
	}
	
	
}
