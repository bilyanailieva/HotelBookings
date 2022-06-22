package com.bookings.mvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bookings.mvc.bean.RoomBean;
import com.bookings.mvc.bean.RoomTypeBean;

public class RoomDao {
	private String url;
    private String name;
    private String pass;
    private Connection con;
    
    
    public RoomDao(String url, String name, String pass) {
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
    
    public boolean insertRoom(RoomBean room) throws SQLException {
        String sql = "insert into rooms (rtid, name, first_floor, extra_bed, baby_crib, is_handycap, h_id)";
        sql += "values(?, ?, ?, ?, ?, ?, ?)";
        
        connect();
         
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, room.getRt().getId());
        statement.setString(2, room.getName());
        statement.setBoolean(3, room.isFirst_floor());
        statement.setBoolean(4, room.isExtra_bed());
        statement.setBoolean(5, room.isBaby_crib());
        statement.setBoolean(6, room.isHandycap());
        statement.setInt(7, room.getHotel().getHid());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
    
    public List<RoomBean> listAllRooms(int hid) throws SQLException {
        List<RoomBean> listRoom = new ArrayList<>();
         
        String sql = "select * from rooms where h_id = " + hid + " order by id";
         
        connect();
         
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int rtid = resultSet.getInt("rtid");
            String name = resultSet.getString("name");
            Boolean first_floor = resultSet.getBoolean("first_floor");
            Boolean extra_bed = resultSet.getBoolean("extra_bed");
            Boolean baby_crib = resultSet.getBoolean("baby_crib");
            Boolean is_handycap = resultSet.getBoolean("is_handycap");

             
            RoomBean room = new RoomBean(id, new RoomTypeBean(rtid), name, first_floor, extra_bed, baby_crib, is_handycap);
            listRoom.add(room);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listRoom;
    }
    
    public boolean updateRoom(RoomBean room) throws SQLException {
        String sql = "update rooms set rtid = ?, name = ?, first_floor = ?, extra_bed = ?, baby_crib = ?, is_handycap =?";
        sql += " where id = ?";
        connect();
         
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, room.getRt().getId());
        statement.setString(2, room.getName());
        statement.setBoolean(3, room.isFirst_floor());
        statement.setBoolean(4, room.isExtra_bed());
        statement.setBoolean(5, room.isBaby_crib());
        statement.setBoolean(6, room.isHandycap());
        statement.setInt(7, room.getId());
        
        
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
    
    public RoomBean getRoom(int id) throws SQLException {
        RoomBean room = null;
        String sql = "SELECT * FROM rooms WHERE id = ?";
         
        connect();
         
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
        	int rtid = resultSet.getInt("rtid");
            String name = resultSet.getString("name");
            Boolean first_floor = resultSet.getBoolean("first_floor");
            Boolean extra_bed = resultSet.getBoolean("extra_bed");
            Boolean baby_crib = resultSet.getBoolean("baby_crib");
            Boolean is_handycap = resultSet.getBoolean("is_handycap");
             
            room = new RoomBean(id, new RoomTypeBean(rtid), name, first_floor, extra_bed, baby_crib, is_handycap);
        }
         
        resultSet.close();
        statement.close();
         
        return room;
    }
    
    public boolean deleteRoom(RoomBean room) throws SQLException {
        String sql = "delete from rooms where id = ?";
         
        connect();
         
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, room.getId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }

}
