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
<title>Assign Rooms</title>

</head>
<body>

	<form action="assign">
		<input type="text" name="res"
			value="<c:out value='${res.res_id}' />" />
		<div class="form-group">
			<label>Room: </label> <select id="room_id" name="room_id">
				<c:forEach var="room" items="${listRoom}">
					<option value="<c:out value='${room.id}'/>">
						<c:out value='${room.name}' /></option>
				</c:forEach>
			</select>
		</div>
		<button type="submit">Assign</button>

		<div id="message"></div>

	</form>

	<%@ include file="Snippets/footer.jsp"%>
	<script>

</script>
</body>
</html>