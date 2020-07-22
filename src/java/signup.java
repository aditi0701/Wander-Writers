
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;



@WebServlet(urlPatterns = {"/signup"})
@MultipartConfig(maxFileSize = 10000000)    // upload file's size up to 1MB
public class signup extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
            out.println("<html>\n<link rel=\"shortcut icon\" type=\"image/png\" href=\"favicon-32x32.png\">\n" 
                +"<link rel=\"stylesheet\" href=\"home.css\">\n\n"
                        + "<header>\n\t"
                        +" <div class=\"welcome-text\">\n\t");
            
            String Sname = request.getParameter("username_signup");
            String Fname = request.getParameter("fname_signup");
            String Lname = request.getParameter("lname_signup");
            String Email = request.getParameter("email_signup");
            String password = request.getParameter("password_signup");
            String location = request.getParameter("location_signup");
            //image upload
            Part part = request.getPart("file_signup");
            String fileName1 = part.getSubmittedFileName();
            String fileName = fileName1.replaceAll(" ", "%20");
            String path = getServletContext().getRealPath("/"+"images"+File.separator+fileName);
            InputStream is = part.getInputStream();
            boolean succ = uploadFile(is,path);
            String date = request.getParameter("dob_signup");
         
            String[] parts;
            parts = date.split("-");
            
            int yr;
            yr = Integer.valueOf(parts[0]);
            
           LocalDate currentDate = LocalDate.now();
//           out.println("here");
//           ZoneId defaultZoneId = ZoneId.systemDefault();
//           out.println("here");
//            java.util.Date currentDate_string = Date.from(currentDate.atStartOfDay(defaultZoneId).toInstant());
//           out.println("here");
            int front = currentDate.getYear();
            int age_grp = front-yr;
            Date date1=Date.valueOf(date);
            String ipAddress = request.getRemoteAddr();
            String hostName = request.getRemoteHost();
            
            /////////////////////////////////////////////////////////////////////////////////////////////////
            //................ALL INPUT VALUES ARE TAKEN FROM THE FORM NOW VALIDATING.........
             
            String currentDateToString = currentDate.toString();
            SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date d1 = df.parse(currentDateToString);
            java.util.Date d2 = df.parse(date); 
            
            // validate first name 
            if( (Fname.matches("[A-Z][a-z]*")==false) ){
                out.println("<h1>Invalid Firstname</h1>");
            }
            
            else if( (Lname.matches("[A-Z][a-z]*")==false) ){
                out.println("<h1>Invalid Lastname</h1>");
            }
            
            else if( (location.matches("[A-Z][a-z]*")==false) ){
                out.println("<h1>Invalid location</h1>");
            }
            
            
            // validate email
            else if(Email.matches ("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")==false ){
                out.println("<h1>Invalid Email Address</h1>");
            }
            
            //validate the image upoaded
            else if(succ==false){
                out.println("<h1>Improper Image</h1>");
            }
            
            //checking that the date of birth comes before teh current date
            
            else if(d2.compareTo(d1)>0){
                out.println("<h1>You are not yet born !!!!</h1>");
            }
            
            else{
            String SQLURL= "jdbc:mysql://localhost:3306/project?autoReconnect=true&useSSL=false";
            String SQLUserName = "root";
            String SQLPassword = "1234";
         
           
        
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn=DriverManager.getConnection(SQLURL,SQLUserName,SQLPassword);
                PreparedStatement st = conn.prepareStatement(" insert into accounts(sname,fname,lname,email,password,location,pict_id,age_grp,bdate,ip,hostname)VAlUES(?,?,?,?,MD5(?),?,?,?,?,?,?);");
                
                
                st.setString(1,Sname);
                st.setString(2,Fname);
                st.setString(3,Lname);
                st.setString(4,Email);
                st.setString(5,password);
                st.setString(6,location);
                st.setString(7,fileName);
                st.setInt(8,age_grp);
               st.setDate(9, date1);
                st.setString(10,ipAddress);
                st.setString(11,hostName);
                
                st.executeUpdate();
                st.close(); 
                conn.close(); 
              
                
                         out.println( "<h1>Successfully Registered</h1><br>\n\t");
                        
            }     
            catch(Exception e)
            {
                System.out.println(e);
                out.println("error in DB");
            }
            
          
        }
          out.println("<a href='home.jsp'>HOME</a>\n</div>\n"
                        + "</header>\n"); 
            out.println("</body>\n</html>");
       
    
    }   catch (ParseException ex) {
            Logger.getLogger(signup.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    }
   
    public boolean uploadFile(InputStream is,String path) throws IOException{
        boolean test = false;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try{
            byte[] byt = new byte[is.available()];
             for (int readNum; (readNum = is.read(byt)) != -1;) {
               
            
                 bos.write(byt,0,readNum);
            try(OutputStream outputStream = new FileOutputStream(path)) {
                bos.writeTo(outputStream);
            }
        
            test=true;
        }
       
       
    }
         catch(Exception e){
                System.out.println(e);
            
        }
         return test;
}


}
