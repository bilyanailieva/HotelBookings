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
        doGet(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
 
        try {
            switch (action) {
            case "/office/welcome/list":
            	//sendRoomsToCalendar(request, response);
                break;
            case "/office/welcome":
            	authorizeLogin(request, response);
            case "/office/login":
            	registerUser(request, response);
            case "/office/register":
            	showRegisterFormData(request, response);
            default:
            	//sendRoomsToCalendar(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    private void showRegisterFormData(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	List<HotelBean> listHotel = hotelDao.listAllHotels();
    	request.setAttribute("listHotel", listHotel);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
        dispatcher.forward(request, response);
    }
    
    private void authorizeLogin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	String username = request.getParameter("txt_username");
    	String password = request.getParameter("txt_password");
    	String today = request.getParameter("currentDate");
        UserBean existingUser = userDao.UserLoginAuthorize(username, password);
        if(existingUser != null ) {
        	HttpSession session=request.getSession();
            session.setAttribute("login", existingUser.getUsername());
            session.setAttribute("currentDate", today);
            RequestDispatcher rd=request.getRequestDispatcher("/office/welcome.jsp");
            rd.include(request, response);
        }
        else
        {
            RequestDispatcher rd=request.getRequestDispatcher("/office/index.jsp");
            rd.forward(request, response);
        }	
    }
    
    private void registerUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	String fname = request.getParameter("txt_fname");
    	String lname = request.getParameter("txt_lname");
    	String username = request.getParameter("txt_username");
    	int hid = Integer.parseInt(request.getParameter("txt_hid"));
    	String password = request.getParameter("txt_password");
    	
    	
    	UserBean newUser = new UserBean(fname, lname, username, hotelDao.getHotel(hid), password);
        userDao.registerUser(newUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}
