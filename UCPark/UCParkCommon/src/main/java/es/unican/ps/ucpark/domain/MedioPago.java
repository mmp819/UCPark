package es.unican.ps.ucpark.domain;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;

/**
 * Clase abstracta que representa un medio de pago.
 * 
 * @author Mario Martin Perez
 */
@SuppressWarnings("serial")
@Entity
@Inheritance(strategy = jakarta.persistence.InheritanceType.JOINED)
public abstract class MedioPago implements Serializable {

	@Id
	@GeneratedValue
	private int id;
	
	public MedioPago() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
