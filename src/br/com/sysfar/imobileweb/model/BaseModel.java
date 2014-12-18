package br.com.sysfar.imobileweb.model;

import java.io.Serializable;
import java.util.Date;

import br.com.topsys.util.TSParseUtil;
import br.com.topsys.util.TSUtil;

@SuppressWarnings("serial")
public class BaseModel implements Serializable {

	protected Long id;
	protected UsuarioModel usuarioCadastroModel;
	protected UsuarioModel usuarioAtualizacaoModel;
	protected Date dataCadastro;
	protected Date dataAtualizacao;
	protected Boolean flagAtivo;

	public Long getId() {
		return TSUtil.tratarLong(id);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UsuarioModel getUsuarioCadastroModel() {
		return usuarioCadastroModel;
	}

	public void setUsuarioCadastroModel(UsuarioModel usuarioCadastroModel) {
		this.usuarioCadastroModel = usuarioCadastroModel;
	}

	public Boolean getFlagAtivo() {
		return flagAtivo;
	}

	public void setFlagAtivo(Boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

	public String getSituacao() {
		return !TSUtil.isEmpty(flagAtivo) && flagAtivo.equals(Boolean.TRUE) ? "Ativo" : "Inativo";
	}

	public String getToolTip() {
		
		StringBuilder desc = new StringBuilder();

		if (!TSUtil.isEmpty(this.id)) {
			desc.append("ID: ").append(id);
		}

		if (!TSUtil.isEmpty(this.usuarioCadastroModel) && !TSUtil.isEmpty(this.usuarioCadastroModel.getNome()) && !TSUtil.isEmpty(this.dataCadastro)) {
			desc.append("<br/> Cadastrado por: ").append(this.usuarioCadastroModel.getNome()).append(" em ").append(TSParseUtil.dateToString(this.dataCadastro, "dd/MM/yyyy HH:mm (Z)"));
		}

		if (!TSUtil.isEmpty(this.usuarioAtualizacaoModel) && !TSUtil.isEmpty(this.usuarioAtualizacaoModel.getNome()) && !TSUtil.isEmpty(this.dataAtualizacao)) {
			desc.append("<br/> Atualizado por:  &nbsp;").append(this.usuarioAtualizacaoModel.getNome()).append(" em ").append(TSParseUtil.dateToString(this.dataAtualizacao, "dd/MM/yyyy HH:mm (Z)"));
		}
		
		desc.append(TSUtil.isEmpty(desc) ? "" : "<br/>").append("Situação: ").append(getSituacao());

		return desc.toString();
	}

	public UsuarioModel getUsuarioAtualizacaoModel() {
		return usuarioAtualizacaoModel;
	}

	public void setUsuarioAtualizacaoModel(UsuarioModel usuarioAtualizacaoModel) {
		this.usuarioAtualizacaoModel = usuarioAtualizacaoModel;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
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
		BaseModel other = (BaseModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
