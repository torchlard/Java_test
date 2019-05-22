package DB;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

@NoRepositoryBean
public interface ICommonRepo<T, ID> extends JpaRepository<T,ID>
{

//  List<T> findAllByName(String name);
//  List<T> findBySex(String sex);

  // List<T> findAllById(Integer i);
//  List<T> findAll();
  
  // <S extends T> S save(S entity);
  // List<T> saveAll(List<T> list);

  List<T> toObjectList(String sql);
	T toObject(String sql);

  List<Map<String,Object>> toResultList(String sql);
	
	Map<String,Object> toResultMap(String sql);
	Map<String,Object> toResultMap(Map<String,Object> obj, String sql);


  EntityManager getEntityManager();
	
	


  
	@FunctionalInterface
	public interface ThreeFn<T,U,V,R>{
		R apply(T t,U u,V v);
	}
  
	@FunctionalInterface
	public interface FourFn<T,U,V,S,R>{
		R apply(T t,U u,V v,S s);
	}

  @FunctionalInterface
	public interface FiveFn<T,U,V,S,W,R>{
		R apply(T t,U u,V v,S s,W w);
	}


}


