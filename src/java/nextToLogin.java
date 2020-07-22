
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class nextToLogin extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
         
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("sessionUsername");
            
            if(username!=null){

            out.println("<!DOCTYPE html>\n");
            out.println("<html>\n");
            out.println("<head>\n");
            out.println("<link rel='shortcut icon' type='image/png' href='favicon-32x32.png'>\n" 
                    +   "<link rel='stylesheet' href='home.css'>\n"
                    +   "<title>Servlet nextToLogin</title>\n");            
            out.println("</head>\n\n\t");
            out.println("<body>\n\t");
            out.println("<header>\n\t"
                    +"<div class='welcome-text'>\n\t"
                    + "<h1 style='color:#ffc107'>\n\tWelcome "+username+" to the Wander Writers Ingenious family!!\n\t"
                    + "</h1>\n</div>");
        
           
            out.println("<div>\n");
            out.println("<ul class='nav-area'>\n"
                       +"<li><a href='addPost.jsp'>Add Post</a><li>\n"
                       +"<li><a href='before viewWall.jsp'>View Wall</a><li>\n" 
                       +"<li><a href='logout.jsp'>Logout</a><li>\n"
                       +"</ul>\n</div>\n</header>\n");
            out.println("</body>\n");
            out.println("</html>");
        }
             else{
                out.println("<div class='welcome-text'>\n\t"
                + "<h1>Ohh!! you are not logged in..</h1>\n\t" 
                + "</div></h1>");
                out.println("<div class='nav-area'>\n\t"
                    +"<ul>\n\t"
                    +"<li><a href='home.jsp'>Home</a> </li>\n\t" 
                    +"</ul>" 
                    +"</div>");
            }
    }
    }
}
