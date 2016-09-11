package br.com.sysfar.imobileweb.faces;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

import br.com.sysfar.imobileweb.dao.ClienteDAO;
import br.com.sysfar.imobileweb.dao.ComboDAO;
import br.com.sysfar.imobileweb.dao.CrudDAO;
import br.com.sysfar.imobileweb.dao.EdificioDAO;
import br.com.sysfar.imobileweb.dao.ImovelDAO;
import br.com.sysfar.imobileweb.dao.ProprietarioDAO;
import br.com.sysfar.imobileweb.dao.UsuarioDAO;
import br.com.sysfar.imobileweb.model.BairroModel;
import br.com.sysfar.imobileweb.model.CaptacaoContatoModel;
import br.com.sysfar.imobileweb.model.CaptacaoModel;
import br.com.sysfar.imobileweb.model.ClienteModel;
import br.com.sysfar.imobileweb.model.CondominioModel;
import br.com.sysfar.imobileweb.model.ConstrutoraModel;
import br.com.sysfar.imobileweb.model.EdificioModel;
import br.com.sysfar.imobileweb.model.ImovelAtualizacaoModel;
import br.com.sysfar.imobileweb.model.ImovelFotoModel;
import br.com.sysfar.imobileweb.model.ImovelModel;
import br.com.sysfar.imobileweb.model.MenuModel;
import br.com.sysfar.imobileweb.model.OperadoraModel;
import br.com.sysfar.imobileweb.model.PosicaoSolModel;
import br.com.sysfar.imobileweb.model.ProprietarioContatoModel;
import br.com.sysfar.imobileweb.model.ProprietarioModel;
import br.com.sysfar.imobileweb.model.TipoAtualizacaoImovelModel;
import br.com.sysfar.imobileweb.model.TipoFachadaModel;
import br.com.sysfar.imobileweb.model.TipoImovelModel;
import br.com.sysfar.imobileweb.model.TipoPisoModel;
import br.com.sysfar.imobileweb.model.UsuarioModel;
import br.com.sysfar.imobileweb.util.Constantes;
import br.com.sysfar.imobileweb.util.GerenciadorCaminhoArquivoUtil;
import br.com.sysfar.imobileweb.util.Utilitario;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;

@SessionScoped
@SuppressWarnings("serial")
@ManagedBean(name = "imovelFaces")
public class ImovelFaces extends CrudFaces<ImovelModel> {

	private ImovelDAO imovelDAO;
	private ComboDAO comboDAO;
	private EdificioDAO edificioDAO;
	private ProprietarioDAO proprietarioDAO;
	private ClienteDAO clienteDAO;
	private UsuarioDAO usuarioDAO;

	private List<SelectItem> comboTipoImovel;
	private List<SelectItem> comboCondominio;
	private List<SelectItem> comboEdificio;
	private List<SelectItem> comboEdificioPesquisa;
	private List<SelectItem> comboBairro;
	private List<SelectItem> comboConstrutora;
	private List<SelectItem> comboTipoPiso;
	private List<SelectItem> comboTipoFachada;
	private List<SelectItem> comboPosicaoSol;
	private List<SelectItem> comboCaptador;
	private List<SelectItem> comboProprietario;
	private List<SelectItem> comboOperadoras;
	private List<SelectItem> comboTipoAtualizacaoImovel;

	private List<BairroModel> bairros;

	private ImovelAtualizacaoModel imovelAtualizacaoModel;
	private ImovelAtualizacaoModel imovelAtualizacaoSelecionadoModel;
	private ImovelFotoModel imovelFotoSelecionadaModel;

