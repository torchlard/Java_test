package DB;

import java.math.BigDecimal;

public class Teacher {
  private Integer id;
  private String name;
  private String sex;
  private float num;
  private int age;
  private BigDecimal largeNum;

  public Integer getId(){
    return id;
  }
  public String getName(){
    return name;
  } 
  public String getSex(){
    return sex;
  }
  public float getNum(){
    return num;
  }
  public int getAge(){
    return age;
  }
  public BigDecimal getLargeNum(){
    return largeNum;
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
  public void setNum(float num){
    this.num = num;
  }
  public void setAge(int age){
    this.age = age;
  }
  public void setLargeNum(BigDecimal b){
    this.largeNum = b;
  }

  public Teacher(Integer id,String name, String sex, float num, int age, BigDecimal b){
    super();
    this.id = id;
    this.name = name;
    this.sex = sex;
    this.num = num;
    this.age = age;
    this.largeNum = b;
  }

  public Teacher(String name, String sex, float num, int age, BigDecimal b){
    super();
    this.name = name;
    this.sex = sex;
    this.num = num;
    this.age = age;
    this.largeNum = b;
  }

  public Teacher(String name, String sex){
    super();
    this.name = name;
    this.sex = sex;
  }

  public Teacher(){
    super();
  }
  
  @Override
  public String toString(){
    return "Person [id="+id+", name=" + name + ", sex=" + sex + ", num=" + num + ", age=" +age+ 
      ", largeNum="+ largeNum +"]";
  }
  
}







