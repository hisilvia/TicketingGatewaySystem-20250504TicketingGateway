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
		
<title>View Ticket</title>
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
			         <a class="dropdown-item" href="/user">User</a>
			         <a class="dropdown-item" href="/login?logout">Logout</a>
			       </div>
			     </li>
			   </ul>
			 </nav>	
			 <div class="d-grid gap-2 col-6 mx-auto">
				<div class="text-center">
					<br/><br/>
					<h2>User View Your Tickets</h2>
					<table border="1" width="100%" cellpadding="8">
					    <thead>
					        <tr>
					            <th>ID</th>
					            <th>Title</th>
					            <th>Priority</th>
					            <th>Status</th>	
								<th>CreatedBy</th>							
					            <th>Assignee</th>
								<th>Files</th>
					            <th>Action</th>
					        </tr>
					    </thead>
						<tbody>
							<c:forEach var="ticket" items="${ticketList}" >
								<tr>
									<td>${ticket.id}</td>
									<td>${ticket.title}</td>
									<td>${ticket.priority}</td>
									<td>${ticket.status}</td>
									<td>${ticket.createdBy}</td>
									<td><c:out value="${ticket.assignee}" default="Not Yet" /></td>
									<td>									
										<ul>
									    <c:forEach var="file" items="${ticket.fileAttachementPath}">
									        <li><a href="/download/${file}">${file}</a></li>
									    </c:forEach>
										</ul>
									</td>
									<td>
										<div class="actionButtons">		
											<form action="http://localhost:8282/user/update/reopen/${ticket.id}" method="POST">
												<button type="submit" class="btn btn-info">REOPEN</button><br/>
											</form>
											<br/>
											<form action="http://localhost:8282/user/update/close/${ticket.id}" method="POST">
												<button type="submit" class="btn btn-info">CLOSE</button><br/>
											</form>
											<br/>
					  			          	<form action="http://localhost:8282/ticketHistory/${ticket.id}" method="GET">
											<button type="submit" class="btn btn-info">View History</button><br/>
											</form>
											<br/>
										</div>
									</td>	
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
						
					<br/>
					<a href="/">Back To Home</a>
				</div>	
			 </div>	
		
	</body>
</html>		