	@Override
	@PostConstruct
	protected void clearFields() {

		this.crudModel = new ImovelModel();
		this.crudModel.setFlagAtivo(Boolean.TRUE);
		this.crudModel.setTipoImovelModel(new TipoImovelModel());
		this.crudModel.setCondominioModel(new CondominioModel());
		this.crudModel.setEdificioModel(new EdificioModel());
		this.crudModel.getEdificioModel().setCondominioModel(new CondominioModel());
		this.crudModel.setBairroModel(new BairroModel());
		this.crudModel.setConstrutoraModel(new ConstrutoraModel());
		this.crudModel.setTipoPisoSalaModel(new TipoPisoModel());
		this.crudModel.setTipoPisoQuartoModel(new TipoPisoModel());
		this.crudModel.setTipoFachadaModel(new TipoFachadaModel());
		this.crudModel.setPosicaoSolModel(new PosicaoSolModel());
		this.crudModel.setCaptacaoModel(new CaptacaoModel());
		this.crudModel.setCaptadorModel(new UsuarioModel());
		this.crudModel.setProprietarioModel(new ProprietarioModel());
		this.crudModel.getProprietarioModel().setFlagAtivo(Boolean.TRUE);
		this.crudModel.getProprietarioModel().setContatos(new ArrayList<ProprietarioContatoModel>());
		this.crudModel.getProprietarioModel().setContatosResponsavelVenda(new ArrayList<ProprietarioContatoModel>());
		this.crudModel.setFotos(new ArrayList<ImovelFotoModel>());

		this.crudPesquisaModel = new ImovelModel();
		this.crudPesquisaModel.setFlagAtivo(Boolean.TRUE);
		this.crudPesquisaModel.setTipoImovelModel(new TipoImovelModel());
		this.crudPesquisaModel.setCondominioModel(new CondominioModel());
		this.crudPesquisaModel.setEdificioModel(new EdificioModel());
		this.crudPesquisaModel.getEdificioModel().setCondominioModel(new CondominioModel());
		this.crudPesquisaModel.setCaptadorModel(new UsuarioModel());
		this.crudPesquisaModel.setProprietarioModel(new ProprietarioModel());
		this.crudPesquisaModel.setBairrosPesquisa(new ArrayList<String>());

		this.imovelDAO = new ImovelDAO();
		this.comboDAO = new ComboDAO();
		this.proprietarioDAO = new ProprietarioDAO();
		this.clienteDAO = new ClienteDAO();
		this.edificioDAO = new EdificioDAO();
		this.usuarioDAO = new UsuarioDAO();

		this.iniciarCombos();
		this.instanciarAtualizacao();

		super.setManterCampos(true);

		CaptacaoModel captacaoModel = (CaptacaoModel) TSFacesUtil.getObjectInSession(Constantes.SESSION_CAPTACAO_ATUAL);

		if (!TSUtil.isEmpty(captacaoModel)) {

			this.gerarViaCaptacao(captacaoModel);

			TSFacesUtil.removeObjectInSession(Constantes.SESSION_CAPTACAO_ATUAL);

		}

		Long imovelId = (Long) TSFacesUtil.getObjectInSession(Constantes.SESSION_IMOVEL_ATUAL_ID);

		if (!TSUtil.isEmpty(imovelId)) {

			this.crudModel.setId(imovelId);

			this.detailEvent();

			TSFacesUtil.removeObjectInSession(Constantes.SESSION_IMOVEL_ATUAL_ID);

		}

	}

	private void gerarViaCaptacao(CaptacaoModel captacao) {

		this.crudModel.setTipoImovelModel(captacao.getTipoImovelModel());
		this.crudModel.setValor(captacao.getValor());
		this.crudModel.setBairroModel(captacao.getBairroModel());
		this.crudModel.setCaptacaoModel(captacao);
		this.crudModel.setDataCaptacao(captacao.getDataAtualizacao());
		this.crudModel.setCaptadorModel(captacao.getResponsavelModel());
		this.crudModel.getProprietarioModel().setNome(captacao.getContato());

		this.comboProprietario.add(new SelectItem(null, this.crudModel.getProprietarioModel().getNome()));

		for (CaptacaoContatoModel contato : captacao.getContatos()) {

			ProprietarioContatoModel model = this.getProprietarioContatoInstance();

			model.setTelefone(contato.getTelefone());
			model.setEmail(contato.getEmail());
			model.setOperadoraModel(contato.getOperadoraModel());

			this.crudModel.getProprietarioModel().getContatos().add(model);

		}

	}

	private void iniciarCombos() {
		this.comboTipoImovel = super.initCombo(this.comboDAO.pesquisarTipoImovel(), "id", "descricao");
		this.comboBairro = super.initCombo(this.comboDAO.pesquisarBairro(), "id", "descricao");
		this.comboConstrutora = super.initCombo(this.comboDAO.pesquisarConstrutora(), "id", "descricao");
		this.comboTipoPiso = super.initCombo(this.comboDAO.pesquisarTipoPiso(), "id", "descricao");
		this.comboTipoFachada = super.initCombo(this.comboDAO.pesquisarTipoFachada(), "id", "descricao");
		this.comboPosicaoSol = super.initCombo(this.comboDAO.pesquisarPosicaoSol(), "id", "descricao");
		this.comboCaptador = super.initCombo(this.comboDAO.pesquisarCaptador(), "id", "nome");
		this.comboTipoAtualizacaoImovel = super.initCombo(this.comboDAO.pesquisarTipoAtualizacaoImovel(), "id", "descricao");
		this.iniciarComboProprietario();
		this.comboOperadoras = super.initCombo(this.comboDAO.pesquisarOperadoras(), "id", "descricao");
		this.bairros = this.comboDAO.pesquisarBairro();
	}

