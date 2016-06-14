package control;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;

@ManagedBean
@FacesValidator(value = "validaMB")
@ViewScoped
public class ValidacaoMB {

	public boolean validarCampos(){

	FacesContext context = FacesContext.getCurrentInstance();

	if(!isNome(null)){
	context.addMessage(null, new FacesMessage("Nome inv�lido!", "Digite o nome"));
	}
	if(!isEndereco(null)){
	context.addMessage(null, new FacesMessage("Endere�o inv�lido", "Digite o endere�o"));
	}
	return true;

	}

	public void salvar() {
	if (!validarCampos()) {
	FacesContext context = FacesContext.getCurrentInstance();
	context.addMessage(null, new FacesMessage("Campos inv�lidos", "Digite corretamente os campos"));
	}
	}
	public boolean isNome(String nome) {
	if (nome != "") {
	return true;
	}
	return false;
	}

	public boolean isEndereco(String nome) {
	if (nome != "") {
	return true;
	}
	return false;
	}

}
