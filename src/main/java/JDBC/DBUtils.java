package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class DBUtils {
  private static ResourceBundle rb = ResourceBundle.getBundle("db-config");
  private static String URL;
  private static String USERNAME;
  private static String PASSWORD;
  private static String DRIVER;

  private DBUtils(){};

  static {
    URL = rb.getString("jdbc.url");
    USERNAME = rb.getString("jdbc.username");
    PASSWORD = rb.getString("jdbc.password");
    DRIVER = rb.getString("jdbc.driver");
    try {
      Class.forName(DRIVER).getConstructor().newInstance();
    } catch(Exception e){
      e.printStackTrace();
    }
  }

  public static Connection getConnection(){
    Connection conn = null;
    try {
      conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    } catch(SQLException e){
      e.printStackTrace();
    }
    return conn;
  }
  
  
}










