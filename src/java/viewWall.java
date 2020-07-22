
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(urlPatterns = {"/viewWall"})
public class viewWall extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            String query = null;
            int flag = 0;
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("sessionUsername");
            
            out.println("<!DOCTYPE html>\n");
            out.println("<html>\n");
            out.println("<head>\n");
            out.println(" <link rel=\"shortcut icon\" type=\"image/png\" href=\"favicon-32x32.png\">\n");
            out.println("<link rel=\"stylesheet\" href=\"viewWall.css\">\n");
            out.println("<title>Servlet viewAll</title>\n");            
            out.println("</head>\n\n");
            out.println("<body>\n\t");
            out.println("<nav>\n\t\t");
            out.println("<p><h1>Wander Writers</h1></p>\n\t\t\t");
            if(username!=null){
            String[] info_arr = new String [2];
            int poem_id;
            String category = request.getParameter("button");
            if (category.equals("all")){
                query = " select poem_id,uid,poem_title,poem_text,upload_date,no_of_comments from poems ORDER BY no_of_comments DESC limit 5";
            } 
            else{   
                query="select poem_id,uid,poem_title,poem_text,upload_date,no_of_comments from poems where genere='"+category+"'";
                  
                    out.println("<form class='sort' method='post' action='sorting'>\n\t\t\t"
                    +"<p>Sort Poems By</p>\n\t\t\t"
                    +"<input name='genere' type='hidden' value="+category+">\n\t\t\t"        
                    +"   <input list='category' name='SortBy' placeholder='select'>\n\t\t\t" 
                    +"    <datalist id='category'>\n\t\t\t\t" 
                    +"    <option value='Date'>\n\t\t\t\t" 
                    +"    <option value='No. of Comments'>\n\t\t\t\t" 
                    +"    <option value='Age (15-30)'>\n\t\t\t\t"
                    +"    <option value='Age (30-45)'>\n\t\t\t\t"
                    +"    <option value='Age (45-60)'>\n\t\t\t\t"
                    +"    <option value='Age (60-85)'>\n\t\t\t"
                    +"    </datalist>\n\t\t"        
                    +"  <button type='submit' value='Sort It !!'>Sort It !!</button>\n\t" 
                    +"</form>\n</nav>");
            }
            
            String SQLURL= "jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false";
            String SQLUserName = "root";
            String SQLPassword = "1234";
            
            
            try{    
               Class.forName("com.mysql.jdbc.Driver");
               Connection conn=DriverManager.getConnection(SQLURL,SQLUserName,SQLPassword);
               
               PreparedStatement ps =conn.prepareStatement(query);
              
                ResultSet rs = ps.executeQuery();
                
                
                while(rs.next()){
                    flag =1 ;
                    poem_id = rs.getInt("poem_id");
                    int uid = rs.getInt("uid");
                    info_arr = getPic(uid);
                    String sname = info_arr[0];
                    String pic =info_arr[1];
                   //int rating = getRating(poem_id);
                   // out.println(rating);
                    
                    
                    String Poem_title = rs.getString("poem_title");
                    String poem_text = rs.getString("poem_text");
                    Date upload_date = rs.getDate("upload_date");
                    int no_of_comments = rs.getInt("no_of_comments");
                    
                    String YourString = poem_text.replaceAll("\\n", "<br>");
                    
                  
                    out.print("\n\n<table class='table'>");
                
                    out.print("\n\t\t<tr style='column-width:100px'><td class='img'><img src=images\\"+pic+" alt='image' style='width:200px;height:200px' object-fit='contain'></td></tr>");
                   
                   out.println("\n\t\t<tr><th class='poemTitle'>"+Poem_title+"<th></tr>"); 
                   out.print("\n\t\t<tr><th class='date'>"+upload_date+"</th></tr>");
                    out.print("\n\t\t<tr><th class='YourString'>"+YourString+"</th></tr>");
                    out.print("<th class='sname'>By-"+sname+"</th>\n\t\t<tr>\n\t\t");
                    out.print("<th class='comments'>"+"Comemnts = "+no_of_comments+"</th>");
                     
                     
                    out.println("\n\n\t\t<tr><td>\n");
                   
                    out.println("<form class='form' method='post' action='comments'>\n\t");  
                    out.println("<input name='poem_id' type='hidden' value="+poem_id+">\n\t"
                               
                               +"<label for='comment'>Comment</label><br>\n\t" 
                                +"<textarea name='comment' rows='3' cols='40' required></textarea><br>\n\n\t"
                                //+"<div class=\"stars\">" 
                                +"<input class=\"star star-5\" id=\"star-5\" type=\"radio\" name=\"star\" value=\"5\"/>\n\t" 
                                +"<label class=\"star star-5\" for=\"star-5\"></label>\n\n\t" 

                               + "<input class=\"star star-4\" id=\"star-4\" type=\"radio\" name=\"star\" value=\"4\"/>\n\t" 
                                + "<label class=\"star star-4\" for=\"star-4\"></label>\n\n\t" 

                                +"<input class=\"star star-3\" id=\"star-3\" type=\"radio\" name=\"star\" value=\"3\"/>\n\t" 
                                +"<label class=\"star star-3\" for=\"star-3\"></label>\n\n\t" 

                                +"<input class=\"star star-2\" id=\"star-2\" type=\"radio\" name=\"star\" value=\"2\"/>\n\t" 
                                +"<label class=\"star star-2\" for=\"star-2\"></label>\n\n\t" 

                                +"<input class=\"star star-1\" id=\"star-1\" type=\"radio\" name=\"star\"  value=\"1\"/>\n\t" 
                                +"<label class=\"star star-1\" for=\"star-1\"></label>\n\n\t" 
                            
                             +"<input type='submit' value='Comment/Rate'>\n");
                    
                      out.println("</form>\n\n");
                      
                      
                       out.println("<form class='next' method='post' action='viewComments'>\n\t");  
                       
                    out.println("<input name='poem_id' type='hidden' value="+poem_id+">\n\t"
                               +"<button type='submit'  name='button' value='viewAllComments'>View All Comments</button>\n"
                               +"</form>\n");
                    out.println("</td></tr>\n");
                    out.println("</table>\n");
                  
                             
                     
                
                }
                if(flag == 0){out.println("<td>NO POSTS AVAILABLE RELATED TO YOUR INTEREST !!</td>");}
                
                    out.println("</table>");
                   
                  

                conn.close();
            }
            catch(Exception e){
                out.println("error in DB");
                System.out.println(e);
            
            }
                
              
        }
        else{
            out.println("<h1>Ohh!! you are not logged in..</h1>\n"
            +"<a href='home.jsp'>Home</a>");
        }
           
            out.println("</body>");
            out.println("</html>");
           
        }
        
    }

    
    String[] getPic(int uid){
        
         String info[]=new String[2];
        try{
         String SQLURL= "jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false";
            String SQLUserName = "root";
            String SQLPassword = "1234";
           
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn=DriverManager.getConnection(SQLURL,SQLUserName,SQLPassword);
        Statement st = conn.createStatement();
        String sql ="select pict_id,sname from accounts where id='"+uid+"';";
        ResultSet rs = st.executeQuery(sql);
                
            while(rs.next()){
                info[0]=rs.getString("sname");    
                info[1] = rs.getString("pict_id");
            }
                
                conn.close();
       }
       catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
        return info;
    }
    
   /* int getRating(int poem_id){
        
         int rating=0;
        try{
         String SQLURL= "jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false";
            String SQLUserName = "root";
            String SQLPassword = "1234";
           
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn=DriverManager.getConnection(SQLURL,SQLUserName,SQLPassword);
        Statement st = conn.createStatement();
        String sql ="select avg(one_rating) as avg from accounts where poem_id='"+poem_id+"';";
        ResultSet rs = st.executeQuery(sql);
                
            while(rs.next()){
               rating=rs.getInt("avg");    
            }
           
                conn.close();
       }
       catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
        return rating;
    }*/
    
}
