package br.com.sysfar.imobileweb.dao;

import java.util.List;

import br.com.sysfar.imobileweb.model.GrupoModel;
import br.com.sysfar.imobileweb.model.MenuGrupoModel;
import br.com.sysfar.imobileweb.util.Utilitario;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public final class GrupoDAO implements CrudDAO<GrupoModel> {

	public GrupoModel obter(final GrupoModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO, MENU_INICIAL_ID FROM GRUPO WHERE ID = ?", model.getId());

		return (GrupoModel) broker.getObjectBean(GrupoModel.class, "id", "descricao", "menuInicialModel.id");
	}

	@SuppressWarnings("unchecked")
	public List<GrupoModel> pesquisar(final GrupoModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO, MENU_INICIAL_ID FROM GRUPO WHERE SEM_ACENTOS(DESCRICAO) ILIKE SEM_ACENTOS(COALESCE(?, DESCRICAO)) ORDER BY DESCRICAO", Utilitario.getStringIlike(model.getDescricao(), true));

		return broker.getCollectionBean(GrupoModel.class, "id", "descricao", "menuInicialModel.id");
	}
	
	@SuppressWarnings("unchecked")
	public List<MenuGrupoModel> pesquisarMenusGrupos(final GrupoModel model) {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT ID, MENU_ID, (SELECT ARVORE_MENU(M.ID, '') FROM MENU M WHERE M.ID = MG.MENU_ID), GRUPO_ID, FLAG_INSERIR, FLAG_ALTERAR, FLAG_EXCLUIR, FLAG_ATALHO FROM MENU_GRUPO MG WHERE MG.GRUPO_ID = ? ORDER BY MG.ID", model.getId());
		
		return broker.getCollectionBean(MenuGrupoModel.class, "id", "menuModel.id", "menuModel.descricao", "grupoModel.id", "flagInserir", "flagAlterar", "flagExcluir", "flagAtalho");
	}

	@SuppressWarnings("unchecked")
	public List<GrupoModel> pesquisarCombo() {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO FROM GRUPO ORDER BY DESCRICAO");

		return broker.getCollectionBean(GrupoModel.class, "id", "descricao");
	}

	public GrupoModel inserir(final GrupoModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("grupo_id_seq"));

		broker.setSQL("INSERT INTO GRUPO (ID, DESCRICAO, MENU_INICIAL_ID) VALUES (?, ?, ?)", model.getId(), model.getDescricao(), model.getMenuInicialModel().getId());

		broker.execute();

		return model;
	}

	public MenuGrupoModel inserir(final MenuGrupoModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("menu_grupo_id_seq"));

		broker.setSQL("INSERT INTO MENU_GRUPO (ID, MENU_ID, GRUPO_ID, FLAG_INSERIR, FLAG_ALTERAR, FLAG_EXCLUIR, FLAG_ATALHO) VALUES (?, ?, ?, ?, ?, ?, ?)", model.getId(), model.getMenuModel().getId(), model.getGrupoModel().getId(), model.getFlagInserir(), model.getFlagAlterar(), model.getFlagExcluir(), model.getFlagAtalho());

		broker.execute();

		return model;
	}

	public GrupoModel alterar(final GrupoModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("UPDATE GRUPO SET DESCRICAO = ?, MENU_INICIAL_ID = ? WHERE ID = ?", model.getDescricao(), model.getMenuInicialModel().getId(), model.getId());

		broker.execute();

		return model;
	}

	public MenuGrupoModel alterar(final MenuGrupoModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("UPDATE MENU_GRUPO SET FLAG_INSERIR = ?, FLAG_ALTERAR = ?, FLAG_EXCLUIR = ?, FLAG_ATALHO = ? WHERE ID = ?", model.getFlagInserir(), model.getFlagAlterar(), model.getFlagExcluir(), model.getFlagAtalho(), model.getId());

		broker.execute();

		return model;
	}

	public GrupoModel excluir(final GrupoModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("DELETE FROM GRUPO WHERE ID = ?", model.getId());

		broker.execute();

		return model;
	}
	
	public MenuGrupoModel excluir(final MenuGrupoModel model) throws TSApplicationException {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("DELETE FROM MENU_GRUPO WHERE ID = ?", model.getId());
		
		broker.execute();
		
		return model;
	}

}
