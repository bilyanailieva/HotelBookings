<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.bookings.mvc.bean.RoomTypeBean"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>
<head>
<%@ include file="Snippets/head.jsp"%>
<title>Welcome</title>
<link rel="stylesheet" href="/HotelBookings/assets/css/base/index.scss" />
<link rel='stylesheet'
	href='https://cdn.jsdelivr.net/npm/gantt-schedule-timeline-calendar@3.7.1/dist/style.css'>
<script>
	var date = <%=session.getAttribute("currentDate")%>;
</script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/emailjs-com@3/dist/email.min.js"></script>
<script type="text/javascript">
	(function() {
		emailjs.init("user_m7QJsVbxEae9fztGJXdqM");
	})();
</script>
</head>
<body>
	<%@ include file="Snippets/header.jsp"%>
	<div class="row">
		<%@ include file="Snippets/side-menu.jsp"%>
		<main class="col-md-9 ml-sm-auto col-lg-10 px-md-4" role="main">
			<h2>
				<%
				if (session.getAttribute("login") == null || session.getAttribute("login") == "") {//check if condition for unauthorize user not direct access welcome.jsp page
					response.sendRedirect("./index.jsp");
				}
				%>


				Welcome,
				<%=session.getAttribute("login")%>

			</h2>
			<div class="row">
				<div class="col-md-6">
					<input type="hidden" value="javascript: date" name="currentDate"
						id="currentDate" />
					<table class="ui compact celled definition table">
						<thead>
							<tr>
								<th colspan="7"><h3>Reservations arriving today</h3></th>
							</tr>
							<tr>
								<th></th>
								<th>ID</th>
								<th>Assigned Rooms</th>
								<th>Customer Name</th>
								<th>Status</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${listConfirmed.size() == 0 }">
								<tr>
									<td colspan="7">No guests arriving today.</td>
								</tr>
							</c:if>
							<c:forEach var="reservation" items="${listConfirmed }"
								varStatus="loopStatus">
								<input type="hidden" name="res" id="res"
									value="<c:out value='${reservation.res_id}' />" />
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
									<td>
										<form action="update-status">
											<div class="form-group">
												<select class="statusChanger" id="currentStatus"
													onChange="changeStatus('${reservation.res_id}', 'today')"
													name="currentStatus">
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

				<div class="col-md-6">
					<input type="hidden" value="javascript: date" name="currentDate"
						id="currentDate" />
					<table class="ui compact celled definition table">
						<thead>
							<tr>
								<th colspan="7"><h3>Reservations arriving tomorrow</h3></th>
							</tr>
							<tr>
								<th></th>
								<th>ID</th>
								<th>Assigned Rooms</th>
								<th>Customer Name</th>
								<th>Status</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${listConfirmedTomorrow.size() == 0 }">
								<tr>
									<td colspan="7">No guests arriving tomorrow.</td>
								</tr>
							</c:if>
							<c:forEach var="reservation" items="${listConfirmedTomorrow }"
								varStatus="loopStatus">
								<input type="hidden" name="res" id="res_t"
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
											value="${listAssignedTom[loopStatus.index] }" /> <c:if
											test="${assigned.size() == 0 }">No assigned rooms.</c:if> <c:forEach
											var="room" items="${assigned }" varStatus="loop">
											<c:out value='${room }' />
											<c:if test="${!loop.last}">,</c:if>
										</c:forEach></td>
									<td><c:out
											value="${reservation.customer.fname} ${reservation.customer.lname }" /></td>
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
				<div class="col-md-12">
					<input type="hidden" value="javascript: date" name="currentDate"
						id="currentDate" />
					<table class="ui compact celled definition table">
						<thead>
							<tr>
								<th colspan="7"><h3>Pending reservation requests</h3></th>
							</tr>
							<tr>
								<th></th>
								<th>ID</th>
								<th>Needed rooms</th>
								<th>Adults</th>
								<th>Children</th>
								<th>Arrival Date</th>
								<th>Departure Date</th>
								<th colspan="2">Actions</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${listPending.size() == 0 }">
								<tr>
									<td colspan="7">No pending reservations.</td>
								</tr>
							</c:if>
							<c:forEach var="reservation" items="${listPending }">
								<tr>
									<td class="collapsing">
										<!-- <div class="ui fitted slider checkbox">
										<input type="checkbox"> <label></label>
									</div> -->
									</td>
									<td><c:out value="${reservation.res_id}" /></td>
									<td><c:out value="${reservation.number_of_rooms}" /></td>
									<td><c:out value="${reservation.adults_count}" /></td>
									<td><c:out value="${reservation.child_count}" /></td>
									<td><c:out value="${reservation.res_start}" /></td>
									<td><c:out value="${reservation.res_end}" /></td>

									<td>
										<form>
											<input type="hidden" name="reservation_id"
												value="<c:out value='${reservation.res_id}' />" /> <a
												href="confirmation?reservation_id=<c:out value='${reservation.res_id}' />">Confirm</a>
										</form>
									</td>

									<td><a href="mailto:<c:out value='${reservation.customer.email}' />">Decline</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<input type="hidden" name="cm" value="<c:out value='${reservation.customer.email}' />" />

		</main>
	</div>

	<%@ include file="Snippets/footer.jsp"%>
	<script
		src="https://cdn.jsdelivr.net/npm/gantt-schedule-timeline-calendar"></script>
	<script>
		$(document).ready(function() {
			var date = new Date();
			var today = date.toISOString().substring(0, 10);
			document.getElementById('currentDate').value = today;

		});

		function changeStatus(res_id, day) {
			if (day === 'today') {

				e = document.getElementById("currentStatus");
			} else if (day === 'tomorrow') {
				e = document.getElementById("currentStatus1");
			}

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
