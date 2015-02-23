package br.com.sysfar.imobileweb.faces;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;

import br.com.sysfar.imobileweb.dao.AtividadeDAO;
import br.com.sysfar.imobileweb.dao.CaptacaoDAO;
import br.com.sysfar.imobileweb.dao.ComboDAO;
import br.com.sysfar.imobileweb.dao.CrudDAO;
import br.com.sysfar.imobileweb.model.AtividadeModel;
import br.com.sysfar.imobileweb.model.BairroModel;
import br.com.sysfar.imobileweb.model.CaptacaoContatoModel;
import br.com.sysfar.imobileweb.model.CaptacaoModel;
import br.com.sysfar.imobileweb.model.ImovelModel;
import br.com.sysfar.imobileweb.model.MenuModel;
import br.com.sysfar.imobileweb.model.OrigemModel;
import br.com.sysfar.imobileweb.model.StatusAtividadeModel;
import br.com.sysfar.imobileweb.model.StatusCaptacaoModel;
import br.com.sysfar.imobileweb.model.TipoImovelModel;
import br.com.sysfar.imobileweb.model.UsuarioModel;
import br.com.sysfar.imobileweb.util.Constantes;
import br.com.sysfar.imobileweb.util.EmailUtil;
import br.com.sysfar.imobileweb.util.LayoutEmailUtil;
import br.com.sysfar.imobileweb.util.Utilitario;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "captacaoFaces")
public class CaptacaoFaces extends CrudFaces<CaptacaoModel> {

	private CaptacaoDAO captacaoDAO;
	private ComboDAO comboDAO;
	private AtividadeDAO atividadeDAO;

	private List<SelectItem> comboTipoImovel;
	private List<SelectItem> comboBairro;
	private List<SelectItem> comboOrigem;
	private List<SelectItem> comboStatusCaptacao;
	private List<SelectItem> comboResponsavel;
	private List<SelectItem> comboStatusAtividade;

	private AtividadeModel atividadeModel;

	private List<CaptacaoModel> captacoesExistentes;

	private boolean flagValidarCaptacaoDuplicada;

	@Override
	@PostConstruct
	protected void clearFields() {

		this.crudModel = new CaptacaoModel();
		this.crudModel.setTipoImovelModel(new TipoImovelModel(Constantes.TIPO_IMOVEL_APARTAMENTO));
		this.crudModel.setBairroModel(new BairroModel());
		this.crudModel.setOrigemModel(new OrigemModel());
		this.crudModel.setStatusCaptacaoModel(new StatusCaptacaoModel(Constantes.STATUS_CAPTACAO_NOVA));
		this.crudModel.setContatos(new ArrayList<CaptacaoContatoModel>());
		this.crudModel.setResponsavelModel(new UsuarioModel());
		this.crudModel.getResponsavelModel().setId(Utilitario.getUsuarioLogado().getId());

		this.carregarContatos();

		this.crudPesquisaModel = new CaptacaoModel();
		this.crudPesquisaModel.setTipoImovelModel(new TipoImovelModel());
		this.crudPesquisaModel.setBairroModel(new BairroModel());
		this.crudPesquisaModel.setOrigemModel(new OrigemModel());
		this.crudPesquisaModel.setStatusCaptacaoModel(new StatusCaptacaoModel());
		this.crudPesquisaModel.setResponsavelModel(new UsuarioModel());

		this.captacaoDAO = new CaptacaoDAO();
		this.comboDAO = new ComboDAO();
		this.atividadeDAO = new AtividadeDAO();

		this.comboTipoImovel = super.initCombo(this.comboDAO.pesquisarTipoImovel(), "id", "descricao");
		this.comboBairro = super.initCombo(this.comboDAO.pesquisarBairro(), "id", "descricao");
		this.comboOrigem = super.initCombo(this.comboDAO.pesquisarOrigem(), "id", "descricao");
		this.comboStatusCaptacao = super.initCombo(this.comboDAO.pesquisarStatusCaptacao(), "id", "descricao");
		this.comboResponsavel = super.initCombo(this.comboDAO.pesquisarUsuarios(), "id", "nome");
		this.comboStatusAtividade = super.initCombo(this.comboDAO.pesquisarStatusAtividade(), "id", "descricao");

		this.atividadeModel = this.getInstanceAtividade();
	}

