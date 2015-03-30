package br.com.sysfar.imobileweb.faces;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import br.com.sysfar.imobileweb.dao.AtividadeDAO;
import br.com.sysfar.imobileweb.dao.ComboDAO;
import br.com.sysfar.imobileweb.dao.CrudDAO;
import br.com.sysfar.imobileweb.model.AtividadeModel;
import br.com.sysfar.imobileweb.model.CaptacaoModel;
import br.com.sysfar.imobileweb.model.ImovelModel;
import br.com.sysfar.imobileweb.model.StatusAtividadeModel;
import br.com.sysfar.imobileweb.model.UsuarioModel;
import br.com.sysfar.imobileweb.util.Constantes;
import br.com.sysfar.imobileweb.util.EmailUtil;
import br.com.sysfar.imobileweb.util.LayoutEmailUtil;
import br.com.sysfar.imobileweb.util.Utilitario;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

@ViewScoped
@ManagedBean(name = "atividadeFaces")
@SuppressWarnings("serial")
public class AtividadeFaces extends CrudFaces<AtividadeModel> {

	private AtividadeDAO atividadeDAO;
	private ComboDAO comboDAO;

	private ScheduleModel lazyEventModel;
	
	private List<SelectItem> comboResponsavel;
	private List<SelectItem> comboStatusAtividade;

	@Override
	@PostConstruct
	protected void clearFields() {

		this.crudModel = this.getInstanceAtividade();
		this.crudPesquisaModel = this.getInstanceAtividade();

		this.atividadeDAO = new AtividadeDAO();
		this.comboDAO = new ComboDAO();
		
		this.comboResponsavel = super.initCombo(this.comboDAO.pesquisarUsuarios(), "id", "nome");
		this.comboStatusAtividade = super.initCombo(this.comboDAO.pesquisarStatusAtividade(), "id", "descricao");
		
		this.pesquisar();

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

	public String pesquisar() {

		lazyEventModel = new LazyScheduleModel() {

			@Override
			public void loadEvents(Date start, Date end) {

				clear();

				crudPesquisaModel.setDataInicial(start);
				crudPesquisaModel.setDataFinal(end);

				DefaultScheduleEvent sm = null;

				for (AtividadeModel model : atividadeDAO.pesquisar(crudPesquisaModel)) {

					sm = new DefaultScheduleEvent(model.getResponsavelModel().getNome().replace("'", ""), model.getDataInicial(), (TSUtil.isEmpty(model.getDataFinal()) ? Utilitario.getDataOperacaoMinutos(model.getDataInicial(), 30) : model.getDataFinal()), false);

					sm.setData(model);

					if (Constantes.STATUS_ATIVIDADE_CONCLUIDA.equals(model.getStatusAtividadeModel().getId())) {

						sm.setStyleClass("verde");

					} else {

						sm.setStyleClass("amarelo");

					}

					addEvent(sm);

				}

			}

		};
		
		return null;

	}

	@Override
	protected boolean validaCampos() {

		boolean valida = true;

		if (TSUtil.isEmpty(this.crudModel.getResponsavelModel().getId())) {
			valida = false;
			super.addErrorMessage("Responsável: Campo obrigatório");
		}

		if (TSUtil.isEmpty(this.crudModel.getStatusAtividadeModel().getId())) {
			valida = false;
			super.addErrorMessage("Status: Campo obrigatório");
		}

		if (TSUtil.isEmpty(this.crudModel.getDataInicial())) {
			valida = false;
			super.addErrorMessage("Data Inicial: Campo obrigatório");
		}

		if (TSUtil.isEmpty(this.crudModel.getObservacao())) {
			valida = false;
			super.addErrorMessage("Observação: Campo obrigatório");
		}

		if (!TSUtil.isEmpty(this.crudModel.getDataInicial()) && !TSUtil.isEmpty(this.crudModel.getDataFinal()) && Utilitario.isPeriodoInvalido(this.crudModel.getDataInicial(), this.crudModel.getDataFinal())) {
			valida = false;
			super.addErrorMessage("Período inválido");
		}

		return valida;

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
	
	@Override
	protected void posInsert() {

		AtividadeModel atividadeModel = this.atividadeDAO.obter(this.crudModel);
		try {

			if (!TSUtil.isEmpty(atividadeModel.getResponsavelModel().getEmail())) {

				new EmailUtil().enviar(atividadeModel.getResponsavelModel().getEmail(), "Nova atividade cadastrada (Código:  " + atividadeModel.getId() + ")", new LayoutEmailUtil().getLayoutEmailNovaAtividade(atividadeModel));

			}

		} catch (TSApplicationException e) {

			super.throwException(e);

		}
		
	}
	
	public void onEventSelect(SelectEvent selectEvent) {
		this.crudModel = (AtividadeModel)((ScheduleEvent) selectEvent.getObject()).getData();
		this.detailEvent();
	}
	
	public void onDateSelect(SelectEvent selectEvent) {
		this.crudModel = this.getInstanceAtividade();
		this.crudModel.setDataInicial((Date) selectEvent.getObject());
	}

	public ScheduleModel getLazyEventModel() {
		return lazyEventModel;
	}

	public void setLazyEventModel(ScheduleModel lazyEventModel) {
		this.lazyEventModel = lazyEventModel;
	}

	public List<SelectItem> getComboResponsavel() {
		return comboResponsavel;
	}

	public void setComboResponsavel(List<SelectItem> comboResponsavel) {
		this.comboResponsavel = comboResponsavel;
	}

	public List<SelectItem> getComboStatusAtividade() {
		return comboStatusAtividade;
	}

	public void setComboStatusAtividade(List<SelectItem> comboStatusAtividade) {
		this.comboStatusAtividade = comboStatusAtividade;
	}

	@Override
	protected CrudDAO<AtividadeModel> getCrudDAO() {
		return this.atividadeDAO;
	}

}
