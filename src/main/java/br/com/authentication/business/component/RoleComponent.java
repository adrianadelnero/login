package br.com.authentication.business.component;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.com.authentication.business.entity.Login;

public interface RoleComponent {
	
	List<SimpleGrantedAuthority> getRoles(Login login);

}
