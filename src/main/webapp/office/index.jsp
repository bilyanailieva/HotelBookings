<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BookARoom</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<link rel="stylesheet" href="../assets/css/login.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/@event-calendar/build/event-calendar.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/@event-calendar/build/event-calendar.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="../semantic/semantic.min.css">
<link rel="stylesheet" type="text/css" href="../fontawesome/css/all.css">
<script src="../semantic/semantic.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
<script src="../assets/js/forms.js" type="text/javascript"></script>
</head>
<body>

	<div class="ui middle aligned center aligned grid">
		<div class="column">
			<h2 class="ui teal image header">
				<div class="content">Log-in to your account</div>
			</h2>
			<form class="ui large form" action="welcome" method="post">
				<div class="ui stacked segment">
					<div class="field">
						<div class="ui left icon input">
							<input type="text" name="txt_username" placeholder="Username">
						</div>
					</div>
					<div class="field">
						<div class="ui left icon input">
							<input type="password" name="txt_password" placeholder="Password">
						</div>
					</div>
					<input type="submit" class="ui fluid large teal submit button"
						name="btn_login" value="Login">
				</div>

				<div class="ui error message"></div>

			</form>

			<div class="ui message">
				New to us? <a href="register">Sign Up</a>
			</div>
			<input type="hidden" value="" name="currentDate" id="currentDate" />
		</div>
	</div>

	<script>

		$(document).ready(function(){
			var date = new Date();
			var today = date.toISOString().substring(0,10);
			document.getElementById('currentDate').value = today;
		});
		</script>
	<script src="../semantic/semantic.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"
		integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/svelte-gantt@3.0.4-beta/index.iife.js"></script>
</body>
</html>