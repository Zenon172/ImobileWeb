package br.com.sysfar.imobileweb.dao;

import java.util.List;

import br.com.sysfar.imobileweb.model.UsuarioModel;
import br.com.sysfar.imobileweb.util.Utilitario;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public final class UsuarioDAO implements CrudDAO<UsuarioModel> {

	public UsuarioModel obter(final UsuarioModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, CODIGO, NOME, EMAIL, LOGIN, SENHA, GRUPO_ID, (SELECT DESCRICAO FROM GRUPO G WHERE G.ID = GRUPO_ID), FLAG_ATIVO, QTD_IMOVEIS FROM USUARIO WHERE ID = ?", model.getId());

		return (UsuarioModel) broker.getObjectBean(UsuarioModel.class, "id", "codigo", "nome", "email", "login", "senha", "grupoModel.id", "grupoModel.descricao", "flagAtivo", "qtdImoveis");
	}

	@SuppressWarnings("unchecked")
	public List<UsuarioModel> pesquisar(final UsuarioModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, NOME, EMAIL, LOGIN, SENHA, GRUPO_ID, (SELECT DESCRICAO FROM GRUPO G WHERE G.ID = GRUPO_ID) FROM USUARIO U WHERE SEM_ACENTOS(NOME) ILIKE SEM_ACENTOS(COALESCE(?, NOME)) AND U.FLAG_ATIVO = ? ORDER BY U.NOME", Utilitario.getStringIlike(model.getNome(), true), model.getFlagAtivo());

		return broker.getCollectionBean(UsuarioModel.class, "id", "nome", "email", "login", "senha", "grupoModel.id", "grupoModel.descricao");
	}
	
	public UsuarioModel inserir(final UsuarioModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("usuario_id_seq"));

		broker.setSQL("INSERT INTO USUARIO (ID, CODIGO, NOME, EMAIL, LOGIN, SENHA, GRUPO_ID, FLAG_ATIVO, QTD_IMOVEIS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", model.getId(), model.getCodigo(), model.getNome(), model.getEmail(), model.getLogin(), model.getSenha(), model.getGrupoModel().getId(), model.getFlagAtivo(), model.getQtdImoveis());

		broker.execute();

		return model;
	}

	public UsuarioModel alterar(final UsuarioModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("UPDATE USUARIO SET NOME = ?, CODIGO = ?, EMAIL = ?, LOGIN = ?, SENHA = ?, GRUPO_ID = ?, FLAG_ATIVO = ?, QTD_IMOVEIS = ? WHERE ID = ?", model.getNome(), model.getCodigo(), model.getEmail(), model.getLogin(), model.getSenha(), model.getGrupoModel().getId(), model.getFlagAtivo(), model.getQtdImoveis(), model.getId());

		broker.execute();

		return model;
	}

	public UsuarioModel excluir(final UsuarioModel model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("DELETE FROM USUARIO WHERE ID = ?", model.getId());

		broker.execute();

		return model;
	}

	public UsuarioModel obterPorLogin(UsuarioModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT U.ID, U.NOME, U.LOGIN, U.SENHA, U.FLAG_ADMINISTRADOR, U.GRUPO_ID, (SELECT G.DESCRICAO FROM GRUPO G WHERE G.ID = U.GRUPO_ID), (SELECT G.MENU_INICIAL_ID FROM GRUPO G WHERE G.ID = U.GRUPO_ID) FROM USUARIO U WHERE U.LOGIN = ?", model.getLogin());

		return (UsuarioModel) broker.getObjectBean(UsuarioModel.class, "id", "nome", "login", "senha", "flagAdministrador", "grupoModel.id", "grupoModel.descricao", "grupoModel.menuInicialModel.id");
	}

	public void alterarSenha(UsuarioModel model, String novaSenha) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("UPDATE USUARIO SET SENHA = ? WHERE LOGIN = ?", novaSenha, model.getLogin());

		broker.execute();
	}
	
	public void addQtdImoveis(UsuarioModel model) throws TSApplicationException {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("UPDATE USUARIO SET QTD_IMOVEIS = QTD_IMOVEIS + 1 WHERE ID = ?", model.getId());
		
		broker.execute();
	}

}
