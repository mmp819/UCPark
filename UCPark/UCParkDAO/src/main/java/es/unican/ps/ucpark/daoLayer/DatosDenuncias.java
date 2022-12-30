package es.unican.ps.ucpark.daoLayer;

import java.util.List;

import es.unican.ps.ucpark.domain.Denuncia;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

@Stateless
public class DatosDenuncias implements IDenunciasDAORemote {

	@PersistenceContext(unitName="UCParkPU")
	private EntityManager em;
	
	@Override
	public Denuncia creaDenuncia(Denuncia denuncia) {
		Denuncia result;
		
		try {
			em.persist(denuncia);
			result = denuncia;
		} catch (PersistenceException e) {
			result = null;
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Denuncia> denuncias() {
		Query q = em.createQuery("SELECT d FROM Denuncia d", Denuncia.class);
		return q.getResultList();
	}
	
	@Override
	public Denuncia modificaDenuncia(Denuncia denuncia) {
		Denuncia result;
		
		try {
			result = em.merge(denuncia);
		} catch (PersistenceException e) {
			result = null;
		}
		return result;
	}
	
	@Override
	public Denuncia eliminaDenuncia(Denuncia denuncia) {
		Denuncia result;
		
		try {
			em.remove(denuncia);
			result = denuncia;
		} catch (PersistenceException e) {
			result = null;
		}
		
		return result;
	}
	
	@Override
	public Denuncia denunciaPorId(int idDenuncia) {
		return em.find(Denuncia.class, idDenuncia);
	}
}
