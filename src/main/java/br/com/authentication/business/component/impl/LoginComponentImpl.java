package br.com.authentication.business.component.impl;

import java.util.Collection;
import java.util.HashSet;
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

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private EncryptionComponent encryptionComponent; 

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		String email = nvl(authentication.getPrincipal());
		String senha = nvl(authentication.getAuthorities());
        Collection<GrantedAuthority> authorities;
        Authentication auth = null;

		try{

			Login login = loginRepository.buscarPorEmail(email);

			if(login == null){
                LOGGER.debug("Login not found");
                throw new UsernameNotFoundException("Usuário inválido");
			}

			if(Status.INATIVO.getStatusCode().equalsIgnoreCase(login.getStatus())){
				throw new BadCredentialsException("Usuario Inativo");
			}
			
			String senhaHex = encryptionComponent.convertToHex(senha);
			
			if(!senhaHex.equals(login.getSenha())){
				throw new BadCredentialsException("Usuario e/ou senha incorretos");
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

}
