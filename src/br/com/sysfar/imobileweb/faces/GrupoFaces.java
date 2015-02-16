package br.com.sysfar.imobileweb.faces;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.com.sysfar.imobileweb.dao.CrudDAO;
import br.com.sysfar.imobileweb.dao.GrupoDAO;
import br.com.sysfar.imobileweb.dao.MenuDAO;
import br.com.sysfar.imobileweb.model.GrupoModel;
import br.com.sysfar.imobileweb.model.MenuGrupoModel;
import br.com.sysfar.imobileweb.model.MenuModel;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "grupoFaces")
public class GrupoFaces extends CrudFaces<GrupoModel> {

	private GrupoDAO grupoDAO;
	private MenuDAO menuDAO;

	private MenuModel menuSelecionadoModel;
	private MenuGrupoModel menuGrupoModel;

	private List<SelectItem> comboMenu;

	@Override
	@PostConstruct
	protected void clearFields() {

		this.crudModel = new GrupoModel();
		this.crudModel.setMenuInicialModel(new MenuModel());
		this.crudModel.setMenus(new ArrayList<MenuGrupoModel>());
		
		this.menuSelecionadoModel = new MenuModel();

		this.crudPesquisaModel = new GrupoModel();
		this.crudPesquisaModel.setMenuInicialModel(new MenuModel());

		this.grupoDAO = new GrupoDAO();
		this.menuDAO = new MenuDAO();

		this.comboMenu = super.initCombo(this.menuDAO.pesquisarCombo(), "id", "descricao");

	}

	@Override
	protected void posPersist() throws TSApplicationException {

		for (MenuGrupoModel menuGrupo : this.crudModel.getMenus()) {

			if (TSUtil.isEmpty(menuGrupo.getId())) {

				this.grupoDAO.inserir(menuGrupo);

			} else {

				this.grupoDAO.alterar(menuGrupo);

			}

		}
	}

	@Override
	protected void posDetail() {
		this.crudModel.setMenus(this.grupoDAO.pesquisarMenusGrupos(this.crudModel));
	}

	public String addMenu() {

		MenuGrupoModel model = new MenuGrupoModel();

		model.setMenuModel(this.menuDAO.obter(this.menuSelecionadoModel));
		model.setGrupoModel(this.crudModel);

		if (this.crudModel.getMenus().contains(model)) {
			super.addErrorMessage("Menu já adicionado anteriormente");
			return null;
		}

		this.crudModel.getMenus().add(model);

		return null;
	}

	public String removerMenu() {

		try {

			this.grupoDAO.excluir(this.menuGrupoModel);

			this.crudModel.getMenus().remove(this.menuGrupoModel);

		} catch (TSApplicationException e) {

			super.throwException(e);

		}

		return null;
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

	public MenuGrupoModel getMenuGrupoModel() {
		return menuGrupoModel;
	}

	public void setMenuGrupoModel(MenuGrupoModel menuGrupoModel) {
		this.menuGrupoModel = menuGrupoModel;
	}

	public MenuModel getMenuSelecionadoModel() {
		return menuSelecionadoModel;
	}

	public void setMenuSelecionadoModel(MenuModel menuSelecionadoModel) {
		this.menuSelecionadoModel = menuSelecionadoModel;
	}

}
