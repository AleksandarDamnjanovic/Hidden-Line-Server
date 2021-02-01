<%@ page import="java.util.ArrayList"%>

    <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<style>
.logoff {
	text-decoration: none;
	color: black;
	background-color: white;
	border-radius: 5px;
	padding: 10px;
}

.titleBox {
	text-align: center;
	background-color: dimgrey;
	border-radius: 5px;
	width: 100%;
	height: 150px;
}

.listElement {
	display: inline;
	float: left;
	width: 100%;
	font-size: 25px;
	padding: 10px;
	border-radius: 5px;
	border-color: black;
	background-color: lightgrey;
	color: black;
	margin: 5px;
}

.listOfUsers {
	height: 50%;
	min-height: 500px;
	min-width: 350px;
	width: 36%;
	margin-top: 25px;
	margin-left: 32%;
	border-radius: 5px;
	border-color: aquamarine;
	border-width: 2px;
}
</style>

<meta charset="UTF-8">
<title>Hidden Line Web Server</title>
</head>

<body>

	<div class="titleBox">
		<h1 style="color: white">User page</h1>
		<h5 style="color: white">Select one among available users and
			start chatting</h5>
		<a class="logoff" href="/">Log off</a>
	</div>

	<div style="display: block;">

		<div class="listOfUsers">

			<%
				ServletContext con = getServletContext();
				ArrayList<String> users = (ArrayList<String>) con.getAttribute("userNames");
				String curentUser = "";
				if (request.getSession().getAttribute("userName") != null)
					curentUser = request.getSession().getAttribute("userName").toString();

				if (!curentUser.equals("")) {
					for (String user : users)
						if (!user.equals(curentUser)) {
			%>

			<div class="listElement">
				<form action="/chatRoom" method="POST">
					<label style="font-size: 16px; margin-left: 15px;"> <%=user%>
					</label>
					<input style="visibility:hidden;" type="text" name="chatWith" value="<%=user%>"/>
					<div style="float: right">
						<input type="submit" name="submit" value="Chat" />
					</div>
				</form>
			</div>
			
			<%
						}
				} else {
					response.sendRedirect("/");
				}
			%>

		</div>
</body>

</html>