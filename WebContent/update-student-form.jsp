<html>

<head>
<title>Update student</title>
<link type="text/css" rel="stylesheet" href="css/style.css"/>
<link type="text/css" rel="stylesheet" href="css/add-student-style.css"/>
</head>

<div id="bg">

<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // http 1.1
		response.setHeader("Pragma", "no-cache"); // http 1.0
		response.setHeader("Expires", "0"); // proxies

			if (session.getAttribute("email1") == null) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			}
 %>




<body>






<div id="wrapper">
<div id="header">
<h2>Maritime University of Szczecin</h2>
</div>
</div>

<div id="container">
<div id="content">
<h3><b>Update student</b></h3>
<form action="StudentControllerServlet" method="GET">

<input type="hidden" name="command" value="UPDATE"/>

<input type="hidden" name="studentId" value="${THE_STUDENT.id}"/>


<table>
<tbody>






<tr>
<td><label>First name: </label></td>
<td><input type="text" name="firstName" value="${THE_STUDENT.firstName}"/></td>
</tr>

<tr>
<td><label>Last name: </label></td>
<td><input type="text" name="lastName" value="${THE_STUDENT.lastName}"/></td>
</tr>

<tr>
<td><label>Email: </label></td>
<td><input type="text" name="email" value="${THE_STUDENT.email}"/></td>
</tr>

<tr>
<td><label>Password: </label></td>
<td><input type="text" name="password" value="${THE_STUDENT.password}"/></td>
</tr>





<tr>
<td><label></label></td>
<td><input type="submit" value="Update" class="save"/></td>
</tr>

</tbody>
</table>
</form>

<a href="StudentControllerServlet"><b><font color="black">Back</font></b></a>

</div>
</div>
</body>
</div>
</html>