package com.LFSotfware.PoltaliaEletlonica.Model.Entidade;

import java.util.ArrayList;
import java.util.List;

public class Funcionario {

	private Long id;
	private String nome;
	private String cargo;
	private String  urlPerfil;
	private boolean entrou;
	private List<ControleEntrada> controleEntrada;
	
	public Funcionario() {
		this.controleEntrada = new ArrayList<ControleEntrada>();
	}
	
	public void inserirControle(ControleEntrada controle ) {
		this.controleEntrada.add(controle);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	
	public String getUrlPerfil() {
		return urlPerfil;
	}

	public void setUrlPerfil(String urlPerfil) {
		this.urlPerfil = urlPerfil;
	}

	public boolean isEntrou() {
		return entrou;
	}

	public void setEntrou(boolean entrou) {
		this.entrou = entrou;
	}

	public List<ControleEntrada> getControleEntrada() {
		return controleEntrada;
	}

	public void setControleEntrada(List<ControleEntrada> controleEntrada) {
		this.controleEntrada = controleEntrada;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

}
