package br.com.sysfar.imobileweb.faces;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;

import br.com.sysfar.imobileweb.dao.BairroDAO;
import br.com.sysfar.imobileweb.dao.ClienteDAO;
import br.com.sysfar.imobileweb.dao.ComboDAO;
import br.com.sysfar.imobileweb.dao.CrudDAO;
import br.com.sysfar.imobileweb.dao.ImovelDAO;
import br.com.sysfar.imobileweb.model.BairroModel;
import br.com.sysfar.imobileweb.model.ClienteContatoModel;
import br.com.sysfar.imobileweb.model.ClienteModel;
import br.com.sysfar.imobileweb.model.ClientePerfilBairroModel;
import br.com.sysfar.imobileweb.model.ClientePerfilModel;
import br.com.sysfar.imobileweb.model.ClienteStatusModel;
import br.com.sysfar.imobileweb.model.ImovelModel;
import br.com.sysfar.imobileweb.model.MenuModel;
import br.com.sysfar.imobileweb.model.OperadoraModel;
import br.com.sysfar.imobileweb.model.OrigemModel;
import br.com.sysfar.imobileweb.model.StatusClienteModel;
import br.com.sysfar.imobileweb.model.TipoImovelModel;
import br.com.sysfar.imobileweb.model.UsuarioModel;
import br.com.sysfar.imobileweb.util.Constantes;
import br.com.sysfar.imobileweb.util.Utilitario;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "clienteFaces")
public class ClienteFaces extends CrudFaces<ClienteModel> {

	private ImovelDAO imovelDAO;
	private ClienteDAO clienteDAO;
	private ComboDAO comboDAO;

	private ClienteStatusModel clienteStatusModel;
	private ClienteStatusModel clienteStatusSelecionadoModel;

	private List<SelectItem> comboStatusCliente;
	private List<SelectItem> comboTipoImovel;
	private List<SelectItem> comboOperadoras;
	private List<SelectItem> comboResponsavel;
	private List<SelectItem> comboOrigens;
	private List<SelectItem> comboPosicaoSol;

	private List<BairroModel> comboBairros;
	private List<String> bairrosSelecionados;

	@Override
	@PostConstruct
	protected void clearFields() {

		this.crudModel = new ClienteModel();
		this.crudModel.setContatos(new ArrayList<ClienteContatoModel>());
		this.crudModel.setClientePerfilModel(this.getInstanceClientePerfil());
		this.crudModel.setOrigemModel(new OrigemModel());

		this.carregarContatos();

		this.crudPesquisaModel = new ClienteModel();
		this.crudPesquisaModel.setUsuarioCadastroModel(new UsuarioModel());
		this.crudPesquisaModel.setStatusAtualModel(new StatusClienteModel());
		this.crudPesquisaModel.setOrigemModel(new OrigemModel());

		this.instanciarClienteStatus();

		this.clienteDAO = new ClienteDAO();
		this.comboDAO = new ComboDAO();
		this.imovelDAO = new ImovelDAO();

		this.comboStatusCliente = super.initCombo(this.comboDAO.pesquisarStatusCliente(), "id", "descricao");
		this.comboTipoImovel = super.initCombo(this.comboDAO.pesquisarTipoImovel(), "id", "descricao");
		this.comboBairros = this.comboDAO.pesquisarBairro();
		this.comboOperadoras = super.initCombo(this.comboDAO.pesquisarOperadoras(), "id", "descricao");
		this.comboResponsavel = super.initCombo(this.comboDAO.pesquisarUsuarios(), "id", "nome");
		this.comboOrigens = super.initCombo(this.comboDAO.pesquisarOrigem(), "id", "descricao");
		this.comboPosicaoSol = super.initCombo(this.comboDAO.pesquisarPosicaoSol(), "id", "descricao");

		this.bairrosSelecionados = new ArrayList<String>();

		super.setManterCampos(true);

		Long clienteId = (Long) TSFacesUtil.getObjectInSession(Constantes.SESSION_CLIENTE_ATUAL_ID);

		if (!TSUtil.isEmpty(clienteId)) {

			this.crudModel.setId(clienteId);

			this.detailEvent();

			TSFacesUtil.removeObjectInSession(Constantes.SESSION_CLIENTE_ATUAL_ID);

		}

	}

	private ClientePerfilModel getInstanceClientePerfil() {

		ClientePerfilModel model = new ClientePerfilModel();

		model.setBairros(new ArrayList<ClientePerfilBairroModel>());
		model.setClienteModel(this.crudModel);
		model.setTipoImovelModel(new TipoImovelModel());

		return model;
	}

