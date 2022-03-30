package es.sotero.NominasJDBC;

import java.sql.*;
import java.util.*;

import es.sotero.NominasJDBC.laboral.*;

public class CalculaNominas {
	
	private static void escribe(Empleado emp1, Empleado emp2) {

		emp1.imprime();
		System.out.println("Sueldo: " + Nomina.sueldo(emp1));
		emp2.imprime();
		System.out.println("Sueldo: " + Nomina.sueldo(emp2));
	}
	
//    public static void main( String[] args ) throws DatosNoCorrectosException, SQLException {
//    	
//    	Empleado e1 = new Empleado("James Cosling", "32000032G", 'M', 4, 7);
//    	Empleado e2 = new Empleado("Ada Lovelace", "32000031R", 'F');
//    	// Comprobar excepción
//    	// Empleado e3 = new Empleado("James Cosling", "32000032G", 'M', 44, 7);
//    	// Empleado e4 = new Empleado("James Cosling", "32000032G", 'M', 4, -7);
//    	escribe(e1, e2);
//    	
//    	e2.incrAnyo();
//    	e2.incrAnyo();
//    	e1.setCategoria(9);
//    	escribe(e1, e2);
//    	
//    	EmpleadoDAO dao = new EmpleadoDAO();
//    	
//    	Empleado e5 = new Empleado("Paco Pérez", "11112223Q", 'M', 6, 3);
//    	dao.addEmpleado(e5);
//    	
//    	System.out.println("===================================");
//    	
//    	List<Empleado> listaEmp = dao.getEmpleadoAll();
//    	Iterator<Empleado> iter = listaEmp.iterator();
//    	
//    	while(iter.hasNext()) {
//    		Empleado emp = iter.next();
//    		emp.imprime();
//    	}
//
//    	System.out.println("===================================");
//    	
//    	System.out.println(dao.getSalario("11112223Q"));
//    	
//    	System.out.println("===================================");
//    	
//    	dao.updateSalario("11112223Q");
//    	System.out.println(dao.getSalario("11112223Q"));
//    	
//    	dao.updateSalarioAll();
//    }
	
	public static void main(String[] args) {
		
		Scanner scStr = new Scanner(System.in);
		Scanner scNum = new Scanner(System.in);
		boolean fin = false;
		
		while (!fin) {
			System.out.println("---- MENÚ NÓMINAS ----");
			System.out.println("1. Mostrar todos los empleados");
			System.out.println("2. Mostrar el salario de un empleado");
			System.out.println("3. Editar empleados existentes");
			System.out.println("4. Actualizar el salario de un empleado");
			System.out.println("5. Actualizar el salario de todos los empleados");
			System.out.println("Pulse otra tecla para salir");
			
			String opcion = scStr.nextLine();
			String dni;
			
			switch(opcion) {
			case "1":
				List<Empleado> listaEmp = EmpleadoDAO.getEmpleadoAll();
		    	Iterator<Empleado> iter = listaEmp.iterator();
		    	
		    	while(iter.hasNext()) {
		    		Empleado emp = iter.next();
		    		emp.imprime();
		    	}
		    	
		    	break;
			
			case "2":
				System.out.println("Introduce el DNI del empleado:");
				dni = scStr.nextLine();
				int salario = EmpleadoDAO.getSalario(dni);
				
				if (salario > 0) {
					System.out.println("El salario del empleado con DNI " + dni + " es " + salario);
				} else {
					System.out.println("El empleado con DNI " + dni + " no se pudo encontrar o no existe");
				}
				
				break;
				
			case "3":
				System.out.println("Introduce el DNI del empleado a modificar:");
				dni = scStr.nextLine();
				boolean finSubmenu = false;
				
				while(!finSubmenu) {
					System.out.println("---- SUBMENÚ DE EDICIÓN ----");
					System.out.println("1. Nombre");
					System.out.println("2. Sexo");
					System.out.println("3. Categoría");
					System.out.println("4. Años");
					System.out.println("Pulse otra tecla para salir");
					
					Empleado emp = EmpleadoDAO.getEmpleado(dni);
					String opcionSubmenu = scStr.nextLine();
					
					switch(opcionSubmenu) {
					case "1":
						System.out.println("Introduce el nuevo valor:");
						emp.nombre = scStr.nextLine();
						
						EmpleadoDAO.updateEmpleado(emp);
						
						break;

					case "2":
						System.out.println("Introduce el nuevo valor:");
						emp.sexo = scStr.nextLine().charAt(0);
						
						EmpleadoDAO.updateEmpleado(emp);
						
						break;

					case "3":
						System.out.println("Introduce el nuevo valor:");
						emp.setCategoria(scNum.nextInt());
						
						EmpleadoDAO.updateEmpleado(emp);
						
						break;

					case "4":
						System.out.println("Introduce el nuevo valor:");
						emp.anyos = scNum.nextInt();
						
						EmpleadoDAO.updateEmpleado(emp);
						
						break;
					
					default:
						finSubmenu = true;
						break;
					}
				}
				
				break;
				
			case "4":
				System.out.println("Introduce el DNI del empleado:");
				dni = scStr.nextLine();
				
				EmpleadoDAO.updateSalario(dni);
				
				break;
			
			case "5":
				EmpleadoDAO.updateSalarioAll();
				
				break;
		    
			default:
				fin = true;
				break;
			}
		}
	}
}