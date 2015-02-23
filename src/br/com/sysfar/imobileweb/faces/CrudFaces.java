package br.com.sysfar.imobileweb.faces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.sysfar.imobileweb.dao.CrudDAO;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.faces.TSMainFaces;
import br.com.topsys.web.util.TSFacesUtil;

@SuppressWarnings("serial")
public abstract class CrudFaces<T extends Serializable> extends TSMainFaces {

	protected T crudModel;

	protected T crudPesquisaModel;

	protected List<T> grid;

	protected boolean alterar;

	protected boolean manterCampos;

	protected boolean ocultarMsg;

	protected boolean retornoSucesso;
	
	protected Integer tabIndex;

	public CrudFaces() {
		this.tabIndex = 1;
	}

	protected abstract CrudDAO<T> getCrudDAO();

	public String limpar() {
		this.alterar = false;
		clearFields();
		return SUCESSO;
	}

	public String limparPesquisa() {
		this.clearFields();
		grid = new ArrayList<T>();
		return SUCESSO;
	}

	protected void preFind() {

	}
	
	protected void preDetail() {
		
	}

	protected void posDetail() {

	}

	protected void preInsert() {

	}

	protected void preUpdate() {

	}

	protected void posInsert() {

	}

	protected void posUpdate() {

	}

	protected void prePersist() {

	}
	
	protected void preDelete() {
		
	}
	
	protected void posDelete() {
		
	}

	protected void posPersist() throws TSApplicationException {

	}

	public void gerarResultadoLista(List<?> lista) {

		if (TSUtil.isEmpty(lista)) {

			TSFacesUtil.addInfoMessage("A pesquisa não retornou nenhuma ocorrência");

		} else {

			Integer tamanho = lista.size();

			if (tamanho.equals(1)) {

				TSFacesUtil.addInfoMessage("A pesquisa retornou 1 ocorrência");

			} else {

				TSFacesUtil.addInfoMessage("A pesquisa retornou " + tamanho + " ocorrências");

			}

		}

	}

	@Override
	protected String find() {

		this.preFind();
		
		this.executeFind();

		this.gerarResultadoLista(grid);

		return null;

	}

	protected void executeFind() {

		this.preFind();

		this.grid = this.getCrudDAO().pesquisar(this.crudPesquisaModel);
	}

	@Override
	protected String detail() {

		if (!TSUtil.isEmpty(this.crudModel)) {
			
			this.preDetail();

			this.crudModel = (T) this.getCrudDAO().obter(this.crudModel);

			this.alterar = true;

			this.posDetail();

		}

		return null;

	}

	@Override
	protected String insert() throws TSApplicationException {

		super.setDefaultMessage(false);

		boolean valido = validaCampos();

		super.setClearFields(valido);

		if (!valido) {
			return null;
		}

		this.prePersist();

		this.preInsert();

		this.getCrudDAO().inserir(this.crudModel);

		this.posInsert();

		this.posPersist();

		super.setDefaultMessage(true);

		super.setClearFields(!this.manterCampos);
		
		this.alterar = this.manterCampos;

		if (retornoSucesso) {
			return SUCESSO;
		}

		return null;

	}

	protected void executeUpdate() throws TSApplicationException {
		this.getCrudDAO().alterar(this.crudModel);
	}

	@Override
	protected String update() throws TSApplicationException {

		super.setDefaultMessage(false);

		super.setClearFields(false);

		if (!validaCampos()) {
			return null;
		}

		this.prePersist();

		this.preUpdate();

		this.executeUpdate();

		this.posUpdate();

		this.posPersist();

		super.setDefaultMessage(true);

		super.setClearFields(!this.manterCampos);

		if (retornoSucesso) {
			return SUCESSO;
		}

		return null;

	}

	@Override
	protected String delete() throws TSApplicationException {

		this.preDelete();
		
		this.getCrudDAO().excluir(this.crudModel);

		this.executeFind();
		
		this.posDelete();

		if (retornoSucesso) {
			return SUCESSO;
		}

		return null;

	}
	
	protected boolean validaCampos() {
		return true;
	}

	public boolean isAlterar() {
		return alterar;
	}

	public void setAlterar(boolean alterar) {
		this.alterar = alterar;
	}

	public T getCrudModel() {
		return crudModel;
	}

	public void setCrudModel(T crudModel) {
		this.crudModel = crudModel;
	}

	public T getCrudPesquisaModel() {
		return crudPesquisaModel;
	}

	public void setCrudPesquisaModel(T crudPesquisaModel) {
		this.crudPesquisaModel = crudPesquisaModel;
	}

	public boolean isManterCampos() {
		return manterCampos;
	}

	public void setManterCampos(boolean manterCampos) {
		this.manterCampos = manterCampos;
	}

	public boolean isOcultarMsg() {
		return ocultarMsg;
	}

	public void setOcultarMsg(boolean ocultarMsg) {
		this.ocultarMsg = ocultarMsg;
	}

	public List<T> getGrid() {
		return grid;
	}

	public void setGrid(List<T> grid) {
		this.grid = grid;
	}

	public Integer getTabIndex() {
		return tabIndex;
	}

	public void setTabIndex(Integer tabIndex) {
		this.tabIndex = tabIndex;
	}

}
