<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" type="image/png" href="favicon-32x32.png">
        <link rel="stylesheet" href="home.css">
        <title>Logout Page</title>
    </head>
    <body>
    <header>
        <%
            String username = (String) session.getAttribute("sessionUsername");
        %>
        <% if(username!=null) {%>
       
            <div class="welcome-text">
                <h1>You are logged out successfully </h1>
            </div>
            
        <%

            session.invalidate();

        %>
        <% }else{ %>
        <div class="welcome-text">
        <h1>You are not logged in</h1>
        </div>
        <%} %>>
        
        <div class="nav-area">
                 <ul>
                     <li><a href="home.jsp">Home page</a> </li>
                 </ul>   
            <div>     
           
        </header>
    </body>
</html>