	private void carregarContatos() {

		for (int i = this.crudModel.getContatos().size(); i < 4; i++) {

			this.crudModel.getContatos().add(this.getCaptacaoContatoInstance());

		}

	}

	private ClienteContatoModel getCaptacaoContatoInstance() {
		ClienteContatoModel model = new ClienteContatoModel();
		model.setClienteModel(this.crudModel);
		model.setOperadoraModel(new OperadoraModel());
		return model;
	}

	public String validarTelefoneDuplicidade(String telefone) {

		ClienteModel clienteExistente = null;

		if (!TSUtil.isEmpty(telefone) && telefone.length() >= 14) {

			clienteExistente = this.clienteDAO.obterPorTelefone(telefone.substring(5));

			if (!TSUtil.isEmpty(clienteExistente)) {

				super.addErrorMessage("O telefone informado já foi encontrado no cliente " + clienteExistente.getNome());

			}

		}

		return null;
	}

	@Override
	protected boolean validaCampos() {

		boolean valida = true;

		if (!TSUtil.isEmpty(this.crudModel.getEmail()) && !TSUtil.isEmailValid(this.crudModel.getEmail())) {
			valida = false;
			super.addErrorMessage("E-mail inválido");
		}

		return valida;
	}

	@Override
	protected void posDetail() {

		this.crudModel.setContatos(this.clienteDAO.pesquisarContatos(this.crudModel));
		this.crudModel.setStatus(this.clienteDAO.pesquisarStatus(this.crudModel));
		this.crudModel.setImoveisPerfil(this.imovelDAO.pesquisarPerfil(this.crudModel));

		this.crudModel.setClientePerfilModel(this.clienteDAO.obterPerfil(this.crudModel));

		if (TSUtil.isEmpty(this.crudModel.getClientePerfilModel())) {

			this.crudModel.setClientePerfilModel(this.getInstanceClientePerfil());

		} else {

			this.crudModel.getClientePerfilModel().setBairros(this.clienteDAO.pesquisarBairrosPerfil(this.crudModel.getClientePerfilModel()));

		}
		
		this.bairrosSelecionados.clear();

		for (ClientePerfilBairroModel model : this.crudModel.getClientePerfilModel().getBairros()) {

			this.bairrosSelecionados.add(model.getBairroModel().getDescricao());

		}

		this.carregarContatos();
	}

	@Override
	protected void posPersist() throws TSApplicationException {
		this.detailEvent();
	}

	public String instanciarClienteStatus() {
		this.clienteStatusModel = new ClienteStatusModel();
		this.clienteStatusModel.setStatusClienteModel(new StatusClienteModel());
		this.clienteStatusModel.setClienteModel(this.crudModel);
		this.clienteStatusModel.setData(new Date());
		return null;
	}
	
	private boolean validaCamposStatus(){
		
		boolean valida = true;
		
		if (TSUtil.isEmpty(this.clienteStatusModel.getData())) {
			valida = false;
			super.addErrorMessage("Data: Campo obrigatório");
		}
		
		if (TSUtil.isEmpty(this.clienteStatusModel.getStatusClienteModel().getId())) {
			valida = false;
			super.addErrorMessage("Status: Campo obrigatório");
		}
		
		return valida;
		
	}

	public String inserirStatus() {

		if (!this.validaCamposStatus()) {
			return null;
		}

		this.clienteStatusModel.setDataCadastro(new Date());
		this.clienteStatusModel.setUsuarioCadastroModel(Utilitario.getUsuarioLogado());

		try {

			this.clienteDAO.inserir(this.clienteStatusModel);
			
			this.crudModel.setStatus(this.clienteDAO.pesquisarStatus(this.crudModel));

			super.addInfoMessageKey(Constantes.OPERACAO_SUCESSO);

			RequestContext.getCurrentInstance().addCallbackParam("valido", true);

		} catch (TSApplicationException e) {

			super.throwException(e);

		}

		return null;
	}

	public String alterarStatus() {
		
		if (!this.validaCamposStatus()) {
			return null;
		}
		
		try {
			
			this.clienteDAO.alterar(this.clienteStatusModel);
			
			this.crudModel.setStatus(this.clienteDAO.pesquisarStatus(this.crudModel));
			
			super.addInfoMessageKey(Constantes.OPERACAO_SUCESSO);
			
			RequestContext.getCurrentInstance().addCallbackParam("valido", true);
			
		} catch (TSApplicationException e) {
			
			super.throwException(e);
			
		}
		
		return null;
	}
	
