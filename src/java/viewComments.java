
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
 
@WebServlet(urlPatterns = {"/viewComments"})
public class viewComments extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("sessionUsername");
            int uid;
            int flag =0;
            
            out.println("<!DOCTYPE html>\n");
            out.println("<html>\n");
            out.println("<head>\n");
            out.println(" <link rel=\"shortcut icon\" type=\"image/png\" href=\"favicon-32x32.png\">\n");
            out.println("<link rel=\"stylesheet\" href=\"viewWall.css\">\n");
            out.println("<title>Servlet viewAll</title>\n");            
            out.println("</head>\n\n");
            out.println("<body>\n\t");
            
            if(username!=null){
                String id = request.getParameter("poem_id");
                
                String SQLURL= "jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false";
                String SQLUserName = "root";
                String SQLPassword = "1234";
                
                
                    out.println("<header>\n\t");
                    out.println("<h1 style='font-size=30px'>Comments on this poem :</h1>\n\t");
                    
                try{    
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn=DriverManager.getConnection(SQLURL,SQLUserName,SQLPassword);
                    String query = " select comment_text,commenter_uid from comments where poem_id="+id+";";
                    PreparedStatement ps =conn.prepareStatement(query);
                    ResultSet rs = ps.executeQuery();
                   
                while(rs.next()){
                    flag =1;
                     out.println("<div class='show'>");
                    
                     String commenter_uid = rs.getString("commenter_uid");
                    String name = getName(commenter_uid);
                    String comment=rs.getString("comment_text");

                    out.println("<p>\n\t\t<div class='name'>"+name+" : </div>\n\t\t<div class='comment'> "+comment+"</div>\n</p>\n</div>\n");
                    //out.println("<hr>");
                }
                if(flag==0){
                    out.println("<div class = 'name'>No comments on this post</div>");
                }
                
                }
                catch(Exception e){
                   out.println("error in DB");
                System.out.println(e);
            
                }
               out.println("</header>\n\n</body\n</html>");
            }
             else{
                out.println("<h1>Ohh!! you are not logged in..</h1>"
                +"<a href='home.jsp'>Home</a>");
            }
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
    String getName(String user_id){
        String SQLURL= "jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false";
        String SQLUserName = "root";
        String SQLPassword = "1234";
        String username = null;
        try{    
        Connection conn=DriverManager.getConnection(SQLURL,SQLUserName,SQLPassword);
        String query = " select sname from accounts where id="+user_id+";";
        PreparedStatement ps =conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            username=rs.getString("sname");
        }
        
        }
        catch(Exception e){
            System.out.println(e);
        }
        return username;
}
}