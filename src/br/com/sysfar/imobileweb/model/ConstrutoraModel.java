package br.com.sysfar.imobileweb.model;

@SuppressWarnings("serial")
public class ConstrutoraModel extends BaseModel {

	private String descricao;
	
	private String email;

	private String telefone;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
