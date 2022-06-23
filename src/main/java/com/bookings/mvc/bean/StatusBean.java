package com.bookings.mvc.bean;

public class StatusBean {
	private int status_id;
	private String status;
	
	public int getStatus_id() {
		return status_id;
	}
	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public StatusBean() {
		
	}
	public StatusBean(int status_id, String status) {
		super();
		this.status_id = status_id;
		this.status = status;
	}
	
	public StatusBean(int status_id) {
		super();
		this.status_id = status_id;
	}
	
	
}
