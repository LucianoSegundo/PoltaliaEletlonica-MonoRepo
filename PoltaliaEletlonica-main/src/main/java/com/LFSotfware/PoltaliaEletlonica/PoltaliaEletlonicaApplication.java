package com.LFSotfware.PoltaliaEletlonica;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.LFSotfware.PoltaliaEletlonica.Model.Repositorio.Configuracao.ConnectionManeger;

@SpringBootApplication
public class PoltaliaEletlonicaApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(PoltaliaEletlonicaApplication.class, args);
	}

}
