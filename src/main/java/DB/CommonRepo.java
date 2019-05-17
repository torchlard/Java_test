package DB;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

@NoRepositoryBean
public interface CommonRepo<T, ID> extends CrudRepository<T,ID>
{

//  List<T> findAllByName(String name);
//  List<T> findBySex(String sex);

  // List<T> findAllById(Integer i);
//  List<T> findAll();
  
  // <S extends T> S save(S entity);
  // List<T> saveAll(List<T> list);


}


