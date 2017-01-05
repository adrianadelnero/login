package br.com.authentication.integration.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.authentication.business.entity.Login;
import br.com.authentication.integration.repository.LoginRepository;

/**
*
* @author aromano
*/
@Transactional
@Repository(value = "loginRepository")
public class LoginDao extends CrudMasterDetailJpaDAO<Login> implements LoginRepository {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginDao.class);

	public LoginDao() {
		super(Login.class);
	}
	
	@Override
	public Login buscarPorEmail(String email){
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Login> cq = cb.createQuery(Login.class);

		Root<Login> r = cq.from(Login.class);
		
		List<Predicate> predicateList = new ArrayList<Predicate>();
		
		predicateList.add(cb.equal(r.get("email"), email));
		
		Predicate[] predicateArray = predicateList.toArray(new Predicate[predicateList.size()]);
		
		cq
		.select(r)
		.where(predicateArray.length == 1 ? predicateArray[0] : cb.and(predicateArray));
		
		try{
			return entityManager.createQuery(cq).getSingleResult();
		}catch (NoResultException emptyEx) {
	        LOGGER.debug("LoginDao.buscarPor () - Nao foi encontrado cadastro com o email informado");
	        return null;
	    }

	}
}
