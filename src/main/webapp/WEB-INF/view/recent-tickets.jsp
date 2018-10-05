<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 
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
 <div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<div class="panel-title">Raise Bug</div>
				</div>
				<div style="padding-top: 30px" class="panel-body">
					<!-- Registration Form -->
					<form:form action="${pageContext.request.contextPath}/ticket/searchTicket" 
						  	   modelAttribute="bugSearchForm"
						  	   class="form-horizontal"
						  	   method="POST">
					    <!-- Place for messages: error, alert etc ... -->
					    <div class="form-group">
					        <div class="col-xs-15">
					            <div>
									<!-- Check for registration error -->
									<c:if test="${registrationError != null}">
										<div class="alert alert-danger col-xs-offset-1 col-xs-10">
											${registrationError}
										</div>
									</c:if>
					            </div>
					        </div>
					    </div>
						<!-- User name -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
							<form:errors path="keyWord" cssClass="error" />
							<form:input path="keyWord" placeholder="Search Keyword (*)" class="form-control" />
						</div>
						<!-- Register Button -->
						<div style="margin-top: 10px" class="form-group">						
							<div class="col-sm-6 controls">
								<button type="submit" class="btn btn-primary">Search</button>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>


    <div id="header">
    	<h2>Bugs Viewer</h2>
    </div>
    
     <div id="container">
    	<div id="content">
    	<input type="button" value="Raise Bug"
    	    onclick="window.location.href='newTicketForm';return false;"
    	    class="add-button"
    	    />
    	<table>
    	<tr>
    			<th>Created By</th>
    			 <th>Assigned To</th>
    			 <th>Bug Statement</th>
    			 <th>Status</th>
    			 <th>Priority</th>
    			 <th>Created On</th>
    			 <th>Last Updated</th>
    			 <th>Action</th>
    		</tr>
    		<c:forEach var="tempTicket" items="${tickets}">
    		
    			<c:url var="updateLink" value="/ticket/showFormForUpdate">
    				<c:param name="ticketId" value="${tempTicket.id}" />
    			</c:url>
    			<c:url var="deleteLink" value="/ticket/delete">
    				<c:param name="ticketId" value="${tempTicket.id}" />
    			</c:url>
    			<c:url var="viewLink" value="/ticket/view">
    				<c:param name="ticketId" value="${tempTicket.id}" />
    			</c:url>
    			<c:url var="historyLink" value="/ticket/history">
    				<c:param name="ticketId" value="${tempTicket.id}" />
    			</c:url>
    			<tr>
    				<td>${tempTicket.createdBy.userName }</td>
    				<td>${tempTicket.assignedTo.userName }</td>
    				<td>${tempTicket.bug }</td>
    				<td>${tempTicket.status.value}</td>
    				<td>${tempTicket.priority.value }</td>
    				<td>${tempTicket.createdOn }</td>
    				<td>${tempTicket.lastModified }</td>
    				<td>
    					<a href="${viewLink}">View</a>
    					|
    					<a href="${deleteLink}" onclick="if(!confirm('Are you sure you want to delete this ticket?' ))" >Delete</a>
    					|
    					<a href="${updateLink}">Update</a>
    					|
    					<a href="${historyLink}">History</a>
    				</td>
    			</tr>
    		</c:forEach> 
    		</table> 
    	</div>
    </div>
    
    
</div>

 </body>
 </html>