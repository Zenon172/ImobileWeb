package br.com.sysfar.imobileweb.model;

import java.util.List;

@SuppressWarnings("serial")
public class GrupoModel extends BaseModel {

	private String descricao;
	
	private MenuModel menuInicialModel;
	
	private List<MenuGrupoModel> menus;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public MenuModel getMenuInicialModel() {
		return menuInicialModel;
	}

	public void setMenuInicialModel(MenuModel menuInicialModel) {
		this.menuInicialModel = menuInicialModel;
	}

	public List<MenuGrupoModel> getMenus() {
		return menus;
	}

	public void setMenus(List<MenuGrupoModel> menus) {
		this.menus = menus;
	}
}
