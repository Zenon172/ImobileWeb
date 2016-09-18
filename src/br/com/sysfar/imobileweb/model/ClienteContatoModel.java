package br.com.sysfar.imobileweb.model;

@SuppressWarnings("serial")
public class ClienteContatoModel extends ContatoGenericoModel {

	private ClienteModel clienteModel;

	public ClienteModel getClienteModel() {
		return clienteModel;
	}

	public void setClienteModel(ClienteModel clienteModel) {
		this.clienteModel = clienteModel;
	}

}
