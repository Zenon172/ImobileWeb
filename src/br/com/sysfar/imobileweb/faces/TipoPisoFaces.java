package br.com.sysfar.imobileweb.faces;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sysfar.imobileweb.dao.CrudDAO;
import br.com.sysfar.imobileweb.dao.TipoPisoDAO;
import br.com.sysfar.imobileweb.model.TipoPisoModel;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "tipoPisoFaces")
public class TipoPisoFaces extends CrudFaces<TipoPisoModel> {

	private TipoPisoDAO tipoPisoDAO;

	@Override
	@PostConstruct
	protected void clearFields() {

		this.crudModel = new TipoPisoModel();
		this.crudPesquisaModel = new TipoPisoModel();

		this.tipoPisoDAO = new TipoPisoDAO();

	}

	@Override
	protected CrudDAO<TipoPisoModel> getCrudDAO() {
		return this.tipoPisoDAO;
	}

}
