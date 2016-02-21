package br.com.authentication.business.handler;


import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component(value = "notAuthorizedEntryPoint")
public class NotAuthorizedEntryPoint implements AuthenticationEntryPoint {
    /**
     * Chamado pelo Spring Security quando a sessao esta invalida.
     * @param request
     * @param response
     * @param exception
     * @throws IOException
     * @throws ServletException 
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

}