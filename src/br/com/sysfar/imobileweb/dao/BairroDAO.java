package br.com.sysfar.imobileweb.dao;

import java.util.List;

import br.com.sysfar.imobileweb.model.BairroModel;
import br.com.sysfar.imobileweb.util.Utilitario;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public final class BairroDAO implements CrudDAO<BairroModel> {

	public BairroModel obter(final BairroModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO FROM BAIRRO WHERE ID = ?", model.getId());

		return (BairroModel) broker.getObjectBean(BairroModel.class, "id", "descricao");
	}
	
	public BairroModel obter(final String descricao) {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT ID, DESCRICAO FROM BAIRRO WHERE DESCRICAO = ?", descricao);
		
		return (BairroModel) broker.getObjectBean(BairroModel.class, "id", "descricao");
	}

	@SuppressWarnings("unchecked")
	public List<BairroModel> pesquisar(final BairroModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO FROM BAIRRO WHERE SEM_ACENTOS(DESCRICAO) ILIKE SEM_ACENTOS(COALESCE(?, DESCRICAO)) ORDER BY DESCRICAO", Utilitario.getStringIlike(model.getDescricao(), true));

		return broker.getCollectionBean(BairroModel.class, "id", "descricao");
	}

	public BairroModel inserir(final BairroModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("bairro_id_seq"));

		broker.setSQL("INSERT INTO BAIRRO (ID, DESCRICAO) VALUES (?, ?)", model.getId(), model.getDescricao());

		broker.execute();

		return model;
	}

	public BairroModel alterar(final BairroModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("UPDATE BAIRRO SET DESCRICAO = ? WHERE ID = ?", model.getDescricao(), model.getId());

		broker.execute();

		return model;
	}

	public BairroModel excluir(final BairroModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("DELETE FROM BAIRRO WHERE ID = ?", model.getId());

		broker.execute();

		return model;
	}

}
