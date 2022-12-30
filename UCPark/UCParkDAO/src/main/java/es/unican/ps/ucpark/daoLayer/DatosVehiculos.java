package es.unican.ps.ucpark.daoLayer;

import java.util.Iterator;
import java.util.List;

import es.unican.ps.ucpark.domain.Vehiculo;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

@Stateless
public class DatosVehiculos implements IVehiculosDAORemote {
	
	@PersistenceContext(unitName="UCParkPU")
	private EntityManager em;
	
	@Override
	public Vehiculo creaVehiculo(Vehiculo vehiculo) {
		Vehiculo result;
		
		try {
			em.persist(vehiculo);
			result = vehiculo;
		} catch (PersistenceException e) {
			result = null;
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Vehiculo> vehiculos() {
		Query q = em.createQuery("SELECT v FROM Vehiculo v", Vehiculo.class);
		return q.getResultList();
	}
	
	@Override
	public Vehiculo modificaVehiculo(Vehiculo vehiculo) {
		Vehiculo result;
		
		try {
			result = em.merge(vehiculo);
		} catch (PersistenceException e) {
			result = null;
		}
		
		return result;
	}
	
	@Override
	public Vehiculo eliminaVehiculo(Vehiculo vehiculo) {
		Vehiculo result;
		
		try {
			em.remove(vehiculo);
			result = vehiculo;
		} catch (PersistenceException e) {
			result = null;
		}
		
		return result;
	}
	
	@Override
	public Vehiculo vehiculoPorMatricula(String matricula) {
		List<Vehiculo> vehiculos = vehiculos();
		Iterator<Vehiculo> iter = vehiculos.iterator();
		
		boolean encontrado = false;
		Vehiculo aux = null;
		
		while (iter.hasNext() && !encontrado) {
			aux = iter.next();
			if (aux.getMatricula().equals(matricula)) {
				encontrado = true;
			}
		}
		
		return aux;
	}

}
