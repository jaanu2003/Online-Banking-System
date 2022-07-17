import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.sql.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  
public class LoginServlet extends HttpServlet{
public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
res.setContentType("text/html");
PrintWriter out = res.getWriter();
String acno = req.getParameter("acctno");
String pwd = req.getParameter("pwd");
try{
Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false&allowPublicKeyRetrieval=true","root","hari@9RUSHI");
Statement stm = con.createStatement();
ResultSet rs = stm.executeQuery("select * from bank where acctno='"+acno+"'and password='"+pwd+"'");
if(rs.next()){
res.sendRedirect("index.html");
}
else{
out.print("<center><h1>You credenials are wrong..</h1></center>");
}
con.close();
}catch(Exception e){
System.out.println(e.getMessage());
}
}
}