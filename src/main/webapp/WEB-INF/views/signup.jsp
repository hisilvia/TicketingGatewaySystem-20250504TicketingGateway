<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
	<body >
		<div class="container">

		    <div id="loginbox" style="margin-top: 50px;"
		         class="col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">

		        <div class="card border-info">

		            <div class="card-header bg-info">
		                New User Registration
		            </div>

		            <div class="card-body">

		                <div class="card-text">
							
							<!-- Registration Form -->
							<form action='/signup' method='post' class="form-horizontal">
								
								<!-- Place for messages: error, alert etc ... -->
								<!--need to do it?????-->
								
								<div style="margin-bottom: 5px" class="form-group">
									<label for="username">Employee Name:</label>
									<input type='text' name='username' class="form-control"/></br>
								</div>
								
								<div style="margin-bottom: 5px" class="form-group">
									<label for="email">Email:</label>
									 <input type='text' name='email' class="form-control"/></br>
								</div>
								
								<div style="margin-bottom: 5px" class="form-group">
									<label for="password">Password: </label>
									<input type='text' name='password' class="form-control"/></br>
								</div>
								
								<div style="margin-bottom: 5px" class="form-group">
									<label for="department">Department: </label>
									<input type='text' name='department' class="form-control"/></br>
								</div>
								
								<div style="margin-bottom: 5px" class="form-group">
									<label for="project">Project: </label>
									<input type='text' name='project' class="form-control"/></br>
								</div>
								
								<div style="margin-bottom: 5px" class="form-group">
									<label for="managerId">ManagerId: </label>
									<input type='text' name='managerId' class="form-control"/></br>
								</div>								
								
								<!--
								<div style="margin-bottom: 5px" class="form-group">
									<p>Role: </p>
									
									<input type='checkbox' id="user" name='role' class="form-control"/>
									<label for="user">USER</label>
									
									<input type='checkbox' id="manager" name='role' class="form-control"/>
									<label for="manager">MANAGER</label>
									
									<input type='checkbox' id="admin" name='role' class="form-control"/>
									<label for="admin">ADMIN</label>
								</div>
								-->
								
								<!-- Registration Button -->
								<div style="margin-top: 10px" class="form-group">
									<div class="col-sm-6 controls">
										<input type='submit' value='SIGNUP' id='signup'>
									</div>
								</div>
							</form>
							
						</div>

		            </div>

		        </div>
		    </div>

		</div>		
		
		
		
		
		
		
		
		
		
		
		
		
	</body>
</html>