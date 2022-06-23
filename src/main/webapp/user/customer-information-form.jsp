<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BookARoom|Comlete your reservation</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<link rel="stylesheet" href="../assets/css/login.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/@event-calendar/build/event-calendar.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/@event-calendar/build/event-calendar.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="../semantic/semantic.min.css">
<link rel="stylesheet" type="text/css" href="../fontawesome/css/all.css">
<script src="../semantic/semantic.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
<script src="../assets/js/forms.js" type="text/javascript"></script>
</head>
<body>

	<div class="col-md-6 offset-md-3">
		<h1>Please fill out the missing information in order to confirm
			your booking with ID: ${res.res_id} at ${res.hotel.hotel_name }</h1>
		<form action="submit-data">
			<input type="hidden" name="c_id" value='<c:out value="${cm.c_id}" />'>
			<input type="hidden" name="res_id"
				value='<c:out value="${res.res_id}" />'> <input
				type="hidden" name="txt_hid"
				value='<c:out value="${res.hotel.hid}" />'> <input
				type="hidden" name="status_id"
				value='<c:out value="${res.status.status_id}" />'> <input
				type="hidden" name="adultCount" value="${res.adults_count}">
			<input type="hidden" name="childCount" value="${res.child_count}">
			<input type="hidden" name="roomCount" value="${res.number_of_rooms}">
			<input type="hidden" name="arrivalDate" value="${res.res_start}">
			<input type="hidden" name="departureDate" value="${res.res_end}">
			<input type="hidden" name="price" value="${res.price}">

			<div class="form-row">
				<div class="form-group col-md-4">
					<label for="inputEmail4">Adults</label> <input type="number"
						class="form-control" id="inputEmail4" value='${res.adults_count }'
						disabled>
				</div>
				<div class="form-group col-md-4">
					<label for="inputPassword4">Children</label> <input type="number"
						class="form-control" id="inputPassword4"
						value='${res.child_count }' disabled>
				</div>
				<div class="form-group col-md-4">
					<label for="inputPassword4">Required Rooms</label> <input
						type="number" class="form-control" id="inputPassword4"
						value='${res.number_of_rooms }' disabled>
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-4">
					<label for="inputAddress">Arrival Date</label> <input type="date"
						class="form-control" id="inputAddress"
						value='<c:out value="${res.res_start }"></c:out>' disabled>
				</div>
				<div class="form-group col-md-4">
					<label for="inputAddress2">Departure Date</label> <input
						type="date" class="form-control" id="inputAddress2"
						value='<c:out value="${res.res_end }"></c:out>' disabled>
				</div>
				<div class="form-group col-md-4">
					<label for="inputAddress2">Current Status</label> <input
						type="text" class="form-control" id="inputAddress2" name="status"
						value='<c:out value="${res.status.status }"></c:out>' disabled>
				</div>
			</div>
			<h3>Customer Information:</h3>
			<div class="form-row">
				<div class="form-group col-md-4">
					<label for="inputCity">First Name:</label> <input type="text"
						name="fname" class="form-control" id="inputCity"
						value='<c:out value="${cm.fname }"></c:out>'>
				</div>
				<div class="form-group col-md-4">
					<label for="inputCity">Last Name</label> <input type="text"
						name="lname" class="form-control" id="inputCity"
						value='<c:out value="${cm.lname }"></c:out>'>
				</div>
				<div class="form-group col-md-4">
					<label for="inputCity">Contact Email:</label> <input type="email"
						name="email" class="form-control" id="inputCity"
						value='<c:out value="${cm.email }"></c:out>'>
				</div>
				<div class="form-group col-md-4">
					<label for="inputCity">Phone number:</label> <input type="text"
						name="phone" class="form-control" id="inputCity"
						value='<c:out value="${cm.phone }"></c:out>'>
				</div>
			</div>
			<br>
			<div class="form-row">
				<div class="form-group col-md-12">
					<label for="inputCity">Price:</label> <input type="number"
						class="form-control" id="inputCity"
						value='<c:out value="${res.price }"></c:out>' disabled>
				</div>
				<div class="form-group col-md-12">
					<label for="inputCity">Notes:</label> <input type="text"
						name="notes" class="form-control" id="inputCity"
						value='<c:out value="${res.notes }"></c:out>'>
				</div>
			</div>
			<button type="submit" class="btn btn-primary">Confirm
				Booking</button>
			<a href="cancel?res_id=<c:out value='${res.res_id}' />">Cancel Booking</a>	
		</form>
	</div>
</body>
</html>