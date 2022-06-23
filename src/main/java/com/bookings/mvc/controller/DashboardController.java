package com.bookings.mvc.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
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
import com.bookings.mvc.bean.PriceBean;
import com.bookings.mvc.bean.ReservationBean;
import com.bookings.mvc.bean.RoomBean;
import com.bookings.mvc.bean.RoomTypeBean;
import com.bookings.mvc.bean.StatusBean;
import com.bookings.mvc.dao.CustomerDao;
import com.bookings.mvc.dao.HotelDao;
import com.bookings.mvc.dao.PriceDao;
import com.bookings.mvc.dao.ReservationDao;
import com.bookings.mvc.dao.RoomTypeDao;
import com.bookings.mvc.dao.StatusDao;
import com.bookings.mvc.dao.UserDao;

public class DashboardController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private ReservationDao reservationDao;
	private CustomerDao customerDao;
	private HotelDao hotelDao;
	private StatusDao statusDao;
	private UserDao userDao;
	
	public void init() {
    	String url = getServletContext().getInitParameter("url");
		String name = getServletContext().getInitParameter("name");
		String pass = getServletContext().getInitParameter("pass");

		reservationDao = new ReservationDao(url, name, pass);
		hotelDao = new HotelDao(url, name, pass);
		customerDao = new CustomerDao(url, name, pass);
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
            case "/office/confirmation":
            	getCmInformation(request, response);
            	break;
            case "/office/change-status":
            	changeStatus(request, response);
            	break;
            case "/office/view":
            	viewReservationDetails(request, response);
            	break;
            case "/office/reservations-list":
            	listReservations(request, response);
            	break;
            case "/office/update-reservation":
            	updateReservationDetails(request, response);
            	break;
            default:
            	listPendingResRequests(request, response);
            	listReservationsNextTwoDays(request, response);
                break;
            }
            
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

private void listReservationsNextTwoDays(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
	LocalDate today = LocalDate.now();
	LocalDate tomorrow = today.plusDays(1);
	HttpSession session=request.getSession();
	int hid = userDao.GetUserByUsername(session.getAttribute("login").toString()).getHId();
    List<ReservationBean> listConfirmed = reservationDao.listAllConfirmedReservationsToday(today, new HotelBean(hid));
    List<ReservationBean> listConfirmedTomorrow = reservationDao.listAllConfirmedReservationsToday(tomorrow, new HotelBean(hid));
    ArrayList<List<String>> listAssignedToday = new ArrayList<List<String>>();
    ArrayList<List<String>> listAssignedTom = new ArrayList<List<String>>();
	List<StatusBean> statuses = statusDao.listAllStatuses();
	request.setAttribute("statuses", statuses);
    request.setAttribute("listConfirmed", listConfirmed);
    request.setAttribute("listConfirmedTomorrow", listConfirmedTomorrow);
    for (ReservationBean res : listConfirmed) {
    	List<String> listAssigned = reservationDao.listAssignedRooms(res.getRes_id());
    	listAssignedToday.add(listAssigned);
    }
    for (ReservationBean res : listConfirmedTomorrow) {
    	List<String> listAssigned = reservationDao.listAssignedRooms(res.getRes_id());
    	listAssignedTom.add(listAssigned);
    }
    request.setAttribute("listAssignedToday", listAssignedToday);
    request.setAttribute("listAssignedTom", listAssignedTom);
    RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
    dispatcher.forward(request, response);
}

private void listPendingResRequests(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
	HttpSession session=request.getSession();
	int hid = userDao.GetUserByUsername(session.getAttribute("login").toString()).getHId();
    List<ReservationBean> listPending = reservationDao.listAllPendingReservations(new HotelBean(hid));
    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
    request.setAttribute("listPending", listPending);
    dispatcher.include(request, response);
}

private void getCmInformation(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("reservation_id"));
    HttpSession session=request.getSession();
	int hid = userDao.GetUserByUsername(session.getAttribute("login").toString()).getHId();
    ReservationBean res = reservationDao.getAllReservationDetails(id);
    CustomerBean cm = customerDao.getCmIdById(res.getCustomer().getC_id());
    HotelBean hotel = hotelDao.getHotel(hid);
    RequestDispatcher dispatcher = request.getRequestDispatcher("confirmation-popup.jsp");
    request.setAttribute("cm", cm);
    request.setAttribute("res", res);
    request.setAttribute("hotel", hotel);
    dispatcher.include(request, response);
}

