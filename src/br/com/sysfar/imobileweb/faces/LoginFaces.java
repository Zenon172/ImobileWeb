package br.com.sysfar.imobileweb.faces;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.relatec.util.ConstantesRelatec;
import br.com.sysfar.imobileweb.dao.ConfiguracaoDAO;
import br.com.sysfar.imobileweb.dao.UsuarioDAO;
import br.com.sysfar.imobileweb.model.ConfiguracaoModel;
import br.com.sysfar.imobileweb.model.UsuarioModel;
import br.com.sysfar.imobileweb.util.Constantes;
import br.com.sysfar.imobileweb.util.GerenciadorCaminhoArquivoUtil;
import br.com.topsys.constant.TSConstant;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSCryptoUtil;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.faces.TSMainFaces;
import br.com.topsys.web.util.TSFacesUtil;

@ViewScoped
@SuppressWarnings("serial")
@ManagedBean(name = "loginFaces")
public final class LoginFaces extends TSMainFaces {

	private UsuarioDAO usuarioDAO;
	private ConfiguracaoDAO configuracaoDAO;
	private UsuarioModel usuarioModel;
	private UsuarioModel usuarioAuxiliarModel;
	private String novaSenha;
	private String novaSenhaConfirmacao;

	@PostConstruct
	protected void clearFields() {
		this.usuarioModel = new UsuarioModel();
		this.usuarioDAO = new UsuarioDAO();
		this.configuracaoDAO = new ConfiguracaoDAO();
		this.limparNovaSenha();
	}

	public String acessar() {

		UsuarioModel usuario = this.usuarioDAO.obterPorLogin(this.usuarioModel);

		if(TSUtil.isEmpty(usuario) || !usuario.getSenha().equals(TSCryptoUtil.gerarHash(this.usuarioModel.getSenha(), TSConstant.CRIPTOGRAFIA_MD5))){
			super.addErrorMessage("Login ou Senha inválidos");
			return null;
		}
		
		TSFacesUtil.getRequest().getSession().invalidate();

		TSFacesUtil.addObjectInSession(Constantes.SESSION_USUARIO_LOGADO, usuario);
		TSFacesUtil.addObjectInSession(ConstantesRelatec.GRUPO_LOGADO_ID, usuario.getGrupoModel().getId());
		TSFacesUtil.addObjectInSession(Constantes.SESSION_FLAG_ACESSO_JAR, true);
		
		ConfiguracaoModel configSistema = this.configuracaoDAO.obter(new ConfiguracaoModel(Constantes.CONFIGURACAO_PASTA_UPLOAD_RELATORIO));
		
		GerenciadorCaminhoArquivoUtil.setPastaCaminhoRelatorio(configSistema.getValor());
		
		configSistema = this.configuracaoDAO.obter(new ConfiguracaoModel(Constantes.CONFIGURACAO_PASTA_UPLOAD_ARQUIVO));
		
		GerenciadorCaminhoArquivoUtil.setPastaCaminhoArquivo(configSistema.getValor());

		return "logar";

	}

	public String trocarSenha() {

		if (!this.validaAlterarSenha()) {
			return null;
		}

		try {

			this.usuarioDAO.alterarSenha(this.usuarioAuxiliarModel, TSCryptoUtil.gerarHash(this.novaSenha, TSConstant.CRIPTOGRAFIA_MD5));

			this.addInfoMessage("Senha alterada com sucesso!");

		} catch (TSApplicationException e) {

			super.throwException(e);

		}

		this.limparNovaSenha();

		return null;

	}

	private void limparNovaSenha() {
		this.usuarioAuxiliarModel = new UsuarioModel();
		this.novaSenha = "";
		this.novaSenhaConfirmacao = "";
	}

	private boolean validaAlterarSenha() {

		boolean valida = true;
		
		if (TSUtil.isEmpty(this.usuarioAuxiliarModel.getLogin())){
			super.addErrorMessage("Usuário: Campo obrigatório");
			valida = false;
		}
		
		if (TSUtil.isEmpty(this.usuarioAuxiliarModel.getSenha())){
			super.addErrorMessage("Senha: Campo obrigatório");
			valida = false;
		}
		
		if (TSUtil.isEmpty(this.novaSenha)){
			super.addErrorMessage("Nova senha: Campo obrigatório");
			valida = false;
		}
		
		if (TSUtil.isEmpty(this.novaSenhaConfirmacao)){
			super.addErrorMessage("Confirmação da nova senha: Campo obrigatório");
			valida = false;
		}
		
		if (!TSUtil.isEmpty(this.novaSenhaConfirmacao) && !TSUtil.isEmpty(this.novaSenha) && !this.novaSenha.equals(this.novaSenhaConfirmacao)) {
			this.addErrorMessage("Senhas não conferem");
			valida = false;
		}

		return valida;

	}

	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

	public UsuarioModel getUsuarioAuxiliarModel() {
		return usuarioAuxiliarModel;
	}

	public void setUsuarioAuxiliarModel(UsuarioModel usuarioAuxiliarModel) {
		this.usuarioAuxiliarModel = usuarioAuxiliarModel;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getNovaSenhaConfirmacao() {
		return novaSenhaConfirmacao;
	}

	public void setNovaSenhaConfirmacao(String novaSenhaConfirmacao) {
		this.novaSenhaConfirmacao = novaSenhaConfirmacao;
	}

}
