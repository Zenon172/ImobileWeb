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

	private Boolean flagPortrait;

	private Integer ordem;

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

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	public Boolean getFlagPortrait() {
		return flagPortrait;
	}

	public void setFlagPortrait(Boolean flagPortrait) {
		this.flagPortrait = flagPortrait;
	}

	public String getArquivoView() {
		return Constantes.PASTA_DOWNLOAD_ARQUIVO + this.arquivo + "." + Constantes.EXTENSAO_FOTOS;
	}
	
	public String getArquivoViewSite() {
		return Constantes.PASTA_DOWNLOAD_ARQUIVO_SITE + this.arquivo + "." + Constantes.EXTENSAO_FOTOS;
	}

	public String getArquivoMini() {
		return !TSUtil.isEmpty(this.flagPortrait) && this.flagPortrait ? this.getArquivo60x80() : this.getArquivo80x60();
	}
	
	public String getArquivoMiniSite() {
		return !TSUtil.isEmpty(this.flagPortrait) && this.flagPortrait ? this.getArquivo60x80Site() : this.getArquivo80x60Site();
	}
	
	public String getArquivoMedio() {
		return !TSUtil.isEmpty(this.flagPortrait) && this.flagPortrait ? this.getArquivo150x200() : this.getArquivo200x150();
	}
	
	public String getArquivoMedioSite() {
		return !TSUtil.isEmpty(this.flagPortrait) && this.flagPortrait ? this.getArquivo150x200Site() : this.getArquivo200x150Site();
	}
	
	public String getArquivo80x60() {
		return Constantes.PASTA_DOWNLOAD_ARQUIVO + this.arquivo + "_80x60." + Constantes.EXTENSAO_FOTOS;
	}
	
	public String getArquivo80x60Site() {
		return Constantes.PASTA_DOWNLOAD_ARQUIVO_SITE + this.arquivo + "_80x60." + Constantes.EXTENSAO_FOTOS;
	}

	public String getArquivo60x80() {
		return Constantes.PASTA_DOWNLOAD_ARQUIVO + this.arquivo + "_60x80." + Constantes.EXTENSAO_FOTOS;
	}
	
	public String getArquivo60x80Site() {
		return Constantes.PASTA_DOWNLOAD_ARQUIVO_SITE + this.arquivo + "_60x80." + Constantes.EXTENSAO_FOTOS;
	}

	public String getArquivo200x150() {
		return Constantes.PASTA_DOWNLOAD_ARQUIVO + this.arquivo + "_200x150." + Constantes.EXTENSAO_FOTOS;
	}
	
	public String getArquivo200x150Site() {
		return Constantes.PASTA_DOWNLOAD_ARQUIVO_SITE + this.arquivo + "_200x150." + Constantes.EXTENSAO_FOTOS;
	}

	public String getArquivo150x200() {
		return Constantes.PASTA_DOWNLOAD_ARQUIVO + this.arquivo + "_150x200." + Constantes.EXTENSAO_FOTOS;
	}
	
	public String getArquivo150x200Site() {
		return Constantes.PASTA_DOWNLOAD_ARQUIVO_SITE + this.arquivo + "_150x200." + Constantes.EXTENSAO_FOTOS;
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
