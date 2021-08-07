package com.bookings.mvc.dao;

import java.sql.*;
import java.util.Date;

import com.bookings.mvc.bean.HotelBean;

public class HotelDao {
	public String authorizeHotelRegister(HotelBean hotelBean) //create authorizeRegister()function
    {
        String hotel_name=hotelBean.getHotel_name();
        int room_count=hotelBean.getRoom_count();
        String start_season=hotelBean.getStart_season();
        String end_season=hotelBean.getEnd_season();
        Boolean dogs = hotelBean.getDogs();
        
        String url="jdbc:mysql://localhost:3306/bookings"; //database connection url string
        String uname="root"; //database username
        String pass="misq1.r007"; //database password
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver"); //load driver
            Connection con=DriverManager.getConnection(url,uname,pass); //create connection
            
            PreparedStatement pstmt=null; //create statement
            
            pstmt=con.prepareStatement("insert into hotels(hotel_name,room_count,start_season,end_season,dogs) values(?,?,?,?,?)"); //sql insert query
            pstmt.setString(1,hotel_name);
            pstmt.setInt(2, room_count);
            pstmt.setString(3, start_season);
            pstmt.setString(4, end_season);
            pstmt.setBoolean(5, dogs);
            pstmt.executeUpdate(); //execute query
             
            pstmt.close(); //close statement
            
            con.close(); //close connection
           
            return "SUCCESS HOTEL REGISTER"; //if valid return string "SUCCESS REGISTER" 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
            return "FAIL HOTEL REGISTER"; //if invalid return string "FAIL REGISTER"
    }

}
