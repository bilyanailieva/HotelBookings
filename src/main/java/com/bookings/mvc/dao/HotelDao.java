package com.bookings.mvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bookings.mvc.bean.HotelBean;
import com.bookings.mvc.bean.PriceBean;

public class HotelDao {
	private String url;
    private String name;
    private String pass;
    private Connection con;
    
    
    public HotelDao(String url, String name, String pass) {
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
    
    public boolean insertHotel(HotelBean hotel) throws SQLException {
        String sql = "insert into hotels (hotel_name, room_count, start_season, end_season, dogs) values(?, ?, ?, ?, ?)";
        connect();
         
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, hotel.getHotel_name());
        statement.setInt(2, hotel.getRoom_count());
        statement.setString(3, hotel.getStart_season());
        statement.setString(4, hotel.getEnd_season());
        statement.setBoolean(5, hotel.getDogs());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
    
    public List<HotelBean> listAllHotels() throws SQLException {
        List<HotelBean> listHotel = new ArrayList<>();
         
        String sql = "select * from hotels order by hid";
         
        connect();
         
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("hid");
            String name = resultSet.getString("hotel_name");
            int rc = resultSet.getInt("room_count");
            String start_season = resultSet.getString("start_season");
            String end_season = resultSet.getString("end_season");
            Boolean dogs = resultSet.getBoolean("dogs");
            
            HotelBean hotel = new HotelBean(id, name, rc, start_season, end_season, dogs);
            listHotel.add(hotel);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listHotel;
    }
    
    public boolean updateHotel(HotelBean hotel) throws SQLException {
        String sql = "update hotels set hotel_name = ?, room_count = ?, start_season = ?, end_season = ?, dogs = ?";
        sql += " where hid = ?";
        connect();
         
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, hotel.getHotel_name());
        statement.setInt(2, hotel.getRoom_count());
        statement.setString(3, hotel.getStart_season());
        statement.setString(4, hotel.getEnd_season());
        statement.setBoolean(5, hotel.getDogs());
        statement.setInt(6, hotel.getHid());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
    
    public HotelBean getHotel(int hid) throws SQLException {
        HotelBean hotel = null;
        String sql = "SELECT * FROM hotels WHERE hid = ?";
         
        connect();
         
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, hid);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String name = resultSet.getString("hotel_name");
            int rc = resultSet.getInt("room_count");
            String start_season = resultSet.getString("start_season");
            String end_season = resultSet.getString("end_season");
            Boolean dogs = resultSet.getBoolean("dogs");
            
            hotel = new HotelBean(hid, name, rc, start_season, end_season, dogs);
        }
         
        resultSet.close();
        statement.close();
         
        return hotel;
    }
    
    public boolean deleteHotel(HotelBean hotel) throws SQLException {
        String sql = "delete from hotels where hid = ?";
         
        connect();
         
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, hotel.getHid());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }

}
