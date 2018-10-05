<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="eratags" prefix="ytags" %>

 
 <!DOCTYPE html>
 
 <html>
 <head>
 
 <title>Ticket Window</title>
 <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<!-- Reference Bootstrap files -->
	<link rel="stylesheet"
		 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
		<style>
		.error {color:red}
	</style>
 <link type="text/css"
       rel="stylesheet"
       href="<c:url value="/resources/css/style.css" />" />
 </head>
 <body>
 <div id="wrapper">
     <div id="header">
    	<h2>Bugs Viewer</h2>
    </div>
    
     <div id="container">
    	<div id="content">
    	<table>
    	<tr>
    			 <th>Assigned To</th>
    			 <th>Bug Statement</th>
    			 <th>Status</th>
    			 <th>Priority</th>
    			 <th>Created On</th>
    			 <th>Last Updated</th>
    			 <th>Modified By</th>
    			 <th>Modification Type</th>
    		</tr>
    		<c:forEach var="hisObj" items="${hisList}">
    		
    			<tr>
    				<td>${hisObj.ticket.assignedTo.userName }</td>
    				<td>${hisObj.ticket.bug }</td>
    				<td>${hisObj.ticket.status.value}</td>
    				<td>${hisObj.ticket.priority.value }</td>
    				<td>${hisObj.ticket.createdOn }</td>
    				<td><ytags:formatTS value="${hisObj.ent.timestamp}" /></td>
    				<td>${hisObj.ent.username}</td>
    				<td>${hisObj.changeType }</td>
    			</tr>
    		</c:forEach> 
    		</table> 
    	</div>
    </div>
    
    
</div>

 </body>
 </html>