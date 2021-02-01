<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Administrator password</title>

        <style>
            .formBox {
                background-color: dimgrey;
                border-radius: 5px;
                text-align: center;
                padding: 25px;
                margin: 10px;
                display: block
            }
        </style>

        <script type="text/javascript">
            history.pushState(null, null, document.URL);
            window.addEventListener('popstate', function() {
                history.pushState(null, null, document.URL);
            });
        </script>

    </head>

    <body>

        <div class="formBox">

            <h2>Administrator must change initial username, password combination</h2>

            <form action="adminPassword" method="POST">
                <label for="name">User name:</label>
                <br/>
                <input type="text" id="name" name="name">
                <br/>
                <label for="password">Password</label>
                <br/>
                <input type="password" id="password" name="password" />
                <br/>
                <br/>
                <div style="display: inline;">
                    <input type="submit" name="submit" value="It's OK">
                    <input type="submit" name="submit" value="No go">
                </div>
            </form>

        </div>

    </body>

    </html>