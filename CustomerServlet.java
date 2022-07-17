import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import java.util.*;
public class CustomerServlet extends HttpServlet
{
public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
{
res.setContentType("text/html");
PrintWriter out=res.getWriter();
out.println("<center><h1>Customer Details</h1></center>");
try{
Class.forName("com.mysql.jdbc.Driver");
Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false&allowPublicKeyRetrieval=true","root","hari@9RUSHI");
Statement stm = con.createStatement();
ResultSet r=stm.executeQuery("select * from bank");
out.println("<center> <table width=100% height=100% border=1px >");
out.println("<tr><th>Account Number</th> <th> Accountant</th> <th> Email  </th> <th> Current Balance</th> </tr>");
while(r.next())
{
out.println("<tr> <td> "+r.getString(1)+"</td> ");
out.println("<td> "+r.getString(2)+"</td> ");
out.println("<td> "+r.getString(3)+"</td>");
out.println("<td> "+r.getInt(4)+"</td></tr> ");
}
out.println("</table></center>");
con.close();
}
catch(SQLException sq)
{
out.println("sql exception"+sq);
}

catch(ClassNotFoundException cl)
{
out.println("class not found"+cl);
}
}
}