package es.unican.ps.ucpark.businessLayer;

import es.unican.ps.ucpark.domain.Usuario;

public interface IGestionUsuarios {

	/**
	 * Registra un usuario.
	 * 
	 * @param usuario Usuario a registrar.
	 * @return usuario registrado.
	 */
	public Usuario registraUsuario(Usuario usuario);
	
	/**
	 * Realiza el login para un usuario concreto.
	 * 
	 * @param email Correo electronico del usuario
	 * @param contrasenha Clave del usuario
	 * @return true si se han conseguido realizar un login correcto.
	 *         false en caso contrario.
	 */
	public boolean loginUsuario(String email, String contrasenha);
}
