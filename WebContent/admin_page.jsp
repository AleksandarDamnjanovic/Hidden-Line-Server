<%@ page import="java.util.ArrayList"%>

    <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
        <!DOCTYPE html>
        <html>

        <head>

            <script type="text/javascript">
                history.pushState(null, null, document.URL);
                window.addEventListener('popstate', function() {
                    history.pushState(null, null, document.URL);
                });

                function namesClick() {
                    var checkBox = document.getElementById("users");
                    var text = document.getElementById("userName");
                    var hid = document.getElementById("hid");
                    hid.value= checkBox.options[checkBox.selectedIndex].value;
                    text.value = checkBox.options[checkBox.selectedIndex].value;
                }
            </script>

            <style>
                .listElement {
                    font-size: 25px;
                    padding: 10px;
                    border-radius: 5px;
                    background-color: lightgrey;
                    color: black;
                    margin: 5px;
                }
                
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
                    height: 100px;
                }
                
                .listOfUsers {
                    width: 350px;
                    margin-top: 25px;
                    margin-left: 25px;
                    border-radius: 5px;
                    border-color: aquamarine;
                    border-width: 2px;
                }
            </style>

            <meta charset="UTF-8">
            <title>Admin Page</title>
        </head>

        <body>

            <div class="titleBox">
                <h1 style="color: white">Admin page</h1>
                <a class="logoff" href="/">Log off</a>
            </div>

            <div style="display: inline; float: left; align-items: center;">

                <form action="/adminPage" method="POST">

                    <div style="float: left; display: inline;">
                        <select name="users" id="users" onclick="namesClick()" class="listOfUsers" size="10">

					<%
						ServletContext con = getServletContext();
						ArrayList<String> users = (ArrayList) con.getAttribute("userNames");
						if (users != null) {
							for (String user : users) {
					%>
					<option class="listElement" value="<%=user%>"><%=user%></option>
					<%
						}
						} else {

						}
					%>

				</select>
                    </div>

                    <div style="display: block; float: left; margin-top: 25px; margin-left: 25px">

                        <label for="userName">User name:</label> <br />
                        <input type="text" id="userName" name="userName" /> <br />

                        <label for="userPassword">Password:</label>
                        <br /> <input type="password" name="userPassword" /> <br /> <br />

						<input type="hidden" name="hid" id="hid" value=""/>

                        <div style="display: inline; float: left;">
                            <input type="submit" name="submit" value="Add" /> <input type="submit" name="submit" value="Update" /> <input type="submit" name="submit" value="Remove" />
                        </div>

                    </div>

                </form>
            </div>

        </body>

        </html>