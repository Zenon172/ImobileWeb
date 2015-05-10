package br.com.sysfar.imobileweb.faces;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.com.sysfar.imobileweb.dao.ComboDAO;
import br.com.sysfar.imobileweb.dao.CorretorDAO;
import br.com.sysfar.imobileweb.dao.CrudDAO;
import br.com.sysfar.imobileweb.model.CorretorContatoModel;
import br.com.sysfar.imobileweb.model.CorretorModel;
import br.com.sysfar.imobileweb.model.OperadoraModel;
import br.com.sysfar.imobileweb.util.Utilitario;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "corretorFaces")
public class CorretorFaces extends CrudFaces<CorretorModel> {

	private CorretorDAO corretorDAO;
	private ComboDAO comboDAO;

	private List<SelectItem> comboOperadoras;

	@Override
	@PostConstruct
	protected void clearFields() {

		this.crudModel = new CorretorModel();
		this.crudModel.setContatos(new ArrayList<CorretorContatoModel>());

		this.carregarContatos();

		this.crudPesquisaModel = new CorretorModel();

		this.corretorDAO = new CorretorDAO();
		this.comboDAO = new ComboDAO();

		this.comboOperadoras = super.initCombo(this.comboDAO.pesquisarOperadoras(), "id", "descricao");

	}

	private void carregarContatos() {

		for (int i = this.crudModel.getContatos().size(); i < 4; i++) {

			this.crudModel.getContatos().add(this.getCorretorContatoInstance());

		}

	}

	private CorretorContatoModel getCorretorContatoInstance() {
		CorretorContatoModel model = new CorretorContatoModel();
		model.setCorretorModel(this.crudModel);
		model.setOperadoraModel(new OperadoraModel());
		return model;
	}

	@Override
	protected void posDetail() {
		this.crudModel.setContatos(this.corretorDAO.pesquisarContatos(this.crudModel));
		this.carregarContatos();
	}

	@Override
	protected void preInsert() {
		this.crudModel.setDataCadastro(new Date());
		this.crudModel.setUsuarioCadastroModel(Utilitario.getUsuarioLogado());
	}

	public List<SelectItem> getComboOperadoras() {
		return comboOperadoras;
	}

	public void setComboOperadoras(List<SelectItem> comboOperadoras) {
		this.comboOperadoras = comboOperadoras;
	}

	@Override
	protected CrudDAO<CorretorModel> getCrudDAO() {
		return this.corretorDAO;
	}

}
