package br.com.sysfar.imobileweb.model;

import java.util.List;

@SuppressWarnings("serial")
public class CondominioModel extends BaseModel {

	private String descricao;
	
	private Integer qtdTorres;
	
	private BairroModel bairroModel;
	
	private List<EdificioModel> edificios;

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

	public List<EdificioModel> getEdificios() {
		return edificios;
	}

	public void setEdificios(List<EdificioModel> edificios) {
		this.edificios = edificios;
	}
}
