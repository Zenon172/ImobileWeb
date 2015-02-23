package br.com.sysfar.imobileweb.model;

import java.util.Date;

@SuppressWarnings("serial")
public class AtividadeModel extends BaseModel {

	private UsuarioModel responsavelModel;
	
	private StatusAtividadeModel statusAtividadeModel;
	
	private String observacao;
	
	private String retorno;
	
	private Date dataInicial;
	
	private Date dataFinal;
	
	private CaptacaoModel captacaoModel;
	
	private ImovelModel imovelModel;
	
	public UsuarioModel getResponsavelModel() {
		return responsavelModel;
	}

	public void setResponsavelModel(UsuarioModel responsavelModel) {
		this.responsavelModel = responsavelModel;
	}

	public StatusAtividadeModel getStatusAtividadeModel() {
		return statusAtividadeModel;
	}

	public void setStatusAtividadeModel(StatusAtividadeModel statusAtividadeModel) {
		this.statusAtividadeModel = statusAtividadeModel;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getRetorno() {
		return retorno;
	}

	public void setRetorno(String retorno) {
		this.retorno = retorno;
	}

	public CaptacaoModel getCaptacaoModel() {
		return captacaoModel;
	}

	public void setCaptacaoModel(CaptacaoModel captacaoModel) {
		this.captacaoModel = captacaoModel;
	}

	public ImovelModel getImovelModel() {
		return imovelModel;
	}

	public void setImovelModel(ImovelModel imovelModel) {
		this.imovelModel = imovelModel;
	}
}
