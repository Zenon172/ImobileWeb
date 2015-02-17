package br.com.sysfar.imobileweb.model;

import java.util.Date;

@SuppressWarnings("serial")
public class ImovelModel extends BaseModel {

	private TipoImovelModel tipoImovelModel;

	private Boolean flagPiscinaPrivativa;

	private Double valor;

	private String endereco;

	private String complemento;
	
	private EdificioModel edificioModel;

	private Integer andar;

	private String unidade;

	private String pontoReferencia;

	private BairroModel bairroModel;

	private String cep;

	private ConstrutoraModel construtoraModel;

	private TipoPisoModel tipoPisoSalaModel;
	
	private TipoPisoModel tipoPisoQuartoModel;

	private Integer anoConstrucao;

	private Double areaPrivativa;

	private TipoFachadaModel tipoFachadaModel;

	private String nomeAdministrador;

	private String telefoneAdministrador;

	private Double valorCondominio;

	private Integer quartos;

	private Integer suites;

	private Integer banheiros;

	private Integer varandas;

	private Integer dependencias;

	private Integer wcEmpregadas;

	private Integer armariosBanheiros;

	private Integer armariosCozinhas;

	private Integer armariosQuartos;

	private Integer closets;

	private Integer pavimentos;

	private Integer homeOffices;

	private Integer despensas;

	private Integer depositos;

	private Boolean flagCozinhaAmericana;

	private Boolean flagSalaoFestas;

	private Boolean flagPlayground;

	private Boolean flagPiscina;

	private Boolean flagAcademia;

	private Boolean flagQuadraPoliesportiva;

	private Boolean flagParqueInfantil;

	private Boolean flagCampo;

	private Boolean flagSalaoJogos;

	private Boolean flagSauna;

	private Boolean flagBrinquedoteca;

	private Boolean flagCinema;

	private Boolean flagQuadraSquash;

	private Boolean flagQuadraTenis;

	private Boolean flagChurrasqueira;

	private Boolean flagEstacionamentoVisitante;

	private Boolean flagAguaIndividual;

	private Boolean flagGasIndividual;

	private Boolean flagGeradorProprio;

	private PosicaoSolModel posicaoSolModel;

	private Boolean flagElevador;

	private Integer qtdElevadores;

	private Boolean flagMobiliado;

	private String descricaoMobilia;

	private Integer qtdVagasEstacionamento;

	private Boolean flagUtilizouFgts;

	private Boolean flagImovelQuitado;

	private Boolean flagTerrenoForeiro;

	private Boolean flagExclusividade;

	private Boolean flagHabitado;

	private Double comissao;

	private String motivoVenda;

	private String outrosImoveisVenda;

	private String observacoes;

	private CaptacaoModel captacaoModel;

	private UsuarioModel captadorModel;

	private Date dataCaptacao;

	private ProprietarioModel proprietarioModel;

	public TipoImovelModel getTipoImovelModel() {
		return tipoImovelModel;
	}

	public void setTipoImovelModel(TipoImovelModel tipoImovelModel) {
		this.tipoImovelModel = tipoImovelModel;
	}

	public Boolean getFlagPiscinaPrivativa() {
		return flagPiscinaPrivativa;
	}

