package br.com.sysfar.imobileweb.dao;

import java.io.Serializable;
import java.util.List;

import br.com.sysfar.imobileweb.model.BairroModel;
import br.com.sysfar.imobileweb.model.CidadeModel;
import br.com.sysfar.imobileweb.model.CondominioModel;
import br.com.sysfar.imobileweb.model.ConstrutoraModel;
import br.com.sysfar.imobileweb.model.EdificioModel;
import br.com.sysfar.imobileweb.model.GrupoModel;
import br.com.sysfar.imobileweb.model.OperadoraModel;
import br.com.sysfar.imobileweb.model.OrigemModel;
import br.com.sysfar.imobileweb.model.PosicaoSolModel;
import br.com.sysfar.imobileweb.model.ProprietarioModel;
import br.com.sysfar.imobileweb.model.StatusAtividadeModel;
import br.com.sysfar.imobileweb.model.StatusCaptacaoModel;
import br.com.sysfar.imobileweb.model.StatusClienteModel;
import br.com.sysfar.imobileweb.model.TipoAtualizacaoImovelModel;
import br.com.sysfar.imobileweb.model.TipoFachadaModel;
import br.com.sysfar.imobileweb.model.TipoImovelModel;
import br.com.sysfar.imobileweb.model.TipoPisoModel;
import br.com.sysfar.imobileweb.model.UsuarioModel;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

@SuppressWarnings("serial")
public final class ComboDAO implements Serializable {

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
	public List<CondominioModel> pesquisarCondominio() {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT ID, DESCRICAO FROM CONDOMINIO ORDER BY DESCRICAO");
		
		return broker.getCollectionBean(CondominioModel.class, "id", "descricao");
	}
	
	@SuppressWarnings("unchecked")
	public List<CondominioModel> pesquisarCondominio(BairroModel model) {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT ID, DESCRICAO FROM CONDOMINIO C WHERE C.BAIRRO_ID = ? ORDER BY DESCRICAO", model.getId());
		
		return broker.getCollectionBean(CondominioModel.class, "id", "descricao");
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
	public List<StatusAtividadeModel> pesquisarStatusAtividade() {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT ID, DESCRICAO FROM STATUS_ATIVIDADE ORDER BY DESCRICAO");
		
		return broker.getCollectionBean(StatusAtividadeModel.class, "id", "descricao");
	}
	
	@SuppressWarnings("unchecked")
	public List<PosicaoSolModel> pesquisarPosicaoSol() {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT ID, DESCRICAO FROM POSICAO_SOL ORDER BY DESCRICAO");
		
		return broker.getCollectionBean(PosicaoSolModel.class, "id", "descricao");
	}
	
	@SuppressWarnings("unchecked")
	public List<EdificioModel> pesquisarEdificio() {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT ID, DESCRICAO FROM EDIFICIO ORDER BY DESCRICAO");
		
		return broker.getCollectionBean(EdificioModel.class, "id", "descricao");
	}
	
	@SuppressWarnings("unchecked")
	public List<ConstrutoraModel> pesquisarConstrutora() {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT ID, DESCRICAO FROM CONSTRUTORA ORDER BY DESCRICAO");
		
		return broker.getCollectionBean(ConstrutoraModel.class, "id", "descricao");
	}
	
	@SuppressWarnings("unchecked")
	public List<TipoPisoModel> pesquisarTipoPiso() {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT ID, DESCRICAO FROM TIPO_PISO ORDER BY DESCRICAO");
		
		return broker.getCollectionBean(TipoPisoModel.class, "id", "descricao");
	}
	
	@SuppressWarnings("unchecked")
	public List<TipoFachadaModel> pesquisarTipoFachada() {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT ID, DESCRICAO FROM TIPO_FACHADA ORDER BY DESCRICAO");
		
		return broker.getCollectionBean(TipoFachadaModel.class, "id", "descricao");
	}
	
	@SuppressWarnings("unchecked")
	public List<UsuarioModel> pesquisarCaptador() {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT ID, NOME FROM USUARIO ORDER BY NOME");
		
		return broker.getCollectionBean(UsuarioModel.class, "id", "nome");
	}
	
	@SuppressWarnings("unchecked")
	public List<ProprietarioModel> pesquisarProprietario() {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT ID, NOME FROM PROPRIETARIO ORDER BY NOME");
		
		return broker.getCollectionBean(ProprietarioModel.class, "id", "nome");
	}
	
	@SuppressWarnings("unchecked")
	public List<UsuarioModel> pesquisarUsuarios() {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT ID, NOME, LOGIN, SENHA, GRUPO_ID, (SELECT DESCRICAO FROM GRUPO G WHERE G.ID = GRUPO_ID) FROM USUARIO U ORDER BY U.NOME");
		
		return broker.getCollectionBean(UsuarioModel.class, "id", "nome", "login", "senha", "grupoModel.id", "grupoModel.descricao");
	}
	
	@SuppressWarnings("unchecked")
	public List<OperadoraModel> pesquisarOperadoras() {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO, FLAG_ATIVO FROM OPERADORA WHERE FLAG_ATIVO ORDER BY DESCRICAO");

		return broker.getCollectionBean(OperadoraModel.class, "id", "descricao", "flagAtivo");
	}

	@SuppressWarnings("unchecked")
	public List<StatusClienteModel> pesquisarStatusCliente() {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, DESCRICAO FROM STATUS_CLIENTE WHERE FLAG_ATIVO ORDER BY DESCRICAO");

		return broker.getCollectionBean(StatusClienteModel.class, "id", "descricao");
	}
	
	@SuppressWarnings("unchecked")
	public List<TipoAtualizacaoImovelModel> pesquisarTipoAtualizacaoImovel() {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT ID, DESCRICAO FROM TIPO_ATUALIZACAO_IMOVEL WHERE FLAG_ATIVO ORDER BY DESCRICAO");
		
		return broker.getCollectionBean(TipoAtualizacaoImovelModel.class, "id", "descricao");
	}
	
	@SuppressWarnings("unchecked")
	public List<CidadeModel> pesquisarCidade() {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, NOME FROM CIDADE ORDER BY NOME");

		return broker.getCollectionBean(CidadeModel.class, "id", "nome");
	}

}
