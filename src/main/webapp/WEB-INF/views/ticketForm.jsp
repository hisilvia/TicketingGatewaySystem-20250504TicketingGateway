<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<style></style>
	<title>Ticket Form</title>
	
	</head>
	<body>
		<h2>Create a New Ticket</h2><br/>
		<!-- enctype="multipart/form-data" to handle file uploads correctly  -->
		<form:form modelAttribute="ticket" method="post" action="submitTicket" enctype="multipart/form-data" >
			
			<form:label path="title">Title</form:label>
			<form:input path="title"/>  <br/><br/>
			
			<form:label path="description">Description</form:label>
			<form:textarea path="description"/> <br/><br/>
			
			<form:label path="createdBy">CreatedBy</form:label>
			<form:input path="createdBy"/> <br/><br/>
			
			<form:label path="assignee">Assignee</form:label>
			<form:input path="assignee"/> <br/><br/>
			
			<form:label path="priority">Priority:</form:label>
	        <form:select id="priority" path="priority">
	            <form:option value="LOW">LOW</form:option>
	            <form:option value="MEDIUM">MEDIUM</form:option>
	            <form:option value="HIGH">HIGH</form:option>
			</form:select>	
			<br/><br/>
			
			<form:label path="status">Status:</form:label>
	        <form:select id="status" path="status">
	            <form:option value="OPEN">OPEN</form:option>
	            <form:option value="PENDING_APPROVAL">PENDING_APPROVAL</form:option>
	            <form:option value="APPROVED">APPROVED</form:option>
				<form:option value="REJECTED">REJECTED</form:option>
				<form:option value="ASSIGNED">ASSIGNED</form:option>
				<form:option value="RESOLVED">RESOLVED</form:option>
				<form:option value="CLOSED">CLOSED</form:option>
				<form:option value="REOPENED">REOPENED</form:option>							
			</form:select>
			<br/><br/>
			
			<form:label path="creationDate">CreationDate</form:label>
			<form:input type="date" path="creationDate"/>
			<br/><br/>
			
			<form:label path="category">Category</form:label>
			<form:input path="category"/>
			<br/><br/>
			
			<form:label path="fileAttachementPath">FileAttachmentPath</form:label>
			<form:input type="file" path="fileAttachementPath"/>  
			<br/><br/>
			
			<input type='submit' id='add' value='Submit'/>
			</br></br>
			
			<a href="/home">Back To Home</a>
		</form:form>
		
	</body>
</html>