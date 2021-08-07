package com.bookings.mvc.bean;

import java.util.Date;

public class HotelBean {
	private int hid;
	private String hotel_name;
	private int room_count;
	private String start_season;
	private String end_season;
	private Boolean dogs;
	
	
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}
	public String getHotel_name() {
		return hotel_name;
	}
	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}
	public int getRoom_count() {
		return room_count;
	}
	public void setRoom_count(int room_count) {
		this.room_count = room_count;
	}
	public String getStart_season() {
		return start_season;
	}
	public void setStart_season(String start_season) {
		this.start_season = start_season;
	}
	public String getEnd_season() {
		return end_season;
	}
	public void setEnd_season(String end_season) {
		this.end_season = end_season;
	}
	public Boolean getDogs() {
		return dogs;
	}
	public void setDogs(Boolean dogs) {
		this.dogs = dogs;
	}
	
	
}
