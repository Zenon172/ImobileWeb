package br.com.sysfar.imobileweb.faces;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sysfar.imobileweb.dao.PorteiroDAO;
import br.com.sysfar.imobileweb.model.PorteiroModel;
import br.com.topsys.web.util.TSFacesUtil;

@ViewScoped
@ManagedBean(name = "porteiroFaces")
public class PorteiroFaces {

	private PorteiroDAO porteiroDAO;
	
	private PorteiroModel porteiroModel;
	private PorteiroModel porteiroPesquisaModel;
	
	private List<PorteiroModel> listaPorteiros;
	
	public PorteiroFaces() {
		
		this.porteiroDAO = new PorteiroDAO();
		
		this.porteiroModel = new PorteiroModel();
		this.porteiroModel.setFlagAtivo(true);
		
		this.porteiroPesquisaModel = new PorteiroModel();
		this.porteiroPesquisaModel.setFlagAtivo(true);
		
	}
	
	public String inserir() {
		
		try {
			
			this.porteiroDAO.inserir(this.porteiroModel);
			
			TSFacesUtil.addInfoMessage("Operação realizada com sucesso");
			
		} catch (Exception e) {
			
			TSFacesUtil.addErrorMessage("Ocorreu um erro ao salvar o porteiro");
			e.printStackTrace();
			
		}
		
		return null;
	}
	
	public String excluir(PorteiroModel porteiroModel) {
		
		try {
			
			this.porteiroDAO.excluir(porteiroModel);
			
			this.pesquisar();
			
			TSFacesUtil.addInfoMessage("Operação realizada com sucesso");
			
		} catch (Exception e) {
			
			TSFacesUtil.addErrorMessage("Ocorreu um erro ao excluir o porteiro");
			e.printStackTrace();
			
		}
		
		return null;
	}
	
	public String alterar() {
		
		try {
			
			this.porteiroDAO.alterar(this.porteiroModel);
			
			this.pesquisar();
			
			TSFacesUtil.addInfoMessage("Operação realizada com sucesso");
			
		} catch (Exception e) {
			
			TSFacesUtil.addErrorMessage("Ocorreu um erro ao alterar o porteiro");
			e.printStackTrace();
			
		}
		
		return null;
	}
	
	public String limpar() {
		
		this.porteiroModel = new PorteiroModel();
		
		return null;
	}
	
	public String pesquisar() {
		
		this.listaPorteiros = this.porteiroDAO.pesquisar(this.porteiroPesquisaModel);
		
		return null;
		
	}

	public PorteiroModel getPorteiroModel() {
		return porteiroModel;
	}

	public void setPorteiroModel(PorteiroModel porteiroModel) {
		this.porteiroModel = porteiroModel;
	}

	public List<PorteiroModel> getListaPorteiros() {
		return listaPorteiros;
	}

	public void setListaPorteiros(List<PorteiroModel> listaPorteiros) {
		this.listaPorteiros = listaPorteiros;
	}

	public PorteiroModel getPorteiroPesquisaModel() {
		return porteiroPesquisaModel;
	}

	public void setPorteiroPesquisaModel(PorteiroModel porteiroPesquisaModel) {
		this.porteiroPesquisaModel = porteiroPesquisaModel;
	}
	
}
