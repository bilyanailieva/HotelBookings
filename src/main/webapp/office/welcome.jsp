<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@page import="com.bookings.mvc.bean.RoomTypeBean"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   
<!doctype html>
<html>
<head>
	<%@ include file="Snippets/head.jsp" %>  
	<title>Settings</title>
	<link rel="stylesheet" href="/HotelBookings/src/main/webapp/assets/css/base/index.scss" />
	<link  rel='stylesheet'  href='https://cdn.jsdelivr.net/npm/gantt-schedule-timeline-calendar@3.7.1/dist/style.css'>

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
<input type="hidden" id="rooms" name="rooms" data-textxml="${listRoom }"/>
		<div id="calendar"></div>
  <script>
	
		/* let ec = new EventCalendar(document.getElementById('calendar'), {
		    view: 'timeGridDay',
		    height: '800px',
	        headerToolbar: {
	            start: 'prev,next today',
	            center: 'title',
	            end: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek resourceTimeGridDay resourceTimeGridWeek '
	        },
	        dayHeaderFormat: {weekday: 'narrow', day: 'numeric'},
	        slotLabelFormat: {weekday: 'narrow', day: 'numeric'},
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
	    } */
		</script> 

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
<script src="https://cdn.jsdelivr.net/npm/gantt-schedule-timeline-calendar"></script>
<script>
const GSTCID = GSTC.api.GSTCID;
const date = GSTC.api.date;

const rowsFromDB = [
	<c:forEach var="row" items="${listRoom}">
    {id: '<c:out value="${row.id}" />',
     label: '<c:out value="${row.name}" />'},
	</c:forEach>
];

	const itemsFromDB = [
	  {
	    id: '1',
	    label: 'Item 1',
	    rowId: '1',
	    time: {
	      start: GSTC.api.date('2020-01-01').startOf('day').valueOf(),
	      end: GSTC.api.date('2020-01-02').endOf('day').valueOf(),
	    },
	  },
	  {
	    id: '2',
	    label: 'Item 2',
	    rowId: '1',
	    time: {
	      start: GSTC.api.date('2020-02-01').startOf('day').valueOf(),
	      end: GSTC.api.date('2020-02-02').endOf('day').valueOf(),
	    },
	  },
	  {
	    id: '3',
	    label: 'Item 3',
	    rowId: '2',
	    time: {
	      start: GSTC.api.date('2021-01-15').startOf('day').valueOf(),
	      end: GSTC.api.date('2021-01-20').endOf('day').valueOf(),
	    },
	  },
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
		
		  list: {
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
</script>
</body>
</html>
