package meghana.model;





import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="pal")
public class RegisterUser {
	
@Id
@GeneratedValue
private int id;	

private	String username;
private	String email;
private	String password;
private String cpassword;
private long phno;
private String gender;
private boolean isonline;
private Date dob;
private String role;
private char status;



public String getCpassword() {
	return cpassword;
}
public void setCpassword(String cpassword) {
	this.cpassword = cpassword;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}

public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

public long getPhno() {
	return phno;
}
public void setPhno(long phno) {
	this.phno = phno;
}
public boolean getIsonline() {
	return isonline;
}
public void setIsonline(boolean isonline) {
	this.isonline = isonline;
}
public Date getDob() {
	return dob;
}
public void setDob(Date dob) {

	this.dob = dob;
}

public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public char getStatus() {
	return status;
}
public void setStatus(char status) {
	this.status = status;
}


}
