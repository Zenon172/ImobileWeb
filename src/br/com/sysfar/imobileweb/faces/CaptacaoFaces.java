package br.com.sysfar.imobileweb.faces;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.com.sysfar.imobileweb.dao.CaptacaoDAO;
import br.com.sysfar.imobileweb.dao.ComboDAO;
import br.com.sysfar.imobileweb.dao.CrudDAO;
import br.com.sysfar.imobileweb.dao.UsuarioDAO;
import br.com.sysfar.imobileweb.model.BairroModel;
import br.com.sysfar.imobileweb.model.CaptacaoContatoModel;
import br.com.sysfar.imobileweb.model.CaptacaoModel;
import br.com.sysfar.imobileweb.model.OrigemModel;
import br.com.sysfar.imobileweb.model.StatusCaptacaoModel;
import br.com.sysfar.imobileweb.model.TipoImovelModel;
import br.com.sysfar.imobileweb.model.UsuarioModel;
import br.com.sysfar.imobileweb.util.Constantes;
import br.com.sysfar.imobileweb.util.Utilitario;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "captacaoFaces")
public class CaptacaoFaces extends CrudFaces<CaptacaoModel> {

	private CaptacaoDAO captacaoDAO;
	private ComboDAO comboDAO;
	private UsuarioDAO usuarioDAO;

	private List<SelectItem> comboTipoImovel;
	private List<SelectItem> comboBairro;
	private List<SelectItem> comboOrigem;
	private List<SelectItem> comboStatusCaptacao;
	private List<SelectItem> comboResponsavel;

	@Override
	@PostConstruct
	protected void clearFields() {

		this.crudModel = new CaptacaoModel();
		this.crudModel.setTipoImovelModel(new TipoImovelModel(Constantes.TIPO_IMOVEL_APARTAMENTO_ID));
		this.crudModel.setBairroModel(new BairroModel());
		this.crudModel.setOrigemModel(new OrigemModel());
		this.crudModel.setStatusCaptacaoModel(new StatusCaptacaoModel(Constantes.STATUS_CAPTACAO_NOVA_ID));
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
		this.usuarioDAO = new UsuarioDAO();

		this.comboTipoImovel = super.initCombo(this.comboDAO.pesquisarTipoImovel(), "id", "descricao");
		this.comboBairro = super.initCombo(this.comboDAO.pesquisarBairro(), "id", "descricao");
		this.comboOrigem = super.initCombo(this.comboDAO.pesquisarOrigem(), "id", "descricao");
		this.comboStatusCaptacao = super.initCombo(this.comboDAO.pesquisarStatusCaptacao(), "id", "descricao");
		this.comboResponsavel = super.initCombo(this.usuarioDAO.pesquisarCombo(), "id", "nome");

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
		this.carregarContatos();
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

	@Override
	protected CrudDAO<CaptacaoModel> getCrudDAO() {
		return this.captacaoDAO;
	}

}
