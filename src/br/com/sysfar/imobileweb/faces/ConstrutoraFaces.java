package br.com.sysfar.imobileweb.faces;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sysfar.imobileweb.dao.ConstrutoraDAO;
import br.com.sysfar.imobileweb.dao.CrudDAO;
import br.com.sysfar.imobileweb.model.ConstrutoraModel;
import br.com.sysfar.imobileweb.util.Utilitario;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "construtoraFaces")
public class ConstrutoraFaces extends CrudFaces<ConstrutoraModel> {

	private ConstrutoraDAO construtoraDAO;

	@Override
	@PostConstruct
	protected void clearFields() {

		this.crudModel = new ConstrutoraModel();
		this.crudPesquisaModel = new ConstrutoraModel();

		this.construtoraDAO = new ConstrutoraDAO();

	}
	
	@Override
	protected void prePersist() {
		this.crudModel.setUsuarioCadastroModel(Utilitario.getUsuarioLogado());
		this.crudModel.setDataCadastro(new Date());
	}

	@Override
	protected CrudDAO<ConstrutoraModel> getCrudDAO() {
		return this.construtoraDAO;
	}

}
