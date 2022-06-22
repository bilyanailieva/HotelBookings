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

import com.bookings.mvc.bean.RoomTypeBean;
import com.bookings.mvc.dao.RoomTypeDao;
import com.bookings.mvc.dao.UserDao;
 
/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @author www.codejava.net
 */
public class RoomTypeController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RoomTypeDao roomTypeDao;
    private UserDao userDao;
 
    public void init() {
    	String url = getServletContext().getInitParameter("url");
		String name = getServletContext().getInitParameter("name");
		String pass = getServletContext().getInitParameter("pass");

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
            case "/office/roomtypes/new":
                showNewForm(request, response);
                break;
            case "/office/roomtypes/insert":
                insertRoomType(request, response);
                break;
            case "/office/roomtypes/delete":
                deleteRoomType(request, response);
                break;
            case "/office/roomtypes/edit":
                showEditForm(request, response);
                break;
            case "/office/roomtypes/update":
                updateRoomType(request, response);
                break;
            default:
            	listRoomTypes(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
 
    protected void listRoomTypes(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<RoomTypeBean> listRoomType = roomTypeDao.listAllRoomTypes();
        request.setAttribute("listRoomTypes", listRoomType);
        RequestDispatcher dispatcher = request.getRequestDispatcher("room_types_settings.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("room_type_update.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        RoomTypeBean existingRoomType = roomTypeDao.getRoomType(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("room_type_update.jsp");
        request.setAttribute("room_type", existingRoomType);
        dispatcher.forward(request, response);
 
    }
 
    private void insertRoomType(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String room_type = request.getParameter("room_type");
        int adult_count = Integer.parseInt(request.getParameter("adult_count"));
        int child_count = Integer.parseInt(request.getParameter("child_count"));
 
        RoomTypeBean newRoomType = new RoomTypeBean(room_type, adult_count, child_count);
        roomTypeDao.insertRoomType(newRoomType);
        response.sendRedirect("list");
    }
 
    private void updateRoomType(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String room_type = request.getParameter("room_type");
        int adult_count = Integer.parseInt(request.getParameter("adult_count"));
        int child_count = Integer.parseInt(request.getParameter("child_count"));
 
        RoomTypeBean roomType = new RoomTypeBean(id, room_type, adult_count, child_count);
        roomTypeDao.updateRoomType(roomType);
        response.sendRedirect("list");
    }
 
    private void deleteRoomType(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        RoomTypeBean roomType = new RoomTypeBean(id);
        roomTypeDao.deleteRoomType(roomType);
        response.sendRedirect("list");
 
    }
}