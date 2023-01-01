package es.unican.ps.ucpark.daoLayer;

import java.util.List;

import es.unican.ps.ucpark.domain.Usuario;

public interface IUsuariosDAO {

	/**
	 * Crea un nuevo usuario.
	 * 
	 * @param usuario Usuario nuevo a registrar.
	 * @return usuario nuevo registrado.
	 *         null si ya existe u ocurre un error.
	 */
	public Usuario creaUsuario(Usuario usuario);
	
	/**
	 * Obtiene los usuarios registrados.
	 * 
	 * @return lista de usuarios registrados.
	 */
	public List<Usuario> usuarios();
	
	/**
	 * Modifica un usuario concreto.
	 * 
	 * @param usuario Usuario modificado.
	 * @return usuario modificado.
	 *         null en caso de ocurrir un error en la modificacion.
	 */
	public Usuario modificaUsuario(Usuario usuario);
	
	/**
	 * Elimina un usuario concreto.
	 * 
	 * @param usuario Usuario a eliminar.
	 * @return usuario eliminado.
	 *         null si ocurre un error en la eliminacion.
	 */
	public Usuario eliminaUsuario(Usuario usuario);
	
	/**
	 * Obtiene un usuario concreto en base a su email.
	 * 
	 * @param email Email del usuario a obtener.
	 * @return usuario cuyo email se corresponde con el especificado.
	 *         null si no existe ningun usuario con el email indicado.
	 */
	public Usuario usuarioPorEmail(String email);
}
