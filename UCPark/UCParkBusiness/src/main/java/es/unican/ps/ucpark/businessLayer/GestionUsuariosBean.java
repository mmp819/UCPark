package es.unican.ps.ucpark.businessLayer;

import es.unican.ps.ucpark.daoLayer.IUsuariosDAOLocal;
import es.unican.ps.ucpark.domain.Usuario;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class GestionUsuariosBean implements IGestionUsuariosLocal, 
	IGestionUsuariosRemote {
	
	@EJB
	private IUsuariosDAOLocal usuariosDAO;
	
	@Override
	public Usuario registraUsuario(Usuario usuario) {		
		return usuariosDAO.creaUsuario(usuario);
	}
	
	@Override
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
