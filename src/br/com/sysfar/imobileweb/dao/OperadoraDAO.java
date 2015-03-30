package br.com.sysfar.imobileweb.dao;

import java.util.List;

import br.com.sysfar.imobileweb.model.OperadoraModel;
import br.com.sysfar.imobileweb.util.Utilitario;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public final class OperadoraDAO implements CrudDAO<OperadoraModel> {

	public OperadoraModel obter(final OperadoraModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO, FLAG_ATIVO FROM OPERADORA WHERE ID = ?", model.getId());

		return (OperadoraModel) broker.getObjectBean(OperadoraModel.class, "id", "descricao", "flagAtivo");
	}

	@SuppressWarnings("unchecked")
	public List<OperadoraModel> pesquisar(final OperadoraModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO, FLAG_ATIVO FROM OPERADORA WHERE SEM_ACENTOS(DESCRICAO) ILIKE SEM_ACENTOS(COALESCE(?, DESCRICAO)) AND FLAG_ATIVO = ? ORDER BY DESCRICAO", Utilitario.getStringIlike(model.getDescricao(), true), model.getFlagAtivo());

		return broker.getCollectionBean(OperadoraModel.class, "id", "descricao", "flagAtivo");
	}

	public OperadoraModel inserir(final OperadoraModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("operadora_id_seq"));

		broker.setSQL("INSERT INTO OPERADORA (ID, DESCRICAO, FLAG_ATIVO) VALUES (?, ?, ?)", model.getId(), model.getDescricao(), model.getFlagAtivo());

		broker.execute();

		return model;
	}

	public OperadoraModel alterar(final OperadoraModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("UPDATE OPERADORA SET DESCRICAO = ?, FLAG_ATIVO = ? WHERE ID = ?", model.getDescricao(), model.getFlagAtivo(), model.getId());

		broker.execute();

		return model;
	}

	public OperadoraModel excluir(final OperadoraModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("DELETE FROM OPERADORA WHERE ID = ?", model.getId());

		broker.execute();

		return model;
	}

}
