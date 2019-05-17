package DB;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class StudentDAOImpl implements StudentDAO {

  @Autowired
  private JdbcTemplate db;

  public int getCount(){
    String sql = "select count(*) from student";
    return db.queryForObject(sql, Integer.class);
  }

  public Teacher getObj1(){
    String sql = "select id,name from teacher where id=1";
    RowMapper<Teacher> m = new BeanPropertyRowMapper<>(Teacher.class);
    return db.queryForObject(sql, m);
  }
  
  public Teacher getObj2(int n){
    String sql = "select * from teacher where id=?";
    RowMapper<Teacher> m = new BeanPropertyRowMapper<>(Teacher.class);
    return db.queryForObject(sql, m, n);
  }

  public List<Map<String,Object>> getList1(){
    String sql = "select id as p from teacher";
    return db.queryForList(sql);
  }

  public List<Teacher> getList2(){
    String sql = "select * from teacher where id<=2";
    RowMapper<Teacher> m = new BeanPropertyRowMapper<>(Teacher.class);
    return db.query(sql, m);
  }

  public int update1(){
    String sql = "update teacher set name=? where id=?";
    return db.update(sql, "args", 1);
  }
  
}





