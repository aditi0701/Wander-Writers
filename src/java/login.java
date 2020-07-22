
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
 
@WebServlet(urlPatterns = {"/login"})
public class login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String username = request.getParameter("username_login");
            String password =request.getParameter("password_login");
            
           
            String SQLURL= "jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false";
            String SQLUserName = "root";
            String SQLPassword = "1234";
            
            try{
             
                if(username!=null){
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn=DriverManager.getConnection(SQLURL,SQLUserName,SQLPassword);
                    String Query = "SELECT * FROM accounts WHERE sname=? and password=MD5(?)";
                    PreparedStatement psm = conn.prepareStatement(Query);
                    psm.setString(1, username);
                    psm.setString(2, password);
                    ResultSet rs = psm.executeQuery();

                    if(rs.next()){
                         HttpSession session = request.getSession();
                         session.setAttribute("sessionUsername",username);
                         response.sendRedirect("nextToLogin");
                         
                    }
                    
                    else{
                        out.println("<!DOCTYPE html>\n");
                        out.println("<html>\n");
                        out.println("<head>\n");
                        out.println("<link rel='shortcut icon' type='image/png' href='favicon-32x32.png'>\n" 
                                +   "<link rel='stylesheet' href='home.css'>\n"
                                +   "<title>Invalid username password</title>\n");            
                        out.println("</head>\n");
                        out.println("\t\t<body>\n");
                        out.println("\t\t\t<header>\n\t\t\t"
                                +"<div class='welcome-text'>"
                                + "<h1>Invalid Username Password</h1>" 
                                + "</div>\n");
                        out.println("<div class='nav-area'>\n"
                                    +"\t<ul>"
                                    +"\n\t<li><a href='home.jsp'>Home page</a> </li>\n" 
                                    +"\t</ul>\n" 
                                    +"\t</div>\n");
                         
                    }
                               
                }
            }
            catch(Exception e){
               out.println("error in DB");
                System.out.println(e);
            
            }
                out.println("</header></body>\n</html>");
        }
       
    }
   

    }