package br.com.sysfar.imobileweb.dao;

import java.util.List;

import br.com.sysfar.imobileweb.model.StatusClienteModel;
import br.com.sysfar.imobileweb.util.Utilitario;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public final class StatusClienteDAO implements CrudDAO<StatusClienteModel> {

	public StatusClienteModel obter(final StatusClienteModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO, FLAG_ATIVO, FLAG_FINALIZADO FROM STATUS_CLIENTE WHERE ID = ?", model.getId());

	return (StatusClienteModel) broker.getObjectBean(StatusClienteModel.class, "id", "descricao", "flagAtivo", "flagFinalizado");
	}

	@SuppressWarnings("unchecked")
	public List<StatusClienteModel> pesquisar(final StatusClienteModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO, FLAG_ATIVO FROM STATUS_CLIENTE WHERE SEM_ACENTOS(DESCRICAO) ILIKE SEM_ACENTOS(COALESCE(?, DESCRICAO)) AND FLAG_ATIVO = ? AND COALESCE(FLAG_FINALIZADO, FALSE) = ? ORDER BY DESCRICAO", Utilitario.getStringIlike(model.getDescricao(), true), model.getFlagAtivo(), model.getFlagFinalizado());

		return broker.getCollectionBean(StatusClienteModel.class, "id", "descricao", "flagAtivo");
	}

	public StatusClienteModel inserir(final StatusClienteModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("status_cliente_id_seq"));

		broker.setSQL("INSERT INTO STATUS_CLIENTE (ID, DESCRICAO, FLAG_ATIVO, FLAG_FINALIZADO) VALUES (?, ?, ?, ?)", model.getId(), model.getDescricao(), model.getFlagAtivo(), model.getFlagFinalizado());

		broker.execute();

		return model;
	}

	public StatusClienteModel alterar(final StatusClienteModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("UPDATE STATUS_CLIENTE SET DESCRICAO = ?, FLAG_ATIVO = ?, FLAG_FINALIZADO = ? WHERE ID = ?", model.getDescricao(), model.getFlagAtivo(), model.getFlagFinalizado(), model.getId());

		broker.execute();

		return model;
	}

	public StatusClienteModel excluir(final StatusClienteModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("DELETE FROM STATUS_CLIENTE WHERE ID = ?", model.getId());

		broker.execute();

		return model;
	}

}
