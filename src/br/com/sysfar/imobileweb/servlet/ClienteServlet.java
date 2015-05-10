package br.com.sysfar.imobileweb.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sysfar.imobileweb.dao.ClienteDAO;
import br.com.sysfar.imobileweb.dao.ConfiguracaoDAO;
import br.com.sysfar.imobileweb.dao.UsuarioDAO;
import br.com.sysfar.imobileweb.model.ClienteModel;
import br.com.sysfar.imobileweb.model.ConfiguracaoModel;
import br.com.sysfar.imobileweb.model.UsuarioModel;
import br.com.sysfar.imobileweb.util.Constantes;
import br.com.sysfar.imobileweb.util.EmailUtil;
import br.com.sysfar.imobileweb.util.LayoutEmailUtil;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSParseUtil;
import br.com.topsys.util.TSUtil;

@WebServlet("/ClienteServlet")
public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ClienteServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LayoutEmailUtil emailUtil = new LayoutEmailUtil();
		ConfiguracaoModel configuracao = new ConfiguracaoDAO().obter(new ConfiguracaoModel(Constantes.CONFIGURACAO_QUANTIDADE_DIAS_SEM_CONTATO_CLIENTE));
		
		for (UsuarioModel usuario : new UsuarioDAO().pesquisar(new UsuarioModel(true))) {

			List<ClienteModel> clientes = new ClienteDAO().pesquisarPendentesContato(new ClienteModel(usuario));

			try {

				if (!TSUtil.isEmpty(usuario.getEmail()) && !TSUtil.isEmpty(clientes)) {

					new EmailUtil().enviar(usuario.getEmail(), "Relação de clientes sem contato dia " + TSParseUtil.dateToString(new Date()), emailUtil.getLayoutEmailPendenciasClientes(usuario, clientes, configuracao));

				}

			} catch (TSApplicationException e) {

				e.printStackTrace();

			}

		}

		System.out.println("enviado email para corretores");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
