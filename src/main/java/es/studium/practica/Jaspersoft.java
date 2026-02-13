package es.studium.practica;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Clase para la creación de informes JasperReports
 * 
 * @author Andrés Martínez Romero
 * @since 22/1/2026
 * @version 1.2
 * 
 */
public class Jaspersoft
{
	/**
	 * Constructor de la clase para generar informes
	 * @param informe Cadena, nombre del informe
	 * @param parametros Lista, parámetros clave-valor que recibe el informe
	 */
	public Jaspersoft(String informe, HashMap<String, Object> parametros) 
	{ 
		try  
		{ 
			//Compilar el informe generando fichero jasper 
			InputStream reportStream = Jaspersoft.class.getResourceAsStream("/" + informe + ".jasper");
			
			// Cargar el informe compilado
			JasperReport report = (JasperReport) JRLoader.loadObject(reportStream);
			
			//Conectar a la base de datos para sacar la información
			String servidor = "jdbc:mysql://localhost:3306/tiendecitaamr?useSSL=false";
			String usuarioDB = "adminTiendecita"; 
			String passwordDB = "Studium2026;";
			Connection conexion = DriverManager.getConnection(servidor, usuarioDB, passwordDB);
			
			//Completar el informe con los datos de la base de datos
			JasperPrint print = JasperFillManager.fillReport(report, parametros, conexion);
			
			//Mostrar el informe en JasperViewer
			JasperViewer viewer = new JasperViewer(print, false);
			viewer.setVisible(true);
		} 
		catch (Exception e)  
		{ 
			System.out.println("Error: " + e.toString()); 
		} 
	} 
		
}


