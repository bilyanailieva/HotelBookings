<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.bookings.mvc.bean.RoomTypeBean"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<%@ include file="../Snippets/head.jsp"%>
<title>Prices</title>
</head>
<body>
	<%@ include file="../Snippets/header.jsp"%>

	<div class="row">
		<%@ include file="../Snippets/side-menu.jsp"%>
		<main class="col-md-9 ml-sm-auto col-lg-10 px-md-4" role="main">
			<div>
				<h1>Prices</h1>
				<p>Manage the room types prices from here.</p>
				<h2>
					<a href="newprice" class="massive ui blue button">Add New Price</a> 
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
							<th>PID</th>
							<th>Room Type</th>
							<th>Description</th>
							<th>Price</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="price" items="${listPrice}">
								<c:if test="${price.rt.id == rt.id && rt.id != null}">
									<tr>
								<td class="collapsing">
									<div class="ui fitted slider checkbox">
										<input type="checkbox"> <label></label>
									</div>
								</td>
								<td><c:out value="${price.pid}" /></td>
									<td><c:out value='${rt.type}' /></td>
								<c:forEach var="room_type" items="${listRoomTypes}">
									<c:if test="${room_type.id == price.rt.id}">
									<td><c:out value='${room_type.type}' /></td>
									</c:if>
								</c:forEach>
								<td><c:out value="${price.price_desc}" /></td>
								<td><c:out value="${price.price}" /></td>
								<td><a href="edit-price?pid=<c:out value='${price.pid}' />">Edit</a>
								</td>
								<td><a href="delete-price?pid=<c:out value='${price.pid}' />">Delete</a></td>
							</tr>
								</c:if>
								<c:if test="${listRoomTypes != null}">
							<tr>
								<td class="collapsing">
									<div class="ui fitted slider checkbox">
										<input type="checkbox"> <label></label>
									</div>
								</td>
								<td><c:out value="${price.pid}" /></td>
								<c:if test="${listRoomTypes == null }">
									<td><c:out value='${rt.type}' /></td>
								</c:if>
								<c:forEach var="room_type" items="${listRoomTypes}">
									<c:if test="${room_type.id == price.rt.id}">
									<td><c:out value='${room_type.type}' /></td>
									</c:if>
								</c:forEach>
								<td><c:out value="${price.price_desc}" /></td>
								<td><c:out value="${price.price}" /></td>
								<td><a href="edit-price?pid=<c:out value='${price.pid}' />">Edit</a>
								</td>
								<td><a href="delete-price?pid=<c:out value='${price.pid}' />">Delete</a></td>
							</tr>
							</c:if>
							
						</c:forEach>
					</tbody>
					<tfoot class="full-width">
						<tr>
							<th></th>
							<th colspan="5">
								<div class="ui right floated small primary labeled icon button">
									<i class="fas fa-user"></i> Add Price
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