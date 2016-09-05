package br.com.sysfar.imobileweb.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.text.MaskFormatter;

import br.com.sysfar.imobileweb.model.ImovelFotoModel;
import br.com.sysfar.imobileweb.model.UsuarioModel;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;

public class Utilitario {

	public static String getStringIlike(String campo, boolean percentDuplo) {
		return TSUtil.isEmpty(campo) ? null : percentDuplo ? "%" + campo.trim() + "%" : campo.trim() + "%";
	}

	public static UsuarioModel getUsuarioLogado() {
		UsuarioModel usuario = null;
		try {
			usuario = (UsuarioModel) TSFacesUtil.getObjectInSession(Constantes.SESSION_USUARIO_LOGADO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

	public static Date getDataOperacaoMinutos(int qtdMinutos) {

		Calendar c = Calendar.getInstance();

		c.add(Calendar.MINUTE, qtdMinutos);

		return c.getTime();
	}

	public static Date getDataOperacaoMinutos(Date data, int qtdMinutos) {

		Calendar c = Calendar.getInstance();

		c.setTime(data);

		c.add(Calendar.MINUTE, qtdMinutos);

		return c.getTime();
	}

	public static Date getDataOperacaoDia(int qtdDias) {

		Calendar c = Calendar.getInstance();

		c.add(Calendar.DAY_OF_MONTH, qtdDias);

		return c.getTime();
	}

	public static Date getDataOperacaoDia(Date data, int qtdDias) {

		Calendar c = Calendar.getInstance();

		c.setTime(data);

		c.add(Calendar.DAY_OF_MONTH, qtdDias);

		return c.getTime();
	}

	public static Date getDataOperacaoMes(int qtdMeses) {

		Calendar c = Calendar.getInstance();

		c.add(Calendar.MONTH, qtdMeses);

		return c.getTime();

	}
	
	public static boolean isPeriodoInvalido(Date dataInicial, Date dataFinal) {
		return dataFinal.before(dataInicial);
	}
	
	public static String lpad(String valueToPad, String filler, int size) {
		StringBuilder builder = new StringBuilder();

		if (valueToPad == null) {
			valueToPad = "";
		}

		while (builder.length() + valueToPad.length() < size) {
			builder.append(filler);
		}
		builder.append(valueToPad);
		return builder.toString();
	}
	
	public static String format(String campo, String pattern) {
		
		MaskFormatter mask;
		
		try {
			
			mask = new MaskFormatter(pattern);
				
			mask.setValueContainsLiteralCharacters(false);
			
			campo = mask.valueToString(campo);
			
		} catch (ParseException e) {
			
			e.printStackTrace();
			
		}
		
		return campo;
		
	}
	
	public static String getTelefoneFormatado9digito(String telefone) {
		
		if(TSUtil.removerNaoDigitos(telefone).length() > 10){
		
			return format(TSUtil.removerNaoDigitos(telefone), "(##) #####-####");
			
		} else {
		
			return format(TSUtil.removerNaoDigitos(telefone), "(##) ####-####");
		
		}
			
	}
	
	public static void ordenarListaPraCima(List<ImovelFotoModel> lista, ImovelFotoModel item) {

		int posicao = lista.indexOf(item);

		if (posicao > 0) {

			lista.remove(posicao);
			lista.add(posicao - 1, item);

		}

	}

	public static void ordenarListaPraBaixo(List<ImovelFotoModel> lista, ImovelFotoModel item) {

		int posicao = lista.indexOf(item);

		if (posicao < lista.size() - 1) {

			lista.remove(posicao);
			lista.add(posicao + 1, item);

		}

	}

}
