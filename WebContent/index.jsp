<%@ page language="java" contentType="text/html; charset=UTF8"
    pageEncoding="UTF8"%>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF8">
        <title>Hidden Line Server</title>

        <style>
            .alertBox {
 
            	<%
            		if(request.getParameter("wrong")==null){
            			%>
            				visibility:hidden;
            			<%
            		}
            	%>

                border-width: 2px;
                position: absolute;
                bottom: 10px;
                left: 20%;
                width: 60%;
                height: 60px;
                border-color: black;
                border-radius: 5px;
                background-color: rgb(182, 170, 170);
                text-align: center;
            }
            
            .entry_form {
                background-color: dimgrey;
                border-radius: 5px;
                padding: 25px;
                margin: 10px;
            }
        </style>

        <script type="text/javascript">
            history.pushState(null, null, document.URL);
            window.addEventListener('popstate', function() {
                history.pushState(null, null, document.URL);
            });
        </script>

    </head>

    <body style="text-align: center;">
        <h1>Hidden Line Web Server</h1>
        <h3>This is private zone</h3>
        <h5>No warranty of any kind. Owner and associates of this server will hold no responsibility for any kind of damage. If you have any suspicion in regard of nature of this service, go somewhere else and forget about all of this.</h5>

        <div class="alertBox">

            <p style="color:white; font-size: 18px; padding:10px; position: relative; top:-20px;">Wrong user name, password combination</p>

        </div>

        <div class="entry_form">

            <form action="/mainHoll" method="POST">

                <p align="center" style="color:white; font-size:25px;">
                    You are entering on your own responsibility
                </p>

                <label for="id">ID:</label>
                <br/>
                <input type="text" id="id" name="id">
                <br/>
                <label for="code">Entry code:</label>
                <br/>
                <input type="password" id="code" name="code">
                <br/>
                <br/>

                <input type="submit" name="accept" value="Going in" />
            </form>

        </div>

    </body>

    </html>
