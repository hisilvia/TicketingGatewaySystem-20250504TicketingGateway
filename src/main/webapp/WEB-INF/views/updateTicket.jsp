<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<head>
<meta charset="UTF-8">
		
<title>Update Ticket</title>
</head>
	<body>
		<h2>Update.....</h2>
		<table id ="myTable" border="1" width="100%" cellpadding="8">
		    <thead>
		        <tr>
		            <th>ID</th>
		            <th>Title</th>
		            <th>Priority</th>
		            <th>Status</th>
		            <th>Assignee</th>
					<th>Actions</th>
					
		        </tr>
		    </thead>
			<tbody>
				<c:forEach var="ticket" items="${ticketList}" >
					<tr id="row->${ticket.id}">
						<td>${ticket.id}</td>
						<td>${ticket.title}</td>
						<td>${ticket.priority}</td>
						<td id="status">${ticket.status}</td>
						<td id="assignee"><c:out value="${ticket.assignee}" default="Not Yet" /></td>
						<td class="updateStatus">
							<div class="actionButtons">
								<security:authorize access="isAuthenticated()">
							        
									  <security:authorize access="hasAuthority('MANAGER')">
										<!--
										<button type="button" data-id="${ticket.id}" onclick="updateData()" class="update-btn btn btn-info">APPROVAL</button><br/>
										-->
										
										<form action="http://localhost:8282/manager/update/approve/${ticket.id}" method="POST">
											<button type="submit" class="btn btn-info">APPROVAL</button><br/>
										</form>
										
										<br/>
										<form action="http://localhost:8282/manager/update/reject" method="GET">
											<button type="submit" class="btn btn-info">REJECTION</button>
										</form>
				  			          </security:authorize>
							          <security:authorize access="hasAuthority('ADMIN')">
										<form action="#localhost:8282/manager/update/reject" method="PUT">
										<button type="submit" onclick="#" class="btn btn-info">RESOLUTION</button>
										</form>
							          </security:authorize>
						        </security:authorize>
								
							</div>
						</td>	
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br/><br/>
	</body>
</html>	