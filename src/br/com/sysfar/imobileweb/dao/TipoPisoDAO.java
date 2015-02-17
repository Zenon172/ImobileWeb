package br.com.sysfar.imobileweb.dao;

import java.util.List;

import br.com.sysfar.imobileweb.model.TipoPisoModel;
import br.com.sysfar.imobileweb.util.Utilitario;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public final class TipoPisoDAO implements CrudDAO<TipoPisoModel> {

	public TipoPisoModel obter(final TipoPisoModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO FROM TIPO_PISO WHERE ID = ?", model.getId());

		return (TipoPisoModel) broker.getObjectBean(TipoPisoModel.class, "id", "descricao");
	}

	@SuppressWarnings("unchecked")
	public List<TipoPisoModel> pesquisar(final TipoPisoModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO FROM TIPO_PISO WHERE SEM_ACENTOS(DESCRICAO) ILIKE SEM_ACENTOS(COALESCE(?, DESCRICAO)) ORDER BY DESCRICAO", Utilitario.getStringIlike(model.getDescricao(), true));

		return broker.getCollectionBean(TipoPisoModel.class, "id", "descricao");
	}

	public TipoPisoModel inserir(final TipoPisoModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("tipo_piso_id_seq"));

		broker.setSQL("INSERT INTO TIPO_PISO (ID, DESCRICAO) VALUES (?, ?)", model.getId(), model.getDescricao());

		broker.execute();

		return model;
	}

	public TipoPisoModel alterar(final TipoPisoModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("UPDATE TIPO_PISO SET DESCRICAO = ? WHERE ID = ?", model.getDescricao(), model.getId());

		broker.execute();

		return model;
	}

	public TipoPisoModel excluir(final TipoPisoModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("DELETE FROM TIPO_PISO WHERE ID = ?", model.getId());

		broker.execute();

		return model;
	}

}
