<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.bookings.mvc.bean.RoomTypeBean"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>
<head>
<%@ include file="Snippets/head.jsp"%>
<title>Calendar</title>
<link rel="stylesheet"
	href="/HotelBookings/src/main/webapp/assets/css/base/index.scss" />
<link rel='stylesheet'
	href='https://cdn.jsdelivr.net/npm/gantt-schedule-timeline-calendar@3.7.1/dist/style.css'>
<script
	src="http://cdn.jsdelivr.net/npm/gantt-schedule-timeline-calendar"
	type="module"></script>
</head>
<body>
	<%@ include file="Snippets/header.jsp"%>
	<div class="row">
		<%@ include file="Snippets/side-menu.jsp"%>
		<main class="col-md-9 ml-sm-auto col-lg-10 px-md-4" role="main">

			<input type="hidden" id="rooms" name="rooms"
				data-textxml="${listRoom }" />

			<div id="calendar"></div>


			<div class="col-md-12">
				<input type="hidden" value="javascript: date" name="currentDate"
					id="currentDate" />
				<table class="ui compact celled definition table">
					<thead>
						<tr>
							<th colspan="7"><h3>Confirmed reservations with
									unassigned rooms</h3></th>
						</tr>
						<tr>
							<th></th>
							<th>ID</th>
							<th>Number of Required Rooms</th>
							<th>Assigned Rooms</th>
							<th>Customer Name</th>
							<th>Arrival Date</th>
							<th>Departure Date</th>
							<th>Status</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${listUnassigned.size() == 0 }">
							<tr>
								<td colspan="7">No unassigned reservations.</td>
							</tr>
						</c:if>

						<c:forEach var="reservation" items="${listUnassigned }"
							varStatus="loopStatus">
							<tr>
								<td class="collapsing">
									<!-- <div class="ui fitted slider checkbox">
										<input type="checkbox"> <label></label>
									</div> -->
								</td>
								<td><c:out value="${reservation.res_id}" /></td>
								<td><c:out value="${reservation.number_of_rooms}" /></td>
								<td><c:set var="assigned"
										value="${listAssigned[loopStatus.index] }" /> <c:if
										test="${listAssigned[loopStatus.index].size() == 0 }">
									No assigned rooms yet.
					</c:if> <c:forEach var="room" items="${assigned }" varStatus="loop">
										<c:out value='${room }' />
										<c:if test="${!loop.last}">,</c:if>
									</c:forEach></td>


								<td><c:out
										value="${reservation.customer.fname} ${reservation.customer.lname}" /></td>
								<td><c:out value="${reservation.res_start}" /></td>
								<td><c:out value="${reservation.res_end}" /></td>
								<td>
									<form action="update-status">
										<div class="form-group">
											<select class="statusChanger" id="currentStatus"
												onChange="changeStatus('${reservation.res_id}')"
												name="currentStatus">
												<c:forEach var="status" items="${statuses}">
													<c:choose>
														<c:when
															test="${status.status_id eq reservation.status.status_id}">
															<option id="selectedStatus"
																value="<c:out value='${status.status_id}'/>" selected>
																<c:out value='${status.status}' /></option>
														</c:when>
														<c:otherwise>
															<option value="<c:out value='${status.status_id}'/>">
																<c:out value='${status.status}' /></option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</select>
										</div>
									</form>
								</td>
								<td><a
									href="assign-form?res_id=<c:out value='${reservation.res_id}' />">Assign
										Rooms</a></td>
								<td><a
									href="view?res_id=<c:out value='${reservation.res_id}' />">View
										Extra Information</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</main>
	</div>
	<%@ include file="Snippets/footer.jsp"%>
	<script
		src="https://cdn.jsdelivr.net/npm/gantt-schedule-timeline-calendar@3.7.1/dist/gstc.umd.min.js"></script>

	<!-- <script
		src="https://cdn.jsdelivr.net/npm/gantt-schedule-timeline-calendar"></script> -->
	<!-- 		<script type="module" src="https://cdn.jsdelivr.net/npm/gantt-schedule-timeline-calendar@3.15.8/dist/gstc.esm.min.js"></script>
 -->
	<script>
	
const GSTCID = GSTC.api.GSTCID;
const date = GSTC.api.date;

const rowsFromDB = [
	<c:forEach var="row" items="${listRoom}">
    {id: '<c:out value="${row.id}" />',
     label: '<c:out value="${row.name}" />'},
	</c:forEach>
];

var last = new Date('<c:out value="${row.res_end}" />');
last.setDate(last.getDate()-1);
var i = 1; 

const itemsFromDB = [
	<c:forEach var="row" items="${listConfirmed}">
    {id: i++,
     label: 'Reservation ID: <c:out value="${row.res_id}" />',
     rowId: '<c:out value="${row.room.id}" />',
     time: {
	      start: GSTC.api.date('<c:out value="${row.res_start}" />').startOf('day').valueOf(),
	      end: GSTC.api.date('<c:out value="${row.res_end}" />').subtract(1, 'day').endOf('day').valueOf(),
	    },
     },
	</c:forEach>
];

	const columnsFromDB = [
	  {
	    id: 'id',
	    label: 'ID',
	    data: ({ row }) => GSTC.api.sourceID(row.id), // show original id (not internal GSTCID)
	    sortable: ({ row }) => Number(GSTC.api.sourceID(row.id)), // sort by id converted to number
	    width: 80,
	    header: {
	      content: 'ID',
	    },
	  },
	  {
	    id: 'label',
	    data: 'label',
	    sortable: 'label',
	    isHTML: false,
	    width: 230,
	    header: {
	      content: 'Label',
	    },
	  },
	];
	
	function fromArray(array) {
		  const GSTCID = GSTC.api.GSTCID; // [IMPORTANT] every id must be wrapped by this function
		  const resultObj = {};
		  for (const item of array) {
		    item.id = GSTCID(item.id);
		    if ('rowId' in item) {
		      item.rowId = GSTCID(item.rowId);
		    }
		    if ('parentId' in item) {
		      item.parentId = GSTCID(item.parentId);
		    }
		    resultObj[item.id] = item;
		  }
		  return resultObj;
		}
	
