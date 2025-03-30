package com.LFSotfware.PoltaliaEletlonica.Model.Repositorio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.LFSotfware.PoltaliaEletlonica.Controller.DTO.Card;
import com.LFSotfware.PoltaliaEletlonica.Model.Entidade.Funcionario;
import com.LFSotfware.PoltaliaEletlonica.Model.Repositorio.Configuracao.ConnectionManeger;

@Repository
public class FuncionarioRepositorio implements Repositorio<Funcionario, Card> {

	@Override
	public void inserir(Funcionario item) throws SQLException {
		String query = "INSERT INTO funcionario (nome, cargo, urlPerfil, entrou) values(?,?,?,?);";
		
		PreparedStatement pstm = ConnectionManeger.getConexaoAtual().prepareStatement(query);
		
		pstm.setString(1, item.getNome());
		pstm.setString(2, item.getCargo());
		pstm.setString(3, item.getUrlPerfil());
		pstm.setBoolean(4, item.isEntrou());
		
		pstm.execute();
	}

	@Override
	public void remover(Funcionario item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alterar(Funcionario item) throws SQLException {
	
		String query = "UPDATE funcionario SET entrou=? WHERE idfunci =?;";
		
		PreparedStatement pstm = ConnectionManeger.getConexaoAtual().prepareStatement(query);
		
		pstm.setBoolean(1, item.isEntrou());
		pstm.setLong(2, item.getId());
		
		pstm.execute();
		
	}

	@Override
	public Funcionario ler(Long id) throws SQLException {
		String query = "select * from funcionario where idfunci =" +id+ ";";

		ResultSet resultado = ConnectionManeger.getConexaoAtual().prepareStatement(query).executeQuery();

		Funcionario resposta = null;

		if (resultado.next()) {
			resposta = new Funcionario();
			
			resposta.setId(resultado.getLong("idfunci"));
			resposta.setNome(resultado.getString("nome"));
			resposta.setCargo(resultado.getString("cargo"));
			resposta.setUrlPerfil(resultado.getString("urlPerfil"));
			resposta.setEntrou(resultado.getBoolean("entrou"));
		}

		return resposta;
	}

	@Override
	public void inserir(Funcionario controle, Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Card> listar() throws SQLException {
		String query = "select * from funcionario;";
		
		ResultSet resultado  = ConnectionManeger.getConexaoAtual().prepareStatement(query).executeQuery();
		
		List<Card> lista = new ArrayList<Card>();
		
		while (resultado.next()) {
			Card dto = new Card(
					resultado.getLong("idfunci"),
					resultado.getString("nome"),
					resultado.getString("cargo"),
					resultado.getString("urlPerfil"),
					resultado.getBoolean("entrou")
					);
			
			lista.add(dto);
			
		}
		
		

		return lista;
	}

	@Override
	public List<Card> listar(long idfunci) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
