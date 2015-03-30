package br.com.sysfar.imobileweb.dao;

import java.sql.Timestamp;
import java.util.List;

import br.com.sysfar.imobileweb.model.CaptacaoContatoModel;
import br.com.sysfar.imobileweb.model.CaptacaoModel;
import br.com.sysfar.imobileweb.util.Utilitario;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public final class CaptacaoDAO implements CrudDAO<CaptacaoModel> {

	public CaptacaoModel obter(final CaptacaoModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT C.ID, C.TIPO_IMOVEL_ID, (SELECT TI.DESCRICAO FROM TIPO_IMOVEL TI WHERE TI.ID = C.TIPO_IMOVEL_ID), C.DESCRICAO, C.VALOR, C.BAIRRO_ID, (SELECT B.DESCRICAO FROM BAIRRO B WHERE B.ID = C.BAIRRO_ID), C.ORIGEM_ID, (SELECT O.DESCRICAO FROM ORIGEM O WHERE O.ID = C.ORIGEM_ID), C.CONTATO, C.DATA_ANUNCIO, C.LINK, C.DATA_CADASTRO, C.USUARIO_CADASTRO_ID, (SELECT U.NOME FROM USUARIO U WHERE U.ID = C.USUARIO_CADASTRO_ID), C.STATUS_CAPTACAO_ID, (SELECT SC.DESCRICAO FROM STATUS_CAPTACAO SC WHERE SC.ID = C.STATUS_CAPTACAO_ID), C.OBSERVACAO, C.RESPONSAVEL_ID, (SELECT U.NOME FROM USUARIO U WHERE U.ID = C.RESPONSAVEL_ID), (SELECT U.EMAIL FROM USUARIO U WHERE U.ID = C.RESPONSAVEL_ID), C.DATA_ATUALIZACAO, C.USUARIO_ATUALIZACAO_ID, (SELECT U.NOME FROM USUARIO U WHERE U.ID = C.USUARIO_ATUALIZACAO_ID), (SELECT I.ID FROM IMOVEL I WHERE I.CAPTACAO_ID = C.ID LIMIT 1) IMOVEL_GERADO_ID FROM CAPTACAO C WHERE C.ID = ?", model.getId());

		return (CaptacaoModel) broker.getObjectBean(CaptacaoModel.class, "id", "tipoImovelModel.id", "tipoImovelModel.descricao", "descricao", "valor", "bairroModel.id", "bairroModel.descricao", "origemModel.id", "origemModel.descricao", "contato", "dataAnuncio", "link", "dataCadastro", "usuarioCadastroModel.id", "usuarioCadastroModel.nome", "statusCaptacaoModel.id", "statusCaptacaoModel.descricao", "observacao", "responsavelModel.id", "responsavelModel.nome", "responsavelModel.email", "dataAtualizacao", "usuarioAtualizacaoModel.id", "usuarioAtualizacaoModel.nome", "imovelGeradoModel.id");
	}

	@SuppressWarnings("unchecked")
	public List<CaptacaoModel> pesquisar(final CaptacaoModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder query = new StringBuilder("SELECT C.ID, C.TIPO_IMOVEL_ID, (SELECT TI.DESCRICAO FROM TIPO_IMOVEL TI WHERE TI.ID = C.TIPO_IMOVEL_ID), C.DESCRICAO, C.VALOR, C.BAIRRO_ID, (SELECT B.DESCRICAO FROM BAIRRO B WHERE B.ID = C.BAIRRO_ID), C.ORIGEM_ID, (SELECT O.DESCRICAO FROM ORIGEM O WHERE O.ID = C.ORIGEM_ID), C.CONTATO, C.DATA_ANUNCIO, C.LINK, C.DATA_CADASTRO, C.USUARIO_CADASTRO_ID, (SELECT U.NOME FROM USUARIO U WHERE U.ID = C.USUARIO_CADASTRO_ID), C.STATUS_CAPTACAO_ID, (SELECT SC.DESCRICAO FROM STATUS_CAPTACAO SC WHERE SC.ID = C.STATUS_CAPTACAO_ID), C.OBSERVACAO, C.RESPONSAVEL_ID, (SELECT U.NOME FROM USUARIO U WHERE U.ID = C.RESPONSAVEL_ID) FROM CAPTACAO C WHERE 1 = 1 ");

		if (!TSUtil.isEmpty(model.getId())) {
			
			query.append(" AND C.ID = ? ");
			
		} else {
			
			query.append(" AND C.TIPO_IMOVEL_ID = COALESCE(?, C.TIPO_IMOVEL_ID) AND SEM_ACENTOS(BUSCA_CAPTACAO(C.ID)) ILIKE SEM_ACENTOS(COALESCE(?, SEM_ACENTOS(BUSCA_CAPTACAO(C.ID)))) AND C.BAIRRO_ID = COALESCE(?, C.BAIRRO_ID) AND C.ORIGEM_ID = COALESCE(?, C.ORIGEM_ID) AND SEM_ACENTOS(COALESCE(C.CONTATO, '')) ILIKE SEM_ACENTOS(COALESCE(?, COALESCE(C.CONTATO, ''))) AND C.STATUS_CAPTACAO_ID = COALESCE(?, C.STATUS_CAPTACAO_ID) AND C.RESPONSAVEL_ID = COALESCE(?, C.RESPONSAVEL_ID) AND DATE(C.DATA_CADASTRO) BETWEEN DATE(COALESCE(?, C.DATA_CADASTRO)) AND DATE(COALESCE(?, C.DATA_CADASTRO)) ORDER BY C.DATA_CADASTRO DESC ");
			
		}

		broker.setSQL(query.toString());

		if (!TSUtil.isEmpty(model.getId())) {

			broker.set(model.getId());
			
		} else {
			
			broker.set(model.getTipoImovelModel().getId(), Utilitario.getStringIlike(model.getDescricao(), true), model.getBairroModel().getId(), model.getOrigemModel().getId(), Utilitario.getStringIlike(model.getContato(), true), model.getStatusCaptacaoModel().getId(), model.getResponsavelModel().getId(), model.getDataInicial(), model.getDataFinal());
			
		}

		return broker.getCollectionBean(CaptacaoModel.class, "id", "tipoImovelModel.id", "tipoImovelModel.descricao", "descricao", "valor", "bairroModel.id", "bairroModel.descricao", "origemModel.id", "origemModel.descricao", "contato", "dataAnuncio", "link", "dataCadastro", "usuarioCadastroModel.id", "usuarioCadastroModel.nome", "statusCaptacaoModel.id", "statusCaptacaoModel.descricao", "observacao", "responsavelModel.id", "responsavelModel.nome");
	}

	public CaptacaoModel inserir(final CaptacaoModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		model.setId(broker.getSequenceNextValue("captacao_id_seq"));

		broker.setSQL("INSERT INTO CAPTACAO(ID, TIPO_IMOVEL_ID, DESCRICAO, VALOR, BAIRRO_ID, ORIGEM_ID, CONTATO, DATA_ANUNCIO, LINK, DATA_CADASTRO, USUARIO_CADASTRO_ID, STATUS_CAPTACAO_ID, OBSERVACAO, RESPONSAVEL_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", model.getId(), model.getTipoImovelModel().getId(), model.getDescricao(), model.getValor(), model.getBairroModel().getId(), model.getOrigemModel().getId(), model.getContato(), model.getDataAnuncio(), model.getLink(), new Timestamp(model.getDataCadastro().getTime()), model.getUsuarioCadastroModel().getId(), model.getStatusCaptacaoModel().getId(), model.getObservacao(), model.getResponsavelModel().getId());

		broker.execute();

		for (CaptacaoContatoModel contato : model.getContatos()) {

			if (!TSUtil.isEmpty(contato.getTelefone()) || !TSUtil.isEmpty(contato.getEmail())) {

				if (TSUtil.isEmpty(contato.getId())) {

					this.inserir(contato, broker);

				}

			}

		}

		broker.endTransaction();

		return model;
	}

	public CaptacaoModel alterar(final CaptacaoModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		broker.setSQL("UPDATE CAPTACAO SET TIPO_IMOVEL_ID = ?, DESCRICAO = ?, VALOR = ?, BAIRRO_ID = ?, ORIGEM_ID = ?, CONTATO = ?, DATA_ANUNCIO = ?, LINK = ?, DATA_ATUALIZACAO = ?, USUARIO_ATUALIZACAO_ID = ?, STATUS_CAPTACAO_ID = ?, OBSERVACAO = ?, RESPONSAVEL_ID = ? WHERE ID = ?", model.getTipoImovelModel().getId(), model.getDescricao(), model.getValor(), model.getBairroModel().getId(), model.getOrigemModel().getId(), model.getContato(), model.getDataAnuncio(), model.getLink(), new Timestamp(model.getDataAtualizacao().getTime()), model.getUsuarioAtualizacaoModel().getId(), model.getStatusCaptacaoModel().getId(), model.getObservacao(), model.getResponsavelModel().getId(), model.getId());

		broker.execute();

		for (CaptacaoContatoModel contato : model.getContatos()) {

			if (TSUtil.isEmpty(contato.getTelefone()) && TSUtil.isEmpty(contato.getEmail())) {

				this.excluir(contato, broker);

			} else {

				if (TSUtil.isEmpty(contato.getId())) {

					this.inserir(contato, broker);

				} else {

					this.alterar(contato, broker);

				}

			}

		}

		broker.endTransaction();

		return model;
	}

	@SuppressWarnings("unchecked")
	public List<CaptacaoContatoModel> pesquisarContatos(final CaptacaoModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT CC.ID, CC.CAPTACAO_ID, CC.TELEFONE, CC.EMAIL, CC.OPERADORA_ID FROM CAPTACAO_CONTATO CC WHERE CC.CAPTACAO_ID = ? ORDER BY CC.ID", model.getId());

		return broker.getCollectionBean(CaptacaoContatoModel.class, "id", "captacaoModel.id", "telefone", "email", "operadoraModel.id");
	}

	public void inserir(final CaptacaoContatoModel model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setId(broker.getSequenceNextValue("captacao_contato_id_seq"));

		broker.setSQL("INSERT INTO CAPTACAO_CONTATO (ID, CAPTACAO_ID, TELEFONE, EMAIL, OPERADORA_ID) VALUES (?, ?, ?, ?, ?)", model.getId(), model.getCaptacaoModel().getId(), model.getTelefone(), model.getEmail(), model.getOperadoraModel().getId());

		broker.execute();

	}

	public void alterar(final CaptacaoContatoModel model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.setSQL("UPDATE CAPTACAO_CONTATO SET TELEFONE = ?, EMAIL = ?, OPERADORA_ID = ? WHERE ID = ?", model.getTelefone(), model.getEmail(), model.getOperadoraModel().getId(), model.getId());

		broker.execute();

	}

	public CaptacaoModel excluir(final CaptacaoModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("DELETE FROM CAPTACAO WHERE ID = ?", model.getId());

		broker.execute();

		return model;
	}

	public void excluir(final CaptacaoContatoModel model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.setSQL("DELETE FROM CAPTACAO_CONTATO WHERE ID = ?", model.getId());

		broker.execute();

	}

	public Boolean isExisteCaptacaoSimilar(String campo) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT EXISTS(SELECT 1 FROM CAPTACAO C WHERE SEM_ACENTOS(BUSCA_CAPTACAO(C.ID)) ILIKE SEM_ACENTOS(COALESCE(?, SEM_ACENTOS(BUSCA_CAPTACAO(C.ID))))) ", Utilitario.getStringIlike(campo, true));

		return (Boolean) broker.getObject();
	}

}
