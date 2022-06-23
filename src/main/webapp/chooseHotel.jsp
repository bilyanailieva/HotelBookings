<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
 <% 
    	if (session.getAttribute("existingHotel") == null || session.getAttribute("existingHotel") == "")
    		{//check if condition for unauthorize user not direct access welcome.jsp page
    			response.sendRedirect("../chooseHotel.jsp");
    		}
    %>
<html>
<head>
<meta charset="UTF-8">
<title>Choose the Hotel you want to visit</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<link rel="stylesheet" href="./assets/css/login.css" />
<link rel="stylesheet" type="text/css"
	href="./semantic/semantic.min.css">
<link rel="stylesheet" type="text/css" href="../fontawesome/css/all.css">
<script src="./semantic/semantic.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
<script src="../assets/js/forms.js" type="text/javascript"></script>
</head>
<body>

	<div class="ui middle aligned center aligned grid">
		<div class="column">
			<h2 class="ui teal image header">
				<div class="content">Choose the Hotel you want to visit</div>
			</h2>
			<form class="ui large form" action="user" method="post"
				name="choose">
				<div class="ui stacked segment">
					<div class="field">
						<div class="ui left icon input">
							<i class="fas fa-user"></i> <select id="txt_hid" name="txt_hid">
								<c:forEach var="hotel" items="${listHotels}">
									<option value="<c:out value='${hotel.hid}'/>">
										<c:out value='${hotel.hotel_name}' />
									</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<input type="submit" class="ui fluid large teal submit button" name="btn_choice" value="Go to booking form"/>
				</div>

				<div class="ui error message"></div>

			</form>
		</div>
	</div>
	<script src="./semantic/semantic.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"
		integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/svelte-gantt@3.0.4-beta/index.iife.js"></script>
</body>
</html>