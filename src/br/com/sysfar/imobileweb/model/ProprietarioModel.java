package br.com.sysfar.imobileweb.model;

import java.util.List;

@SuppressWarnings("serial")
public class ProprietarioModel extends BaseModel {

	private String nome;
	
	private String telefone;
	
	private String celular;
	
	private String email;
	
	private String observacao;
	
	private String responsavelVenda;
	
	private String telefoneResponsavelVenda;
	
	private String celularResponsavelVenda;
	
	private String emailResponsavelVenda;
	
	private List<ImovelModel> imoveis;
	
	private List<ProprietarioContatoModel> contatos;
	private List<ProprietarioContatoModel> contatosResponsavelVenda;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getResponsavelVenda() {
		return responsavelVenda;
	}

	public void setResponsavelVenda(String responsavelVenda) {
		this.responsavelVenda = responsavelVenda;
	}

	public String getTelefoneResponsavelVenda() {
		return telefoneResponsavelVenda;
	}

	public void setTelefoneResponsavelVenda(String telefoneResponsavelVenda) {
		this.telefoneResponsavelVenda = telefoneResponsavelVenda;
	}

	public String getCelularResponsavelVenda() {
		return celularResponsavelVenda;
	}

	public void setCelularResponsavelVenda(String celularResponsavelVenda) {
		this.celularResponsavelVenda = celularResponsavelVenda;
	}

	public String getEmailResponsavelVenda() {
		return emailResponsavelVenda;
	}

	public void setEmailResponsavelVenda(String emailResponsavelVenda) {
		this.emailResponsavelVenda = emailResponsavelVenda;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<ImovelModel> getImoveis() {
		return imoveis;
	}

	public void setImoveis(List<ImovelModel> imoveis) {
		this.imoveis = imoveis;
	}

	public List<ProprietarioContatoModel> getContatos() {
		return contatos;
	}

	public void setContatos(List<ProprietarioContatoModel> contatos) {
		this.contatos = contatos;
	}

	public List<ProprietarioContatoModel> getContatosResponsavelVenda() {
		return contatosResponsavelVenda;
	}

	public void setContatosResponsavelVenda(List<ProprietarioContatoModel> contatosResponsavelVenda) {
		this.contatosResponsavelVenda = contatosResponsavelVenda;
	}
}
