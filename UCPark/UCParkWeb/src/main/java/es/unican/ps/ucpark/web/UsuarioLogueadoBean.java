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
	public void getEmail() {
		email = ab.getEmail();
	}
	
	@PostConstruct
	public void loadMatriculasRegistradas() {
		List<Vehiculo> vehiculos = consultaVehiculos.consultaVehiculosRegistrados(email);
		for (Vehiculo v:vehiculos) {
			listaMatriculasRegistradas.add(v.getMatricula());
		}
	}
	
	public String doNuevoEstacionamiento() {
		return "nuevoEstacionamiento.xhtml";
	}
	
	public String doConfirmEstacionamiento() {
		Vehiculo v = consultaVehiculos.consultaVehiculoRegistrado(email, 
				matriculaSeleccionada);
		
		Estacionamiento e = gestionEstacionamientos.creaEstacionamiento(v, minutos);
		
		String result;
		
		if (e == null) {
			result = "nuevoEstacionamiento.xhtml";
		} else {
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
