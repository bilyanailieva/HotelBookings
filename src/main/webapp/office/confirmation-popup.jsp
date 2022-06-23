<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@page import="com.bookings.mvc.bean.RoomTypeBean"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<%@ include file="Snippets/head.jsp" %>
<title>Send Confirmation</title>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/emailjs-com@3/dist/email.min.js"></script>
<script type="text/javascript">
(function() {
emailjs.init("user_m7QJsVbxEae9fztGJXdqM");
})();
</script>
</head>
<body>

  <form>
  	Are you sure you want to confirm this reservation request?
  	<input type="text" name="cm" id="email" value="<c:out value='${cm.email}' />" disabled/>
  	<button type="button" id="sendConfirm">Confirm</button>
  	
  	<div id="message"></div>
  	
  </form>
  
<%@ include file="Snippets/footer.jsp" %>
<script>
$(document.getElementById('sendConfirm')).click(function(){
	var templateParams = {
		    from_name: '${hotel.hotel_name}',
		    reply_to: 'b58433824@gmail.com',
		    c_email: document.getElementById("email").value,
		    c_id: '${cm.c_id}',
		    res_id: '${res.res_id}',
		    status: '${res.status}',
		};
		 
		emailjs.send('service_hxb0ycf', 'template_1agk1dj', templateParams)
		    .then(function(response) {
		       console.log('SUCCESS!', response.status, response.text);
		       $("#message").html('SUCCESS!', response.status, response.text);
		       window.location.replace("http://localhost:8080/HotelBookings/office/welcome.jsp");
		    }, function(error) {
		       console.log('FAILED...', error);
		       $("#message").html('FAILED...', error);
		    });
});
</script>
</body>
</html>