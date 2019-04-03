import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class generate_url {
      public Connection getConnection()throws ClassNotFoundException,SQLException
        {
             Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/url_shortner","root","12345");
        return con;
        }
    public UUID get_Id(String longurl)
    {
        UUID id=null;
      Connection con=null;
        try
        {
        con=getConnection();  
        Statement st=con.createStatement();
        ResultSet rs=null;
        String query="Select id from url_data where long_url='"+longurl+"'";
        rs=st.executeQuery(query);
       
                
        if(rs.next()==true)
        {
            id=UUID.fromString(rs.getString("id"));
        }
        else
        {
          UUID uid = UUID.randomUUID();
       st.execute("Insert into url_data values('"+longurl+"','"+uid+"')"); 
       id=uid;
        }
        }
         catch (SQLException ex) {
            System.out.print(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.print(ex.getMessage());
        }
       return id; 
    }
    public String getShortUrl(String long_url,String servername, int portnumber,String contextpath){
    UUID id=get_Id(long_url);
    return "http://"+servername+":"+portnumber+contextpath+"/"+id;
    }
    public String getLongUrl(String uid)
    {
         if (uid.startsWith("/")) {
        uid = uid.replace("/", "");
    }
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        String longurl=null;
      try
      {
      con=getConnection();
      st=con.createStatement();
      rs=st.executeQuery("Select long_url from url_data where id='"+uid+"'");
      while(rs.next())
      {
          longurl=rs.getString("long_url");
      }
              
              }
      
         catch (ClassNotFoundException ex) {
              Logger.getLogger(generate_url.class.getName()).log(Level.SEVERE, null, ex);
          } catch (SQLException ex) {
              Logger.getLogger(generate_url.class.getName()).log(Level.SEVERE, null, ex);
          }
      return longurl;
    }
            }
