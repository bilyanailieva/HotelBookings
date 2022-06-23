package com.bookings.mvc.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookings.mvc.bean.CustomerBean;
import com.bookings.mvc.bean.HotelBean;
import com.bookings.mvc.bean.ReservationBean;
import com.bookings.mvc.bean.RoomBean;
import com.bookings.mvc.bean.RoomTypeBean;
import com.bookings.mvc.bean.StatusBean;
import com.bookings.mvc.dao.HotelDao;
import com.bookings.mvc.dao.ReservationDao;
import com.bookings.mvc.dao.RoomDao;
import com.bookings.mvc.dao.RoomTypeDao;
import com.bookings.mvc.dao.StatusDao;
import com.bookings.mvc.dao.UserDao;

public class CalendarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private RoomDao roomDao;
    private RoomTypeDao roomTypeDao;
    private HotelDao hotelDao;
    private ReservationDao reservationDao;
    private StatusDao statusDao;
    private UserDao userDao;
 
    public void init() {
    	String url = getServletContext().getInitParameter("url");
		String name = getServletContext().getInitParameter("name");
		String pass = getServletContext().getInitParameter("pass");

		roomDao = new RoomDao(url, name, pass);
		roomTypeDao = new RoomTypeDao(url, name, pass);
		hotelDao = new HotelDao(url, name, pass);
		reservationDao = new ReservationDao(url, name, pass);
		statusDao = new StatusDao(url, name, pass);
		userDao = new UserDao(url, name, pass);
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
            case "/office/assign-form":
            	showRoomAssignmentForm(request, response);
            case "/office/assign":
            	assignRoom(request, response);
            default:
            	sendInformationToCalendar(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    private void sendInformationToCalendar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	HttpSession session=request.getSession();
    	int hid = userDao.GetUserByUsername(session.getAttribute("login").toString()).getHId();
    	List<RoomBean> listRoom = roomDao.listAllRooms(hid);
        List<RoomTypeBean> listRoomType = roomTypeDao.listAllRoomTypes();
        List<HotelBean> listHotel = hotelDao.listAllHotels();
        List<ReservationBean> listUnassigned = reservationDao.listReservationsNoAssignedRooms(new HotelBean(hid));
        List<ReservationBean> listConfirmed = reservationDao.listAllAssignedReservations(hid);
        ArrayList<List<String>> listRoomsByRes = new ArrayList<List<String>>();
    	List<StatusBean> statuses = statusDao.listAllStatuses();
    	request.setAttribute("statuses", statuses);
        request.setAttribute("listRoomTypes", listRoomType);
        request.setAttribute("listRoom", listRoom);
        request.setAttribute("listHotel", listHotel);
        request.setAttribute("listUnassigned", listUnassigned);
        for (ReservationBean res : listUnassigned) {
        	List<String> listAssigned = reservationDao.listAssignedRooms(res.getRes_id());
        	listRoomsByRes.add(listAssigned);
        }
        request.setAttribute("listAssigned", listRoomsByRes);
        request.setAttribute("listConfirmed", listConfirmed);
        RequestDispatcher dispatcher = request.getRequestDispatcher("calendar.jsp");
        dispatcher.include(request, response);
    }
    
    private void showRoomAssignmentForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("res_id"));
        HttpSession session=request.getSession();
    	int hid = userDao.GetUserByUsername(session.getAttribute("login").toString()).getHId();
        ReservationBean res_id = reservationDao.getReservationById(id, "status != 1");
        List<RoomBean> listRoom = roomDao.listAllRooms(hid);
        RequestDispatcher dispatcher = request.getRequestDispatcher("assign-rooms.jsp");
        request.setAttribute("res", res_id);
        request.setAttribute("listRoom", listRoom);
        dispatcher.include(request, response);
    }
    
    private void assignRoom(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int res_id = Integer.parseInt(request.getParameter("res"));
        int room_id = Integer.parseInt(request.getParameter("room_id"));
        
        reservationDao.assignRooms(res_id, room_id);
        response.sendRedirect("calendar");
    }
    

}
