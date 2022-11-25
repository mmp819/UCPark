package es.unican.ps.ucpark.businessLayer;

import java.util.List;

import es.unican.ps.ucpark.domain.Denuncia;
import es.unican.ps.ucpark.domain.Usuario;

public interface IConsultaDenuncias {

	/**
	 * Obtiene la lista de denuncias acumuladas por un usuario determinado.
	 * 
	 * @param usuario Usuario del que se desean obtener las denuncias acumuladas.
	 * @return denuncias acumuladas por el usuario especificado.
	 */
	public List<Denuncia> consultaDenunciasAcumuladas(Usuario usuario);
}
