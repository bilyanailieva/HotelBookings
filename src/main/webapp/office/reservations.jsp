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
<title>Reservations</title>

</head>
<body>
	<%@ include file="Snippets/header.jsp"%>
	<div class="row">
		<%@ include file="Snippets/side-menu.jsp"%>
		<main class="col-md-9 ml-sm-auto col-lg-10 px-md-4" role="main">
			<div class="col-md-12">
					<input type="hidden" value="javascript: date" name="currentDate"
						id="currentDate" />
					<table class="ui compact celled definition table">
						<thead>
							<tr>
								<th colspan="7"><h3>All Reservations</h3></th>
							</tr>
							<tr>
								<th></th>
								<th>ID</th>
								<th>Assigned Rooms</th>
								<th>Customer Name</th>
								<th>Arrival Date</th>
								<th>Departure Date</th>
								<th>Status</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="reservation" items="${reservations }"
								varStatus="loopStatus">
								<input type="hidden" name="res" id="res"
									value="<c:out value='${reservation.res_id}' />" />
								<c:set var="res" value="<c:out value='${reservation.res_id}' />" />
								<tr>
									<td class="collapsing">
										<!-- <div class="ui fitted slider checkbox">
										<input type="checkbox"> <label></label>
									</div> -->
									</td>
									<td><c:out value="${reservation.res_id}" /></td>
									<td><c:set var="assigned"
											value="${listAssignedToday[loopStatus.index] }" /> <c:if
											test="${assigned.size() == 0 }">No assigned rooms.</c:if> <c:forEach
											var="room" items="${assigned }" varStatus="loop">
											<c:out value='${room }' />
											<c:if test="${!loop.last}">,</c:if>
										</c:forEach></td>
									<td><c:out
											value="${reservation.customer.fname} ${reservation.customer.lname }" /></td>
											<td><c:out
											value="${reservation.res_start}" /></td>
									<td><c:out
											value="${reservation.res_end}" /></td>
									<td>
										<form action="update-status">
											<div class="form-group">
												<select class="statusChanger" id="currentStatus1"
													onChange="changeStatus('${reservation.res_id}', 'tomorrow')"
													name="currentStatus1">
													<c:forEach var="status" items="${statuses}">
														<c:choose>
															<c:when
 																test="${status.status_id eq reservation.status.status_id}"> 
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
											</div>
										</form>
									</td>
									<td><a
										href="view?res_id=<c:out value='${reservation.res_id}' />">View
											Extra Information</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
		</main>
	</div>

	<%@ include file="Snippets/footer.jsp"%>
	<script>
	function changeStatus(res_id) {
		e = document.getElementById("currentStatus1");
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