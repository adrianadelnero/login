package br.com.autentication.integration.repository;

import java.util.List;

/**
*
* @author aromano
*/
public interface CrudMasterDetailRepository<E,K> {
	
    void insert(E e);
    E update(E e);
    void delete(E e);
    List<E> findAll();
    E getByPrimaryKey(K pk);
}
