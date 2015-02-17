package br.com.sysfar.imobileweb.dao;

import java.util.List;

import br.com.sysfar.imobileweb.model.TipoImovelModel;
import br.com.sysfar.imobileweb.util.Utilitario;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public final class TipoImovelDAO implements CrudDAO<TipoImovelModel> {

	public TipoImovelModel obter(final TipoImovelModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO FROM TIPO_IMOVEL WHERE ID = ?", model.getId());

		return (TipoImovelModel) broker.getObjectBean(TipoImovelModel.class, "id", "descricao");
	}

	@SuppressWarnings("unchecked")
	public List<TipoImovelModel> pesquisar(final TipoImovelModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO FROM TIPO_IMOVEL WHERE SEM_ACENTOS(DESCRICAO) ILIKE SEM_ACENTOS(COALESCE(?, DESCRICAO)) ORDER BY DESCRICAO", Utilitario.getStringIlike(model.getDescricao(), true));

		return broker.getCollectionBean(TipoImovelModel.class, "id", "descricao");
	}

	public TipoImovelModel inserir(final TipoImovelModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("tipo_imovel_id_seq"));

		broker.setSQL("INSERT INTO TIPO_IMOVEL (ID, DESCRICAO) VALUES (?, ?)", model.getId(), model.getDescricao());

		broker.execute();

		return model;
	}

	public TipoImovelModel alterar(final TipoImovelModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("UPDATE TIPO_IMOVEL SET DESCRICAO = ? WHERE ID = ?", model.getDescricao(), model.getId());

		broker.execute();

		return model;
	}

	public TipoImovelModel excluir(final TipoImovelModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("DELETE FROM TIPO_IMOVEL WHERE ID = ?", model.getId());

		broker.execute();

		return model;
	}

}
