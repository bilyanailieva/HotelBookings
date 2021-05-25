<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./assets/base/variables.scss" />
</head>
<body>
	<h2 class="welcome-msg">Welcome to <strong>BookARoom</strong>! Your hotel bookings management tool. </h2>
	
	
		<h2>Login</h2>
		
		<div>Enter your username and password below.</div>
		
		<form method="post" action="welcome" name="LoginForm" onsubmit="return validate();">
		
		Username: <input type="text" name="txt_username" />
		Password: <input type="password" name="txt_password" />
		
		<input type="submit" name="btn_login" value="Login" />
		
		</form>
</body>
</html>