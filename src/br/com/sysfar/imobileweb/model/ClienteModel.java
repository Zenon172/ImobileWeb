package br.com.sysfar.imobileweb.model;

import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class ClienteModel extends BaseModel {

	private String nome;

	private String email;
	
	private String telefones;
	
	private String observacoes;
	
	private OrigemModel origemModel;
	
	private StatusClienteModel statusAtualModel;
	
	private List<ClienteContatoModel> contatos;
	
	private List<ClienteStatusModel> status;
	
	private ClientePerfilModel clientePerfilModel;
	
	private List<ImovelModel> imoveisPerfil;

	private Date dataInicial;
	
	private Date dataUltimoContato;
	
	public ClienteModel() {
	}
	
	public ClienteModel(UsuarioModel usuarioResponsavel) {
		this.usuarioCadastroModel = usuarioResponsavel;
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

	public List<ClienteContatoModel> getContatos() {
		return contatos;
	}

	public void setContatos(List<ClienteContatoModel> contatos) {
		this.contatos = contatos;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public List<ClienteStatusModel> getStatus() {
		return status;
	}

	public void setStatus(List<ClienteStatusModel> status) {
		this.status = status;
	}

	public ClientePerfilModel getClientePerfilModel() {
		return clientePerfilModel;
	}

	public void setClientePerfilModel(ClientePerfilModel clientePerfilModel) {
		this.clientePerfilModel = clientePerfilModel;
	}

	public StatusClienteModel getStatusAtualModel() {
		return statusAtualModel;
	}

	public void setStatusAtualModel(StatusClienteModel statusAtualModel) {
		this.statusAtualModel = statusAtualModel;
	}

	public List<ImovelModel> getImoveisPerfil() {
		return imoveisPerfil;
	}

	public void setImoveisPerfil(List<ImovelModel> imoveisPerfil) {
		this.imoveisPerfil = imoveisPerfil;
	}

	public OrigemModel getOrigemModel() {
		return origemModel;
	}

	public void setOrigemModel(OrigemModel origemModel) {
		this.origemModel = origemModel;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataUltimoContato() {
		return dataUltimoContato;
	}

	public void setDataUltimoContato(Date dataUltimoContato) {
		this.dataUltimoContato = dataUltimoContato;
	}

	public String getTelefones() {
		return telefones;
	}

	public void setTelefones(String telefones) {
		this.telefones = telefones;
	}
}
