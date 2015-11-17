package br.com.authentication.integration.repository;

import java.util.List;

/**
*
* @author aromano
*/
public interface CrudMasterDetailRepository<E> {
	
    void insert(E e);
    E update(E e);
    void delete(E e);
    List<E> findAll();
    E getByPrimaryKey(long id);
}
