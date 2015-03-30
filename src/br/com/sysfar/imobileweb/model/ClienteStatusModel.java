package br.com.sysfar.imobileweb.model;

import java.util.Date;

@SuppressWarnings("serial")
public class ClienteStatusModel extends BaseModel {

	private ClienteModel clienteModel;
	
	private StatusClienteModel statusClienteModel;
	
	private Date data;
	
	private String observacao;

	public ClienteModel getClienteModel() {
		return clienteModel;
	}

	public void setClienteModel(ClienteModel clienteModel) {
		this.clienteModel = clienteModel;
	}

	public StatusClienteModel getStatusClienteModel() {
		return statusClienteModel;
	}

	public void setStatusClienteModel(StatusClienteModel statusClienteModel) {
		this.statusClienteModel = statusClienteModel;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((clienteModel == null) ? 0 : clienteModel.hashCode());
		result = prime * result + ((statusClienteModel == null) ? 0 : statusClienteModel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteStatusModel other = (ClienteStatusModel) obj;
		if (clienteModel == null) {
			if (other.clienteModel != null)
				return false;
		} else if (!clienteModel.equals(other.clienteModel))
			return false;
		if (statusClienteModel == null) {
			if (other.statusClienteModel != null)
				return false;
		} else if (!statusClienteModel.equals(other.statusClienteModel))
			return false;
		return true;
	}
}
