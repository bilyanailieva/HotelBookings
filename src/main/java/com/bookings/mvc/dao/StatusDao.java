package com.bookings.mvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bookings.mvc.bean.CustomerBean;
import com.bookings.mvc.bean.ReservationBean;
import com.bookings.mvc.bean.StatusBean;

public class StatusDao {
	private String url;
    private String name;
    private String pass;
    private Connection con;
    
    public StatusDao(String url, String name, String pass) {
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
    
    public List<StatusBean> listAllStatuses() throws SQLException {
        List<StatusBean> listStatuses = new ArrayList<>();
         
        String sql = "select * from reservation_statuses";
         
        connect();
         
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	int status_id = resultSet.getInt("stat_id");
        	String status = resultSet.getString("status");

        	StatusBean stat = new StatusBean(status_id, status);
           
        	listStatuses.add(stat);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listStatuses;
    }
    
    public StatusBean getStatIdById(int id) throws SQLException {
    	StatusBean stat = null;
        String sql = "SELECT * FROM reservation_statuses WHERE stat_id = ?";
         
        connect();
         
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
        	int s_id = resultSet.getInt("stat_id");
        	String status = resultSet.getString("status");
            
        	stat = new StatusBean(s_id, status);
        }
         
        resultSet.close();
        statement.close();
         
        return stat;
    }
}
