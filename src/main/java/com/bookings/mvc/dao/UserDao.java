package com.bookings.mvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bookings.mvc.bean.RoomBean;
import com.bookings.mvc.bean.UserBean;

public class UserDao 
{
	private String url;
    private String name;
    private String pass;
    private Connection con;
    
    
    public UserDao(String url, String name, String pass) {
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
    
    public UserBean UserLoginAuthorize(String username, String password) throws SQLException {
    	UserBean user = null;
        String sql = "select * from users where username=? and password=?";
        
        connect();
        
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        
        if(resultSet.next())
        {    
        	int id = resultSet.getInt("id");
            String fname = resultSet.getString("firstname");   //fetchable database record username and password store in this two variable dbusername,dbpassword above created 
            String lname = resultSet.getString("lastname"); 
            int role = resultSet.getInt("role");
            
            user = new UserBean(id, fname, lname, username, role, password);
            
        } 
        statement.close();       
        disconnect(); 
        
        return user;
    }
	
    public boolean registerUser(UserBean user) throws SQLException {
        String sql = "insert into users (firstname, lastname, username, role, password) values(?, ?, ?, ?, ?)";
        connect();
         
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, user.getFirstname());
        statement.setString(2, user.getLastname());
        statement.setString(3, user.getUsername());
        statement.setInt(4, user.getRole());
        statement.setString(5, user.getPassword());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
}

