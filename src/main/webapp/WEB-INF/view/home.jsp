<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
	<title>buddhaunderbonsai Company Home Page</title>
</head>

<body>
	<h2>buddhaunderbonsai Company Home Page</h2>
	<hr>
	
	<p>
	Welcome to the buddhaunderbonsai company home page!
	</p>
	
	<hr>
	
	<!-- display user name and role -->
	
	<p>
		User: <security:authentication property="principal.username" />
		<br><br>
		Role(s): <security:authentication property="principal.authorities" />
		<br><br>
		First name: ${user.firstName}, Last name: ${user.lastName}, Email: ${user.email}
	</p>
	
	<security:authorize access="hasRole('MANAGER')">
	
		<!-- Add a link to point to /leaders ... this is for the managers -->
		
	

	</security:authorize>	
	
	
	<security:authorize access="hasRole('ADMIN')">  

		<!-- Add a link to point to /systems ... this is for the admins -->
		
				
		<p>
			<a href="${pageContext.request.contextPath}/systems/updateUserRole">Add Roles To User</a>
			(Only Admins Allowed)
		</p>
		
		
		
	
	</security:authorize>
	
	<p>
			<a href="${pageContext.request.contextPath}/ticket/">View Bugs</a>
		</p>
	
	<hr>
	
	
	<!-- Add a logout button -->
	<form:form action="${pageContext.request.contextPath}/logout" 
			   method="POST">
	
		<input type="submit" value="Logout" />
	
	</form:form>
	
</body>

</html>









