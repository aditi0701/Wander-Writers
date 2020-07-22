
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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
import java.sql.Statement;

@WebServlet(urlPatterns = {"/comments"})
public class comments extends HttpServlet {

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("sessionUsername");
            int uid;
            
            out.println("<html>\n"
                    +"<head><title>comments</title>\n"
                    + "<link rel=\"shortcut icon\" type=\"image/png\" href=\"favicon-32x32.png\">\n" 
                    +"<link rel=\"stylesheet\" href=\"home.css\">\n"
                    + "</head>");
            out.println("\n\t<header>");
            
            if(username!=null){
            
            String commenter_uid = null;
            String rating = null;
            String value=null;
            String id = request.getParameter("poem_id");
            
            String comment_text = request.getParameter("comment");
        
         /*   String[] values=request.getParameterValues("star");
            
            if(values!=null){
             
            out.println("3");
           for(int i=0;i<values.length;i++)
            {
                rating = values[i];
            } 
            }*/
            
            //out.println("after");
            
//            for(int i=1;i<=5;i++){
//                out.println(request.getParameter("star"+i));
//               value = request.getParameter("star"+i);
//                
//                if(value!=null){
//                    break;
//                    }
//            }
          //  out.println(value);
   
         value = request.getParameter("star");
         rating=value;
            
           // out.println(username);
            String ip = request.getRemoteAddr();
            
            String SQLURL= "jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false";
            String SQLUserName = "root";
            String SQLPassword = "1234";
            
            try{    
               Class.forName("com.mysql.jdbc.Driver");
               Connection conn=DriverManager.getConnection(SQLURL,SQLUserName,SQLPassword);
                Statement st = conn.createStatement();
               String sql ="select id from accounts where sname='"+username+"';";
                ResultSet rs = st.executeQuery(sql);
                
                while(rs.next()){
                   uid = rs.getInt("id");
                  commenter_uid  = Integer.toString(uid) ;
                }
                conn.close();
            }
            catch(Exception e){
                out.println(e);
            }
            
           try{
               int comments = 0;
               Class.forName("com.mysql.jdbc.Driver");
               Connection conn=DriverManager.getConnection(SQLURL,SQLUserName,SQLPassword);
               Statement st = conn.createStatement();
               String sql ="select no_of_comments from poems where poem_id='"+id+"';";
                ResultSet rs = st.executeQuery(sql);
                
                while(rs.next()){
                  comments  = rs.getInt("no_of_comments");
                }
                comments = comments+1;
                PreparedStatement st1 = conn.prepareStatement("update poems set no_of_comments='"+comments+"' where poem_id='"+id+"';");
                 st1.executeUpdate();
                conn.close();
          }
           catch(Exception e){
               out.println(e);
           }
                     
            try{    
               Class.forName("com.mysql.jdbc.Driver");
               Connection conn=DriverManager.getConnection(SQLURL,SQLUserName,SQLPassword);
               PreparedStatement st1 = conn.prepareStatement("insert into comments(poem_id,comment_text,one_rating,commenter_uid,ip)values(?,?,?,?,?) ");
               
                st1.setString(1,id);
                st1.setString(2,comment_text);
                st1.setString(3,value);
                st1.setString(4,commenter_uid);
                st1.setString(5,ip);
                st1.executeUpdate();
                st1.close(); 
                conn.close(); 
                  
                        
                        out.println("\n\t\t <div class=\"welcome-text\">\n\t"
                        + "<h1>Successfully Commented</h1>\n\t<br>\n\t"
                        +"<a href='before viewWall.jsp'>VIEW WALL</a>\n\t</header>\n\t"
                        + "</div>\n"); 
            }
            catch(Exception e){
                out.println("error in DB");
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
            out.println("</body></html>");
        }
    }
    
    
}