	public void carregarComboEdificio() {
		this.comboEdificio = super.initCombo(this.edificioDAO.pesquisar(this.crudModel.getCondominioModel()), "id", "descricao");
	}

	public void carregarComboCondominio() {
		this.comboCondominio = super.initCombo(this.comboDAO.pesquisarCondominio(this.crudModel.getBairroModel()), "id", "descricao");
	}

	public void carregarComboEdificioPesquisa() {
		this.comboEdificioPesquisa = super.initCombo(this.edificioDAO.pesquisar(this.crudPesquisaModel.getCondominioModel()), "id", "descricao");
	}

	private void iniciarComboProprietario() {
		this.comboProprietario = super.initCombo(this.comboDAO.pesquisarProprietario(), "id", "nome");
	}

	private ProprietarioContatoModel getProprietarioContatoInstance() {
		ProprietarioContatoModel model = new ProprietarioContatoModel();
		model.setProprietarioModel(this.crudModel.getProprietarioModel());
		model.setOperadoraModel(new OperadoraModel());
		return model;
	}

	private void carregarContatosProprietario() {

		for (int i = this.crudModel.getProprietarioModel().getContatos().size(); i < 4; i++) {

			this.crudModel.getProprietarioModel().getContatos().add(this.getProprietarioContatoInstance());

		}

		for (int i = this.crudModel.getProprietarioModel().getContatosResponsavelVenda().size(); i < 4; i++) {

			this.crudModel.getProprietarioModel().getContatosResponsavelVenda().add(this.getProprietarioContatoInstance());

		}

	}

	public String carregarProprietario() {

		if (!TSUtil.isEmpty(this.crudModel.getProprietarioModel().getId())) {

			this.crudModel.setProprietarioModel(this.proprietarioDAO.obter(this.crudModel.getProprietarioModel()));

			this.crudModel.getProprietarioModel().setContatos(this.proprietarioDAO.pesquisarContatos(this.crudModel.getProprietarioModel(), false));
			this.crudModel.getProprietarioModel().setContatosResponsavelVenda(this.proprietarioDAO.pesquisarContatos(this.crudModel.getProprietarioModel(), true));

		}

		this.carregarContatosProprietario();

		return null;

	}

	public String salvarProprietario() {

		boolean valida = true;

		if (!TSUtil.isEmpty(this.crudModel.getProprietarioModel().getEmail()) && !TSUtil.isEmailValid(this.crudModel.getProprietarioModel().getEmail())) {
			valida = false;
			super.addErrorMessage("E-mail inválido");
		}

		if (!TSUtil.isEmpty(this.crudModel.getProprietarioModel().getEmailResponsavelVenda()) && !TSUtil.isEmailValid(this.crudModel.getProprietarioModel().getEmailResponsavelVenda())) {
			valida = false;
			super.addErrorMessage("E-mail do responsável pela venda inválido");
		}

		if (!valida) {
			return null;
		}

		this.crudModel.getProprietarioModel().setDataCadastro(new Date());
		this.crudModel.getProprietarioModel().setUsuarioCadastroModel(Utilitario.getUsuarioLogado());

		try {

			if (TSUtil.isEmpty(this.crudModel.getProprietarioModel().getId())) {

				this.proprietarioDAO.inserir(this.crudModel.getProprietarioModel());

			} else {

				this.proprietarioDAO.alterar(this.crudModel.getProprietarioModel());

			}

			this.iniciarComboProprietario();

			super.addInfoMessageKey(Constantes.OPERACAO_SUCESSO);

			RequestContext.getCurrentInstance().addCallbackParam("valido", true);

		} catch (TSApplicationException e) {

			super.throwException(e);

		}

		return null;
	}

