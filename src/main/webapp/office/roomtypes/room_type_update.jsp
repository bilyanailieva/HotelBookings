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
				        
				<div>
<!-- 					<a href="new" class="massive ui blue button">Add New Room Type</a> -->
					<a class="ui labeled icon button" href="list">
					<i class="fas fa-backspace"></i>
					Back
					</a>                       
				</div>
				    
			</div>
			<h2>
				                    
				<c:if test="${room_type != null}">
                        Edit Room Type
                    </c:if>
				                    
				<c:if test="${room_type == null}">
                        Add New Room Type
                    </c:if>
				            
			</h2>
			<div class="col-md-7 offset-md-2 align-center">
				        
				<c:if test="${room_type != null}">
            <form action="update" method="post">        
				</c:if>
				        
				<c:if test="${room_type == null}">
            <form action="insert" method="post">        
				</c:if>
				<c:if test="${room_type != null}">
                    <input type="hidden" name="id"
						value="<c:out value='${room_type.id}' />" />
                </c:if>
				<div id="insertMessage"></div>
				<div class="form-group">
					<label>RoomType:</label> <input class="form-control" type="text"
						id="room_type" name="room_type" size="45"
						value="<c:out value='${room_type.type}' />" />
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