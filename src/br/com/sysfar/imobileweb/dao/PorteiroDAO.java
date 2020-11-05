package br.com.sysfar.imobileweb.dao;

import java.util.List;

import br.com.sysfar.imobileweb.model.PorteiroModel;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class PorteiroDAO {

	public void inserir(PorteiroModel porteiroModel) throws TSApplicationException {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		porteiroModel.setId(broker.getSequenceNextValue("porteiro_id_seq"));

		broker.setSQL("INSERT INTO PORTEIRO (ID, NOME, FLAG_ATIVO) VALUES (?, ?, ?)", porteiroModel.getId(), porteiroModel.getNome(), porteiroModel.getFlagAtivo());

		broker.execute();

	}
	
	public List<PorteiroModel> pesquisar(PorteiroModel porteiroModel) {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID, NOME, FLAG_ATIVO FROM PORTEIRO WHERE NOME ILIKE ?", porteiroModel.getNome());

		return broker.getCollectionBean(PorteiroModel.class, "id", "nome", "flagAtivo");
		
	}
	
	public void excluir(PorteiroModel porteiroModel) throws TSApplicationException {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("DELETE FROM PORTEIRO WHERE ID = ?", porteiroModel.getId());

		broker.execute();

	}
	
	public void alterar(PorteiroModel porteiroModel) throws TSApplicationException {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setSQL("UPDATE PORTEIRO SET NOME = ?, FLAG_ATIVO = ? WHERE ID = ?", porteiroModel.getNome(), porteiroModel.getFlagAtivo(), porteiroModel.getId());
		
		broker.execute();
		
	}
}
