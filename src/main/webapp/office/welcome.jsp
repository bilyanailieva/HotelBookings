<jsp:include page="Snippets/header.jsp" /> 

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
</div>
<jsp:include page="Snippets/footer.jsp" /> 
