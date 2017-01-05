package br.com.authentication.integration.repository;

import java.math.BigDecimal;
import java.util.List;

/**
*
* @author aromano
*/
public interface CrudMasterDetailRepository<E> {
	
    void insert(E e);
    E update(E e);
    void delete(BigDecimal id);
    List<E> findAll();
    E getByPrimaryKey(BigDecimal id);
}
