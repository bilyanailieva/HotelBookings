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
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@event-calendar/build/event-calendar.min.css">
<script src="https://cdn.jsdelivr.net/npm/@event-calendar/build/event-calendar.min.js"></script>
<link rel="stylesheet" type="text/css" href="../semantic/semantic.min.css">
<link rel="stylesheet" type="text/css" href="../fontawesome/css/all.css">
<script src="../semantic/semantic.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
<script src="../assets/js/forms.js" type="text/javascript"></script>
</head>
<body>
	
	<div class="ui middle aligned center aligned grid">
  <div class="column">
    <h2 class="ui teal image header">
      <div class="content">
        Log-in to your account
      </div>
    </h2>
    <form class="ui large form" action="welcome" method="post">
      <div class="ui stacked segment">
        <div class="field">
          <div class="ui left icon input">
            <i class="fas fa-user"></i>
            <input type="text" name="txt_username" placeholder="Username">
          </div>
        </div>
        <div class="field">
          <div class="ui left icon input">
            <i class="fas fa-key"></i>
            <input type="password" name="txt_password" placeholder="Password">
          </div>
        </div>
        <input type="submit" class="ui fluid large teal submit button" name="btn_login" value="Login">
      </div>

      <div class="ui error message"></div>

    </form>

    <div class="ui message">
      New to us? <a href="register">Sign Up</a>
    </div>
    <input type="hidden" value="" name="currentDate" id="currentDate"  />
  </div>
</div>
	
		<!-- <h2>Login</h2>
		
		<div>Enter your username and password below.</div>
		
		<div id="calendar"></div>
		
		<form method="post" action="welcome" name="LoginForm" onsubmit="return validate();">
		
		Username: <input type="text" name="txt_username" />
		Password: <input type="password" name="txt_password" />
		
		<input type="submit" name="btn_login" value="Login" />
		
		</form> -->
		
		<!-- <script>
		let ec = new EventCalendar(document.getElementById('calendar'), {
		    view: 'timeGridDay',
		    height: '800px',
	        headerToolbar: {
	            start: 'prev,next today',
	            center: 'title',
	            end: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek resourceTimeGridDay resourceTimeGridWeek '
	        },
	        dayHeaderFormat: {weekday: 'narrow', day: 'numeric'},
	        slotLabelFormat: {},
	        slotDuration: '24:00',
	        eventTimeFormat: {},
		    buttonText: function (texts) {
	            texts.resourceTimeGridDay = 'dayView';
	            texts.resourceTimeGridWeek = 'weekView';
	            return texts;
	        },
			resources: [{id: 1, title: '101'}, {id: 2, title: '102'}, {id: 3, title: '103'},
				{id: 4, title: '200'}, {id: 5, title: '201'}, {id: 6, title: '202'},
				{id: 7, title: '203'}],
	        events: createEvents()
		});
		
		function createEvents() {
	        let days = [];
	        for (let i = 0; i < 7; ++i) {
	            let day = new Date();
	            let diff = i - day.getDay();
	            day.setDate(day.getDate() + diff);
	            days[i] = day.getFullYear() + "-" + _pad(day.getMonth()+1) + "-" + _pad(day.getDate());
	        }

	        return [
	            //{start: days[0] + " 00:00", end: days[0] + " 23:59", resourceId: 1, display: "background"},
	            {start: days[1] + " 12:00", end: days[1] + " 14:00", resourceId: 2, display: "background"},
	            {start: days[2] + " 17:00", end: days[2] + " 24:00", resourceId: 1, display: "background"},
	            {start: days[0], end: days[1], resourceId: 1, title: "123", color: "#FE6B64"},
	            {start: days[1] + " 16:00", end: days[2] + " 08:00", resourceId: 2, title: "An event may span to another day", color: "#B29DD9"},
	            {start: days[2] + " 09:00", end: days[2] + " 13:00", resourceId: 2, title: "Events can be assigned to resources and the calendar has the resources view built-in", color: "#779ECB"},
	            {start: days[3] + " 14:00", end: days[3] + " 20:00", resourceId: 1, title: "", color: "#FE6B64"},
	            {start: days[3] + " 15:00", end: days[3] + " 18:00", resourceId: 1, title: "Overlapping events are positioned properly", color: "#779ECB"},
	            {start: days[5] + " 10:00", end: days[5] + " 16:00", resourceId: 2, title: "You have complete control over the <i><b>display</b></i> of events…", color: "#779ECB"},
	            {start: days[5] + " 14:00", end: days[5] + " 19:00", resourceId: 2, title: "…and you can drag and drop the events!", color: "#FE6B64"},
	            {start: days[5] + " 18:00", end: days[5] + " 21:00", resourceId: 2, title: "", color: "#B29DD9"}
	        ];
	    }

	    function _pad(num) {
	        let norm = Math.floor(Math.abs(num));
	        return (norm < 10 ? '0' : '') + norm;
	    }
		</script> -->
		
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
		<script src="https://cdn.jsdelivr.net/npm/svelte-gantt@3.0.4-beta/index.iife.js"></script>
</body>
</html>