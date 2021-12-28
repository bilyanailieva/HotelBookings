package com.bookings.mvc.bean;

public class PriceBean {
	private int pid;
	private int rtid;
	private RoomTypeBean type;
	private String price_desc;
	private Float price;
	private Boolean weekend;

	public PriceBean() {
    }
 
    public PriceBean(int pid) {
        this.pid = pid;
    }
    
    public PriceBean(int pid, int rtid, RoomTypeBean type, String price_desc, Float price, Boolean weekend) {
        this(rtid, price_desc, price);
        this.weekend = weekend;
        this.type = type;
        this.pid = pid;
    }
    
    public PriceBean(int pid, int rtid, String price_desc, Float price, Boolean weekend) {
        this(rtid, price_desc, price, weekend);
        this.pid = pid;
    }
 
    public PriceBean(int pid, int rtid, String price_desc, Float price) {
        this(rtid, price_desc, price);
        this.pid = pid;
    }
    
    public PriceBean(int rtid, String price_desc, Float price, Boolean weekend) {
        this.rtid = rtid;
        this.price_desc = price_desc; 
        this.price = price;
        this.weekend = weekend;
    }
     
    public PriceBean(int rtid, String price_desc, Float price) {
        this.rtid = rtid;
        this.price_desc = price_desc; 
        this.price = price;
    }
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getRtid() {
		return rtid;
	}
	public void setRtid(int rtid) {
		this.rtid = rtid;
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

	public RoomTypeBean getType() {
		return type;
	}

	public void setType(RoomTypeBean type) {
		this.type = type;
	}
	
	public Boolean getWeekend() {
		return weekend;
	}

	public void setWeekend(Boolean weekend) {
		this.weekend = weekend;
	}
	
	
	
	
}
