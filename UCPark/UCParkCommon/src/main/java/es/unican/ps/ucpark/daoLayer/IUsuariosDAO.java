package es.unican.ps.ucpark.daoLayer;

import java.util.List;

import es.unican.ps.ucpark.domain.Usuario;

public interface IUsuariosDAO {

	/**
	 * Crea un nuevo usuario.
	 * 
	 * @param usuario Usuario nuevo a registrar.
	 * @return usuario nuevo registrado.
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
	 */
	public Usuario modificaUsuario(Usuario usuario);
	
	/**
	 * Elimina un usuario concreto.
	 * 
	 * @param usuario Usuario a eliminar.
	 * @return usuario eliminado.
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
	
	/**
	 * Obtiene un usuario concreto en base a su id.
	 * 
	 * @param id ID del usuario a obtener.
	 * @return usuario cuyo ID se corresponde con el especificado.´
	 *         null si no existe ningun usuario con el email indicado.
	 */
	public Usuario usuarioPorId(int id);
}