	public void setFlagPiscinaPrivativa(Boolean flagPiscinaPrivativa) {
		this.flagPiscinaPrivativa = flagPiscinaPrivativa;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Integer getAndar() {
		return andar;
	}

	public void setAndar(Integer andar) {
		this.andar = andar;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getPontoReferencia() {
		return pontoReferencia;
	}

	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}

	public BairroModel getBairroModel() {
		return bairroModel;
	}

	public void setBairroModel(BairroModel bairroModel) {
		this.bairroModel = bairroModel;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public ConstrutoraModel getConstrutoraModel() {
		return construtoraModel;
	}

	public void setConstrutoraModel(ConstrutoraModel construtoraModel) {
		this.construtoraModel = construtoraModel;
	}

	public TipoPisoModel getTipoPisoSalaModel() {
		return tipoPisoSalaModel;
	}

	public void setTipoPisoSalaModel(TipoPisoModel tipoPisoSalaModel) {
		this.tipoPisoSalaModel = tipoPisoSalaModel;
	}

	public TipoPisoModel getTipoPisoQuartoModel() {
		return tipoPisoQuartoModel;
	}

	public void setTipoPisoQuartoModel(TipoPisoModel tipoPisoQuartoModel) {
		this.tipoPisoQuartoModel = tipoPisoQuartoModel;
	}

	public Integer getAnoConstrucao() {
		return anoConstrucao;
	}

	public void setAnoConstrucao(Integer anoConstrucao) {
		this.anoConstrucao = anoConstrucao;
	}

	public Double getAreaPrivativa() {
		return areaPrivativa;
	}

	public void setAreaPrivativa(Double areaPrivativa) {
		this.areaPrivativa = areaPrivativa;
	}

	public TipoFachadaModel getTipoFachadaModel() {
		return tipoFachadaModel;
	}

	public void setTipoFachadaModel(TipoFachadaModel tipoFachadaModel) {
		this.tipoFachadaModel = tipoFachadaModel;
	}

	public String getNomeAdministrador() {
		return nomeAdministrador;
	}

	public void setNomeAdministrador(String nomeAdministrador) {
		this.nomeAdministrador = nomeAdministrador;
	}

	public String getTelefoneAdministrador() {
		return telefoneAdministrador;
	}

	public void setTelefoneAdministrador(String telefoneAdministrador) {
		this.telefoneAdministrador = telefoneAdministrador;
	}

	public Double getValorCondominio() {
		return valorCondominio;
	}

	public void setValorCondominio(Double valorCondominio) {
		this.valorCondominio = valorCondominio;
	}

	public Integer getQuartos() {
		return quartos;
	}

	public void setQuartos(Integer quartos) {
		this.quartos = quartos;
	}

	public Integer getSuites() {
		return suites;
	}

	public void setSuites(Integer suites) {
		this.suites = suites;
	}

	public Integer getBanheiros() {
		return banheiros;
	}

	public void setBanheiros(Integer banheiros) {
		this.banheiros = banheiros;
	}

	public Integer getVarandas() {
		return varandas;
	}

	public void setVarandas(Integer varandas) {
		this.varandas = varandas;
	}

	public Integer getDependencias() {
		return dependencias;
	}

	public void setDependencias(Integer dependencias) {
		this.dependencias = dependencias;
	}

	public Integer getWcEmpregadas() {
		return wcEmpregadas;
	}

	public void setWcEmpregadas(Integer wcEmpregadas) {
		this.wcEmpregadas = wcEmpregadas;
	}

	public Integer getArmariosBanheiros() {
		return armariosBanheiros;
	}

	public void setArmariosBanheiros(Integer armariosBanheiros) {
		this.armariosBanheiros = armariosBanheiros;
	}

	public Integer getArmariosCozinhas() {
		return armariosCozinhas;
	}

	public void setArmariosCozinhas(Integer armariosCozinhas) {
		this.armariosCozinhas = armariosCozinhas;
	}

	public Integer getArmariosQuartos() {
		return armariosQuartos;
	}

	public void setArmariosQuartos(Integer armariosQuartos) {
		this.armariosQuartos = armariosQuartos;
	}

	public Integer getClosets() {
		return closets;
	}

	public void setClosets(Integer closets) {
		this.closets = closets;
	}

	public Integer getPavimentos() {
		return pavimentos;
	}

	public void setPavimentos(Integer pavimentos) {
		this.pavimentos = pavimentos;
	}

	public Integer getHomeOffices() {
		return homeOffices;
	}

	public void setHomeOffices(Integer homeOffices) {
		this.homeOffices = homeOffices;
	}

	public Integer getDespensas() {
		return despensas;
	}

	public void setDespensas(Integer despensas) {
		this.despensas = despensas;
	}

	public Integer getDepositos() {
		return depositos;
	}

	public void setDepositos(Integer depositos) {
		this.depositos = depositos;
	}

	public Boolean getFlagCozinhaAmericana() {
		return flagCozinhaAmericana;
	}

	public void setFlagCozinhaAmericana(Boolean flagCozinhaAmericana) {
		this.flagCozinhaAmericana = flagCozinhaAmericana;
	}

	public Boolean getFlagSalaoFestas() {
		return flagSalaoFestas;
	}

	public void setFlagSalaoFestas(Boolean flagSalaoFestas) {
		this.flagSalaoFestas = flagSalaoFestas;
	}

	public Boolean getFlagPlayground() {
		return flagPlayground;
	}

	public void setFlagPlayground(Boolean flagPlayground) {
		this.flagPlayground = flagPlayground;
	}

	public Boolean getFlagPiscina() {
		return flagPiscina;
	}

	public void setFlagPiscina(Boolean flagPiscina) {
		this.flagPiscina = flagPiscina;
	}

	public Boolean getFlagAcademia() {
		return flagAcademia;
	}

	public void setFlagAcademia(Boolean flagAcademia) {
		this.flagAcademia = flagAcademia;
	}

	public Boolean getFlagQuadraPoliesportiva() {
		return flagQuadraPoliesportiva;
	}

	public void setFlagQuadraPoliesportiva(Boolean flagQuadraPoliesportiva) {
		this.flagQuadraPoliesportiva = flagQuadraPoliesportiva;
	}

	public Boolean getFlagParqueInfantil() {
		return flagParqueInfantil;
	}

	public void setFlagParqueInfantil(Boolean flagParqueInfantil) {
		this.flagParqueInfantil = flagParqueInfantil;
	}

	public Boolean getFlagCampo() {
		return flagCampo;
	}

	public void setFlagCampo(Boolean flagCampo) {
		this.flagCampo = flagCampo;
	}

	public Boolean getFlagSalaoJogos() {
		return flagSalaoJogos;
	}

	public void setFlagSalaoJogos(Boolean flagSalaoJogos) {
		this.flagSalaoJogos = flagSalaoJogos;
	}

	public Boolean getFlagSauna() {
		return flagSauna;
	}

	public void setFlagSauna(Boolean flagSauna) {
		this.flagSauna = flagSauna;
	}

	public Boolean getFlagBrinquedoteca() {
		return flagBrinquedoteca;
	}

	public void setFlagBrinquedoteca(Boolean flagBrinquedoteca) {
		this.flagBrinquedoteca = flagBrinquedoteca;
	}

	public Boolean getFlagCinema() {
		return flagCinema;
	}

	public void setFlagCinema(Boolean flagCinema) {
		this.flagCinema = flagCinema;
	}

	public Boolean getFlagQuadraSquash() {
		return flagQuadraSquash;
	}

	public void setFlagQuadraSquash(Boolean flagQuadraSquash) {
		this.flagQuadraSquash = flagQuadraSquash;
	}

	public Boolean getFlagQuadraTenis() {
		return flagQuadraTenis;
	}

	public void setFlagQuadraTenis(Boolean flagQuadraTenis) {
		this.flagQuadraTenis = flagQuadraTenis;
	}

	public Boolean getFlagChurrasqueira() {
		return flagChurrasqueira;
	}

	public void setFlagChurrasqueira(Boolean flagChurrasqueira) {
		this.flagChurrasqueira = flagChurrasqueira;
	}

	public Boolean getFlagEstacionamentoVisitante() {
		return flagEstacionamentoVisitante;
	}

	public void setFlagEstacionamentoVisitante(Boolean flagEstacionamentoVisitante) {
		this.flagEstacionamentoVisitante = flagEstacionamentoVisitante;
	}

	public Boolean getFlagAguaIndividual() {
		return flagAguaIndividual;
	}

	public void setFlagAguaIndividual(Boolean flagAguaIndividual) {
		this.flagAguaIndividual = flagAguaIndividual;
	}

	public Boolean getFlagGasIndividual() {
		return flagGasIndividual;
	}

	public void setFlagGasIndividual(Boolean flagGasIndividual) {
		this.flagGasIndividual = flagGasIndividual;
	}

	public Boolean getFlagGeradorProprio() {
		return flagGeradorProprio;
	}

	public void setFlagGeradorProprio(Boolean flagGeradorProprio) {
		this.flagGeradorProprio = flagGeradorProprio;
	}

	public PosicaoSolModel getPosicaoSolModel() {
		return posicaoSolModel;
	}

	public void setPosicaoSolModel(PosicaoSolModel posicaoSolModel) {
		this.posicaoSolModel = posicaoSolModel;
	}

	public Boolean getFlagElevador() {
		return flagElevador;
	}

	public void setFlagElevador(Boolean flagElevador) {
		this.flagElevador = flagElevador;
	}

	public Integer getQtdElevadores() {
		return qtdElevadores;
	}

	public void setQtdElevadores(Integer qtdElevadores) {
		this.qtdElevadores = qtdElevadores;
	}

	public Boolean getFlagMobiliado() {
		return flagMobiliado;
	}

	public void setFlagMobiliado(Boolean flagMobiliado) {
		this.flagMobiliado = flagMobiliado;
	}

	public String getDescricaoMobilia() {
		return descricaoMobilia;
	}

	public void setDescricaoMobilia(String descricaoMobilia) {
		this.descricaoMobilia = descricaoMobilia;
	}

	public Integer getQtdVagasEstacionamento() {
		return qtdVagasEstacionamento;
	}

	public void setQtdVagasEstacionamento(Integer qtdVagasEstacionamento) {
		this.qtdVagasEstacionamento = qtdVagasEstacionamento;
	}

	public Boolean getFlagUtilizouFgts() {
		return flagUtilizouFgts;
	}

	public void setFlagUtilizouFgts(Boolean flagUtilizouFgts) {
		this.flagUtilizouFgts = flagUtilizouFgts;
	}

	public Boolean getFlagImovelQuitado() {
		return flagImovelQuitado;
	}

	public void setFlagImovelQuitado(Boolean flagImovelQuitado) {
		this.flagImovelQuitado = flagImovelQuitado;
	}

	public Boolean getFlagTerrenoForeiro() {
		return flagTerrenoForeiro;
	}

	public void setFlagTerrenoForeiro(Boolean flagTerrenoForeiro) {
		this.flagTerrenoForeiro = flagTerrenoForeiro;
	}

	public Boolean getFlagExclusividade() {
		return flagExclusividade;
	}

	public void setFlagExclusividade(Boolean flagExclusividade) {
		this.flagExclusividade = flagExclusividade;
	}

	public Boolean getFlagHabitado() {
		return flagHabitado;
	}

	public void setFlagHabitado(Boolean flagHabitado) {
		this.flagHabitado = flagHabitado;
	}

	public Double getComissao() {
		return comissao;
	}

	public void setComissao(Double comissao) {
		this.comissao = comissao;
	}

	public String getMotivoVenda() {
		return motivoVenda;
	}

	public void setMotivoVenda(String motivoVenda) {
		this.motivoVenda = motivoVenda;
	}

	public String getOutrosImoveisVenda() {
		return outrosImoveisVenda;
	}

	public void setOutrosImoveisVenda(String outrosImoveisVenda) {
		this.outrosImoveisVenda = outrosImoveisVenda;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public CaptacaoModel getCaptacaoModel() {
		return captacaoModel;
	}

	public void setCaptacaoModel(CaptacaoModel captacaoModel) {
		this.captacaoModel = captacaoModel;
	}

	public UsuarioModel getCaptadorModel() {
		return captadorModel;
	}

	public void setCaptadorModel(UsuarioModel captadorModel) {
		this.captadorModel = captadorModel;
	}

	public Date getDataCaptacao() {
		return dataCaptacao;
	}

	public void setDataCaptacao(Date dataCaptacao) {
		this.dataCaptacao = dataCaptacao;
	}

	public ProprietarioModel getProprietarioModel() {
		return proprietarioModel;
	}

	public void setProprietarioModel(ProprietarioModel proprietarioModel) {
		this.proprietarioModel = proprietarioModel;
	}

	public EdificioModel getEdificioModel() {
		return edificioModel;
	}

	public void setEdificioModel(EdificioModel edificioModel) {
		this.edificioModel = edificioModel;
	}
}
