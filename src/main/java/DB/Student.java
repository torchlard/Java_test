package DB;

import org.springframework.stereotype.Repository;

public class Student {
  private Integer id;
  private String name;
  private String sex;
  private float job1;
  private int age;

  public Integer getId(){
    return id;
  }
  public String getName(){
    return name;
  } 
  public String getSex(){
    return sex;
  }
  public float getJob1(){
    return job1;
  }
  public int getAge(){
    return age;
  }

  public void setId(Integer id){
    this.id = id;
  }
  public void setName(String name){
    this.name = name;
  }
  public void setSex(String sex){
    this.sex = sex;
  }
  public void setJob1(float job1){
    this.job1 = job1;
  }
  public void setAge(int age){
    this.age = age;
  }

  public Student(String name, String sex, float job1, int age){
    super();
    this.name = name;
    this.sex = sex;
    this.job1 = job1;
    this.age = age;
  }

  
  public Student(Integer id, String name, String sex, float job1, int age){
    super();
    this.id = id;
    this.name = name;
    this.sex = sex;
    this.job1 = job1;
    this.age = age;
  }

  public Student(){
    super();
  }
  
  @Override
  public String toString(){
    return "Person [id="+id+", name=" + name + ", sex=" + sex + ", job1=" + job1 + ", age=" +age+" ]";
  }
  
}







