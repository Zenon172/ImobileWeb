package br.com.sysfar.imobileweb.model;

@SuppressWarnings("serial")
public class GrupoModel extends BaseModel {

	private String descricao;
	
	private MenuModel menuInicialModel;

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
}
