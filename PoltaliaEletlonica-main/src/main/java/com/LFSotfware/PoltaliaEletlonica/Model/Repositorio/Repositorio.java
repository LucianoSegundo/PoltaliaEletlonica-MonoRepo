package com.LFSotfware.PoltaliaEletlonica.Model.Repositorio;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.LFSotfware.PoltaliaEletlonica.Controller.DTO.Card;
import com.LFSotfware.PoltaliaEletlonica.Model.Entidade.ControleEntrada;
import com.LFSotfware.PoltaliaEletlonica.Model.Entidade.Funcionario;

public interface Repositorio<T, dto> {

	  public void inserir(T item) throws SQLException;
	  public void remover(T item);
	  public void alterar(T item) throws SQLException;
	  public T ler (Long id) throws SQLException;
	  public void inserir(T item, Long id) throws SQLException;
	  public List<dto> listar() throws SQLException;
	  public List<dto> listar(long idfunci) throws SQLException;
}
