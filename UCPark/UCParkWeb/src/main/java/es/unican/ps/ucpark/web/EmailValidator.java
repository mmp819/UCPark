package es.unican.ps.ucpark.web;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator(value="emailValidator")
public class EmailValidator implements Validator<Object> {
	private Pattern pattern;
	private Matcher matcher;
	
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object value)
		throws ValidatorException {
		String componentValue = value.toString();
		pattern = Pattern.compile("^(.+)@(.+)$");
		matcher = pattern.matcher(componentValue);
		
		if (!matcher.find()) {
			String message = MessageFormat.format("Formato de email incorrecto", componentValue);
			FacesMessage facesMessage = new FacesMessage(message, message);
			throw new ValidatorException(facesMessage);
		}
	}
}
