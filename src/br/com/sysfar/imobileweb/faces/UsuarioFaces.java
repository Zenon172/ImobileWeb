package br.com.sysfar.imobileweb.faces;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.com.sysfar.imobileweb.dao.CrudDAO;
import br.com.sysfar.imobileweb.dao.GrupoDAO;
import br.com.sysfar.imobileweb.dao.UsuarioDAO;
import br.com.sysfar.imobileweb.model.GrupoModel;
import br.com.sysfar.imobileweb.model.UsuarioModel;
import br.com.topsys.constant.TSConstant;
import br.com.topsys.util.TSCryptoUtil;
import br.com.topsys.util.TSUtil;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "usuarioFaces")
public class UsuarioFaces extends CrudFaces<UsuarioModel> {

	private UsuarioDAO usuarioDAO;
	private GrupoDAO grupoDAO;

	private String senha;
	private String repetirSenha;

	private List<SelectItem> grupos;

	@Override
	@PostConstruct
	protected void clearFields() {

		this.crudModel = new UsuarioModel();
		this.crudModel.setGrupoModel(new GrupoModel());

		this.crudPesquisaModel = new UsuarioModel();
		this.crudPesquisaModel.setGrupoModel(new GrupoModel());

		this.senha = null;
		this.repetirSenha = null;

		this.usuarioDAO = new UsuarioDAO();
		this.grupoDAO = new GrupoDAO();

		this.grupos = super.initCombo(this.grupoDAO.pesquisarCombo(), "id", "descricao");

	}

	@Override
	protected boolean validaCampos() {

		boolean valida = true;
		
		if(!TSUtil.isEmpty(this.crudModel.getEmail()) && !TSUtil.isEmailValid(this.crudModel.getEmail())){
			super.addErrorMessage("E-mail inválido");
			valida = false;
		}

		if (TSUtil.isEmpty(this.crudModel.getId())) {

			if (TSUtil.isEmpty(this.senha)) {
				super.addErrorMessage("Senha: Campo obrigatório");
				valida = false;
			}

			if (TSUtil.isEmpty(this.repetirSenha)) {
				super.addErrorMessage("Repetir Senha: Campo obrigatório");
				valida = false;
			}

		} else {

			if (!TSUtil.isEmpty(this.senha) && TSUtil.isEmpty(this.repetirSenha)) {
				super.addErrorMessage("Repetir Senha: Campo obrigatório");
				valida = false;
			}

			if (!TSUtil.isEmpty(this.repetirSenha) && TSUtil.isEmpty(this.senha)) {
				super.addErrorMessage("Senha: Campo obrigatório");
				valida = false;
			}

			if (!TSUtil.isEmpty(this.repetirSenha) && !TSUtil.isEmpty(this.senha) && !this.senha.equals(this.repetirSenha)) {
				super.addErrorMessage("Senhas não conferem");
				valida = false;
			}

		}

		return valida;
	}

	@Override
	protected void prePersist() {

		if (!TSUtil.isEmpty(this.senha)) {
			this.crudModel.setSenha(TSCryptoUtil.gerarHash(this.senha, TSConstant.CRIPTOGRAFIA_MD5));
		}

	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getRepetirSenha() {
		return repetirSenha;
	}

	public void setRepetirSenha(String repetirSenha) {
		this.repetirSenha = repetirSenha;
	}

	public List<SelectItem> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<SelectItem> grupos) {
		this.grupos = grupos;
	}

	@Override
	protected CrudDAO<UsuarioModel> getCrudDAO() {
		return this.usuarioDAO;
	}

}