	@Override
	protected boolean validaCampos() {

		boolean valida = true;

		if (TSUtil.isEmpty(this.crudModel.getId()) && TSUtil.isEmpty(this.crudModel.getProprietarioModel().getId())) {

			if (TSUtil.isEmpty(this.crudModel.getProprietarioModel().getNome())) {
				valida = false;
				super.addErrorMessage("Nome do Proprietário: Campo obrigatório");
			}

			boolean acheiContato = false;

			for (ProprietarioContatoModel contato : this.crudModel.getProprietarioModel().getContatos()) {

				if (!TSUtil.isEmpty(contato.getTelefone()) || !TSUtil.isEmpty(contato.getEmail())) {
					acheiContato = true;
				}

			}

			for (ProprietarioContatoModel contato : this.crudModel.getProprietarioModel().getContatosResponsavelVenda()) {

				if (!TSUtil.isEmpty(contato.getTelefone()) || !TSUtil.isEmpty(contato.getEmail())) {
					acheiContato = true;
				}

			}

			if (!acheiContato) {
				valida = false;
				super.addErrorMessage("Adicione pelo menos um contato ao proprietário");
			}

		}

		return valida;
	}

	@Override
	protected void preInsert() {

		if (TSUtil.isEmpty(this.crudModel.getProprietarioModel().getId())) {

			this.salvarProprietario();

		}

	}

	@Override
	protected void posInsert() {

		try {

			this.usuarioDAO.addQtdImoveis(this.crudModel.getCaptadorModel());

		} catch (TSApplicationException e) {

			super.throwException(e);

		}

	}

	@Override
	protected void prePersist() {
		this.crudModel.setDataCadastro(new Date());
		this.crudModel.setUsuarioCadastroModel(Utilitario.getUsuarioLogado());
	}

	@Override
	protected void posPersist() throws TSApplicationException {
		this.detailEvent();
	}

	@Override
	protected void posDetail() {
		this.crudModel.setClientesPerfil(this.clienteDAO.pesquisarPerfil(this.crudModel));
		this.crudModel.setAtualizacoes(this.imovelDAO.pesquisarAtualizacoes(this.crudModel));
		this.crudModel.setFotos(this.imovelDAO.pesquisarFotos(this.crudModel));
		this.carregarComboCondominio();
	}

	public String instanciarAtualizacao() {
		this.imovelAtualizacaoModel = new ImovelAtualizacaoModel();
		this.imovelAtualizacaoModel.setTipoAtualizacaoImovelModel(new TipoAtualizacaoImovelModel());
		this.imovelAtualizacaoModel.setImovelModel(this.crudModel);
		this.imovelAtualizacaoModel.setData(new Date());
		return null;
	}

	public String obterAtualizacao(ImovelAtualizacaoModel model) {

		this.imovelAtualizacaoModel = this.imovelDAO.obterAtualizacao(model);

		return null;
	}

	private boolean validaCamposAtualizacao() {

		boolean valida = true;

		if (TSUtil.isEmpty(this.imovelAtualizacaoModel.getData())) {
			valida = false;
			super.addErrorMessage("Data: Campo obrigatório");
		}

		if (TSUtil.isEmpty(this.imovelAtualizacaoModel.getTipoAtualizacaoImovelModel().getId())) {
			valida = false;
			super.addErrorMessage("Tipo: Campo obrigatório");
		}

		return valida;

	}

	public String inserirAtualizacao() {

		if (!this.validaCamposAtualizacao()) {
			return null;
		}

		this.imovelAtualizacaoModel.setDataCadastro(new Date());
		this.imovelAtualizacaoModel.setUsuarioCadastroModel(Utilitario.getUsuarioLogado());

		try {

			this.imovelDAO.inserir(this.imovelAtualizacaoModel);

			this.crudModel.setAtualizacoes(this.imovelDAO.pesquisarAtualizacoes(this.crudModel));

			super.addInfoMessageKey(Constantes.OPERACAO_SUCESSO);

			RequestContext.getCurrentInstance().addCallbackParam("valido", true);

		} catch (TSApplicationException e) {

			super.throwException(e);

		}

		return null;
	}

	public String alterarAtualizacao() {

		if (!this.validaCamposAtualizacao()) {
			return null;
		}

		try {

			this.imovelDAO.alterar(this.imovelAtualizacaoModel);

			this.crudModel.setAtualizacoes(this.imovelDAO.pesquisarAtualizacoes(this.crudModel));

			super.addInfoMessageKey(Constantes.OPERACAO_SUCESSO);

			RequestContext.getCurrentInstance().addCallbackParam("valido", true);

		} catch (TSApplicationException e) {

			super.throwException(e);

		}

		return null;
	}

