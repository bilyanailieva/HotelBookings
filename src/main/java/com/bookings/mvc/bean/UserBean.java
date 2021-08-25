package com.bookings.mvc.bean;

public class UserBean 
{
	private int id, role;
    private String firstname, lastname, username, password;
    
    public UserBean() {
    }
 
    public UserBean(int id) {
        this.id = id;
    }
 
    public UserBean(int id, String firstname, String lastname, String username, int role, String password) {
        this(firstname, lastname, username, role, password);
        this.id = id;
    }
     
    public UserBean(String firstname, String lastname, String username, int role, String password) {
        this.firstname = firstname;
        this.lastname = lastname; 
        this.username = username;
        this.role = role;
        this.password = password; 
    }
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
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

	public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password=password;
    }
}

