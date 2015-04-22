package br.com.sysfar.imobileweb.util;

import java.util.Calendar;
import java.util.Date;

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

}
