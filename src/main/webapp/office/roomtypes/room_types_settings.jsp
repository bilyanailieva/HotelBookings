<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.bookings.mvc.bean.RoomTypeBean"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<%@ include file="../Snippets/head.jsp"%>
<title>Room Types</title>
</head>
<body>
	<%@ include file="../Snippets/header.jsp"%>

	<div class="row">
		<%@ include file="../Snippets/side-menu.jsp"%>
		<main class="col-md-9 ml-sm-auto col-lg-10 px-md-4" role="main">
			<div>
				<h1>Room Types</h1>
				<p>Manage the hotel's room types from here. Add types or change
					prices.</p>
				<h2>
					<a href="new" class="massive ui blue button">Add New RoomType</a> 
					<!-- <a href="list">List All RoomTypes</a> -->

				</h2>
			</div>
			<br>
			<br>
			<div class="col-md-8">
				<table class="ui compact celled definition table">
					<thead>
						<tr>
							<th></th>
							<th>ID</th>
							<th>Type</th>
							<th>Adults to accommodate</th>
							<th>Children to accommodate</th>
							<th>Prices</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="room_type" items="${listRoomTypes}">
							<tr>
								<td class="collapsing">
									<div class="ui fitted slider checkbox">
										<input type="checkbox"> <label></label>
									</div>
								</td>
								<td><c:out value="${room_type.id}" /></td>
								<td><c:out value="${room_type.type}" /></td>
								<td><c:out value="${room_type.adults}" /></td>
								<td><c:out value="${room_type.children}" /></td>
								<td><a href="view-prices?id=<c:out value='${room_type.id}' />">View Prices</a></td>
								<td><a href="edit?id=<c:out value='${room_type.id}' />">Edit</a>
								</td>
								<td><a href="delete?id=<c:out value='${room_type.id}' />">Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot class="full-width">
						<tr>
							<th></th>
							<th colspan="5">
								<div class="ui right floated small primary labeled icon button">
									<i class="fas fa-user"></i> Add Room Type
								</div>
								<div class="ui small button">Edit</div>
								<div class="ui small button">Delete</div>
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