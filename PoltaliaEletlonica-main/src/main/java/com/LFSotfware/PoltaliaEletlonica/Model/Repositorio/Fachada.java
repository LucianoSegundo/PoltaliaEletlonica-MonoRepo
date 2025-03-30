package com.LFSotfware.PoltaliaEletlonica.Model.Repositorio;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LFSotfware.PoltaliaEletlonica.Controller.DTO.Card;
import com.LFSotfware.PoltaliaEletlonica.Controller.DTO.DadosEntradaDTO;
import com.LFSotfware.PoltaliaEletlonica.Model.Entidade.ControleEntrada;
import com.LFSotfware.PoltaliaEletlonica.Model.Entidade.Funcionario;

import jakarta.persistence.EntityNotFoundException;

@Service
public class Fachada {

	@Autowired
	private Repositorio<Funcionario, Card> repositorioFuncionario;
	
	@Autowired
	private Repositorio<ControleEntrada, DadosEntradaDTO> repositorioControle;

	public Fachada() {
	}

	public void cadastrarFuncionario(Funcionario funcio) throws SQLException {
		repositorioFuncionario.inserir(funcio);

	}

	public void AdicionaControle(ControleEntrada controle, Long idfunci) throws SQLException {
		Funcionario funcio = repositorioFuncionario.ler(idfunci);
		if(funcio == null) throw new EntityNotFoundException();
			
		repositorioControle.inserir(controle, idfunci);

	}

	public List<Card> listarfuncionarios() throws SQLException {
	
		return repositorioFuncionario.listar();
	}

	public void atualizarStatusEntrada(boolean b, Long idfunci) throws SQLException {
		Funcionario f = new Funcionario();
		f.setEntrou(b);
		f.setId(idfunci);
		repositorioFuncionario.alterar(f);
		
	}

	public List<DadosEntradaDTO> listarDatas(Long idfunci) throws SQLException {
		Funcionario funcio = repositorioFuncionario.ler(idfunci);
		if(funcio == null) throw new EntityNotFoundException();
		
		return repositorioControle.listar(idfunci);
	}

}
