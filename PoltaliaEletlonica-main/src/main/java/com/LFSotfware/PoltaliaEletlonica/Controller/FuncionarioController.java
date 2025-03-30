package com.LFSotfware.PoltaliaEletlonica.Controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LFSotfware.PoltaliaEletlonica.Controller.DTO.Card;
import com.LFSotfware.PoltaliaEletlonica.Controller.DTO.DadosEntradaDTO;
import com.LFSotfware.PoltaliaEletlonica.Controller.DTO.FuncionarioDTO;
import com.LFSotfware.PoltaliaEletlonica.Model.Service.FuncionarioService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/funcionario")
public class FuncionarioController {

	@Autowired
	private FuncionarioService service;

	public FuncionarioController() {
	}

	@PostMapping(value = "/cadastrar")
	public ResponseEntity<Void> CadastrarFuncionario(@RequestBody FuncionarioDTO funcionario) {

		try {
			service.cadastrar(funcionario);

		} catch (IllegalArgumentException e) {
			return ResponseEntity.unprocessableEntity().build();
		} catch (SQLException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}

		return ResponseEntity.ok().build();
	}

	@PutMapping (value = "/{idfunci}/definir/entrada")
	public ResponseEntity<Void> definirEntrada(@PathVariable Long idfunci) {
		try {
			service.definirEntrada(idfunci);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.unprocessableEntity().build();
		} catch (SQLException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().build();
	}

	@PutMapping (value = "/{idfunci}/definir/saida")
	public ResponseEntity<Void> definirSaida(@PathVariable Long idfunci) {

		try {
			service.definirSaida(idfunci);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.unprocessableEntity().build();
		} catch (SQLException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/listar")
	public ResponseEntity<List<Card>> listarFuncionarios() {

		List<Card> cards = null;
		try {
			cards = service.listarfuncionarios();

		} catch (SQLException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.ok(cards);

	}
	
	@GetMapping(value = "/{id}/dados")
	public ResponseEntity<List<DadosEntradaDTO>> listarDatas(@PathVariable Long id){
		
		List<DadosEntradaDTO> lista;
		
		try {
			lista = service.listarDatas(id);

		} catch (IllegalArgumentException e) {
			return ResponseEntity.unprocessableEntity().build();
		} catch (SQLException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		return  ResponseEntity.ok(lista);
	}

}
