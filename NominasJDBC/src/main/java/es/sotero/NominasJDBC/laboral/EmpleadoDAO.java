package es.sotero.NominasJDBC.laboral;

import java.sql.*;
import java.util.*;

public class EmpleadoDAO {
	
	public static Empleado getEmpleado(String dni) {
		
		Connection c = Conexion.conectar();
		Statement st;
		ResultSet res;
		Empleado emp = null;
		
		try {
			st = c.createStatement();
			res = st.executeQuery("SELECT nombre, dni, sexo, categoria, anyos FROM empleados WHERE dni = '" + dni + "'");
			
			while (res.next()) {
				emp = new Empleado(res.getString(1),res.getString(2),res.getString(3).charAt(0),res.getInt(4),res.getInt(5));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
            System.out.println("Error al ejecutar el programa");
		} catch (DatosNoCorrectosException e) {
			e.printStackTrace();
            System.out.println("Error en los datos de la consulta");
		}
		
		return emp;
	}
	
	public static List<Empleado> getEmpleadoAll() {
		
		Connection c = Conexion.conectar();
		Statement st;
		ResultSet res;
		List<Empleado> listaEmp = new ArrayList<Empleado>();
		
		try {
			st = c.createStatement();
			res = st.executeQuery("SELECT nombre, dni, sexo, categoria, anyos FROM empleados");
			
			while(res.next()) {
				Empleado emp = new Empleado(res.getString(1),res.getString(2),res.getString(3).charAt(0),res.getInt(4),res.getInt(5));
				listaEmp.add(emp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
            System.out.println("Error al ejecutar el programa");
		} catch (DatosNoCorrectosException e) {
			e.printStackTrace();
            System.out.println("Error en los datos de la consulta");
		}
		
		return listaEmp;
	}
	
	public static void addEmpleado(Empleado emp) {

		Connection c = Conexion.conectar();
		Statement st;
		
		try {
			st = c.createStatement();
			st.execute("INSERT INTO empleados VALUES ('" + emp.nombre + "','" + emp.dni + 
					"','" + emp.sexo + "','" + emp.getCategoria() + "','" + emp.anyos + "')");
			st.execute("INSERT INTO nominas VALUES ('" + emp.dni + "','" + Nomina.sueldo(emp) + "')");
		} catch (SQLException e) {
			e.printStackTrace();
            System.out.println("Error al ejecutar el programa");
		}
	}
	
	public static void updateEmpleado(Empleado emp) {
		
		Connection c = Conexion.conectar();
		Statement st;
		
		try {
			st = c.createStatement();
			st.execute("UPDATE empleados SET nombre='" + emp.nombre + "', sexo='" + emp.sexo + "', categoria=" + 
					emp.getCategoria() + ", anyos=" + emp.anyos + " WHERE dni='" + emp.dni + "'");
			st.execute("UPDATE nominas SET sueldo=" + Nomina.sueldo(emp) + " WHERE dni='" + emp.dni + "'");
			
		} catch (SQLException e) {
			e.printStackTrace();
            System.out.println("Error al ejecutar el programa");
		}
	}
	
	public static int getSalario(String dni) {
		
		Connection c = Conexion.conectar();
		
		Statement st;
		ResultSet res;
		int result = 0;
		
		try {
			st = c.createStatement();
			res = st.executeQuery("SELECT sueldo FROM nominas WHERE dni = '" + dni + "'");
			
			while(res.next()) {
				result = res.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
            System.out.println("Error al ejecutar el programa");
		}
		
		return result;
	}
	
	public static void updateSalario(String dni) {
		
		Connection c = Conexion.conectar();
		Statement st;
		Empleado emp = getEmpleado(dni);
		
		try {
			if (emp != null) {
				st = c.createStatement();
				st.execute("UPDATE nominas SET sueldo=" + Nomina.sueldo(emp) + " WHERE dni='" + dni + "'");
				System.out.println("Salario actualizado correctamente");
			} else {
				System.out.println("El empleado con DNI " + dni + " no existe");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
            System.out.println("Error al ejecutar el programa");
		}
	}
	
	public static void updateSalarioAll() {
		
		Connection c = Conexion.conectar();
		Statement st;
		List<Empleado> listaEmp = getEmpleadoAll();
		
		try {
			
			st = c.createStatement();
			Iterator<Empleado> iter = listaEmp.iterator();
	    	
	    	while(iter.hasNext()) {
	    		Empleado emp = iter.next();
	    		st.execute("UPDATE nominas SET sueldo=" + Nomina.sueldo(emp) + " WHERE dni='" + emp.dni + "'");
	    	}

		} catch (SQLException e) {
			e.printStackTrace();
            System.out.println("Error al ejecutar el programa");
		}
	}
}



/*SELECT R.REGION_ID, R.REGION_NAME, C.COUNTRY_NAME
FROM REGIONS R, COUNTRIES C
WHERE R.REGION_ID = C.REGION_ID;*/
