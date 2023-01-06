package es.unican.ps.ucpark.web;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import es.unican.ps.ucpark.businessLayer.IConsultaVehiculosRemote;
import es.unican.ps.ucpark.businessLayer.IGestionEstacionamientosRemote;
import es.unican.ps.ucpark.domain.Estacionamiento;
import es.unican.ps.ucpark.domain.Vehiculo;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class UsuarioLogueadoBean implements Serializable {
	
	private String email;
	private List<String> listaMatriculasRegistradas;
	private String matriculaSeleccionada;
	private int minutos;
	private LocalDateTime horaFin;
	
	@Inject
	private AutenticacionBean ab;
	
	@EJB
	private IGestionEstacionamientosRemote gestionEstacionamientos;
	@EJB
	private IConsultaVehiculosRemote consultaVehiculos;
	
	public UsuarioLogueadoBean() {
		
	}
	
	@PostConstruct
	public void loadMatriculasRegistradas() {
		this.getEmail();
		listaMatriculasRegistradas = consultaVehiculos.consultaMatriculasRegistradas(email);
	}
	
	public void getEmail() {
		email = ab.getEmail();
	}
	
	public String doNuevoEstacionamiento() {
		return "nuevoEstacionamiento.xhtml";
	}
	
	public String doIndex() {
		return "index.xhtml";
	}
	
	public String doConfirmEstacionamiento() {
		Vehiculo v = consultaVehiculos.consultaVehiculoRegistrado(email, 
				matriculaSeleccionada);
		
		Estacionamiento e = null;
		
		try {
			e = gestionEstacionamientos.creaEstacionamiento(v, minutos);
		} catch (Exception exc) {
			String mensaje = "El vehiculo ya tiene un estacionamiento en vigor.";
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, null));
			return "nuevoEstacionamiento.xhtml";
		}
		
		String result;
		
		if (e == null) {
			result = "nuevoEstacionameinto.xhtml";
		} else {
			this.setHoraFin(e.getHoraInicio().plusMinutes(e.getMinutos()));
			result = "infoEstacionamiento.xhtml";
		}
		
		return result;
	}


	public List<String> getListaMatriculasRegistradas() {
		return listaMatriculasRegistradas;
	}

	public void setListaMatriculasRegistradas(List<String> listaMatriculasRegistradas) {
		this.listaMatriculasRegistradas = listaMatriculasRegistradas;
	}

	public String getMatriculaSeleccionada() {
		return matriculaSeleccionada;
	}

	public void setMatriculaSeleccionada(String matriculaSeleccionada) {
		this.matriculaSeleccionada = matriculaSeleccionada;
	}

	public int getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}

	public LocalDateTime getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(LocalDateTime horaFin) {
		this.horaFin = horaFin;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
