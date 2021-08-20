package com.bookings.mvc.bean;

public class RoomBean {
	private int id; 
	private int rtid;
	private String name;
	private boolean first_floor; 
	private boolean extra_bed; 
	private boolean baby_crib; 
	private boolean handycap;
	
	public RoomBean() {
    }
 
    public RoomBean(int id) {
        this.id = id;
    }
 
    public RoomBean(int id, int rtid, String name, boolean first_floor, boolean extra_bed, boolean baby_crib, boolean handycap) {
        this(rtid, name, first_floor, extra_bed, baby_crib, handycap);
        this.id = id;
    }
     
    public RoomBean(int rtid, String name, boolean first_floor, boolean extra_bed, boolean baby_crib, boolean handycap) {
        this.rtid = rtid;
        this.name = name; 
        this.first_floor = first_floor;
        this.extra_bed = extra_bed;
        this.baby_crib = baby_crib;
        this.handycap = handycap;
    }
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRtid() {
		return rtid;
	}
	public void setRtid(int rtid) {
		this.rtid = rtid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isFirst_floor() {
		return first_floor;
	}
	public void setFirst_floor(boolean first_floor) {
		this.first_floor = first_floor;
	}
	public boolean isExtra_bed() {
		return extra_bed;
	}
	public void setExtra_bed(boolean extra_bed) {
		this.extra_bed = extra_bed;
	}
	public boolean isBaby_crib() {
		return baby_crib;
	}
	public void setBaby_crib(boolean baby_crib) {
		this.baby_crib = baby_crib;
	}
	public boolean isHandycap() {
		return handycap;
	}
	public void setHandycap(boolean handycap) {
		this.handycap = handycap;
	}
	
	
}
