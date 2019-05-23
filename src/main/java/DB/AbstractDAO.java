package DB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import Util.SqlParser;


public abstract class AbstractDAO<E> implements ICustomRepo<E> {

  protected IAbstractRepository<E> commonRepository;

  @PersistenceContext
  protected EntityManager entityManager;

 
  public List<E> toObjectList(String sql, Class<E> cls){
    return baseToObject(entityManager,sql, cls);
  }

  public Optional<E> toObject(String sql, Class<E> cls){
    List<E> results = baseToObject(entityManager, sql, cls);
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

  // public E findOne(Integer id) {
  //     return commonRepository.findOne(id);
  // }

  public List<E> findAll() {
      return commonRepository.findAll();
  }

  public List<E> findAll(Sort sort) {
      return commonRepository.findAll(sort);
  }

  public Page<E> findAll(Pageable pageable) {
      return commonRepository.findAll(pageable);
  }

  public long count() {
      return commonRepository.count();
  }

  public void delete(E e) {
      commonRepository.delete(e);
  }

  public void deleteAll() {
      commonRepository.deleteAll();
  }

  public <S extends E> S save(S s) {
      return commonRepository.save(s);
  }

  // public <S extends E> Iterable<S> save(Iterable<S> entities) {
  //     return commonRepository.save(entities);
  // }

  public <S extends E> S saveWithMerge(S s) {
      merge(s);
      return save(s);
  }

  public void flush() {
      commonRepository.flush();
  }

  public void deleteInBatch(Iterable<E> iterable) {
      commonRepository.deleteInBatch(iterable);
  }

  public void deleteAllInBatch() {
      commonRepository.deleteAllInBatch();
  }

  public void detach(E entity) {
      entityManager.detach(entity);
  }

  public void merge(E entity) {
      entityManager.merge(entity);
  }

  public void detach(List<E> entitys) {
      if (entitys != null) {
          for (E e : entitys) {
              entityManager.detach(e);
          }
      }
  }


}




















