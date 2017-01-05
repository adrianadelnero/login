package br.com.authentication.business.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.authentication.presentation.web.form.LoginForm;
import br.com.authentication.utils.Constantes;

@Controller
public class LoginController {
	

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(LoginForm loginForm, HttpSession httpSession ) {
    	
    	httpSession.setAttribute(Constantes.USUARIO_LOGADO, loginForm);
    	
        return "home";
    }
    
}
