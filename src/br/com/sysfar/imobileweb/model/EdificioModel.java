package br.com.sysfar.imobileweb.model;

@SuppressWarnings("serial")
public class EdificioModel extends BaseModel {

	private String descricao;
	
	private CondominioModel condominioModel;
	
	private Integer qtdPavimentos;
	
	private Integer qtdApartamentosAndar;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public CondominioModel getCondominioModel() {
		return condominioModel;
	}

	public void setCondominioModel(CondominioModel condominioModel) {
		this.condominioModel = condominioModel;
	}

	public Integer getQtdPavimentos() {
		return qtdPavimentos;
	}

	public void setQtdPavimentos(Integer qtdPavimentos) {
		this.qtdPavimentos = qtdPavimentos;
	}

	public Integer getQtdApartamentosAndar() {
		return qtdApartamentosAndar;
	}

	public void setQtdApartamentosAndar(Integer qtdApartamentosAndar) {
		this.qtdApartamentosAndar = qtdApartamentosAndar;
	}
}
