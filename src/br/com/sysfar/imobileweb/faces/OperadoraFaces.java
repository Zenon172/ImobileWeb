package br.com.sysfar.imobileweb.faces;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sysfar.imobileweb.dao.CrudDAO;
import br.com.sysfar.imobileweb.dao.OperadoraDAO;
import br.com.sysfar.imobileweb.model.OperadoraModel;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "operadoraFaces")
public class OperadoraFaces extends CrudFaces<OperadoraModel> {

	private OperadoraDAO operadoraDAO;

	@Override
	@PostConstruct
	protected void clearFields() {

		this.crudModel = new OperadoraModel();
		this.crudModel.setFlagAtivo(Boolean.TRUE);

		this.crudPesquisaModel = new OperadoraModel();
		this.crudPesquisaModel.setFlagAtivo(Boolean.TRUE);

		this.operadoraDAO = new OperadoraDAO();

	}

	@Override
	protected CrudDAO<OperadoraModel> getCrudDAO() {
		return this.operadoraDAO;
	}

}