const config = {
		  /* ... */
		  licenseKey: '====BEGIN LICENSE KEY====\nY4VaZwXaaYJXC1hLjiGZJHQt1zRfdQj1QEL73KClIYPbw9PfNp1FPlyVm4FHiaXWtJACNEmiTwH3HzHLDMGHdBkA+5PiMMu/OY4d6u1LXAwNh26JrRP59qqc1hTA7gui++Glz4XTznVPmRC1Bs2C5CtBmTeiKNNExqSd7PugwNV28KVe9auR4tBXGqmENpZyF7q+QNYXC/0uILUuvUzvRrfMDErX3lT/RoQ7rQC7W0+6NYnziwXOqE5Lq+nMSXO/9LtPJqhyTDj0DydgkNW5zHl+wdkk8nFusQDBDd+3FO+lwP1CuWMffnoxGx2L/aD2nENdcGVNyxY8XehD/B9MhA==||U2FsdGVkX19vaQ580BkbAOfSOS595HbpO9D8VdrVtmWBf2qfBlCTag9NtETnGNndvNoD3ocGxC/SRz9Loq1O8G+vK1Wxznhkw4UueoCsgG8=\nKKYvwtCmceDRNEnqgjluakYAQ7+FHWvh8A7tAnQh88UslQwxbo6BrWPhazBUIFF+ui980Q02QsK69oGO2PEvQ1nZhMBNzMJS8dUyuzkRDR2RoluMPY0/40B9SFuNgYV0rewqkFm6aY0TgNo2DX80hYG7j6NjGtcZN+e15nnM8IN4lvPxQ1QqmIwTYO+s49uLoJSAUT2OuWV/1PpZHxxbOFo4QWQBdJ54LwS7Wcepr49aq0ypZ+HvuYPplC/7B/KRoGya19ZljBerFSYaExF5eOWFlj/bN6NjN6u+C65QjSHhjzxBLD3Hyxy+MzNK5zgnnVKJ2rIHVsQbitzg1CUxNQ==\n====END LICENSE KEY====',
		
	/* 	  licenseKey: '====BEGIN LICENSE KEY====\nY4VaZwXaaYJXC1hLjiGZJHQt1zRfdQj1QEL73KClIYPbw9PfNp1FPlyVm4FHiaXWtJACNEmiTwH3HzHLDMGHdBkA+5PiMMu/OY4d6u1LXAwNh26JrRP59qqc1hTA7gui++Glz4XTznVPmRC1Bs2C5CtBmTeiKNNExqSd7PugwNV28KVe9auR4tBXGqmENpZyF7q+QNYXC/0uILUuvUzvRrfMDErX3lT/RoQ7rQC7W0+6NYnziwXOqE5Lq+nMSXO/9LtPJqhyTDj0DydgkNW5zHl+wdkk8nFusQDBDd+3FO+lwP1CuWMffnoxGx2L/aD2nENdcGVNyxY8XehD/B9MhA==||U2FsdGVkX19vaQ580BkbAOfSOS595HbpO9D8VdrVtmWBf2qfBlCTag9NtETnGNndvNoD3ocGxC/SRz9Loq1O8G+vK1Wxznhkw4UueoCsgG8=\nKKYvwtCmceDRNEnqgjluakYAQ7+FHWvh8A7tAnQh88UslQwxbo6BrWPhazBUIFF+ui980Q02QsK69oGO2PEvQ1nZhMBNzMJS8dUyuzkRDR2RoluMPY0/40B9SFuNgYV0rewqkFm6aY0TgNo2DX80hYG7j6NjGtcZN+e15nnM8IN4lvPxQ1QqmIwTYO+s49uLoJSAUT2OuWV/1PpZHxxbOFo4QWQBdJ54LwS7Wcepr49aq0ypZ+HvuYPplC/7B/KRoGya19ZljBerFSYaExF5eOWFlj/bN6NjN6u+C65QjSHhjzxBLD3Hyxy+MzNK5zgnnVKJ2rIHVsQbitzg1CUxNQ==\n====END LICENSE KEY====',
	 */	  list: {
			    columns: {
			      data: GSTC.api.fromArray(columnsFromDB),
			    },
			    rows: GSTC.api.fromArray(rowsFromDB),
			  },
			  chart: {
			    items: GSTC.api.fromArray(itemsFromDB),
			  },
};

		const state = GSTC.api.stateFromConfig(config);

		const app = GSTC({
		  element: document.getElementById('calendar'),
		  state,
		}); 
		
		function changeStatus(res_id) {
			e = document.getElementById("currentStatus");
			var selected = e.options[e.selectedIndex].value;
			console.log(res_id, selected);
			$.ajax({
				url : "change-status",
				type : "post",
				data : {
					res_id : res_id,
					currentStatus : selected
				},
				success : function() {
					console.log('Success!');
				},
				error : function() {
				}
			});
		};
</script>
</body>
</html>
