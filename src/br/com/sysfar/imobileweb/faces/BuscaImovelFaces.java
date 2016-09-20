package br.com.sysfar.imobileweb.faces;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sysfar.imobileweb.dao.ImovelDAO;
import br.com.sysfar.imobileweb.model.CondominioModel;
import br.com.sysfar.imobileweb.model.EdificioModel;
import br.com.sysfar.imobileweb.model.ImovelModel;
import br.com.sysfar.imobileweb.model.ProprietarioModel;
import br.com.sysfar.imobileweb.model.TipoImovelModel;
import br.com.sysfar.imobileweb.model.UsuarioModel;
import br.com.topsys.web.faces.TSMainFaces;

@ViewScoped
@SuppressWarnings("serial")
@ManagedBean(name = "buscaImovelFaces")
public class BuscaImovelFaces extends TSMainFaces {

	private ImovelDAO imovelDAO;
	
	private ImovelModel crudPesquisaModel;
	
	private ImovelModel imovelModel;
	private List<ImovelModel> imoveis;
	
	@Override
	@PostConstruct
	protected void clearFields() {

		this.imovelDAO = new ImovelDAO();
		
		this.crudPesquisaModel = new ImovelModel();
		this.crudPesquisaModel.setFlagAtivo(Boolean.TRUE);
		this.crudPesquisaModel.setTipoImovelModel(new TipoImovelModel());
		this.crudPesquisaModel.setCondominioModel(new CondominioModel());
		this.crudPesquisaModel.setEdificioModel(new EdificioModel());
		this.crudPesquisaModel.getEdificioModel().setCondominioModel(new CondominioModel());
		this.crudPesquisaModel.setCaptadorModel(new UsuarioModel());
		this.crudPesquisaModel.setProprietarioModel(new ProprietarioModel());
		this.crudPesquisaModel.setBairrosPesquisa(new ArrayList<String>());
		this.crudPesquisaModel.setCodigo("NM206");
		
		this.imoveis = this.imovelDAO.pesquisar(this.crudPesquisaModel);
		
	}
	
	public ImovelModel getImovelModel() {
		return imovelModel;
	}

	public void setImovelModel(ImovelModel imovelModel) {
		this.imovelModel = imovelModel;
	}

	public List<ImovelModel> getImoveis() {
		return imoveis;
	}

	public void setImoveis(List<ImovelModel> imoveis) {
		this.imoveis = imoveis;
	}

	public ImovelModel getCrudPesquisaModel() {
		return crudPesquisaModel;
	}

	public void setCrudPesquisaModel(ImovelModel crudPesquisaModel) {
		this.crudPesquisaModel = crudPesquisaModel;
	}

}
