package br.com.sysfar.imobileweb.util;

import br.com.sysfar.imobileweb.model.CaptacaoModel;
import br.com.topsys.util.TSParseUtil;
import br.com.topsys.util.TSUtil;

public class LayoutEmailUtil {

	public String getLayoutEmailNovaCaptacao(CaptacaoModel model){
		
		StringBuilder texto = new StringBuilder();
		
		texto.append("Olá ").append(model.getResponsavelModel().getNome()).append(",<br/><br/>");
		texto.append("Você possui uma nova captação em ").append(model.getBairroModel().getDescricao()).append(".");
		
		texto.append("<br/><br/><b>Detalhes da captação:</b>");
		
		if(!TSUtil.isEmpty(model.getDataAnuncio())){
			texto.append("<br/><br/>Data do anúncio: ").append(TSParseUtil.dateToString(model.getDataAnuncio()));
		}
		
		if(!TSUtil.isEmpty(model.getTipoImovelModel().getId())){
			texto.append("<br/>Tipo: ").append(model.getTipoImovelModel().getDescricao());
		}
		
		if(!TSUtil.isEmpty(model.getOrigemModel().getId())){
			texto.append("<br/>Origem: ").append(model.getOrigemModel().getDescricao());
		}
		
		if(!TSUtil.isEmpty(model.getValor())){
			texto.append("<br/>Valor: ").append(TSParseUtil.doubleToString(model.getValor()));
		}
		
		if(!TSUtil.isEmpty(model.getLink())){
			texto.append("<br/>Link: ").append("<a href='").append(model.getLink()).append("'>").append(model.getLink()).append("</a>");
		}
		
		if(!TSUtil.isEmpty(model.getDescricao())){
			texto.append("<br/>Descrição: ").append(model.getDescricao());
		}
		
		texto.append("<br/><br/>Para saber mais sobre sua nova captação acesse o sistema ImobileWeb <a href='http://www.sistemaimobileweb.com.br/imobileweb/pages/login.xhtml'>CLICANDO AQUI</a>");
		texto.append("<br/><br/><br/>Atenciosamente, ");
		texto.append("<br/><br/>Equipe Imobile Web - Gestão Imobiliária");
		
		return texto.toString();
		
	}
}
