<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.bookings.mvc.bean.RoomTypeBean"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>
<html>
<head>
<%@ include file="../Snippets/head.jsp"%>
<title>My Hotel</title>
</head>
<body>
	<%@ include file="../Snippets/header.jsp"%>

	<div class="row">
		<%@ include file="../Snippets/side-menu.jsp"%>
		<main class="col-md-9 ml-sm-auto col-lg-10 px-md-4" role="main">
			<div>
				<h1>My Hotel</h1>
				<p>Manage your hotel from here. See rooms, room types and prices.</p>
				<c:if test="${listHotel.size() < 1}">
				<h2>
					<a href="new-hotel" class="massive ui blue button">Add New Hotel</a> 
					<p>Add the details of the hotel, that you want to be managed by clicking the button above.</p>
					<!-- <a href="list">List All RoomTypes</a> -->
				</h2>
				</c:if>
				<c:if test="${listHotel.size() >= 1}">
					<p>You have already added one hotel. The system currently supports the management of only one hotel.</p>
				</c:if>
			</div>
			<br>
			<br>
			<div class="col-md-6">
				<table class="ui compact celled definition table">
					<thead>
						<tr>
							<th>HID</th>
							<th>Hotel Name</th>
							<th>Number of rooms</th>
							<th>Working season start</th>
							<th>Working season end</th>
							<th>Dogs</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="hotel" items="${listHotel}">
							<tr>
								<td><c:out value="${hotel.hid}" /></td>
								<td><c:out value="${hotel.hotel_name}" /></td>
								<td><c:out value="${hotel.room_count}" /></td>
								<td><c:out value="${hotel.start_season}" /></td>
								<td><c:out value="${hotel.end_season}" /></td>
								<td><c:if test="${hotel.dogs == true }"><i class="fas fa-check"></i></c:if></td>
								<td><a href="edit-hotel?hid=<c:out value='${hotel.hid}' />">Edit</a>
								</td>
								<td><a href="delete-hotel?hid=<c:out value='${hotel.hid}' />">Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot class="full-width">
						<tr>
							<!-- <th></th>
							<th colspan="5">
								<div class="ui right floated small primary labeled icon button">
									<i class="fas fa-user"></i> Add Hotel
								</div>
								<div class="ui small button">Edit</div>
								<div class="ui small button">Delete</div>
							</th> -->
						</tr>
					</tfoot>
				</table>
			</div>
		</main>
	</div>
	<script src="../assets/js/forms.js"></script>
	<%-- <jsp:include page="Snippets/footer.jsp" /> --%>
	<%@ include file="../Snippets/footer.jsp"%>

</body>
</html>