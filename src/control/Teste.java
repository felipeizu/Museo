package control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.GenericException;
import model.Obra;
import persistence.ObraDao;
import persistence.ObraDaoImpl;

public class Teste {

	public static void main(String[] args) {
		ObraDao ldao = new ObraDaoImpl();
		Obra obra = null;
		List<Obra> lista = new ArrayList<Obra>();
		
		try {
			lista=ldao.pesquisa(obra);
			obra=lista.get(0);
			System.out.println(obra.getTitulo());
		} catch (GenericException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
