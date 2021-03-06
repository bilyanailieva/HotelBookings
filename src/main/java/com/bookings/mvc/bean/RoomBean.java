package com.bookings.mvc.bean;

public class RoomBean {
	private int id; 
	private RoomTypeBean rt;
	private String name;
	private boolean first_floor; 
	private boolean extra_bed; 
	private boolean baby_crib; 
	private boolean handycap;
	private HotelBean hotel;

	public RoomBean() {
    }
 
    public RoomBean(int id) {
        this.id = id;
    }
 
    public RoomBean(int id, RoomTypeBean rt, String name, boolean first_floor, boolean extra_bed, boolean baby_crib, boolean handycap) {
        this(rt, name, first_floor, extra_bed, baby_crib, handycap);
        this.id = id;
    }
     
    public RoomBean(RoomTypeBean rt, String name, boolean first_floor, boolean extra_bed, boolean baby_crib, boolean handycap) {
        this.rt = rt;
        this.name = name; 
        this.first_floor = first_floor;
        this.extra_bed = extra_bed;
        this.baby_crib = baby_crib;
        this.handycap = handycap;
    }
    
    public RoomBean(int id, RoomTypeBean rt, String name, boolean first_floor, boolean extra_bed, boolean baby_crib, boolean handycap, HotelBean hotel) {
        this(rt, name, first_floor, extra_bed, baby_crib, handycap, hotel);
        this.id = id;
    }
     
    public RoomBean(RoomTypeBean rt, String name, boolean first_floor, boolean extra_bed, boolean baby_crib, boolean handycap, HotelBean hotel) {
        this.rt = rt;
        this.name = name; 
        this.first_floor = first_floor;
        this.extra_bed = extra_bed;
        this.baby_crib = baby_crib;
        this.handycap = handycap;
        this.hotel = hotel;
    }
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public RoomTypeBean getRt() {
		return rt;
	}
	public void setRtid(RoomTypeBean rt) {
		this.rt = rt;
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
	
	public HotelBean getHotel() {
		return hotel;
	}

	public void setHotel(HotelBean hotel) {
		this.hotel = hotel;
	}
	
	
}
