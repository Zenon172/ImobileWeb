package br.com.sysfar.imobileweb.dao;

import java.sql.Timestamp;
import java.util.List;

import br.com.sysfar.imobileweb.model.EdificioModel;
import br.com.sysfar.imobileweb.util.Utilitario;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public final class EdificioDAO implements CrudDAO<EdificioModel> {

	public EdificioModel obter(final EdificioModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO, CONDOMINIO_ID, QTD_PAVIMENTOS, QTD_APARTAMENTOS_ANDAR, USUARIO_CADASTRO_ID, (SELECT U.NOME FROM USUARIO U WHERE U.ID = E.USUARIO_CADASTRO_ID), DATA_CADASTRO FROM EDIFICIO E WHERE ID = ?", model.getId());

		return (EdificioModel) broker.getObjectBean(EdificioModel.class, "id", "descricao", "condominioModel.id", "qtdPavimentos", "qtdApartamentosAndar", "usuarioCadastroModel.id", "usuarioCadastroModel.nome", "dataCadastro");
	}

	@SuppressWarnings("unchecked")
	public List<EdificioModel> pesquisar(final EdificioModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO, CONDOMINIO_ID, (SELECT C.DESCRICAO FROM CONDOMINIO C WHERE C.ID = E.CONDOMINIO_ID), QTD_PAVIMENTOS, QTD_APARTAMENTOS_ANDAR FROM EDIFICIO E WHERE SEM_ACENTOS(DESCRICAO) ILIKE SEM_ACENTOS(COALESCE(?, DESCRICAO)) ORDER BY E.DESCRICAO", Utilitario.getStringIlike(model.getDescricao(), true));

		return broker.getCollectionBean(EdificioModel.class, "id", "descricao", "condominioModel.id", "condominioModel.descricao", "qtdPavimentos", "qtdApartamentosAndar");
	}

	@SuppressWarnings("unchecked")
	public List<EdificioModel> pesquisarCombo() {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO FROM EDIFICIO ORDER BY DESCRICAO");

		return broker.getCollectionBean(EdificioModel.class, "id", "descricao");
	}

	public EdificioModel inserir(final EdificioModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("edificio_id_seq"));

		broker.setSQL("INSERT INTO EDIFICIO (ID, DESCRICAO, CONDOMINIO_ID, QTD_PAVIMENTOS, QTD_APARTAMENTOS_ANDAR, USUARIO_CADASTRO_ID, DATA_CADASTRO) VALUES (?, ?, ?, ?, ?, ?, ?)", model.getId(), model.getDescricao(), model.getCondominioModel().getId(), model.getQtdPavimentos(), model.getQtdApartamentosAndar(), model.getUsuarioCadastroModel().getId(), new Timestamp(model.getDataCadastro().getTime()));

		broker.execute();

		return model;
	}

	public EdificioModel alterar(final EdificioModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("UPDATE EDIFICIO SET DESCRICAO = ?, CONDOMINIO_ID = ?, QTD_PAVIMENTOS = ?, QTD_APARTAMENTOS_ANDAR = ?, USUARIO_CADASTRO_ID = ?, DATA_CADASTRO = ? WHERE ID = ?", model.getDescricao(), model.getCondominioModel().getId(), model.getQtdPavimentos(), model.getQtdApartamentosAndar(), model.getUsuarioCadastroModel().getId(), new Timestamp(model.getDataCadastro().getTime()), model.getId());

		broker.execute();

		return model;
	}

	public EdificioModel excluir(final EdificioModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("DELETE FROM EDIFICIO WHERE ID = ?", model.getId());

		broker.execute();

		return model;
	}

}
