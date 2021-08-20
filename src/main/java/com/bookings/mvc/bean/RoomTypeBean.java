package com.bookings.mvc.bean;

public class RoomTypeBean {
	private int id;
	private String type;
	
	public RoomTypeBean() {
    }
 
    public RoomTypeBean(int id) {
        this.id = id;
    }
 
    public RoomTypeBean(int id, String type) {
        this(type);
        this.id = id;
    }
     
    public RoomTypeBean(String type) {
        this.type = type;
    }
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
