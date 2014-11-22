package br.edu.ifam.snaa.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifam.snaa.security.UsuarioSessao;
import br.gov.frameworkdemoiselle.util.Beans;

public class SnaaFilter implements Filter {

	private UsuarioSessao usuarioSessao;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		String url = httpServletRequest.getRequestURI();

		if (url.contains("/pages/publico") || url.contains("/login/login.jsf")
				|| url.contains("javax.faces.resource")) {

			chain.doFilter(request, response);
			return;
		}

		if (!usuarioSessao.isLogado()) {
			httpServletResponse.sendRedirect("/snaa/index.html");
			return;
		}

		chain.doFilter(request, response);
		
		System.out.println("OMK");
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		usuarioSessao = Beans.getReference(UsuarioSessao.class);

	}

}
