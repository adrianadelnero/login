package br.com.authentication.integration.repository;

import br.com.authentication.business.entity.Login;

/**
*
* @author aromano
*/
public interface LoginRepository extends CrudMasterDetailRepository<Login> {
	
	Login buscarPorEmail(String email);

}
