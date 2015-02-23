package br.com.sysfar.imobileweb.model;

import java.util.Date;
import java.util.List;

import br.com.topsys.util.TSUtil;

@SuppressWarnings("serial")
public class CaptacaoModel extends BaseModel {

	private TipoImovelModel tipoImovelModel;

	private String descricao;

	private Double valor;

	private BairroModel bairroModel;

	private OrigemModel origemModel;

	private String contato;

	private Date dataAnuncio;

	private String link;

	private StatusCaptacaoModel statusCaptacaoModel;

	private String observacao;
	
	private UsuarioModel responsavelModel;
	
	private List<CaptacaoContatoModel> contatos;
	
	private ImovelModel imovelGeradoModel;
	
	private List<AtividadeModel> atividades;
	
	private Date dataInicial;
	private Date dataFinal;

	public TipoImovelModel getTipoImovelModel() {
		return tipoImovelModel;
	}

	public void setTipoImovelModel(TipoImovelModel tipoImovelModel) {
		this.tipoImovelModel = tipoImovelModel;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public BairroModel getBairroModel() {
		return bairroModel;
	}

	public void setBairroModel(BairroModel bairroModel) {
		this.bairroModel = bairroModel;
	}

	public OrigemModel getOrigemModel() {
		return origemModel;
	}

	public void setOrigemModel(OrigemModel origemModel) {
		this.origemModel = origemModel;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public Date getDataAnuncio() {
		return dataAnuncio;
	}

	public void setDataAnuncio(Date dataAnuncio) {
		this.dataAnuncio = dataAnuncio;
	}

	public String getLink() {
		return TSUtil.tratarString(link);
	}

	public void setLink(String link) {
		this.link = link;
	}

	public StatusCaptacaoModel getStatusCaptacaoModel() {
		return statusCaptacaoModel;
	}

	public void setStatusCaptacaoModel(StatusCaptacaoModel statusCaptacaoModel) {
		this.statusCaptacaoModel = statusCaptacaoModel;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<CaptacaoContatoModel> getContatos() {
		return contatos;
	}

	public void setContatos(List<CaptacaoContatoModel> contatos) {
		this.contatos = contatos;
	}

	public UsuarioModel getResponsavelModel() {
		return responsavelModel;
	}

	public void setResponsavelModel(UsuarioModel responsavelModel) {
		this.responsavelModel = responsavelModel;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public ImovelModel getImovelGeradoModel() {
		return imovelGeradoModel;
	}

	public void setImovelGeradoModel(ImovelModel imovelGeradoModel) {
		this.imovelGeradoModel = imovelGeradoModel;
	}

	public List<AtividadeModel> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<AtividadeModel> atividades) {
		this.atividades = atividades;
	}
}
