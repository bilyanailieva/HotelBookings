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
	
    private Connection con;
     
   
	
	protected void connect() throws SQLException {
		String url="jdbc:mysql://localhost:3306/bookings"; //database connection url string
	    String uname="root"; //database username
	    String pass="misq1.r007";
	    
		if (con == null || con.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					throw new SQLException(e);
					}
			con = DriverManager.getConnection(
					url, uname, pass);
			}
	}

	protected void disconnect() throws SQLException {
		if (con != null && !con.isClosed()) {
			con.close();
			}
		}
	
	public String addRoomType(RoomTypeBean roomTypeBean) {
		
		String type = roomTypeBean.getType();
		
		String url="jdbc:mysql://localhost:3306/bookings"; //database connection url string
	    String uname="root"; //database username
	    String pass="misq1.r007"; //database password
	    
	    try
        {
            Class.forName("com.mysql.jdbc.Driver"); //load driver
            Connection con=DriverManager.getConnection(url,uname,pass); //create connection
            
            PreparedStatement pstmt=null; //create statement
           
            pstmt=con.prepareStatement("insert into room_types(type) value(?)"); //sql insert query
            pstmt.setString(1,type);
            pstmt.executeUpdate(); 
             
            pstmt.close(); //close statement
            
            con.close(); //close connection
           
            return "SUCCESSFULLY ADDED ROOM TYPE"; //if valid return string "SUCCESS REGISTER" 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
            return "OOPS! AN ISSUE OCCURED"; //if invalid return string "FAIL REGISTER"
	        
	}
	
	public String checkIfExists(RoomTypeBean roomTypeBean) {
		
		 String type = roomTypeBean.getType();
		
		 String url="jdbc:mysql://localhost:3306/bookings"; //database connection url string
		 String uname="root"; //database username
		 String pass="misq1.r007"; //database password

		 try
	        {
	         Class.forName("com.mysql.jdbc.Driver"); //load driver
	         Connection con=DriverManager.getConnection(url,uname,pass); //create connection
    		
           	
            PreparedStatement ps=null; //create statement
            
            ps=con.prepareStatement("select * from room_types where type=?");
            ps.setString(1,type);  
            ResultSet rs = ps.executeQuery();
           	while(rs.next())
            {
           			return "exists";
           	}
           	
           	ps.close(); //close statement
            
            con.close();
          //close connection
        
     }
     catch(Exception e)
     {
         e.printStackTrace();
         
     }
         return "free";
	}
	
	public List<RoomTypeBean> getRoomTypes() {
		
        RoomTypeBean room_type = null;
        List<RoomTypeBean> list = new ArrayList<RoomTypeBean>();

        String url="jdbc:mysql://localhost:3306/bookings"; //database connection url string
		String uname="root"; //database username
		String pass="misq1.r007"; //database password
		 
        try {
        	Class.forName("com.mysql.jdbc.Driver"); //load driver
	        Connection con=DriverManager.getConnection(url,uname,pass); //create connection
	        
	        String query = "select * from room_types";
	        
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                room_type = new RoomTypeBean();
                /*Retrieve one employee details 
                and store it in employee object*/
                room_type.setId(rs.getInt("id"));
                room_type.setType(rs.getString("type"));
 
                //add each employee to the list
                list.add(room_type);
              
                rs.close();
                statement.close();
                con.close();
                
                return list;
                
            }
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
        return list;
    }
}
