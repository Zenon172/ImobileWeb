package br.com.sysfar.imobileweb.dao;

import java.sql.Timestamp;
import java.util.List;

import br.com.sysfar.imobileweb.model.ContatoModel;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public final class ContatoDAO implements CrudDAO<ContatoModel> {

	public ContatoModel obter(final ContatoModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT C.ID, C.IMOVEL_ID, C.NOME, C.TELEFONE, C.EMAIL, C.MENSAGEM, C.FLAG_RETORNO_WHATSAPP, C.FLAG_RETORNO_TELEFONE, C.DATA_CADASTRO FROM CONTATO C WHERE C.ID = ?", model.getId());

		return (ContatoModel) broker.getObjectBean(ContatoModel.class, "id", "nome", "email", "observacoes", "origemModel.id", "dataInicial", "dataCadastro", "usuarioCadastroModel.id", "usuarioCadastroModel.nome", "statusAtualModel.id");
	}

	@SuppressWarnings("unchecked")
	public List<ContatoModel> pesquisar(final ContatoModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT C.ID, C.IMOVEL_ID, C.NOME, C.TELEFONE, C.EMAIL, C.MENSAGEM, C.FLAG_RETORNO_WHATSAPP, C.FLAG_RETORNO_TELEFONE, C.DATA_CADASTRO FROM CONTATO C ORDER BY C.DATA_CADASTRO DESC");

		return broker.getCollectionBean(ContatoModel.class, "id", "nome", "email", "observacoes", "dataCadastro", "usuarioCadastroModel.id", "usuarioCadastroModel.nome", "statusAtualModel.descricao");
	}
	
	public ContatoModel inserir(final ContatoModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("contato_id_seq"));

		broker.setSQL("INSERT INTO CONTATO (ID, IMOVEL_ID, NOME, TELEFONE, EMAIL, MENSAGEM, FLAG_RETORNO_WHATSAPP, FLAG_RETORNO_TELEFONE, DATA_CADASTRO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", model.getId(), model.getImovelModel().getId(), model.getNome(), model.getTelefone(), model.getEmail(), model.getMensagem(), model.getFlagRetornoWhatsapp(), model.getFlagRetornoTelefone(), new Timestamp(model.getDataCadastro().getTime()));

		broker.execute();

		return model;
	}

	public ContatoModel alterar(final ContatoModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("UPDATE CONTATO SET NOME = ?, TELEFONE = ?, EMAIL = ?, MENSAGEM = ?, FLAG_RETORNO_WHATSAPP = ?, FLAG_RETORNO_TELEFONE = ? WHERE ID = ?", model.getNome(), model.getTelefone(), model.getEmail(), model.getMensagem(), model.getFlagRetornoWhatsapp(), model.getFlagRetornoTelefone(), model.getId());

		broker.execute();

		return model;
	}

	public ContatoModel excluir(final ContatoModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("DELETE FROM CONTATO WHERE ID = ?", model.getId());

		broker.execute();

		return model;
	}

}
