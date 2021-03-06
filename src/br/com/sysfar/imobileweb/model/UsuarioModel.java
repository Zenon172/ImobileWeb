package br.com.sysfar.imobileweb.model;

@SuppressWarnings("serial")
public class UsuarioModel extends BaseModel {

	private String codigo;
	
	private String nome;

	private String email;
	
	private String login;

	private String senha;

	private Boolean flagAdministrador;

	private GrupoModel grupoModel;
	
	private Long qtdImoveis;
	
	public UsuarioModel() {
	}
	
	public UsuarioModel(Long id) {
		this.id = id;
	}
	
	public UsuarioModel(Boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Long getQtdImoveis() {
		return qtdImoveis;
	}

	public void setQtdImoveis(Long qtdImoveis) {
		this.qtdImoveis = qtdImoveis;
	}
}
