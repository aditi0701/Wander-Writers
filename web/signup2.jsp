
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 
   <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sign Up Form</title>
         <link rel="shortcut icon" type="image/png" href="favicon-32x32.png">
        <link rel="stylesheet" href="css/normalize.css">
        <link href='https://fonts.googleapis.com/css?family=Nunito:400,300' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="main2.css">
    </head>

    <!-- Title Page-->
    
    <body>
       
        
      <form action="signup" method="post" enctype="multipart/form-data">
      
        <h1>Sign Up</h1>
        
        <fieldset>
          <legend><span class="number">1</span>Your basic info</legend>
          <label for="name">Username:</label>
          <input type="text" id="name" name="username_signup">
          
          <label for="name">First Name:</label>
          <input type="text" id="name" name="fname_signup">
          
           <label for="name">Last Name:</label>
          <input type="text" id="name" name="lname_signup">
          
          <label for="mail">Email:</label>
          <input type="email" id="mail" name="email_signup">
          
          <label for="password">Password:</label>
          <input type="password" id="password" name="password_signup">
          
          <label for="location">Location:</label>
          <input type="text" id="password" name="location_signup">
          
           <label for="image">Image:</label>
          <input type="file" id="password" name="file_signup">
          
          <label for="dob">DOB</label>
          <input type="date" id="password" name="dob_signup">
          </fieldset>
       
        <button type="submit">Sign Up</button>
      </form>
</html>
