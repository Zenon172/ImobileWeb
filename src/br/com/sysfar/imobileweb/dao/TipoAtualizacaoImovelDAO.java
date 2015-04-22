package br.com.sysfar.imobileweb.dao;

import java.util.List;

import br.com.sysfar.imobileweb.model.TipoAtualizacaoImovelModel;
import br.com.sysfar.imobileweb.util.Utilitario;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public final class TipoAtualizacaoImovelDAO implements CrudDAO<TipoAtualizacaoImovelModel> {

	public TipoAtualizacaoImovelModel obter(final TipoAtualizacaoImovelModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO, FLAG_ATIVO FROM TIPO_ATUALIZACAO_IMOVEL WHERE ID = ?", model.getId());

		return (TipoAtualizacaoImovelModel) broker.getObjectBean(TipoAtualizacaoImovelModel.class, "id", "descricao", "flagAtivo");
	}

	@SuppressWarnings("unchecked")
	public List<TipoAtualizacaoImovelModel> pesquisar(final TipoAtualizacaoImovelModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO, FLAG_ATIVO FROM TIPO_ATUALIZACAO_IMOVEL WHERE SEM_ACENTOS(DESCRICAO) ILIKE SEM_ACENTOS(COALESCE(?, DESCRICAO)) AND FLAG_ATIVO = ? ORDER BY DESCRICAO", Utilitario.getStringIlike(model.getDescricao(), true), model.getFlagAtivo());

		return broker.getCollectionBean(TipoAtualizacaoImovelModel.class, "id", "descricao", "flagAtivo");
	}

	public TipoAtualizacaoImovelModel inserir(final TipoAtualizacaoImovelModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("tipo_atualizacao_imovel_id_seq"));

		broker.setSQL("INSERT INTO TIPO_ATUALIZACAO_IMOVEL (ID, DESCRICAO, FLAG_ATIVO) VALUES (?, ?, ?)", model.getId(), model.getDescricao(), model.getFlagAtivo());

		broker.execute();

		return model;
	}

	public TipoAtualizacaoImovelModel alterar(final TipoAtualizacaoImovelModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("UPDATE TIPO_ATUALIZACAO_IMOVEL SET DESCRICAO = ?, FLAG_ATIVO = ? WHERE ID = ?", model.getDescricao(), model.getFlagAtivo(), model.getId());

		broker.execute();

		return model;
	}

	public TipoAtualizacaoImovelModel excluir(final TipoAtualizacaoImovelModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("DELETE FROM TIPO_ATUALIZACAO_IMOVEL WHERE ID = ?", model.getId());

		broker.execute();

		return model;
	}

}
