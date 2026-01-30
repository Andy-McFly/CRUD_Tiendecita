package es.studium.practica;

import java.awt.Desktop;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Jaspersoft
{
	public Jaspersoft(String informe, HashMap<String, Object> parametros) 
	{ 
		try  
		{ 
			// Compilar el informe generando fichero jasper 
			JasperCompileManager.compileReportToFile("./src/main/resources/" + informe + ".jrxml"); 
			System.out.println("Fichero " + informe + ".jasper generado CORRECTAMENTE!"); 
			// Cargar el informe compilado
			JasperReport report = (JasperReport)JRLoader.loadObjectFromFile("./src/main/resources/" + informe + ".jasper");
			// Conectar a la base de datos para sacar la información
			String servidor = "jdbc:mysql://localhost:3306/tiendecitaamr?useSSL=false";
			String usuarioDB = "root"; 
			String passwordDB = "Studium2024;";
			Connection conexion = DriverManager.getConnection(servidor, usuarioDB, passwordDB);
			// Completar el informe con los datos de la base de datos
			JasperPrint print = JasperFillManager.fillReport(report, parametros, conexion);
			// Mostrar el informe en JasperViewer
			JasperViewer.viewReport(print, false);
			// Para exportarlo a pdf
			JasperExportManager.exportReportToPdfFile(print, "./src/main/resources/" + informe + ".pdf");
			// Abrir el fichero PDF generado
			File path = new File ("./src/main/resources/" + informe + ".pdf");
			Desktop.getDesktop().open(path);
		} 
		catch (Exception e)  
		{ 
			System.out.println("Error: " + e.toString()); 
		} 
	} 
		
}


