package org.ranga.passws.model;

public class UserInfo {
	
	
	String userid;
	String password;
	String role;
	String emailid;
	
	
	public UserInfo()
	{
		
	}
	public UserInfo(String userid,String password,String role,String emailid)
	{
		this.userid = userid;
		this.password = password;
		this.role = role;
		this.emailid = emailid;
	}

	

	public String getUserid() {
		return userid;
	}



	public void setUserid(String userid) {
		this.userid = userid;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	public String getEmailid() {
		return emailid;
	}



	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	
	
	
	
	
	
	
}
