
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.*;

public class get_request extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       PrintWriter out=response.getWriter();
       String long_url=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getRequestURI();
       try
       {
       Class.forName("com.mysql.cj.jdbc.Driver");
       Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/url_shortner","root","12345");
       Statement st=con.createStatement();
       ResultSet rs;
       UUID id = UUID.randomUUID();
       st.execute("Insert into data values('"+id+"','"+long_url+"')");
       out.println(id);
       out.println(long_url);
    }
       catch(ClassNotFoundException e)
       {
            out.println(e.getMessage());
       }
       catch(SQLException e)
       {
           out.println(e.getMessage());
       }
    }
      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   

}
