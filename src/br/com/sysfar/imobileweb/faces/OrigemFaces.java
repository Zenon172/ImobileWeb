package br.com.sysfar.imobileweb.faces;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sysfar.imobileweb.dao.CrudDAO;
import br.com.sysfar.imobileweb.dao.OrigemDAO;
import br.com.sysfar.imobileweb.model.OrigemModel;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "origemFaces")
public class OrigemFaces extends CrudFaces<OrigemModel> {

	private OrigemDAO origemDAO;

	@Override
	@PostConstruct
	protected void clearFields() {

		this.crudModel = new OrigemModel();
		this.crudPesquisaModel = new OrigemModel();

		this.origemDAO = new OrigemDAO();

	}

	@Override
	protected CrudDAO<OrigemModel> getCrudDAO() {
		return this.origemDAO;
	}

}
