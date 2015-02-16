package br.com.sysfar.imobileweb.faces;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.com.sysfar.imobileweb.dao.ComboDAO;
import br.com.sysfar.imobileweb.dao.CrudDAO;
import br.com.sysfar.imobileweb.dao.EdificioDAO;
import br.com.sysfar.imobileweb.model.CondominioModel;
import br.com.sysfar.imobileweb.model.EdificioModel;
import br.com.sysfar.imobileweb.util.Utilitario;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "edificioFaces")
public class EdificioFaces extends CrudFaces<EdificioModel> {

	private EdificioDAO edificioDAO;

	private ComboDAO comboDAO;

	private List<SelectItem> comboCondominio;

	@Override
	@PostConstruct
	protected void clearFields() {

		this.crudModel = new EdificioModel();
		this.crudModel.setCondominioModel(new CondominioModel());

		this.crudPesquisaModel = new EdificioModel();
		this.crudPesquisaModel.setCondominioModel(new CondominioModel());

		this.edificioDAO = new EdificioDAO();
		this.comboDAO = new ComboDAO();

		this.comboCondominio = super.initCombo(this.comboDAO.pesquisarCondominio(), "id", "descricao");

	}

	@Override
	protected void prePersist() {
		this.crudModel.setUsuarioCadastroModel(Utilitario.getUsuarioLogado());
		this.crudModel.setDataCadastro(new Date());
	}

	@Override
	protected CrudDAO<EdificioModel> getCrudDAO() {
		return this.edificioDAO;
	}

	public List<SelectItem> getComboCondominio() {
		return comboCondominio;
	}

	public void setComboCondominio(List<SelectItem> comboCondominio) {
		this.comboCondominio = comboCondominio;
	}

}
