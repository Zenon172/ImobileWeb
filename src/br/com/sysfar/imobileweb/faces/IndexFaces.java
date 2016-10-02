package br.com.sysfar.imobileweb.faces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.google.gson.Gson;

import br.com.sysfar.imobileweb.dao.ComboDAO;
import br.com.sysfar.imobileweb.dao.ImovelDAO;
import br.com.sysfar.imobileweb.model.ImovelModel;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.faces.TSMainFaces;

@SessionScoped
@ManagedBean(name = "indexFaces")
@SuppressWarnings("serial")
public class IndexFaces extends TSMainFaces {

	private ImovelDAO imovelDAO;
	private ComboDAO comboDAO;

	private ImovelModel imovelPesquisaModel;

	private List<ImovelModel> imoveis;
	private List<SelectItem> comboBairros;
	private List<SelectItem> comboTipoImovel;
	List<Map<String, String>> listaCoordenadas;
	
	private String tipoOrdenacao;

	@Override
	@PostConstruct
	protected void clearFields() {

		this.imovelDAO = new ImovelDAO();
		this.comboDAO = new ComboDAO();
		
		this.imovelPesquisaModel = new ImovelModel();
		this.imovelPesquisaModel.instanciarCamposPesquisa();

		this.find();
		
		this.comboBairros = super.initCombo(this.comboDAO.pesquisarBairro(), "id", "descricao");
		this.comboTipoImovel = super.initCombo(this.comboDAO.pesquisarTipoImovel(), "id", "descricao");

	}
	
	@Override
	protected String find() {
		
		StringBuilder parametrosUrl = new StringBuilder();
		
		if(!TSUtil.isEmpty(this.imovelPesquisaModel.getBairroModel().getId())){
			parametrosUrl.append(!TSUtil.isEmpty(parametrosUrl.toString()) ? "&" : "").append("bairroId=").append(this.imovelPesquisaModel.getBairroModel().getId());
		}
		
		if(!TSUtil.isEmpty(this.imovelPesquisaModel.getValorMin())){
			parametrosUrl.append(!TSUtil.isEmpty(parametrosUrl.toString()) ? "&" : "").append("minValor=").append(this.imovelPesquisaModel.getValorMin());
		}
		
		if(!TSUtil.isEmpty(this.imovelPesquisaModel.getValorMax())){
			parametrosUrl.append(!TSUtil.isEmpty(parametrosUrl.toString()) ? "&" : "").append("maxValor=").append(this.imovelPesquisaModel.getValorMax());
		}
		
		if(!TSUtil.isEmpty(this.imovelPesquisaModel.getTipoImovelModel().getId())){
			parametrosUrl.append(!TSUtil.isEmpty(parametrosUrl.toString()) ? "&" : "").append("tipo=").append(this.imovelPesquisaModel.getTipoImovelModel().getId());
		}
		
		if(!TSUtil.isEmpty(this.imovelPesquisaModel.getCodigo())){
			parametrosUrl.append(!TSUtil.isEmpty(parametrosUrl.toString()) ? "&" : "").append("codigo=").append(this.imovelPesquisaModel.getCodigo());
		}
		
		if(!TSUtil.isEmpty(parametrosUrl.toString())){

			this.imoveis = this.imovelDAO.pesquisar(this.imovelPesquisaModel, this.tipoOrdenacao);
			
		} else {
			
			this.imoveis = this.imovelDAO.pesquisarHome();
			
		}

		listaCoordenadas = new ArrayList<Map<String, String>>();

		Map<String, String> mapaCoordenadas = null;

		for (ImovelModel model : this.imoveis) {

			if (!TSUtil.isEmpty(model.getLatitude()) && !TSUtil.isEmpty(model.getLongitude())) {

				mapaCoordenadas = new HashMap<String, String>();

				mapaCoordenadas.put("id", model.getId().toString());
				mapaCoordenadas.put("googlemap_lt", model.getLatitude().toString());
				mapaCoordenadas.put("googlemap_ln", model.getLongitude().toString());
				mapaCoordenadas.put("title", null);
				mapaCoordenadas.put("pids", model.getId().toString());
				mapaCoordenadas.put("gmap_icon", "mapIcon.png");

				listaCoordenadas.add(mapaCoordenadas);

			}

		}
		
		if(!TSUtil.isEmpty(this.tipoOrdenacao)){
			parametrosUrl.append(!TSUtil.isEmpty(parametrosUrl.toString()) ? "&" : "").append("ordem=").append(this.tipoOrdenacao);
		}
		
		return "index.xhtml" + (!TSUtil.isEmpty(parametrosUrl.toString()) ? "?" + parametrosUrl.toString() + "&faces-redirect=true" : "");
		
	}

	public String getCoordenadas() {
		return new Gson().toJson(listaCoordenadas);
	}

	public List<ImovelModel> getImoveis() {
		return imoveis;
	}

	public void setImoveis(List<ImovelModel> imoveis) {
		this.imoveis = imoveis;
	}

	public ImovelModel getImovelPesquisaModel() {
		return imovelPesquisaModel;
	}

	public void setImovelPesquisaModel(ImovelModel imovelPesquisaModel) {
		this.imovelPesquisaModel = imovelPesquisaModel;
	}

	public List<SelectItem> getComboBairros() {
		return comboBairros;
	}

	public void setComboBairros(List<SelectItem> comboBairros) {
		this.comboBairros = comboBairros;
	}

	public List<SelectItem> getComboTipoImovel() {
		return comboTipoImovel;
	}

	public void setComboTipoImovel(List<SelectItem> comboTipoImovel) {
		this.comboTipoImovel = comboTipoImovel;
	}

	public String getTipoOrdenacao() {
		return tipoOrdenacao;
	}

	public void setTipoOrdenacao(String tipoOrdenacao) {
		this.tipoOrdenacao = tipoOrdenacao;
	}

}