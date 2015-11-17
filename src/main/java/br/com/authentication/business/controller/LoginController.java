package br.com.authentication.business.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.authentication.presentation.web.form.LoginForm;

@Controller
public class LoginController {
	

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(LoginForm loginForm) {
        return "login";
    }
    
}
