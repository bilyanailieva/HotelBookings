package com.bookings.mvc.dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bookings.mvc.bean.PriceBean;
import com.bookings.mvc.bean.RoomTypeBean;
import com.mysql.fabric.xmlrpc.base.Data;

public class PriceDao {
	private String url;
    private String name;
    private String pass;
    private Connection con;
    
    
    public PriceDao(String url, String name, String pass) {
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
    
    public boolean insertPrice(PriceBean price) throws SQLException {
        String sql = "insert into prices (rt_id, price_desc, price) values(?, ?, ?)";
        connect();
         
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, price.getRtid());
        statement.setString(2, price.getPrice_desc());
        statement.setFloat(3, price.getPrice());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
    
    public List<PriceBean> listAllPrices() throws SQLException {
        List<PriceBean> listPrice = new ArrayList<>();
         
        String sql = "select * from prices order by rt_id";
         
        connect();
         
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("pid");
            int rtid = resultSet.getInt("rt_id");
            String description = resultSet.getString("price_desc");
            Float price = resultSet.getFloat("price");


             
            PriceBean priceObj = new PriceBean(id, rtid, description, price);
            listPrice.add(priceObj);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listPrice;
    }
    
    public boolean updatePrice(PriceBean priceObj) throws SQLException {
        String sql = "update prices set rt_id = ?, price_desc = ?, price = ?";
        sql += " where pid = ?";
        connect();
         
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, priceObj.getRtid());
        statement.setString(2, priceObj.getPrice_desc());
        statement.setFloat(3, priceObj.getPrice());
        statement.setInt(4, priceObj.getPid());
        
        
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
    
    public PriceBean getPrice(int pid) throws SQLException {
        PriceBean priceObj = null;
        String sql = "SELECT * FROM prices WHERE pid = ?";
         
        connect();
         
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, pid);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
        	int rtid = resultSet.getInt("rt_id");
            String description = resultSet.getString("price_desc");
            Float price = resultSet.getFloat("price");
             
            priceObj = new PriceBean(pid, rtid, description, price);
        }
         
        resultSet.close();
        statement.close();
         
        return priceObj;
    }
    
    public boolean deletePrice(PriceBean priceObj) throws SQLException {
        String sql = "delete from prices where pid = ?";
         
        connect();
         
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, priceObj.getPid());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }

    public List<PriceBean> listPossiblePrices(int adult_count, int child_count, int number_of_rooms, int nights, int hid) throws SQLException {
    	List<Object> listPossiblePrices = new ArrayList<>();
        List<PriceBean> listPrice = new ArrayList<>();
        String sql = null;
       
        List<List<PriceBean>> options = new ArrayList<>();
        int assigned = 0;
        
        List<RoomTypeBean> possibleRTypes = accommodate(adult_count, child_count, number_of_rooms);
        
        
        if(possibleRTypes.size() > 0) {
        	
        for(RoomTypeBean rt : possibleRTypes) {
        	sql = "select * from prices where rt_id=" + rt.getId();
        	sql += " and hid = " + hid;
        	
        	connect();
            
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
             
            while (resultSet.next()) {
                int id = resultSet.getInt("pid");
                int rtid = resultSet.getInt("rt_id");
                String description = resultSet.getString("price_desc");
                Float price = resultSet.getFloat("price");
                Boolean weekend = resultSet.getBoolean("weekend");


                 
                PriceBean priceObj = new PriceBean(id, rtid, rt, description, price, weekend);
                listPrice.add(priceObj);
            }
             
            resultSet.close();
            statement.close();
             
            disconnect();
            
        }
        }
        
        return listPrice;
    }
    
    public List<RoomTypeBean> accommodate(int adult_count, int child_count, int number_of_rooms) throws SQLException {
    	RoomTypeBean rt = null;
    	List<String> guests = new ArrayList<>();
    	 List<RoomTypeBean> possibleRTypes = new ArrayList<>();

    			
    			String sql = "select * from room_types where adults <=" + adult_count;
    			sql += " and children <=" + child_count;
            	connect();
                
                Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                 
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String type = resultSet.getString("type");
                    int adult_c = resultSet.getInt("adults");
                    int children_c = resultSet.getInt("children");

                    rt = new RoomTypeBean(id, type, adult_c, children_c);
                    possibleRTypes.add(rt);
                }  
    	
    	return possibleRTypes;
    }
    

}
