<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<style>

	.sender {
                display: block;
                float: left;
                width: 50%;
                text-align: left;
                font-size: 18px;
                padding: 3px;
                border-radius: 5px;
                border-color: black;
                background-color: darkgray;
                color: black;
                margin: 5px;
            }
            
            .receiver {
                display: block;
                float: right;
                text-align: right;
                width: 50%;
                font-size: 18px;
                padding: 3px;
                border-radius: 5px;
                border-color: black;
                background-color: rgb(27, 26, 26);
                color: rgb(211, 198, 198);
                margin: 5px;
            }

</style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="refresh" content="5" />

</head>
<body>

			<% 
        		String conversation=request.getSession().getAttribute("conversation").toString();
        		pageContext.setAttribute("conversation",conversation);
        		
        		%>
        			${conversation}
        		<%
    		
        	%>

</body>
</html>