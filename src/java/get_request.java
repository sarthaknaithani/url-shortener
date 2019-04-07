import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;


public class get_request extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException,ClassNotFoundException,SQLException {
        PrintWriter out=response.getWriter();
        try
        {
        response.setContentType("text/html;charset=UTF-8");
      
       String long_url=request.getParameter("longurl");
       generate_url u=new generate_url();
       String servername=request.getServerName();
       int portnumber=request.getServerPort();
       String contextpath=request.getContextPath();
      
     out.println(u.getShortUrl(long_url,servername,portnumber,contextpath));
     out.println("<----YOUR SHORT URL"); 
        }
       catch(SQLException | ClassNotFoundException e)
        {
            out.println("Error:---"+e.getMessage());
        }
    }
      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
              PrintWriter out=response.getWriter();
              out.println("Error:---"+ex.getMessage());
        }
        
      
        }
    }

   


