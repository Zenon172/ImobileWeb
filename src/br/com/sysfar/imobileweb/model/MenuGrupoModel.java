package br.com.sysfar.imobileweb.model;

import java.io.Serializable;

import br.com.topsys.util.TSUtil;

@SuppressWarnings("serial")
public class MenuGrupoModel implements Serializable{

	private Long id;
	
	private MenuModel menuModel;
	
	private GrupoModel grupoModel;
	
	private Boolean flagInserir;
	
	private Boolean flagAlterar;
	
	private Boolean flagExcluir;
	
	private Boolean flagAtalho;

	public Long getId() {
		return TSUtil.tratarLong(id);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MenuModel getMenuModel() {
		return menuModel;
	}

	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}

	public GrupoModel getGrupoModel() {
		return grupoModel;
	}

	public void setGrupoModel(GrupoModel grupoModel) {
		this.grupoModel = grupoModel;
	}

	public Boolean getFlagInserir() {
		return flagInserir;
	}

	public void setFlagInserir(Boolean flagInserir) {
		this.flagInserir = flagInserir;
	}

	public Boolean getFlagAlterar() {
		return flagAlterar;
	}

	public void setFlagAlterar(Boolean flagAlterar) {
		this.flagAlterar = flagAlterar;
	}

	public Boolean getFlagExcluir() {
		return flagExcluir;
	}

	public void setFlagExcluir(Boolean flagExcluir) {
		this.flagExcluir = flagExcluir;
	}

	public Boolean getFlagAtalho() {
		return flagAtalho;
	}

	public void setFlagAtalho(Boolean flagAtalho) {
		this.flagAtalho = flagAtalho;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((grupoModel == null) ? 0 : grupoModel.hashCode());
		result = prime * result + ((menuModel == null) ? 0 : menuModel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuGrupoModel other = (MenuGrupoModel) obj;
		if (grupoModel == null) {
			if (other.grupoModel != null)
				return false;
		} else if (!grupoModel.equals(other.grupoModel))
			return false;
		if (menuModel == null) {
			if (other.menuModel != null)
				return false;
		} else if (!menuModel.equals(other.menuModel))
			return false;
		return true;
	}
}
