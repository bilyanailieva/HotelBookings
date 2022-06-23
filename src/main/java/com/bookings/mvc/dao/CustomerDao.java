package com.bookings.mvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bookings.mvc.bean.CustomerBean;
import com.bookings.mvc.bean.ReservationBean;
import com.bookings.mvc.bean.RoomBean;

public class CustomerDao {
	private String url;
    private String name;
    private String pass;
    private Connection con;
    
    
    public CustomerDao(String url, String name, String pass) {
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
    
    public boolean newCmFromResReqest(CustomerBean cust) throws SQLException {
    	String sql = "insert into customers (email) values(?)";
        connect();
         
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, cust.getEmail()); 
        
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
    
    public int getCmIdByEmail(String email) throws SQLException {
    	int id = 0;
        String sql = "SELECT * FROM customers WHERE email = ?";
         
        connect();
         
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, email);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
        	id = resultSet.getInt("c_id");
            
        }
         
        resultSet.close();
        statement.close();
         
        return id;
    }
    
    public CustomerBean getCmIdById(int id) throws SQLException {
    	CustomerBean cm = null;
        String sql = "SELECT * FROM customers WHERE c_id = ?";
         
        connect();
         
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
        	int c_id = resultSet.getInt("c_id");
        	String email = resultSet.getString("email");
        	String fname = resultSet.getString("fname");
        	String lname = resultSet.getString("lname");
        	String phone = resultSet.getString("phone");
            
        	cm = new CustomerBean(c_id, fname, lname, email, phone);
        }
         
        resultSet.close();
        statement.close();
         
        return cm;
    }
    
    public boolean updateCustomerInformation(CustomerBean cm) throws SQLException {
    	String sql = "update customers set fname = ?, lname = ?, email = ?, phone = ?";
        sql += " where c_id = ?";
         
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, cm.getFname());
        statement.setString(2, cm.getLname());
        statement.setString(3, cm.getEmail());
        statement.setString(4, cm.getPhone());
        statement.setInt(5, cm.getC_id());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
}