	public String removeStatus() {

		try {

			this.clienteDAO.excluir(this.clienteStatusSelecionadoModel);

			this.crudModel.getStatus().remove(this.clienteStatusSelecionadoModel);

			super.addInfoMessageKey(Constantes.OPERACAO_SUCESSO);

		} catch (TSApplicationException e) {

			super.throwException(e);

		}

		return null;
	}

	@Override
	protected void prePersist() {

		this.crudModel.setDataCadastro(new Date());
		this.crudModel.setUsuarioCadastroModel(Utilitario.getUsuarioLogado());

		this.crudModel.getClientePerfilModel().getBairros().clear();

		BairroDAO bairroDAO = new BairroDAO();

		for (String bairro : this.bairrosSelecionados) {

			ClientePerfilBairroModel bairroModel = new ClientePerfilBairroModel();
			bairroModel.setBairroModel(bairroDAO.obter(bairro));
			bairroModel.setClientePerfilModel(this.crudModel.getClientePerfilModel());

			this.crudModel.getClientePerfilModel().getBairros().add(bairroModel);

		}

	}

	public String obterStatus(ClienteStatusModel model) {

		this.clienteStatusModel = this.clienteDAO.obterStatus(model);

		return null;
	}

	public String redirecionarImovel(ImovelModel model) {

		TSFacesUtil.addObjectInSession(Constantes.SESSION_IMOVEL_ATUAL_ID, model.getId());

		TSFacesUtil.removeManagedBeanInSession(Constantes.IMOVEL_FACES);

		MenuFaces menuFaces = (MenuFaces) TSFacesUtil.getManagedBean(Constantes.MENU_FACES);

		return menuFaces.escolherMenu(new MenuModel(Constantes.MENU_IMOVEL));
	}

	@Override
	protected CrudDAO<ClienteModel> getCrudDAO() {
		return this.clienteDAO;
	}

	public ClienteStatusModel getClienteStatusModel() {
		return clienteStatusModel;
	}

	public void setClienteStatusModel(ClienteStatusModel clienteStatusModel) {
		this.clienteStatusModel = clienteStatusModel;
	}

	public List<SelectItem> getComboStatusCliente() {
		return comboStatusCliente;
	}

	public void setComboStatusCliente(List<SelectItem> comboStatusCliente) {
		this.comboStatusCliente = comboStatusCliente;
	}

	public ClienteStatusModel getClienteStatusSelecionadoModel() {
		return clienteStatusSelecionadoModel;
	}

	public void setClienteStatusSelecionadoModel(ClienteStatusModel clienteStatusSelecionadoModel) {
		this.clienteStatusSelecionadoModel = clienteStatusSelecionadoModel;
	}

	public List<SelectItem> getComboTipoImovel() {
		return comboTipoImovel;
	}

	public void setComboTipoImovel(List<SelectItem> comboTipoImovel) {
		this.comboTipoImovel = comboTipoImovel;
	}

	public List<BairroModel> getComboBairros() {
		return comboBairros;
	}

	public void setComboBairros(List<BairroModel> comboBairros) {
		this.comboBairros = comboBairros;
	}

	public List<String> getBairrosSelecionados() {
		return bairrosSelecionados;
	}

	public void setBairrosSelecionados(List<String> bairrosSelecionados) {
		this.bairrosSelecionados = bairrosSelecionados;
	}

	public List<SelectItem> getComboOperadoras() {
		return comboOperadoras;
	}

	public void setComboOperadoras(List<SelectItem> comboOperadoras) {
		this.comboOperadoras = comboOperadoras;
	}

	public List<SelectItem> getComboResponsavel() {
		return comboResponsavel;
	}

	public void setComboResponsavel(List<SelectItem> comboResponsavel) {
		this.comboResponsavel = comboResponsavel;
	}

	public List<SelectItem> getComboOrigens() {
		return comboOrigens;
	}

	public void setComboOrigens(List<SelectItem> comboOrigens) {
		this.comboOrigens = comboOrigens;
	}

	public List<SelectItem> getComboPosicaoSol() {
		return comboPosicaoSol;
	}

	public void setComboPosicaoSol(List<SelectItem> comboPosicaoSol) {
		this.comboPosicaoSol = comboPosicaoSol;
	}

}
