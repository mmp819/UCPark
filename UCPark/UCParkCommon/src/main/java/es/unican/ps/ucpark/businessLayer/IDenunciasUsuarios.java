package es.unican.ps.ucpark.businessLayer;

import es.unican.ps.ucpark.domain.Denuncia;

public interface IDenunciasUsuarios {

	/**
	 * Anula una denuncia determinada.
	 * 
	 * @param denuncia Denuncia a anular.
	 * @return denuncia anulada.
	 */
	public Denuncia anulaDenuncia(Denuncia denuncia);
}
