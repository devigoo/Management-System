<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<title>Add new semester</title>
<link type="text/css" rel="stylesheet" href="css/style.css"/>
<link type="text/css" rel="stylesheet" href="css/add-student-style.css"/>
</head>

<div id="bg">


<body>
<% 
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // http 1.1
		response.setHeader("Pragma", "no-cache"); // http 1.0
		response.setHeader("Expires", "0"); // proxies

			if (session.getAttribute("email1") == null) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			}
			%>

<div id="wrapper">
<div id="header">
<h2>Maritime University</h2>
</div>
</div>

<div id="container">
<h3>Add Semester</h3>
<form action="StudentControllerServlet" method="GET">
<input type="hidden" name="command" value="ADDSEMESTER"/>


<%

String id=request.getParameter("id");

%>

<table>
<tbody>


<tr>
<td><input type="hidden" name="id" value=<%=id%> /></td>
</tr>


<tr>
<td><label>Math </label></td>
<td><input type="text" name="math"/></td>
</tr>

<tr>
<td><label>Phisics: </label></td>
<td><input type="text" name="phisics"/></td>
</tr>


<tr>
<td><label>English: </label></td>
<td><input type="text" name="english"/></td>
</tr>

<tr>
<td><label>Biology: </label></td>
<td><input type="text" name="biology"/></td>
</tr>

<tr>
<td><label>Art: </label></td>
<td><input type="text" name="art"/></td>
</tr>

<tr>
<td><label>History: </label></td>
<td><input type="text" name="history"/></td>
</tr>

<tr>
<td><label>School Year: </label></td>
<td><input type="text" name="schoolYear"/></td>
</tr>


<tr>
<td><label></label></td>
<td><input type="submit" value="Save" class="save" onclick="if(!(confirm('Make sure that marks you wrote in are correct')))return false"></td>
</tr>

</tbody>
</table>
</form>

<a href="StudentControllerServlet"><font color="black"><b>Back</b></font></a>

</div>



</body>
</div>
</html>