package com.bookings.mvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bookings.mvc.bean.RoomTypeBean;

public class RoomTypeDao {
	private String url;
    private String name;
    private String pass;
    private Connection con;
    
    
    public RoomTypeDao(String url, String name, String pass) {
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
    
    public boolean insertRoomType(RoomTypeBean room_type) throws SQLException {
        String sql = "insert into room_types(type, adults, children) value(?, ?, ?)";
        connect();
         
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, room_type.getType());
        statement.setInt(2, room_type.getAdults());
        statement.setInt(3, room_type.getChildren());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
    
    public List<RoomTypeBean> listAllRoomTypes() throws SQLException {
        List<RoomTypeBean> listRoomType = new ArrayList<>();
         
        String sql = "select * from room_types order by id";
      //String sql = "select rt.* from room_types rt left join rooms r on rt.id = r.rtid where r.h_id = " + hid + " group by rt.id order by id";
        
        
        connect();
         
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String type = resultSet.getString("type");
            int adults = resultSet.getInt("adults");
            int children = resultSet.getInt("children");
             
            RoomTypeBean roomType = new RoomTypeBean(id, type, adults, children);
            listRoomType.add(roomType);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listRoomType;
    }
    
    public boolean updateRoomType(RoomTypeBean roomType) throws SQLException {
        String sql = "update room_types set type = ?, adults = ?, children = ?";
        sql += " where id = ?";
        connect();
         
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, roomType.getType());
        statement.setInt(2, roomType.getAdults());
        statement.setInt(3, roomType.getChildren());
        statement.setInt(4, roomType.getId());
        
        
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
    
    public RoomTypeBean getRoomType(int id) throws SQLException {
        RoomTypeBean roomType = null;
        String sql = "SELECT * FROM room_types WHERE id = ?";
         
        connect();
         
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String type = resultSet.getString("type");
             
            roomType = new RoomTypeBean(id, type);
        }
         
        resultSet.close();
        statement.close();
         
        return roomType;
    }
    
    public boolean deleteRoomType(RoomTypeBean roomType) throws SQLException {
        String sql = "delete from room_types where id = ?";
         
        connect();
         
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, roomType.getId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
    
    
	/*
	 * public String checkIfExists(RoomTypeBean roomTypeBean) throws SQLException {
	 * String room_type = roomTypeBean.getType(); String sql =
	 * "select * from room_types where type=?";
	 * 
	 * try {
	 * 
	 * connect(); PreparedStatement ps = con.prepareStatement(sql); // create
	 * statement ps.setString(1, room_type); ResultSet rs = ps.executeQuery();
	 * 
	 * while (rs.next()) {
	 * 
	 * return "exists";
	 * 
	 * } ps.close(); // close statement
	 * 
	 * disconnect(); } catch (Exception e) { e.printStackTrace();
	 * 
	 * } // close connection return "free";
	 * 
	 * }
	 */
	
	public Boolean checkIfExists(RoomTypeBean roomTypeBean) throws SQLException {
		 String sql = "select * from room_types where type=?";
		 connect();
		 //create statement
		 
		 //ps=con.prepareStatement("select * from room_types where type=?");
		 //ps.setString(1,type); 
		 PreparedStatement statement = con.prepareStatement(sql);
	     statement.setString(1, roomTypeBean.getType());
	     
	     ResultSet resultSet = statement.executeQuery();
         
	        if (resultSet.next()) {
	        	resultSet.close();
		        statement.close();
	            return true;
	            
	            
	        }
	        else {
	        	return false;
	        }
	        
	         
	        
	         
	        
		 }

}
