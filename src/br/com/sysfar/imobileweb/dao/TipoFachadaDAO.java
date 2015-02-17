package br.com.sysfar.imobileweb.dao;

import java.util.List;

import br.com.sysfar.imobileweb.model.TipoFachadaModel;
import br.com.sysfar.imobileweb.util.Utilitario;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public final class TipoFachadaDAO implements CrudDAO<TipoFachadaModel> {

	public TipoFachadaModel obter(final TipoFachadaModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO FROM TIPO_FACHADA WHERE ID = ?", model.getId());

		return (TipoFachadaModel) broker.getObjectBean(TipoFachadaModel.class, "id", "descricao");
	}

	@SuppressWarnings("unchecked")
	public List<TipoFachadaModel> pesquisar(final TipoFachadaModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO FROM TIPO_FACHADA WHERE SEM_ACENTOS(DESCRICAO) ILIKE SEM_ACENTOS(COALESCE(?, DESCRICAO)) ORDER BY DESCRICAO", Utilitario.getStringIlike(model.getDescricao(), true));

		return broker.getCollectionBean(TipoFachadaModel.class, "id", "descricao");
	}

	public TipoFachadaModel inserir(final TipoFachadaModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("tipo_fachada_id_seq"));

		broker.setSQL("INSERT INTO TIPO_FACHADA (ID, DESCRICAO) VALUES (?, ?)", model.getId(), model.getDescricao());

		broker.execute();

		return model;
	}

	public TipoFachadaModel alterar(final TipoFachadaModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("UPDATE TIPO_FACHADA SET DESCRICAO = ? WHERE ID = ?", model.getDescricao(), model.getId());

		broker.execute();

		return model;
	}

	public TipoFachadaModel excluir(final TipoFachadaModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("DELETE FROM TIPO_FACHADA WHERE ID = ?", model.getId());

		broker.execute();

		return model;
	}

}
