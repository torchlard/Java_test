package DB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import Util.SqlParser;

// @Repository
// @Transactional(readOnly = true)
public class BaseCustomRepo<T,ID> extends SimpleJpaRepository<T,ID> implements ICommonRepo<T,ID>, ICustomRepo<T> {
  
  // @PersistenceContext
  protected EntityManager entityManager;
  
  public BaseCustomRepo(JpaEntityInformation<T,?> entityInformation, EntityManager entityManager){
    super(entityInformation, entityManager);
    this.entityManager = entityManager;
  }

  public BaseCustomRepo(Class<T> domainClass, EntityManager em){
    super(domainClass, em);
    this.entityManager = em;
  }


  
  public EntityManager getEntityManager(){
    return entityManager;
  }

  public List<T> toObjectList(String sql){
    return baseToObject(entityManager,sql,super.getDomainClass());
  }

  public Optional<T> toObject(String sql){
    List<T> results = baseToObject(entityManager, sql, super.getDomainClass());
    return Optional.of(results.get(0));
  }
  
  public List<Map<String,Object>> toResultList(String sql){
    return toResultList(entityManager, sql);
  }

  public Map<String,Object> toResultMap(Map<String,Object> obj, String sql){
    return baseResultMap(entityManager, obj, sql);
  }

  public Map<String,Object> toResultMap(String sql){
    return baseResultMap(entityManager, new HashMap<>(), sql);
  }




  // private List<T> baseToObject(String sql){
  //   Class<T> cls = super.getDomainClass();
  //   Query query = entityManager.createNativeQuery(sql, cls);
  //   @SuppressWarnings("unchecked")
  //   List<T> res = query.getResultList();
  //   return res;
  // }

  // public List<T> toObjectList(String sql){
  //   return baseToObject(sql);
  // }

  // public T toObject(String sql){
  //   List<T> results = baseToObject(sql);
  //   return results.size()==0 ? null : results.get(0);
  // }


  // public List<Map<String,Object>> toResultList(String sql){
  //   List<Map<String,Object>> output = new ArrayList<>();
  //   try {
  //     List<String> columnNames = SqlParser.getSelectColumns(sql);
  //     @SuppressWarnings("unchecked")
  //     List<Object[]> results = entityManager.createNativeQuery(sql).getResultList();
  //     for(Object[] obj: results){
  //       Map<String,Object> map = new HashMap<>();
  //       for(int i=0; i<columnNames.size(); i++){
  //         map.put(columnNames.get(i), obj[i]);
  //       }
  //       output.add(map);
  //     }
  //   } catch(Exception e) {
  //     e.printStackTrace();
  //   }
  //   return output;
  // }

  // private Map<String,Object> baseResultMap(Map<String,Object> map, String sql){
  //   try {
  //     List<String> columnNames = SqlParser.getSelectColumns(sql);
  //     @SuppressWarnings("unchecked")
  //     List<Object> results = entityManager.createNativeQuery(sql).getResultList();

  //     if (results.get(0) instanceof Object[]){
  //       Object[] objs = (Object[]) results.get(0);
  //       for(int i=0; i<columnNames.size(); i++){
  //         map.put(columnNames.get(i), objs[i]);
  //       }
  //     } else {
  //       map.put(columnNames.get(0), results.get(0));
  //     }
  //   } catch(Exception e) {
  //     e.printStackTrace();
  //   }
  //   return map;
  // }

  // public Map<String,Object> toResultMap(Map<String,Object> obj, String sql){
  //   return baseResultMap(obj, sql);
  // }
  // public Map<String,Object> toResultMap(String sql){
  //   return baseResultMap(new HashMap<String,Object>(), sql);
  // }
  
    
}


