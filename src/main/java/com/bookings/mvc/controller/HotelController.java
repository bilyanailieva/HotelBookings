package com.bookings.mvc.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookings.mvc.bean.HotelBean;
import com.bookings.mvc.dao.HotelDao;

/**
 * Servlet implementation class HotelController
 */
public class HotelController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        
        if(request.getParameter("add_hotel")!=null) //check button click event not null from register.jsp page button
        {
            String hotel_name=request.getParameter("hotel_name");
            String room_count=request.getParameter("room_count");
            String season_start=request.getParameter("season_start");  //get all textbox name from register.jsp page
            String season_end=request.getParameter("season_end");
            String dogs=request.getParameter("dogs");
            
            HotelBean hotelBean=new HotelBean(); //this class contain  seeting up all received values from register.jsp page to setter and getter method for application require effectively
            
            hotelBean.setHotel_name(hotel_name);
            hotelBean.setRoom_count(Integer.parseInt(room_count));
			/*
			 * DateFormat format = new SimpleDateFormat("MM-dd-yyyy"); Date finalStartDate =
			 * null; try { finalStartDate = format.parse(season_start);
			 * hotelBean.setStart_season(finalStartDate);
			 * System.out.println(finalStartDate); } catch (ParseException ex) {
			 * System.out.println(ex.getMessage()); } Date finalEndDate = null; try {
			 * finalEndDate = format.parse(season_end);
			 * hotelBean.setEnd_season(finalEndDate); System.out.println(finalEndDate); }
			 * catch (ParseException ex) { System.out.println(ex.getMessage()); }
			 */
            hotelBean.setStart_season(season_start);
            hotelBean.setEnd_season(season_end);
            hotelBean.setDogs(Boolean.parseBoolean(dogs));
           
            HotelDao hoteldao=new HotelDao(); //this class contain main logic to perform function calling and database operation
            
            String registerValidate=hoteldao.authorizeHotelRegister(hotelBean); //send registerBean object values into authorizeRegister() function in RegisterDao class
            
            if(registerValidate.equals("SUCCESS HOTEL REGISTER")) //check calling authorizeRegister() function receive "SUCCESS REGISTER" string message after redirect to index.jsp page
            {
                request.setAttribute("RegiseterSuccessMsg",registerValidate); //apply register successfully message "RegiseterSuccessMsg"
                RequestDispatcher rd=request.getRequestDispatcher("/office/settings.jsp"); //redirect to index.jsp page
                rd.forward(request, response);
            }
            else
            {
                request.setAttribute("RegisterErrorMsg",registerValidate); // apply register error message "RegiseterErrorMsg"
                RequestDispatcher rd=request.getRequestDispatcher("/office/settings.jsp"); //show error same page register.jsp page
                rd.include(request, response);
            }
        }
    }
}
