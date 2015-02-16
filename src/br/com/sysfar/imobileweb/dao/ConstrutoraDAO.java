package br.com.sysfar.imobileweb.dao;

import java.sql.Timestamp;
import java.util.List;

import br.com.sysfar.imobileweb.model.ConstrutoraModel;
import br.com.sysfar.imobileweb.util.Utilitario;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public final class ConstrutoraDAO implements CrudDAO<ConstrutoraModel> {

	public ConstrutoraModel obter(final ConstrutoraModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO, EMAIL, TELEFONE, USUARIO_CADASTRO_ID, (SELECT U.NOME FROM USUARIO U WHERE U.ID = C.USUARIO_CADASTRO_ID), C.DATA_CADASTRO FROM CONSTRUTORA C WHERE ID = ?", model.getId());

		return (ConstrutoraModel) broker.getObjectBean(ConstrutoraModel.class, "id", "descricao", "email", "telefone", "usuarioCadastroModel.id", "usuarioCadastroModel.nome", "dataCadastro");
	}

	@SuppressWarnings("unchecked")
	public List<ConstrutoraModel> pesquisar(final ConstrutoraModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO, EMAIL, TELEFONE FROM CONSTRUTORA WHERE SEM_ACENTOS(DESCRICAO) ILIKE SEM_ACENTOS(COALESCE(?, DESCRICAO)) ORDER BY DESCRICAO", Utilitario.getStringIlike(model.getDescricao(), true));

		return broker.getCollectionBean(ConstrutoraModel.class, "id", "descricao", "email", "telefone");
	}

	@SuppressWarnings("unchecked")
	public List<ConstrutoraModel> pesquisarCombo() {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO, EMAIL, TELEFONE FROM CONSTRUTORA ORDER BY DESCRICAO");

		return broker.getCollectionBean(ConstrutoraModel.class, "id", "descricao", "email", "telefone");
	}

	public ConstrutoraModel inserir(final ConstrutoraModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("construtora_id_seq"));

		broker.setSQL("INSERT INTO CONSTRUTORA (ID, DESCRICAO, EMAIL, TELEFONE, USUARIO_CADASTRO_ID, DATA_CADASTRO) VALUES (?, ?, ?, ?, ?, ?)", model.getId(), model.getDescricao(), model.getEmail(), model.getTelefone(), model.getUsuarioCadastroModel().getId(), new Timestamp(model.getDataCadastro().getTime()));

		broker.execute();

		return model;
	}

	public ConstrutoraModel alterar(final ConstrutoraModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("UPDATE CONSTRUTORA SET DESCRICAO = ?, EMAIL = ?, TELEFONE = ?, USUARIO_CADASTRO_ID = ?, DATA_CADASTRO = ? WHERE ID = ?", model.getDescricao(), model.getEmail(), model.getTelefone(), model.getUsuarioCadastroModel().getId(), new Timestamp(model.getDataCadastro().getTime()), model.getId());

		broker.execute();

		return model;
	}

	public ConstrutoraModel excluir(final ConstrutoraModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("DELETE FROM CONSTRUTORA WHERE ID = ?", model.getId());

		broker.execute();

		return model;
	}

}
