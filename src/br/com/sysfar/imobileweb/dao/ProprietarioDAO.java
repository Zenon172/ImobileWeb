package br.com.sysfar.imobileweb.dao;

import java.sql.Timestamp;
import java.util.List;

import br.com.sysfar.imobileweb.model.ProprietarioContatoModel;
import br.com.sysfar.imobileweb.model.ProprietarioModel;
import br.com.sysfar.imobileweb.util.Utilitario;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public final class ProprietarioDAO implements CrudDAO<ProprietarioModel> {

	public ProprietarioModel obter(final ProprietarioModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, NOME, TELEFONE, CELULAR, EMAIL, RESPONSAVEL_VENDA, TELEFONE_RESPONSAVEL_VENDA, CELULAR_RESPONSAVEL_VENDA, EMAIL_RESPONSAVEL_VENDA, OBSERVACAO, FLAG_ATIVO, DATA_CADASTRO, USUARIO_CADASTRO_ID, (SELECT U.NOME FROM USUARIO U WHERE U.ID = P.USUARIO_CADASTRO_ID) FROM PROPRIETARIO P WHERE P.ID = ?", model.getId());

		return (ProprietarioModel) broker.getObjectBean(ProprietarioModel.class, "id", "nome", "telefone", "celular", "email", "responsavelVenda", "telefoneResponsavelVenda", "celularResponsavelVenda", "emailResponsavelVenda", "observacao", "flagAtivo", "dataCadastro", "usuarioCadastroModel.id", "usuarioCadastroModel.nome");
	}

	@SuppressWarnings("unchecked")
	public List<ProprietarioModel> pesquisar(final ProprietarioModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, NOME, TELEFONE, CELULAR, EMAIL, RESPONSAVEL_VENDA, TELEFONE_RESPONSAVEL_VENDA, CELULAR_RESPONSAVEL_VENDA, EMAIL_RESPONSAVEL_VENDA, OBSERVACAO, FLAG_ATIVO, DATA_CADASTRO, USUARIO_CADASTRO_ID, (SELECT U.NOME FROM USUARIO U WHERE U.ID = P.USUARIO_CADASTRO_ID) FROM PROPRIETARIO P WHERE SEM_ACENTOS(BUSCA_PROPRIETARIO(P.ID)) ILIKE SEM_ACENTOS(COALESCE(?, SEM_ACENTOS(BUSCA_PROPRIETARIO(P.ID)))) AND P.FLAG_ATIVO = ? ORDER BY P.NOME", Utilitario.getStringIlike(model.getObservacao(), true), model.getFlagAtivo());

		return broker.getCollectionBean(ProprietarioModel.class, "id", "nome", "telefone", "celular", "email", "responsavelVenda", "telefoneResponsavelVenda", "celularResponsavelVenda", "emailResponsavelVenda", "observacao", "flagAtivo", "dataCadastro", "usuarioCadastroModel.id", "usuarioCadastroModel.nome");
	}

	public ProprietarioModel inserir(final ProprietarioModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		model.setId(broker.getSequenceNextValue("proprietario_id_seq"));

		broker.setSQL("INSERT INTO PROPRIETARIO (ID, NOME, TELEFONE, CELULAR, EMAIL, RESPONSAVEL_VENDA, TELEFONE_RESPONSAVEL_VENDA, CELULAR_RESPONSAVEL_VENDA, EMAIL_RESPONSAVEL_VENDA, OBSERVACAO, FLAG_ATIVO, DATA_CADASTRO, USUARIO_CADASTRO_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", model.getId(), model.getNome(), model.getTelefone(), model.getCelular(), model.getEmail(), model.getResponsavelVenda(), model.getTelefoneResponsavelVenda(), model.getCelularResponsavelVenda(), model.getEmailResponsavelVenda(), model.getObservacao(), model.getFlagAtivo(), new Timestamp(model.getDataCadastro().getTime()), model.getUsuarioCadastroModel().getId());

		broker.execute();

		for (ProprietarioContatoModel contato : model.getContatos()) {

			if (!TSUtil.isEmpty(contato.getTelefone()) || !TSUtil.isEmpty(contato.getEmail())) {

				if (TSUtil.isEmpty(contato.getId())) {

					contato.setFlagResponsavelVenda(Boolean.FALSE);
					
					this.inserir(contato, broker);

				}

			}

		}

		for (ProprietarioContatoModel contato : model.getContatosResponsavelVenda()) {
			
			if (!TSUtil.isEmpty(contato.getTelefone()) || !TSUtil.isEmpty(contato.getEmail())) {
				
				if (TSUtil.isEmpty(contato.getId())) {
					
					contato.setFlagResponsavelVenda(Boolean.TRUE);
					
					this.inserir(contato, broker);
					
				}
				
			}
			
		}

		broker.endTransaction();
		
		return model;
	}

	public ProprietarioModel alterar(final ProprietarioModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		broker.setSQL("UPDATE PROPRIETARIO SET NOME = ?, TELEFONE = ?, CELULAR = ?, EMAIL = ?, RESPONSAVEL_VENDA = ?, TELEFONE_RESPONSAVEL_VENDA = ?, CELULAR_RESPONSAVEL_VENDA = ?, EMAIL_RESPONSAVEL_VENDA = ?, OBSERVACAO = ?, FLAG_ATIVO = ?, DATA_CADASTRO = ?, USUARIO_CADASTRO_ID =? WHERE ID = ?", model.getNome(), model.getTelefone(), model.getCelular(), model.getEmail(), model.getResponsavelVenda(), model.getTelefoneResponsavelVenda(), model.getCelularResponsavelVenda(), model.getEmailResponsavelVenda(), model.getObservacao(), model.getFlagAtivo(), new Timestamp(model.getDataCadastro().getTime()), model.getUsuarioCadastroModel().getId(), model.getId());

		broker.execute();

		for (ProprietarioContatoModel contato : model.getContatos()) {

			if (TSUtil.isEmpty(contato.getTelefone()) && TSUtil.isEmpty(contato.getEmail())) {

				this.excluir(contato, broker);

			} else {

				if (TSUtil.isEmpty(contato.getId())) {

					contato.setFlagResponsavelVenda(Boolean.FALSE);
					
					this.inserir(contato, broker);

				} else {

					this.alterar(contato, broker);

				}

			}

		}
		
		for (ProprietarioContatoModel contato : model.getContatosResponsavelVenda()) {
			
			if (TSUtil.isEmpty(contato.getTelefone()) && TSUtil.isEmpty(contato.getEmail())) {
				
				this.excluir(contato, broker);
				
			} else {
				
				if (TSUtil.isEmpty(contato.getId())) {
					
					contato.setFlagResponsavelVenda(Boolean.TRUE);
					
					this.inserir(contato, broker);
					
				} else {
					
					this.alterar(contato, broker);
					
				}
				
			}
			
		}

		broker.endTransaction();
		
		return model;
	}

	public ProprietarioModel excluir(final ProprietarioModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("DELETE FROM PROPRIETARIO WHERE ID = ?", model.getId());

		broker.execute();

		return model;
	}

	@SuppressWarnings("unchecked")
	public List<ProprietarioContatoModel> pesquisarContatos(final ProprietarioModel model, boolean flagResponsavelVenda) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT PC.ID, PC.PROPRIETARIO_ID, PC.NOME, PC.TELEFONE, PC.EMAIL, PC.FLAG_RESPONSAVEL_VENDA, PC.OPERADORA_ID FROM PROPRIETARIO_CONTATO PC WHERE PC.PROPRIETARIO_ID = ? AND PC.FLAG_RESPONSAVEL_VENDA = ? ORDER BY PC.ID", model.getId(), flagResponsavelVenda);

		return broker.getCollectionBean(ProprietarioContatoModel.class, "id", "proprietarioModel.id", "nome", "telefone", "email", "flagResponsavelVenda", "operadoraModel.id");
	}

	public void inserir(final ProprietarioContatoModel model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setId(broker.getSequenceNextValue("proprietario_contato_id_seq"));

		broker.setSQL("INSERT INTO PROPRIETARIO_CONTATO (ID, PROPRIETARIO_ID, NOME, TELEFONE, EMAIL, OPERADORA_ID, FLAG_RESPONSAVEL_VENDA) VALUES (?, ?, ?, ?, ?, ?, ?)", model.getId(), model.getProprietarioModel().getId(), model.getNome(), model.getTelefone(), model.getEmail(), model.getOperadoraModel().getId(), model.getFlagResponsavelVenda());

		broker.execute();

	}

	public void alterar(final ProprietarioContatoModel model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.setSQL("UPDATE PROPRIETARIO_CONTATO SET NOME = ?, TELEFONE = ?, EMAIL = ?, OPERADORA_ID = ? WHERE ID = ?", model.getNome(), model.getTelefone(), model.getEmail(), model.getOperadoraModel().getId(), model.getId());

		broker.execute();

	}

	public void excluir(final ProprietarioContatoModel model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.setSQL("DELETE FROM PROPRIETARIO_CONTATO WHERE ID = ?", model.getId());

		broker.execute();

	}
	
}
