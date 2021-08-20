<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.bookings.mvc.bean.RoomTypeBean"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<%@ include file="../Snippets/head.jsp"%>
<title>Rooms</title>
</head>
<body>
	<%@ include file="../Snippets/header.jsp"%>

	<div class="row">
		<%@ include file="../Snippets/side-menu.jsp"%>
		<main class="col-md-9 ml-sm-auto col-lg-10 px-md-4" role="main">
			    
			<div>
				        
				<h1>Rooms</h1>
				        
				<div>
<!-- 					<a href="new" class="massive ui blue button">Add New Room Type</a> -->
					<a class="ui labeled icon button" href="list">
					<i class="fas fa-backspace"></i>
					Back
					</a>                       
				</div>
				    
			</div>
			<h2>
				                    
				<c:if test="${room != null}">
                        Edit Room 
                    </c:if>
				                    
				<c:if test="${room == null}">
                        Add New Room
                    </c:if>
				            
			</h2>
			<div class="col-md-7 offset-md-2 align-center">
				        
				<c:if test="${room != null}">
            <form action="update" method="post">        
				</c:if>
				        
				<c:if test="${room == null}">
            <form action="insert" method="post">        
				</c:if>
				<c:if test="${room != null}">
                    <input type="hidden" name="id"
						value="<c:out value='${room.id}' />" />
                </c:if>
				<div id="insertMessage"></div>
				<div class="form-group">
					<label>RoomType: </label>
							<select id="rtid" name="rtid">
							<c:forEach var="room_type" items="${listRoomTypes}">
  								<option value="<c:out value='${room_type.id}'/>"> <c:out value='${room_type.type}'/></option>
  							</c:forEach>
							</select>
				</div>
				<div class="form-group">
					<label>Name:</label> <input class="form-control" type="text"
						id="name" name="name" size="45"
						value="<c:out value='${room.name}' />" />
				</div>
				<div class="form-group form-check">
					<input type="checkbox" class="form-check-input" name="first_floor" value="<c:out value='${room.first_floor}' />" <c:if test="${room.first_floor == true}">checked</c:if>>
					<label class="form-check-label">First Floor</label>
				</div>
				<div class="form-group form-check">
					<input type="checkbox" class="form-check-input" name="extra_bed" value="<c:out value='${room.extra_bed}' />" <c:if test="${room.extra_bed == true}">checked</c:if>>
					<label class="form-check-label">Extra Bed</label>
				</div>
				<div class="form-group form-check">
					<input type="checkbox" class="form-check-input" name="baby_crib" value="<c:out value='${room.baby_crib}' />" <c:if test="${room.baby_crib == true}">checked</c:if>>
					<label class="form-check-label">Baby Crib</label>
				</div>
				<div class="form-group form-check">
					<input type="checkbox" class="form-check-input" name="is_handycap" value="<c:out value='${room.handycap}' />" <c:if test="${room.handycap == true}">checked</c:if>>
					<label class="form-check-label">Is Handycap?</label>
				</div>

				<input type="submit" class="btn btn-primary" value="Save" />
				</form>


				    
			</div>
			<script>
  
			</script>
			   
			<script src="../assets/js/forms.js"></script>
			<%-- <jsp:include page="Snippets/footer.jsp" /> --%>
			<%@ include file="../Snippets/footer.jsp"%>
</body>
</html>