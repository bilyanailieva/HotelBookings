<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.bookings.mvc.bean.RoomTypeBean"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<%@ include file="../Snippets/head.jsp"%>
<title>Rooms</title>
</head>
<body>
	<%@ include file="../Snippets/header.jsp"%>

	<div class="row">
		<%@ include file="../Snippets/side-menu.jsp"%>
		<main class="col-md-9 ml-sm-auto col-lg-10 px-md-4" role="main">
			<div>
				<h1>Rooms</h1>
				<p>Manage the hotel's rooms from here.</p>
				<c:set var="hotel" value="${listHotel[0] }"></c:set>
				<c:if test="${hotel.room_count <= listRoom.size() }">
					<h3>
						You have added all
						<c:out value="${hotel.room_count }"></c:out>
						of your hotel. <a
							href="/HotelBookings/office/hotelsettings/edit-hotel?hid="
							<c:out value="${hid }"></c:out>> Increase room number </a> in
						order to add more rooms.
					</h3>
				</c:if>
				<c:if test="${hotel.room_count > listRoom.size() }">
					<h2>
						<a href="new" class="massive ui blue button">Add New Room</a>
					</h2>
				</c:if>
			</div>
			<br> <br>
			<div class="col-md-8">
				<table class="ui compact celled definition table">
					<thead>
						<tr>
							<th></th>
							<th>ID</th>
							<th>Room Type</th>
							<th>Name</th>
							<th>First Floor</th>
							<th>Extra Bed</th>
							<th>Baby Crib</th>
							<th>Is Handycap?</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="room" items="${listRoom}">
							<tr>
								<td class="collapsing">
									<!-- <div class="ui fitted slider checkbox">
										<input type="checkbox"> <label></label>
									</div> -->
								</td>
								<td><c:out value="${room.id}" /></td>
								<c:forEach var="room_type" items="${listRoomTypes}">
									<c:if test="${room_type.id == room.rt.id}">
										<td><c:out value='${room_type.type}' /></td>
									</c:if>
								</c:forEach>
								<td><c:out value="${room.name}" /></td>
								<td><c:if test="${room.first_floor == true }">
										<i class="fas fa-check"></i>
									</c:if></td>
								<td><c:if test="${room.extra_bed == true }">
										<i class="fas fa-check"></i>
									</c:if></td>
								<td><c:if test="${room.baby_crib == true }">
										<i class="fas fa-check"></i>
									</c:if></td>
								<td><c:if test="${room.handycap == true }">
										<i class="fas fa-check"></i>
									</c:if></td>
								<td><a href="edit?id=<c:out value='${room.id}' />">Edit</a>
								</td>
								<td><a href="delete?id=<c:out value='${room.id}' />">Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot class="full-width">
						<tr>
							<th></th>
							<th colspan="9">
								<div class="ui right floated small primary labeled icon button">Add
									Room</div>
							</th>
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