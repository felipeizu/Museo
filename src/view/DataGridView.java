package view;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import control.ObraMB;
import exception.GenericException;
import model.Obra;
import persistence.PesquisaDaoImpl;


@ManagedBean(name = "dataGridView")
//@SessionScoped
@ApplicationScoped
public class DataGridView implements Serializable {
	private static final long serialVersionUID = 7659498095569980364L;

	private List<Obra> obras;
	private Obra selectedObra;
	private String busca;
	private String parametroPesquisa;

	@ManagedProperty("#{obraMB}")
	private ObraMB obramb;

	@PostConstruct
	public void init() {
		// primeira pagina
		try {
			obras = obramb.pesquisar();
		//	System.out.println(obras.size());
		} catch (GenericException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void buscarObra(String parametro) {
		PesquisaDaoImpl p = new PesquisaDaoImpl();
		try {
			if (parametroPesquisa.equalsIgnoreCase("título")) {
				obras = p.pesquisaPorTitulo(parametro);
			}else if (parametroPesquisa.equalsIgnoreCase("autor")) {
				obras = p.pesquisaPorAutor(parametro);
			}else{
				obras = p.pesquisaPorTitulo(parametro);
			}
			
		} catch (GenericException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String pagPrincipal() {
		try {
			obras = obramb.pesquisar();
		} catch (GenericException | SQLException e) {
			e.printStackTrace();
		}
		return "index";
	}

	public List<Obra> getObras() {
		return obras;
	}

	public void setObras(List<Obra> obras) {
		this.obras = obras;
	}

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

	public String getParametroPesquisa() {
		return parametroPesquisa;
	}

	public void setParametroPesquisa(String parametroPesquisa) {
		this.parametroPesquisa = parametroPesquisa;
	}

	public ObraMB getObramb() {
		return obramb;
	}

	public void setObramb(ObraMB obramb) {
		this.obramb = obramb;
	}
	
	public Obra getSelectedObra() {
		return selectedObra;
	}

	public void setSelectedObra(Obra selectedObra) {
		this.selectedObra = selectedObra;
	}

	



}