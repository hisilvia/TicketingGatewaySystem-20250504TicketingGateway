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
<title>MANAGER page</title>
</head>
	<body>
		<nav class="navbar navbar-dark bg-primary justify-content-between">
			   <a class="navbar-brand" href="/">MANAGER</a>
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
			         <a class="dropdown-item" href="/manager">Manager</a>
			         <a class="dropdown-item" href="/login?logout">Logout</a>
			       </div>
			     </li>
			   </ul>
			 </nav>	
			 <div class="d-grid gap-2 col-6 mx-auto">
				<div class="text-center">
					<h2>Hey ${message2}, welcome to MANAGER page!</h2>
					<br/><br/>
					<div>
						<form action="http://localhost:8282/allTicketsView" method="GET">
  			            	<button type="submit" class="btn btn-info">View Tickets</button><br/><br/>
						</form>
					<!--
						<form action="#" method="GET">
  			            	<button type="submit" class="btn btn-info">View Status</button><br/><br/>
						</form>
						-->
					</div>		
					<br/>
					<a href="/">Back To Home</a>
				</div>	
			 </div>	
		
	</body>
</html>				