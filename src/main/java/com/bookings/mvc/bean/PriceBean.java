package com.bookings.mvc.bean;

public class PriceBean {
	private int pid;
	private int rtid;
	private RoomTypeBean rt;
	private String price_desc;
	private Float price;
	private Boolean weekend;
	private HotelBean hotel;

	public PriceBean() {
    }
 
    public PriceBean(int pid) {
        this.pid = pid;
    }
    
    public PriceBean(int pid, RoomTypeBean rt, String price_desc, Float price, Boolean weekend) {
        this(rt, price_desc, price, weekend);
        this.pid = pid;
    }
    
    public PriceBean(int pid, RoomTypeBean rt, String price_desc, Float price, HotelBean hotel) {
        this(rt, price_desc, price, hotel);
        this.pid = pid;
    }
    
    public PriceBean(RoomTypeBean rt, String price_desc, Float price, HotelBean hotel) {
        this.rt = rt;
        this.price_desc = price_desc; 
        this.price = price;
        this.hotel = hotel;
    }
 
    public PriceBean(int pid, RoomTypeBean rt, String price_desc, Float price) {
        this(rt, price_desc, price);
        this.pid = pid;
    }
    
    public PriceBean(RoomTypeBean rt, String price_desc, Float price, Boolean weekend) {
        this.rt = rt;
        this.price_desc = price_desc; 
        this.price = price;
        this.weekend = weekend;
    }
     
    public PriceBean(RoomTypeBean rt, String price_desc, Float price) {
        this.rt = rt;
        this.price_desc = price_desc; 
        this.price = price;
    }
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public RoomTypeBean getRt() {
		return rt;
	}
	public void setRt(RoomTypeBean rt) {
		this.rt = rt;
	}
	public String getPrice_desc() {
		return price_desc;
	}
	public void setPrice_desc(String price_desc) {
		this.price_desc = price_desc;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	
	public Boolean getWeekend() {
		return weekend;
	}

	public void setWeekend(Boolean weekend) {
		this.weekend = weekend;
	}

	public HotelBean getHotel() {
		return hotel;
	}

	public void setHotel(HotelBean hotel) {
		this.hotel = hotel;
	}
	
	
	
	
	
}
