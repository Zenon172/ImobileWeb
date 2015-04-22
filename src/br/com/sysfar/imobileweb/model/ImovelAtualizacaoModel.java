package br.com.sysfar.imobileweb.model;

import java.util.Date;

@SuppressWarnings("serial")
public class ImovelAtualizacaoModel extends BaseModel {

	private ImovelModel imovelModel;
	
	private TipoAtualizacaoImovelModel tipoAtualizacaoImovelModel;
	
	private Date data;
	
	private String observacao;

	public ImovelModel getImovelModel() {
		return imovelModel;
	}

	public void setImovelModel(ImovelModel imovelModel) {
		this.imovelModel = imovelModel;
	}

	public TipoAtualizacaoImovelModel getTipoAtualizacaoImovelModel() {
		return tipoAtualizacaoImovelModel;
	}

	public void setTipoAtualizacaoImovelModel(TipoAtualizacaoImovelModel tipoAtualizacaoImovelModel) {
		this.tipoAtualizacaoImovelModel = tipoAtualizacaoImovelModel;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
