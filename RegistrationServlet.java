import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class RegistrationServlet extends HttpServlet{
public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
res.setContentType("text/html");
PrintWriter out = res.getWriter();
String username = req.getParameter("user");
String password = req.getParameter("pwd");
int balance = Integer.parseInt(req.getParameter("bal"));
String email = req.getParameter("email");
Random rd = new Random();
List<String> accn = Arrays.asList("0", "1","2","3","4","5","6","7", "8", "9","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","@","#","*","&");
String accnn = "";
for(int i = 0;i < 10; i++){
int idx = rd.nextInt(accn.size());
accnn += accn.get(idx);
}
try{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false&allowPublicKeyRetrieval=true","root","hari@9RUSHI");
String q = "insert into bank(acctno, name, email, bal, password) values(?,?,?,?,?)";
PreparedStatement stm = con.prepareStatement(q);
stm.setString(1, accnn);
stm.setString(2, username);
stm.setString(3, email);
stm.setInt(4, balance);
stm.setString(5, password);
int x = stm.executeUpdate();
System.out.println("Data upadated sucessfully...." + x);
if(x > 0){
out.print("<html><br><br><center><img src = https://jazzvideolessons.net/wp-content/uploads/2014/12/registration-successful.png width = 350 height = 300 <br></center></html>");
out.println("<h2><center><blink>Your new Account number is " + accnn +"</blink></center></h2>");
}
else{
out.println("<html>Register Not successful</html>");
}
con.close();}
catch(Exception e){
out.print(e);
}}}