	public String removeAtualizacao() {

		try {

			this.imovelDAO.excluir(this.imovelAtualizacaoSelecionadoModel);

			this.crudModel.getAtualizacoes().remove(this.imovelAtualizacaoSelecionadoModel);

			super.addInfoMessageKey(Constantes.OPERACAO_SUCESSO);

		} catch (TSApplicationException e) {

			super.throwException(e);

		}

		return null;
	}

	public String removeFoto() {

		try {

			this.imovelDAO.excluir(this.imovelFotoSelecionadaModel);

			this.crudModel.getFotos().remove(this.imovelFotoSelecionadaModel);

			super.addInfoMessageKey(Constantes.OPERACAO_SUCESSO);

		} catch (TSApplicationException e) {

			super.throwException(e);

		}

		return null;
	}

	public void enviarFoto(FileUploadEvent event) {

		ImovelFotoModel imovelFotoModel = new ImovelFotoModel();

		imovelFotoModel.setImovelModel(this.crudModel);
		imovelFotoModel.setArquivo(TSUtil.gerarId());
		imovelFotoModel.setOrdem(this.crudModel.getFotos().size());

		try {

			File imagem = new File(GerenciadorCaminhoArquivoUtil.getPastaUploadArquivo() + imovelFotoModel.getArquivo() + "." + Constantes.EXTENSAO_FOTOS);

			FileUtils.copyInputStreamToFile(event.getFile().getInputstream(), imagem);
			
			BufferedImage img = ImageIO.read(imagem);

			if (img.getWidth() > img.getHeight()) {
				imovelFotoModel.setFlagPortrait(false);
			}

			if(imovelFotoModel.getFlagPortrait()){
				
				BufferedImage imagem60x80 = Utilitario.redimensionarImagem(imagem, 60, 80);
				BufferedImage imagem150x200 = Utilitario.redimensionarImagem(imagem, 150, 200);
				
				ImageIO.write(imagem60x80, Constantes.EXTENSAO_FOTOS, new File(GerenciadorCaminhoArquivoUtil.getPastaUploadArquivo() + imovelFotoModel.getArquivo() + "_60x80." + Constantes.EXTENSAO_FOTOS));
				ImageIO.write(imagem150x200, Constantes.EXTENSAO_FOTOS, new File(GerenciadorCaminhoArquivoUtil.getPastaUploadArquivo() + imovelFotoModel.getArquivo() + "_150x200." + Constantes.EXTENSAO_FOTOS));
				
			} else {
				
				BufferedImage imagem80x60 = Utilitario.redimensionarImagem(imagem, 80, 60);
				BufferedImage imagem200x150 = Utilitario.redimensionarImagem(imagem, 200, 150);
				
				ImageIO.write(imagem80x60, Constantes.EXTENSAO_FOTOS, new File(GerenciadorCaminhoArquivoUtil.getPastaUploadArquivo() + imovelFotoModel.getArquivo() + "_80x60." + Constantes.EXTENSAO_FOTOS));
				ImageIO.write(imagem200x150, Constantes.EXTENSAO_FOTOS, new File(GerenciadorCaminhoArquivoUtil.getPastaUploadArquivo() + imovelFotoModel.getArquivo() + "_200x150." + Constantes.EXTENSAO_FOTOS));
				
			}

			this.crudModel.getFotos().add(imovelFotoModel);

		} catch (Exception ex) {

			super.addErrorMessage("Ocorreu um erro ao enviar a imagem");

		}

	}

	public String subirFoto(ImovelFotoModel foto) {
		Utilitario.ordenarListaPraCima(this.crudModel.getFotos(), foto);
		return null;
	}

	public String descerFoto(ImovelFotoModel foto) {
		Utilitario.ordenarListaPraBaixo(this.crudModel.getFotos(), foto);
		return null;
	}

	@Override
	protected CrudDAO<ImovelModel> getCrudDAO() {
		return this.imovelDAO;
	}

	public String redirecionarCliente(ClienteModel model) {

		TSFacesUtil.addObjectInSession(Constantes.SESSION_CLIENTE_ATUAL_ID, model.getId());

		TSFacesUtil.removeManagedBeanInSession(Constantes.CLIENTE_FACES);

		MenuFaces menuFaces = (MenuFaces) TSFacesUtil.getManagedBean(Constantes.MENU_FACES);

		return menuFaces.escolherMenu(new MenuModel(Constantes.MENU_CLIENTE));
	}

