package com.bookings.mvc.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookings.mvc.bean.HotelBean;
import com.bookings.mvc.bean.PriceBean;
import com.bookings.mvc.bean.RoomTypeBean;
import com.bookings.mvc.dao.PriceDao;
import com.bookings.mvc.dao.RoomTypeDao;
import com.bookings.mvc.dao.UserDao;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @author www.codejava.net
 */
public class PriceController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PriceDao priceDao;
    private RoomTypeDao roomTypeDao;
    private UserDao userDao;
    
    public void init() {
    	String url = getServletContext().getInitParameter("url");
		String name = getServletContext().getInitParameter("name");
		String pass = getServletContext().getInitParameter("pass");

		priceDao = new PriceDao(url, name, pass);
		roomTypeDao = new RoomTypeDao(url, name, pass);
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
            case "/office/roomtypes/newprice":
                showNewPriceForm(request, response);
                break;
            case "/office/roomtypes/insert-price":
                insertPrice(request, response);
                break;
            case "/office/roomtypes/delete-price":
                deletePrice(request, response);
                break;
            case "/office/roomtypes/edit-price":
                showEditPriceForm(request, response);
                break;
            case "/office/roomtypes/update-price":
                updatePrice(request, response);
                break;
            case "/office/roomtypes/view-prices":
            	viewRTPrices(request, response);
            	break;
            default:
            	listPrices(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
 
    private void listPrices(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	HttpSession session=request.getSession();
    	int hid = userDao.GetUserByUsername(session.getAttribute("login").toString()).getHId();
        List<PriceBean> listPrice = priceDao.listAllPrices(hid);
        List<RoomTypeBean> rtlist = roomTypeDao.listAllRoomTypes();
        RequestDispatcher dispatcher = request.getRequestDispatcher("prices.jsp");
        request.setAttribute("listRoomTypes", rtlist);
        request.setAttribute("listPrice", listPrice);
        dispatcher.forward(request, response);
    }
    
    private void viewRTPrices(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	int rtid = Integer.parseInt(request.getParameter("id"));
    	HttpSession session=request.getSession();
    	int hid = userDao.GetUserByUsername(session.getAttribute("login").toString()).getHId();
    	RoomTypeBean rt = roomTypeDao.getRoomType(rtid);
        List<PriceBean> listPrice = priceDao.listAllPrices(hid);
        RequestDispatcher dispatcher = request.getRequestDispatcher("prices.jsp");
        request.setAttribute("rt", rt);
        request.setAttribute("listPrice", listPrice);
        dispatcher.forward(request, response);
    }
 
    private void showNewPriceForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
    	List<RoomTypeBean> rtlist = roomTypeDao.listAllRoomTypes();
        RequestDispatcher dispatcher = request.getRequestDispatcher("price_form.jsp");
        request.setAttribute("listRoomTypes", rtlist);
        dispatcher.forward(request, response);
    }
 
    private void showEditPriceForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("pid"));
        PriceBean existingPrice = priceDao.getPrice(id);
        List<RoomTypeBean> rtlist = roomTypeDao.listAllRoomTypes();
        RequestDispatcher dispatcher = request.getRequestDispatcher("price_form.jsp");
        request.setAttribute("listRoomTypes", rtlist);
        request.setAttribute("price", existingPrice);
        dispatcher.forward(request, response);
 
    }
 
    private void insertPrice(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
    	HttpSession session=request.getSession();
    	int hid = userDao.GetUserByUsername(session.getAttribute("login").toString()).getHId();
    	int rtid = Integer.parseInt(request.getParameter("rt_id"));
        String description = request.getParameter("price_desc");
        Float price = Float.parseFloat(request.getParameter("price"));
        
        PriceBean newPrice = new PriceBean(new RoomTypeBean(rtid), description, price, new HotelBean(hid));
        priceDao.insertPrice(newPrice);
        response.sendRedirect("list-prices");
    }
 
    private void updatePrice(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("pid"));
        int rtid = Integer.parseInt(request.getParameter("rt_id"));
        String description = request.getParameter("price_desc");
        Float price = Float.parseFloat(request.getParameter("price"));
       
        PriceBean priceObj = new PriceBean(id, new RoomTypeBean(rtid), description, price);
        priceDao.updatePrice(priceObj);
        response.sendRedirect("list-prices");
    }
 
    private void deletePrice(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("pid"));
 
        PriceBean price = new PriceBean(id);
        priceDao.deletePrice(price);
        response.sendRedirect("list-prices");
 
    }
}
