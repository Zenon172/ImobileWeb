package br.com.sysfar.imobileweb.dao;

import java.util.List;

import br.com.topsys.exception.TSApplicationException;

public interface CrudDAO<T> {

	public T obter(final T model);

	public List<T> pesquisar(final T model);

	public T inserir(final T model) throws TSApplicationException;

	public T alterar(final T model) throws TSApplicationException;

	public T excluir(final T model) throws TSApplicationException;
}
