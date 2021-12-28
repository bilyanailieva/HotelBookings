package com.bookings.mvc.bean;

public class RoomTypeBean {
	private int id;
	private String type;
	private int adults;
	private int children;
	
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
    
    public RoomTypeBean(int id, String type, int adults, int children) {
        this(type, adults, children);
        this.id = id;
    }
    
    public RoomTypeBean(String type, int adults, int children) {
        this.type = type;
        this.adults = adults;
        this.children = children;
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

	public int getAdults() {
		return adults;
	}

	public void setAdults(int adults) {
		this.adults = adults;
	}

	public int getChildren() {
		return children;
	}

	public void setChildren(int children) {
		this.children = children;
	}
	
	
	
}
