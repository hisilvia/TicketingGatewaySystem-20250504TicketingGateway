<!DOCTYPE html>
<html>
  <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>  
  <%@ page isELIgnored="false" %> 
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
  <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
  <head>
    <script
      src="https://code.jquery.com/jquery-3.6.3.min.js"
      integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="
      crossorigin="anonymous"
    ></script>
    <link
      rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
    />
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
    />
    <meta charset="UTF-8" />
    <title>Ticketing System</title>
    <style>
      .highlighter {
        display: inline-block;
        background-color: #a8a8a8;
        margin: 15px 0px;
        padding: 5px 10px;
        color: white;
      }

      .link {
        display: block;
        width: 100px;
        font-weight: 500;
      }
    </style>
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
	         <a class="dropdown-item" href="/home">Home</a>
	         <a class="dropdown-item" href="/login?logout">Logout</a>
	       </div>
	     </li>
	   </ul>
	   <!--
		<form:form action="${pageContext.request.contextPath}/logout" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<button class="btn btn-outline-success" type="submit">Logout</button>

		</form:form>
	   -->
	 </nav>	
		
		
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
					  <security:authorize access="hasAuthority('MANAGER')">
  			            <button type="button" class="btn btn-info">MANAGER</button>
  			          </security:authorize>
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


