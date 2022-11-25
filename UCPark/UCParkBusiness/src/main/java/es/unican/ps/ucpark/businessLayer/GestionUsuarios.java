package es.unican.ps.ucpark.businessLayer;

import es.unican.ps.ucpark.daoLayer.IUsuariosDAO;
import es.unican.ps.ucpark.domain.Usuario;

public class GestionUsuarios implements IGestionUsuarios {
	
	private IUsuariosDAO usuariosDAO;
	
	/**
	 * Crea un nuevo usuario.
	 * 
	 * @param usuario Usuario a crear.
	 * 
	 * @return usuario registrado.
	 *         null si el usuario ya esta registrado.
	 */
	public Usuario registraUsuario(Usuario usuario) {
		
		Usuario usuarioRegistrado;
		
		if (usuariosDAO.usuarioPorId(usuario.getId()) == null) {
			usuarioRegistrado = null;
		} else {
			usuarioRegistrado = usuariosDAO.creaUsuario(usuario);
		}
		
		return usuarioRegistrado;
	}
	
	public boolean loginUsuario(String email, String contrasenha) {
		boolean estadoLogin;
		Usuario login = usuariosDAO.usuarioPorEmail(email);
		
		if (login != null && login.getContrasenha().equals(contrasenha)) {
			estadoLogin = true;
		} else {
			estadoLogin = false;
		}
		
		return estadoLogin;
	}
	
	
}