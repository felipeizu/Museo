package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import connection.ConnectionImpl;
import connection.GenericConnection;
import exception.GenericException;
import model.Obra;

public class PesquisaDaoImpl implements PesquisaDao {
	private Connection c;

	public PesquisaDaoImpl() {
		GenericConnection gc = new ConnectionImpl();
		c = gc.getConnection();
	}


	public List<Obra> pesquisaPorTitulo(String titulo) throws GenericException, SQLException {
		List<Obra> lista = new ArrayList<Obra>();
		ObraDaoImpl lDao = new ObraDaoImpl();
		
		String query = "SELECT * FROM obra WHERE titulo like ?";

		PreparedStatement ps = c.prepareStatement(query);
		ps.setString(1, "%" + titulo + "%");

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			Obra ob = new Obra();
			ob.setId(rs.getInt("id"));
			ob.setAutor(lDao.pesquisaInnerAutor(ob));
			ob.setCategoria(rs.getString("categoria"));
			ob.setDimensoes(rs.getString("dimensoes"));
			ob.setTitulo(rs.getString("titulo"));
			ob.setAno(rs.getInt("ano"));
			ob.setDescricao(rs.getString("descricao"));
			ob.setImagem(rs.getString("imagem"));

			lista.add(ob);
		}
		ps.close();

		return lista;
	}

	

	/**
	 * Pesquisa obras a partir de uma editora
	 * @param editora
	 * @return
	 * @throws GenericException
	 * @throws SQLException
	 */

	
	public List<Obra> pesquisaPorAutor(String autor) throws GenericException, SQLException {
		List<Obra> lista = new ArrayList<Obra>();
		ObraDaoImpl lDao = new ObraDaoImpl();
		
		String query = "select * from v_pesquisaPorAutor where nome like ?";
		PreparedStatement ps = c.prepareStatement(query);
		ps.setString(1, "%" + autor + "%");
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			Obra ob = new Obra();
			ob.setId(rs.getInt("id"));
			ob.setAutor(lDao.pesquisaInnerAutor(ob));
			ob.setCategoria(rs.getString("categoria"));
			ob.setDimensoes(rs.getString("dimensoes"));
			ob.setTitulo(rs.getString("titulo"));
			ob.setAno(rs.getInt("ano"));
			ob.setDescricao(rs.getString("descricao"));
			ob.setImagem(rs.getString("imagem"));
			
			lista.add(ob);
		}
		ps.close();
		return lista;
	}
	

}
