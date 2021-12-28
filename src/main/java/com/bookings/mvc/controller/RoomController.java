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
public class RoomController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RoomDao roomDao;
    private RoomTypeDao roomTypeDao;
    private HotelDao hotelDao;
 
    public void init() {
    	String url = getServletContext().getInitParameter("url");
		String name = getServletContext().getInitParameter("name");
		String pass = getServletContext().getInitParameter("pass");

		roomDao = new RoomDao(url, name, pass);
		roomTypeDao = new RoomTypeDao(url, name, pass);
		hotelDao = new HotelDao(url, name, pass);
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
            case "/office/rooms/new":
                showNewForm(request, response);
                break;
            case "/office/rooms/insert":
                insertRoom(request, response);
                break;
            case "/office/rooms/delete":
                deleteRoom(request, response);
                break;
            case "/office/rooms/edit":
                showEditForm(request, response);
                break;
            case "/office/rooms/update":
                updateRoom(request, response);
                break;
            case "/office/calendar":
            	sendRoomsToCalendar(request, response);
                break;
            default:
            	listRooms(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
 
    private void listRooms(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<RoomBean> listRoom = roomDao.listAllRooms();
        List<RoomTypeBean> listRoomType = roomTypeDao.listAllRoomTypes();
        List<HotelBean> listHotel = hotelDao.listAllHotels();
        request.setAttribute("listRoomTypes", listRoomType);
        request.setAttribute("listRoom", listRoom);
        request.setAttribute("listHotel", listHotel);
        RequestDispatcher dispatcher = request.getRequestDispatcher("rooms_settings.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
    	List<RoomTypeBean> listRoomType = roomTypeDao.listAllRoomTypes();
        request.setAttribute("listRoomTypes", listRoomType);
        RequestDispatcher dispatcher = request.getRequestDispatcher("room_form.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
    	List<RoomTypeBean> listRoomType = roomTypeDao.listAllRoomTypes();
        request.setAttribute("listRoomTypes", listRoomType);
        int id = Integer.parseInt(request.getParameter("id"));
        RoomBean existingRoom = roomDao.getRoom(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("room_form.jsp");
        request.setAttribute("room", existingRoom);
        dispatcher.forward(request, response);
 
    }
 
    private void insertRoom(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
    	int rtid = Integer.parseInt(request.getParameter("rtid"));
        String name = request.getParameter("name");
        Boolean first_floor = false, extra_bed = false, baby_crib = false, is_handycap = false;
        if (request.getParameter("first_floor") != null) {
        	first_floor = true;
        }
        if(request.getParameter("extra_bed") != null) {
        	extra_bed = true;
        }
        if(request.getParameter("baby_crib") != null) {
        	baby_crib = true;
        }
        if(request.getParameter("is_handycap") != null) {
        	is_handycap = true;
        }
        RoomBean newRoom = new RoomBean(rtid, name, first_floor, extra_bed, baby_crib, is_handycap);
        roomDao.insertRoom(newRoom);
        response.sendRedirect("list");
    }
 
    private void updateRoom(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int rtid = Integer.parseInt(request.getParameter("rtid"));
        Boolean first_floor = false;
        Boolean extra_bed = false;
        Boolean baby_crib = false;
        Boolean is_handycap = false;
        String name = request.getParameter("name");
        if (request.getParameter("first_floor") != null) {
        	first_floor = true;
        }
        if(request.getParameter("extra_bed") != null) {
        	extra_bed = true;
        }
        if(request.getParameter("baby_crib") != null) {
        	baby_crib = true;
        }
        if(request.getParameter("is_handycap") != null) {
        	is_handycap = true;
        }
        RoomBean room = new RoomBean(id, rtid, name, first_floor, extra_bed, baby_crib, is_handycap);
        roomDao.updateRoom(room);
        response.sendRedirect("list");
    }
 
    private void deleteRoom(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        RoomBean room = new RoomBean(id);
        roomDao.deleteRoom(room);
        response.sendRedirect("list");
 
    }
    
    private void listRoomTypes(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<RoomTypeBean> listRoomType = roomTypeDao.listAllRoomTypes();
        request.setAttribute("listRoomTypes", listRoomType);
        RequestDispatcher dispatcher = request.getRequestDispatcher("room_form.jsp");
        dispatcher.include(request, response);
    }
    
    private void sendRoomsToCalendar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	List<RoomBean> listRoom = roomDao.listAllRooms();
        List<RoomTypeBean> listRoomType = roomTypeDao.listAllRoomTypes();
        List<HotelBean> listHotel = hotelDao.listAllHotels();
        request.setAttribute("listRoomTypes", listRoomType);
        request.setAttribute("listRoom", listRoom);
        request.setAttribute("listHotel", listHotel);
        RequestDispatcher dispatcher = request.getRequestDispatcher("./welcome.jsp");
        dispatcher.forward(request, response);
    }
}
