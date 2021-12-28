<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


	 <% 
    	if (session.getAttribute("login") == null || session.getAttribute("login") == "")
    		{//check if condition for unauthorize user not direct access welcome.jsp page
    			response.sendRedirect("./index.jsp");
    		}
    %>
<header>
	<div class="top-menu-bar">
		<nav class="navbar navbar-expand-lg navbar-light">
  <a class="navbar-brand mr-auto" href="welcome.jsp">BookARoom</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
  <div class="navbar-nav mr-auto">
  </div>
    <div class="account-info dropdown">
      <div class="nav-item">
     
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" name="username">
          <%=session.getAttribute("login")%>
        </a>
        <div class="dropdown-menu dropdown-menu-lg-right" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#">Account Settings</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="/HotelBookings/office/logout.jsp">Log Out</a>
        </div>
      </div>
    </div>
  </div>
</nav>
	</div>
</header>




	


