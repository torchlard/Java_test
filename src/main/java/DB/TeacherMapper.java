package DB;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TeacherMapper implements RowMapper<Teacher> {
  @Override
  public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
    Teacher stu = new Teacher(
      rs.getInt("id"), rs.getString("name"), rs.getString("sex"),
      rs.getFloat("num"), rs.getInt("age"), rs.getBigDecimal("largeNum")
    );
    return stu;
  }
}







