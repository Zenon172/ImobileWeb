package br.com.sysfar.imobileweb.model;

import java.io.Serializable;

import br.com.sysfar.imobileweb.util.Constantes;
import br.com.topsys.util.TSUtil;

@SuppressWarnings("serial")
public class ImovelFotoModel implements Serializable {

	private Long id;
	
	private ImovelModel imovelModel;
	
	private String arquivo;
	
	private Boolean flagAtivo;

	public Long getId() {
		return TSUtil.tratarLong(id);
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

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public Boolean getFlagAtivo() {
		return flagAtivo;
	}

	public void setFlagAtivo(Boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}
	public String getArquivoView(){
		return Constantes.PASTA_DOWNLOAD_ARQUIVO + this.arquivo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arquivo == null) ? 0 : arquivo.hashCode());
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
		ImovelFotoModel other = (ImovelFotoModel) obj;
		if (arquivo == null) {
			if (other.arquivo != null)
				return false;
		} else if (!arquivo.equals(other.arquivo))
			return false;
		return true;
	}

}
