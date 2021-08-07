<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
    <%@page import="com.bookings.mvc.bean.RoomTypeBean"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<%-- <%@ include file="Snippets/header.jsp" %> --%>
<!doctype html>
<html>
<head>
	<%@ include file="Snippets/head.jsp" %>  
	<title>Settings</title>
</head>
<body>
<%@ include file="Snippets/header.jsp" %> 

<div class="row">
	<%@ include file="Snippets/side-menu.jsp" %> 
	<main class="col-md-9 ml-sm-auto col-lg-10 px-md-4" role="main">

		<div class="hotel-common">
			<form action="hotelsettings" method="post" name="HotelForm" accept-charset=utf-8 onsubmit="return validateForm()">
				<div class="form-group">
					<label>Hotel Name:</label> <input
						type="text" class="form-control"
						aria-describedby="emailHelp" name="hotel_name"> <small id="emailHelp"
						class="form-text text-muted">Add the name of you hotel here.</small>
				</div>
				<div class="form-group">
					<label>How many rooms does the hotel have?</label> <input name="room_count"
						type="number" class="form-control">
				</div>
				<div class="form-group">
					<label>Working Season Start</label><input name="season_start" type="date" class="form-control">
				</div>
				<div class="form-group">
					<label>Working Season End</label><input name="season_end" type="date" class="form-control">
				</div>
				<div class="form-group form-check">
					<input type="checkbox" class="form-check-input" name="dogs">
					<label class="form-check-label">Dogs are allowed</label>
				</div>
				<button type="submit" name="add_hotel"class="btn btn-primary">Submit</button>
			</form>
		</div>
		
		<div class="room-types">
			<form action="roomtypes" method="post" name="RoomTypeForm" accept-charset=utf-8>
			<h1>Add Room Type</h1>
			<div id="insertMessage"></div>
				<div class="form-group">
					<label>RoomType:</label> <input
						type="text" class="form-control"
						 name="room_type" id="room_type">
				</div>
				<button type="submit" name="add_type" id="add_room_type" class="btn btn-primary">Submit</button>
			</form>
		</div>
		<br>
		<button type="button" id="all_room_types" name="all_room_types" class="btn btn-primary">Show all</button>
		
		<div class="room-types-list">
			<h3>Product List</h3>
			<c:set var="total" value="0"></c:set>
			<table border="1" cellpadding="2" cellspacing="2" class="table table-hover">
		<tr>
			<th>Id</th>
			<th>RoomType</th>
		</tr>
		<c:forEach var="room_type" items="${roomTypesList }">
			<tr>
				<td>${room_type.id }</td>
				<td>${room_type.type }</td>
			</tr>
		</c:forEach>
	</table>
		</div>

	</main>
</div>
<script src="../assets/js/forms.js"></script>
<%-- <jsp:include page="Snippets/footer.jsp" /> --%>
<%@ include file="Snippets/footer.jsp" %>  

</body>
</html>
