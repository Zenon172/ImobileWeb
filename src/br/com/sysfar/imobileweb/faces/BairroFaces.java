package br.com.sysfar.imobileweb.faces;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sysfar.imobileweb.dao.BairroDAO;
import br.com.sysfar.imobileweb.dao.CrudDAO;
import br.com.sysfar.imobileweb.model.BairroModel;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "bairroFaces")
public class BairroFaces extends CrudFaces<BairroModel> {

	private BairroDAO bairroDAO;

	@Override
	@PostConstruct
	protected void clearFields() {

		this.crudModel = new BairroModel();
		this.crudPesquisaModel = new BairroModel();

		this.bairroDAO = new BairroDAO();

	}

	@Override
	protected CrudDAO<BairroModel> getCrudDAO() {
		return this.bairroDAO;
	}

}
