package org.ranga.passws.model;

public class BioData {
	
	private String empid;
	private String firstname;
	private String lastname;
	private String fathersname;
	private String mothersname;
	private String dob;
	private String pob;
	private String mobile;
	private String email;
	private String occupation;
	private String nationality;
	private String presentaddress;
	private String permanentaddress;	
	
	public BioData()
	{
		
	}	
	public BioData(String empid, String firstname, String lastname, String fathersname, String mothersname, String dob, String pob, String mobile, String email, String occupation, String nationality, String presentaddress, String permanentaddress)
	{
		this.empid = empid;
		this.firstname = firstname;		
		this.lastname = lastname;
		this.fathersname = fathersname;		
		this.mothersname = mothersname;
		this.dob = dob;
		this.pob = pob;
		this.mobile = mobile;
		this.email = email;
		this.occupation = occupation;
		this.nationality = nationality;
		this.presentaddress = presentaddress;
		this.permanentaddress = permanentaddress;
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFathersname() {
		return fathersname;
	}
	public void setFathersname(String fathersname) {
		this.fathersname = fathersname;
	}
	public String getMothersname() {
		return mothersname;
	}
	public void setMothersname(String mothersname) {
		this.mothersname = mothersname;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getPob() {
		return pob;
	}
	public void setPob(String pob) {
		this.pob = pob;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getPresentaddress() {
		return presentaddress;
	}
	public void setPresentaddress(String presentaddress) {
		this.presentaddress = presentaddress;
	}
	public String getPermanentaddress() {
		return permanentaddress;
	}
	public void setPermanentaddress(String permanentaddress) {
		this.permanentaddress = permanentaddress;
	}

	
	
	
	

}
