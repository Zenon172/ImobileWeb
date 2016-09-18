package br.com.sysfar.imobileweb.faces;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sysfar.imobileweb.dao.ContatoDAO;
import br.com.sysfar.imobileweb.dao.ImovelDAO;
import br.com.sysfar.imobileweb.model.ContatoModel;
import br.com.sysfar.imobileweb.model.ImovelModel;
import br.com.sysfar.imobileweb.util.Constantes;
import br.com.sysfar.imobileweb.util.EmailUtil;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.faces.TSMainFaces;

@ViewScoped
@SuppressWarnings("serial")
@ManagedBean(name = "imovelSiteFaces")
public class ImovelSiteFaces extends TSMainFaces {

	private ImovelDAO imovelDAO;
	private ContatoDAO contatoDAO;
	
	private ImovelModel imovelModel;
	private ContatoModel contatoModel;
	
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
				
				this.contatoDAO = new ContatoDAO();
				
				this.instanciarContato();
				
			}
					
		}
		

	}
	
	private void instanciarContato(){
		this.contatoModel = new ContatoModel();
		this.contatoModel.setImovelModel(this.imovelModel);
		this.contatoModel.setMensagem("Olá! Achei esse imóvel através do site www.nayaramaciel.com.br. Por favor, gostaria de mais informações sobre o mesmo. Aguardo contato. Grato.");
	}
	
	public String enviarMensagem(){
		
		if(TSUtil.isEmpty(this.contatoModel.getTelefone()) && TSUtil.isEmpty(this.contatoModel.getEmail())){
			super.addErrorMessage("Telefone ou Email: Campo obrigatório");
			return null;
		}
		
		if(!TSUtil.isEmpty(this.contatoModel.getEmail()) && !TSUtil.isEmailValid(this.contatoModel.getEmail())){
			super.addErrorMessage("E-mail inválido");
			return null;
		}
		
		this.contatoModel.setDataCadastro(new Date());
		
		try {
			
			this.contatoDAO.inserir(this.contatoModel);
			
			new EmailUtil().enviar("contato@nayaramaciel.com.br", "Contato através do site: www.nayaramaciel.com.br", this.contatoModel.getMensagem());
			
			this.instanciarContato();
			
			super.addInfoMessageKey(Constantes.OPERACAO_SUCESSO);
			
		} catch (TSApplicationException e) {
			
			super.throwException(e);
			
		}
		
		return null;
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

	public ContatoModel getContatoModel() {
		return contatoModel;
	}

	public void setContatoModel(ContatoModel contatoModel) {
		this.contatoModel = contatoModel;
	}

}
