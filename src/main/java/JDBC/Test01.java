package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.PreparedStatement;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

// import DB.Teacher;


public class Test01 {

  public static void main(String[] args) {
    
    // String query = "select id,name,sex,job1,age from student where age>? and name like ?";
    
    // String query = "select name,age,job1,bigDec,id from student";
    // String query2 = "update student set ";
    // String query = "update actor set first_name='peter' where actor_id=1";
    
    // long start = System.nanoTime();
    // System.out.println( (System.nanoTime() - start)/1_000_000_000.0 );
    
    try(Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/testdb", "root", "666666");
    ) {

      // List<Teacher> tt = new ArrayList<>();
      // Teacher t1 = new Teacher("t3", "M");
      // Teacher t2 = new Teacher("t4", "M");
      // tt.add(t1);
      // tt.add(t2);
      
      //   String sql = "insert into teacher(name,sex,num) values(?,?,?)";
      //   // String sql = "insert into teacher(name,sex,num,age) values (?,?,?,?)";
      //   // String sql = "select id from student where age is ?";
      //   // String sql = "select * from teacher where id=2";


      //   // Teacher ll = JdbcUtils.toObjectMap(conn, Teacher.class, sql);
      //   // ll.stream().forEach(System.out::println);

      //   // JdbcUtils.batchUpdate(conn.prepareStatement(sql), tt, "name", "sex");
      //   JdbcUtils.oneObjUpdate(conn.prepareStatement(sql), null, "name", "sex","num");

  
    } catch(SQLException ex){
      System.out.println("sqlexception: " + ex.getMessage());
      System.out.println("sql state: " + ex.getSQLState());
      System.out.println("vendor error: " + ex.getErrorCode());
    } 
    
  }

}


