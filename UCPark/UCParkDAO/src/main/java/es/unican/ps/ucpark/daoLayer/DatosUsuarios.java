package es.unican.ps.ucpark.daoLayer;

import java.util.Iterator;
import java.util.List;

import es.unican.ps.ucpark.domain.Usuario;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

@Stateless
public class DatosUsuarios implements IUsuariosDAOLocal {

	@PersistenceContext(unitName="UCParkPU")
	private EntityManager em;
	
	
	@Override
	public Usuario creaUsuario(Usuario usuario) {
		Usuario result;
		
		try {
			em.persist(usuario);
			result = usuario;
		} catch (PersistenceException e) {
			result = null;
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> usuarios() {
		Query q = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
		return q.getResultList();
	}
	
	@Override
	public Usuario modificaUsuario(Usuario usuario) {
		Usuario result;
		
		try {
			result = em.merge(usuario);
		} catch (PersistenceException e) {
			result = null;
		}
		
		return result;
	}
	
	@Override
	public Usuario eliminaUsuario(Usuario usuario) {
		Usuario result;
		
		try {
			em.remove(usuario);
			result = usuario;
		} catch (PersistenceException e) {
			result = null;
		}
		
		return result;
	}
	
	@Override
	public Usuario usuarioPorEmail(String email) {
		List<Usuario> usuarios = usuarios();
		Iterator<Usuario> iter = usuarios.iterator();
		
		boolean encontrado = false;
		Usuario aux = null;
		
		while (iter.hasNext() && !encontrado) {
			aux = iter.next();
			if (aux.getEmail().equals(email)) {
				encontrado = true;
			}
		}
		
		return aux;
	}
	
	@Override
	public Usuario usuarioPorId(int id) {
		return em.find(Usuario.class, id);
	}
}
