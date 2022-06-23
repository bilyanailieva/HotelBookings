package com.bookings.mvc.bean;

public class CustomerBean {
	private int c_id;
	private String fname;
	private String lname;
	private String email;
	private String phone;
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public CustomerBean() {
		
	}
	
	public CustomerBean(int c_id) {
		this.c_id = c_id;
	}
	
	public CustomerBean(int c_id, String fname, String lname, String email, String phone) {
		this(fname, lname, email, phone);
		this.c_id = c_id;
	}
	
	public CustomerBean(String fname, String lname, String email, String phone) {
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.phone = phone;
	}
	
	public CustomerBean(String email) {
		this.email = email;
	}
	
	public CustomerBean(String email, int c_id) {
		this.c_id = c_id;
		this.email = email;
	}

}
