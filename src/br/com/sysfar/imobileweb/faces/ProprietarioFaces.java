package br.com.sysfar.imobileweb.faces;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.com.sysfar.imobileweb.dao.ComboDAO;
import br.com.sysfar.imobileweb.dao.CrudDAO;
import br.com.sysfar.imobileweb.dao.ImovelDAO;
import br.com.sysfar.imobileweb.dao.ProprietarioDAO;
import br.com.sysfar.imobileweb.model.OperadoraModel;
import br.com.sysfar.imobileweb.model.ProprietarioContatoModel;
import br.com.sysfar.imobileweb.model.ProprietarioModel;
import br.com.sysfar.imobileweb.util.Utilitario;
import br.com.topsys.util.TSUtil;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "proprietarioFaces")
public class ProprietarioFaces extends CrudFaces<ProprietarioModel> {

	private ProprietarioDAO proprietarioDAO;
	private ImovelDAO imovelDAO;
	private ComboDAO comboDAO;
	
	private List<SelectItem> comboOperadoras;

	@Override
	@PostConstruct
	protected void clearFields() {

		this.crudModel = new ProprietarioModel();
		this.crudModel.setFlagAtivo(Boolean.TRUE);
		this.crudModel.setContatos(new ArrayList<ProprietarioContatoModel>());
		this.crudModel.setContatosResponsavelVenda(new ArrayList<ProprietarioContatoModel>());

		this.carregarContatos();
		
		this.crudPesquisaModel = new ProprietarioModel();
		this.crudPesquisaModel.setFlagAtivo(Boolean.TRUE);

		this.proprietarioDAO = new ProprietarioDAO();
		this.imovelDAO = new ImovelDAO();
		this.comboDAO = new ComboDAO();
		
		this.comboOperadoras = super.initCombo(this.comboDAO.pesquisarOperadoras(), "id", "descricao");

	}
	
	private void carregarContatos() {

		for (int i = this.crudModel.getContatos().size(); i < 4; i++) {

			this.crudModel.getContatos().add(this.getProprietarioContatoInstance());

		}
		
		for (int i = this.crudModel.getContatosResponsavelVenda().size(); i < 4; i++) {
			
			this.crudModel.getContatosResponsavelVenda().add(this.getProprietarioContatoInstance());
			
		}

	}
	
	private ProprietarioContatoModel getProprietarioContatoInstance() {
		ProprietarioContatoModel model = new ProprietarioContatoModel();
		model.setProprietarioModel(this.crudModel);
		model.setOperadoraModel(new OperadoraModel());
		return model;
	}
	
	@Override
	protected boolean validaCampos() {
		
		boolean valida = true;
		
		if (!TSUtil.isEmpty(this.crudModel.getEmail()) && !TSUtil.isEmailValid(this.crudModel.getEmail())) {
			valida = false;
			super.addErrorMessage("E-mail inválido");
		}
		
		if (!TSUtil.isEmpty(this.crudModel.getEmailResponsavelVenda()) && !TSUtil.isEmailValid(this.crudModel.getEmailResponsavelVenda())) {
			valida = false;
			super.addErrorMessage("E-mail do responsável pela venda inválido");
		}
		
		return valida;
	}
	
	@Override
	protected void prePersist() {
		this.crudModel.setDataCadastro(new Date());
		this.crudModel.setUsuarioCadastroModel(Utilitario.getUsuarioLogado());
	}
	
	@Override
	protected void posDetail() {
		this.crudModel.setImoveis(this.imovelDAO.pesquisar(this.crudModel));
		this.crudModel.setContatos(this.proprietarioDAO.pesquisarContatos(this.crudModel, false));
		this.crudModel.setContatosResponsavelVenda(this.proprietarioDAO.pesquisarContatos(this.crudModel, true));;
		this.carregarContatos();
	}

	public List<SelectItem> getComboOperadoras() {
		return comboOperadoras;
	}

	public void setComboOperadoras(List<SelectItem> comboOperadoras) {
		this.comboOperadoras = comboOperadoras;
	}

	@Override
	protected CrudDAO<ProprietarioModel> getCrudDAO() {
		return this.proprietarioDAO;
	}

}
