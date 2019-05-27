package DB;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;


@Entity
@Table(name="student")
public class Student {

  @Id
  private Integer id;
  private String name;
  private String sex;
  // @Column(name="teacher_id")
  // private Integer teacherId;

  @JoinColumn(name="teacher_id")
  @ManyToOne(fetch=FetchType.LAZY)
  private Teacher2 teacher;

  public Integer getId(){
    return id;
  }
  public String getName(){
    return name;
  } 
  public String getSex(){
    return sex;
  }
  // public Integer getTeacherId(){
  //   return teacherId;
  // }


  public void setId(Integer id){
    this.id = id;
  }
  public void setName(String name){
    this.name = name;
  }
  public void setSex(String sex){
    this.sex = sex;
  }
  // public void setTeacherId(Integer id){
  //   this.teacherId = id;
  // }
  
  public Student(Integer id, String name, String sex){
    super();
    this.id = id;
    this.name = name;
    this.sex = sex;
    // this.teacherId = teacherId;
  }

  public Student(){
    super();
  }
  
  @Override
  public String toString(){
    return "Person [id="+id+", name=" + name + ", sex=" + sex + 
    // ", teacherId="+ teacherId +
    " ]";
  }
  
}







