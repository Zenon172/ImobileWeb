package br.com.sysfar.imobileweb.dao;

import java.sql.Timestamp;
import java.util.List;

import br.com.sysfar.imobileweb.model.CorretorContatoModel;
import br.com.sysfar.imobileweb.model.CorretorModel;
import br.com.sysfar.imobileweb.util.Utilitario;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public final class CorretorDAO implements CrudDAO<CorretorModel> {

	public CorretorModel obter(final CorretorModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT C.ID, C.NOME, C.DATA_CADASTRO, C.USUARIO_CADASTRO_ID, (SELECT U.NOME FROM USUARIO U WHERE U.ID = C.USUARIO_CADASTRO_ID) FROM CORRETOR C WHERE C.ID = ?", model.getId());

		return (CorretorModel) broker.getObjectBean(CorretorModel.class, "id", "nome", "dataCadastro", "usuarioCadastroModel.id", "usuarioCadastroModel.nome");
	}

	@SuppressWarnings("unchecked")
	public List<CorretorModel> pesquisar(final CorretorModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT C.ID, C.NOME, C.DATA_CADASTRO, C.USUARIO_CADASTRO_ID, (SELECT U.NOME FROM USUARIO U WHERE U.ID = C.USUARIO_CADASTRO_ID) FROM CORRETOR C WHERE SEM_ACENTOS(BUSCA_CORRETOR(C.ID)) ILIKE SEM_ACENTOS(COALESCE(?, SEM_ACENTOS(BUSCA_CORRETOR(C.ID)))) ORDER BY C.NOME", Utilitario.getStringIlike(model.getNome(), true));

		return broker.getCollectionBean(CorretorModel.class, "id", "nome", "dataCadastro", "usuarioCadastroModel.id", "usuarioCadastroModel.nome");
	}

	public CorretorModel inserir(final CorretorModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		model.setId(broker.getSequenceNextValue("corretor_id_seq"));

		broker.setSQL("INSERT INTO CORRETOR(ID, NOME, DATA_CADASTRO, USUARIO_CADASTRO_ID) VALUES (?, ?, ?, ?)", model.getId(), model.getNome(), new Timestamp(model.getDataCadastro().getTime()), model.getUsuarioCadastroModel().getId());

		broker.execute();

		for (CorretorContatoModel contato : model.getContatos()) {

			if (!TSUtil.isEmpty(contato.getTelefone()) || !TSUtil.isEmpty(contato.getEmail())) {

				if (TSUtil.isEmpty(contato.getId())) {

					this.inserir(contato, broker);

				}

			}

		}

		broker.endTransaction();

		return model;
	}

	public CorretorModel alterar(final CorretorModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		broker.setSQL("UPDATE CORRETOR SET NOME = ? WHERE ID = ?", model.getNome(), model.getId());

		broker.execute();

		for (CorretorContatoModel contato : model.getContatos()) {

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
	public List<CorretorContatoModel> pesquisarContatos(final CorretorModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT CC.ID, CC.CORRETOR_ID, CC.NOME, CC.TELEFONE, CC.EMAIL, CC.OPERADORA_ID FROM CORRETOR_CONTATO CC WHERE CC.CORRETOR_ID = ? ORDER BY CC.ID", model.getId());

		return broker.getCollectionBean(CorretorContatoModel.class, "id", "corretorModel.id", "nome", "telefone", "email", "operadoraModel.id");
	}

	public void inserir(final CorretorContatoModel model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setId(broker.getSequenceNextValue("corretor_contato_id_seq"));

		broker.setSQL("INSERT INTO CORRETOR_CONTATO (ID, CORRETOR_ID, NOME, TELEFONE, EMAIL, OPERADORA_ID) VALUES (?, ?, ?, ?, ?, ?)", model.getId(), model.getCorretorModel().getId(), model.getNome(), model.getTelefone(), model.getEmail(), model.getOperadoraModel().getId());

		broker.execute();

	}

	public void alterar(final CorretorContatoModel model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.setSQL("UPDATE CORRETOR_CONTATO SET NOME = ?, TELEFONE = ?, EMAIL = ?, OPERADORA_ID = ? WHERE ID = ?", model.getNome(), model.getTelefone(), model.getEmail(), model.getOperadoraModel().getId(), model.getId());

		broker.execute();

	}

	public CorretorModel excluir(final CorretorModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("DELETE FROM CORRETOR WHERE ID = ?", model.getId());

		broker.execute();

		return model;
	}

	public void excluir(final CorretorContatoModel model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.setSQL("DELETE FROM CORRETOR_CONTATO WHERE ID = ?", model.getId());

		broker.execute();

	}
	
	public Boolean isExisteCorretor(String campo) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT EXISTS(SELECT 1 FROM CORRETOR C WHERE SEM_ACENTOS(BUSCA_CORRETOR(C.ID)) ILIKE SEM_ACENTOS(COALESCE(?, SEM_ACENTOS(BUSCA_CORRETOR(C.ID))))) ", Utilitario.getStringIlike(campo, true));

		return (Boolean) broker.getObject();
	}

}
