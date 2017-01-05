package br.com.authentication.business.component;

import java.math.BigDecimal;

import br.com.authentication.business.entity.Login;


public interface LoginComponent extends CrudMasterDetailComponent<Login, BigDecimal> {

	void insert(Login login);
}
