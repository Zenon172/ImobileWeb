package br.com.sysfar.imobileweb.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.sysfar.imobileweb.model.ClienteModel;
import br.com.sysfar.imobileweb.model.ImovelAtualizacaoModel;
import br.com.sysfar.imobileweb.model.ImovelFotoModel;
import br.com.sysfar.imobileweb.model.ImovelModel;
import br.com.sysfar.imobileweb.model.ProprietarioModel;
import br.com.sysfar.imobileweb.model.UsuarioModel;
import br.com.sysfar.imobileweb.util.Utilitario;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public final class ImovelDAO implements CrudDAO<ImovelModel> {

	public ImovelModel obter(final ImovelModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT I.ID, I.FLAG_ATIVO, I.CODIGO, I.TIPO_IMOVEL_ID, (SELECT TI.DESCRICAO FROM TIPO_IMOVEL TI WHERE TI.ID = I.TIPO_IMOVEL_ID), I.DATA_CADASTRO, I.USUARIO_CADASTRO_ID, (SELECT U.NOME FROM USUARIO U WHERE U.ID = I.USUARIO_CADASTRO_ID), I.FLAG_PISCINA_PRIVATIVA, I.VALOR, I.ENDERECO, I.COMPLEMENTO, I.EDIFICIO_ID, (SELECT E.CONDOMINIO_ID FROM EDIFICIO E WHERE E.ID = I.EDIFICIO_ID), ANDAR, I.UNIDADE, I.PONTO_REFERENCIA, I.BAIRRO_ID, (SELECT B.DESCRICAO FROM BAIRRO B WHERE B.ID = I.BAIRRO_ID), I.CEP, I.CONSTRUTORA_ID, I.TIPO_PISO_SALA_ID, I.TIPO_PISO_QUARTO_ID, I.ANO_CONSTRUCAO, I.AREA_PRIVATIVA, I.TIPO_FACHADA_ID, I.NOME_ADMINISTRADOR, I.TELEFONE_ADMINISTRADOR, I.VALOR_CONDOMINIO, I.QUARTOS, I.SUITES, I.BANHEIROS, I.VARANDAS, I.DEPENDENCIAS, I.WC_EMPREGADAS, I.ARMARIOS_BANHEIROS, I.ARMARIOS_COZINHAS, I.ARMARIOS_QUARTOS, I.CLOSETS, I.PAVIMENTOS, I.HOME_OFFICES, I.DESPENSAS, I.DEPOSITOS, I.FLAG_COZINHA_AMERICANA, I.FLAG_SALAO_FESTAS, I.FLAG_PLAYGROUND, I.FLAG_PISCINA, I.FLAG_ACADEMIA, I.FLAG_QUADRA_POLIESPORTIVA, I.FLAG_PARQUE_INFANTIL, I.FLAG_CAMPO, I.FLAG_SALAO_JOGOS, I.FLAG_SAUNA, I.FLAG_BRINQUEDOTECA, I.FLAG_CINEMA, I.FLAG_QUADRA_SQUASH, I.FLAG_QUADRA_TENIS, I.FLAG_CHURRASQUEIRA, I.FLAG_ESTACIONAMENTO_VISITANTE, I.FLAG_AGUA_INDIVIDUAL, I.FLAG_GAS_INDIVIDUAL, I.FLAG_GERADOR_PROPRIO, I.POSICAO_SOL_ID, (SELECT PS.DESCRICAO FROM POSICAO_SOL PS WHERE PS.ID = I.POSICAO_SOL_ID), I.FLAG_ELEVADOR, I.QTD_ELEVADORES, I.FLAG_MOBILIADO, I.DESCRICAO_MOBILIA, I.QTD_VAGAS_ESTACIONAMENTO, I.FLAG_UTILIZOU_FGTS, I.FLAG_IMOVEL_QUITADO, I.FLAG_TERRENO_FOREIRO, I.FLAG_EXCLUSIVIDADE, I.FLAG_HABITADO, I.COMISSAO, I.MOTIVO_VENDA, I.OUTROS_IMOVEIS_VENDA, I.OBSERVACOES, I.PROPRIETARIO_ID, I.CAPTADOR_ID, I.DATA_CAPTACAO, I.FLAG_ARMARIOS_BANHEIRO, I.FLAG_ARMARIOS_COZINHA, I.FLAG_ARMARIOS_QUARTOS, I.QTD_VAGAS_COBERTAS, I.QTD_VAGAS_DESCOBERTAS, I.QTD_VAGAS_SOLTAS, i.FLAG_INFRAESTRUTURA, I.CONDOMINIO_ID, I.FLAG_VARIAS_VAGAS, I.FLAG_NAO_ANUNCIAR, I.DESCRICAO_ANUNCIO, I.TITULO_ANUNCIO, I.URL_AMIGAVEL, I.VALOR_IPTU, I.VALOR_ALUGUEL, I.FLAG_VENDA, I.FLAG_ALUGUEL FROM IMOVEL I WHERE I.ID = ?", model.getId());

		return (ImovelModel) broker.getObjectBean(ImovelModel.class, "id", "flagAtivo", "codigo", "tipoImovelModel.id", "tipoImovelModel.descricao", "dataCadastro", "usuarioCadastroModel.id", "usuarioCadastroModel.nome", "flagPiscinaPrivativa", "valor", "endereco", "complemento", "edificioModel.id", "edificioModel.condominioModel.id", "andar", "unidade", "pontoReferencia", "bairroModel.id", "bairroModel.descricao", "cep", "construtoraModel.id", "tipoPisoSalaModel.id", "tipoPisoQuartoModel.id", "anoConstrucao", "areaPrivativa", "tipoFachadaModel.id", "nomeAdministrador", "telefoneAdministrador", "valorCondominio", "quartos", "suites", "banheiros", "varandas", "dependencias", "wcEmpregadas", "armariosBanheiros", "armariosCozinhas", "armariosQuartos", "closets", "pavimentos", "homeOffices", "despensas", "depositos", "flagCozinhaAmericana", "flagSalaoFestas", "flagPlayground", "flagPiscina", "flagAcademia", "flagQuadraPoliesportiva", "flagParqueInfantil", "flagCampo", "flagSalaoJogos", "flagSauna", "flagBrinquedoteca", "flagCinema", "flagQuadraSquash", "flagQuadraTenis", "flagChurrasqueira", "flagEstacionamentoVisitante", "flagAguaIndividual", "flagGasIndividual", "flagGeradorProprio", "posicaoSolModel.id", "posicaoSolModel.descricao", "flagElevador", "qtdElevadores", "flagMobiliado", "descricaoMobilia", "qtdVagasEstacionamento", "flagUtilizouFgts", "flagImovelQuitado", "flagTerrenoForeiro", "flagExclusividade", "flagHabitado", "comissao", "motivoVenda", "outrosImoveisVenda", "observacoes", "proprietarioModel.id", "captadorModel.id", "dataCaptacao", "flagArmariosBanheiro", "flagArmariosCozinha", "flagArmariosQuartos", "qtdVagasCobertas", "qtdVagasDescobertas", "qtdVagasSoltas", "flagInfraestrutura", "condominioModel.id", "flagVariasVagas", "flagNaoAnunciar", "descricaoAnuncio", "tituloAnuncio", "urlAmigavel", "valorIPTU", "valorAluguel", "flagVenda", "flagAluguel");

	}

	@SuppressWarnings("unchecked")
	public List<ImovelModel> pesquisar(final ProprietarioModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT I.ID, I.FLAG_ATIVO, I.CODIGO, I.TIPO_IMOVEL_ID, (SELECT TI.DESCRICAO FROM TIPO_IMOVEL TI WHERE TI.ID = I.TIPO_IMOVEL_ID), I.CAPTADOR_ID, (SELECT C.NOME FROM USUARIO C WHERE C.ID = I.CAPTADOR_ID), I.BAIRRO_ID, (SELECT B.DESCRICAO FROM BAIRRO B WHERE B.ID = I.BAIRRO_ID), I.EDIFICIO_ID, (SELECT E.DESCRICAO FROM EDIFICIO E WHERE E.ID = I.EDIFICIO_ID), I.VALOR, I.ENDERECO, I.COMPLEMENTO, I.ANDAR, I.UNIDADE, I.PONTO_REFERENCIA, I.CEP, I.OBSERVACOES, I.POSICAO_SOL_ID, (SELECT PS.DESCRICAO FROM POSICAO_SOL PS WHERE PS.ID = I.POSICAO_SOL_ID) FROM IMOVEL I WHERE I.PROPRIETARIO_ID = ? ", model.getId());

		return broker.getCollectionBean(ImovelModel.class, "id", "flagAtivo", "codigo", "tipoImovelModel.id", "tipoImovelModel.descricao", "captadorModel.id", "captadorModel.nome", "bairroModel.id", "bairroModel.descricao", "edificioModel.id", "edificioModel.descricao", "valor", "endereco", "complemento", "andar", "unidade", "pontoReferencia", "cep", "observacoes", "posicaoSolModel.id", "posicaoSolModel.descricao");

	}
	
	@SuppressWarnings("unchecked")
	public List<ImovelModel> pesquisarHome() {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT I.ID, I.FLAG_ATIVO, I.CODIGO, I.TIPO_IMOVEL_ID, (SELECT TI.DESCRICAO FROM TIPO_IMOVEL TI WHERE TI.ID = I.TIPO_IMOVEL_ID), I.CAPTADOR_ID, (SELECT C.NOME FROM USUARIO C WHERE C.ID = I.CAPTADOR_ID), I.BAIRRO_ID, (SELECT B.DESCRICAO FROM BAIRRO B WHERE B.ID = I.BAIRRO_ID), (SELECT C.NOME FROM CIDADE C, BAIRRO B WHERE C.ID = B.CIDADE_ID AND B.ID = I.BAIRRO_ID), I.EDIFICIO_ID, (SELECT E.DESCRICAO FROM EDIFICIO E WHERE E.ID = I.EDIFICIO_ID), I.VALOR, I.ENDERECO, I.COMPLEMENTO, I.ANDAR, I.UNIDADE, I.PONTO_REFERENCIA, I.CEP, I.OBSERVACOES, I.POSICAO_SOL_ID, (SELECT PS.DESCRICAO FROM POSICAO_SOL PS WHERE PS.ID = I.POSICAO_SOL_ID), (SELECT IF.ARQUIVO FROM IMOVEL_FOTO IF WHERE IF.IMOVEL_ID = I.ID AND IF.FLAG_ATIVO ORDER BY IF.ORDEM LIMIT 1), (SELECT IF.FLAG_PORTRAIT FROM IMOVEL_FOTO IF WHERE IF.IMOVEL_ID = I.ID AND IF.FLAG_ATIVO ORDER BY IF.ORDEM LIMIT 1), I.QUARTOS, I.SUITES, I.AREA_PRIVATIVA FROM IMOVEL I WHERE I.FLAG_ATIVO AND EXISTS(SELECT 1 FROM IMOVEL_FOTO IF WHERE IF.IMOVEL_ID = I.ID AND IF.FLAG_ATIVO) ORDER BY ID DESC");
		
		return broker.getCollectionBean(ImovelModel.class, "id", "flagAtivo", "codigo", "tipoImovelModel.id", "tipoImovelModel.descricao", "captadorModel.id", "captadorModel.nome", "bairroModel.id", "bairroModel.descricao", "bairroModel.cidadeModel.nome", "edificioModel.id", "edificioModel.descricao", "valor", "endereco", "complemento", "andar", "unidade", "pontoReferencia", "cep", "observacoes", "posicaoSolModel.id", "posicaoSolModel.descricao", "imagemFotoPrincipalModel.arquivo", "imagemFotoPrincipalModel.flagPortrait", "quartos", "suites", "areaPrivativa");
		
	}
	
	@SuppressWarnings("unchecked")
	public List<ImovelModel> pesquisarPerfil(final ClienteModel model) {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT I.ID, I.FLAG_ATIVO, I.CODIGO, I.TIPO_IMOVEL_ID, (SELECT TI.DESCRICAO FROM TIPO_IMOVEL TI WHERE TI.ID = I.TIPO_IMOVEL_ID), I.CAPTADOR_ID, (SELECT C.NOME FROM USUARIO C WHERE C.ID = I.CAPTADOR_ID), I.BAIRRO_ID, (SELECT B.DESCRICAO FROM BAIRRO B WHERE B.ID = I.BAIRRO_ID), I.EDIFICIO_ID, (SELECT E.DESCRICAO FROM EDIFICIO E WHERE E.ID = I.EDIFICIO_ID), I.VALOR, I.ENDERECO, I.COMPLEMENTO, I.ANDAR, I.UNIDADE, I.PONTO_REFERENCIA, I.CEP, I.OBSERVACOES, I.POSICAO_SOL_ID, (SELECT PS.DESCRICAO FROM POSICAO_SOL PS WHERE PS.ID = I.POSICAO_SOL_ID), I.QUARTOS, I.AREA_PRIVATIVA, I.SUITES, I.QTD_VAGAS_ESTACIONAMENTO FROM IMOVEL I, CLIENTE_PERFIL CP WHERE CP.CLIENTE_ID = ? AND I.FLAG_ATIVO AND COALESCE(I.VALOR, 0) BETWEEN (COALESCE(CP.VALOR_MIN, COALESCE(I.VALOR, 0)) - (SELECT C.VALOR::NUMERIC FROM CONFIGURACAO C WHERE C.ID = 1)) AND (COALESCE(CP.VALOR_MAX, COALESCE(I.VALOR, 0)) + (SELECT C.VALOR::NUMERIC FROM CONFIGURACAO C WHERE C.ID = 1)) AND COALESCE(I.QUARTOS, 0) BETWEEN COALESCE(CP.QTD_QUARTOS_MIN, COALESCE(I.QUARTOS, 0)) AND COALESCE(CP.QTD_QUARTOS_MAX, COALESCE(I.QUARTOS, 0)) AND COALESCE(I.AREA_PRIVATIVA, 0) BETWEEN COALESCE(CP.METRAGEM_MIN, COALESCE(I.AREA_PRIVATIVA, 0)) AND COALESCE(CP.METRAGEM_MAX, COALESCE(I.AREA_PRIVATIVA, 0)) AND COALESCE(I.SUITES, 0) BETWEEN COALESCE(CP.SUITE_MIN, COALESCE(I.SUITES, 0)) AND COALESCE(CP.SUITE_MAX, COALESCE(I.SUITES, 0)) AND COALESCE(I.QTD_VAGAS_ESTACIONAMENTO, 0) BETWEEN COALESCE(CP.GARAGENS_MIN, COALESCE(I.QTD_VAGAS_ESTACIONAMENTO, 0)) AND COALESCE(CP.GARAGENS_MAX, COALESCE(I.QTD_VAGAS_ESTACIONAMENTO, 0)) AND COALESCE(I.TIPO_IMOVEL_ID, 0) = COALESCE(CP.TIPO_IMOVEL_ID, COALESCE(I.TIPO_IMOVEL_ID, 0)) AND CASE WHEN COALESCE(CP.FLAG_INFRAESTRUTURA, FALSE) = TRUE THEN COALESCE(I.FLAG_INFRAESTRUTURA, FALSE) = TRUE ELSE TRUE END AND COALESCE(I.VALOR_CONDOMINIO, 0) BETWEEN COALESCE(CP.VALOR_CONDOMINIO_MIN, COALESCE(I.VALOR_CONDOMINIO, 0)) AND COALESCE(CP.VALOR_CONDOMINIO_MAX, COALESCE(I.VALOR_CONDOMINIO, 0)) AND COALESCE(I.POSICAO_SOL_ID, 0) = COALESCE(CP.POSICAO_SOL_ID, COALESCE(I.POSICAO_SOL_ID, 0)) AND CASE WHEN EXISTS(SELECT 1 FROM CLIENTE_PERFIL_BAIRRO CPB WHERE CPB.CLIENTE_PERFIL_ID = CP.ID) THEN I.BAIRRO_ID IN (SELECT BAIRRO_ID FROM CLIENTE_PERFIL_BAIRRO CPB WHERE CPB.CLIENTE_PERFIL_ID = CP.ID) ELSE TRUE END ORDER BY I.ID", model.getId());
		
		return broker.getCollectionBean(ImovelModel.class, "id", "flagAtivo", "codigo", "tipoImovelModel.id", "tipoImovelModel.descricao", "captadorModel.id", "captadorModel.nome", "bairroModel.id", "bairroModel.descricao", "edificioModel.id", "edificioModel.descricao", "valor", "endereco", "complemento", "andar", "unidade", "pontoReferencia", "cep", "observacoes", "posicaoSolModel.id", "posicaoSolModel.descricao", "quartos", "areaPrivativa", "suites", "qtdVagasEstacionamento");
		
	}

	@SuppressWarnings("unchecked")
	public List<ImovelModel> pesquisar(final ImovelModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder query = new StringBuilder("SELECT I.ID, I.FLAG_ATIVO, I.CODIGO, I.TIPO_IMOVEL_ID, (SELECT TI.DESCRICAO FROM TIPO_IMOVEL TI WHERE TI.ID = I.TIPO_IMOVEL_ID), I.CAPTADOR_ID, (SELECT C.NOME FROM USUARIO C WHERE C.ID = I.CAPTADOR_ID), I.BAIRRO_ID, (SELECT B.DESCRICAO FROM BAIRRO B WHERE B.ID = I.BAIRRO_ID), I.EDIFICIO_ID, (SELECT E.DESCRICAO FROM EDIFICIO E WHERE E.ID = I.EDIFICIO_ID), I.VALOR, (SELECT IF.ARQUIVO FROM IMOVEL_FOTO IF WHERE IF.IMOVEL_ID = I.ID AND IF.FLAG_ATIVO ORDER BY IF.ORDEM LIMIT 1), (SELECT IF.FLAG_PORTRAIT FROM IMOVEL_FOTO IF WHERE IF.IMOVEL_ID = I.ID AND IF.FLAG_ATIVO ORDER BY IF.ORDEM LIMIT 1), I.TITULO_ANUNCIO, I.DESCRICAO_ANUNCIO, I.ENDERECO FROM IMOVEL I, BAIRRO B WHERE I.BAIRRO_ID = B.ID AND I.FLAG_ATIVO = ? AND COALESCE(I.CODIGO, '') ILIKE COALESCE(?, COALESCE(I.CODIGO, '')) AND COALESCE(I.TIPO_IMOVEL_ID, 0) = COALESCE(?, COALESCE(I.TIPO_IMOVEL_ID, 0)) AND COALESCE(I.CAPTADOR_ID, 0) = COALESCE(?, COALESCE(I.CAPTADOR_ID, 0)) AND COALESCE(I.VALOR, 0) BETWEEN COALESCE(?, COALESCE(I.VALOR, 0)) AND COALESCE(?, COALESCE(I.VALOR, 0)) AND COALESCE(I.VALOR_CONDOMINIO, 0) BETWEEN COALESCE(?, COALESCE(I.VALOR_CONDOMINIO, 0)) AND COALESCE(?, COALESCE(I.VALOR_CONDOMINIO, 0)) AND COALESCE(I.QUARTOS, 0) BETWEEN COALESCE(?, COALESCE(I.QUARTOS, 0)) AND COALESCE(?, COALESCE(I.QUARTOS, 0)) AND COALESCE(I.PROPRIETARIO_ID, 0) = COALESCE(?, COALESCE(I.PROPRIETARIO_ID, 0)) AND COALESCE(I.CONDOMINIO_ID, 0) = COALESCE(?, COALESCE(I.CONDOMINIO_ID, 0)) ");

		if (!TSUtil.isEmpty(model.getBairrosPesquisa())) {
			query.append(" AND B.DESCRICAO IN (");
		}

		List<String> bairrosArray = new ArrayList<String>();

		for (String bairro : model.getBairrosPesquisa()) {
			query.append("?,");
			bairrosArray.add(bairro);
		}

		if (!TSUtil.isEmpty(model.getBairrosPesquisa())) {
			query.deleteCharAt(query.length() - 1);
			query.append(")");
		}

		query.append(" ORDER BY I.ID ");

		broker.setSQL(query.toString(), model.getFlagAtivo(), Utilitario.getStringIlike(model.getCodigo(), true), model.getTipoImovelModel().getId(), model.getCaptadorModel().getId(), model.getValorMin(), model.getValorMax(), model.getValorCondominioMin(), model.getValorCondominioMax(), model.getQuartosMin(), model.getQuartosMax(), model.getProprietarioModel().getId(), model.getCondominioModel().getId());

		if (!TSUtil.isEmpty(model.getBairrosPesquisa())) {
			broker.set(bairrosArray.toArray());
		}

		return broker.getCollectionBean(ImovelModel.class, "id", "flagAtivo", "codigo", "tipoImovelModel.id", "tipoImovelModel.descricao", "captadorModel.id", "captadorModel.nome", "bairroModel.id", "bairroModel.descricao", "edificioModel.id", "edificioModel.descricao", "valor", "imagemFotoPrincipalModel.arquivo", "imagemFotoPrincipalModel.flagPortrait", "tituloAnuncio", "descricaoAnuncio", "endereco");
	}

	public ImovelModel inserir(final ImovelModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.beginTransaction();

		model.setId(broker.getSequenceNextValue("imovel_id_seq"));

		broker.setSQL("INSERT INTO IMOVEL (ID, FLAG_ATIVO, CODIGO, TIPO_IMOVEL_ID, DATA_CADASTRO, USUARIO_CADASTRO_ID, FLAG_PISCINA_PRIVATIVA, VALOR, ENDERECO, COMPLEMENTO, EDIFICIO_ID, ANDAR, UNIDADE, PONTO_REFERENCIA, BAIRRO_ID, CEP, CONSTRUTORA_ID, TIPO_PISO_SALA_ID, TIPO_PISO_QUARTO_ID, ANO_CONSTRUCAO, AREA_PRIVATIVA, TIPO_FACHADA_ID, NOME_ADMINISTRADOR, TELEFONE_ADMINISTRADOR, VALOR_CONDOMINIO, QUARTOS, SUITES, BANHEIROS, VARANDAS, DEPENDENCIAS, WC_EMPREGADAS, ARMARIOS_BANHEIROS, ARMARIOS_COZINHAS, ARMARIOS_QUARTOS, CLOSETS, PAVIMENTOS, HOME_OFFICES, DESPENSAS, DEPOSITOS, FLAG_COZINHA_AMERICANA, FLAG_SALAO_FESTAS, FLAG_PLAYGROUND, FLAG_PISCINA, FLAG_ACADEMIA, FLAG_QUADRA_POLIESPORTIVA, FLAG_PARQUE_INFANTIL, FLAG_CAMPO, FLAG_SALAO_JOGOS, FLAG_SAUNA, FLAG_BRINQUEDOTECA, FLAG_CINEMA, FLAG_QUADRA_SQUASH, FLAG_QUADRA_TENIS, FLAG_CHURRASQUEIRA, FLAG_ESTACIONAMENTO_VISITANTE, FLAG_AGUA_INDIVIDUAL, FLAG_GAS_INDIVIDUAL, FLAG_GERADOR_PROPRIO, POSICAO_SOL_ID, FLAG_ELEVADOR, QTD_ELEVADORES, FLAG_MOBILIADO, DESCRICAO_MOBILIA, QTD_VAGAS_ESTACIONAMENTO, FLAG_UTILIZOU_FGTS, FLAG_IMOVEL_QUITADO, FLAG_TERRENO_FOREIRO, FLAG_EXCLUSIVIDADE, FLAG_HABITADO, COMISSAO, MOTIVO_VENDA, OUTROS_IMOVEIS_VENDA, OBSERVACOES, PROPRIETARIO_ID, CAPTADOR_ID, DATA_CAPTACAO, CAPTACAO_ID, FLAG_ARMARIOS_BANHEIRO, FLAG_ARMARIOS_COZINHA, FLAG_ARMARIOS_QUARTOS, QTD_VAGAS_COBERTAS, QTD_VAGAS_DESCOBERTAS, QTD_VAGAS_SOLTAS, FLAG_INFRAESTRUTURA, CONDOMINIO_ID, FLAG_VARIAS_VAGAS, FLAG_NAO_ANUNCIAR, DESCRICAO_ANUNCIO, TITULO_ANUNCIO, URL_AMIGAVEL, VALOR_IPTU, VALOR_ALUGUEL, FLAG_VENDA, FLAG_ALUGUEL) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", model.getId(), model.getFlagAtivo(), model.getCodigo(), model.getTipoImovelModel().getId(), new Timestamp(model.getDataCadastro().getTime()), model.getUsuarioCadastroModel().getId(), model.getFlagPiscinaPrivativa(), model.getValor(), model.getEndereco(), model.getComplemento(), model.getEdificioModel().getId(), model.getAndar(), model.getUnidade(), model.getPontoReferencia(), model.getBairroModel().getId(), model.getCep(), model.getConstrutoraModel().getId(), model.getTipoPisoSalaModel().getId(), model.getTipoPisoQuartoModel().getId(), model.getAnoConstrucao(), model.getAreaPrivativa(), model.getTipoFachadaModel().getId(), model.getNomeAdministrador(), model.getTelefoneAdministrador(), model.getValorCondominio(), model.getQuartos(), model.getSuites(), model.getBanheiros(), model.getVarandas(), model.getDependencias(), model.getWcEmpregadas(), model.getArmariosBanheiros(), model.getArmariosCozinhas(), model.getArmariosQuartos(), model.getClosets(), model.getPavimentos(), model.getHomeOffices(), model.getDespensas(), model.getDepositos(), model.getFlagCozinhaAmericana(), model.getFlagSalaoFestas(), model.getFlagPlayground(), model.getFlagPiscina(), model.getFlagAcademia(), model.getFlagQuadraPoliesportiva(), model.getFlagParqueInfantil(), model.getFlagCampo(), model.getFlagSalaoJogos(), model.getFlagSauna(), model.getFlagBrinquedoteca(), model.getFlagCinema(), model.getFlagQuadraSquash(), model.getFlagQuadraTenis(), model.getFlagChurrasqueira(), model.getFlagEstacionamentoVisitante(), model.getFlagAguaIndividual(), model.getFlagGasIndividual(), model.getFlagGeradorProprio(), model.getPosicaoSolModel().getId(), model.getFlagElevador(), model.getQtdElevadores(), model.getFlagMobiliado(), model.getDescricaoMobilia(), model.getQtdVagasEstacionamento(), model.getFlagUtilizouFgts(), model.getFlagImovelQuitado(), model.getFlagTerrenoForeiro(), model.getFlagExclusividade(), model.getFlagHabitado(), model.getComissao(), model.getMotivoVenda(), model.getOutrosImoveisVenda(), model.getObservacoes(), model.getProprietarioModel().getId(), model.getCaptadorModel().getId(), model.getDataCaptacao(), model.getCaptacaoModel().getId(), model.getFlagArmariosBanheiro(), model.getFlagArmariosCozinha(), model.getFlagArmariosQuartos(), model.getQtdVagasCobertas(), model.getQtdVagasDescobertas(), model.getQtdVagasSoltas(), model.getFlagInfraestrutura(), model.getCondominioModel().getId(), model.getFlagVariasVagas(), model.getFlagNaoAnunciar(), model.getDescricaoAnuncio(), model.getTituloAnuncio(), model.getUrlAmigavel(), model.getValorIPTU(), model.getValorAluguel(), model.getFlagVenda(), model.getFlagAluguel());

		broker.execute();
		
		int cont = 0;
		
		for(ImovelFotoModel foto : model.getFotos()){
			
			foto.setOrdem(cont);
			
			this.inserir(foto, broker);
			
			cont++;
			
		}
		
		broker.endTransaction();

		return model;
	}
	
	public ImovelFotoModel inserir(final ImovelFotoModel model, TSDataBaseBrokerIf broker) throws TSApplicationException {
		
		model.setId(broker.getSequenceNextValue("imovel_foto_id_seq"));
		
		broker.setSQL("INSERT INTO IMOVEL_FOTO (ID, IMOVEL_ID, ARQUIVO, ORDEM, FLAG_PORTRAIT) VALUES (?, ?, ?, ?, ?)", model.getId(), model.getImovelModel().getId(), model.getArquivo(), model.getOrdem(), model.getFlagPortrait());
		
		broker.execute();
		
		return model;
	}

	public ImovelModel alterar(final ImovelModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.beginTransaction();

		broker.setSQL("UPDATE IMOVEL SET CODIGO = ?, FLAG_ATIVO = ?, TIPO_IMOVEL_ID=?, DATA_CADASTRO=?, USUARIO_CADASTRO_ID=?, FLAG_PISCINA_PRIVATIVA=?, VALOR=?, ENDERECO=?, COMPLEMENTO=?, EDIFICIO_ID=?, ANDAR=?, UNIDADE=?, PONTO_REFERENCIA=?, BAIRRO_ID=?,  CEP=?, CONSTRUTORA_ID=?, TIPO_PISO_SALA_ID=?, TIPO_PISO_QUARTO_ID=?,  ANO_CONSTRUCAO=?, AREA_PRIVATIVA=?, TIPO_FACHADA_ID=?, NOME_ADMINISTRADOR=?, TELEFONE_ADMINISTRADOR=?, VALOR_CONDOMINIO=?, QUARTOS=?, SUITES=?, BANHEIROS=?, VARANDAS=?, DEPENDENCIAS=?, WC_EMPREGADAS=?, ARMARIOS_BANHEIROS=?, ARMARIOS_COZINHAS=?, ARMARIOS_QUARTOS=?, CLOSETS=?, PAVIMENTOS=?, HOME_OFFICES=?, DESPENSAS=?, DEPOSITOS=?, FLAG_COZINHA_AMERICANA=?, FLAG_SALAO_FESTAS=?, FLAG_PLAYGROUND=?, FLAG_PISCINA=?, FLAG_ACADEMIA=?, FLAG_QUADRA_POLIESPORTIVA=?, FLAG_PARQUE_INFANTIL=?, FLAG_CAMPO=?, FLAG_SALAO_JOGOS=?, FLAG_SAUNA=?, FLAG_BRINQUEDOTECA=?, FLAG_CINEMA=?, FLAG_QUADRA_SQUASH=?, FLAG_QUADRA_TENIS=?, FLAG_CHURRASQUEIRA=?, FLAG_ESTACIONAMENTO_VISITANTE=?, FLAG_AGUA_INDIVIDUAL=?, FLAG_GAS_INDIVIDUAL=?, FLAG_GERADOR_PROPRIO=?, POSICAO_SOL_ID=?, FLAG_ELEVADOR=?, QTD_ELEVADORES=?, FLAG_MOBILIADO=?, DESCRICAO_MOBILIA=?, QTD_VAGAS_ESTACIONAMENTO=?, FLAG_UTILIZOU_FGTS=?, FLAG_IMOVEL_QUITADO=?, FLAG_TERRENO_FOREIRO=?, FLAG_EXCLUSIVIDADE=?, FLAG_HABITADO=?, COMISSAO=?, MOTIVO_VENDA=?, OUTROS_IMOVEIS_VENDA=?, OBSERVACOES=?, PROPRIETARIO_ID=?, CAPTADOR_ID=?, DATA_CAPTACAO=?, FLAG_ARMARIOS_BANHEIRO=?, FLAG_ARMARIOS_COZINHA=?, FLAG_ARMARIOS_QUARTOS=?, QTD_VAGAS_COBERTAS=?, QTD_VAGAS_DESCOBERTAS=?, QTD_VAGAS_SOLTAS=?, FLAG_INFRAESTRUTURA=?, CONDOMINIO_ID=?, FLAG_VARIAS_VAGAS=?, FLAG_NAO_ANUNCIAR=?, DESCRICAO_ANUNCIO=?, TITULO_ANUNCIO=?, URL_AMIGAVEL=?, VALOR_IPTU=?, VALOR_ALUGUEL=?, FLAG_VENDA=?, FLAG_ALUGUEL=? WHERE ID = ?", model.getCodigo(), model.getFlagAtivo(), model.getTipoImovelModel().getId(), new Timestamp(model.getDataCadastro().getTime()), model.getUsuarioCadastroModel().getId(), model.getFlagPiscinaPrivativa(), model.getValor(), model.getEndereco(), model.getComplemento(), model.getEdificioModel().getId(), model.getAndar(), model.getUnidade(), model.getPontoReferencia(), model.getBairroModel().getId(), model.getCep(), model.getConstrutoraModel().getId(), model.getTipoPisoSalaModel().getId(), model.getTipoPisoQuartoModel().getId(), model.getAnoConstrucao(), model.getAreaPrivativa(), model.getTipoFachadaModel().getId(), model.getNomeAdministrador(), model.getTelefoneAdministrador(), model.getValorCondominio(), model.getQuartos(), model.getSuites(), model.getBanheiros(), model.getVarandas(), model.getDependencias(), model.getWcEmpregadas(), model.getArmariosBanheiros(), model.getArmariosCozinhas(), model.getArmariosQuartos(), model.getClosets(), model.getPavimentos(), model.getHomeOffices(), model.getDespensas(), model.getDepositos(), model.getFlagCozinhaAmericana(), model.getFlagSalaoFestas(), model.getFlagPlayground(), model.getFlagPiscina(), model.getFlagAcademia(), model.getFlagQuadraPoliesportiva(), model.getFlagParqueInfantil(), model.getFlagCampo(), model.getFlagSalaoJogos(), model.getFlagSauna(), model.getFlagBrinquedoteca(), model.getFlagCinema(), model.getFlagQuadraSquash(), model.getFlagQuadraTenis(), model.getFlagChurrasqueira(), model.getFlagEstacionamentoVisitante(), model.getFlagAguaIndividual(), model.getFlagGasIndividual(), model.getFlagGeradorProprio(), model.getPosicaoSolModel().getId(), model.getFlagElevador(), model.getQtdElevadores(), model.getFlagMobiliado(), model.getDescricaoMobilia(), model.getQtdVagasEstacionamento(), model.getFlagUtilizouFgts(), model.getFlagImovelQuitado(), model.getFlagTerrenoForeiro(), model.getFlagExclusividade(), model.getFlagHabitado(), model.getComissao(), model.getMotivoVenda(), model.getOutrosImoveisVenda(), model.getObservacoes(), model.getProprietarioModel().getId(), model.getCaptadorModel().getId(), model.getDataCaptacao(), model.getFlagArmariosBanheiro(), model.getFlagArmariosCozinha(), model.getFlagArmariosQuartos(), model.getQtdVagasCobertas(), model.getQtdVagasDescobertas(), model.getQtdVagasSoltas(), model.getFlagInfraestrutura(), model.getCondominioModel().getId(), model.getFlagVariasVagas(), model.getFlagNaoAnunciar(), model.getDescricaoAnuncio(), model.getTituloAnuncio(), model.getUrlAmigavel(), model.getValorIPTU(), model.getValorAluguel(), model.getFlagVenda(), model.getFlagAluguel(), model.getId());

		broker.execute();
		
		int cont = 0;
		
		for(ImovelFotoModel foto : model.getFotos()){
			
			foto.setOrdem(cont);
			
			if(TSUtil.isEmpty(foto.getId())){
				
				this.inserir(foto, broker);
				
			} else {
				
				this.alterar(foto, broker);
				
			}
			
			cont++;
		}
		
		broker.endTransaction();

		return model;
	}

	public ImovelFotoModel alterar(final ImovelFotoModel model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.setSQL("UPDATE IMOVEL_FOTO SET ORDEM = ? WHERE ID = ?", model.getOrdem(), model.getId());

		broker.execute();

		return model;
	}
	
	public ImovelModel excluir(final ImovelModel model) throws TSApplicationException {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("UPDATE IMOVEL SET FLAG_ATIVO = FALSE WHERE ID = ?", model.getId());
		
		broker.execute();
		
		return model;
	}

	@SuppressWarnings("unchecked")
	public List<ImovelModel> pesquisar(String dadosProprietario) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT I.ID, I.FLAG_ATIVO, I.CODIGO, I.TIPO_IMOVEL_ID, (SELECT TI.DESCRICAO FROM TIPO_IMOVEL TI WHERE TI.ID = I.TIPO_IMOVEL_ID), I.CAPTADOR_ID, (SELECT C.NOME FROM USUARIO C WHERE C.ID = I.CAPTADOR_ID), I.BAIRRO_ID, (SELECT B.DESCRICAO FROM BAIRRO B WHERE B.ID = I.BAIRRO_ID), I.EDIFICIO_ID, (SELECT E.DESCRICAO FROM EDIFICIO E WHERE E.ID = I.EDIFICIO_ID), I.VALOR FROM IMOVEL I, PROPRIETARIO P WHERE P.ID = I.PROPRIETARIO_ID AND SEM_ACENTOS(BUSCA_PROPRIETARIO(P.ID)) ILIKE SEM_ACENTOS(COALESCE(?, SEM_ACENTOS(BUSCA_PROPRIETARIO(P.ID)))) ", Utilitario.getStringIlike(dadosProprietario, true));

		return broker.getCollectionBean(ImovelModel.class, "id", "flagAtivo", "codigo", "tipoImovelModel.id", "tipoImovelModel.descricao", "captadorModel.id", "captadorModel.nome", "bairroModel.id", "bairroModel.descricao", "edificioModel.id", "edificioModel.descricao", "valor");
	}

	public Boolean isExisteImovel(String campo) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT EXISTS(SELECT 1 FROM IMOVEL I, PROPRIETARIO P WHERE P.ID = I.PROPRIETARIO_ID AND SEM_ACENTOS(BUSCA_PROPRIETARIO(P.ID)) ILIKE SEM_ACENTOS(COALESCE(?, SEM_ACENTOS(BUSCA_PROPRIETARIO(P.ID))))) ", Utilitario.getStringIlike(campo, true));

		return (Boolean) broker.getObject();
	}
	
	public Long obterQtdImoveisEstoque(UsuarioModel usuario) {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT COUNT(*) FROM IMOVEL I WHERE I.CAPTADOR_ID = ? ", usuario.getId());
		
		return (Long) broker.getObject();
	}
	
	public ImovelAtualizacaoModel obterAtualizacao(final ImovelAtualizacaoModel model) {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT IA.ID, IA.IMOVEL_ID, IA.TIPO_ATUALIZACAO_IMOVEL_ID, (SELECT TAI.DESCRICAO FROM TIPO_ATUALIZACAO_IMOVEL TAI WHERE TAI.ID = IA.TIPO_ATUALIZACAO_IMOVEL_ID), IA.DATA, IA.OBSERVACAO, IA.DATA_CADASTRO, IA.USUARIO_CADASTRO_ID, (SELECT U.NOME FROM USUARIO U WHERE U.ID = IA.USUARIO_CADASTRO_ID) FROM IMOVEL_ATUALIZACAO IA WHERE IA.ID = ? ", model.getId());
		
		return (ImovelAtualizacaoModel)broker.getObjectBean(ImovelAtualizacaoModel.class, "id", "imovelModel.id", "tipoAtualizacaoImovelModel.id", "tipoAtualizacaoImovelModel.descricao", "data", "observacao", "dataCadastro", "usuarioCadastroModel.id", "usuarioCadastroModel.nome");
	}
	
	public ImovelAtualizacaoModel excluir(final ImovelAtualizacaoModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("UPDATE IMOVEL_ATUALIZACAO SET FLAG_ATIVO = FALSE WHERE ID = ?", model.getId());

		broker.execute();

		return model;
	}
	
	public ImovelFotoModel excluir(final ImovelFotoModel model) throws TSApplicationException {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("UPDATE IMOVEL_FOTO SET FLAG_ATIVO = FALSE WHERE ID = ?", model.getId());
		
		broker.execute();
		
		return model;
	}
	
	@SuppressWarnings("unchecked")
	public List<ImovelAtualizacaoModel> pesquisarAtualizacoes(final ImovelModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT IA.ID, IA.IMOVEL_ID, IA.TIPO_ATUALIZACAO_IMOVEL_ID, (SELECT TAI.DESCRICAO FROM TIPO_ATUALIZACAO_IMOVEL TAI WHERE TAI.ID = IA.TIPO_ATUALIZACAO_IMOVEL_ID), IA.DATA, IA.OBSERVACAO, IA.DATA_CADASTRO, IA.USUARIO_CADASTRO_ID, (SELECT U.NOME FROM USUARIO U WHERE U.ID = IA.USUARIO_CADASTRO_ID) FROM IMOVEL_ATUALIZACAO IA WHERE IA.IMOVEL_ID = ? AND IA.FLAG_ATIVO ORDER BY IA.ID", model.getId());

		return broker.getCollectionBean(ImovelAtualizacaoModel.class, "id", "imovelModel.id", "tipoAtualizacaoImovelModel.id", "tipoAtualizacaoImovelModel.descricao", "data", "observacao", "dataCadastro", "usuarioCadastroModel.id", "usuarioCadastroModel.nome");
	}
	
	@SuppressWarnings("unchecked")
	public List<ImovelFotoModel> pesquisarFotos(final ImovelModel model) {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT IF.ID, IF.IMOVEL_ID, IF.ARQUIVO, IF.ORDEM, IF.FLAG_PORTRAIT FROM IMOVEL_FOTO IF WHERE IF.IMOVEL_ID = ? AND IF.FLAG_ATIVO ORDER BY IF.ORDEM", model.getId());
		
		return broker.getCollectionBean(ImovelFotoModel.class, "id", "imovelModel.id", "arquivo", "ordem", "flagPortrait");
	}
	
	@SuppressWarnings("unchecked")
	public List<String> pesquisarGaleria(final ImovelModel model) {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("SELECT IF.ARQUIVO FROM IMOVEL_FOTO IF WHERE IF.IMOVEL_ID = ? AND IF.FLAG_ATIVO ORDER BY IF.ORDEM", model.getId());
		
		return broker.getList();
	}
	
	public ImovelAtualizacaoModel inserir(final ImovelAtualizacaoModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("imovel_atualizacao_id_seq"));

		broker.setSQL("INSERT INTO IMOVEL_ATUALIZACAO (ID, IMOVEL_ID, TIPO_ATUALIZACAO_IMOVEL_ID, DATA, OBSERVACAO, DATA_CADASTRO, USUARIO_CADASTRO_ID) VALUES (?, ?, ?, ?, ?, ?, ?)", model.getId(), model.getImovelModel().getId(), model.getTipoAtualizacaoImovelModel().getId(), new Timestamp(model.getData().getTime()), model.getObservacao(), new Timestamp(model.getDataCadastro().getTime()), model.getUsuarioCadastroModel().getId());

		broker.execute();

		return model;
	}
	
	public ImovelAtualizacaoModel alterar(final ImovelAtualizacaoModel model) throws TSApplicationException {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("UPDATE IMOVEL_ATUALIZACAO SET TIPO_ATUALIZACAO_IMOVEL_ID = ?, DATA = ?, OBSERVACAO = ? WHERE ID = ?", model.getTipoAtualizacaoImovelModel().getId(), new Timestamp(model.getData().getTime()), model.getObservacao(), model.getId());
		
		broker.execute();
		
		return model;
	}

}