	public List<SelectItem> getComboTipoImovel() {
		return comboTipoImovel;
	}

	public void setComboTipoImovel(List<SelectItem> comboTipoImovel) {
		this.comboTipoImovel = comboTipoImovel;
	}

	public List<SelectItem> getComboEdificio() {
		return comboEdificio;
	}

	public void setComboEdificio(List<SelectItem> comboEdificio) {
		this.comboEdificio = comboEdificio;
	}

	public List<SelectItem> getComboBairro() {
		return comboBairro;
	}

	public void setComboBairro(List<SelectItem> comboBairro) {
		this.comboBairro = comboBairro;
	}

	public List<SelectItem> getComboConstrutora() {
		return comboConstrutora;
	}

	public void setComboConstrutora(List<SelectItem> comboConstrutora) {
		this.comboConstrutora = comboConstrutora;
	}

	public List<SelectItem> getComboTipoPiso() {
		return comboTipoPiso;
	}

	public void setComboTipoPiso(List<SelectItem> comboTipoPiso) {
		this.comboTipoPiso = comboTipoPiso;
	}

	public List<SelectItem> getComboTipoFachada() {
		return comboTipoFachada;
	}

	public void setComboTipoFachada(List<SelectItem> comboTipoFachada) {
		this.comboTipoFachada = comboTipoFachada;
	}

	public List<SelectItem> getComboPosicaoSol() {
		return comboPosicaoSol;
	}

	public void setComboPosicaoSol(List<SelectItem> comboPosicaoSol) {
		this.comboPosicaoSol = comboPosicaoSol;
	}

	public List<SelectItem> getComboCaptador() {
		return comboCaptador;
	}

	public void setComboCaptador(List<SelectItem> comboCaptador) {
		this.comboCaptador = comboCaptador;
	}

	public List<SelectItem> getComboProprietario() {
		return comboProprietario;
	}

	public void setComboProprietario(List<SelectItem> comboProprietario) {
		this.comboProprietario = comboProprietario;
	}

	public List<BairroModel> getBairros() {
		return bairros;
	}

	public void setBairros(List<BairroModel> bairros) {
		this.bairros = bairros;
	}

	public List<SelectItem> getComboOperadoras() {
		return comboOperadoras;
	}

	public void setComboOperadoras(List<SelectItem> comboOperadoras) {
		this.comboOperadoras = comboOperadoras;
	}

	public List<SelectItem> getComboCondominio() {
		return comboCondominio;
	}

	public void setComboCondominio(List<SelectItem> comboCondominio) {
		this.comboCondominio = comboCondominio;
	}

	public List<SelectItem> getComboEdificioPesquisa() {
		return comboEdificioPesquisa;
	}

	public void setComboEdificioPesquisa(List<SelectItem> comboEdificioPesquisa) {
		this.comboEdificioPesquisa = comboEdificioPesquisa;
	}

	public ImovelAtualizacaoModel getImovelAtualizacaoModel() {
		return imovelAtualizacaoModel;
	}

	public void setImovelAtualizacaoModel(ImovelAtualizacaoModel imovelAtualizacaoModel) {
		this.imovelAtualizacaoModel = imovelAtualizacaoModel;
	}

	public ImovelAtualizacaoModel getImovelAtualizacaoSelecionadoModel() {
		return imovelAtualizacaoSelecionadoModel;
	}

	public void setImovelAtualizacaoSelecionadoModel(ImovelAtualizacaoModel imovelAtualizacaoSelecionadoModel) {
		this.imovelAtualizacaoSelecionadoModel = imovelAtualizacaoSelecionadoModel;
	}

	public List<SelectItem> getComboTipoAtualizacaoImovel() {
		return comboTipoAtualizacaoImovel;
	}

	public void setComboTipoAtualizacaoImovel(List<SelectItem> comboTipoAtualizacaoImovel) {
		this.comboTipoAtualizacaoImovel = comboTipoAtualizacaoImovel;
	}

	public ImovelFotoModel getImovelFotoSelecionadaModel() {
		return imovelFotoSelecionadaModel;
	}

	public void setImovelFotoSelecionadaModel(ImovelFotoModel imovelFotoSelecionadaModel) {
		this.imovelFotoSelecionadaModel = imovelFotoSelecionadaModel;
	}

}
