package DB;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class StudentMapper implements RowMapper<Student> {
  @Override
  public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
    Student stu = new Student(
      rs.getInt("id"), rs.getString("name"), rs.getString("sex"),
      rs.getFloat("job1"), rs.getInt("age")
    );
    return stu;
  }
}







