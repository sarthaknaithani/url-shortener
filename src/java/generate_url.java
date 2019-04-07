import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.math.BigInteger;
public class generate_url {
      public Connection getConnection()throws ClassNotFoundException,SQLException
        {
             Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/url_shortner","root","12345");
        return con;
        }
      public String get_62base(BigInteger b)
      {
          int n,a,r;
          String str="",str1="";
          String map[]=new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","0","1","2","3","4","5","6","7","8","9"};
           n=b.intValue();
           while(n>0)
           {
             r=n%62;
             str=str+map[r];
             n=n/62;  
           }
          for(int i=(str.length()-1);i>=0;i--)
          {
              str1=str1+str.charAt(i);
          }
          return str1;     
      }
    public String get_Id(String longurl)throws ClassNotFoundException,SQLException
    {
      BigInteger b=new BigInteger("0");
        String s=null;
      Connection con=null;
      ResultSet rs=null;
      Statement st=null;
        try
        {
        con=getConnection();  
        st=con.createStatement();
        
      
        String query="Select * from url_data where long_url='"+longurl+"'";
        
        rs=st.executeQuery(query);
       
                
        if(rs.next()==true)
        {
        s=rs.getString("uid");
        }
        
        
        else
        { 
       
        Connection con1=getConnection();
      ResultSet rs1;
      Statement st1=con1.createStatement();;
   
      st.execute("Insert into url_data(long_url) values('"+longurl+"')"); 
       
       rs1=st1.executeQuery(query);
       while(rs1.next())
       {
        b=new BigInteger(rs1.getString("id"));
       }
         s=get_62base(b);
            st1.execute("update url_data set uid='"+s+"' where long_url='"+longurl+"'"); 
        }
         
        }
         catch (SQLException | ClassNotFoundException ex) {
            System.out.print(ex.getMessage());
            throw ex;
        }
          finally {
          if (rs != null) {
          rs.close();
          }
          if (st != null) {
          st.close();
          }
          if (con != null) {
          con.close();
          }
          }
        
        
     
       return s; 
    }
    public String getShortUrl(String longurl,String servername, int portnumber,String contextpath)throws ClassNotFoundException,SQLException{
    String id=get_Id(longurl);
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
      rs=st.executeQuery("Select long_url from url_data where uid='"+uid+"'");
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
