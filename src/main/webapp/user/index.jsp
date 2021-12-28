<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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

	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<c:if test="${availability == null}">
					<form action="availability" id="findAvailableRoomsForm">
				</c:if>
				<c:if test="${availability != null}">
					<form action="request" id="findAvailableRoomsForm">
				</c:if>
				<legend>Check for room availability:</legend>
				<div class="form-group">
					<c:set var="hotel" value="${hotel }"/>
					<label>Choose hotel:</label>
					<select class="custom-select" id="txt_hid" name="txt_hid">
					<c:if test="${availability != null && txt_hid != null }">
					</c:if>
								<c:forEach var="hotel" items="${listHotels}">
									<option value="<c:out value='${hotel.hid}'/>">
										<c:out value='${hotel.hotel_name}' />
									</option>
								</c:forEach>
							</select>
				</div>
				<div class="form-group">
				<c:set var="adultCount" value="${adultCount}"/>
				<c:if test="${adultCount == null}"><c:set var="adultCount" value="${adultCount = 1}"/></c:if>
					<label>Adults:</label> <input type="number" class="form-control"
						id="adultCount" name="adultCount" value="${adultCount }" min="1"
						<c:if test="${adultCount != null && availability !=null }"> disabled </c:if>>
				</div>
				<div class="form-group">
				<c:set var="childCount" value="${childCount}"/>
				<c:if test="${childCount == null}"><c:set var="childCount" value="${childCount = 0}"/></c:if>
					<label>Children:</label> <input type="number" class="form-control"
						id="childCount" name="childCount" value="${childCount }" min="0"
						<c:if test="${childCount != null && availability !=null}">disabled</c:if>>
				</div>
				<div class="form-group">
				<c:set var="roomCount" value="${roomCount}"/>
				<c:if test="${roomCount == null}"><c:set var="roomCount" value="${roomCount = 1}"/></c:if>
					<label>Rooms:</label> <input type="number" class="form-control"
						id="roomCount" name="roomCount" value="${roomCount }" min="1"
						<c:if test="${roomCount != null && availability !=null}">disabled</c:if>>
				</div>
				<div class="form-group">
					<label>Day of Arrival:</label> <input type="date"
						class="form-control" id="arrivalDate" name="arrivalDate"
						value="${arrivalDate }"
						<c:if test="${arrivalDate != null}">disabled</c:if> />
				</div>
				<div class="form-group">
					<label>Day of Departure:</label> <input type="date"
						class="form-control" id="departureDate" name="departureDate"
						value="${departureDate }"
						<c:if test="${departureDate != null}">disabled</c:if> />
				</div>

				<c:if test="${availability == null}">
					<button type="submit" class="btn btn-primary"
						id="availabilitySearch" name="availabilitySearch">Search
						for available rooms</button>
				</c:if>
				<c:if test="${availability != null}">
					<c:set var="required" value="${roomCount }" />
					<c:set var="contains" value="false" />
					<c:forEach var="item" items="${availability}">
						<c:if test="${item < required}">
							<c:set var="contains" value="true" />
						</c:if>
					</c:forEach>
					<c:if test="${contains == false}">
						<p>
							There are enough available rooms for the selected dates to fit
							your request. To change your request click <a
								href="/HotelBookings/user/">here</a>
						</p>
						<%-- <input type="hidden" name="txt_hid" value="${txt_hid}"> --%>
						<input type="hidden" name="adultCount" value="${adultCount}">
						<input type="hidden" name="childCount" value="${childCount}">
						<input type="hidden" name="roomCount" value="${roomCount}">
						<input type="hidden" name="arrivalDate" value="${arrivalDate}">
						<input type="hidden" name="departureDate" value="${departureDate}">
						<div class="form-group">
							<label>Price predictions:</label>
							<select class="custom-select" size="${roomCount }" name="priceOption" multiple>
						<c:forEach var="item" items="${prices}" varStatus="loopStatus">
							<option value="${item.pid }">
							<c:out value="${item.type.type}/${item.price_desc }: ${item.price}" />
							</option>
						</c:forEach>
						</select>
						</div>
						<div class="form-group">
						<label>Contact Email:</label> <input type="email"
						class="form-control" id="email" name="email" />
						</div>
						<input type="submit" class="btn btn-primary" value="Make a reservation request"/>
					</c:if>
					<c:if test="${contains == true}">
						<p>
							There are not enough available rooms for the selected dates to
							fit your request. To change your request click <a
								href="/HotelBookings/user/">here</a>
						</p>
					</c:if>
				</c:if>
				<!--  	 			 	<button type="submit" class="btn btn-primary">Make a reservation request</button>
 -->
				</form>
			</div>
			<div class="col-md-8">
				<div id="availabilityCalendar"></div>
			</div>
		</div>

		<input type="hidden" id="listConfirmed" value="${listConfirmed}">
		<input type="hidden" id="availability" name="availability"
			value="${availability}">
			<input type="hidden" id="prices" name="prices"
			value="${prices}">



	</div>

	<script>
		 $(document).ready(function(){
		  if( $('#availability').val() ) {   
		  var aval = [
					<c:forEach var="row" items="${availability}">
				     <c:out value="${row}" />,
					</c:forEach>
		  ];
		  var total = "${total_rooms}";
		  console.log(aval);	  
		  let cal =  document.getElementById('availabilityCalendar');
		  let ec = new EventCalendar(cal, {
		    view: 'dayGridMonth',
		    height: '550px',
	        headerToolbar: {
	            start: 'prev,next today',
	            center: 'title',
	            end: 'dayGridMonth,timeGridWeek'
	        },
	        dayHeaderFormat: {weekday: 'narrow', day: 'numeric'},
	        slotLabelFormat: {},
	        slotDuration: '24:00',
	        eventTimeFormat: {},
	        events: createEvents()
		});
		  function createEvents() {
				var startDate = new Date(document.getElementById('arrivalDate').value);
				var endDate = new Date(document.getElementById('departureDate').value);
				var diff_time = endDate.getTime() - startDate.getTime();
				var number_days = diff_time/(1000 * 3600 * 24);
				var color = "";
		        let days = [];
		        for (let i = 0; i < number_days+1; ++i) {
		            let day = new Date(document.getElementById('arrivalDate').value);
		            let diff = i;
		            day.setDate(day.getDate() + diff);
		            days[i] = day.getFullYear() + "-" + _pad(day.getMonth()+1) + "-" + _pad(day.getDate());
		        }
		        var events = [];
		        for (let j = 0; j < (days.length - 1); j++) {
		        	if(aval[j] > total/2 && aval[j] < total){
		        		color = "#00FF00";
		        	}else if(aval[j] < total/2 && aval[j] != 0) {
		        		color = "#FFFF00";
		        	}else if(aval[j] == 0) {
		        		color = "#FF0000";
		        	}
		        	let event = {start: days[j], end: days[j + 1], resourceId: 1, title: "There are " + aval[j] + " rooms available.", color: color};
		        	events.push(event);
	            }
		        return events;
		    }

		    function _pad(num) {
		        let norm = Math.floor(Math.abs(num));
		        return (norm < 10 ? '0' : '') + norm;
		    }
		  }
		  
		  function validateForm() {
			  var today = new Date();
			  var dd = today.getDate();
			  var mm = today.getMonth()+1; //January is 0 so need to add 1 to make it 1!
			  var yyyy = today.getFullYear();
			  if(dd<10){
			    dd='0'+dd
			  } 
			  if(mm<10){
			    mm='0'+mm
			  } 

			  let dd1 = today.getDate() + 1;
			  today = yyyy+'-'+mm+'-'+dd;
			  let tomorrow = yyyy+'-'+mm+'-'+dd1;
			  document.getElementById("arrivalDate").setAttribute("min", today);
			  document.getElementById("departureDate").setAttribute("min", tomorrow);
		  }
		  validateForm();
		  });
		</script>
		
		<script type="text/javascript">
			
		</script>
</body>
</html>