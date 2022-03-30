package es.sotero.NominasJDBC.laboral;

public class Persona {
	
	public String nombre, dni;
	public char sexo;
	
	public Persona(String nombre, String dni, char sexo) throws DatosNoCorrectosException {
		
		if (sexo == 'M' || sexo == 'F') {
			this.nombre = nombre;
			this.dni = dni;
			this.sexo = sexo;
		} else {
			throw new DatosNoCorrectosException("El sexo introducido no es válido.");
		}
	}

	public Persona(String nombre, char sexo) throws DatosNoCorrectosException {
		
		if (sexo == 'M' || sexo == 'F') {
			this.nombre = nombre;
			this.sexo = sexo;
		} else {
			throw new DatosNoCorrectosException("El sexo introducido no es válido.");
		}
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void imprime() {
		System.out.println("---- PERSONA ----" + "\n" +
				"Nombre: " + nombre + "\n" +
				"DNI: " + dni + "\n" +
				"Sexo: " + sexo);
	}
}
