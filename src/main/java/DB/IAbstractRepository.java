package DB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IAbstractRepository<E> extends JpaRepository<E, Integer> {

}
