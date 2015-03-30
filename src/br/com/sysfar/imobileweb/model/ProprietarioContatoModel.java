package br.com.sysfar.imobileweb.model;


@SuppressWarnings("serial")
public class ProprietarioContatoModel extends ContatoModel {

	private ProprietarioModel proprietarioModel;

	private Boolean flagResponsavelVenda;

	public ProprietarioModel getProprietarioModel() {
		return proprietarioModel;
	}

	public void setProprietarioModel(ProprietarioModel proprietarioModel) {
		this.proprietarioModel = proprietarioModel;
	}

	public Boolean getFlagResponsavelVenda() {
		return flagResponsavelVenda;
	}

	public void setFlagResponsavelVenda(Boolean flagResponsavelVenda) {
		this.flagResponsavelVenda = flagResponsavelVenda;
	}

}
