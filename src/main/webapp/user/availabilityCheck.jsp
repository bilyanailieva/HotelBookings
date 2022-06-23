<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.bookings.mvc.controller.RoomTypeController" %>
<%@page import="com.bookings.mvc.dao.RoomTypeDao" %>
<%

String url="jdbc:mysql://localhost:3306/bookings"; //database connection url string
String uname="root"; //database username
String pass="misq1.r007";

    if(request.getParameter("room_type")!=null) //get "uname" jQuery & Ajax part this line 'data:"uname="+username' from index.jsp file check not null
    {
        String room_type=request.getParameter("room_type"); //getable "uname" store in new "user_name variable"
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver"); //load driver
            Connection con=DriverManager.getConnection(url,uname,pass); //create connection
           
            PreparedStatement pstmt=null; //create statement
            
            pstmt=con.prepareStatement("select * from room_types where type=?"); //sql select query
            pstmt.setString(1,room_type); //set where cluase condition username set is new user_name variable
            ResultSet rs=pstmt.executeQuery(); //execute query and set in ResultSet object "rs".
            
            if(rs.next())               
            {  
                %>
                <span class="text-danger">Room Type Already Exists ! </span>
                <%
            }
            else
            {
                %>
                <span class="text-success">Room Type is available</span>
                <%
            }

            con.close(); //close connection
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
%>