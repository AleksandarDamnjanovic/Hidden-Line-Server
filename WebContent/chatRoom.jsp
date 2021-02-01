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
            
            .chatSpace {
                display: block;
                height: 50%;
                min-height: 500px;
                width: 70%;
                margin-top: 25px;
                margin-left: 15%;
                border-radius: 5px;
                border-color: black;
                border-width: 2px;
                background-color: rgb(230, 221, 221);
            }
            
            .textBox {
                margin-top: 15px;
                width: 55%;
                padding: 5px;
                margin-left: 15%;
                margin-right: .5%;
            }
            
            .button {
                margin-top: 15px;
                width: 12%;
                padding: 5px;
                margin-left: 0.5%;
            }
        </style>

        <meta charset="UTF-8">
        <title>Chat Room</title>
    </head>

    <body>

        <div class="titleBox">
            <h1 style="color: white">Chat Room</h1>
            <a class="logoff" href="/UserPage">Back</a>
        </div>
        
        <iframe src="/ChatDisplay" class="chatSpace" title="Chat Space"></iframe>
   
        <div style="display: inline;width: 100%;">
            <form action="/chatRoom" method="POST">
                <input type="text" name="message" class="textBox" />
                <input type="submit" name="submit" value="Send" class="button" />
            </form>

        </div>

    </body>

    </html>