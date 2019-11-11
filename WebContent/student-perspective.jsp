<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Arrays,java.util.List"%>
<%@ page import="newpackage.Student"%>


<html>
<div id="bg">
	<head>
<title>Your semester results</title>
<link type="text/css" rel="stylesheet" href="css/view-student-style.css" />
<link type="text/css" rel="stylesheet" href="css/style.css" />

	</head>
	

		<%
			if (session.getAttribute("email1") == null) {
				response.sendRedirect(request.getContextPath() + "/index.jsp");
			}

		%>

<body>

		<form action="StudentControllerServlet">
					<input type="hidden" name="command" value="LOGOUT"/>
					<input type="submit" name="submit" class="add-semester-button" value="Log out"/>
					</form>
					
			<div id="header">
			<div id="wrapper">
				
				
				 	<h2>Semestral results of ${basicStudent.firstName}
					${basicStudent.lastName}</h2>
					
					
			</div>
			
		</div>
		
		


		<div id="container">
			<div id="content">
			
			

				<table>

					<tr>
						<th>School year</th>
						<th>Math</th>
						<th>Phisics</th>
						<th>English</th>
						<th>Biology</th>
						<th>Art</th>
						<th>History</th>
					</tr>

					<!-- Display semester results of the student -->
					<c:forEach var="tempResults" items="${RESULTS1}">
						<tr>
							<td>${tempResults.schoolYear}</td>
							<td>${tempResults.math}</td>
							<td>${tempResults.phisics}</td>
							<td>${tempResults.english}</td>
							<td>${tempResults.biology}</td>
							<td>${tempResults.art}</td>
							<td>${tempResults.history}</td>

						</tr>
					</c:forEach>
					<tr>
					<tr>
				</table>
			</div>
		</div>
	</body>
</div>
</html>