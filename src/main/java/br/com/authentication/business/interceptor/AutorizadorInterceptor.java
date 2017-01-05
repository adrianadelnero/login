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
		}else if(uri.endsWith("logout")){
			request.getSession().invalidate();
		}
		
		response.sendRedirect("/login");
		
		return false;
		
	}
	
	private boolean uriDeveSerIgnorada(String uri){
		return uri.contains("login") || uri.endsWith("about");
	}
	
	private boolean isUsuarioLogado(HttpServletRequest request){
		return request.getSession().getAttribute(Constantes.USUARIO_LOGADO) != null;
	}

}
