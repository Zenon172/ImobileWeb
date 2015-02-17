package br.com.sysfar.imobileweb.faces;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sysfar.imobileweb.dao.CrudDAO;
import br.com.sysfar.imobileweb.dao.TipoImovelDAO;
import br.com.sysfar.imobileweb.model.TipoImovelModel;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "tipoImovelFaces")
public class TipoImovelFaces extends CrudFaces<TipoImovelModel> {

	private TipoImovelDAO tipoImovelDAO;

	@Override
	@PostConstruct
	protected void clearFields() {

		this.crudModel = new TipoImovelModel();
		this.crudPesquisaModel = new TipoImovelModel();

		this.tipoImovelDAO = new TipoImovelDAO();

	}

	@Override
	protected CrudDAO<TipoImovelModel> getCrudDAO() {
		return this.tipoImovelDAO;
	}

}
