package br.com.sysfar.imobileweb.faces;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.com.sysfar.imobileweb.dao.BairroDAO;
import br.com.sysfar.imobileweb.dao.ComboDAO;
import br.com.sysfar.imobileweb.dao.CrudDAO;
import br.com.sysfar.imobileweb.model.BairroModel;
import br.com.sysfar.imobileweb.model.CidadeModel;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "bairroFaces")
public class BairroFaces extends CrudFaces<BairroModel> {

	private BairroDAO bairroDAO;
	
	private List<SelectItem> comboCidade;

	@Override
	@PostConstruct
	protected void clearFields() {

		this.crudModel = new BairroModel();
		this.crudModel.setCidadeModel(new CidadeModel());
		
		this.crudPesquisaModel = new BairroModel();
		this.crudPesquisaModel.setCidadeModel(new CidadeModel());

		this.bairroDAO = new BairroDAO();
		
		this.comboCidade = super.initCombo(new ComboDAO().pesquisarCidade(), "id", "nome");

	}

	@Override
	protected CrudDAO<BairroModel> getCrudDAO() {
		return this.bairroDAO;
	}

	public List<SelectItem> getComboCidade() {
		return comboCidade;
	}

	public void setComboCidade(List<SelectItem> comboCidade) {
		this.comboCidade = comboCidade;
	}

}
