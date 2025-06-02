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
		
<title>View All Tickets</title>
</head>
	<body>
		<nav class="navbar navbar-dark bg-primary justify-content-between">
		   <a class="navbar-brand" href="/">Home</a>
		   <ul class="navbar-nav">
		     <li class="nav-item dropdown">
		       <a
		         class="nav-link dropdown-toggle"
		         href="#"
		         id="navbarDropdownMenuLink"
		         data-toggle="dropdown"
		         aria-haspopup="true"
		         aria-expanded="false"
		       >
		         <security:authorize access="isAuthenticated()">
		           Welcome,<span
		             id="username"
		             class="font-weight-bold font-italic ml-1"
		             ><security:authentication property="principal.username"
		           /></span>
		         </security:authorize>
		       </a>
		       <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
		         <a class="dropdown-item" href="/login?logout">Logout</a>
		       </div>
		     </li>
		   </ul>
		 </nav>	
			 
		 <div class="d-grid gap-2 col-6 mx-auto">
			<div class="text-center">
				<security:authorize access="isAuthenticated()">
			         <br/>
					  <security:authorize access="hasAuthority('MANAGER')">
						<h2>MANAGER View All Tickets</h2>
  			          </security:authorize>
			          <security:authorize access="hasAuthority('ADMIN')">
						<h2>ADMIN View All Tickets</h2>
			          </security:authorize>
		        </security:authorize>
				
				<br/><br/>
				<table id ="myTable" border="1" width="100%" cellpadding="8">
				    <thead>
				        <tr>
				            <th>ID</th>
				            <th>Title</th>
				            <th>Priority</th>
				            <th>Status</th>
							<th>CreatedBy</th>
				            <th>Assignee</th>
							<th>Files</th>
							<th>Actions</th>
							<th>History</th>
							
				        </tr>
				    </thead>
					<tbody>
						<c:forEach var="ticket" items="${ticketList}" >
							<tr id="row->${ticket.id}">
								<td>${ticket.id}</td>
								<td>${ticket.title}</td>
								<td>${ticket.priority}</td>
								<td id="status">${ticket.status}</td>
								<td>${ticket.createdBy}</td>
								<td id="assignee"><c:out value="${ticket.assignee}" default="Not Yet" /></td>
								<td>									
									<ul>
								    <c:forEach var="file" items="${ticket.fileAttachementPath}">
								        <li><a href="/download/${file}">${file}</a></li>
								    </c:forEach>
									</ul>
								</td>
								<td class="updateStatus">
									<div class="actionButtons">
										<security:authorize access="isAuthenticated()">
									        
											  <security:authorize access="hasAuthority('MANAGER')">
												<!--
												<button type="button" data-id="${ticket.id}" onclick="updateData()" class="update-btn btn btn-info">APPROVAL</button><br/>
												-->
												
												<form action="http://localhost:8282/manager/update/approve/${ticket.id}" method="POST">
													<button type="submit" class="btn btn-primary">APPROVAL</button><br/>
												</form>
												
												<br/>
												<form action="http://localhost:8282/manager/update/reject/${ticket.id}" method="POST">
													<button type="submit" class="btn btn-warning">REJECTION</button>
												</form>
						  			          </security:authorize>
									          <security:authorize access="hasAuthority('ADMIN')">
												<form action="http://localhost:8282/admin/update/resolve/${ticket.id}" method="POST">
												<button type="submit" class="btn btn-success">RESOLUTION</button>
												</form>
									          </security:authorize>
								        </security:authorize>
										
									</div>
								</td>
								<td>
									<form action="http://localhost:8282/ticketHistory/${ticket.id}" method="GET">
										<button type="submit" class="btn btn-info">View History</button>
									</form>
								</td>	
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<br/><br/>
			</div>					
				<br/>
				<a href="/">Back To Home</a>
		 </div>
	</body>
</html>		