package com.LFSotfware.PoltaliaEletlonica.Model.Repositorio.Configuracao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManeger {

	private static final String diretorio = "target/banco.db";

	private static final String url = "jdbc:sqlite:" + diretorio;
	private static final String user = "sem usuario";
	private static final String senha = "sem senha";

	private static Connection conexao = null;

	public static Connection getConexaoAtual() throws SQLException {

		File file = new File(diretorio);

		if (conexao == null && file.exists() == true) {

			conexao = instanciarConexao(conexao);
		} else if (conexao == null && file.exists() == false) {
			conexao = instanciarConexao(conexao);

			criarBanco(conexao);
		}

		return conexao;
	}

	private static Connection instanciarConexao(Connection conexao) {
		try {

			Class.forName("org.sqlite.JDBC");

			conexao = DriverManager.getConnection(url, user, senha);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conexao;
	}

	private static void criarBanco(Connection conexao) throws SQLException {

		String query = "CREATE TABLE funcionario(\n"
				+ "idfunci INTEGER PRIMARY KEY AUTOINCREMENT, \n"
				+ "nome TEXT NOT NULL, \n"
				+ "cargo TEXT NOT NULL, \n"
				+ "urlPerfil TEXT NOT NULL, \n"
				+ "entrou BOOLEAN);\n";
	
		conexao.prepareStatement(query).execute();
		
		System.out.println(query);
		
		query = "CREATE TABLE controle(\n"
				+ "idcontrole INTEGER PRIMARY KEY AUTOINCREMENT, \n"
				+ "tipo TEXT NOT NULL, \n"
				+ "datahora TIMESTAMP NOT NULL, \n"
				+ "idfunci INTEGER, \n"
				+ "FOREIGN KEY (idfunci) REFERENCES funcionario(idfunci) ON DELETE CASCADE);\n";
		
		conexao.prepareStatement(query).execute();
		System.out.println(query);
		
		
		
	}
}
