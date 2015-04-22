package br.com.sysfar.imobileweb.model;

@SuppressWarnings("serial")
public class ConfiguracaoModel extends BaseModel {

	private String descricao;
	
	private TipoRespostaModel tipoRespostaModel;

	private String valor;
	
	private Long respostaEscolhidaLong;
	private Double respostaEscolhidaDouble;
	private String respostaEscolhida;
	private Boolean respostaEscolhidaBoolean;

	public ConfiguracaoModel() {
		super();
	}

	public ConfiguracaoModel(Long id) {
		super();
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoRespostaModel getTipoRespostaModel() {
		return tipoRespostaModel;
	}

	public void setTipoRespostaModel(TipoRespostaModel tipoRespostaModel) {
		this.tipoRespostaModel = tipoRespostaModel;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Long getRespostaEscolhidaLong() {
		return respostaEscolhidaLong;
	}

	public void setRespostaEscolhidaLong(Long respostaEscolhidaLong) {
		this.respostaEscolhidaLong = respostaEscolhidaLong;
	}

	public Double getRespostaEscolhidaDouble() {
		return respostaEscolhidaDouble;
	}

	public void setRespostaEscolhidaDouble(Double respostaEscolhidaDouble) {
		this.respostaEscolhidaDouble = respostaEscolhidaDouble;
	}

	public String getRespostaEscolhida() {
		return respostaEscolhida;
	}

	public void setRespostaEscolhida(String respostaEscolhida) {
		this.respostaEscolhida = respostaEscolhida;
	}

	public Boolean getRespostaEscolhidaBoolean() {
		return respostaEscolhidaBoolean;
	}

	public void setRespostaEscolhidaBoolean(Boolean respostaEscolhidaBoolean) {
		this.respostaEscolhidaBoolean = respostaEscolhidaBoolean;
	}
}
