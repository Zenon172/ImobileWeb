package br.com.sysfar.imobileweb.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.sysfar.imobileweb.model.ImovelModel;
import br.com.sysfar.imobileweb.model.ProprietarioModel;
import br.com.sysfar.imobileweb.util.Utilitario;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public final class ImovelDAO implements CrudDAO<ImovelModel> {

	public ImovelModel obter(final ImovelModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT I.ID, I.TIPO_IMOVEL_ID, I.DATA_CADASTRO, I.USUARIO_CADASTRO_ID, (SELECT U.NOME FROM USUARIO U WHERE U.ID = I.USUARIO_CADASTRO_ID), I.FLAG_PISCINA_PRIVATIVA, I.VALOR, I.ENDERECO, I.COMPLEMENTO, I.EDIFICIO_ID, ANDAR, I.UNIDADE, I.PONTO_REFERENCIA, I.BAIRRO_ID, I.CEP, I.CONSTRUTORA_ID, I.TIPO_PISO_SALA_ID, I.TIPO_PISO_QUARTO_ID, I.ANO_CONSTRUCAO, I.AREA_PRIVATIVA, I.TIPO_FACHADA_ID, I.NOME_ADMINISTRADOR, I.TELEFONE_ADMINISTRADOR, I.VALOR_CONDOMINIO, I.QUARTOS, I.SUITES, I.BANHEIROS, I.VARANDAS, I.DEPENDENCIAS, I.WC_EMPREGADAS, I.ARMARIOS_BANHEIROS, I.ARMARIOS_COZINHAS, I.ARMARIOS_QUARTOS, I.CLOSETS, I.PAVIMENTOS, I.HOME_OFFICES, I.DESPENSAS, I.DEPOSITOS, I.FLAG_COZINHA_AMERICANA, I.FLAG_SALAO_FESTAS, I.FLAG_PLAYGROUND, I.FLAG_PISCINA, I.FLAG_ACADEMIA, I.FLAG_QUADRA_POLIESPORTIVA, I.FLAG_PARQUE_INFANTIL, I.FLAG_CAMPO, I.FLAG_SALAO_JOGOS, I.FLAG_SAUNA, I.FLAG_BRINQUEDOTECA, I.FLAG_CINEMA, I.FLAG_QUADRA_SQUASH, I.FLAG_QUADRA_TENIS, I.FLAG_CHURRASQUEIRA, I.FLAG_ESTACIONAMENTO_VISITANTE, I.FLAG_AGUA_INDIVIDUAL, I.FLAG_GAS_INDIVIDUAL, I.FLAG_GERADOR_PROPRIO, I.POSICAO_SOL_ID, I.FLAG_ELEVADOR, I.QTD_ELEVADORES, I.FLAG_MOBILIADO, I.DESCRICAO_MOBILIA, I.QTD_VAGAS_ESTACIONAMENTO, I.FLAG_UTILIZOU_FGTS, I.FLAG_IMOVEL_QUITADO, I.FLAG_TERRENO_FOREIRO, I.FLAG_EXCLUSIVIDADE, I.FLAG_HABITADO, I.COMISSAO, I.MOTIVO_VENDA, I.OUTROS_IMOVEIS_VENDA, I.OBSERVACOES, I.PROPRIETARIO_ID, I.CAPTADOR_ID, I.DATA_CAPTACAO FROM IMOVEL I WHERE I.ID = ?", model.getId());

		return (ImovelModel) broker.getObjectBean(ImovelModel.class, "id", "tipoImovelModel.id", "dataCadastro", "usuarioCadastroModel.id", "usuarioCadastroModel.nome", "flagPiscinaPrivativa", "valor", "endereco", "complemento", "edificioModel.id", "andar", "unidade", "pontoReferencia", "bairroModel.id", "cep", "construtoraModel.id", "tipoPisoSalaModel.id", "tipoPisoQuartoModel.id", "anoConstrucao", "areaPrivativa", "tipoFachadaModel.id", "nomeAdministrador", "telefoneAdministrador", "valorCondominio", "quartos", "suites", "banheiros", "varandas", "dependencias", "wcEmpregadas", "armariosBanheiros", "armariosCozinhas", "armariosQuartos", "closets", "pavimentos", "homeOffices", "despensas", "depositos", "flagCozinhaAmericana", "flagSalaoFestas", "flagPlayground", "flagPiscina", "flagAcademia", "flagQuadraPoliesportiva", "flagParqueInfantil", "flagCampo", "flagSalaoJogos", "flagSauna", "flagBrinquedoteca", "flagCinema", "flagQuadraSquash", "flagQuadraTenis", "flagChurrasqueira", "flagEstacionamentoVisitante", "flagAguaIndividual", "flagGasIndividual", "flagGeradorProprio", "posicaoSolModel.id", "flagElevador", "qtdElevadores", "flagMobiliado", "descricaoMobilia", "qtdVagasEstacionamento", "flagUtilizouFgts", "flagImovelQuitado", "flagTerrenoForeiro", "flagExclusividade", "flagHabitado", "comissao", "motivoVenda", "outrosImoveisVenda", "observacoes", "proprietarioModel.id", "captadorModel.id", "dataCaptacao");

	}

	@SuppressWarnings("unchecked")
	public List<ImovelModel> pesquisar(final ProprietarioModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT I.ID, I.CODIGO, I.TIPO_IMOVEL_ID, (SELECT TI.DESCRICAO FROM TIPO_IMOVEL TI WHERE TI.ID = I.TIPO_IMOVEL_ID), I.CAPTADOR_ID, (SELECT C.NOME FROM USUARIO C WHERE C.ID = I.CAPTADOR_ID), I.BAIRRO_ID, (SELECT B.DESCRICAO FROM BAIRRO B WHERE B.ID = I.BAIRRO_ID), I.EDIFICIO_ID, (SELECT E.DESCRICAO FROM EDIFICIO E WHERE E.ID = I.EDIFICIO_ID), I.VALOR, I.ENDERECO, I.COMPLEMENTO, I.ANDAR, I.UNIDADE, I.PONTO_REFERENCIA, I.CEP, I.OBSERVACOES, I.POSICAO_SOL_ID, (SELECT PS.DESCRICAO FROM POSICAO_SOL PS WHERE PS.ID = I.POSICAO_SOL_ID) FROM IMOVEL I WHERE I.PROPRIETARIO_ID = ? ", model.getId());

		return broker.getCollectionBean(ImovelModel.class, "id", "codigo", "tipoImovelModel.id", "tipoImovelModel.descricao", "captadorModel.id", "captadorModel.nome", "bairroModel.id", "bairroModel.descricao", "edificioModel.id", "edificioModel.descricao", "valor", "endereco", "complemento", "andar", "unidade", "pontoReferencia", "cep", "observacoes", "posicaoSolModel.id", "posicaoSolModel.descricao");

	}

	@SuppressWarnings("unchecked")
	public List<ImovelModel> pesquisar(final ImovelModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder query = new StringBuilder("SELECT I.ID, I.CODIGO, I.TIPO_IMOVEL_ID, (SELECT TI.DESCRICAO FROM TIPO_IMOVEL TI WHERE TI.ID = I.TIPO_IMOVEL_ID), I.CAPTADOR_ID, (SELECT C.NOME FROM USUARIO C WHERE C.ID = I.CAPTADOR_ID), I.BAIRRO_ID, (SELECT B.DESCRICAO FROM BAIRRO B WHERE B.ID = I.BAIRRO_ID), I.EDIFICIO_ID, (SELECT E.DESCRICAO FROM EDIFICIO E WHERE E.ID = I.EDIFICIO_ID), I.VALOR FROM IMOVEL I, BAIRRO B WHERE I.BAIRRO_ID = B.ID AND COALESCE(I.CODIGO, '') ILIKE COALESCE(?, COALESCE(I.CODIGO, '')) AND COALESCE(I.TIPO_IMOVEL_ID, 0) = COALESCE(?, COALESCE(I.TIPO_IMOVEL_ID, 0)) AND COALESCE(I.CAPTADOR_ID, 0) = COALESCE(?, COALESCE(I.CAPTADOR_ID, 0)) AND COALESCE(I.VALOR, 0) BETWEEN COALESCE(?, COALESCE(I.VALOR, 0)) AND COALESCE(?, COALESCE(I.VALOR, 0)) AND COALESCE(I.VALOR_CONDOMINIO, 0) BETWEEN COALESCE(?, COALESCE(I.VALOR_CONDOMINIO, 0)) AND COALESCE(?, COALESCE(I.VALOR_CONDOMINIO, 0)) AND COALESCE(I.QUARTOS, 0) BETWEEN COALESCE(?, COALESCE(I.QUARTOS, 0)) AND COALESCE(?, COALESCE(I.QUARTOS, 0)) AND COALESCE(I.PROPRIETARIO_ID, 0) = COALESCE(?, COALESCE(I.PROPRIETARIO_ID, 0)) ");

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

		broker.setSQL(query.toString(), Utilitario.getStringIlike(model.getCodigo(), true), model.getTipoImovelModel().getId(), model.getCaptadorModel().getId(), model.getValorMin(), model.getValorMax(), model.getValorCondominioMin(), model.getValorCondominioMax(), model.getQuartosMin(), model.getQuartosMax(), model.getProprietarioModel().getId());

		if (!TSUtil.isEmpty(model.getBairrosPesquisa())) {
			broker.set(bairrosArray.toArray());
		}

		return broker.getCollectionBean(ImovelModel.class, "id", "codigo", "tipoImovelModel.id", "tipoImovelModel.descricao", "captadorModel.id", "captadorModel.nome", "bairroModel.id", "bairroModel.descricao", "edificioModel.id", "edificioModel.descricao", "valor");
	}

	public ImovelModel inserir(final ImovelModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("imovel_id_seq"));

		broker.setSQL("INSERT INTO IMOVEL (ID, TIPO_IMOVEL_ID, DATA_CADASTRO, USUARIO_CADASTRO_ID, FLAG_PISCINA_PRIVATIVA, VALOR, ENDERECO, COMPLEMENTO, EDIFICIO_ID, ANDAR, UNIDADE, PONTO_REFERENCIA, BAIRRO_ID, CEP, CONSTRUTORA_ID, TIPO_PISO_SALA_ID, TIPO_PISO_QUARTO_ID, ANO_CONSTRUCAO, AREA_PRIVATIVA, TIPO_FACHADA_ID, NOME_ADMINISTRADOR, TELEFONE_ADMINISTRADOR, VALOR_CONDOMINIO, QUARTOS, SUITES, BANHEIROS, VARANDAS, DEPENDENCIAS, WC_EMPREGADAS, ARMARIOS_BANHEIROS, ARMARIOS_COZINHAS, ARMARIOS_QUARTOS, CLOSETS, PAVIMENTOS, HOME_OFFICES, DESPENSAS, DEPOSITOS, FLAG_COZINHA_AMERICANA, FLAG_SALAO_FESTAS, FLAG_PLAYGROUND, FLAG_PISCINA, FLAG_ACADEMIA, FLAG_QUADRA_POLIESPORTIVA, FLAG_PARQUE_INFANTIL, FLAG_CAMPO, FLAG_SALAO_JOGOS, FLAG_SAUNA, FLAG_BRINQUEDOTECA, FLAG_CINEMA, FLAG_QUADRA_SQUASH, FLAG_QUADRA_TENIS, FLAG_CHURRASQUEIRA, FLAG_ESTACIONAMENTO_VISITANTE, FLAG_AGUA_INDIVIDUAL, FLAG_GAS_INDIVIDUAL, FLAG_GERADOR_PROPRIO, POSICAO_SOL_ID, FLAG_ELEVADOR, QTD_ELEVADORES, FLAG_MOBILIADO, DESCRICAO_MOBILIA, QTD_VAGAS_ESTACIONAMENTO, FLAG_UTILIZOU_FGTS, FLAG_IMOVEL_QUITADO, FLAG_TERRENO_FOREIRO, FLAG_EXCLUSIVIDADE, FLAG_HABITADO, COMISSAO, MOTIVO_VENDA, OUTROS_IMOVEIS_VENDA, OBSERVACOES, PROPRIETARIO_ID, CAPTADOR_ID, DATA_CAPTACAO, CAPTACAO_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", model.getId(), model.getTipoImovelModel().getId(), new Timestamp(model.getDataCadastro().getTime()), model.getUsuarioCadastroModel().getId(), model.getFlagPiscinaPrivativa(), model.getValor(), model.getEndereco(), model.getComplemento(), model.getEdificioModel().getId(), model.getAndar(), model.getUnidade(), model.getPontoReferencia(), model.getBairroModel().getId(), model.getCep(), model.getConstrutoraModel().getId(), model.getTipoPisoSalaModel().getId(), model.getTipoPisoQuartoModel().getId(), model.getAnoConstrucao(), model.getAreaPrivativa(), model.getTipoFachadaModel().getId(), model.getNomeAdministrador(), model.getTelefoneAdministrador(), model.getValorCondominio(), model.getQuartos(), model.getSuites(), model.getBanheiros(), model.getVarandas(), model.getDependencias(), model.getWcEmpregadas(), model.getArmariosBanheiros(), model.getArmariosCozinhas(), model.getArmariosQuartos(), model.getClosets(), model.getPavimentos(), model.getHomeOffices(), model.getDespensas(), model.getDepositos(), model.getFlagCozinhaAmericana(), model.getFlagSalaoFestas(), model.getFlagPlayground(), model.getFlagPiscina(), model.getFlagAcademia(), model.getFlagQuadraPoliesportiva(), model.getFlagParqueInfantil(), model.getFlagCampo(), model.getFlagSalaoJogos(), model.getFlagSauna(), model.getFlagBrinquedoteca(), model.getFlagCinema(), model.getFlagQuadraSquash(), model.getFlagQuadraTenis(), model.getFlagChurrasqueira(), model.getFlagEstacionamentoVisitante(), model.getFlagAguaIndividual(), model.getFlagGasIndividual(), model.getFlagGeradorProprio(), model.getPosicaoSolModel().getId(), model.getFlagElevador(), model.getQtdElevadores(), model.getFlagMobiliado(), model.getDescricaoMobilia(), model.getQtdVagasEstacionamento(), model.getFlagUtilizouFgts(), model.getFlagImovelQuitado(), model.getFlagTerrenoForeiro(), model.getFlagExclusividade(), model.getFlagHabitado(), model.getComissao(), model.getMotivoVenda(), model.getOutrosImoveisVenda(), model.getObservacoes(), model.getProprietarioModel().getId(), model.getCaptadorModel().getId(), model.getDataCaptacao(), model.getCaptacaoModel().getId());

		broker.execute();

		return model;
	}

	public ImovelModel alterar(final ImovelModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("UPDATE IMOVEL SET TIPO_IMOVEL_ID=?, DATA_CADASTRO=?, USUARIO_CADASTRO_ID=?, FLAG_PISCINA_PRIVATIVA=?, VALOR=?, ENDERECO=?, COMPLEMENTO=?, EDIFICIO_ID=?, ANDAR=?, UNIDADE=?, PONTO_REFERENCIA=?, BAIRRO_ID=?,  CEP=?, CONSTRUTORA_ID=?, TIPO_PISO_SALA_ID=?, TIPO_PISO_QUARTO_ID=?,  ANO_CONSTRUCAO=?, AREA_PRIVATIVA=?, TIPO_FACHADA_ID=?, NOME_ADMINISTRADOR=?, TELEFONE_ADMINISTRADOR=?, VALOR_CONDOMINIO=?, QUARTOS=?, SUITES=?, BANHEIROS=?, VARANDAS=?, DEPENDENCIAS=?, WC_EMPREGADAS=?, ARMARIOS_BANHEIROS=?, ARMARIOS_COZINHAS=?, ARMARIOS_QUARTOS=?, CLOSETS=?, PAVIMENTOS=?, HOME_OFFICES=?, DESPENSAS=?, DEPOSITOS=?, FLAG_COZINHA_AMERICANA=?, FLAG_SALAO_FESTAS=?, FLAG_PLAYGROUND=?, FLAG_PISCINA=?, FLAG_ACADEMIA=?, FLAG_QUADRA_POLIESPORTIVA=?, FLAG_PARQUE_INFANTIL=?, FLAG_CAMPO=?, FLAG_SALAO_JOGOS=?, FLAG_SAUNA=?, FLAG_BRINQUEDOTECA=?, FLAG_CINEMA=?, FLAG_QUADRA_SQUASH=?, FLAG_QUADRA_TENIS=?, FLAG_CHURRASQUEIRA=?, FLAG_ESTACIONAMENTO_VISITANTE=?, FLAG_AGUA_INDIVIDUAL=?, FLAG_GAS_INDIVIDUAL=?, FLAG_GERADOR_PROPRIO=?, POSICAO_SOL_ID=?, FLAG_ELEVADOR=?, QTD_ELEVADORES=?, FLAG_MOBILIADO=?, DESCRICAO_MOBILIA=?, QTD_VAGAS_ESTACIONAMENTO=?, FLAG_UTILIZOU_FGTS=?, FLAG_IMOVEL_QUITADO=?, FLAG_TERRENO_FOREIRO=?, FLAG_EXCLUSIVIDADE=?, FLAG_HABITADO=?, COMISSAO=?, MOTIVO_VENDA=?, OUTROS_IMOVEIS_VENDA=?, OBSERVACOES=?, PROPRIETARIO_ID=?, CAPTADOR_ID=?, DATA_CAPTACAO=? WHERE ID = ?", model.getTipoImovelModel().getId(), new Timestamp(model.getDataCadastro().getTime()), model.getUsuarioCadastroModel().getId(), model.getFlagPiscinaPrivativa(), model.getValor(), model.getEndereco(), model.getComplemento(), model.getEdificioModel().getId(), model.getAndar(), model.getUnidade(), model.getPontoReferencia(), model.getBairroModel().getId(), model.getCep(), model.getConstrutoraModel().getId(), model.getTipoPisoSalaModel().getId(), model.getTipoPisoQuartoModel().getId(), model.getAnoConstrucao(), model.getAreaPrivativa(), model.getTipoFachadaModel().getId(), model.getNomeAdministrador(), model.getTelefoneAdministrador(), model.getValorCondominio(), model.getQuartos(), model.getSuites(), model.getBanheiros(), model.getVarandas(), model.getDependencias(), model.getWcEmpregadas(), model.getArmariosBanheiros(), model.getArmariosCozinhas(), model.getArmariosQuartos(), model.getClosets(), model.getPavimentos(), model.getHomeOffices(), model.getDespensas(), model.getDepositos(), model.getFlagCozinhaAmericana(), model.getFlagSalaoFestas(), model.getFlagPlayground(), model.getFlagPiscina(), model.getFlagAcademia(), model.getFlagQuadraPoliesportiva(), model.getFlagParqueInfantil(), model.getFlagCampo(), model.getFlagSalaoJogos(), model.getFlagSauna(), model.getFlagBrinquedoteca(), model.getFlagCinema(), model.getFlagQuadraSquash(), model.getFlagQuadraTenis(), model.getFlagChurrasqueira(), model.getFlagEstacionamentoVisitante(), model.getFlagAguaIndividual(), model.getFlagGasIndividual(), model.getFlagGeradorProprio(), model.getPosicaoSolModel().getId(), model.getFlagElevador(), model.getQtdElevadores(), model.getFlagMobiliado(), model.getDescricaoMobilia(), model.getQtdVagasEstacionamento(), model.getFlagUtilizouFgts(), model.getFlagImovelQuitado(), model.getFlagTerrenoForeiro(), model.getFlagExclusividade(), model.getFlagHabitado(), model.getComissao(), model.getMotivoVenda(), model.getOutrosImoveisVenda(), model.getObservacoes(), model.getProprietarioModel().getId(), model.getCaptadorModel().getId(), model.getDataCaptacao(), model.getId());

		broker.execute();

		return model;
	}

	public ImovelModel excluir(final ImovelModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("DELETE FROM IMOVEL WHERE ID = ?", model.getId());

		broker.execute();

		return model;
	}

	@SuppressWarnings("unchecked")
	public List<ImovelModel> pesquisar(String dadosProprietario) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT I.ID, I.CODIGO, I.TIPO_IMOVEL_ID, (SELECT TI.DESCRICAO FROM TIPO_IMOVEL TI WHERE TI.ID = I.TIPO_IMOVEL_ID), I.CAPTADOR_ID, (SELECT C.NOME FROM USUARIO C WHERE C.ID = I.CAPTADOR_ID), I.BAIRRO_ID, (SELECT B.DESCRICAO FROM BAIRRO B WHERE B.ID = I.BAIRRO_ID), I.EDIFICIO_ID, (SELECT E.DESCRICAO FROM EDIFICIO E WHERE E.ID = I.EDIFICIO_ID), I.VALOR FROM IMOVEL I, PROPRIETARIO P WHERE P.ID = I.PROPRIETARIO_ID AND SEM_ACENTOS(BUSCA_PROPRIETARIO(P.ID)) ILIKE SEM_ACENTOS(COALESCE(?, SEM_ACENTOS(BUSCA_PROPRIETARIO(P.ID)))) ", Utilitario.getStringIlike(dadosProprietario, true));

		return broker.getCollectionBean(ImovelModel.class, "id", "codigo", "tipoImovelModel.id", "tipoImovelModel.descricao", "captadorModel.id", "captadorModel.nome", "bairroModel.id", "bairroModel.descricao", "edificioModel.id", "edificioModel.descricao", "valor");
	}

	public Boolean isExisteImovel(String campo) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT EXISTS(SELECT 1 FROM IMOVEL I, PROPRIETARIO P WHERE P.ID = I.PROPRIETARIO_ID AND SEM_ACENTOS(BUSCA_PROPRIETARIO(P.ID)) ILIKE SEM_ACENTOS(COALESCE(?, SEM_ACENTOS(BUSCA_PROPRIETARIO(P.ID))))) ", Utilitario.getStringIlike(campo, true));

		return (Boolean) broker.getObject();
	}

}
