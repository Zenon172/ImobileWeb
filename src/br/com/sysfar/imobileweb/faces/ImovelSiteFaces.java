package br.com.sysfar.imobileweb.faces;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.sysfar.imobileweb.dao.ImovelDAO;
import br.com.sysfar.imobileweb.model.ImovelModel;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.faces.TSMainFaces;

@RequestScoped
@SuppressWarnings("serial")
@ManagedBean(name = "imovelSiteFaces")
public class ImovelSiteFaces extends TSMainFaces {

	private ImovelDAO imovelDAO;
	
	private ImovelModel imovelModel;
	
	private List<String> fotos;

	@Override
	@PostConstruct
	protected void clearFields() {

		this.imovelDAO = new ImovelDAO();
		
		String imovelId = super.getRequestParameter("codigo");
		
		if(!TSUtil.isEmpty(imovelId) && TSUtil.isNumeric(imovelId)){
			
			this.imovelModel = this.imovelDAO.obter(new ImovelModel(Long.valueOf(imovelId)));
			
			if(!TSUtil.isEmpty(this.imovelModel)){
				
				this.imovelModel.setFotos(this.imovelDAO.pesquisarFotos(this.imovelModel));
				
				this.fotos = this.imovelDAO.pesquisarGaleria(this.imovelModel);
				
			}
					
		}
		

	}

	public ImovelModel getImovelModel() {
		return imovelModel;
	}

	public void setImovelModel(ImovelModel imovelModel) {
		this.imovelModel = imovelModel;
	}

	public List<String> getFotos() {
		return fotos;
	}

	public void setFotos(List<String> fotos) {
		this.fotos = fotos;
	}

}
