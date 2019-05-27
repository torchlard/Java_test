package DB;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.ParameterMode;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javassist.SerialVersionUID;


// @NamedStoredProcedureQuery(name="teacher.plus1", procedureName="plus1", parameters = {
//   @StoredProcedureParameter(mode=ParameterMode.IN, name="arg", type=Integer.class),
//   @StoredProcedureParameter(mode=ParameterMode.OUT, name="res", type=Integer.class)
// })
// @NamedNativeQueries(value={
//   @NamedNativeQuery(name="Teacher2.query1", query="select * from teacher where age>40", resultClass = Teacher2.class),
//   @NamedNativeQuery(name="Teacher2.query2", query="select * from teacher where name like 'j%'", resultSetMapping="tMap")
// })
// @SqlResultSetMapping(name="tMap", entities={},
//   columns = {
//     @ColumnResult(name="id"),
//     @ColumnResult(name="name")
//   })
// @SQLDelete(sql = "update teacher set delete_flag=1 where id=?")
// @Where(clause="age > 0")
@Entity
@Table(name="teacher")
public class Teacher2 implements Serializable {

  @Id
  private Integer id;
  private String name;
  private String sex;
  @Column(name="num")
  private float num;
  private int age;
  private BigInteger largeNum;
  private String hello;

  @Formula("age*100")
  private int sexnum;

  static final long SerialVersionUID = 54354325342l;

  // specify 'private Teacher2 teacher' in class Student
  @OneToMany(mappedBy = "teacher")
  private List<Student> student;
  

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
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
  @Transient
  public String getHello(){
    return hello;
  }
  @Transient
  public int getSexnum(){
    return sexnum;
  }

  @Basic
  @Column(name="largeNum")
  public BigInteger getLargeNum(){
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
  public void setLargeNum(BigInteger b){
    this.largeNum = b;
  }

  public Teacher2(Integer id,String name, String sex, float num, int age, BigInteger largeNum){
    super();
    this.id = id;
    this.name = name;
    this.sex = sex;
    this.num = num;
    this.age = age;
    this.largeNum = largeNum;
  }

  public Teacher2(String name, String sex, float num, int age, BigInteger largeNum){
    super();
    this.name = name;
    this.sex = sex;
    this.num = num;
    this.age = age;
    this.largeNum = largeNum;
  }

  public Teacher2(String name, String sex){
    super();
    this.name = name;
    this.sex = sex;
  }

  public Teacher2(){
    super();
  }
  
  @Override
  public String toString(){
    return "Person [id="+id+", name=" + name + ", sex=" + sex + ", num=" + num + ", age=" +age+ 
      ", largeNum="+ largeNum +", sexnum="+sexnum + 
      ", student="+student + 
      "]";
  }

  private void tableScript(){
    //     CREATE TABLE `teacher` (
    //   `id` int(11) NOT NULL AUTO_INCREMENT,
    //   `name` varchar(50) NOT NULL,
    //   `sex` char(1) DEFAULT 'M',
    //   `num` float(10,3) DEFAULT 0.000,
    //   `age` int(5) DEFAULT 0,
    //   `largeNum` bigint(20) DEFAULT NULL,
    //   PRIMARY KEY (`id`)
    // ) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8
  }
  
}







