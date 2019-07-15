package br.com.openliber;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class AutorizadorInterceptor implements HandlerInterceptor {
	private static final String[] RECURSOS_BLOQUEADOS = { "/upload" };
	private static final String PAGINA_ACESSO_NEGADO_PADRAO = "/login";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println(" >>> INFO:: Interceptor antes da chamada <<< ");

		for (String recurso : RECURSOS_BLOQUEADOS) {
			if (!request.getRequestURL().toString().endsWith(recurso)) {
				return true;
			}
		}

		if (request.getSession().getAttribute("usuarioLogado") == null) {
			request.setAttribute("acessoNegado", true);
			request.setAttribute("retorno", request.getRequestURI());
			request.getRequestDispatcher(PAGINA_ACESSO_NEGADO_PADRAO).forward(request, response);

			return false;
		}

		return true;
	}
}
