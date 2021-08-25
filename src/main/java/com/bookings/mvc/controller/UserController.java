package com.bookings.mvc.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookings.mvc.bean.HotelBean;
import com.bookings.mvc.bean.UserBean;
import com.bookings.mvc.bean.RoomBean;
import com.bookings.mvc.bean.RoomTypeBean;
import com.bookings.mvc.dao.HotelDao;
import com.bookings.mvc.dao.UserDao;
import com.bookings.mvc.dao.RoomDao;
import com.bookings.mvc.dao.RoomTypeDao;

public class UserController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    private RoomDao roomDao;
    private RoomTypeDao roomTypeDao;
    private HotelDao hotelDao;
    private UserDao userDao;
 
    public void init() {
    	String url = getServletContext().getInitParameter("url");
		String name = getServletContext().getInitParameter("name");
		String pass = getServletContext().getInitParameter("pass");

		roomDao = new RoomDao(url, name, pass);
		roomTypeDao = new RoomTypeDao(url, name, pass);
		hotelDao = new HotelDao(url, name, pass);
		userDao = new UserDao(url, name, pass);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
		/*
		 * if(request.getParameter("btn_login")!=null) //check button click event not
		 * null from login.jsp page button { String
		 * username=request.getParameter("txt_username"); //get textbox name
		 * "txt_username" String password=request.getParameter("txt_password"); //get
		 * textbox name "txt_password"
		 * 
		 * UserBean loginBean=new UserBean(); //this class contain seeting up all
		 * received values from index.jsp page to setter and getter method for
		 * application require effectively
		 * 
		 * loginBean.setUsername(username); //set username through loginBean object
		 * loginBean.setPassword(password); //set password through loginBean object
		 * 
		 * UserDao loginDao=new UserDao(); //this class contain main logic to perform
		 * function calling and database operation
		 * 
		 * String authorize=loginDao.authorizeLogin(loginBean); //send loginBean object
		 * values into authorizeLogin() function in LoginDao class
		 * 
		 * if(authorize.equals("SUCCESS LOGIN")) //check calling authorizeLogin()
		 * function receive string "SUCCESS LOGIN" message after continue process {
		 * HttpSession session=request.getSession(); //session is created
		 * session.setAttribute("login",loginBean.getUsername()); //session name is
		 * "login" and store username in "getUsername()" get through loginBean object
		 * RequestDispatcher rd=request.getRequestDispatcher("/office/welcome.jsp");
		 * //redirect to welcome.jsp page rd.forward(request, response); } else {
		 * request.setAttribute("WrongLoginMsg",authorize); //wrong login error message
		 * is "WrongLoginMsg" RequestDispatcher
		 * rd=request.getRequestDispatcher("/office/index.jsp"); //show error same
		 * index.jsp page rd.include(request, response); } }
		 */
        doGet(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
 
        try {
            switch (action) {
            case "/office/welcome/list":
            	sendRoomsToCalendar(request, response);
                break;
            case "/office/welcome":
            	authorizeLogin(request, response);
            case "/office/login":
            	registerUser(request, response);
            default:
            	sendRoomsToCalendar(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void sendRoomsToCalendar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	List<RoomBean> listRoom = roomDao.listAllRooms();
        List<RoomTypeBean> listRoomType = roomTypeDao.listAllRoomTypes();
        List<HotelBean> listHotel = hotelDao.listAllHotels();
        request.setAttribute("listRoomTypes", listRoomType);
        request.setAttribute("listRoom", listRoom);
        request.setAttribute("listHotel", listHotel);
        RequestDispatcher dispatcher = request.getRequestDispatcher("../welcome.jsp");
        dispatcher.forward(request, response);
    }
    
    private void authorizeLogin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	String username = request.getParameter("txt_username");
    	String password = request.getParameter("txt_password");
        UserBean existingUser = userDao.UserLoginAuthorize(username, password);
        if(existingUser != null ) {
        	HttpSession session=request.getSession(); //session is created
            session.setAttribute("login", existingUser.getUsername()); //session name is "login" and  store username in "getUsername()" get through loginBean object
            RequestDispatcher rd=request.getRequestDispatcher("/office/welcome.jsp"); //redirect to welcome.jsp page
            rd.forward(request, response);
        }
        else
        {
            RequestDispatcher rd=request.getRequestDispatcher("/office/index.jsp"); //show error same index.jsp page
            rd.include(request, response);
        }	
    }
    
    private void registerUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	String fname = request.getParameter("txt_fname");
    	String lname = request.getParameter("txt_lname");
    	String username = request.getParameter("txt_username");
    	int role = Integer.parseInt(request.getParameter("txt_role"));
    	String password = request.getParameter("txt_password");
    	
    	
    	UserBean newUser = new UserBean(fname, lname, username, role, password);
        userDao.registerUser(newUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}
