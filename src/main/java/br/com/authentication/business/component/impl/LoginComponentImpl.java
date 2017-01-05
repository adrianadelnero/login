package br.com.authentication.business.component.impl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.authentication.business.component.EncryptionComponent;
import br.com.authentication.business.component.LoginComponent;
import br.com.authentication.business.entity.ApplicationLogin;
import br.com.authentication.business.entity.Login;
import br.com.authentication.business.entity.Role;
import br.com.authentication.business.entity.Status;
import br.com.authentication.integration.dao.LoginDao;
import br.com.authentication.integration.repository.LoginRepository;

/**
 *
 * @author aromano
 */
@Component(value = "loginComponent")
public class LoginComponentImpl implements LoginComponent ,AuthenticationProvider{

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginDao.class);
	private static final String MSG_AUTENTICACAO_INVALIDA = "Credencial inv\u00e1lida, verifique se o usu\u00e1rio e/ou senha est\u00e3o corretos.";
	
	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private EncryptionComponent encryptionComponent; 

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		String email = nvl(authentication.getPrincipal());
		String senha = nvl(authentication.getCredentials().toString());

        Collection<GrantedAuthority> authorities;
        Authentication auth = null;

		try{

			Login login = loginRepository.buscarPorEmail(email);

			if(login == null){
                LOGGER.info("Login not found");
                throw new UsernameNotFoundException(MSG_AUTENTICACAO_INVALIDA);
			}

			if(Status.INATIVO.getStatusCode().equalsIgnoreCase(login.getStatus())){
				throw new BadCredentialsException("Usu\u00e1rio Inativo.");
			}
			
			String senhaHex = encryptionComponent.convertToHex(senha);			
			if(!senhaHex.equals(login.getSenha())){
				LOGGER.info("Wrong password");
				throw new BadCredentialsException(MSG_AUTENTICACAO_INVALIDA);
			}
			
            authorities = getAuthorities(login);
            
            ApplicationLogin appUser = new ApplicationLogin(login.getEmail(),login.getSenha(), true, true, true, true,authorities);
            auth = new UsernamePasswordAuthenticationToken(appUser, senhaHex, authorities);

			return auth;
			
		}catch (Exception ex) {
			LOGGER.info(ex.getMessage(), ex);
			throw new BadCredentialsException(ex.getMessage(), ex);
		} 
	}
	
	@Override
	public void insert(Login login){
		
		String senhaHex = encryptionComponent.convertToHex(login.getSenha());	
		login.setSenha(senhaHex);
		
		this.insert(login);
	}
	
    private Collection<GrantedAuthority> getAuthorities(Login login){
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for(Role role : login.getRoles()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
            authorities.add(grantedAuthority);
        }
        return authorities;
    }

    @Override
    public boolean supports(Class<? extends Object> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

	protected String nvl(Object obj) {
		return obj instanceof String ? ((String)obj) : "";
	}

	@Override
	public Login getByPrimaryKey(BigDecimal idLogin) {
		return loginRepository.getByPrimaryKey(idLogin);
	}

	@Override
	public List<Login> findAll() {
		return loginRepository.findAll();
	}

	@Override
	public Login update(Login e) {
		return loginRepository.update(e);
	}

	@Override
	public void delete(BigDecimal idLogin) {
		loginRepository.delete(idLogin);		
	}
}
