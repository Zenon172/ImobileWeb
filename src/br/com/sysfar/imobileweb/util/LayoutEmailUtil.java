package br.com.sysfar.imobileweb.util;

import java.util.List;

import br.com.sysfar.imobileweb.model.AtividadeModel;
import br.com.sysfar.imobileweb.model.CaptacaoModel;
import br.com.sysfar.imobileweb.model.ClienteModel;
import br.com.sysfar.imobileweb.model.ConfiguracaoModel;
import br.com.sysfar.imobileweb.model.UsuarioModel;
import br.com.topsys.util.TSDateUtil;
import br.com.topsys.util.TSParseUtil;
import br.com.topsys.util.TSUtil;

public class LayoutEmailUtil {

	public String getLayoutEmailNovaCaptacao(CaptacaoModel model){
		
		StringBuilder texto = new StringBuilder();
		
		texto.append("Olá ").append(model.getResponsavelModel().getNome()).append(",<br/><br/>");
		texto.append("Você possui uma nova captação em ").append(model.getBairroModel().getDescricao()).append(".");
		
		texto.append("<br/><br/><b>Detalhes da captação:</b><br/>");
		
		if(!TSUtil.isEmpty(model.getDataAnuncio())){
			texto.append("<br/>Data do anúncio: ").append(TSParseUtil.dateToString(model.getDataAnuncio()));
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

	public String getLayoutEmailNovaAtividade(AtividadeModel model){
		
		StringBuilder texto = new StringBuilder();
		
		texto.append("Olá ").append(model.getResponsavelModel().getNome()).append(",<br/><br/>");

		if(model.getResponsavelModel().equals(model.getUsuarioCadastroModel())){
			
			texto.append("Você possui uma nova atividade cadastrada.");
			
		} else {
			
			texto.append("Você possui uma nova atividade cadastrada por ").append(model.getUsuarioCadastroModel().getNome()).append(".");
			
		}
		
		texto.append("<br/><br/><b>Detalhes da atividade:</b><br/>");
		
		if(!TSUtil.isEmpty(model.getDataInicial())){
			texto.append("<br/>Data: ").append(TSParseUtil.dateToString(model.getDataInicial(), TSDateUtil.DD_MM_YYYY_HH_MM));
		}
		
		if(!TSUtil.isEmpty(model.getObservacao())){
			texto.append("<br/>Observação: ").append(model.getObservacao());
		}
		
		texto.append("<br/><br/>Para saber mais sobre sua nova atividade acesse o sistema ImobileWeb <a href='http://www.sistemaimobileweb.com.br/imobileweb/pages/login.xhtml'>CLICANDO AQUI</a>");
		texto.append("<br/><br/><br/>Atenciosamente, ");
		texto.append("<br/><br/>Equipe Imobile Web - Gestão Imobiliária");
		
		return texto.toString();
		
	}
	
	public String getLayoutEmailPendenciasClientes(UsuarioModel usuario, List<ClienteModel> clientesPendentesContato, ConfiguracaoModel configuracao){
		
		StringBuilder texto = new StringBuilder();
		
		texto.append("Atenção ").append(usuario.getNome()).append(", <br/><br/>");
		
		texto.append("Alguns clientes estão sem contato a mais de ").append(configuracao.getValor()).append(" dias, por favor, verifique suas pendências<br/><br/>");
		
		texto.append("<table border='1px'>");
		texto.append("<tr>");
		texto.append("<th>");
		texto.append("Cliente");
		texto.append("</th>");
		texto.append("<th>");
		texto.append("Telefone");
		texto.append("</th>");
		texto.append("<th>");
		texto.append("Último Contato");
		texto.append("</th>");
		texto.append("</tr>");
		
		for(ClienteModel cliente : clientesPendentesContato){
			
			texto.append("<tr>");
			texto.append("<td>");
			texto.append(cliente.getNome());
			texto.append("</td>");
			texto.append("<td>");
			texto.append(cliente.getTelefones());
			texto.append("</td>");
			texto.append("<td>");
			texto.append(TSParseUtil.dateToString(cliente.getDataUltimoContato(), TSDateUtil.DD_MM_YYYY_HH_MM));
			texto.append("</td>");
			texto.append("</tr>");
			
		}
		
		texto.append("</table>");
		
		return texto.toString();
		
	}
}
