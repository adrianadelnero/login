package br.com.authentication.business.component.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import br.com.authentication.business.component.CriptografiaComponent;
import br.com.authentication.business.component.LoginComponent;
import br.com.authentication.business.component.RoleComponent;
import br.com.authentication.business.entity.Login;
import br.com.authentication.business.entity.Status;
import br.com.authentication.integration.dao.LoginDao;
import br.com.authentication.integration.repository.LoginRepository;

/**
 *
 * @author aromano
 */
@Component(value = "loginComponent")
public class LoginComponentImpl implements LoginComponent {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginDao.class);

	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private RoleComponent roleComponent;

	@Autowired
	private CriptografiaComponent criptografiaComponent; 

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		String email = nvl(authentication.getPrincipal());
		String senha = nvl(authentication.getAuthorities());

		try{

			Login login = loginRepository.buscarPor(email);

			if(login == null){
				throw new BadCredentialsException("Login incorreto");
			}

			if(Status.INATIVO.getStatusCode().equalsIgnoreCase(login.getStatus())){
				throw new BadCredentialsException("Usuario Inativo");
			}
			
			String senhaHex = criptografiaComponent.convertToHex(senha);
			
			if(!senhaHex.equals(login.getSenha())){
				throw new BadCredentialsException("Usuario e/ou senha incorretos");
			}
			
			List<SimpleGrantedAuthority> grantedAuthorityList = roleComponent.getRoles(login);
			UsernamePasswordAuthenticationToken usernameAutenticationToken = new UsernamePasswordAuthenticationToken(login.getEmail(), senhaHex, grantedAuthorityList);
			usernameAutenticationToken.setDetails(login);
			
			return usernameAutenticationToken;
			
		}catch (Exception ex) {
			LOGGER.info(ex.getMessage(), ex);
			throw new BadCredentialsException(ex.getMessage(), ex);
		} 
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.equals(authentication);
	}

	protected String nvl(Object obj) {
		return obj instanceof String ? ((String)obj) : "";
	}

}
