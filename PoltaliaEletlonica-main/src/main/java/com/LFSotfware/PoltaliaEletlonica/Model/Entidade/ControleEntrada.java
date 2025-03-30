package com.LFSotfware.PoltaliaEletlonica.Model.Entidade;

import java.time.LocalDateTime;

public class ControleEntrada {

	private String tipo;
	private LocalDateTime data;
	
	public ControleEntrada() {
		this.setData(LocalDateTime.now());
	}

	public LocalDateTime getData() {
		return data;
	}
	public String getDataFormatada() {
		
		String dataformatada ="";
		
		dataformatada += this.data.getDayOfMonth() +"/";
		
		int mes = data.getMonthValue();
		
		if(mes < 10) dataformatada += "0";
		
		dataformatada += mes +"/";
		dataformatada += data.getYear();
		
		return dataformatada;
	}
	
	public String getHoraFormatada() {
		
		String horaFormatada = "";
		
		horaFormatada += data.getHour() + ":";
		horaFormatada += data.getMinute() + ":";
		horaFormatada += data.getSecond();
		
		return horaFormatada;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
