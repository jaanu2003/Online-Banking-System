import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.sql.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  
public class TransactionServlet extends HttpServlet{
public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
res.setContentType("text/html");
PrintWriter out = res.getWriter();
String usr = req.getParameter("customer");
String pwd = req.getParameter("amount");
String usr1 = req.getParameter("customer1");
String accn1 = req.getParameter("accn1");
String accn2 = req.getParameter("accn2");

int amount = Integer.parseInt(pwd);
  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
   LocalDateTime now = LocalDateTime.now();  
try{
Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false","root","hari@9RUSHI");
Statement stm = con.createStatement();
ResultSet rs = stm.executeQuery("select bal from bank where name='"+usr1+"'");

if(rs.next()){
String n = rs.getString(1);
int balance1 = Integer.parseInt(n);
if(balance1 - amount > 0){
String q = "update bank set bal = bal +'"+amount+"' where name='"+usr+"'";
int x = stm.executeUpdate(q);
String q1 = "update bank set bal = bal - '"+amount+"' where name='"+usr1+"'";
int x2 = stm.executeUpdate(q1);
out.print("<html><br><br><center><img src = https://orbitbiotech.com/wp-content/uploads/2018/10/Orbit-Biotech-Online-Registration-Successful.jpg width = 400 height = 400 <br></center></html>");
out.println("<center><h1><blink>Transaction Succesfull</blink></h1></center>");
String q2 = "insert into trans values('"+usr+"','"+usr1+"',"+amount+",'"+dtf.format(now)+"')";
PreparedStatement stm1 = con.prepareStatement(q2);
int x1 = stm1.executeUpdate();
}
else{
out.print("<html><br><br><center><img src = https://iraimmigration.com/wp-content/uploads/2019/12/transaction-failed-300x300.png width = 400 height = 400 <br></center></html>");
out.println("<center><h1><blink>Transaction Failed</blink></h1></center>");
String q2 = "insert into trans values('"+usr+"','"+usr1+"',"+amount+",'"+dtf.format(now)+" (Failed) "+"')";
PreparedStatement stm1 = con.prepareStatement(q2);
int x1 = stm1.executeUpdate();
}
}

}
catch(Exception e){
out.print(e);
}
}
}