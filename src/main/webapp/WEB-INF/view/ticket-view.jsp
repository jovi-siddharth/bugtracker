<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="eratags" prefix="ytags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>
			View Ticket
		</title>
		<link type="text/css" rel="stylesheet"
			href="${pageContext.request.contextPath}/resources/css/style.css">
		<link type="text/css" rel="stylesheet"
			href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">		
	</head>
	<body>
		<div id="wrapper">
			<div id="header">
			 	<h2>Ticket View Page</h2>
			 	
			 	
			</div>
			<div id="container">
				<h3>Save Customer</h3>
			 		<table>
			 			<tbody>
			 				<tr>
			 					<td><label>Created By:</label></td>
			 					<td>${ticket.createdBy.userName }</td>
			 				</tr>
			 				<tr>
			 					<td><label>Assigned To:</label></td>
			 					<td>${ticket.assignedTo.userName}</td>
			 				</tr>
			 				<tr>
			 					<td><label>Created On Date: </label></td>
			 					<td>${ticket.createdOn}</td>
			 				</tr>
			 				
			 				<tr>
			 					<td><label>Last Modified Date:</label></td>
			 					<td>${ticket.lastModified}</td>
			 				</tr>
			 				<tr>
			 					<td><label>Priority:</label></td>
			 					<td>${ticket.priority.value}</td>
			 				</tr>
			 				<tr>
			 					<td><label>Status:</label></td>
			 					<td>${ticket.status.value}</td>
			 				</tr>
			 				<tr>
			 					<td><label>Bug : </label></td>
			 					<td>${ticket.bug}</td>
			 				</tr>
			 				<c:forEach var="comment" items="${ticket.comments }" >
			 				<tr>
			 					<td><label>Comment:</label></td>
			 					<td>${comment.content}</td>
			 				</tr>
			 				</c:forEach>
			 			</tbody>
			 		</table>
			 	<div style="clear;both;"></div>
			 	<c:url var="updateLink" value="/ticket/showFormForUpdate">
    				<c:param name="ticketId" value="${ticket.id}" />
    			</c:url>
			 	<p>
			 		<a href="${pageContext.request.contextPath}/ticket/">Back to Bug List</a>|<a href="${updateLink}">Update Ticket</a>
			 	</p>
			 	
			</div>
		</div>
	</body>
</html>