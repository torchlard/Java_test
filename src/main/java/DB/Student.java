package DB;

public class Student {
  private String name;
  private String sex;
  private String job1;

  public String getName(){
    return name;
  } 
  public String getSex(){
    return sex;
  }
  public String getJob1(){
    return job1;
  }

  public void setName(String name){
    this.name = name;
  }
  public void setSex(String sex){
    this.sex = sex;
  }
  public void setJob1(String job1){
    this.job1 = job1;
  }

  public Student(String name, String sex, String job1){
    super();
    this.name = name;
    this.sex = sex;
    this.job1 = job1;
  }

  public Student(){
    super();
  }
  
  @Override
  public String toString(){
    return "Person [name=" + name + ", sex=" + sex + ", job1=" + job1 + " ]";
  }
  
}







