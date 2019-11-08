<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Arrays,java.util.List"%>
<%@ page import="newpackage.SemesterResults"%>


<html>
<div id="bg">
<head>
<title>View Student</title>
<link type="text/css" rel="stylesheet" href="css/view-student-style.css" />
<link type="text/css" rel="stylesheet" href="css/style.css" />

</head>

<body>


	<%!int id;%>
	<%
	
		id= Integer.parseInt(request.getParameter("studentId"));

		
	%>


	<div id="wrapper">

		<div id="header">
			<h2>Student's semestral results</h2>
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



				<c:forEach var="tempResults" items="${RESULTS}">


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
					<td>
								<!-- Here passing parameter with a value of id of the student to assign it to the new semester -->
					 
						<form action="add-semester.jsp">

							<input type="hidden" name="id"  value=<%=id%>> <input
								type="submit" value="Add semester">
						</form>

					</td>
				<tr>
			</table>

			<a href="StudentControllerServlet"><font color="black"><b>Back</b></font></b></a>



		</div>
	</div>
</body>
</div>
</html>