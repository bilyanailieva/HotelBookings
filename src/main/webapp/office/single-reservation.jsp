<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.bookings.mvc.bean.RoomTypeBean"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="Snippets/head.jsp"%>
<title>Reservation Details: Res Nr.: <c:out
		value='${res.res_id}' />
</title>

</head>
<body>
	<%@ include file="Snippets/header.jsp"%>
	<div class="row">
		<%@ include file="Snippets/side-menu.jsp"%>
		<main class="col-md-9 ml-sm-auto col-lg-10 px-md-4" role="main">
			<form action="update-reservation">
			<input type="hidden" name="res_id" value='<c:out value="${res.res_id }"></c:out>'>
			<input type="hidden" name="c_id" value='<c:out value="${res.customer.c_id }"></c:out>'>
			<input type="hidden" name="txt_hid" value='<c:out value="${res.hotel.hid }"></c:out>'>
				<h2>
					Reservation Nr.:
					<c:out value='${res.res_id}' />
				</h2>
				<div class="form-row">
					<div class="form-group col-md-4">
						<label for="inputEmail4">Adults</label> <input type="number"
							class="form-control" id="inputEmail4" name="adultCount"
							value='<c:out value="${res.adults_count }"></c:out>'>
					</div>
					<div class="form-group col-md-4">
						<label for="inputPassword4">Children</label> <input type="number"
							class="form-control" id="inputPassword4" name="childCount"
							value='<c:out value="${res.child_count }"></c:out>'>
					</div>
					<div class="form-group col-md-4">
						<label for="inputPassword4">Required Rooms</label> <input
							type="number" class="form-control" id="inputPassword4" name="roomCount"
							value='<c:out value="${res.number_of_rooms }"></c:out>'>
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-4">
						<label for="inputAddress">Arrival Date</label> <input type="date"
							class="form-control" id="inputAddress" name="arrivalDate"
							value='<c:out value="${res.res_start }"></c:out>'>
					</div>
					<div class="form-group col-md-4">
						<label for="inputAddress2">Departure Date</label> <input
							type="date" class="form-control" id="inputAddress2" name="departureDate"
							value='<c:out value="${res.res_end }"></c:out>'>
					</div>
					<div class="form-group col-md-4">
					<label for="inputAddress2">Current Status</label>
<!-- 					<form action="update-status"> -->
							<select class="statusChanger form-control" id="currentStatus"
								onChange="changeStatus('${res.res_id}')"
								name="currentStatus">
								<c:forEach var="status" items="${statuses}">
									<c:choose>
										<c:when
											test="${status.status_id eq currentStatus}">
											<option id="selectedStatus"
												value="<c:out value='${status.status_id}'/>" selected>
												<c:out value='${status.status}' /></option>
										</c:when>
										<c:otherwise>
											<option value="<c:out value='${status.status_id}'/>">
												<c:out value='${status.status}' /></option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
<!-- 					</form> -->
					</div>
				</div>
				<h3>Customer Information:</h3>
				<div class="form-row">
					<div class="form-group col-md-4">
						<label for="inputCity">First Name:</label> <input type="text" name="fname"
							class="form-control" id="inputCity" value='<c:out value="${cm.fname }"></c:out>'>
					</div>
					<div class="form-group col-md-4">
						<label for="inputCity">Last Name</label> <input type="text" name="lname"
							class="form-control" id="inputCity" value='<c:out value="${cm.lname }"></c:out>'>
					</div>
					<div class="form-group col-md-4">
						<label for="inputCity">Contact Email:</label> <input type="email" name="email"
							class="form-control" id="inputCity" value='<c:out value="${cm.email }"></c:out>'>
					</div>
					<div class="form-group col-md-4">
						<label for="inputCity">Phone number:</label> <input type="text" name="phone"
							class="form-control" id="inputCity" value='<c:out value="${cm.phone }"></c:out>'>
					</div>
				</div>
				<br>
				<div class="form-row">
				<div class="form-group col-md-12">
						<label for="inputCity">Price:</label> <input type="text" name="price"
							class="form-control" id="inputCity" value='<c:out value="${res.price }"></c:out>'>
					</div>
					<div class="form-group col-md-12">
						<label for="inputCity">Notes:</label> <input type="text" name="notes"
							class="form-control" id="inputCity" value='<c:out value="${res.notes }"></c:out>'>
					</div>
				</div>
				<button type="submit" class="btn btn-primary">Update Reservation</button>
			</form>

		</main>
	</div>

	<%@ include file="Snippets/footer.jsp"%>
	<script>
	function changeStatus(res_id) {
		e = document.getElementById("currentStatus");
		var selected = e.options[e.selectedIndex].value;
		console.log(res_id, selected);
		$.ajax({
			url : "change-status",
			type : "post",
			data : {
				res_id : res_id,
				currentStatus : selected
			},
			success : function() {
				console.log('Success!');
			},
			error : function() {
			}
		});
	};
	</script>
</body>
</html>