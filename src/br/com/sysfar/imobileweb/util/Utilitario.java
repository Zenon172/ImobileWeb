package br.com.sysfar.imobileweb.util;

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
	
}
