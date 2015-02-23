package br.com.sysfar.imobileweb.dao;

import java.sql.Timestamp;
import java.util.List;

import br.com.sysfar.imobileweb.model.AtividadeModel;
import br.com.sysfar.imobileweb.model.CaptacaoModel;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public final class AtividadeDAO implements CrudDAO<AtividadeModel> {

	public AtividadeModel obter(final AtividadeModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT A.ID, A.RESPONSAVEL_ID, (SELECT U.NOME FROM USUARIO U WHERE U.ID = A.RESPONSAVEL_ID), A.STATUS_ATIVIDADE_ID, (SELECT SA.DESCRICAO FROM STATUS_ATIVIDADE SA WHERE SA.ID = A.STATUS_ATIVIDADE_ID), A.OBSERVACAO, A.RETORNO, A.DATA_INICIAL, A.DATA_FINAL, A.DATA_CADASTRO, A.USUARIO_CADASTRO_ID, (SELECT U.NOME FROM USUARIO U WHERE U.ID = A.USUARIO_CADASTRO_ID), A.DATA_ATUALIZACAO, A.USUARIO_ATUALIZACAO_ID, (SELECT U.NOME FROM USUARIO U WHERE U.ID = A.USUARIO_ATUALIZACAO_ID), A.FLAG_ATIVO, A.CAPTACAO_ID, A.IMOVEL_ID FROM ATIVIDADE A WHERE ID = ?", model.getId());

		return (AtividadeModel) broker.getObjectBean(AtividadeModel.class, "id", "responsavelModel.id", "responsavelModel.nome", "statusAtividadeModel.id", "statusAtividadeModel.descricao", "observacao", "retorno", "dataInicial", "dataFinal", "dataCadastro", "usuarioCadastroModel.id", "usuarioCadastroModel.nome", "dataAtualizacao", "usuarioAtualizacaoModel.id", "usuarioAtualizacaoModel.nome", "flagAtivo", "captacaoModel.id", "imovelModel.id");
	}

	@SuppressWarnings("unchecked")
	public List<AtividadeModel> pesquisar(final CaptacaoModel model) {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT A.ID, A.RESPONSAVEL_ID, (SELECT U.NOME FROM USUARIO U WHERE U.ID = A.RESPONSAVEL_ID), A.STATUS_ATIVIDADE_ID, (SELECT SA.DESCRICAO FROM STATUS_ATIVIDADE SA WHERE SA.ID = A.STATUS_ATIVIDADE_ID), A.OBSERVACAO, A.RETORNO, A.DATA_INICIAL, A.DATA_FINAL, A.DATA_CADASTRO, A.USUARIO_CADASTRO_ID, (SELECT U.NOME FROM USUARIO U WHERE U.ID = A.USUARIO_CADASTRO_ID), A.DATA_ATUALIZACAO, A.USUARIO_ATUALIZACAO_ID, (SELECT U.NOME FROM USUARIO U WHERE U.ID = A.USUARIO_ATUALIZACAO_ID), A.FLAG_ATIVO, A.CAPTACAO_ID, A.IMOVEL_ID FROM ATIVIDADE A WHERE A.CAPTACAO_ID = ?", model.getId());
		
		return broker.getCollectionBean(AtividadeModel.class, "id", "responsavelModel.id", "responsavelModel.nome", "statusAtividadeModel.id", "statusAtividadeModel.descricao", "observacao", "retorno", "dataInicial", "dataFinal", "dataCadastro", "usuarioCadastroModel.id", "usuarioCadastroModel.nome", "dataAtualizacao", "usuarioAtualizacaoModel.id", "usuarioAtualizacaoModel.nome", "flagAtivo", "captacaoModel.id", "imovelModel.id");
	}
	
	@SuppressWarnings("unchecked")
	public List<AtividadeModel> pesquisar(final AtividadeModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT A.ID, A.RESPONSAVEL_ID, (SELECT U.NOME FROM USUARIO U WHERE U.ID = A.RESPONSAVEL_ID), A.STATUS_ATIVIDADE_ID, (SELECT SA.DESCRICAO FROM STATUS_ATIVIDADE SA WHERE SA.ID = A.STATUS_ATIVIDADE_ID), A.OBSERVACAO, A.RETORNO, A.DATA_INICIAL, A.DATA_FINAL, A.DATA_CADASTRO, A.USUARIO_CADASTRO_ID, (SELECT U.NOME FROM USUARIO U WHERE U.ID = A.USUARIO_CADASTRO_ID), A.DATA_ATUALIZACAO, A.USUARIO_ATUALIZACAO_ID, (SELECT U.NOME FROM USUARIO U WHERE U.ID = A.USUARIO_ATUALIZACAO_ID), A.FLAG_ATIVO, A.CAPTACAO_ID, A.IMOVEL_ID FROM ATIVIDADE A WHERE A.FLAG_ATIVO AND A.RESPONSAVEL_ID = COALESCE(?, A.RESPONSAVEL_ID) AND A.STATUS_ATIVIDADE_ID = COALESCE(?, A.STATUS_ATIVIDADE_ID) AND DATE(A.DATA_INICIAL) BETWEEN DATE(?) AND DATE(?) ORDER BY A.ID", model.getResponsavelModel().getId(), model.getStatusAtividadeModel().getId(), model.getDataInicial(), model.getDataFinal());

		return broker.getCollectionBean(AtividadeModel.class, "id", "responsavelModel.id", "responsavelModel.nome", "statusAtividadeModel.id", "statusAtividadeModel.descricao", "observacao", "retorno", "dataInicial", "dataFinal", "dataCadastro", "usuarioCadastroModel.id", "usuarioCadastroModel.nome", "dataAtualizacao", "usuarioAtualizacaoModel.id", "usuarioAtualizacaoModel.nome", "flagAtivo", "captacaoModel.id", "imovelModel.id");
	}

	public AtividadeModel inserir(final AtividadeModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("atividade_id_seq"));

		Timestamp dataFinal = null;

		if (!TSUtil.isEmpty(model.getDataFinal())) {
			dataFinal = new Timestamp(model.getDataFinal().getTime());
		}

		broker.setSQL("INSERT INTO ATIVIDADE (ID, DATA_CADASTRO, USUARIO_CADASTRO_ID, RESPONSAVEL_ID, OBSERVACAO, STATUS_ATIVIDADE_ID, DATA_INICIAL, DATA_FINAL, CAPTACAO_ID, IMOVEL_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", model.getId(), new Timestamp(model.getDataCadastro().getTime()), model.getUsuarioCadastroModel().getId(), model.getResponsavelModel().getId(), model.getObservacao(), model.getStatusAtividadeModel().getId(), new Timestamp(model.getDataInicial().getTime()), dataFinal, model.getCaptacaoModel().getId(), model.getImovelModel().getId());

		broker.execute();

		return model;
	}

	public AtividadeModel alterar(final AtividadeModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		Timestamp dataFinal = null;

		if (!TSUtil.isEmpty(model.getDataFinal())) {
			dataFinal = new Timestamp(model.getDataFinal().getTime());
		}

		broker.setSQL("UPDATE ATIVIDADE SET OBSERVACAO = ?, RETORNO = ?, STATUS_ATIVIDADE_ID = ?, DATA_INICIAL = ?, DATA_FINAL = ?, DATA_ATUALIZACAO = ?, USUARIO_ATUALIZACAO_ID = ? WHERE ID = ?", model.getObservacao(), model.getRetorno(), model.getStatusAtividadeModel().getId(), new Timestamp(model.getDataInicial().getTime()), dataFinal, new Timestamp(model.getDataAtualizacao().getTime()), model.getUsuarioAtualizacaoModel().getId(), model.getId());

		broker.execute();

		return model;
	}

	public AtividadeModel excluir(final AtividadeModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("UPDATE ATIVIDADE SET FLAG_ATIVO = FALSE WHERE ID = ?", model.getId());

		broker.execute();

		return model;
	}

}
