package br.com.autentication.integration.dao;

import org.springframework.stereotype.Repository;
import br.com.autentication.business.entity.Login;

/**
*
* @author aromano
*/
@Repository(value = "loginRepository")
public class LoginDao extends CrudMasterDetailJpaDAO<Login> {

	public LoginDao() {
		super(Login.class);
	}

}
