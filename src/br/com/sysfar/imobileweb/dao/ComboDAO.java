package br.com.sysfar.imobileweb.dao;

import java.util.List;

import br.com.sysfar.imobileweb.model.BairroModel;
import br.com.sysfar.imobileweb.model.GrupoModel;
import br.com.sysfar.imobileweb.model.OrigemModel;
import br.com.sysfar.imobileweb.model.PosicaoSolModel;
import br.com.sysfar.imobileweb.model.StatusCaptacaoModel;
import br.com.sysfar.imobileweb.model.TipoImovelModel;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public final class ComboDAO {

	@SuppressWarnings("unchecked")
	public List<GrupoModel> pesquisarGrupo() {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO FROM GRUPO ORDER BY DESCRICAO");

		return broker.getCollectionBean(GrupoModel.class, "id", "descricao");
	}

	@SuppressWarnings("unchecked")
	public List<TipoImovelModel> pesquisarTipoImovel() {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO FROM TIPO_IMOVEL ORDER BY DESCRICAO");

		return broker.getCollectionBean(TipoImovelModel.class, "id", "descricao");
	}

	@SuppressWarnings("unchecked")
	public List<BairroModel> pesquisarBairro() {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO FROM BAIRRO ORDER BY DESCRICAO");

		return broker.getCollectionBean(BairroModel.class, "id", "descricao");
	}

	@SuppressWarnings("unchecked")
	public List<OrigemModel> pesquisarOrigem() {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO FROM ORIGEM ORDER BY DESCRICAO");

		return broker.getCollectionBean(OrigemModel.class, "id", "descricao");
	}

	@SuppressWarnings("unchecked")
	public List<StatusCaptacaoModel> pesquisarStatusCaptacao() {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO FROM STATUS_CAPTACAO ORDER BY DESCRICAO");

		return broker.getCollectionBean(StatusCaptacaoModel.class, "id", "descricao");
	}
	
	@SuppressWarnings("unchecked")
	public List<PosicaoSolModel> pesquisarPosicaoSol() {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT ID, DESCRICAO FROM POSICAO_SOL ORDER BY DESCRICAO");
		
		return broker.getCollectionBean(PosicaoSolModel.class, "id", "descricao");
	}

}
