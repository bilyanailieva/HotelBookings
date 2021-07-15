<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="mt" tagdir="/WEB-INF/tags"%>
<mt:layout title="Book Now">
	<jsp:attribute name="content">
		<div class="container">
			<div class="col-md-4 offset-md-4">
				<form action="javascript:" id="findAvailableRoomsForm">
					<legend>Check for room availability:</legend>
					<div class="form-group">
    					<label>Adults:</label>
    					<input type="number" class="form-control" id="adultCount">
 					</div>
  					<div class="form-group">
    					<label>Children:</label>
    					<input type="number" class="form-control" id="childCount">
  					</div>
  					<div class="form-group">
    					<label>Rooms:</label>
    					<input type="number" class="form-control" id="roomCount">
  					</div>
  					<div class="form-group">
  						<label>Day of Arrival:</label>
  						<input type="text" class="form-control" id="arrivalDate" />
  					</div>
  					<div class="form-group">
  						<label>Day of Departure:</label>
  						<input type="text" class="form-control" id="departureDate" />
  					</div>
 	 				<button type="submit" class="btn btn-primary">Look for available rooms</button>
				</form>
			</div>
		</div>
	</jsp:attribute>
</mt:layout>