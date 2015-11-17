package br.com.authentication.business.component.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import br.com.authentication.business.component.RoleComponent;
import br.com.authentication.business.entity.Login;
import br.com.authentication.business.entity.Role;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
*
* @author aromano
*/
@Component(value = "roleComponent")
public class RoleComponentImpl implements RoleComponent {
	
    private static final Function<String,SimpleGrantedAuthority> FUNCTION_STRING_TO_AUTHORITY = new Function<String, SimpleGrantedAuthority>() {
        @Override
        public SimpleGrantedAuthority apply(String nomeRole) {
            return new SimpleGrantedAuthority("ROLE_" + StringUtils.upperCase(nomeRole));
        }
    };
    
    public List<SimpleGrantedAuthority> getRoles(Login login){
    	
    	List<Role> roles = login.getRoleList();
    	List<String> permissions = new ArrayList<String>();
    	
    	for (Role role : roles) {
    		permissions.add(role.getRoleName());
		}
    	
    	return Lists.newArrayList(Iterables.transform(permissions, FUNCTION_STRING_TO_AUTHORITY));
    	
    }

}
