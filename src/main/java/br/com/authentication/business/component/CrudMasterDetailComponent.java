package br.com.authentication.business.component;

import java.util.List;

public interface CrudMasterDetailComponent<E,K> {

	E getByPrimaryKey(K pk);
	List<E> findAll();
	void insert(E e);
	E update(E e);
	void delete(K k);

}