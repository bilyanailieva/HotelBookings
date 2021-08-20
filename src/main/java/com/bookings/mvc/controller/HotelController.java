package com.bookings.mvc.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookings.mvc.bean.HotelBean;
import com.bookings.mvc.bean.RoomBean;
import com.bookings.mvc.bean.RoomTypeBean;
import com.bookings.mvc.dao.HotelDao;
import com.bookings.mvc.dao.RoomDao;
import com.bookings.mvc.dao.RoomTypeDao;
 
/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @author www.codejava.net
 */
public class HotelController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private HotelDao hotelDao;
    private RoomTypeDao roomTypeDao;
 
    public void init() {
    	String url = getServletContext().getInitParameter("url");
		String name = getServletContext().getInitParameter("name");
		String pass = getServletContext().getInitParameter("pass");

		hotelDao = new HotelDao(url, name, pass);
		roomTypeDao = new RoomTypeDao(url, name, pass);
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
 
        try {
            switch (action) {
            case "/office/hotelsettings/new-hotel":
            	//listRoomTypes(request, response);
                showNewHotelForm(request, response);
                break;
            case "/office/hotelsettings/insert-hotel":
                insertHotel(request, response);
                break;
            case "/office/hotelsettings/delete-hotel":
                deleteHotel(request, response);
                break;
            case "/office/hotelsettings/edit-hotel":
                //listRoomTypes(request, response);
                showEditHotelForm(request, response);
                break;
            case "/office/hotelsettings/update-hotel":
                updateHotel(request, response);
                break;
            default:
            	//listRoomTypes(request, response);
            	listHotels(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
 
    private void listHotels(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<HotelBean> listHotel = hotelDao.listAllHotels();
        request.setAttribute("listHotel", listHotel);
        RequestDispatcher dispatcher = request.getRequestDispatcher("settings.jsp");
        dispatcher.forward(request, response);
        
    }
 
    private void showNewHotelForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("hotel_form.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showEditHotelForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("hid"));
        HotelBean existingHotel = hotelDao.getHotel(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("hotel_form.jsp");
        request.setAttribute("hotel", existingHotel);
        dispatcher.forward(request, response);
 
    }
 
    private void insertHotel(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
    	String hotel_name = request.getParameter("hotel_name");
    	int rc = Integer.parseInt(request.getParameter("room_count"));
    	String start_season = request.getParameter("start_season");
    	String end_season = request.getParameter("end_season");
        Boolean dogs = false;
        if (request.getParameter("dogs") != null) {
        	dogs = true;
        }
        HotelBean newHotel = new HotelBean(hotel_name, rc, start_season, end_season, dogs);
        hotelDao.insertHotel(newHotel);
        response.sendRedirect("list-hotel");
    }
 
    private void updateHotel(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int hid = Integer.parseInt(request.getParameter("hid"));
        String hotel_name = request.getParameter("hotel_name");
        int rc = Integer.parseInt(request.getParameter("room_count"));
        String start_season = request.getParameter("start_season");
        String end_season = request.getParameter("end_season");
        Boolean dogs = false;
        
        if (request.getParameter("dogs") != null) {
        	dogs = true;
        }
        HotelBean hotel = new HotelBean(hid, hotel_name, rc, start_season, end_season, dogs);
        hotelDao.updateHotel(hotel);
        response.sendRedirect("list-hotel");
    }
 
    private void deleteHotel(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int hid = Integer.parseInt(request.getParameter("hid"));
 
        HotelBean hotel = new HotelBean(hid);
        hotelDao.deleteHotel(hotel);
        response.sendRedirect("list-hotel");
 
    }
    
	/*
	 * private void listRoomTypes(HttpServletRequest request, HttpServletResponse
	 * response) throws SQLException, IOException, ServletException {
	 * List<RoomTypeBean> listRoomType = roomTypeDao.listAllRoomTypes();
	 * request.setAttribute("listRoomTypes", listRoomType); RequestDispatcher
	 * dispatcher = request.getRequestDispatcher("room_form.jsp");
	 * dispatcher.include(request, response); }
	 */
}
