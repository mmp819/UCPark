package es.unican.ps.ucpark.domain;

import jakarta.persistence.Entity;

/**
 * Clase que representa un medio de pago de tipo 'Tarjeta'.
 * 
 * @author Mario Martin Perez
 */
@SuppressWarnings("serial")
@Entity
public class Tarjeta extends MedioPago {

	private String numero;
	private String cvc;
	private String titular;
	
	/**
	 * Constructor por defecto.
	 */
	public Tarjeta() {
		
	}

	/*
	 * Getters & Setters.
	 */

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCvc() {
		return cvc;
	}

	public void setCvc(String cvc) {
		this.cvc = cvc;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarjeta other = (Tarjeta) obj;
		if (other.getNumero().equals(this.numero)) {
			return true;
		}
		return false;
	}  
}
