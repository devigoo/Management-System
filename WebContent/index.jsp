<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<link rel="stylesheet" type="text/css" href="css/login.css">
</head>

<body>
<div id="bg">
	<h2 align="center">School Management System</h2>
		</div>
			<form action="StudentControllerServlet" method="GET">
			<input type="hidden" name="command" value="LOGIN"/>
			Teacher<input type="radio" name="choice" value="teacher" style="width: 10px; height: 10px; ">
			Student<input type="radio" name="choice" value ="student" style="width: 10px; height: 10px; ">
  			<label for=""></label>
 			<input type="text" name="email"  placeholder="email" class="email">
  			<label for=""></label>
  			<input type="password" name="password" placeholder="password" class="pass">
  			<button type="submit">login to your account</button>
			</form>
</body>
</html>


