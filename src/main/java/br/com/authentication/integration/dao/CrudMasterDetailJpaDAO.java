package br.com.authentication.integration.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.authentication.integration.repository.CrudMasterDetailRepository;

public class CrudMasterDetailJpaDAO<E> implements CrudMasterDetailRepository<E> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CrudMasterDetailJpaDAO.class);
    protected final Class<E> entityClass; 
    
    @PersistenceContext
    protected EntityManager entityManager;
    
    public CrudMasterDetailJpaDAO(Class<E> entityClass) {
    	this.entityClass = entityClass;
    }
    
    @Override
    public E getByPrimaryKey(long id) {
        try {
            LOGGER.info("findById class {} id {}", entityClass, id);
            return entityManager.createNamedQuery(entityClass.getSimpleName() + ".findById", entityClass)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (PersistenceException pe) {
        	LOGGER.error("getByPrimaryKey() - ",pe);
            throw pe;
        }
    }
    
    @Override
    public List<E> findAll() {
        try {
            LOGGER.info("findAll class {}", entityClass);
            return entityManager.createNamedQuery(entityClass.getSimpleName() + ".findAll", entityClass)
                    .getResultList();
        } catch (PersistenceException pe) {
        	LOGGER.error("getByPrimaryKey() - ",pe);
            throw pe;
        }
    }
    
    @Override
    public void insert(E e) {
        try {
            LOGGER.info("insert class {} entity {}", entityClass, e);
            entityManager.persist(e);
        } catch (PersistenceException pe) {
        	LOGGER.error("getByPrimaryKey() - ",pe);
            throw pe;
        }
    }

    @Override
    public E update(E e) {
        try {
            LOGGER.info("update class {} entity {}", entityClass, e);
            return entityManager.merge(e);
        } catch (PersistenceException pe) {
        	LOGGER.error("getByPrimaryKey() - ",pe);
            throw pe;
        }
    }

    @Override
    public void delete(E e) {
        try {
            LOGGER.info("delete class {} entity {}", entityClass, e);
            entityManager.remove(e);
        } catch (PersistenceException pe) {
        	LOGGER.error("getByPrimaryKey() - ",pe);
            throw pe;
        }
    }
}
