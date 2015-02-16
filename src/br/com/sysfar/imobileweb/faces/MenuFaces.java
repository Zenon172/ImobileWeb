package br.com.sysfar.imobileweb.faces;import java.util.ArrayList;import java.util.List;import javax.annotation.PostConstruct;import javax.faces.bean.ManagedBean;import javax.faces.bean.SessionScoped;import br.com.sysfar.imobileweb.dao.MenuDAO;import br.com.sysfar.imobileweb.model.GrupoModel;import br.com.sysfar.imobileweb.model.MenuModel;import br.com.sysfar.imobileweb.model.UsuarioModel;import br.com.sysfar.imobileweb.util.Utilitario;import br.com.topsys.util.TSUtil;import br.com.topsys.web.faces.TSMainFaces;import br.com.topsys.web.util.TSFacesUtil;@SuppressWarnings("serial")@SessionScoped@ManagedBean(name = "menuFaces")public final class MenuFaces extends TSMainFaces {	private MenuDAO menuDAO;		private String nomeTela;	private String tela;	private MenuModel menuModel;	private List<MenuModel> menus;	@PostConstruct	protected void clearFields() {		this.menuDAO = new MenuDAO();				setNomeTela("Área de Trabalho");		this.menuModel = new MenuModel();		this.menuModel.setDescricao("Área de Trabalho");		UsuarioModel usuarioLogado = Utilitario.getUsuarioLogado();		if (usuarioLogado.getFlagAdministrador()) {			this.menus = this.menuDAO.pesquisarMenusADM(this.menuModel);			this.pesquisarMenusFilhosADM(this.menus);		} else {			this.menus = this.menuDAO.pesquisarMenuRaiz(usuarioLogado);			this.pesquisarMenusFilhos(this.menus, usuarioLogado.getGrupoModel());		}		if (!TSUtil.isEmpty(usuarioLogado.getGrupoModel()) && !TSUtil.isEmpty(usuarioLogado.getGrupoModel().getId())) {			MenuModel menu = usuarioLogado.getGrupoModel().getMenuInicialModel();			menu = obterMenu(this.menus, menu);			if (!TSUtil.isEmpty(menu)) {				this.menuModel = menu;				this.redirecionar();			}		}	}	private MenuModel obterMenu(List<MenuModel> menus, MenuModel menuProcurado) {		if (menus.contains(menuProcurado)) {			return menus.get(menus.indexOf(menuProcurado));		} else {			for (MenuModel menu : menus) {				MenuModel menuModel = obterMenu(menu.getSubMenus(), menuProcurado);				if (!TSUtil.isEmpty(menuModel)) {					return menuModel;				}			}		}		return null;	}	public void pesquisarMenusFilhosADM(List<MenuModel> menus) {		for (MenuModel model : menus) {			model.setSubMenus(this.menuDAO.pesquisarMenusADM(model));			if (!TSUtil.isEmpty(model.getSubMenus())) {				this.pesquisarMenusFilhosADM(model.getSubMenus());			}		}	}	public void pesquisarMenusFilhos(List<MenuModel> menus, GrupoModel grupo) {		List<MenuModel> subMenusVazio = new ArrayList<MenuModel>();				for (MenuModel model : menus) {			model.setSubMenus(this.menuDAO.pesquisarMenuFilho(model, grupo));			if (!TSUtil.isEmpty(model.getSubMenus())) {				this.pesquisarMenusFilhos(model.getSubMenus(), grupo);			} else {								subMenusVazio.add(model);							}		}				menus.removeAll(subMenusVazio);			}	public String redirecionar() {		if (!TSUtil.isEmpty(this.menuModel.getManagedBean())) {			TSFacesUtil.removeManagedBeanInSession(this.menuModel.getManagedBean());		}		setTela(this.menuModel.getUrl());		setNomeTela("Área de Trabalho > " + (!TSUtil.isEmpty(menuModel.getMenuModel().getDescricao()) ? menuModel.getMenuModel().getDescricao() + " > " : "") + menuModel.getDescricao());		return SUCESSO;	}	private MenuModel obterMenu(MenuModel menuModel, List<MenuModel> menus) {		for (MenuModel model : menus) {			if (model.equals(menuModel)) {				return model;			} else if (!TSUtil.isEmpty(model.getSubMenus())) {				MenuModel temp = this.obterMenu(menuModel, model.getSubMenus());				if (!TSUtil.isEmpty(temp)) {					return temp;				}			}		}		return null;	}	public String escolherMenu(MenuModel model) {		MenuModel menu = this.obterMenu(model, this.menus);		if (!TSUtil.isEmpty(menu)) {			this.menuModel = menu;			return redirecionar();		}		return null;	}	public String logout() {		if (!TSUtil.isEmpty(TSFacesUtil.getRequest()) && !TSUtil.isEmpty(TSFacesUtil.getRequest().getSession())) {			TSFacesUtil.getRequest().getSession().invalidate();		}		return "login";	}	public String getNomeTela() {		return nomeTela;	}	public void setNomeTela(String nomeTela) {		this.nomeTela = nomeTela;	}	public String getTela() {		return tela;	}	public void setTela(String tela) {		this.tela = tela;	}	public MenuModel getMenuModel() {		return menuModel;	}	public void setMenuModel(MenuModel menuModel) {		this.menuModel = menuModel;	}	public List<MenuModel> getMenus() {		return menus;	}	public void setMenus(List<MenuModel> menus) {		this.menus = menus;	}}