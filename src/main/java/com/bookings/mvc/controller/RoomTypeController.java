package com.bookings.mvc.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookings.mvc.bean.RoomTypeBean;
import com.bookings.mvc.dao.RoomTypeDao;

/**
 * Servlet implementation class HotelController
 */
public class RoomTypeController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RoomTypeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void insertRoomType(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("add_type") != null) // check button click event not null from register.jsp page button
		{
			String type = request.getParameter("room_type");

			RoomTypeBean roomTypeBean = new RoomTypeBean(); // this class contain seeting up all received values from
															// register.jsp page to setter and getter method for
															// application require effectively

			roomTypeBean.setType(type);

			RoomTypeDao roomTypeDao = new RoomTypeDao(); // this class contain main logic to perform function calling
															// and database operation

			String insertValidate = roomTypeDao.addRoomType(roomTypeBean); // send registerBean object values into
																			// authorizeRegister() function in
																			// RegisterDao class

			if (insertValidate.equals("SUCCESSFULLY ADDED ROOM TYPE")) // check calling authorizeRegister() function
																		// receive "SUCCESS REGISTER" string message
																		// after redirect to index.jsp page
			{
				request.setAttribute("RegiseterSuccessMsg", insertValidate); // apply register successfully message
																				// "RegiseterSuccessMsg"
				RequestDispatcher rd = request.getRequestDispatcher("/office/welcome.jsp"); // redirect to index.jsp
																							// page
				rd.forward(request, response);
			} else {
				request.setAttribute("RegisterErrorMsg", insertValidate); // apply register error message
																			// "RegiseterErrorMsg"
				RequestDispatcher rd = request.getRequestDispatcher("/office/settings.jsp"); // show error same page
																								// register.jsp page
				rd.include(request, response);
			}
		}
	}

	protected void checkIfRoomTypeExists(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getParameter("room_type") != null) {
		String room_type = request.getParameter("room_type");

		RoomTypeBean roomTypeBean = new RoomTypeBean(); // this class contain seeting up all received values from
														// register.jsp page to setter and getter method for application
														// require effectively

		roomTypeBean.setType(room_type);

		RoomTypeDao roomTypeDao = new RoomTypeDao();

		String selectValidate = roomTypeDao.checkIfExists(roomTypeBean); // send registerBean object values into
																			// authorizeRegister() function in
																			// RegisterDao class

		if (selectValidate.equals("exists")) {
			String message = "Room Type already exists!";

			response.setContentType("text/plain");
			response.getWriter().write(message);
		}
	}
	}
	
	public void seeAllRoomTypes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getParameter("all_room_types") != null) 
		{
			RoomTypeDao roomTypeDao = new RoomTypeDao();
	        List<RoomTypeBean> roomTypesList = roomTypeDao.getRoomTypes();
	        
	        request.setAttribute("roomTypesList", roomTypesList);
	        
	        RequestDispatcher rd = request.getRequestDispatcher("/office/settings.jsp");
			rd.forward(request, response);
		}
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	checkIfRoomTypeExists(request, response);
    	//seeAllRoomTypes(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	insertRoomType(request, response);
    
	}
}
