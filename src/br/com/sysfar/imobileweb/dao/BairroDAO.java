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

		broker.setSQL("SELECT ID, DESCRICAO, CIDADE_ID FROM BAIRRO WHERE ID = ?", model.getId());

		return (BairroModel) broker.getObjectBean(BairroModel.class, "id", "descricao", "cidadeModel.id");
	}
	
	public BairroModel obter(final String descricao) {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT ID, DESCRICAO, CIDADE_ID FROM BAIRRO WHERE DESCRICAO = ?", descricao);
		
		return (BairroModel) broker.getObjectBean(BairroModel.class, "id", "descricao", "cidadeModel.id");
	}

	@SuppressWarnings("unchecked")
	public List<BairroModel> pesquisar(final BairroModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO, CIDADE_ID, (SELECT C.NOME FROM CIDADE C WHERE C.ID = B.CIDADE_ID) FROM BAIRRO B WHERE SEM_ACENTOS(DESCRICAO) ILIKE SEM_ACENTOS(COALESCE(?, DESCRICAO)) ORDER BY DESCRICAO", Utilitario.getStringIlike(model.getDescricao(), true));

		return broker.getCollectionBean(BairroModel.class, "id", "descricao", "cidadeModel.id", "cidadeModel.nome");
	}

	public BairroModel inserir(final BairroModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("bairro_id_seq"));

		broker.setSQL("INSERT INTO BAIRRO (ID, DESCRICAO, CIDADE_ID) VALUES (?, ?, ?)", model.getId(), model.getDescricao(), model.getCidadeModel().getId());

		broker.execute();

		return model;
	}

	public BairroModel alterar(final BairroModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("UPDATE BAIRRO SET DESCRICAO = ?, CIDADE_ID = ? WHERE ID = ?", model.getDescricao(), model.getCidadeModel().getId(), model.getId());

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
