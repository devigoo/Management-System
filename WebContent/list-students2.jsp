<!-- dont have to import package when using jstl -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<div id="bg">
<head>
<title>Maritime university of Szczecin</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>



<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // http 1.1
		response.setHeader("Pragma", "no-cache"); // http 1.0
		response.setHeader("Expires", "0"); // proxies

			
 %>

	<div id="wrapper">
		<div id="header">
			<h2>Maritime university of Szczecin</h2>
		</div>
	</div>
	


	
	<div id="container">
		<div id="content">

			<form action="StudentControllerServlet" method="GET">
				<input type="hidden" name="command" value="SEARCH" /> <b>Search
				student:</b> <input type="text" name="theSearchName" /> <input
					type="submit" value="SEARCH" class="add-student-button" />
			</form>
			<br>

			<table>
				<tr>
					<input type="button" value="Add Student"
						onclick="window.location.href='add-student-form.jsp'; return false;"
						class="add-student-button" />



					<form action="StudentControllerServlet">
						<input type="hidden" name="command" value="LOGOUT" /> <input
							type="submit" value="Log out" class="logout" />
					</form>
				</tr>

			</table>


			<table>

				<tr>
					<th>Id</th>
					<th>First name</th>
					<th>Last name</th>
					<th>Email</th>
					<th>Action</th>

				</tr>

				<c:forEach var="tempStudent" items="${STUDENT_LIST}">
				
			
				<!-- Set up an update link for each student -->
					<c:url var="tempLink" value="StudentControllerServlet">
					<c:param name="command" value="LOAD" />
					<c:param name="studentId" value="${tempStudent.id}" />
				</c:url>
					
				<c:url var="seeMoreLink" value="StudentControllerServlet">
				<c:param name="command" value="LOADSTUDENTDATA"/>
						<c:param name="studentId" value="${tempStudent.id}"/>
						</c:url>
		
					<!-- Set up a delete link for each student -->
					<c:url var="deleteLink" value="StudentControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="studentId" value="${tempStudent.id}" />
					</c:url>
					
					<tr>
						<td>${tempStudent.id}</td>
						<td>${tempStudent.firstName}</td>
						<td>${tempStudent.lastName}</td>
						<td>${tempStudent.email}</td>
						<td><a href="${seeMoreLink}"><font color="black">More</font></a> |
						<a href="${tempLink}"><font color="black">Update personal data</font></a> |
						<a href="${deleteLink}" onclick="if(!(confirm('Are you sure you want to delete this student?')))return false">
						<font color="black">Delete</font></a>
						</td>

					</tr>

				</c:forEach>
			</table>
		</div>
	</div>
</body>
</div>
</html>