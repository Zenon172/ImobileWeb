package br.com.sysfar.imobileweb.dao;

import java.util.List;

import br.com.sysfar.imobileweb.model.OrigemModel;
import br.com.sysfar.imobileweb.util.Utilitario;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public final class OrigemDAO implements CrudDAO<OrigemModel> {

	public OrigemModel obter(final OrigemModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO FROM ORIGEM WHERE ID = ?", model.getId());

		return (OrigemModel) broker.getObjectBean(OrigemModel.class, "id", "descricao");
	}

	@SuppressWarnings("unchecked")
	public List<OrigemModel> pesquisar(final OrigemModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO FROM ORIGEM WHERE SEM_ACENTOS(DESCRICAO) ILIKE SEM_ACENTOS(COALESCE(?, DESCRICAO)) ORDER BY DESCRICAO", Utilitario.getStringIlike(model.getDescricao(), true));

		return broker.getCollectionBean(OrigemModel.class, "id", "descricao");
	}

	public OrigemModel inserir(final OrigemModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("origem_id_seq"));

		broker.setSQL("INSERT INTO ORIGEM (ID, DESCRICAO) VALUES (?, ?)", model.getId(), model.getDescricao());

		broker.execute();

		return model;
	}

	public OrigemModel alterar(final OrigemModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("UPDATE ORIGEM SET DESCRICAO = ? WHERE ID = ?", model.getDescricao(), model.getId());

		broker.execute();

		return model;
	}

	public OrigemModel excluir(final OrigemModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("DELETE FROM ORIGEM WHERE ID = ?", model.getId());

		broker.execute();

		return model;
	}

}
