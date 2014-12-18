package br.com.sysfar.imobileweb.faces;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.com.sysfar.imobileweb.dao.CrudDAO;
import br.com.sysfar.imobileweb.dao.GrupoDAO;
import br.com.sysfar.imobileweb.dao.MenuDAO;
import br.com.sysfar.imobileweb.model.GrupoModel;
import br.com.sysfar.imobileweb.model.MenuModel;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "grupoFaces")
public class GrupoFaces extends CrudFaces<GrupoModel> {

	private GrupoDAO grupoDAO;
	private MenuDAO menuDAO;

	private List<SelectItem> comboMenu;

	@Override
	@PostConstruct
	protected void clearFields() {

		this.crudModel = new GrupoModel();
		this.crudModel.setMenuInicialModel(new MenuModel());

		this.crudPesquisaModel = new GrupoModel();
		this.crudPesquisaModel.setMenuInicialModel(new MenuModel());

		this.grupoDAO = new GrupoDAO();
		this.menuDAO = new MenuDAO();

		this.comboMenu = super.initCombo(this.menuDAO.pesquisarCombo(), "id", "descricao");

	}

	public List<SelectItem> getComboMenu() {
		return comboMenu;
	}

	public void setComboMenu(List<SelectItem> comboMenu) {
		this.comboMenu = comboMenu;
	}

	@Override
	protected CrudDAO<GrupoModel> getCrudDAO() {
		return this.grupoDAO;
	}

}
