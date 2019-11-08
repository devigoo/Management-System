<html>

<head>
<title>Add new student</title>
<link type="text/css" rel="stylesheet" href="css/style.css"/>
<link type="text/css" rel="stylesheet" href="css/add-student-style.css"/>
</head>

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
<h2>Maritime University - add student</h2>
</div>
</div>

<div id="bg">
	<div id="container">
		<div id="content">
<h3>Add Student</h3>
<form action="StudentControllerServlet" method="GET">
<input type="hidden" name="command" value="ADD"/>


<table>
<tbody>





<tr>
<td><label>First name: </label></td>
<td><input type="text" name="firstName"/></td>
</tr>

<tr>
<td><label>Last name: </label></td>
<td><input type="text" name="lastName"/></td>
</tr>

<tr>
<td><label>Email: </label></td>
<td><input type="text" name="email"/></td>
</tr>


<tr>
<td><label>Password: </label></td>
<td><input type="text" name="password"/></td>
</tr>

<tr>
<td><label></label></td>
<td><input type="submit" value="Save" class="save"/></td>
</tr>

</tbody>
</table>
</form>

<a href="StudentControllerServlet"><font color="black"><b>Back</b></font></a>



</div>
</div>
</body>

</div>
</html>