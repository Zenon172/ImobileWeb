package br.com.sysfar.imobileweb.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class ContatoModel implements Serializable {

	private Long id;
	
	private ImovelModel imovelModel;
	
	private String nome;
	
	private String telefone;
	
	private String email;
	
	private String mensagem;
	
	private Boolean flagRetornoWhatsapp;
	
	private Boolean flagRetornoTelefone;
	
	private Date dataCadastro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ImovelModel getImovelModel() {
		return imovelModel;
	}

	public void setImovelModel(ImovelModel imovelModel) {
		this.imovelModel = imovelModel;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Boolean getFlagRetornoWhatsapp() {
		return flagRetornoWhatsapp;
	}

	public void setFlagRetornoWhatsapp(Boolean flagRetornoWhatsapp) {
		this.flagRetornoWhatsapp = flagRetornoWhatsapp;
	}

	public Boolean getFlagRetornoTelefone() {
		return flagRetornoTelefone;
	}

	public void setFlagRetornoTelefone(Boolean flagRetornoTelefone) {
		this.flagRetornoTelefone = flagRetornoTelefone;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContatoModel other = (ContatoModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
