package es.sotero.NominasJDBC.laboral;

public class Empleado extends Persona {

	private int categoria;
	public int anyos;
	
	public Empleado(String nombre, String dni, char sexo) throws DatosNoCorrectosException {
		super(nombre, dni, sexo);
		this.categoria = 1;
		this.anyos = 0;
	}

	public Empleado(String nombre, String dni, char sexo, int categoria, int anyos) throws DatosNoCorrectosException {
		super(nombre, dni, sexo);
		
		if (categoria >= 1 && categoria <= 10 && anyos >= 0) {
			this.categoria = categoria;
			this.anyos = anyos;
		} else {
			throw new DatosNoCorrectosException("La categoría o los años introducidos no son válidos.");
		}
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
	
	public void incrAnyo() {
		this.anyos++;
	}

	@Override
	public void imprime() {
		System.out.println("---- EMPLEADO ----" + "\n" +
				"Nombre: " + nombre + "\n" +
				"DNI: " + dni + "\n" +
				"Sexo: " + sexo + "\n" +
				"Categoría: " + categoria + "\n" +
				"Años: " + anyos);
	}
}
