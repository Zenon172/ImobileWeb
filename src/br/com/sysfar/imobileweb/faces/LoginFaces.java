package br.com.sysfar.imobileweb.faces;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sysfar.imobileweb.dao.UsuarioDAO;
import br.com.sysfar.imobileweb.model.UsuarioModel;
import br.com.sysfar.imobileweb.util.Constantes;
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
	private UsuarioModel usuarioModel;
	private UsuarioModel usuarioAuxiliarModel;
	private String novaSenha;
	private String novaSenhaConfirmacao;

	@PostConstruct
	protected void clearFields() {
		this.usuarioModel = new UsuarioModel();
		this.usuarioDAO = new UsuarioDAO();
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

		return "logar";

	}

	public String trocarSenha() {

		if (!this.validaAlterarSenha()) {
			return null;
		}

		try {

			this.usuarioDAO.alterarSenha(this.usuarioAuxiliarModel, this.novaSenha);

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

		if (!TSUtil.isEmpty(this.novaSenhaConfirmacao) && !TSUtil.isEmpty(this.novaSenha) && !this.novaSenha.equals(this.novaSenhaConfirmacao)) {
			this.addErrorMessage("Senhas não conferem");
			return false;
		}

		return true;

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
