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
import model.Autor;
import model.Obra;

/**
 * 
 * @author Hury
 *
 */

public class ObraDaoImpl implements ObraDao {
	private Connection c;
	private AutorDao autorDao;

	public ObraDaoImpl() {
		GenericConnection gc = new ConnectionImpl();
		c = gc.getConnection();
	}

	@Override
	public void inclui(Obra l) throws GenericException, SQLException {

		String query = "INSERT INTO autor VALUES (?,?,?,?,?,?,?,?,?,?,?)"; // ?
		PreparedStatement ps = c.prepareStatement(query);

		// ps.setInt(1, l.getAutor().getId());
		ps.setString(2, l.getTitulo());
		ps.setInt(7, l.getAno());
		ps.setString(8, l.getDescricao());
		ps.setString(9, l.getCategoria());
		ps.setString(10, l.getDimensoes());
		ps.setString(11, l.getImagem());
		ps.execute();
		ps.close();

	}

	@Override
	public List<Obra> pesquisa(Obra l) throws GenericException, SQLException {
		List<Obra> lista = new ArrayList<Obra>();
		String query = "SELECT * FROM obra";

		PreparedStatement ps = c.prepareStatement(query);
		ResultSet rs = ps.executeQuery();

		autorDao = new AutorDaoImpl();

		while (rs.next()) {
			Obra li = new Obra();
			li.setId(rs.getInt("id"));
			li.setAutor(pesquisaInnerAutor(li));
			li.setTitulo(rs.getString("titulo"));
			li.setAno(rs.getInt("ano"));
			li.setDescricao(rs.getString("descricao"));
			li.setCategoria(rs.getString("idioma"));
			li.setDimensoes(rs.getString("dimensoes"));
			li.setImagem(rs.getString("imagem"));

			lista.add(li);
		}
		System.out.println("Obras pesquisadas");
		ps.close();

		return lista;
	}

	public List<Autor> pesquisaInnerAutor(Obra li) throws SQLException {

		List<Autor> lista = new ArrayList<Autor>();
		String query = "SELECT obra.id, obra.titulo, autor.id as idautor, autor.nome as nome, autor.datanasc as nasc, autor.biografia "
				+ "from obra "
				+ "inner join obraautor "
				+ "on obra.id = obraautor.idobra "
				+ "inner join autor "
				+ "on autor.id = obraautor.idautor "
				+ "where obra.id = ? "
				+ "order by obra.id" ;

		PreparedStatement ps = c.prepareStatement(query);
		ps.setInt(1, li.getId());
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			Autor aut = new Autor();
			aut.setId(rs.getInt("idautor"));
			aut.setNome(rs.getString("nome"));
			aut.setDatanasc(rs.getDate("nasc"));
			aut.setBiografia(rs.getString("biografia"));
			lista.add(aut);
		}

		ps.close();
//
//		List<Autor> lista2 = new ArrayList<Autor>();
//		for (Autor autor : lista) {
//			query = "SELECT * FROM autor WHERE id = ?";
//
//			ps = c.prepareStatement(query);
//			ps.setInt(1, autor.getId());
//			rs = ps.executeQuery();
//			rs.next();
//
//			autor.setNome(rs.getString("nome"));
//			autor.setDatanasc(rs.getDate("datanasc"));
//			autor.setDatafale(rs.getDate("datafale"));
//			autor.setLocalmorte(rs.getString("localmorte"));
//
//			lista2.add(autor);
//		}
//		ps.close();
		return lista;
	}

	@Override
	public void altera(Obra l) throws GenericException, SQLException {
		String sql = "UPDATE obra SET idautor = ?, titulo = ?,"
				+ "ano = ?," + "descricao = ?, categoria = ?, dimensoes = ?"
				+ "WHERE id = ?";

		PreparedStatement ps = c.prepareStatement(sql);

		// ps.setInt(1, l.getAutor().getId());
		ps.setString(2, l.getTitulo());
		ps.setInt(7, l.getAno());
		ps.setString(8, l.getDescricao());
		ps.setString(9, l.getCategoria());
		ps.setString(10, l.getDimensoes());
		ps.setString(11, l.getImagem());
		ps.execute();
		ps.close();

		ps.execute();
		ps.close();
	}

	@Override
	public void exclui(Obra l) throws GenericException, SQLException {
		String query = "DELETE obra where id = ?";
		PreparedStatement ps = c.prepareStatement(query);

		ps.setInt(1, l.getId());

		ps.execute();
		ps.close();

	}

}