//private void getReservationInformation(HttpServletRequest request, HttpServletResponse response)
//        throws SQLException, ServletException, IOException {
//    int id = Integer.parseInt(request.getParameter("c_id"));
//    ReservationBean res = reservationDao.getReservationByCID(id);
//    RequestDispatcher dispatcher = request.getRequestDispatcher("confirmation-popup.jsp");
//    request.setAttribute("res", res);
//    dispatcher.forward(request, response);
//}
//
//private void showChangeStatusModal(HttpServletRequest request, HttpServletResponse response)
//        throws SQLException, ServletException, IOException {
//	int res_id = Integer.parseInt(request.getParameter("res_id"));
//	ReservationBean res = reservationDao.getReservationById(res_id, "status != 1");
//	List<StatusBean> statuses = statusDao.listAllStatuses();
//	RequestDispatcher dispatcher = request.getRequestDispatcher("change-status.jsp");
//	request.setAttribute("statuses", statuses);
//	request.setAttribute("currentStatus", res.getStatus());
//	dispatcher.forward(request, response);
//}

private void changeStatus(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
    int status_id = Integer.parseInt(request.getParameter("currentStatus"));
    int res_id = Integer.parseInt(request.getParameter("res_id"));
    StatusBean stat = new StatusBean(status_id);
    
	RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");

    reservationDao.updateReservationStatus(res_id, stat.getStatus_id());
    dispatcher.forward(request, response);
}

private void viewReservationDetails(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
	int res_id = Integer.parseInt(request.getParameter("res_id"));
	ReservationBean res = reservationDao.getAllReservationDetails(res_id);
	CustomerBean cm = customerDao.getCmIdById(res.getCustomer().getC_id());
	List<StatusBean> statuses = statusDao.listAllStatuses();
	RequestDispatcher dispatcher = request.getRequestDispatcher("single-reservation.jsp");
	request.setAttribute("statuses", statuses);
	request.setAttribute("currentStatus", res.getStatus().getStatus_id());
	request.setAttribute("res", res);
	request.setAttribute("cm", cm);
	dispatcher.forward(request, response);
}

private void listReservations(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
	HttpSession session=request.getSession();
	int hid = userDao.GetUserByUsername(session.getAttribute("login").toString()).getHId();
    List<ReservationBean> reservations = reservationDao.listReservations(new HotelBean(hid));
    ArrayList<List<String>> listAssignedToday = new ArrayList<List<String>>();
	List<StatusBean> statuses = statusDao.listAllStatuses();
	request.setAttribute("statuses", statuses);
    for (ReservationBean res : reservations) {
    	List<String> listAssigned = reservationDao.listAssignedRooms(res.getRes_id());
    	listAssignedToday.add(listAssigned);
    }
    request.setAttribute("listAssignedToday", listAssignedToday);
    request.setAttribute("reservations", reservations);
    RequestDispatcher dispatcher = request.getRequestDispatcher("reservations.jsp");
    dispatcher.forward(request, response);
}

private void updateReservationDetails(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
    int c_id = Integer.parseInt(request.getParameter("c_id"));
    int res_id = Integer.parseInt(request.getParameter("res_id"));
      
    String fname = request.getParameter("fname");
    String lname = request.getParameter("lname");
    String email = request.getParameter("email");
    String phone = request.getParameter("phone");
    CustomerBean cm = new CustomerBean(c_id, fname, lname, email, phone);
    customerDao.updateCustomerInformation(cm);
    
    String arrival = request.getParameter("arrivalDate");
	String departure = request.getParameter("departureDate");
	int rooms_required = Integer.parseInt(request.getParameter("roomCount"));
	int adults = Integer.parseInt(request.getParameter("adultCount"));
	int child = Integer.parseInt(request.getParameter("childCount"));
	Float price = Float.parseFloat(request.getParameter("price"));
    int status_id = Integer.parseInt(request.getParameter("currentStatus"));
    String notes = request.getParameter("notes");
    int hid = Integer.parseInt(request.getParameter("txt_hid"));
    
    ReservationBean res = new ReservationBean(res_id, rooms_required, adults, child, arrival, departure, new StatusBean(status_id), price, new CustomerBean(c_id), notes, new HotelBean(hid));
    reservationDao.updateReservation(res);
    
    response.sendRedirect("reservations-list");
}
}
