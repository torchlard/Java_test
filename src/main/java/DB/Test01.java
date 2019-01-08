package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class Test01 {
  public static void main(String[] args) {
    ResultSet rs = null;

    try {
      Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
    } catch(Exception e){
      e.printStackTrace();
    }

    try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/sakila?useSSL=false&allowPublicKeyRetrieval=true&user=user&password=123456");
    Statement stmt = conn.createStatement();
         ) {
      
      rs = stmt.executeQuery("select * from actor");
      
    } catch(SQLException ex){
      System.out.println("sqlexception: " + ex.getMessage());
      System.out.println("sql state: " + ex.getSQLState());
      System.out.println("vendor error: " + ex.getErrorCode());
    } finally {
      if (rs != null){
        try {
          rs.close();
        } catch(SQLException es){}
        rs = null;
      }
    }
    
  }    
}





