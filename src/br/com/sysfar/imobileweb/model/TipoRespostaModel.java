package br.com.sysfar.imobileweb.model;

import java.io.Serializable;

import br.com.sysfar.imobileweb.util.Constantes;
import br.com.topsys.util.TSUtil;

@SuppressWarnings("serial")
public class TipoRespostaModel implements Serializable {

	private Long id;

	private String descricao;

	public TipoRespostaModel() {
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
	
	public boolean isNumerico(){
		return Constantes.TIPO_RESPOSTA_INTEIRO.equals(this.id);
	}
	
	public boolean isPontoFlutuante(){
		return Constantes.TIPO_RESPOSTA_PONTO_FLUTUANTE.equals(this.id);
	}
	
	public boolean isTexto(){
		return Constantes.TIPO_RESPOSTA_TEXTO.equals(this.id);
	}
	
	public boolean isInputText(){
		return Constantes.TIPO_RESPOSTA_INPUT_TEXT.equals(this.id);
	}
	
	public boolean isBooleano(){
		return Constantes.TIPO_RESPOSTA_BOOLEAN.equals(this.id);
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
		TipoRespostaModel other = (TipoRespostaModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
