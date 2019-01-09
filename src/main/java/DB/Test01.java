package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.sql.Types;
import java.sql.PreparedStatement;

public class Test01 {
  public static void main(String[] args) {

    Student stu = new Student("h1", "F", "ti");
    Student stu2 = new Student("h2", "G", "ti2");

    try {
      Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
    } catch(Exception e){
      e.printStackTrace();
    }

    // String query = "select actor_id,first_name from actor limit 10";
    // String query = "update actor set first_name='peter' where actor_id=1";
    String query = "insert into student(name,sex,job1) values(?,?,?)";

    try(Connection conn = DriverManager.getConnection(
      "jdbc:mysql://localhost/testdb?useSSL=false&allowPublicKeyRetrieval=true", "user", "123456");
      PreparedStatement ps = conn.prepareStatement(query);
        // Statement stmt = conn.createStatement();
        // ResultSet rs = stmt.executeQuery(query);
         ) {

          conn.setAutoCommit(false);

          ps.setString(1, stu.getName() );
          ps.setString(2, stu.getSex() );
          ps.setString(3, stu.getJob1() );
          ps.executeUpdate();

          ps.setString(1, stu2.getName() );
          ps.setString(2, stu2.getSex() );
          ps.setString(3, stu2.getJob1() );
          ps.executeUpdate();
          conn.commit();

          // int state = stmt.executeUpdate(query);
          // System.out.println("state: " + state);

          // while(rs.next()){
          //   int id = rs.getInt(1);
          //   String name = rs.getString(2);
          //   System.out.println( Integer.toString(id) + " "+ name);
          // }

          // CallableStatement cs = conn.prepareCall("{call demoSp(?,?)}");
          // cs.setString(1, "abcdefg");
          // cs.setInt(2, 26);
          // cs.registerOutParameter(2, Types.INTEGER);
          // boolean hadResult = cs.execute();
          // while(hadResult){
          //   ResultSet rs2 = cs.getResultSet();
          //   hadResult = cs.getMoreResults();
          // }
          // System.out.println("outputValue: " + cs.getInt(2));

      
    } catch(SQLException ex){
      System.out.println("sqlexception: " + ex.getMessage());
      System.out.println("sql state: " + ex.getSQLState());
      System.out.println("vendor error: " + ex.getErrorCode());
    } 
    
  }    
}





