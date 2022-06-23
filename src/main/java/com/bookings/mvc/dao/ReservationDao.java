package com.bookings.mvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bookings.mvc.bean.CustomerBean;
import com.bookings.mvc.bean.HotelBean;
import com.bookings.mvc.bean.PriceBean;
import com.bookings.mvc.bean.ReservationBean;
import com.bookings.mvc.bean.RoomBean;
import com.bookings.mvc.bean.StatusBean;

public class ReservationDao {
	private String url;
    private String name;
    private String pass;
    private Connection con;
    
    public ReservationDao(String url, String name, String pass) {
        this.url = url;
        this.name = name;
        this.pass = pass;
    }
     
    protected void connect() throws SQLException {
        if (con == null || con.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            con = DriverManager.getConnection(url, name, pass);
        }
    }
    
    protected void disconnect() throws SQLException {
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }
	
    public boolean sendReservationRequest(ReservationBean res_request) throws SQLException {
    	try {
        String sql = "insert into reservations (number_of_rooms, adults_count, child_count, res_start, res_end, status, price, customer_id, notes, hotelid) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        connect();
         
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, res_request.getNumber_of_rooms());
        statement.setInt(2, res_request.getAdults_count());
        statement.setInt(3, res_request.getChild_count());
        statement.setString(4, res_request.getRes_start());
        statement.setString(5, res_request.getRes_end());
        statement.setInt(6, res_request.getStatus().getStatus_id());
        statement.setFloat(7, res_request.getPrice());
        statement.setInt(8, res_request.getCustomer().getC_id());
        statement.setString(9, res_request.getNotes());
        statement.setInt(10, res_request.getHotel().getHid());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    	}
    	catch(NullPointerException e) {
    		System.out.println("Handled!");
    		return false;
    	}
    }
    
    public List<ReservationBean> listAllConfirmedReservations(HotelBean hotel) throws SQLException {
        List<ReservationBean> listConfirmed = new ArrayList<>();
        
        String sql = "select * from reservations where status != 1 and status != 7 and hotelid = " + hotel.getHid();
        
        connect();
         
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	int res_id = resultSet.getInt("res_id");
        	int number_of_rooms = resultSet.getInt("number_of_rooms");
        	int adults_count = resultSet.getInt("adults_count");
        	int child_count = resultSet.getInt("child_count");
        	String res_start = resultSet.getString("res_start");
        	String res_end = resultSet.getString("res_end");
        	int status = resultSet.getInt("status");
        	Float price = resultSet.getFloat("price");
        	int customer_id = resultSet.getInt("customer_id");
        	String notes = resultSet.getString("notes");

        	ReservationBean reservation = new ReservationBean(res_id, number_of_rooms, adults_count,
        			child_count, res_start, res_end, new StatusBean(status), price, new CustomerBean(customer_id), notes, hotel);
           
            listConfirmed.add(reservation);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listConfirmed;
    }
    
    public List<Integer> returnRoomsTaken(String arrival, String departure, int total_rooms, HotelBean hotel) 
    		throws SQLException, ParseException {
    	long days_to_check = 0;
    	int rooms_taken = 0;
        List<ReservationBean> listConfirmed = listAllConfirmedReservations(hotel);
        List<Integer> availability = new ArrayList<>();
        LocalDate date1 = LocalDate.parse(arrival);
        LocalDate date2 = LocalDate.parse(departure);
        days_to_check = ChronoUnit.DAYS.between(date1, date2);
        for(int i = 0; i < days_to_check; i++) {
        	rooms_taken = 0;
        	LocalDate date = date1;
        	for (ReservationBean res : listConfirmed) {
        	LocalDate start = LocalDate.parse(res.getRes_start());
        	LocalDate end = LocalDate.parse(res.getRes_end());
        	
        	if((start.isBefore(date) && end.isAfter(date)) || (start.isEqual(date) && end.isAfter(date)) || start.isEqual(date)) {
        		rooms_taken += res.getNumber_of_rooms();
        	}
        	else {
        		rooms_taken += 0;
        	}
        }
        	date1 = date1.plusDays(1);
        	availability.add(total_rooms - rooms_taken);
        }
       
        return availability;
        
    }
    
    public List<ReservationBean> listReservationsNoAssignedRooms(HotelBean hotel) throws SQLException {
        List<ReservationBean> listUnassigned = new ArrayList<>();
        String sql = "select * from reservations r where";
        		sql+= " (r.status != 1 and r.status != 7 and hotelid = " + hotel.getHid();
        		sql+= ") and";
        		sql+= " r.number_of_rooms > (select count(room_index) from reservation_rooms where res_id = r.res_id)";
         
        connect();
         
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	int res_id = resultSet.getInt("res_id");
        	int number_of_rooms = resultSet.getInt("number_of_rooms");
        	int adults_count = resultSet.getInt("adults_count");
        	int child_count = resultSet.getInt("child_count");
        	String res_start = resultSet.getString("res_start");
        	String res_end = resultSet.getString("res_end");
        	int status = resultSet.getInt("status");
        	Float price = resultSet.getFloat("price");
        	int customer_id = resultSet.getInt("customer_id");
        	String notes = resultSet.getString("notes");

        	CustomerDao dao = new CustomerDao(url, name, pass);
        	CustomerBean cm = dao.getCmIdById(customer_id);
        	StatusDao sdao = new StatusDao(url, name, pass);
        	StatusBean stat = sdao.getStatIdById(status);
        			           
        	ReservationBean reservation = new ReservationBean(res_id, number_of_rooms, adults_count, child_count, res_start, res_end, stat, price, cm, notes);
        	listUnassigned.add(reservation);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listUnassigned;
    }
    
    public List<ReservationBean> listAllConfirmedReservationsToday(LocalDate date, HotelBean hotel) throws SQLException {
        List<ReservationBean> listConfirmed = new ArrayList<>();
        String today = date.toString();
        String sql = "select * from reservations where status != 1 and status != 7 and hotelid = ? and res_start = ?";
        
        connect();
         
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, hotel.getHid());
        statement.setString(2, today);
        ResultSet resultSet = statement.executeQuery();
         
        while (resultSet.next()) {
        	int res_id = resultSet.getInt("res_id");
        	int number_of_rooms = resultSet.getInt("number_of_rooms");
        	int adults_count = resultSet.getInt("adults_count");
        	int child_count = resultSet.getInt("child_count");
        	String res_start = resultSet.getString("res_start");
        	String res_end = resultSet.getString("res_end");
        	int status = resultSet.getInt("status");
        	Float price = resultSet.getFloat("price");
        	int customer_id = resultSet.getInt("customer_id");
        	String notes = resultSet.getString("notes");

        	CustomerDao dao = new CustomerDao(url, name, pass);
        	CustomerBean cm = dao.getCmIdById(customer_id);
        	StatusDao sdao = new StatusDao(url, name, pass);
        	StatusBean stat = sdao.getStatIdById(status);
        			           
        	ReservationBean reservation = new ReservationBean(res_id, number_of_rooms, adults_count, child_count, res_start, res_end, stat, price, cm, notes);
        	
            listConfirmed.add(reservation);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listConfirmed;
    }
    
    public List<ReservationBean> listAllPendingReservations(HotelBean hotel) throws SQLException {
        List<ReservationBean> listConfirmed = new ArrayList<>();
        String sql = "select * from reservations where status = 1 and hotelid = " + hotel.getHid();
         
        connect();
         
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	int res_id = resultSet.getInt("res_id");
        	int number_of_rooms = resultSet.getInt("number_of_rooms");
        	int adults_count = resultSet.getInt("adults_count");
        	int child_count = resultSet.getInt("child_count");
        	String res_start = resultSet.getString("res_start");
        	String res_end = resultSet.getString("res_end");
        	int status = resultSet.getInt("status");
        	Float price = resultSet.getFloat("price");
        	int customer_id = resultSet.getInt("customer_id");
        	String notes = resultSet.getString("notes");
        	
        	CustomerDao dao = new CustomerDao(url, name, pass);
        	CustomerBean cm = dao.getCmIdById(customer_id);
        	StatusDao sdao = new StatusDao(url, name, pass);
        	StatusBean stat = sdao.getStatIdById(status);

        	ReservationBean reservation = new ReservationBean(res_id, number_of_rooms, adults_count, child_count, res_start, res_end, stat, price, cm, notes, hotel);
           
            listConfirmed.add(reservation);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listConfirmed;
    }
    
    public ReservationBean getReservationByCID(int c_id) throws SQLException {
        ReservationBean res = null;
         
        String sql = "select * from reservations where customer_id = ? and status = 1";
         
        connect();
         
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, c_id);
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
        	int res_id = resultSet.getInt("res_id");
        	int number_of_rooms = resultSet.getInt("number_of_rooms");
        	int adults_count = resultSet.getInt("adults_count");
        	int child_count = resultSet.getInt("child_count");
        	String res_start = resultSet.getString("res_start");
        	String res_end = resultSet.getString("res_end");
        	int status = resultSet.getInt("status");
        	Float price = resultSet.getFloat("price");
        	int customer_id = resultSet.getInt("customer_id");
        	String notes = resultSet.getString("notes");
        	int hid = resultSet.getInt("hotelid");
        	
        	CustomerDao dao = new CustomerDao(url, name, pass);
        	CustomerBean cm = dao.getCmIdById(customer_id);
        	StatusDao sdao = new StatusDao(url, name, pass);
        	StatusBean stat = sdao.getStatIdById(status);

        	res = new ReservationBean(res_id, number_of_rooms, adults_count, child_count, res_start, res_end, stat, price, cm, notes, new HotelBean(hid));
           
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return res;
    }
    
    public ReservationBean getReservationById(int res_id, String stat) throws SQLException {
        ReservationBean res = null;
         
        String sql = "select * from reservations where res_id = ? and "
        		+ stat;
         
        connect();
         
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, res_id);
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
        	int r_id = resultSet.getInt("res_id");
        	int number_of_rooms = resultSet.getInt("number_of_rooms");
        	int adults_count = resultSet.getInt("adults_count");
        	int child_count = resultSet.getInt("child_count");
        	String res_start = resultSet.getString("res_start");
        	String res_end = resultSet.getString("res_end");
        	int status = resultSet.getInt("status");
        	Float price = resultSet.getFloat("price");
        	int customer_id = resultSet.getInt("customer_id");
        	String notes = resultSet.getString("notes");
        	int hid = resultSet.getInt("hotelid");
        	
        	res = new ReservationBean(r_id, number_of_rooms, adults_count, child_count, res_start, res_end, new StatusBean(status, stat), price, new CustomerBean(customer_id), notes, new HotelBean(hid));
           
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return res;
    }
    
    public boolean assignRooms(int res_id, int room_id) throws SQLException {
    	String sql = "insert into reservation_rooms(res_id, room_id) values(?, ?)";
        connect();
         
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, res_id);
        statement.setInt(2, room_id);
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
    
    public boolean updateReservationStatus(int res_id, int status) throws SQLException {
    	String sql = "update reservations set status=?";
        sql += " where res_id = ?";
        connect();
         
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, status);
        statement.setInt(2, res_id);
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
    
    public List<String> listAssignedRooms(int res_id) throws SQLException {
        List<String> listAssigned = new ArrayList<>();
        String sql = "select name from rooms r left join reservation_rooms rr on r.id = rr.room_id where rr.res_id = ?";
         
        connect();
         
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, res_id);
        ResultSet resultSet = statement.executeQuery();
         
        while (resultSet.next()) {
        	String name = resultSet.getString("name");
 
        	listAssigned.add(name);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listAssigned;
    }
    
    public List<ReservationBean> listAllAssignedReservations(int hid) throws SQLException {
        List<ReservationBean> listAssigned = new ArrayList<>();
        String sql = "select * from reservation_rooms rr left join reservations r on rr.res_id = r.res_id"; 
         sql += " where r.status != 1 and r.status !=7 and r.hotelid = " + hid;
         
        connect();
         
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	int res_id = resultSet.getInt("res_id");
        	int number_of_rooms = resultSet.getInt("number_of_rooms");
        	int adults_count = resultSet.getInt("adults_count");
        	int child_count = resultSet.getInt("child_count");
        	String res_start = resultSet.getString("res_start");
        	String res_end = resultSet.getString("res_end");
        	int status = resultSet.getInt("status");
        	Float price = resultSet.getFloat("price");
        	int customer_id = resultSet.getInt("customer_id");
        	String notes = resultSet.getString("notes");
        	int room_id = resultSet.getInt("room_id");

        	ReservationBean reservation = new ReservationBean(res_id, number_of_rooms, adults_count, child_count, res_start, res_end, new StatusBean(status), price, new CustomerBean(customer_id), notes, new RoomBean(room_id));

 
        	listAssigned.add(reservation);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listAssigned;
    }
    
    public ReservationBean getAllReservationDetails(int res_id) throws SQLException {
        ReservationBean res = null;
         
        String sql = "select * from reservations where res_id = ?";
         
        connect();
         
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, res_id);
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
        	int r_id = resultSet.getInt("res_id");
        	int number_of_rooms = resultSet.getInt("number_of_rooms");
        	int adults_count = resultSet.getInt("adults_count");
        	int child_count = resultSet.getInt("child_count");
        	String res_start = resultSet.getString("res_start");
        	String res_end = resultSet.getString("res_end");
        	int status = resultSet.getInt("status");
        	Float price = resultSet.getFloat("price");
        	int customer_id = resultSet.getInt("customer_id");
        	String notes = resultSet.getString("notes");
        	int hid = resultSet.getInt("hotelid");
        	
        	CustomerDao dao = new CustomerDao(url, name, pass);
        	CustomerBean cm = dao.getCmIdById(customer_id);
        	StatusDao sdao = new StatusDao(url, name, pass);
        	StatusBean stat = sdao.getStatIdById(status);
        	HotelDao hotelDao = new HotelDao(url, name, pass);
        	HotelBean hotel = hotelDao.getHotel(hid);

        	res = new ReservationBean(r_id, number_of_rooms, adults_count, child_count, res_start, res_end, stat, price, cm, notes, hotel);
           
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return res;
    }
    
    public List<ReservationBean> listReservations(HotelBean hotel) throws SQLException {
        List<ReservationBean> listConfirmed = new ArrayList<>();
        String sql = "select * from reservations where hotelid = " + hotel.getHid();
         
        connect();
         
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	int res_id = resultSet.getInt("res_id");
        	int number_of_rooms = resultSet.getInt("number_of_rooms");
        	int adults_count = resultSet.getInt("adults_count");
        	int child_count = resultSet.getInt("child_count");
        	String res_start = resultSet.getString("res_start");
        	String res_end = resultSet.getString("res_end");
        	int status = resultSet.getInt("status");
        	Float price = resultSet.getFloat("price");
        	int customer_id = resultSet.getInt("customer_id");
        	String notes = resultSet.getString("notes");

        	CustomerDao dao = new CustomerDao(url, name, pass);
        	CustomerBean cm = dao.getCmIdById(customer_id);
        	StatusDao sdao = new StatusDao(url, name, pass);
        	StatusBean stat = sdao.getStatIdById(status);
        			
        	ReservationBean reservation = new ReservationBean(res_id, number_of_rooms, adults_count, child_count, res_start, res_end, stat, price, cm, notes);
           
            listConfirmed.add(reservation);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listConfirmed;
    }
    
    public boolean updateReservation(ReservationBean res) throws SQLException {
    	String sql = "update reservations set number_of_rooms=?, adults_count=?, child_count=?, res_start=?, res_end=?, status=?, price=?, notes=?";
        sql += " where res_id = ?";
        connect();
         
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, res.getNumber_of_rooms());
        statement.setInt(2, res.getAdults_count());
        statement.setInt(3, res.getChild_count());
        statement.setString(4, res.getRes_start());
        statement.setString(5, res.getRes_end());
        statement.setInt(6, res.getStatus().getStatus_id());
        statement.setFloat(7, res.getPrice());
        statement.setString(8, res.getNotes());
        statement.setInt(9, res.getRes_id());
        
        
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
}
