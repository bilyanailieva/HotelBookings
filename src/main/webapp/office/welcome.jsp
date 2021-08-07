<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@page import="com.bookings.mvc.bean.RoomTypeBean"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!doctype html>
<html>
<head>
	<%@ include file="Snippets/head.jsp" %>  
	<title>Settings</title>
</head>
<body>
<%@ include file="Snippets/header.jsp" %> 
<div class="row">
<%@ include file="Snippets/side-menu.jsp" %> 
<main class="col-md-9 ml-sm-auto col-lg-10 px-md-4" role="main">
<h2>
    <% 
    	if (session.getAttribute("login") == null || session.getAttribute("login") == "")
    		{//check if condition for unauthorize user not direct access welcome.jsp page
    			response.sendRedirect("./index.jsp");
    		}
    %>

    
    Welcome, <%=session.getAttribute("login")%> 
</h2>



<h3>
    <a class="btn btn-primary" href="./logout.jsp">Logout</a>
</h3>


<br>
		<br>
		<br>
		<br>
		<br>
		<br>
    <h2>
				<a
					href="https://onlyxscript.blogspot.in/2018/02/java-mvc-login-and-register-script.html">Tutorial Link</a>
			</h2>

</main>
</div>
<%@ include file="Snippets/footer.jsp" %>  

</body>
</html>
