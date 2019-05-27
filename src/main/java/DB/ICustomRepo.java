package DB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Util.SqlParser;


public interface ICustomRepo<T> {

  default <U> List<U> baseToObject(EntityManager em, String sql, Class<U> cls){
    // Class<T> cls = super.getDomainClass();
    Query query = em.createNativeQuery(sql, cls);
    @SuppressWarnings("unchecked")
    List<U> res = query.getResultList();
    return res;
  }

  default List<Map<String,Object>> toResultList(EntityManager em, String sql){
    List<Map<String,Object>> output = new ArrayList<>();
    try {
      List<String> columnNames = SqlParser.getSelectColumns(sql);
      @SuppressWarnings("unchecked")
      List<Object[]> results = em.createNativeQuery(sql).getResultList();
      for(Object[] obj: results){
        Map<String,Object> map = new HashMap<>();
        for(int i=0; i<columnNames.size(); i++){
          map.put(columnNames.get(i), obj[i]);
        }
        output.add(map);
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
    return output;
  }

  default Map<String,Object> baseResultMap(EntityManager em, Map<String,Object> map, String sql){
    try {
      List<String> columnNames = SqlParser.getSelectColumns(sql);
      @SuppressWarnings("unchecked")
      List<Object> results = em.createNativeQuery(sql).getResultList();

      if (results.get(0) instanceof Object[]){
        Object[] objs = (Object[]) results.get(0);
        for(int i=0; i<columnNames.size(); i++){
          map.put(columnNames.get(i), objs[i]);
        }
      } else {
        map.put(columnNames.get(0), results.get(0));
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
    return map;
  }

}




