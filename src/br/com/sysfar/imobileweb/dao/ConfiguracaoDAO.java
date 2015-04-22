package br.com.sysfar.imobileweb.dao;

import java.sql.Timestamp;
import java.util.List;

import br.com.sysfar.imobileweb.model.ConfiguracaoModel;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public final class ConfiguracaoDAO {

	public ConfiguracaoModel obter(ConfiguracaoModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT ID, DESCRICAO, TIPO_RESPOSTA_ID, VALOR, DATA_ATUALIZACAO, USUARIO_ATUALIZACAO_ID FROM CONFIGURACAO WHERE ID = ?", model.getId());

		return (ConfiguracaoModel) broker.getObjectBean(ConfiguracaoModel.class, "id", "descricao", "tipoRespostaModel.id", "valor", "dataAtualizacao", "usuarioAtualizacaoModel.id");
	}

	@SuppressWarnings("unchecked")
	public List<ConfiguracaoModel> pesquisar() {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO, TIPO_RESPOSTA_ID, VALOR, DATA_ATUALIZACAO, USUARIO_ATUALIZACAO_ID FROM CONFIGURACAO ORDER BY ID");

		return broker.getCollectionBean(ConfiguracaoModel.class, "id", "descricao", "tipoRespostaModel.id", "valor", "dataAtualizacao", "usuarioAtualizacaoModel.id");
	}

	public void alterar(final List<ConfiguracaoModel> configuracoes) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		for (ConfiguracaoModel model : configuracoes) {

			ConfiguracaoModel configAtual = this.obter(model);

			if (!configAtual.getValor().equals(model.getValor())) {

				broker.setSQL("UPDATE CONFIGURACAO SET VALOR = ?, DATA_ATUALIZACAO = ?, USUARIO_ATUALIZACAO_ID = ? WHERE ID = ?", model.getValor(), new Timestamp(model.getDataAtualizacao().getTime()), model.getUsuarioAtualizacaoModel().getId(), model.getId());

				broker.execute();

			}

		}

		broker.endTransaction();

	}

}
