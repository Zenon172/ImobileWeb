package br.com.sysfar.imobileweb.model;

@SuppressWarnings("serial")
public class UsuarioModel extends BaseModel {

	private String nome;

	private String login;

	private String senha;

	private Boolean flagAdministrador;

	private GrupoModel grupoModel;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getFlagAdministrador() {
		return flagAdministrador;
	}

	public void setFlagAdministrador(Boolean flagAdministrador) {
		this.flagAdministrador = flagAdministrador;
	}

	public GrupoModel getGrupoModel() {
		return grupoModel;
	}

	public void setGrupoModel(GrupoModel grupoModel) {
		this.grupoModel = grupoModel;
	}
}
