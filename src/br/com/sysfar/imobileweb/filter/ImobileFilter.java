package br.com.sysfar.imobileweb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sysfar.imobileweb.model.UsuarioModel;
import br.com.sysfar.imobileweb.util.Constantes;

public class ImobileFilter implements Filter {

	private static final String AJAX = "XMLHttpRequest";

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest r = (HttpServletRequest) request;

		r.setCharacterEncoding("UTF-8");

		response.setCharacterEncoding("UTF-8");

		String uri = r.getRequestURI();

		if (uri != null) {

			uri = uri.substring(uri.lastIndexOf("/"), uri.length());

		}

		UsuarioModel usuario = (UsuarioModel) r.getSession().getAttribute(Constantes.SESSION_USUARIO_LOGADO);

		HttpServletResponse res = (HttpServletResponse) response;

		if (usuario != null) {

			if (uri.equals("/login.xhtml")) {

				try {

					chain.doFilter(request, response);

				} catch (ServletException e) {

					if (e.getCause().getClass().equals(IllegalArgumentException.class)) {

						((HttpServletResponse) response).sendRedirect(r.getContextPath() + "/pages/dashboard.xhtml");

					} else {

						throw new ServletException(e);

					}

				} catch (NullPointerException e) {

					((HttpServletResponse) response).sendRedirect(r.getContextPath() + "/pages/login.xhtml");

				}

			} else if (uri.equals("/dashboard.xhtml")) {

				try {

					chain.doFilter(request, response);

				} catch (ServletException e) {

					if (e.getCause().getClass().equals(IllegalArgumentException.class)) {

						((HttpServletResponse) response).sendRedirect(r.getContextPath() + "/pages/dashboard.xhtml");

					} else {

						throw new ServletException(e);

					}

				} catch (NullPointerException e) {

					((HttpServletResponse) response).sendRedirect(r.getContextPath() + "/pages/login.xhtml");

				}

			} else {

				((HttpServletResponse) response).sendRedirect(r.getContextPath() + "/pages/dashboard.xhtml");

			}

		} else {

			if (uri.equals("/painelChamadas.xhtml") || uri.equals("/login.xhtml") || uri.equals("/loginExpirado.xhtml")) {

				try {

					chain.doFilter(request, response);

				} catch (ServletException e) {

					if (e.getCause().getClass().equals(IllegalArgumentException.class)) {

						((HttpServletResponse) response).sendRedirect(r.getContextPath() + "/pages/login.xhtml");

					} else if (e.getCause().getClass().equals(NullPointerException.class)) {

						((HttpServletResponse) response).sendRedirect(r.getContextPath() + "/pages/login.xhtml");

					} else {

						throw new ServletException(e);

					}

				} catch (Exception e) {
					
					((HttpServletResponse) response).sendRedirect(r.getContextPath() + "/pages/login.xhtml");
					
				}

			} else {

				if (isAjaxRequest(r)) {

					res.getWriter().print(redirectAjaxRequest(r, "/pages/login.xhtml?expired=true"));

					res.flushBuffer();

				} else {

					((HttpServletResponse) response).sendRedirect(r.getContextPath() + "/pages/login.xhtml");

				}
			}

		}

	}

	private boolean isAjaxRequest(HttpServletRequest request) {
		return AJAX.equals(request.getHeader("X-Requested-With"));
	}

	private String redirectAjaxRequest(HttpServletRequest request, String page) {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<partial-response><redirect url=\"").append(request.getContextPath()).append(page).append("\"/></partial-response>");
		return sb.toString();
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.getProperties().put("org.apache.el.parser.COERCE_TO_ZERO", "false");
	}

}
