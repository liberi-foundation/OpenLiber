package br.com.openliber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

public class AutorizadorInterceptor implements HandlerInterceptor {
	
	private static final ArrayList<String> RECURSOS_BLOQUEADOS = new ArrayList<>(Arrays.asList("/upload"));
	private static final String[] RECURSOS_BLOQUEADOS_USUARIO = {"/login", "/cadastro"};
	private static final String PAGINA_ACESSO_NEGADO_PADRAO = "/login";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println(" >>> INFO:: Interceptor antes da chamada <<< ");
		
		if (request.getSession().getAttribute("usuarioLogado") == null) {
			for (String recurso : RECURSOS_BLOQUEADOS) {
				if (!request.getRequestURL().toString().endsWith(recurso)) {
					return true;
				}
			}
		} else {
			for (String recurso : RECURSOS_BLOQUEADOS_USUARIO) {
				System.out.println("<< info: dentro do for >>");
				if (request.getRequestURI().equals(recurso)) {
					System.out.println(">> info: dentro do if <<");
					request.getRequestDispatcher("redirect:/");

					return false;
				}
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
