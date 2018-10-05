<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="eratags" prefix="ytags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>
			Update Ticket Form
		</title>
		<link type="text/css" rel="stylesheet"
			href="${pageContext.request.contextPath}/resources/css/style.css">
		<link type="text/css" rel="stylesheet"
			href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">		
	</head>
	<body>
		<div id="wrapper">
			<div id="header">
			 	<h2>Ticket Update Form</h2>
			 	
			 	
			</div>
			<div id="container">
				<h3>Save Customer</h3>
				<form:form action="updateTicket" modelAttribute="ticket" method="POST">
					<form:hidden path="id" />
			 		<table>
			 			<tbody>
			 				<tr>
			 					<td><label>Created By:</label></td>
			 					<td><form:input path="createdBy" /></td>
			 				</tr>
			 				<tr>
			 					<td><label>Assigned To:</label></td>
			 					<td><form:input path="assignedTo" /></td>
			 				</tr>
			 				<tr>
			 					<td><label>Created On: </label></td>
			 					<td><form:input path="createdOn"   /><form:errors path="createdOn" cssClass="error" /></td>
			 				</tr>
			 				
			 				<tr>
			 					<td><label>Last Modified:</label></td>
			 					<td><form:input path="lastModified"  /><form:errors path="lastModified" cssClass="error" /></td>
			 				</tr>
			 				<tr>
			 					<td><label>Priority:</label></td>
			 					<td><form:select path="priority" items="${priorities }" itemLable="value" itemValue="value"/></td>
			 				</tr>
			 				<tr>
			 					<td><label>Status:</label></td>
			 					<td><form:select path="status" items="${statuses }" itemLable="value" itemValue="value"/></td>
			 				</tr>
			 				<tr>
			 					<td><label>Bug:</label></td>
			 					<td><form:textarea path="bug" placeholder="Bug Description (*)"  
										size="200" maxlength="200" rows="5"/></td>
			 				</tr>
			 				<tr>
			 					<td><label>Add Comment:</label></td>
			 					<td><input type="text" name="comment" placeholder="Add a Comment"  
										size="200" maxlength="200"/></td>
			 				</tr>
			 				<tr>
			 					<td><label></label></td>
			 					<td><input type="submit" value="Save" class="save" /></td>
			 				</tr>
			 			</tbody>
			 		</table>
			 	</form:form> 	
			 	<div style="clear;both;"></div>
			 	<p>
			 		<a href="${pageContext.request.contextPath}/ticket/">View Bugs</a>
			 	</p>
			 	
			</div>
		</div>
	</body>
</html>