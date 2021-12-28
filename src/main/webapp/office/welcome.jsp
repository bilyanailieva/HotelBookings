<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@page import="com.bookings.mvc.bean.RoomTypeBean"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   
<!doctype html>
<html>
<head>
	<%@ include file="Snippets/head.jsp" %>  
	<title>Welcome</title>
	<link rel="stylesheet" href="/HotelBookings/assets/css/base/index.scss" />
	<link  rel='stylesheet'  href='https://cdn.jsdelivr.net/npm/gantt-schedule-timeline-calendar@3.7.1/dist/style.css'>

</head>
<body>
<%@ include file="Snippets/header.jsp" %> 
<div class="row">
<%@ include file="Snippets/side-menu.jsp" %> 
<main class="col-md-9 ml-sm-auto col-lg-10 px-md-4" role="main">
<h2>
    <% 
    	if (session.getAttribute("login") == null || session.getAttribute("login") == "")
    		{//check if condition for unauthorize user not direct access welcome.jsp page
    			response.sendRedirect("./index.jsp");
    		}
    %>

    
    Welcome, <%=session.getAttribute("login")%> 
    
    Your hotel is <%=session.getAttribute("hotel")%> 
</h2>

<%-- <input type="hidden" value="<%=session.getAttribute("currentDate")%>" name="currentDate" id="currentDate"  /> --%>

<div class="col-md-6">
	<table class="ui compact celled definition table">
					<thead>
						<tr>
							<th></th>
							<th>ID</th>
							<th>Assigned Rooms</th>
							<th>Customer Name</th>
							<th>Status</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="reservation" items="${listConfirmed }">
							<tr>
								<td class="collapsing">
									<!-- <div class="ui fitted slider checkbox">
										<input type="checkbox"> <label></label>
									</div> -->
								</td>
								<td><c:out value="${reservation.res_id}" /></td>
								<td><c:out value="${reservation.res_start}" /></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td><a href="edit?id=<c:out value='${reservation.res_id}' />">View Extra Information</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot class="full-width">
						<tr>
							<th></th>
							<th colspan="9">
								<div class="ui right floated small primary labeled icon button">
									<i class="fas fa-user"></i> Add Room
								</div>
								<div class="ui small button">Edit</div>
								<div class="ui small button">Delete</div>
							</th>
						</tr>
					</tfoot>
				</table>
</div>

<input type="hidden" id="rooms" name="rooms" data-textxml="${listRoom }"/>
		<div id="calendar"></div>
 

<h3>
    <a class="btn btn-primary" href="HotelSettings/office/logout.jsp">Logout</a>
</h3>


<br>
		<br>
		<br>
		<br>
		<br>
		<br>
    <h2>
				<a
					href="https://onlyxscript.blogspot.in/2018/02/java-mvc-login-and-register-script.html">Tutorial Link</a>
			</h2>

</main>
</div>	
<%@ include file="Snippets/footer.jsp" %>  
<script src="https://cdn.jsdelivr.net/npm/gantt-schedule-timeline-calendar"></script>
</body>
</html>
