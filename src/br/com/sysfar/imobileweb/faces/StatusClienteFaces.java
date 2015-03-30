package br.com.sysfar.imobileweb.faces;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sysfar.imobileweb.dao.CrudDAO;
import br.com.sysfar.imobileweb.dao.StatusClienteDAO;
import br.com.sysfar.imobileweb.model.StatusClienteModel;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "statusClienteFaces")
public class StatusClienteFaces extends CrudFaces<StatusClienteModel> {

	private StatusClienteDAO statusClienteDAO;

	@Override
	@PostConstruct
	protected void clearFields() {

		this.crudModel = new StatusClienteModel();
		this.crudModel.setFlagAtivo(Boolean.TRUE);
		
		this.crudPesquisaModel = new StatusClienteModel();
		this.crudPesquisaModel.setFlagAtivo(Boolean.TRUE);

		this.statusClienteDAO = new StatusClienteDAO();

	}

	@Override
	protected CrudDAO<StatusClienteModel> getCrudDAO() {
		return this.statusClienteDAO;
	}

}
