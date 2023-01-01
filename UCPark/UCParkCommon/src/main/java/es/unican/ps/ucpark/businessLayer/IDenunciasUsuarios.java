package es.unican.ps.ucpark.businessLayer;

import es.unican.ps.ucpark.domain.Denuncia;

public interface IDenunciasUsuarios {

	/**
	 * Anula una denuncia concreta.
	 * 
	 * @param denuncia Denuncia a anular.
	 * @return denuncia anulada.
	 *         null si no existe.
	 */
	public Denuncia anulaDenuncia(Denuncia denuncia);
}
