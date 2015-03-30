package br.com.sysfar.imobileweb.model;

import java.io.Serializable;
import java.util.List;

import br.com.topsys.util.TSUtil;

@SuppressWarnings("serial")
public class ClientePerfilModel implements Serializable {

	private Long id;
	
	private ClienteModel clienteModel;
	
	private List<ClientePerfilBairroModel> bairros;
	
	private Integer qtdQuartosMin;
	
	private Integer qtdQuartosMax;
	
	private Integer metragemMin;
	
	private Integer metragemMax;
	
	private Double valorMin;
	
	private Double valorMax;
	
	private Integer suiteMin;
	
	private Integer suiteMax;
	
	private Integer garagensMin;
	
	private Integer garagensMax;
	
	private Boolean flagInfraestrutura;
	
	private TipoImovelModel tipoImovelModel;

	public Long getId() {
		return TSUtil.tratarLong(id);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ClienteModel getClienteModel() {
		return clienteModel;
	}

	public void setClienteModel(ClienteModel clienteModel) {
		this.clienteModel = clienteModel;
	}

	public Integer getQtdQuartosMin() {
		return qtdQuartosMin;
	}

	public void setQtdQuartosMin(Integer qtdQuartosMin) {
		this.qtdQuartosMin = qtdQuartosMin;
	}

	public Integer getQtdQuartosMax() {
		return qtdQuartosMax;
	}

	public void setQtdQuartosMax(Integer qtdQuartosMax) {
		this.qtdQuartosMax = qtdQuartosMax;
	}

	public Integer getMetragemMin() {
		return metragemMin;
	}

	public void setMetragemMin(Integer metragemMin) {
		this.metragemMin = metragemMin;
	}

	public Integer getMetragemMax() {
		return metragemMax;
	}

	public void setMetragemMax(Integer metragemMax) {
		this.metragemMax = metragemMax;
	}

	public Double getValorMin() {
		return valorMin;
	}

	public void setValorMin(Double valorMin) {
		this.valorMin = valorMin;
	}

	public Double getValorMax() {
		return valorMax;
	}

	public void setValorMax(Double valorMax) {
		this.valorMax = valorMax;
	}

	public Integer getSuiteMin() {
		return suiteMin;
	}

	public void setSuiteMin(Integer suiteMin) {
		this.suiteMin = suiteMin;
	}

	public Integer getSuiteMax() {
		return suiteMax;
	}

	public void setSuiteMax(Integer suiteMax) {
		this.suiteMax = suiteMax;
	}

	public Integer getGaragensMin() {
		return garagensMin;
	}

	public void setGaragensMin(Integer garagensMin) {
		this.garagensMin = garagensMin;
	}

	public Integer getGaragensMax() {
		return garagensMax;
	}

	public void setGaragensMax(Integer garagensMax) {
		this.garagensMax = garagensMax;
	}

	public Boolean getFlagInfraestrutura() {
		return flagInfraestrutura;
	}

	public void setFlagInfraestrutura(Boolean flagInfraestrutura) {
		this.flagInfraestrutura = flagInfraestrutura;
	}

	public TipoImovelModel getTipoImovelModel() {
		return tipoImovelModel;
	}

	public void setTipoImovelModel(TipoImovelModel tipoImovelModel) {
		this.tipoImovelModel = tipoImovelModel;
	}

	public List<ClientePerfilBairroModel> getBairros() {
		return bairros;
	}

	public void setBairros(List<ClientePerfilBairroModel> bairros) {
		this.bairros = bairros;
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
		ClientePerfilModel other = (ClientePerfilModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
