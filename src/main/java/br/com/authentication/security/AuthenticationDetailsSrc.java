package br.com.authentication.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.stereotype.Component;

@Component("authenticationDetailsSrc")
public class AuthenticationDetailsSrc implements AuthenticationDetailsSource<HttpServletRequest, AuthenticationDetails> {

	@Override
	public AuthenticationDetails buildDetails(HttpServletRequest req) {
		return new AuthenticationDetails(req);
	}

}
