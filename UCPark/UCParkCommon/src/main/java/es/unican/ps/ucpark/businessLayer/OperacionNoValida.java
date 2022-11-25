package es.unican.ps.ucpark.businessLayer;

@SuppressWarnings("serial")
public class OperacionNoValida extends RuntimeException {

	public OperacionNoValida(String string) {
		super(string);
	}

}
