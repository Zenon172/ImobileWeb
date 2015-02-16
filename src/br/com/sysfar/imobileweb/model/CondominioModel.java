package br.com.sysfar.imobileweb.model;

@SuppressWarnings("serial")
public class CondominioModel extends BaseModel {

	private String descricao;
	
	private Integer qtdTorres;
	
	private BairroModel bairroModel;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQtdTorres() {
		return qtdTorres;
	}

	public void setQtdTorres(Integer qtdTorres) {
		this.qtdTorres = qtdTorres;
	}

	public BairroModel getBairroModel() {
		return bairroModel;
	}

	public void setBairroModel(BairroModel bairroModel) {
		this.bairroModel = bairroModel;
	}
}
