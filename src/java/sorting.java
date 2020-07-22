
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

@WebServlet(urlPatterns = {"/sorting"})
public class sorting extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
              
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("sessionUsername");
            
            if(username!=null){
            String[] info_arr = new String [3];
            String query = null;
            int ll =0;
            int ul = 100;
            int flag = 0;
            String SQLURL= "jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false";
            String SQLUserName = "root";
            String SQLPassword = "1234";
            
            String SortBy = request.getParameter("SortBy");
            String category = request.getParameter("genere");
            
            out.println("<!DOCTYPE html>\n");
            out.println("<html>\n\n");
            out.println("<head>\n");
            out.println(" <link rel=\"shortcut icon\" type=\"image/png\" href=\"favicon-32x32.png\">\n");
            out.println("<link rel=\"stylesheet\" href=\"viewWall.css\">\n");
            out.println("<title>Servlet viewAll</title>\n");            
            out.println("</head>\n\n");
            out.println("<body>\n");
            out.println("<header>\n");
            out.println("<nav>\n");
            out.println("<p><h1>Wander Writers</h1></p>\n\n");
            
            if(SortBy.equals("Date")){
                query="select poem_id,uid,poem_title,poem_text,upload_date,no_of_comments from poems where genere='"+category+"' ORDER BY upload_date ASC";
                
            }
            else if(SortBy.equals("No. of Comments")){
                query="select poem_id,uid,poem_title,poem_text,upload_date,no_of_comments from poems where genere='"+category+"' ORDER BY no_of_comments DESC";
                 
            }
            
            else if(SortBy.equals("Age (15-30)")){
                ll = 15; ul = 30;
                query="select poem_id,uid,poem_title,poem_text,upload_date,no_of_comments from poems where genere='"+category+"';";
            }
            else if(SortBy.equals("Age (30-45)")){
                ll = 30; ul = 45;
                query="select poem_id,uid,poem_title,poem_text,upload_date,no_of_comments from poems where genere='"+category+"';";
            }
            else if(SortBy.equals("Age (45-60)")){
                ll = 45; ul = 60;
                query="select poem_id,uid,poem_title,poem_text,upload_date,no_of_comments from poems where genere='"+category+"';";
            }
            else if(SortBy.equals("Age (60-85)")){
                ll = 60; ul = 85;
                query="select poem_id,uid,poem_title,poem_text,upload_date,no_of_comments from poems where genere='"+category+"';";
            }
            else{
                out.println("<h1 style='color:red'>Inappropriate Selection!!</h1>");         
           }
           
            
            out.println("<form class='sort' method='post' action='sorting'>\n\t"
            +"<p>Sort Poems By</p>\n\t"
            +"<input list='SortBy' name='SortBy'>\n\t"  
            +"<input name='genere' type='hidden' value="+category+">\n\t\t"        
            +"    <datalist class='SortBy'>\n\t\t" 
            +"    <option value='Date'>\n\t\t" 
            +"    <option value='No. of Comments'>\n\t\t" 
            +"    <option value='Age (15-30)'>\n\t\t"
            +"    <option value='Age (30-45)'>\n\t\t"
            +"    <option value='Age (45-60)'>\n\t\t"
            +"    <option value='Age (60-85)'>\n\t"
            +"    </datalist>\n"        
            +"  <button type='submit' value='Sort It !!'>Sort It !!</button>\n" 
            +"</form></nav>\n\n");
             try{    
               Class.forName("com.mysql.jdbc.Driver");
               Connection conn=DriverManager.getConnection(SQLURL,SQLUserName,SQLPassword);
               
               PreparedStatement ps =conn.prepareStatement(query);
              
                ResultSet rs = ps.executeQuery();
                
                
                while(rs.next()){
                   String poem_id = rs.getString("poem_id");
                   int uid = rs.getInt("uid");
                   info_arr = getPic(uid);
                   String sname = info_arr[0];
                   String pic =info_arr[1];
                   int age_grp = Integer.parseInt(info_arr[2]);
                   
                   
                    String Poem_title = rs.getString("poem_title");
                    String poem_text = rs.getString("poem_text");
                    Date upload_date = rs.getDate("upload_date");
                    int no_of_comments = rs.getInt("no_of_comments");
                   
                    if(age_grp>ll && age_grp<=ul){
                    
                     flag =1;   
                     //out.println(pic);
                    
                    String YourString = poem_text.replaceAll("\\n", "<br>");
                     out.print("<table class='table'>\n\t");
                  
                    
                    out.print("<tr><td class='img'><img src=images\\"+pic+" alt='image' border=1 height=100 width=100'></td></tr>\n\t");
                   
                   out.println("<tr><th class='poemTitle'>"+Poem_title+"</th></tr>\n\t"); 
                   out.print("<tr><th class='date'>"+upload_date+"</th></tr>\n\t");
                    out.print("<tr><th class='YourString'>"+YourString+"</th></tr>\n\t");
                    out.print("<th class='sname'>By-"+sname+"</th>\n\t");
                    out.print("<th class='comments'>"+"Comemnts = "+no_of_comments+"</th>\n\n");
               
                     
                     
                    out.println("<tr><td>\n");
                    
                    out.println("<form class='form' method='post' action='comments'>\n\t");  
                    out.println("<input name='poem_id' type='hidden' value="+poem_id+">\n\t"
                               
                               +"<label for='comment'>Comment</label><br>\n\t" 
                                +"<textarea name='comment' rows='3' cols='40'></textarea><br>\n\t"
                                +"<div class=\"stars\">\n\n\t" 
                                +"<input class=\"star star-5\" id=\"star-5\" type=\"radio\" name=\"star5\" value=\"5\"/>\n\t" 
                                +"<label class=\"star star-5\" for=\"star-5\"></label>\n\n\t" 

                               + "<input class=\"star star-4\" id=\"star-4\" type=\"radio\" name=\"star4\" value=\"4\"/>\n\t" 
                                + "<label class=\"star star-4\" for=\"star-4\"></label>\n\n\t" 

                                +"<input class=\"star star-3\" id=\"star-3\" type=\"radio\" name=\"star3\" value=\"3\"/>\n\t" 
                                +"<label class=\"star star-3\" for=\"star-3\"></label>\n\n\t" 

                                +"<input class=\"star star-2\" id=\"star-2\" type=\"radio\" name=\"star2\" value=\"2\"/>\n\t" 
                                +"<label class=\"star star-2\" for=\"star-2\"></label>\n\n\t" 

                                +"<input class=\"star star-1\" id=\"star-1\" type=\"radio\" name=\"star1\"  value=\"1\"/>\n\t" 
                                +"<label class=\"star star-1\" for=\"star-1\"></label>\n\n\t" 
                                +"</div>  \n\t"
                             +"<input type='submit' value='Comment/Rate'>\n\n"); 
                      out.println("</form>\n\n\n");
                      
                      
                      out.println("<form class='next' method='post' action='viewComments'>\n\t");  
                      out.println("<input name='poem_id' type='hidden' value="+poem_id+">\n\t"
                            +"<button type='submit'  name='button' value='viewAllComments'>View All Comments</button>\n"
                            +"</form>\n\n");
                     
                    out.println("</td></tr>\n");
                    out.println("</table>\n");
                             
                }
                    
                }
                
                if(flag==0){
                    out.println("<td>NO POSTS AVAILABLE RELATED TO YOUR INTEREST !!</td>");
                }
                    out.println("</table>");
                   
                    
                conn.close();
            }
            catch(ClassNotFoundException | NumberFormatException | SQLException e){
                out.println("error in DB");
                System.out.println(e);
            
            }
                 out.println("</body>");
                 out.println("</html>");
     
           
        }
        else{
            out.println("<h1>Ohh!! you are not logged in..</h1>");
        }
        }
        
    }

    
    String[] getPic(int uid){
        
         String info[]=new String[3];
        try{
         String SQLURL= "jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false";
            String SQLUserName = "root";
            String SQLPassword = "1234";
           
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn=DriverManager.getConnection(SQLURL,SQLUserName,SQLPassword);
        Statement st = conn.createStatement();
        String sql ="select pict_id,sname,age_grp from accounts where id='"+uid+"';";
        ResultSet rs = st.executeQuery(sql);
                
            while(rs.next()){
                info[0]=rs.getString("sname");    
                info[1] = rs.getString("pict_id");
                info[2] = rs.getString("age_grp");
            }
                
                conn.close();
       }
       catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
        return info;
    }
}
    
   