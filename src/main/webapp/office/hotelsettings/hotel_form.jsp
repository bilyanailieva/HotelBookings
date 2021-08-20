<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.bookings.mvc.bean.RoomTypeBean"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<%@ include file="../Snippets/head.jsp"%>
<title>My Hotel</title>
</head>
<body>
	<%@ include file="../Snippets/header.jsp"%>

	<div class="row">
		<%@ include file="../Snippets/side-menu.jsp"%>
		<main class="col-md-9 ml-sm-auto col-lg-10 px-md-4" role="main">
			    
			<div>
				        
				<h1>My Hotel</h1>
				        
				<div>
<!-- 					<a href="new" class="massive ui blue button">Add New Room Type</a> -->
					<a class="ui labeled icon button" href="../hotelsettings">
					<i class="fas fa-backspace"></i>
					Back
					</a>                       
				</div>
				    
			</div>
			<h2>
				                    
				<c:if test="${hotel != null}">
                        Edit Hotel
                    </c:if>
				                    
				<c:if test="${hotel == null}">
                        Add Hotel
                    </c:if>
				            
			</h2>
			<div class="col-md-7 offset-md-2 align-center">
				        
				<c:if test="${hotel != null}">
            <form action="update-hotel" method="post">        
				</c:if>
				        
				<c:if test="${hotel == null}">
            <form action="insert-hotel" method="post">        
				</c:if>
				<c:if test="${hotel != null}">
                    <input type="hidden" name="hid"
						value="<c:out value='${hotel.hid}' />" />
                </c:if>
				<div id="insertMessage"></div>
				<div class="form-group">
					<label>Hotel Name:</label> <input
						type="text" class="form-control"
						aria-describedby="emailHelp" name="hotel_name" value="<c:out value='${hotel.hotel_name}' />"> <small id="emailHelp"
						class="form-text text-muted">Add the name of you hotel here.</small>
				</div>
				<div class="form-group">
					<label>How many rooms does the hotel have?</label> <input name="room_count"
						type="number" class="form-control" value="<c:out value='${hotel.room_count}' />">
				</div>
				<div class="form-group">
					<label>Working Season Start</label><input name="start_season" type="date" class="form-control" value="<c:out value='${hotel.start_season}' />">
				</div>
				<div class="form-group">
					<label>Working Season End</label><input name="end_season" type="date" class="form-control" value="<c:out value='${hotel.end_season}' />">
				</div>
				<div class="form-group form-check">
					<input type="checkbox" class="form-check-input" name="dogs" value="<c:out value='${hotel.dogs}' />" <c:if test="${room.baby_crib == true}">checked</c:if>>
					<label class="form-check-label">Dogs are allowed</label>
				</div>
				<input type="submit" class="btn btn-primary" value="Save" />
				</form>


				    
			</div>
			<script>

			$(document).ready(function(){
				   
			    $("#room_type").blur(function(){
			        
			        var room_type =$("#room_type").val();
			        if(room_type == "double" || room_type == "Double" || room_type == "D") {
			            room_type = $('#room_type').val("DBL");
			        }
			        if(room_type == "single" || room_type == "Single" || room_type == "S") {
			            room_type = $('#room_type').val("SGL");
			        }
			        if(room_type.length >= 2)
			        {
			            $.ajax({
			                url:"../../office/AjaxChecks/checkIfRoomTypeExists.jsp",
			                type:"post",
			                data:"room_type="+room_type,
			                dataType:"text",
			                success:function(data)
			                {
			                    $("#insertMessage").html(data)
			                } 
			            });
			        }
			        else
			        {
			          $("#insertMessage").html(" ");
			        }
			        
			   }); 
			   
			});  
			</script>
			   
			<script src="../assets/js/forms.js"></script>
			<%-- <jsp:include page="Snippets/footer.jsp" /> --%>
			<%@ include file="../Snippets/footer.jsp"%>
</body>
</html>