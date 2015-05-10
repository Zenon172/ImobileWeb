package br.com.sysfar.imobileweb.model;

import java.io.Serializable;

import br.com.topsys.util.TSUtil;

@SuppressWarnings("serial")
public class StatusClienteModel implements Serializable {

	private Long id;

	private String descricao;

	private Boolean flagAtivo;
	
	private Boolean flagFinalizado;
	
	private Boolean flagEnviarEmailDesatualizado;

	public StatusClienteModel(Boolean flagAtivo) {
		super();
		this.flagAtivo = flagAtivo;
	}

	public StatusClienteModel() {
		super();
	}

	public Long getId() {
		return TSUtil.tratarLong(id);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getFlagAtivo() {
		return flagAtivo;
	}

	public void setFlagAtivo(Boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

	public Boolean getFlagFinalizado() {
		return flagFinalizado;
	}

	public void setFlagFinalizado(Boolean flagFinalizado) {
		this.flagFinalizado = flagFinalizado;
	}

	public Boolean getFlagEnviarEmailDesatualizado() {
		return flagEnviarEmailDesatualizado;
	}

	public void setFlagEnviarEmailDesatualizado(Boolean flagEnviarEmailDesatualizado) {
		this.flagEnviarEmailDesatualizado = flagEnviarEmailDesatualizado;
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
		StatusClienteModel other = (StatusClienteModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
