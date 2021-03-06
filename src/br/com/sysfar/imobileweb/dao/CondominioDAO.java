package br.com.sysfar.imobileweb.dao;

import java.sql.Timestamp;
import java.util.List;

import br.com.sysfar.imobileweb.model.CondominioModel;
import br.com.sysfar.imobileweb.model.EdificioModel;
import br.com.sysfar.imobileweb.util.Utilitario;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public final class CondominioDAO implements CrudDAO<CondominioModel> {

	public CondominioModel obter(final CondominioModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO, QTD_TORRES, BAIRRO_ID, USUARIO_CADASTRO_ID, (SELECT U.NOME FROM USUARIO U WHERE U.ID = C.USUARIO_CADASTRO_ID), DATA_CADASTRO FROM CONDOMINIO C WHERE ID = ?", model.getId());

		return (CondominioModel) broker.getObjectBean(CondominioModel.class, "id", "descricao", "qtdTorres", "bairroModel.id", "usuarioCadastroModel.id", "usuarioCadastroModel.nome", "dataCadastro");
	}

	@SuppressWarnings("unchecked")
	public List<CondominioModel> pesquisar(final CondominioModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO, QTD_TORRES, BAIRRO_ID, (SELECT B.DESCRICAO FROM BAIRRO B WHERE B.ID = C.BAIRRO_ID) FROM CONDOMINIO C WHERE SEM_ACENTOS(DESCRICAO) ILIKE SEM_ACENTOS(COALESCE(?, DESCRICAO)) AND COALESCE(C.BAIRRO_ID, 0) = COALESCE(?, COALESCE(C.BAIRRO_ID, 0)) ORDER BY C.DESCRICAO", Utilitario.getStringIlike(model.getDescricao(), true), model.getBairroModel().getId());

		return broker.getCollectionBean(CondominioModel.class, "id", "descricao", "qtdTorres", "bairroModel.id", "bairroModel.descricao");
	}

	@SuppressWarnings("unchecked")
	public List<CondominioModel> pesquisarCombo() {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO FROM CONDOMINIO ORDER BY DESCRICAO");

		return broker.getCollectionBean(CondominioModel.class, "id", "descricao");
	}

	public CondominioModel inserir(final CondominioModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		model.setId(broker.getSequenceNextValue("condominio_id_seq"));

		broker.setSQL("INSERT INTO CONDOMINIO (ID, DESCRICAO, QTD_TORRES, BAIRRO_ID, USUARIO_CADASTRO_ID, DATA_CADASTRO) VALUES (?, ?, ?, ?, ?, ?)", model.getId(), model.getDescricao(), model.getQtdTorres(), model.getBairroModel().getId(), model.getUsuarioCadastroModel().getId(), new Timestamp(model.getDataCadastro().getTime()));

		broker.execute();

		EdificioDAO edificioDAO = new EdificioDAO();

		for (EdificioModel edificioModel : model.getEdificios()) {

			edificioModel.setUsuarioCadastroModel(model.getUsuarioCadastroModel());
			edificioModel.setDataCadastro(model.getDataCadastro());

			edificioDAO.inserir(edificioModel, broker);

		}

		broker.endTransaction();

		return model;
	}

	public CondominioModel alterar(final CondominioModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		broker.setSQL("UPDATE CONDOMINIO SET DESCRICAO = ?, QTD_TORRES = ?, BAIRRO_ID = ?, USUARIO_CADASTRO_ID = ?, DATA_CADASTRO = ? WHERE ID = ?", model.getDescricao(), model.getQtdTorres(), model.getBairroModel().getId(), model.getUsuarioCadastroModel().getId(), new Timestamp(model.getDataCadastro().getTime()), model.getId());

		broker.execute();

		EdificioDAO edificioDAO = new EdificioDAO();

		for (EdificioModel edificioModel : model.getEdificios()) {

			edificioModel.setUsuarioCadastroModel(model.getUsuarioCadastroModel());
			edificioModel.setDataCadastro(model.getDataCadastro());

			if (TSUtil.isEmpty(edificioModel.getId())) {

				edificioDAO.inserir(edificioModel, broker);

			} else {

				edificioDAO.alterar(edificioModel, broker);

			}

		}

		broker.endTransaction();

		return model;
	}

	public CondominioModel excluir(final CondominioModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("DELETE FROM CONDOMINIO WHERE ID = ?", model.getId());

		broker.execute();

		return model;
	}

}
