package br.com.sysfar.imobileweb.dao;

import java.sql.Timestamp;
import java.util.List;

import br.com.sysfar.imobileweb.model.ClienteContatoModel;
import br.com.sysfar.imobileweb.model.ClienteModel;
import br.com.sysfar.imobileweb.model.ClientePerfilBairroModel;
import br.com.sysfar.imobileweb.model.ClientePerfilModel;
import br.com.sysfar.imobileweb.model.ClienteStatusModel;
import br.com.sysfar.imobileweb.util.Utilitario;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public final class ClienteDAO implements CrudDAO<ClienteModel> {

	public ClienteModel obter(final ClienteModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT C.ID, C.NOME, C.EMAIL, C.OBSERVACOES, C.DATA_CADASTRO, C.USUARIO_CADASTRO_ID, (SELECT U.NOME FROM USUARIO U WHERE U.ID = C.USUARIO_CADASTRO_ID), (SELECT CS.STATUS_CLIENTE_ID FROM CLIENTE_STATUS CS, STATUS_CLIENTE SC WHERE SC.ID = CS.STATUS_CLIENTE_ID AND CS.CLIENTE_ID = C.ID ORDER BY CS.ID DESC LIMIT 1) STATUS_ATUAL FROM CLIENTE C WHERE C.ID = ?", model.getId());

		return (ClienteModel) broker.getObjectBean(ClienteModel.class, "id", "nome", "email", "observacoes", "dataCadastro", "usuarioCadastroModel.id", "usuarioCadastroModel.nome", "statusAtualModel.id");
	}

	public ClienteModel obterPorTelefone(final String telefone) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT C.ID, C.NOME, C.EMAIL, C.DATA_CADASTRO, C.USUARIO_CADASTRO_ID, (SELECT U.NOME FROM USUARIO U WHERE U.ID = C.USUARIO_CADASTRO_ID) FROM CLIENTE C WHERE EXISTS (SELECT 1 FROM CLIENTE_CONTATO CC WHERE CC.CLIENTE_ID = C.ID AND SEM_ACENTOS(CC.TELEFONE) ILIKE SEM_ACENTOS(?)", Utilitario.getStringIlike(telefone, true));

		return (ClienteModel) broker.getObjectBean(ClienteModel.class, "id", "nome", "email", "dataCadastro", "usuarioCadastroModel.id", "usuarioCadastroModel.nome");
	}

	@SuppressWarnings("unchecked")
	public List<ClienteModel> pesquisar(final ClienteModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT C.ID, C.NOME, C.EMAIL, C.OBSERVACOES, C.DATA_CADASTRO, C.USUARIO_CADASTRO_ID, (SELECT U.NOME FROM USUARIO U WHERE U.ID = C.USUARIO_CADASTRO_ID) FROM CLIENTE C WHERE SEM_ACENTOS(BUSCA_CLIENTE(C.ID)) ILIKE SEM_ACENTOS(COALESCE(?, SEM_ACENTOS(BUSCA_CLIENTE(C.ID)))) AND C.USUARIO_CADASTRO_ID = COALESCE(?, C.USUARIO_CADASTRO_ID) AND COALESCE((SELECT CS.STATUS_CLIENTE_ID FROM CLIENTE_STATUS CS, STATUS_CLIENTE SC WHERE SC.ID = CS.STATUS_CLIENTE_ID AND CS.CLIENTE_ID = C.ID ORDER BY CS.ID DESC LIMIT 1), 0) = COALESCE(?, COALESCE((SELECT CS.STATUS_CLIENTE_ID FROM CLIENTE_STATUS CS, STATUS_CLIENTE SC WHERE SC.ID = CS.STATUS_CLIENTE_ID AND CS.CLIENTE_ID = C.ID ORDER BY CS.ID DESC LIMIT 1), 0)) ORDER BY C.NOME", Utilitario.getStringIlike(model.getObservacoes(), true), model.getUsuarioCadastroModel().getId(), model.getStatusAtualModel().getId());

		return broker.getCollectionBean(ClienteModel.class, "id", "nome", "email", "observacoes", "dataCadastro", "usuarioCadastroModel.id", "usuarioCadastroModel.nome");
	}

	public ClienteModel inserir(final ClienteModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		model.setId(broker.getSequenceNextValue("cliente_id_seq"));

		broker.setSQL("INSERT INTO CLIENTE (ID, NOME, EMAIL, OBSERVACOES, DATA_CADASTRO, USUARIO_CADASTRO_ID) VALUES (?, ?, ?, ?, ?, ?)", model.getId(), model.getNome(), model.getEmail(), model.getObservacoes(), new Timestamp(model.getDataCadastro().getTime()), model.getUsuarioCadastroModel().getId());

		broker.execute();

		for (ClienteContatoModel contato : model.getContatos()) {

			if (!TSUtil.isEmpty(contato.getTelefone())) {

				if (TSUtil.isEmpty(contato.getId())) {

					this.inserir(contato, broker);

				}

			}

		}

		this.inserir(model.getClientePerfilModel(), broker);

		broker.endTransaction();

		return model;
	}

	public ClienteModel alterar(final ClienteModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		broker.setSQL("UPDATE CLIENTE SET NOME = ?, EMAIL = ?, OBSERVACOES = ? WHERE ID = ?", model.getNome(), model.getEmail(), model.getObservacoes(), model.getId());

		broker.execute();

		for (ClienteContatoModel contato : model.getContatos()) {

			if (TSUtil.isEmpty(contato.getTelefone())) {

				this.excluir(contato, broker);

			} else {

				if (TSUtil.isEmpty(contato.getId())) {

					this.inserir(contato, broker);

				} else {

					this.alterar(contato, broker);

				}

			}

		}

		this.alterar(model.getClientePerfilModel(), broker);

		broker.endTransaction();

		return model;
	}

	public ClienteModel excluir(final ClienteModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("DELETE FROM CLIENTE WHERE ID = ?", model.getId());

		broker.execute();

		return model;
	}

	@SuppressWarnings("unchecked")
	public List<ClienteContatoModel> pesquisarContatos(final ClienteModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT CC.ID, CC.CLIENTE_ID, CC.TELEFONE, CC.EMAIL, CC.OPERADORA_ID FROM CLIENTE_CONTATO CC WHERE CC.CLIENTE_ID = ? ORDER BY CC.ID", model.getId());

		return broker.getCollectionBean(ClienteContatoModel.class, "id", "clienteModel.id", "telefone", "email", "operadoraModel.id");
	}

	@SuppressWarnings("unchecked")
	public List<ClientePerfilBairroModel> pesquisarBairrosPerfil(final ClientePerfilModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT CPB.ID, CPB.CLIENTE_PERFIL_ID, CPB.BAIRRO_ID FROM CLIENTE_PERFIL_BAIRRO CPB WHERE CPB.CLIENTE_PERFIL_ID = ? ORDER BY CPB.ID", model.getId());

		return broker.getCollectionBean(ClienteContatoModel.class, "id", "bairroModel.id", "clientePerfilModel.id");
	}

	public void inserir(final ClienteContatoModel model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setId(broker.getSequenceNextValue("cliente_contato_id_seq"));

		broker.setSQL("INSERT INTO CLIENTE_CONTATO (ID, CLIENTE_ID, TELEFONE, EMAIL, OPERADORA_ID) VALUES (?, ?, ?, ?, ?)", model.getId(), model.getClienteModel().getId(), model.getTelefone(), model.getEmail(), model.getOperadoraModel().getId());

		broker.execute();

	}

	public void alterar(final ClienteContatoModel model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.setSQL("UPDATE CLIENTE_CONTATO SET TELEFONE = ?, EMAIL = ?, OPERADORA_ID = ? WHERE ID = ?", model.getTelefone(), model.getEmail(), model.getOperadoraModel().getId(), model.getId());

		broker.execute();

	}

	public void excluir(final ClienteContatoModel model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.setSQL("DELETE FROM CLIENTE_CONTATO WHERE ID = ?", model.getId());

		broker.execute();

	}

	@SuppressWarnings("unchecked")
	public List<ClienteStatusModel> pesquisarStatus(final ClienteModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT CS.ID, CS.CLIENTE_ID, CS.STATUS_CLIENTE_ID, (SELECT SC.DESCRICAO FROM STATUS_CLIENTE SC WHERE SC.ID = CS.STATUS_CLIENTE_ID), CS.DATA, CS.OBSERVACAO, CS.DATA_CADASTRO, CS.USUARIO_CADASTRO_ID, (SELECT U.NOME FROM USUARIO U WHERE U.ID = CS.USUARIO_CADASTRO_ID) FROM CLIENTE_STATUS CS WHERE CS.CLIENTE_ID = ? ORDER BY CS.ID", model.getId());

		return broker.getCollectionBean(ClienteStatusModel.class, "id", "clienteModel.id", "statusClienteModel.id", "statusClienteModel.descricao", "data", "observacao", "dataCadastro", "usuarioCadastroModel.id", "usuarioCadastroModel.nome");
	}

	public ClientePerfilModel obterPerfil(final ClienteModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT CP.ID, CP.CLIENTE_ID, CP.QTD_QUARTOS_MIN, CP.QTD_QUARTOS_MAX, CP.METRAGEM_MIN, CP.METRAGEM_MAX, CP.VALOR_MIN, CP.VALOR_MAX, CP.SUITE_MIN, CP.SUITE_MAX, CP.GARAGENS_MIN, CP.GARAGENS_MAX, CP.FLAG_INFRAESTRUTURA, CP.TIPO_IMOVEL_ID FROM CLIENTE_PERFIL CP WHERE CP.CLIENTE_ID = ? ", model.getId());

		return (ClientePerfilModel) broker.getObjectBean(ClientePerfilModel.class, "id", "clienteModel.id", "qtdQuartosMin", "qtdQuartosMax", "metragemMin", "metragemMax", "valorMin", "valorMax", "suiteMin", "suiteMax", "garagensMin", "garagensMax", "flagInfraestrutura", "tipoImovelModel.id");
	}

	public ClienteStatusModel inserir(final ClienteStatusModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("cliente_status_id_seq"));

		broker.setSQL("INSERT INTO CLIENTE_STATUS (ID, CLIENTE_ID, STATUS_CLIENTE_ID, DATA, OBSERVACAO, DATA_CADASTRO, USUARIO_CADASTRO_ID) VALUES (?, ?, ?, ?, ?, ?, ?)", model.getId(), model.getClienteModel().getId(), model.getStatusClienteModel().getId(), model.getData(), model.getObservacao(), new Timestamp(model.getDataCadastro().getTime()), model.getUsuarioCadastroModel().getId());

		broker.execute();

		return model;
	}

	public ClientePerfilModel inserir(final ClientePerfilModel model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setId(broker.getSequenceNextValue("cliente_perfil_id_seq"));

		broker.setSQL("INSERT INTO CLIENTE_PERFIL (ID, CLIENTE_ID, QTD_QUARTOS_MIN, QTD_QUARTOS_MAX, METRAGEM_MIN, METRAGEM_MAX, VALOR_MIN, VALOR_MAX, SUITE_MIN, SUITE_MAX, GARAGENS_MIN, GARAGENS_MAX, FLAG_INFRAESTRUTURA, TIPO_IMOVEL_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", model.getId(), model.getClienteModel().getId(), model.getQtdQuartosMin(), model.getQtdQuartosMax(), model.getMetragemMin(), model.getMetragemMax(), model.getValorMin(), model.getValorMax(), model.getSuiteMin(), model.getSuiteMax(), model.getGaragensMin(), model.getGaragensMax(), model.getFlagInfraestrutura(), model.getTipoImovelModel().getId());

		broker.execute();
		
		for(ClientePerfilBairroModel bairro : model.getBairros()){
			
			this.inserir(bairro, broker);
			
		}

		return model;
	}

	public ClientePerfilBairroModel inserir(final ClientePerfilBairroModel model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setId(broker.getSequenceNextValue("cliente_perfil_bairro_id_seq"));

		broker.setSQL("INSERT INTO CLIENTE_PERFIL_BAIRRO (ID, CLIENTE_PERFIL_ID, BAIRRO_ID) VALUES (?, ?, ?)", model.getId(), model.getClientePerfilModel().getId(), model.getBairroModel().getId());

		broker.execute();

		return model;
	}

	public ClientePerfilModel alterar(final ClientePerfilModel model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.setSQL("UPDATE CLIENTE_PERFIL SET QTD_QUARTOS_MIN = ?, QTD_QUARTOS_MAX = ?, METRAGEM_MIN = ?, METRAGEM_MAX = ?, VALOR_MIN = ?, VALOR_MAX = ?, SUITE_MIN = ?, SUITE_MAX = ?, GARAGENS_MIN = ?, GARAGENS_MAX = ?, FLAG_INFRAESTRUTURA = ?, TIPO_IMOVEL_ID = ? WHERE ID = ?", model.getQtdQuartosMin(), model.getQtdQuartosMax(), model.getMetragemMin(), model.getMetragemMax(), model.getValorMin(), model.getValorMax(), model.getSuiteMin(), model.getSuiteMax(), model.getGaragensMin(), model.getGaragensMax(), model.getFlagInfraestrutura(), model.getTipoImovelModel().getId(), model.getId());

		broker.execute();
		
		this.excluirBairros(model, broker);
		
		for(ClientePerfilBairroModel bairro : model.getBairros()){
			
			this.inserir(bairro, broker);
			
		}

		return model;
	}

	public ClientePerfilModel excluirBairros(final ClientePerfilModel model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.setSQL("DELETE FROM CLIENTE_PERFIL_BAIRRO WHERE CLIENTE_PERFIL_ID = ?", model.getId());

		broker.execute();

		return model;
	}

	public ClienteStatusModel excluir(final ClienteStatusModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("UPDATE CLIENTE_STATUS SET FLAG_ATIVO = FALSE WHERE ID = ?", model.getId());

		broker.execute();

		return model;
	}

}
