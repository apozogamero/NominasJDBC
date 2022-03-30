package es.sotero.NominasJDBC.laboral;

public class DatosNoCorrectosException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public DatosNoCorrectosException(String msg) {
		super(msg);
	}
}
