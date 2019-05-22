package DB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class TeacherRepo2 {

  @Autowired
  TeacherRepository repo;

  public Teacher2 objxx1(){
    return repo.objxx1();
  }

  public List<Teacher2> objxx2(){

    EntityManager em = repo.getEntityManager();
    TypedQuery<Teacher2> finalQuery;
    CriteriaBuilder builder = repo.getEntityManager().getCriteriaBuilder();
    CriteriaQuery<Teacher2> query = builder.createQuery(Teacher2.class);

    Root<Teacher2> root = query.from(Teacher2.class);
    List<Predicate> predicates = new ArrayList<>();
    predicates.add(
      builder.and(
        builder.greaterThan(root.get("age"), 20),
        builder.lessThan(root.get("num"), 50)
      )
    );
    Predicate pred = predicates.get(0);

    return em.createQuery(query.select(root).where(pred)).getResultList();
  }

  public void saving(Teacher2 t){
    repo.save(t);
  }

  public void saves(Iterable<Teacher2> ll){
    repo.saveAll(ll);
  }



}















