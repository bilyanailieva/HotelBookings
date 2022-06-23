package com.bookings.mvc.bean;

public class ReservationBean {
	protected int res_id;
	protected int number_of_rooms;
	protected int adults_count;
	protected int child_count;
	protected String res_start;
	protected String res_end;
	protected StatusBean status;
	protected Float price;
	protected CustomerBean customer;
	protected String notes;
	protected HotelBean hotel;
	protected RoomBean room;
	
	public ReservationBean() {
		
	}
	
	public ReservationBean(int res_id) {
		this.res_id = res_id;
	}
	
	public ReservationBean(int res_id, int number_of_rooms, int adults_count, int child_count, String res_start,
			String res_end, StatusBean status, Float price, CustomerBean customer, String notes, RoomBean room) {
		// TODO Auto-generated constructor stub
		this(number_of_rooms, adults_count, child_count, res_start, res_end,
			status, price, customer);
		this.res_id = res_id;
		this.notes = notes;
		this.room = room;
	}
	
	public ReservationBean(int number_of_rooms, int adults_count, int child_count, String res_start, String res_end,
			StatusBean status, Float price, CustomerBean customer) {
		this(number_of_rooms, adults_count, child_count, res_start, res_end,
			status, customer);
		this.price = price;
	}
	
	public ReservationBean(int number_of_rooms, int adults_count, int child_count, String res_start, String res_end,
			StatusBean status, Float price, CustomerBean customer, HotelBean hotel) {
		this(number_of_rooms, adults_count, child_count, res_start, res_end,
			status, customer, hotel);
		this.price = price;
	}
	
	public ReservationBean(int res_id, int number_of_rooms, int adults_count, int child_count, String res_start,
			String res_end, StatusBean status, Float price, CustomerBean customer, String notes) {
		this(number_of_rooms, adults_count, child_count, res_start, res_end,
			status, price, customer, notes);
		this.res_id = res_id;
	}
	
	public ReservationBean(int res_id, int number_of_rooms, int adults_count, int child_count, String res_start,
			String res_end, StatusBean status, Float price, CustomerBean customer, String notes, HotelBean hotel) {
		this(number_of_rooms, adults_count, child_count, res_start, res_end,
			status, price, customer, notes, hotel);
		this.res_id = res_id;
	}
	
	public ReservationBean(int number_of_rooms, int adults_count, int child_count, String res_start, String res_end,
			StatusBean status, Float price, CustomerBean customer, String notes) {
		this(number_of_rooms, adults_count, child_count, res_start, res_end,
			status, customer);
		this.price = price;
		this.customer = customer;
		this.notes = notes;
	}
	
	public ReservationBean(int number_of_rooms, int adults_count, int child_count, String res_start, String res_end,
			StatusBean status, Float price, CustomerBean customer, String notes, HotelBean hotel) {
		this(number_of_rooms, adults_count, child_count, res_start, res_end,
			status, customer, hotel);
		this.price = price;
		this.customer = customer;
		this.notes = notes;
	}
	
	public ReservationBean(int number_of_rooms, int adults_count, int child_count, String res_start, String res_end) {
		this.number_of_rooms = number_of_rooms;
		this.adults_count = adults_count;
		this.child_count = child_count;
		this.res_start = res_start;
		this.res_end = res_end;
	}
	
	public ReservationBean(int number_of_rooms, int adults_count, int child_count, String res_start, String res_end, HotelBean hotel) {
		this.number_of_rooms = number_of_rooms;
		this.adults_count = adults_count;
		this.child_count = child_count;
		this.res_start = res_start;
		this.res_end = res_end;
		this.hotel = hotel;
	}
	
	public ReservationBean(int number_of_rooms, int adults_count, int child_count, String res_start, String res_end,
			StatusBean status, CustomerBean customer) {
		this.number_of_rooms = number_of_rooms;
		this.adults_count = adults_count;
		this.child_count = child_count;
		this.res_start = res_start;
		this.res_end = res_end;
		this.status = status;
		this.customer = customer;
	}
	
	public ReservationBean(int number_of_rooms, int adults_count, int child_count, String res_start, String res_end,
			StatusBean status, CustomerBean customer, HotelBean hotel) {
		this.number_of_rooms = number_of_rooms;
		this.adults_count = adults_count;
		this.child_count = child_count;
		this.res_start = res_start;
		this.res_end = res_end;
		this.status = status;
		this.customer = customer;
		this.hotel = hotel;
	}
	
	public int getRes_id() {
		return res_id;
	}
	public void setRes_id(int res_id) {
		this.res_id = res_id;
	}
	public int getNumber_of_rooms() {
		return number_of_rooms;
	}
	public void setNumber_of_rooms(int number_of_rooms) {
		this.number_of_rooms = number_of_rooms;
	}
	public int getAdults_count() {
		return adults_count;
	}
	public void setAdults_count(int adults_count) {
		this.adults_count = adults_count;
	}
	public int getChild_count() {
		return child_count;
	}
	public void setChild_count(int child_count) {
		this.child_count = child_count;
	}
	public String getRes_start() {
		return res_start;
	}
	public void setRes_start(String res_start) {
		this.res_start = res_start;
	}
	public String getRes_end() {
		return res_end;
	}
	public void setRes_end(String res_end) {
		this.res_end = res_end;
	}
	public StatusBean getStatus() {
		return status;
	}
	public void setStatus(StatusBean status) {
		this.status = status;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public CustomerBean getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerBean customer) {
		this.customer = customer;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}

	public HotelBean getHotel() {
		return hotel;
	}

	public void setHotel(HotelBean hotel) {
		this.hotel = hotel;
	}

	public RoomBean getRoom() {
		return room;
	}

	public void setRoom(RoomBean room) {
		this.room = room;
	}
	
	
	

}
