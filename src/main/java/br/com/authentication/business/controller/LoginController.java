package br.com.authentication.business.controller;

import java.math.BigDecimal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.authentication.business.component.LoginComponent;
import br.com.authentication.business.entity.Login;
import br.com.authentication.business.entity.Role;
import br.com.authentication.presentation.web.form.LoginForm;
import br.com.authentication.utils.Constantes;

@Controller
@RequestMapping("/api")
public class LoginController {
	
	private static final String URL_ADMIN = "/admin";
	private static final String URL_LOGIN = "/login";
	
	@Autowired
	private LoginComponent loginComponent;
	

    @RequestMapping(value = URL_LOGIN, method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String login(LoginForm loginForm, HttpSession httpSession ) {    	
    	httpSession.setAttribute(Constantes.USUARIO_LOGADO, loginForm);    	
        return "home";
    }
    
    @Secured(Role.ROLE_ADMIN)
    @RequestMapping(value = URL_ADMIN + URL_LOGIN, method = RequestMethod.POST, headers = {"content-type=multipart/form-data"} )
    @ResponseBody
    public void insert(@RequestBody Login login){
    	loginComponent.insert(login);
    }
    
    @Secured(Role.ROLE_ADMIN)
    @RequestMapping(value = URL_ADMIN + URL_LOGIN + "/{id}", method = RequestMethod.DELETE, headers = {"content-type=multipart/form-data"} )
    @ResponseBody
    public void delete(@RequestBody BigDecimal id){
    	loginComponent.delete(id);
    }
    
}