	private AtividadeModel getInstanceAtividade() {

		AtividadeModel model = new AtividadeModel();

		model.setCaptacaoModel(new CaptacaoModel());
		model.setFlagAtivo(Boolean.TRUE);
		model.setImovelModel(new ImovelModel());
		model.setResponsavelModel(new UsuarioModel(Utilitario.getUsuarioLogado().getId()));
		model.setStatusAtividadeModel(new StatusAtividadeModel(Constantes.STATUS_ATIVIDADE_NOVA));

		return model;
	}

	private void carregarContatos() {

		for (int i = this.crudModel.getContatos().size(); i < 4; i++) {

			this.crudModel.getContatos().add(this.getCaptacaoContatoInstance());

		}

	}

	private CaptacaoContatoModel getCaptacaoContatoInstance() {
		CaptacaoContatoModel model = new CaptacaoContatoModel();
		model.setCaptacaoModel(this.crudModel);
		return model;
	}

	@Override
	protected void posDetail() {
		this.crudModel.setContatos(this.captacaoDAO.pesquisarContatos(this.crudModel));
		this.crudModel.setAtividades(this.atividadeDAO.pesquisar(this.crudModel));
		this.carregarContatos();
	}

	@Override
	protected void posInsert() {

		CaptacaoModel captacaoModel = this.captacaoDAO.obter(this.crudModel);

		try {

			if (!TSUtil.isEmpty(captacaoModel.getResponsavelModel().getEmail())) {

				new EmailUtil().enviar(captacaoModel.getResponsavelModel().getEmail(), "Nova Captação em " + captacaoModel.getBairroModel().getDescricao() + " (Código:  " + captacaoModel.getId() + ")", new LayoutEmailUtil().getLayoutEmailNovaCaptacao(captacaoModel));

			}

		} catch (TSApplicationException e) {

			super.throwException(e);

		}

	}

	@Override
	protected boolean validaCampos() {

		boolean valida = true;

		if (TSUtil.isEmpty(this.crudModel.getId()) && this.flagValidarCaptacaoDuplicada && !this.validarCaptacaoDuplicada()) {

			super.addErrorMessage("Captações similares foram encontradas");
			valida = false;
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", false);

		} else {

			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);

		}

