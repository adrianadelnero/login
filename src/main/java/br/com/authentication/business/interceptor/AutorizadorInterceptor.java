package br.com.authentication.business.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.authentication.utils.Constantes;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
		throws Exception {
		
		String uri = request.getRequestURI();		
		
		if(isUsuarioLogado(request) || uriDeveSerIgnorada(uri)){
			return true;
		}
		
		response.sendRedirect("/login");
		
		return false;
		
	}
	
	private boolean uriDeveSerIgnorada(String uri){
		return uri.contains("login") || uri.contains("logout")	|| uri.endsWith("cadastro")	;
	}
	
	private boolean isUsuarioLogado(HttpServletRequest request){
		return request.getSession().getAttribute(Constantes.USUARIO_LOGADO) != null;
	}

}
