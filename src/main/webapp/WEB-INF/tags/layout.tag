<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="title" required="true" rtexprvalue="true" %>
<%@ attribute name="header" fragment="true" %>
<%@ attribute name="content" fragment="true" %>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" contentType="text/html; charset=UTF-8"/>
	<title>${title }</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
	<link rel="stylesheet" href="../assets/css/base/index.scss" />
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@event-calendar/build/event-calendar.min.css">
	<script src="https://cdn.jsdelivr.net/npm/@event-calendar/build/event-calendar.min.js"></script>
</head>
<body>

	<a class="test" href="./index.jsp">Home</a>
	<a class="btn btn-primary">Book Now</a>
	<br />
	<jsp:invoke fragment="content"></jsp:invoke>
	
	
	
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</body>
</html>


