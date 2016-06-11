package control;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@ManagedBean
@FacesValidator(value = "validaMB")
@ViewScoped
public class ValidacaoMB implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object valorTela) throws ValidatorException {
		if (!validaCNPJ(String.valueOf(valorTela))) {
			FacesMessage message = new FacesMessage();
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			message.setSummary(
					ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle())
							.getString("erro.validacao.cnpj"));
			throw new ValidatorException(message);
		}
	}

	
	
	
	
	public boolean validarCampos(){

	FacesContext context = FacesContext.getCurrentInstance();

	if(!isNome(null)){
	context.addMessage(null, new FacesMessage("Nome inválido!", "Digite o nome"));
	}
	if(!isEndereco(null)){
	context.addMessage(null, new FacesMessage("Endereço inválido", "Digite o endereço"));
	}
	return true;

	}

	public void salvar() {
	if (!validarCampos()) {
	FacesContext context = FacesContext.getCurrentInstance();
	context.addMessage(null, new FacesMessage("Campos inválidos", "Digite corretamente os campos"));
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
