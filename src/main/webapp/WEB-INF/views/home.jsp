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
<title>Home</title>
</head>
	<body>
		<div class="d-grid gap-2 col-6 mx-auto">
			<h2>${message1}: <span>${message2}</span></h2><br/>
 			<div class="text-center">
				Welcome <br/>
				<!-- display user name and role -->
			    <p>
			        User: <span>
								<security:authentication property="principal.username" /><br/><br/>
					  	  </span>
			          		
			        Role(s): <span>
								<security:authentication property="principal.authorities" /><br/><br/>
				  			 </span>
			    </p>
				<hr/>
				<security:authorize access="isAuthenticated()">
			          Welcome
			          <span id="username">
			            <security:authentication property="principal.username" /><br/><br/>
						<button type="button" class="btn btn-primary">USER</button><br/><br/>
			          </span>
			          <security:authorize access="hasAuthority('ADMIN')">
			            <button type="button" class="btn btn-info">ADMIN</button>
			          </security:authorize>
		        </security:authorize>
				<br/><br/>
				<form:form action="${pageContext.request.contextPath}/logout" method="post">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<input type="submit" value="Logout">	
				</form:form>	
			</div>
	   </div>
	   
	</body>
</html>


