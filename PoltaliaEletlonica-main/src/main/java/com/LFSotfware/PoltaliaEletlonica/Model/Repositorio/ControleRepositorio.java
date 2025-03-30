package com.LFSotfware.PoltaliaEletlonica.Model.Repositorio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.LFSotfware.PoltaliaEletlonica.Controller.DTO.Card;
import com.LFSotfware.PoltaliaEletlonica.Controller.DTO.DadosEntradaDTO;
import com.LFSotfware.PoltaliaEletlonica.Model.Entidade.ControleEntrada;
import com.LFSotfware.PoltaliaEletlonica.Model.Repositorio.Configuracao.ConnectionManeger;

@Repository
public class ControleRepositorio implements Repositorio<ControleEntrada, DadosEntradaDTO> {

	@Override
	public void inserir(ControleEntrada item) throws SQLException {

	}

	@Override
	public void remover(ControleEntrada item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void alterar(ControleEntrada item) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public ControleEntrada ler(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void inserir(ControleEntrada item, Long id) throws SQLException {
		String query = "INSERT INTO controle (tipo, datahora, idfunci) values(?,?,?);";

		PreparedStatement pstm = ConnectionManeger.getConexaoAtual().prepareStatement(query);

		pstm.setString(1, item.getTipo());
		System.out.println("data " + item.getData());
		System.out.println("timestemp " + Timestamp.valueOf(item.getData()));

		pstm.setTimestamp(2, Timestamp.valueOf(item.getData()) );
		pstm.setLong(3, id);

		pstm.execute();

	}

	@Override
	public List<DadosEntradaDTO> listar(long idfunci) throws SQLException {
		String query = "select * from controle where idfunci="+idfunci+" ;";
		
		ResultSet resultado  = ConnectionManeger.getConexaoAtual().prepareStatement(query).executeQuery();
		
		List<DadosEntradaDTO> lista = new ArrayList<DadosEntradaDTO>();
		
		long indice = 1;
		while (resultado.next()) {
			
			Timestamp tempo = resultado.getTimestamp("datahora");
			
			ControleEntrada controle = new ControleEntrada();
			controle.setData(tempo.toLocalDateTime());
			
			DadosEntradaDTO dto = new DadosEntradaDTO(
					indice,
					resultado.getString("tipo"),
					controle.getDataFormatada(),
					controle.getHoraFormatada());
			
			lista.add(dto);
			indice++;
			
		}

		return lista;
	}

	@Override
	public List<DadosEntradaDTO> listar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
