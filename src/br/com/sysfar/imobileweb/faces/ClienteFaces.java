package br.com.sysfar.imobileweb.faces;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;

import br.com.sysfar.imobileweb.dao.ClienteDAO;
import br.com.sysfar.imobileweb.dao.ComboDAO;
import br.com.sysfar.imobileweb.dao.CrudDAO;
import br.com.sysfar.imobileweb.model.BairroModel;
import br.com.sysfar.imobileweb.model.ClienteContatoModel;
import br.com.sysfar.imobileweb.model.ClienteModel;
import br.com.sysfar.imobileweb.model.ClientePerfilBairroModel;
import br.com.sysfar.imobileweb.model.ClientePerfilModel;
import br.com.sysfar.imobileweb.model.ClienteStatusModel;
import br.com.sysfar.imobileweb.model.OperadoraModel;
import br.com.sysfar.imobileweb.model.StatusClienteModel;
import br.com.sysfar.imobileweb.model.TipoImovelModel;
import br.com.sysfar.imobileweb.model.UsuarioModel;
import br.com.sysfar.imobileweb.util.Constantes;
import br.com.sysfar.imobileweb.util.Utilitario;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "clienteFaces")
public class ClienteFaces extends CrudFaces<ClienteModel> {

	private ClienteDAO clienteDAO;
	private ComboDAO comboDAO;

	private ClienteStatusModel clienteStatusModel;
	private ClienteStatusModel clienteStatusSelecionadoModel;

	private List<SelectItem> comboStatusCliente;
	private List<SelectItem> comboTipoImovel;
	private List<SelectItem> comboOperadoras;
	private List<SelectItem> comboResponsavel;

	private List<BairroModel> comboBairros;
	private List<BairroModel> bairrosSelecionados;

	@Override
	@PostConstruct
	protected void clearFields() {

		this.crudModel = new ClienteModel();
		this.crudModel.setContatos(new ArrayList<ClienteContatoModel>());
		this.crudModel.setClientePerfilModel(this.getInstanceClientePerfil());

		this.carregarContatos();

		this.crudPesquisaModel = new ClienteModel();
		this.crudPesquisaModel.setUsuarioCadastroModel(new UsuarioModel());
		this.crudPesquisaModel.setStatusAtualModel(new StatusClienteModel());

		this.instanciarClienteStatus();

		this.clienteDAO = new ClienteDAO();
		this.comboDAO = new ComboDAO();

		this.comboStatusCliente = super.initCombo(this.comboDAO.pesquisarStatusCliente(), "id", "descricao");
		this.comboTipoImovel = super.initCombo(this.comboDAO.pesquisarTipoImovel(), "id", "descricao");
		this.comboBairros = this.comboDAO.pesquisarBairro();
		this.comboOperadoras = super.initCombo(this.comboDAO.pesquisarOperadoras(), "id", "descricao");
		this.comboResponsavel = super.initCombo(this.comboDAO.pesquisarUsuarios(), "id", "nome");

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

		this.crudModel.setClientePerfilModel(this.clienteDAO.obterPerfil(this.crudModel));

		if (TSUtil.isEmpty(this.crudModel.getClientePerfilModel())) {

			this.crudModel.setClientePerfilModel(this.getInstanceClientePerfil());

		} else {

			this.crudModel.getClientePerfilModel().setBairros(this.clienteDAO.pesquisarBairrosPerfil(this.crudModel.getClientePerfilModel()));

		}

		this.carregarContatos();
	}

	public String instanciarClienteStatus() {
		this.clienteStatusModel = new ClienteStatusModel();
		this.clienteStatusModel.setStatusClienteModel(new StatusClienteModel());
		this.clienteStatusModel.setClienteModel(this.crudModel);
		this.clienteStatusModel.setData(new Date());
		return null;
	}

	public String addStatus() {

		boolean valida = true;

		if (TSUtil.isEmpty(this.clienteStatusModel.getData())) {
			valida = false;
			super.addErrorMessage("Data: Campo obrigatório");
		}

		if (TSUtil.isEmpty(this.clienteStatusModel.getStatusClienteModel().getId())) {
			valida = false;
			super.addErrorMessage("Status: Campo obrigatório");
		}

		if (!valida) {
			return null;
		}

		this.clienteStatusModel.setDataCadastro(new Date());
		this.clienteStatusModel.setUsuarioCadastroModel(Utilitario.getUsuarioLogado());

		try {

			this.clienteDAO.inserir(this.clienteStatusModel);

			this.crudModel.getStatus().add(this.clienteStatusModel);

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

		for (BairroModel bairro : this.bairrosSelecionados) {

			ClientePerfilBairroModel bairroModel = new ClientePerfilBairroModel();
			bairroModel.setBairroModel(bairro);
			bairroModel.setClientePerfilModel(this.crudModel.getClientePerfilModel());

			this.crudModel.getClientePerfilModel().getBairros().add(bairroModel);

		}

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

	public List<BairroModel> getBairrosSelecionados() {
		return bairrosSelecionados;
	}

	public void setBairrosSelecionados(List<BairroModel> bairrosSelecionados) {
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

}
