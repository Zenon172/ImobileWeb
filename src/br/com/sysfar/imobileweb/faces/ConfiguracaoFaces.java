package br.com.sysfar.imobileweb.faces;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sysfar.imobileweb.dao.ConfiguracaoDAO;
import br.com.sysfar.imobileweb.model.ConfiguracaoModel;
import br.com.sysfar.imobileweb.util.Utilitario;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.web.faces.TSMainFaces;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "configuracaoFaces")
public class ConfiguracaoFaces extends TSMainFaces {

	private List<ConfiguracaoModel> grid;

	private ConfiguracaoDAO configuracaoDAO;

	@Override
	@PostConstruct
	protected void clearFields() {

		this.configuracaoDAO = new ConfiguracaoDAO();

		this.grid = this.configuracaoDAO.pesquisar();

		for (ConfiguracaoModel model : this.grid) {

			if (model.getTipoRespostaModel().isTexto()) {

				model.setRespostaEscolhida(model.getValor());

			} else if (model.getTipoRespostaModel().isNumerico()) {

				model.setRespostaEscolhidaLong(Long.valueOf(model.getValor()));

			} else if (model.getTipoRespostaModel().isPontoFlutuante()) {

				model.setRespostaEscolhidaDouble(Double.valueOf(model.getValor()));

			} else if (model.getTipoRespostaModel().isInputText()) {

				model.setRespostaEscolhida(model.getValor());

			} else if (model.getTipoRespostaModel().isBooleano()) {

				model.setRespostaEscolhidaBoolean(Boolean.valueOf(model.getValor()));

			}

		}

	}

	@Override
	protected String update() throws TSApplicationException {

		super.setDefaultMessage(false);

		super.setClearFields(false);

		for (ConfiguracaoModel model : this.grid) {

			model.setUsuarioAtualizacaoModel(Utilitario.getUsuarioLogado());
			model.setDataAtualizacao(new Date());
			
			if (model.getTipoRespostaModel().isTexto()) {

				model.setValor(model.getRespostaEscolhida());

			} else if (model.getTipoRespostaModel().isNumerico()) {

				model.setValor(model.getRespostaEscolhidaLong().toString());

			} else if (model.getTipoRespostaModel().isPontoFlutuante()) {

				model.setValor(model.getRespostaEscolhidaDouble().toString());

			} else if (model.getTipoRespostaModel().isInputText()) {

				model.setValor(model.getRespostaEscolhida().toString());

			} else if (model.getTipoRespostaModel().isBooleano()) {

				model.setValor(model.getRespostaEscolhidaBoolean().toString());

			}

		}

		this.configuracaoDAO.alterar(this.grid);

		super.setDefaultMessage(true);

		super.setClearFields(true);

		return null;

	}

	public List<ConfiguracaoModel> getGrid() {
		return grid;
	}

	public void setGrid(List<ConfiguracaoModel> grid) {
		this.grid = grid;
	}

}
