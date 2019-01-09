package DB;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class StudentDAOImpl implements StudentDAO {

  @Override
  public void add(Student s) throws SQLException {
    String sql = "insert into student(name,sex,job1) values(?,?,?)";

    try (
      Connection conn = DBUtils.getConnection();
      PreparedStatement ps = conn.prepareStatement(sql);
    ){
      ps.setString(1, s.getName());
      ps.setString(2, s.getSex());
      ps.setString(3, s.getJob1());
      ps.executeUpdate();
      
    } catch(SQLException e){
      e.printStackTrace();
    }
  }
  
}





