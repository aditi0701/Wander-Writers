
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class addPost extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
        
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("sessionUsername");
            
            out.println("<!DOCTYPE html>");
            out.println("<html>\n");
            out.println("<head>\n\t");
            out.println("<link rel='shortcut icon' type='image/png' href='favicon-32x32.png'>\n" 
            +   "<link rel='stylesheet' href='home.css'>\n"
            +   "<title>Posted successfully</title>\n");            
            out.println("</head>");
            out.println("<body>");
            out.println("<header>");
            
            if(username!=null){
            Statement st = null;
            int uid; 
            String uid1 = null;
            String title = request.getParameter("title");
            String category = request.getParameter("category");
            String content = request.getParameter("content");
            
            String ipAddress = request.getRemoteAddr();
            
            
            String SQLURL= "jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false";
            String SQLUserName = "root";
            String SQLPassword = "1234";
         
           int c = 0;
           String comments = Integer.toString(c); 
          try{
               Class.forName("com.mysql.jdbc.Driver");
               Connection conn=DriverManager.getConnection(SQLURL,SQLUserName,SQLPassword);
               st = conn.createStatement();
               String sql ="select id from accounts where sname='"+username+"';";
                ResultSet rs = st.executeQuery(sql);
                
                while(rs.next()){
                  uid  = rs.getInt("id");
                  uid1  = Integer.toString(uid) ;
                }
                conn.close();
          }
            catch(Exception e){
                  out.println("error in DB");
                  System.out.println(e);
                }
        
           try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn=DriverManager.getConnection(SQLURL,SQLUserName,SQLPassword);
                PreparedStatement st1 = conn.prepareStatement(" insert into poems(uid,genere,poem_title,poem_text,no_of_comments,ip)VAlUES(?,?,?,?,?,?);");
               
                st1.setString(1,uid1);
                st1.setString(2,category);
                st1.setString(3,title);
                st1.setString(4,content);
                st1.setString(5,comments);
                st1.setString(6,ipAddress);
               
                st1.executeUpdate();
                st.close(); 
                conn.close(); 
              
              
                
                out.println("<div class='welcome-text'>\n\t"
                + "<h1>Successfully Posted</h1>\n\t" 
                + "</div></h1>");
                out.println("<div class='nav-area'>\n\t"
                    +"<ul>\n\t"
                    +"<li><a href='before viewWall.jsp'>Have a look!!</a> </li>\n\t" 
                    +"</ul>" 
                    +"</div>");
                   
  
            }     
            catch(ClassNotFoundException | SQLException e)
            {
                out.println("Error in DB");
                System.out.println(e);
            }
       
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
            out.println("</header></body></html>");
        }
    }
    }
 


