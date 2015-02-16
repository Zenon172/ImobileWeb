package br.com.sysfar.imobileweb.faces;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sysfar.imobileweb.dao.CrudDAO;
import br.com.sysfar.imobileweb.dao.TipoFachadaDAO;
import br.com.sysfar.imobileweb.model.TipoFachadaModel;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "tipoFachadaFaces")
public class TipoFachadaFaces extends CrudFaces<TipoFachadaModel> {

	private TipoFachadaDAO tipoFachadaDAO;

	@Override
	@PostConstruct
	protected void clearFields() {

		this.crudModel = new TipoFachadaModel();
		this.crudPesquisaModel = new TipoFachadaModel();

		this.tipoFachadaDAO = new TipoFachadaDAO();

	}

	@Override
	protected CrudDAO<TipoFachadaModel> getCrudDAO() {
		return this.tipoFachadaDAO;
	}

}
