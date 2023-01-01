package es.unican.ps.ucpark.businessLayer;

import java.util.List;

import es.unican.ps.ucpark.domain.Denuncia;
import es.unican.ps.ucpark.domain.Usuario;

public interface IConsultaDenuncias {

	/**
	 * Consulta las denuncias acumuladas vigentes de un usuario.
	 * 
	 * @param usuario Usuario del que se quieren consultar las denuncias.
	 * @return denuncias vigentes acumuladas por el usuario especificado.
	 */
	public List<Denuncia> consultaDenunciasAcumuladas(Usuario usuario);
}
