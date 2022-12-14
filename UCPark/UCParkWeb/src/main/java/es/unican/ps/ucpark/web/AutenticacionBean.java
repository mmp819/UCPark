package es.unican.ps.ucpark.web;

import java.io.Serializable;

import es.unican.ps.ucpark.businessLayer.IGestionUsuariosRemote;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class AutenticacionBean implements Serializable {

	@EJB
	private IGestionUsuariosRemote usuarios;
	
	private String email;
	private String contrasenha;
	
	/**
	 * Constructor por defecto.
	 */
	public AutenticacionBean() {
		
	}
	
	
	public String doLogin() {		
		String result = "login.xhtml";
		boolean logStatus = usuarios.loginUsuario(email, contrasenha);
		
		if (logStatus) {
			result = "index.xhtml";
		} else {
			String mensaje = "Email y/o clave incorrectos.";
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, null));
		}
		
		return result;
	}


	public IGestionUsuariosRemote getUsuarios() {
		return usuarios;
	}


	public void setUsuarios(IGestionUsuariosRemote usuarios) {
		this.usuarios = usuarios;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getContrasenha() {
		return contrasenha;
	}


	public void setContrasenha(String contrasenha) {
		this.contrasenha = contrasenha;
	}
}
