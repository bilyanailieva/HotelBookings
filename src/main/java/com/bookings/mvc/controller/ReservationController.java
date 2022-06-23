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

public class ReservationController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private ReservationDao reservationDao;
	private CustomerDao customerDao;
	private HotelDao hotelDao;
	private PriceDao priceDao;
	private RoomTypeDao roomTypeDao;
	
	public void init() {
    	String url = getServletContext().getInitParameter("url");
		String name = getServletContext().getInitParameter("name");
		String pass = getServletContext().getInitParameter("pass");

		reservationDao = new ReservationDao(url, name, pass);
		hotelDao = new HotelDao(url, name, pass);
		customerDao = new CustomerDao(url, name, pass);
		priceDao = new PriceDao(url, name, pass);
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
            case "/user/request":
            	sendReservationRequest(request, response);
            	break;
            case "/user/reservation-confirmation":
            	showCustomerInformationForm(request, response);
            	break;
            case "/user/submit-data":
            	makeReservationOfficial(request, response);
            	break;
            case "/user/edit-request":
            	showReservationRequestForm(request, response);
            	break;
            case "/user/cancel":
            	cancelReservationRequest(request,response);
            	break;
            default:
            	listHotels(request, response);
            	if(request.getParameter("arrivalDate") != null && request.getParameter("departureDate") != null) {
            	listAvailability(request, response);
            	}
            	//sendHidToBookingForm(request, response);
                break;
            }
            
        } catch (SQLException | ParseException ex) {
            throw new ServletException(ex);
        }
    }

private void sendReservationRequest(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
	int c_id;
	int adult_count = Integer.parseInt(request.getParameter("adultCount"));
	int child_count = Integer.parseInt(request.getParameter("childCount"));
	if (request.getParameter("childCount") == null) {
		child_count = 0;
	}
	int number_of_rooms = Integer.parseInt(request.getParameter("roomCount"));
    String start_date = request.getParameter("arrivalDate");
    String end_date = request.getParameter("departureDate");
    int status = 1;
    String email = request.getParameter("email");
    String[] price = request.getParameterValues("priceOption");
    List<PriceBean> selectedPrices = new ArrayList<>();
    for(int i = 0; i < price.length; i++) {
    	PriceBean pr = priceDao.getPrice(Integer.parseInt(price[i]));
    	selectedPrices.add(pr);
    }
    int hid = Integer.parseInt(request.getParameter("txt_hid"));
    
    Float calculated = (float) 0;
    int toBeAccomm = adult_count + child_count;
    if(price.length == number_of_rooms) {
    	for(int i = 0; i < price.length; i++) {
    		PriceBean pr = priceDao.getPrice(Integer.parseInt(price[i]));
    			calculated = calculated + pr.getPrice();
    		}
    	}
    else {
    	int nr = number_of_rooms;
    	while(nr > 0) {
    		for(PriceBean p : selectedPrices) {
    			RoomTypeBean rt = roomTypeDao.getRoomType(p.getRt().getId());
        		int people = rt.getAdults() + rt.getChildren();
        		if (people <= toBeAccomm) {
    				calculated = calculated + p.getPrice();
    				toBeAccomm = toBeAccomm - people;
    				nr--;
    			}
    		}
    	}
    }

    c_id = customerDao.getCmIdByEmail(email);
    if (c_id == 0) {
    	CustomerBean cm = new CustomerBean(email);
    	customerDao.newCmFromResReqest(cm);
    	c_id = customerDao.getCmIdByEmail(email);
    }
    ReservationBean newReservationRequest = new ReservationBean(number_of_rooms, adult_count, child_count, start_date, end_date, new StatusBean(status), calculated, new CustomerBean(email, c_id), new HotelBean(hid));
    
    reservationDao.sendReservationRequest(newReservationRequest);
    response.sendRedirect("success.jsp");
    
}

private void listHotels(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
	List<HotelBean> listHotels = hotelDao.listAllHotels();
    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
    request.setAttribute("listHotels", listHotels);
    dispatcher.include(request, response);
}

private void listAvailability(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException, ParseException {
	String arrival = request.getParameter("arrivalDate");
	String departure = request.getParameter("departureDate");
	String rooms_required = request.getParameter("roomCount");
	String adults = request.getParameter("adultCount");
	String child = request.getParameter("childCount");
	String hid = request.getParameter("txt_hid");
	int total_rooms = hotelDao.getHotelRoomCount(Integer.parseInt(hid));
	List<Integer> availability = reservationDao.returnRoomsTaken(arrival, departure, total_rooms, new HotelBean(Integer.parseInt(hid)));
	List<PriceBean> possiblePrices = priceDao.listPossiblePrices(Integer.parseInt(adults), Integer.parseInt(child), total_rooms, availability.size(), Integer.parseInt(hid));
	HotelBean hotel = hotelDao.getHotel(Integer.parseInt(hid));
	request.setAttribute("h", hotel);
	request.setAttribute("adultCount", adults);
	request.setAttribute("childCount", child);
	request.setAttribute("roomCount", rooms_required);
	request.setAttribute("arrivalDate", arrival);
	request.setAttribute("total_rooms", total_rooms);
	request.setAttribute("departureDate", departure);
    request.setAttribute("availability", availability);
    request.setAttribute("prices", possiblePrices);
	RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
    dispatcher.forward(request, response);
}

private void showCustomerInformationForm(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("c_id"));
    int res_id = Integer.parseInt(request.getParameter("res_id"));
    ReservationBean res = reservationDao.getAllReservationDetails(res_id);
    CustomerBean cm = customerDao.getCmIdById(id);
    RequestDispatcher dispatcher = request.getRequestDispatcher("customer-information-form.jsp");
    request.setAttribute("res", res);
    request.setAttribute("cm", cm);
    dispatcher.include(request, response);
}

private void showReservationRequestForm(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, ServletException, IOException {
	String arrival = request.getParameter("arrivalDate");
	String departure = request.getParameter("departureDate");
	int rooms_required = Integer.parseInt(request.getParameter("roomCount"));
	int adults = Integer.parseInt(request.getParameter("adultCount"));
	int child = Integer.parseInt(request.getParameter("childCount"));
	RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	request.setAttribute("adultCount", adults);
	request.setAttribute("childCount", child);
	request.setAttribute("roomCount", rooms_required);
	request.setAttribute("arrivalDate", arrival);
	request.setAttribute("departureDate", departure);
	dispatcher.include(request, response);
}

private void makeReservationOfficial(HttpServletRequest request, HttpServletResponse response)
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
    String notes = request.getParameter("notes");
    int hid = Integer.parseInt(request.getParameter("txt_hid"));
    
    ReservationBean res = new ReservationBean(res_id, rooms_required, adults, child, arrival, departure, new StatusBean(2), price, new CustomerBean(c_id), notes, new HotelBean(hid));
    reservationDao.updateReservation(res);
    
    //reservationDao.updateReservationStatus(res_id, 2);
    response.sendRedirect("success.jsp");
}

private void cancelReservationRequest(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
	int id = Integer.parseInt(request.getParameter("res_id"));
	 
    ReservationBean res = new ReservationBean(id);
    reservationDao.updateReservationStatus(id, 7);
    response.sendRedirect("/HotelBookings/user");
}


}
