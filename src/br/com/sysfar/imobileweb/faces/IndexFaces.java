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
import br.com.sysfar.imobileweb.dao.ImovelDAO;
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
import br.com.topsys.web.faces.TSMainFaces;

@ViewScoped
@ManagedBean(name = "indexFaces")
@SuppressWarnings("serial")
public class IndexFaces extends TSMainFaces {

	private ImovelDAO imovelDAO;
	private ComboDAO comboDAO;
	
	private List<ImovelModel> imoveis;

	@Override
	@PostConstruct
	protected void clearFields() {

		this.imovelDAO = new ImovelDAO();
		
		this.imoveis = this.imovelDAO.pesquisarHome();

	}

	public List<ImovelModel> getImoveis() {
		return imoveis;
	}

	public void setImoveis(List<ImovelModel> imoveis) {
		this.imoveis = imoveis;
	}

}