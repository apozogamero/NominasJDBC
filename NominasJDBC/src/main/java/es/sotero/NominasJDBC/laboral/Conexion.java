package es.sotero.NominasJDBC.laboral;

import java.sql.*;

public class Conexion {
	
	public static String url = "jdbc:oracle:thin:@localhost:1521/xe";
	public static String user = "ALEJANDRO";
	public static String pass = "ALEJANDRO1";
	public static Connection conn;
	
	public void desconectar() {
		try {
            if (conn != null)
                conn.close();
        } catch(SQLException e) {
            System.out.println("Error al cerrar la BD");
        }
	}
	
	public static Connection conectar() {
		try {
            conn = DriverManager.getConnection(url,user,pass);
//            System.out.print("Conexión válida: " + conn.isValid(10));
//            System.out.println(", Estado del autocommit: " + conn.getAutoCommit());
        } catch(SQLException e) {
            System.out.println("Error al conectar a la BD");
        }
		
		return conn;
	}
}