package com.LFSotfware.PoltaliaEletlonica.Model.Service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.LFSotfware.PoltaliaEletlonica.Controller.DTO.FuncionarioDTO;
import com.LFSotfware.PoltaliaEletlonica.Controller.DTO.Card;
import com.LFSotfware.PoltaliaEletlonica.Controller.DTO.DadosEntradaDTO;
import com.LFSotfware.PoltaliaEletlonica.Model.Entidade.ControleEntrada;
import com.LFSotfware.PoltaliaEletlonica.Model.Entidade.Funcionario;
import com.LFSotfware.PoltaliaEletlonica.Model.Repositorio.Fachada;

@Service
public class FuncionarioService {

	@Autowired
	private Fachada fachada;

	public FuncionarioService() {
	}

	public void cadastrar(FuncionarioDTO funcionario) throws SQLException {
		if (funcionario.nome() == null || funcionario.nome().isBlank())
			throw new IllegalArgumentException();
		if (funcionario.cargo() == null || funcionario.cargo().isBlank())
			throw new IllegalArgumentException();
		if (funcionario.urlPerfil() == null || funcionario.urlPerfil().isBlank())
			throw new IllegalArgumentException();

		Funcionario funcio = new Funcionario();
		funcio.setNome(funcionario.nome());
		funcio.setCargo(funcionario.cargo());
		funcio.setUrlPerfil(funcionario.urlPerfil());
		funcio.setEntrou(false);

		fachada.cadastrarFuncionario(funcio);
	}

	public void definirSaida(Long idfunci) throws SQLException {

		if (idfunci == null)
			throw new IllegalArgumentException();

		ControleEntrada controle = new ControleEntrada();
		controle.setTipo("SAIDA");

		fachada.AdicionaControle(controle, idfunci);
		
		fachada.atualizarStatusEntrada(false, idfunci);

	}

	public void definirEntrada(Long idfunci) throws SQLException {

		if (idfunci == null)
			throw new IllegalArgumentException();

		ControleEntrada controle = new ControleEntrada();
		controle.setTipo("ENTRADA");

		fachada.AdicionaControle(controle, idfunci);

		fachada.atualizarStatusEntrada(true, idfunci);
	}

	public List<Card> listarfuncionarios() throws SQLException {

		return fachada.listarfuncionarios();
	}

	public List<DadosEntradaDTO> listarDatas(Long idfunci) throws SQLException {
		if (idfunci == null)
			throw new IllegalArgumentException();
		
		return fachada.listarDatas(idfunci); 
	}

}
