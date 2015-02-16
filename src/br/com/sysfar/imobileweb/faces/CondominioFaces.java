package br.com.sysfar.imobileweb.faces;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.com.sysfar.imobileweb.dao.ComboDAO;
import br.com.sysfar.imobileweb.dao.CondominioDAO;
import br.com.sysfar.imobileweb.dao.CrudDAO;
import br.com.sysfar.imobileweb.model.BairroModel;
import br.com.sysfar.imobileweb.model.CondominioModel;
import br.com.sysfar.imobileweb.util.Utilitario;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "condominioFaces")
public class CondominioFaces extends CrudFaces<CondominioModel> {

	private CondominioDAO condominioDAO;
	
	private ComboDAO comboDAO;
	
	private List<SelectItem> comboBairro;

	@Override
	@PostConstruct
	protected void clearFields() {

		this.crudModel = new CondominioModel();
		this.crudModel.setBairroModel(new BairroModel());
		
		this.crudPesquisaModel = new CondominioModel();
		this.crudPesquisaModel.setBairroModel(new BairroModel());

		this.condominioDAO = new CondominioDAO();
		this.comboDAO = new ComboDAO();
		
		this.comboBairro = super.initCombo(this.comboDAO.pesquisarBairro(), "id", "descricao");

	}
	
	@Override
	protected void prePersist() {
		this.crudModel.setUsuarioCadastroModel(Utilitario.getUsuarioLogado());
		this.crudModel.setDataCadastro(new Date());
	}

	@Override
	protected CrudDAO<CondominioModel> getCrudDAO() {
		return this.condominioDAO;
	}

	public List<SelectItem> getComboBairro() {
		return comboBairro;
	}

	public void setComboBairro(List<SelectItem> comboBairro) {
		this.comboBairro = comboBairro;
	}

}
