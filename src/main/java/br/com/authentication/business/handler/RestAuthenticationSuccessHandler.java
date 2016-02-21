package br.com.authentication.business.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import br.com.authentication.business.entity.Login;

@Component(value = "restAuthenticationSuccessHandler")
public class RestAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
    	Login login = (Login)authentication.getDetails();        
        ObjectMapper om = new ObjectMapper();
        om.writeValue(httpServletResponse.getOutputStream(), login);
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
    }
}
