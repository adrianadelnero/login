package br.com.authentication.security;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CORSSpringSecurityFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        response.setHeader("Access-Control-Allow-Origin", handleHttpResponseSplitting(request.getHeader("Origin")));
        response.setHeader("Content-Type", "text/plain");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization, From");
        response.setHeader("Access-Control-Expose-Headers", "Location");

        if (!"OPTIONS".equals(request.getMethod())) {
            chain.doFilter(req, response);
        }
    }

    protected String handleHttpResponseSplitting(String str) {
        String result;
        if (str == null) {
            result = "";
        } else {
            int idxCRLF = str.indexOf("\r\n"); //CRLF
            result = ((idxCRLF >= 0) ? str.substring(0, idxCRLF) : str);
        }
        return result;
    }
}
