package br.com.sysfar.imobileweb.model;

import java.io.Serializable;

import br.com.topsys.util.TSUtil;

@SuppressWarnings("serial")
public class ClientePerfilBairroModel implements Serializable {

	private Long id;

	private ClientePerfilModel clientePerfilModel;

	private BairroModel bairroModel;

	public Long getId() {
		return TSUtil.tratarLong(id);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ClientePerfilModel getClientePerfilModel() {
		return clientePerfilModel;
	}

	public void setClientePerfilModel(ClientePerfilModel clientePerfilModel) {
		this.clientePerfilModel = clientePerfilModel;
	}

	public BairroModel getBairroModel() {
		return bairroModel;
	}

	public void setBairroModel(BairroModel bairroModel) {
		this.bairroModel = bairroModel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairroModel == null) ? 0 : bairroModel.hashCode());
		result = prime * result + ((clientePerfilModel == null) ? 0 : clientePerfilModel.hashCode());
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
		ClientePerfilBairroModel other = (ClientePerfilBairroModel) obj;
		if (bairroModel == null) {
			if (other.bairroModel != null)
				return false;
		} else if (!bairroModel.equals(other.bairroModel))
			return false;
		if (clientePerfilModel == null) {
			if (other.clientePerfilModel != null)
				return false;
		} else if (!clientePerfilModel.equals(other.clientePerfilModel))
			return false;
		return true;
	}
}
