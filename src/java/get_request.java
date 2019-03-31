import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


public class get_request extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       PrintWriter out=response.getWriter();
       String long_url=request.getParameter("longurl");
        String a = (request.getRequestURI());
       out.println(long_url);
       generate_url u=new generate_url();
       UUID uid=u.get_Id(long_url);
       
       out.println(uid);
       out.println(long_url);
    }
      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   

}
