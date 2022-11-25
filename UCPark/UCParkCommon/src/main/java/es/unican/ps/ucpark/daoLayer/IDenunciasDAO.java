package es.unican.ps.ucpark.daoLayer;

import java.util.List;

import es.unican.ps.ucpark.domain.Denuncia;

public interface IDenunciasDAO {

	/**
	 * Crear una nueva denuncia.
	 * 
	 * @param denuncia Denuncia creada a registrar.
	 * @return denuncia creada.
	 */
	public Denuncia creaDenuncia(Denuncia denuncia);
	
	/**
	 * Obtiene la lista de denuncias registradas.
	 * 
	 * @return denuncias registradas.
	 */
	public List<Denuncia> denuncias();
	
	/**
	 * Modifica una denuncia concreta.
	 * 
	 * @param denuncia Denuncia modificada.
	 * @return denuncia modificada.
	 */
	public Denuncia modificaDenuncia(Denuncia denuncia);
	
	/**
	 * Elimina una denuncia concreta.
	 * 
	 * @param denuncia Denuncia a eliminar.
	 * @return denuncia eliminada.
	 */
	public Denuncia eliminaDenuncia(Denuncia denuncia);
	
	/**
	 * Obtiene una denuncia concreta en base a su ID.
	 * 
	 * @param idDenuncia ID de la denuncia a obtener.
	 * @return denuncia cuyo ID coincide con el indicado.
	 *         null si no existe ninguna denuncia que se corresponda con el ID.
	 */
	public Denuncia denunciaPorId(String idDenuncia);
}
