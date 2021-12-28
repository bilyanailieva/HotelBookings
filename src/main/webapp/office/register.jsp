<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
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
				<div class="content">Create your account</div>
			</h2>
			<form class="ui large form" action="login" method="post"
				name="register">
				<div class="ui stacked segment">
					<div class="field">
						<div class="ui left icon input">
							<i class="fas fa-user"></i> <input type="text" name="txt_fname"
								placeholder="First Name">
						</div>
					</div>
					<div class="field">
						<div class="ui left icon input">
							<i class="fas fa-user"></i> <input type="text" name="txt_lname"
								placeholder="Last Name">
						</div>
					</div>
					<div class="field">
						<div class="ui left icon input">
							<i class="fas fa-user"></i> <input type="text"
								name="txt_username" placeholder="Username">
						</div>
					</div>
					<div class="field">
						<div class="ui left icon input">
							<i class="fas fa-user"></i> <select id="txt_hid" name="txt_hid">
								<c:forEach var="hotel" items="${listHotel}">
								<option selected>Choose hotel...</option>
									<option value="<c:out value='${hotel.hid}'/>">
										<c:out value='${hotel.hotel_name}' /></option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="field">
						<div class="ui left icon input">
							<i class="fas fa-key"></i> <input type="password"
								name="txt_password" placeholder="Password">
						</div>
					</div>
					<input type="submit" class="ui fluid large teal submit button"
						name="btn_login" value="Login"
						onclick="CheckPassword(document.register.txt_password)" />
				</div>

				<div class="ui error message"></div>

			</form>

			<div class="ui message">
				Already have account? <a href="index.jsp">Sign In</a>
			</div>
		</div>
	</div>
	<script>
		var options = {
			    /* ... */
				gantt.$set({ 
				    from: moment().startOf('week'),
				    to: moment().endOf('week')
				});
			};

			var gantt = new SvelteGantt({ 
			    // target a DOM element
			    target: document.getElementById('calendar'), 
			    // svelte-gantt options
			    props: options
			});
		</script>
	<script>
		var message = '';
		function CheckPassword(inputtxt) 
		{ 
			var passw = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$/;
			if(!inputtxt.value.match(passw)) 
			{ 
			message = 'Invalid password!'
			return false;
			}
			
		}
		</script>
	<script src="../semantic/semantic.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"
		integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/svelte-gantt@3.0.4-beta/index.iife.js"></script>
</body>
</html>