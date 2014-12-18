package br.com.sysfar.imobileweb.dao;

import java.util.List;

import br.com.sysfar.imobileweb.model.MenuModel;
import br.com.sysfar.imobileweb.model.UsuarioModel;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public final class MenuDAO {

	@SuppressWarnings("unchecked")
	public List<MenuModel> pesquisarMenuFilhos(final MenuModel menuModel, final UsuarioModel usuarioModel) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT M.ID, M.DESCRICAO, M.URL, M.MENU_ID, (SELECT M2.DESCRICAO FROM MENU M2 WHERE M2.ID = M.MENU_ID), M.MANAGED_BEAN, M.ORDEM, TRUE, TRUE, TRUE, TRUE, M.ICONE FROM MENU M ");

		return broker.getCollectionBean(MenuModel.class, "id", "descricao", "url", "menuModel.id", "menuModel.descricao", "managedBean", "ordem", "flagInserir", "flagAlterar", "flagExcluir", "flagImprimir", "icone");

	}

	@SuppressWarnings("unchecked")
	public List<MenuModel> pesquisarMenuFilho(final MenuModel menuModel, final UsuarioModel usuarioModel) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT M.ID, M.DESCRICAO, M.URL, M.MENU_ID, (SELECT M2.DESCRICAO FROM MENU M2 WHERE M2.ID = M.MENU_ID), M.MANAGED_BEAN, M.ORDEM, TRUE, TRUE, TRUE, TRUE, M.ICONE FROM MENU M WHERE M.MENU_ID = ?", menuModel.getId());

		return broker.getCollectionBean(MenuModel.class, "id", "descricao", "url", "menuModel.id", "menuModel.descricao", "managedBean", "ordem", "flagInserir", "flagAlterar", "flagExcluir", "flagImprimir", "icone");

	}

	@SuppressWarnings("unchecked")
	public List<MenuModel> pesquisarMenusADM(final MenuModel menuModel) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT M.ID, M.DESCRICAO, M.URL, M.MENU_ID, (SELECT M2.DESCRICAO FROM MENU M2 WHERE M2.ID = M.MENU_ID), M.MANAGED_BEAN, M.ORDEM, TRUE, TRUE, TRUE, TRUE, M.ICONE FROM MENU M ");

		return broker.getCollectionBean(MenuModel.class, "id", "descricao", "url", "menuModel.id", "menuModel.descricao", "managedBean", "ordem", "flagInserir", "flagAlterar", "flagExcluir", "flagImprimir", "icone");

	}

	@SuppressWarnings("unchecked")
	public List<MenuModel> pesquisarMenuRaiz(final UsuarioModel usuarioModel) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT M.ID, M.DESCRICAO, M.URL, M.MENU_ID, (SELECT M2.DESCRICAO FROM MENU M2 WHERE M2.ID = M.MENU_ID), M.MANAGED_BEAN, M.ORDEM, TRUE, TRUE, TRUE, TRUE, M.ICONE FROM MENU M WHERE M.MENU_ID IS NULL");

		return broker.getCollectionBean(MenuModel.class, "id", "descricao", "url", "menuModel.id", "menuModel.descricao", "managedBean", "ordem", "flagInserir", "flagAlterar", "flagExcluir", "flagImprimir", "icone");

	}

	@SuppressWarnings("unchecked")
	public List<MenuModel> pesquisarCombo() {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT M.ID, ARVORE_MENU(M.ID, '') FROM MENU M WHERE M.FLAG_ATIVO AND M.MENU_ID IS NOT NULL");

		return broker.getCollectionBean(MenuModel.class, "id", "descricao");

	}

}