		return valida;
	}

	public boolean validarCaptacaoDuplicada() {

		String descricao = this.crudPesquisaModel.getDescricao();

		this.captacoesExistentes = new ArrayList<CaptacaoModel>();

		for (CaptacaoContatoModel contato : this.crudModel.getContatos()) {

			if (!TSUtil.isEmpty(contato.getTelefone()) && contato.getTelefone().length() > 5) {

				this.crudPesquisaModel.setDescricao(contato.getTelefone().substring(5));
				this.captacoesExistentes.addAll(this.captacaoDAO.pesquisar(this.crudPesquisaModel));

			}

			if (!TSUtil.isEmpty(contato.getEmail())) {

				this.crudPesquisaModel.setDescricao(contato.getEmail());
				this.captacoesExistentes.addAll(this.captacaoDAO.pesquisar(this.crudPesquisaModel));

			}

		}

		this.crudPesquisaModel.setDescricao(descricao);

		return this.captacoesExistentes.isEmpty();
	}

	public String validarTelefoneDuplicidade(String telefone) {

		if (!TSUtil.isEmpty(telefone) && telefone.length() >= 14 && this.captacaoDAO.isExisteCaptacaoSimilar(telefone.substring(5))) {

			super.addErrorMessage("O telefone informado já foi encontrado em outra captação");

		}

		return null;
	}

	public String validarEmailDuplicidade(String email) {

		if (!TSUtil.isEmpty(email) && this.captacaoDAO.isExisteCaptacaoSimilar(email)) {

			super.addErrorMessage("O e-mail informado já foi encontrado em outra captação");

		}

		return null;
	}

	public String gerarImovel() {

		TSFacesUtil.addObjectInSession(Constantes.SESSION_CAPTACAO_ATUAL, this.captacaoDAO.obter(this.crudModel));

		MenuModel menuModel = new MenuModel();

		menuModel.setId(Constantes.MENU_IMOVEL);

		MenuFaces mf = (MenuFaces) TSFacesUtil.getManagedBeanInSession(Constantes.MENU_FACES);

		return (mf.escolherMenu(menuModel));

	}

	@Override
	protected void preInsert() {
		this.crudModel.setDataCadastro(new Date());
		this.crudModel.setUsuarioCadastroModel(Utilitario.getUsuarioLogado());
	}

	@Override
	protected void preUpdate() {
		this.crudModel.setDataAtualizacao(new Date());
		this.crudModel.setUsuarioAtualizacaoModel(Utilitario.getUsuarioLogado());
	}

	private boolean validaCamposAtividade() {

		boolean valida = true;

		if (TSUtil.isEmpty(this.atividadeModel.getResponsavelModel().getId())) {
			valida = false;
			super.addErrorMessage("Responsável: Campo obrigatório");
		}

		if (TSUtil.isEmpty(this.atividadeModel.getStatusAtividadeModel().getId())) {
			valida = false;
			super.addErrorMessage("Status: Campo obrigatório");
		}

		if (TSUtil.isEmpty(this.atividadeModel.getDataInicial())) {
			valida = false;
			super.addErrorMessage("Data Inicial: Campo obrigatório");
		}

		if (TSUtil.isEmpty(this.atividadeModel.getObservacao())) {
			valida = false;
			super.addErrorMessage("Observação: Campo obrigatório");
		}

		if (!TSUtil.isEmpty(this.atividadeModel.getDataInicial()) && !TSUtil.isEmpty(this.atividadeModel.getDataFinal()) && Utilitario.isPeriodoInvalido(this.atividadeModel.getDataInicial(), this.atividadeModel.getDataFinal())) {
			valida = false;
			super.addErrorMessage("Período inválido");
		}

		return valida;
	}

	public String addAtividade() {

		if (!this.validaCamposAtividade()) {
			return null;
		}

		this.atividadeModel.setUsuarioCadastroModel(Utilitario.getUsuarioLogado());
		this.atividadeModel.setDataCadastro(new Date());
		this.atividadeModel.setCaptacaoModel(this.crudModel);

		try {

			this.atividadeDAO.inserir(this.atividadeModel);

			this.crudModel.setAtividades(this.atividadeDAO.pesquisar(this.crudModel));

			super.addInfoMessageKey(Constantes.OPERACAO_SUCESSO);

			RequestContext.getCurrentInstance().addCallbackParam("valido", true);

		} catch (TSApplicationException e) {

			super.throwException(e);

		}

		return null;
	}

	public List<SelectItem> getComboTipoImovel() {
		return comboTipoImovel;
	}

	public void setComboTipoImovel(List<SelectItem> comboTipoImovel) {
		this.comboTipoImovel = comboTipoImovel;
	}

	public List<SelectItem> getComboBairro() {
		return comboBairro;
	}

	public void setComboBairro(List<SelectItem> comboBairro) {
		this.comboBairro = comboBairro;
	}

	public List<SelectItem> getComboOrigem() {
		return comboOrigem;
	}

	public void setComboOrigem(List<SelectItem> comboOrigem) {
		this.comboOrigem = comboOrigem;
	}

	public List<SelectItem> getComboStatusCaptacao() {
		return comboStatusCaptacao;
	}

	public void setComboStatusCaptacao(List<SelectItem> comboStatusCaptacao) {
		this.comboStatusCaptacao = comboStatusCaptacao;
	}

	public List<SelectItem> getComboResponsavel() {
		return comboResponsavel;
	}

	public void setComboResponsavel(List<SelectItem> comboResponsavel) {
		this.comboResponsavel = comboResponsavel;
	}

	public List<CaptacaoModel> getCaptacoesExistentes() {
		return captacoesExistentes;
	}

	public void setCaptacoesExistentes(List<CaptacaoModel> captacoesExistentes) {
		this.captacoesExistentes = captacoesExistentes;
	}

	public boolean isFlagValidarCaptacaoDuplicada() {
		return flagValidarCaptacaoDuplicada;
	}

	public void setFlagValidarCaptacaoDuplicada(boolean flagValidarCaptacaoDuplicada) {
		this.flagValidarCaptacaoDuplicada = flagValidarCaptacaoDuplicada;
	}

	public AtividadeModel getAtividadeModel() {
		return atividadeModel;
	}

	public void setAtividadeModel(AtividadeModel atividadeModel) {
		this.atividadeModel = atividadeModel;
	}

	public List<SelectItem> getComboStatusAtividade() {
		return comboStatusAtividade;
	}

	public void setComboStatusAtividade(List<SelectItem> comboStatusAtividade) {
		this.comboStatusAtividade = comboStatusAtividade;
	}

	@Override
	protected CrudDAO<CaptacaoModel> getCrudDAO() {
		return this.captacaoDAO;
	}

}
