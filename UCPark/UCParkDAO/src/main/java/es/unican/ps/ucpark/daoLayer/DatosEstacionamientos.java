package es.unican.ps.ucpark.daoLayer;

import java.util.List;

import es.unican.ps.ucpark.domain.Estacionamiento;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

@Stateless
public class DatosEstacionamientos implements IEstacionamientosDAOLocal{

	@PersistenceContext(unitName="UCParkPU")
	private EntityManager em;
	
	@Override
	public Estacionamiento creaEstacionamiento(Estacionamiento estacionamiento) {
		Estacionamiento result;
		
		try {
			em.persist(estacionamiento);
			result = estacionamiento;
		} catch (PersistenceException e) {
			result = null;
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Estacionamiento> estacionamientos() {
		Query q = em.createQuery("SELECT e FROM Estacionamiento e", Estacionamiento.class);
		return q.getResultList();
	}
	
	@Override
	public Estacionamiento modificaEstacionamiento(Estacionamiento estacionamiento) {
		Estacionamiento result;
		
		try {
			result = em.merge(estacionamiento);
		} catch (PersistenceException e) {
			result = null;
		}
		
		return result;
	}
	
	@Override
	public Estacionamiento eliminaEstacionamiento(Estacionamiento estacionamiento) {
		Estacionamiento result;
		
		try {
			em.remove(estacionamiento);
			result = estacionamiento;
		} catch (PersistenceException e) {
			result = null;
		}
		
		return result;
	}
	
	@Override
	public Estacionamiento estacionamientoPorId(String idEstacionamiento) {
		return em.find(Estacionamiento.class, idEstacionamiento);
	}
}
