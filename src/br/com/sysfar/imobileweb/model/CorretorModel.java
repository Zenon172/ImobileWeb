package br.com.sysfar.imobileweb.model;

import java.util.List;

@SuppressWarnings("serial")
public class CorretorModel extends BaseModel {

	private String nome;
	
	private List<CorretorContatoModel> contatos;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<CorretorContatoModel> getContatos() {
		return contatos;
	}

	public void setContatos(List<CorretorContatoModel> contatos) {
		this.contatos = contatos;
	}
	
}
