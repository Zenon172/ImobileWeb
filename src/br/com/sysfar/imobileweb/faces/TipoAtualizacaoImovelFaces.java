package br.com.sysfar.imobileweb.faces;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sysfar.imobileweb.dao.CrudDAO;
import br.com.sysfar.imobileweb.dao.TipoAtualizacaoImovelDAO;
import br.com.sysfar.imobileweb.model.TipoAtualizacaoImovelModel;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "tipoAtualizacaoImovelFaces")
public class TipoAtualizacaoImovelFaces extends CrudFaces<TipoAtualizacaoImovelModel> {

	private TipoAtualizacaoImovelDAO tipoAtualizacaoImovelDAO;

	@Override
	@PostConstruct
	protected void clearFields() {

		this.crudModel = new TipoAtualizacaoImovelModel();
		this.crudModel.setFlagAtivo(Boolean.TRUE);
		
		this.crudPesquisaModel = new TipoAtualizacaoImovelModel();
		this.crudPesquisaModel.setFlagAtivo(Boolean.TRUE);

		this.tipoAtualizacaoImovelDAO = new TipoAtualizacaoImovelDAO();

	}

	@Override
	protected CrudDAO<TipoAtualizacaoImovelModel> getCrudDAO() {
		return this.tipoAtualizacaoImovelDAO;
	}

}
