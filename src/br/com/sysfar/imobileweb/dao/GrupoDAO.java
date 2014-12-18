package br.com.sysfar.imobileweb.dao;

import java.util.List;

import br.com.sysfar.imobileweb.model.GrupoModel;
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

	public GrupoModel alterar(final GrupoModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("UPDATE GRUPO SET DESCRICAO = ?, MENU_INICIAL_ID = ? WHERE ID = ?", model.getDescricao(), model.getMenuInicialModel().getId(), model.getId());

		broker.execute();

		return model;
	}

	public GrupoModel excluir(final GrupoModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("DELETE FROM GRUPO WHERE ID = ?", model.getId());

		broker.execute();

		return model;
	}

}
