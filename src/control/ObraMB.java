package control;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import exception.GenericException;
import exception.ObraDaoException;
import model.Autor;
import model.Obra;
import persistence.AutorDaoImpl;
import persistence.GenericDao;
import persistence.ObraDao;
import persistence.ObraDaoImpl;

/**
 * 
 * @author Classe responsavel pelo controle do objeto obra
 */

@ManagedBean
@ApplicationScoped
public class ObraMB extends GenericBean<Obra> {
	private static final long serialVersionUID = -6883059046573310496L;

	public ObraMB() {
		super.listaPesquisa = new ArrayList<Obra>();
		super.objAtual = new Obra();
		super.dao = new ObraDaoImpl();
		super.selectedObj = new Obra();
	}

	@Override
	public void inclui() throws GenericException, SQLException {
		String msg = "Erro ao cadastrar!";
		try {
			dao.inclui(objAtual);
			msg = "Cadastro concluído com sucesso!";
			FacesContext fc = FacesContext.getCurrentInstance();
			fc.addMessage("", new FacesMessage(msg));
		} catch (ObraDaoException lx) {
			lx.printStackTrace();
		}
	}

	@Override
	public void altera(Obra selectedObj) throws GenericException, SQLException {

		FacesContext context = FacesContext.getCurrentInstance();
		try {
			dao.altera(selectedObj);
			context.addMessage(null, new FacesMessage("Obra Alterada com sucesso!"));
			pesquisar();
			System.out.println("Alterado");
		} catch (GenericException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Exclui uma determinada obra do banco de dados
	 */
	@Override
	public void exclui(Obra selectedObj) throws GenericException, SQLException {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			dao.exclui(selectedObj);
			context.addMessage(null, new FacesMessage("Obra Excluida com sucesso!"));
			pesquisar();
		} catch (GenericException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Pesquisa Todos os obras na DB
	 */
	@Override
	public List<Obra> pesquisar() throws GenericException, SQLException {
		
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			listaPesquisa = dao.pesquisa(objAtual);
			context.addMessage(null, new FacesMessage("Pesquisado, encontrado " + listaPesquisa.size() + " registros"));
			System.out.println("Pesquisado");
		} catch (GenericException | SQLException e) {
			e.printStackTrace();
		}

		return listaPesquisa;
	}
	

	
	/**
	 * Pesquisa um determinado Obra pelo titulo
	 * @return
	 */
	public List<Obra> pesquisarTitulo(){
		
		return null;
	}
	
	/**
	 * Pesquisa Obras por autores
	 * @return
	 */
	public List<Obra> pesquisarAutor(){
		
		return null;
	}
	
	@Override
	public List<Obra> getListaPesquisa() {
		// TODO Auto-generated method stub
		return listaPesquisa;
	}

	@Override
	public void setListaPesquisa(List<Obra> listaPesquisa) {
		// TODO Auto-generated method stub
		this.listaPesquisa = listaPesquisa;
	}

	public Obra getObjAtual() {
		return objAtual;
	}

	public void setObjAtual(Obra setObjAtual) {
		this.objAtual = setObjAtual;
	}
	
	public Obra getSelectedObj() {
		return selectedObj;
	}

	public void setSelectedObj(Obra selectedObj) {
		this.selectedObj = selectedObj;
	}
	
    public void onRowEdit(RowEditEvent event) throws GenericException, SQLException {
    	altera( (Obra) event.getObject());
    	FacesMessage msg = new FacesMessage("Autor editado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Autor Cancelled"); //, ((Autor) event.getObject()).getId()
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }


}
