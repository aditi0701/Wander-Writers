<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" type="image/png" href="favicon-32x32.png">
        <link rel="stylesheet" href="addPost.css">
        <title>JSP Page</title>
    </head>
    <body>
     
        <form method="post" action="addPost">
             <h1> Your Post Wall</h1>
            <label for="title">Title</label><br>
            <textarea name="title" rows="2" cols="30" required></textarea>
            
               
           <p><label for="category">Category</label><br>
            <input list="category" name="category" placeholder="select" required>
            <datalist id="category">
            <option value="Fashion">
            <option value="Food">
            <option value="Travel">
            <option value="Music">
            <option value="Lifestyle">
            <option value="Fitness"> 
            <option value="Sports">
            <option value="Finance">
            <option value="Political">
            <option value="Parenting">
            <option value="Business">
            <option value="Personal">
            <option value="Educational">  
            <option value="Movie">  
            <option value="Gaming">    
            <option value="Others">    
            </datalist>
            </p>
            
            
            <label for="content">Content</label><br>
            <textarea name="content" rows="15" cols="55" required></textarea><br>
            
            
            
            <input type="submit" value="Post">
           
            
                
        </form>
            
       
    </body>
</html>
