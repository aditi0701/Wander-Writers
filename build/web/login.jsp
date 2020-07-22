
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
         <link rel="shortcut icon" type="image/png" href="favicon-32x32.png">
        <link rel="stylesheet" href="main2.css">
    </head>
    <body>
       
             <form action="login"  method="post">
            <h1>Login</h1>
            <fieldset>
           
                <legend><span class="number">1</span>Your basic info</legend>
                <label for="name">Username:</label>
                <input type="text" id="name" name="username_login">
                
                <label for="password">Password:</label>
                <input type="password" id="password" name="password_login">
                
                <a href="forgot.jsp">Forgot Password?</a>
                <fieldset>
                    <button type="submit">Login</button>
            </form>
            
        
    </body>
</html>
