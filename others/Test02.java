package DB;

import java.sql.SQLException;

public class Test02 {
  public static void main(String[] args) throws SQLException {
    
    Student s = new Student("gg1", "F", "mygg", 35);
  
    StudentDAOImpl db = new StudentDAOImpl();
    db.add(s);
    
  }


}












