package br.com.sysfar.imobileweb.faces;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.com.sysfar.imobileweb.dao.ComboDAO;
import br.com.sysfar.imobileweb.dao.CrudDAO;
import br.com.sysfar.imobileweb.dao.ImovelDAO;
import br.com.sysfar.imobileweb.model.BairroModel;
import br.com.sysfar.imobileweb.model.CaptacaoModel;
import br.com.sysfar.imobileweb.model.ConstrutoraModel;
import br.com.sysfar.imobileweb.model.EdificioModel;
import br.com.sysfar.imobileweb.model.ImovelModel;
import br.com.sysfar.imobileweb.model.PosicaoSolModel;
import br.com.sysfar.imobileweb.model.ProprietarioModel;
import br.com.sysfar.imobileweb.model.TipoFachadaModel;
import br.com.sysfar.imobileweb.model.TipoImovelModel;
import br.com.sysfar.imobileweb.model.TipoPisoModel;
import br.com.sysfar.imobileweb.model.UsuarioModel;
import br.com.sysfar.imobileweb.util.Constantes;
import br.com.sysfar.imobileweb.util.Utilitario;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;

@SuppressWarnings("serial")
@SessionScoped
@ManagedBean(name = "imovelFaces")
public class ImovelFaces extends CrudFaces<ImovelModel> {

	private ImovelDAO imovelDAO;
	private ComboDAO comboDAO;

	private List<SelectItem> comboTipoImovel;
	private List<SelectItem> comboEdificio;
	private List<SelectItem> comboBairro;
	private List<SelectItem> comboConstrutora;
	private List<SelectItem> comboTipoPiso;
	private List<SelectItem> comboTipoFachada;
	private List<SelectItem> comboPosicaoSol;
	private List<SelectItem> comboCaptador;
	private List<SelectItem> comboProprietario;

	@Override
	@PostConstruct
	protected void clearFields() {

		this.crudModel = new ImovelModel();
		this.crudModel.setTipoImovelModel(new TipoImovelModel());
		this.crudModel.setEdificioModel(new EdificioModel());
		this.crudModel.setBairroModel(new BairroModel());
		this.crudModel.setConstrutoraModel(new ConstrutoraModel());
		this.crudModel.setTipoPisoSalaModel(new TipoPisoModel());
		this.crudModel.setTipoPisoQuartoModel(new TipoPisoModel());
		this.crudModel.setTipoFachadaModel(new TipoFachadaModel());
		this.crudModel.setPosicaoSolModel(new PosicaoSolModel());
		this.crudModel.setCaptacaoModel(new CaptacaoModel());
		this.crudModel.setCaptadorModel(new UsuarioModel());
		this.crudModel.setProprietarioModel(new ProprietarioModel());

		this.crudPesquisaModel = new ImovelModel();

		this.imovelDAO = new ImovelDAO();
		this.comboDAO = new ComboDAO();

		this.iniciarCombos();
		
		CaptacaoModel captacaoModel = (CaptacaoModel)TSFacesUtil.getObjectInSession(Constantes.SESSION_CAPTACAO_ATUAL);

		if(!TSUtil.isEmpty(captacaoModel)){
			
			this.gerarViaCaptacao(captacaoModel);
			
			TSFacesUtil.removeObjectInSession(Constantes.SESSION_CAPTACAO_ATUAL);
			
		}
	}
	
	private void gerarViaCaptacao(CaptacaoModel captacao){
		
		this.crudModel.setTipoImovelModel(captacao.getTipoImovelModel());
		this.crudModel.setValor(captacao.getValor());
		this.crudModel.setBairroModel(captacao.getBairroModel());
		this.crudModel.setCaptacaoModel(captacao);
		this.crudModel.setDataCaptacao(captacao.getDataAtualizacao());
		this.crudModel.setCaptadorModel(captacao.getResponsavelModel());
		
	}

	private void iniciarCombos() {
		this.comboTipoImovel = super.initCombo(this.comboDAO.pesquisarTipoImovel(), "id", "descricao");
		this.comboEdificio = super.initCombo(this.comboDAO.pesquisarEdificio(), "id", "descricao");
		this.comboBairro = super.initCombo(this.comboDAO.pesquisarBairro(), "id", "descricao");
		this.comboConstrutora = super.initCombo(this.comboDAO.pesquisarConstrutora(), "id", "descricao");
		this.comboTipoPiso = super.initCombo(this.comboDAO.pesquisarTipoPiso(), "id", "descricao");
		this.comboTipoFachada = super.initCombo(this.comboDAO.pesquisarTipoFachada(), "id", "descricao");
		this.comboPosicaoSol = super.initCombo(this.comboDAO.pesquisarPosicaoSol(), "id", "descricao");
		this.comboCaptador = super.initCombo(this.comboDAO.pesquisarCaptador(), "id", "nome");
		this.comboProprietario = super.initCombo(this.comboDAO.pesquisarProprietario(), "id", "nome");
	}

	@Override
	protected void prePersist() {
		this.crudModel.setDataCadastro(new Date());
		this.crudModel.setUsuarioCadastroModel(Utilitario.getUsuarioLogado());
	}

	@Override
	protected CrudDAO<ImovelModel> getCrudDAO() {
		return this.imovelDAO;
	}

	public List<SelectItem> getComboTipoImovel() {
		return comboTipoImovel;
	}

	public void setComboTipoImovel(List<SelectItem> comboTipoImovel) {
		this.comboTipoImovel = comboTipoImovel;
	}

	public List<SelectItem> getComboEdificio() {
		return comboEdificio;
	}

	public void setComboEdificio(List<SelectItem> comboEdificio) {
		this.comboEdificio = comboEdificio;
	}

	public List<SelectItem> getComboBairro() {
		return comboBairro;
	}

	public void setComboBairro(List<SelectItem> comboBairro) {
		this.comboBairro = comboBairro;
	}

	public List<SelectItem> getComboConstrutora() {
		return comboConstrutora;
	}

	public void setComboConstrutora(List<SelectItem> comboConstrutora) {
		this.comboConstrutora = comboConstrutora;
	}

	public List<SelectItem> getComboTipoPiso() {
		return comboTipoPiso;
	}

	public void setComboTipoPiso(List<SelectItem> comboTipoPiso) {
		this.comboTipoPiso = comboTipoPiso;
	}

	public List<SelectItem> getComboTipoFachada() {
		return comboTipoFachada;
	}

	public void setComboTipoFachada(List<SelectItem> comboTipoFachada) {
		this.comboTipoFachada = comboTipoFachada;
	}

	public List<SelectItem> getComboPosicaoSol() {
		return comboPosicaoSol;
	}

	public void setComboPosicaoSol(List<SelectItem> comboPosicaoSol) {
		this.comboPosicaoSol = comboPosicaoSol;
	}

	public List<SelectItem> getComboCaptador() {
		return comboCaptador;
	}

	public void setComboCaptador(List<SelectItem> comboCaptador) {
		this.comboCaptador = comboCaptador;
	}

	public List<SelectItem> getComboProprietario() {
		return comboProprietario;
	}

	public void setComboProprietario(List<SelectItem> comboProprietario) {
		this.comboProprietario = comboProprietario;
	}

}
