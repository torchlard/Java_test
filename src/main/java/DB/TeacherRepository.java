package DB;

import java.awt.print.Pageable;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

import java.lang.reflect.Field;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.LinkedList;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;


class dto {
  private final String name;
  private final String sex;
  // private final String full;
  private final float num;
  public dto(String name,String sex,float num){
    this.name = name;
    this.sex = sex;
    this.num = num;
  } 
  // public String getName(){
  //   return name;
  // }
  // public String getSex(){
  //   return sex;
  // }
  // public String getFull(){
  //   return name+"::"+sex;
  // }
  public BigInteger getNumMod(){
    return BigInteger.valueOf((long)num);
  }
  // public String toString(){
  //   return name+", "+sex;
  // }
}

interface NamesOnly {
  // String getName();
  // String getSex();
  @Value("#{target.name+' '+target.sex}")
  String getFull();
  @Value("#{target.num*8}")
  int getMod();
}


class MyGenericClass<U>{}


@Transactional
interface TeacherRepository<T extends Teacher2, ID extends Serializable> 
  // extends CommonRepo<T,ID>, ITeacherCustom<T>
  extends ICommonRepo<T,ID>
{
  
  // List<T> findIdByName(String name);
  // List<T> findBySex(String sex);
  // List<T> findAllByAge(int age);
  
  // List<T> findAllByLargeNumIsNull();
  // List<T> findAllByNameLike(String a);
  // List<T> findAllByNameContaining(String a);

  // List<T> findAllByAgeNotIn(int... a);
  // List<T> findAllByAgeNotInOrderByAge(int... a);
  // List<T> findIxxxByAgeNotInOrderByAgeDesc(int... a);
  
  // long countByAgeNotIn(int... a);
  // List<T> getByAgeIn(int... a);
  // List<T> readByAgeIn(int... a);
  // List<T> queryByAgeIn(int... a);

  // List<T> findFirst3BySex(String x);
  // // List<dto> findAllBySex(String x);
  // List<NamesOnly> findAllBySex(String x);


  // @Query(value="select * from teacher where num>?1", nativeQuery=true)
  // List<T> getSth(float x);

  // @Query(value="select * from teacher where age>?1 "+
  //   "and name like ?2%", nativeQuery=true)
  // List<T> getSth2(int x, String name);

  // @Query("select t from #{#entityName} t where t.age > ?1")
  // List<T> getSth3(int x, Sort sort);

  // @Query("select t from Teacher2 t where t.age > ?1")
  // List<T> getSth4(int x, PageRequest page);

  // @Query("select t from Teacher2 t where t.num > ?1")
  // List<T> getSth5(float x);
  
  // @Modifying
  // @Query("update Teacher2 t set t.age = ?1 where id=?2")
  // void setAge1(int age, int id);

  // @Procedure("plus1")
  // Integer runPlus1(Integer arg);

  // List<T> query1();

  


  default List<T> getxx1(int age, String order){
    BiFunction<Integer,String,String> querySQL = (_age, _order) ->
      "select id,name,sex,num, age,largeNum "+
      "from teacher where age>" + _age +
      " order by " + _order;

    return toObjectList(querySQL.apply(age, order));
  }

  
  default public List<Map<String,Object>> getxx2(int age, String order){
    BiFunction<Integer,String,String> querySQL = (_age, _order) ->
      "select id,name,sex,num, age,largeNum "+
      "from teacher where age>" + _age +
      " order by " + _order;

    return toResultList(querySQL.apply(age, order));
  }

  default Map<String,Object> countxx1(){
    String sql = "select num from teacher where id=1";

    Map<String,Object> map = new HashMap<>();
    return toResultMap(map, sql);
  }



  // default T objxx1(){
  //   String sql = "select * from teacher where id=10";
  //   return toObject(sql);
  // }

  
  

}
  

