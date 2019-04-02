import java.sql.*;
import java.util.*;
public class generate_url {
    public UUID get_Id(String longurl)
    {
        UUID id=null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/url_shortner","root","12345");
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
    public String getShortUrl(String long_url,String servername, int portnumber){
    UUID id=get_Id(long_url);
    return "http://"+servername+":"+portnumber+"/"+id;
    }
}
