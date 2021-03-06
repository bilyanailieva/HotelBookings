package com.bookings.mvc.bean;

public class UserBean {
	private int id;
	private String firstname, lastname, username, password;
	private HotelBean hotel_id;
	private int hid;

	public UserBean() {
	}

	public UserBean(int id) {
		this.id = id;
	}

	public UserBean(int id, String firstname, String lastname, String username, HotelBean hotel_id, String password) {
		this(firstname, lastname, username, hotel_id, password);
		this.id = id;
	}

	public UserBean(int id, String firstname, String lastname, String username, int hid, String password) {
		this(firstname, lastname, username, hid, password);
		this.id = id;
	}

	public UserBean(String firstname, String lastname, String username, HotelBean hotel_id, String password) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.hotel_id = hotel_id;
		this.password = password;
	}

	public UserBean(String firstname, String lastname, String username, int hid, String password) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.hid = hid;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public HotelBean getHotelId() {
		return hotel_id;
	}

	public void setHotelId(HotelBean hotel_id) {
		this.hotel_id = hotel_id;
	}

	public int getHId() {
		return hid;
	}

	public void setHId(int hid) {
		this.hid = hid;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
