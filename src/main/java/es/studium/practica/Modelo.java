package es.studium.practica;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JTextArea;

public class Modelo 
{
	Connection dbcon;
	Statement sentencia;

	//Conexión con la Base de Datos.
	public Modelo() 
	{
		try 
		{
			//Lectura de datos desde un fichero externo.
			FileReader fr = new FileReader("datos.txt");
			BufferedReader br = new BufferedReader(fr);
			String driver = br.readLine();
			Class.forName(driver);
			String sourceURL = br.readLine();
			String usuario = br.readLine();
			String contraseña = br.readLine();
			
			//Acceso con los datos obtenidos.
			dbcon = DriverManager.getConnection(sourceURL, usuario, contraseña);
			sentencia = dbcon.createStatement();
			
			br.close();
			fr.close();
		}
		catch (Exception e) 
		{
			System.out.println("Error en al conectar");
		} 
		
	}
	
	
	//ARTÍCULOS.
	
	//Alta de artículo. Devuelve si se efectúa correctamente o no.
	public boolean altaArticulo (String descripcion, double precio, int cantidad) 
	{
		boolean resultado = false;
		try
		{
			String insertar = "INSERT INTO Articulos VALUES (null, '" + descripcion + "', " + precio + ", " + cantidad + ")";
			sentencia.executeUpdate(insertar);
			System.out.println(insertar);
			resultado = true;
		}
		catch (SQLException e)
		{
			System.out.println("Error al crear artículo");
		}
		
		return resultado;
	}
	
	//Baja de artículo. Devuelve si se efectúa correctamente o no.
	public boolean bajaArticulo(int idArticulo)
	{
		boolean resultado = false;
		//Envía el ID del artículo para borrar cualquier línea que lo incluya.
		borrarLinea(idArticulo);
		try
		{
			String baja = "DELETE FROM Articulos WHERE idArticulo = " + idArticulo + ";";
			sentencia.executeUpdate(baja);
			System.out.println(baja);
			resultado = true;
		}
		catch (SQLException e)
		{
			System.out.println("Error al eliminar artículo");
		}
		
		return resultado;
	}
	
	//Baja de artículo. Borra las líneas del ticket en las que aparece el artículo (borrado en cascada).
	public void borrarLinea(int idArticuloFK) 
	{
		try
		{
			String baja = "DELETE FROM lineaticket WHERE idArticuloFK = " + idArticuloFK + ";";
			sentencia.executeUpdate(baja);
			System.out.println(baja);
		}
		catch (SQLException e)
		{
			System.out.println("Error al eliminar artículo");
		}
	}
	
	//Modificar artículo. Devuelve si se efectúa correctamente o no.
	public boolean modificarArticulo(int id, String descripcion, double precio, int stock)
	{
		boolean resultado = false;
		try
		{
			String modificar = "UPDATE Articulos SET descripcionArticulo = '"+ descripcion +"', precioArticulo = " 
					+ precio + ", cantidadArticulo = " + stock + " WHERE idArticulo = " + id + ";";
			sentencia.executeUpdate(modificar);
			System.out.println(modificar);
			resultado = true;
		}
		catch (SQLException e)
		{
			System.out.println("Error al modificar artículo");
		}
		return resultado;
	}
	
	//Rellenar datos para la ventana de Edición de artículo.
	public String datosArticulo(int idArticulo, String campo) 
	{
		String valor = "";
		try
		{
			String consultar = "SELECT " + campo + " FROM articulos WHERE idArticulo = " + idArticulo + ";";
			ResultSet rs = sentencia.executeQuery(consultar);
			while (rs.next())
			{
				valor = rs.getString(campo);
			}
		}
		catch (SQLException e)
		{
			System.out.println("Error al consultar artículos");
		}
			
		return valor;
	}
	
	//Consultar artículos. Añade a un área de texto una lista con los artículos de la base de datos.
	public void consultarArticulos(JTextArea txaArticulos)
	{
		try
		{
			String consultar = "SELECT * FROM Articulos";
			ResultSet rs = sentencia.executeQuery(consultar);
			System.out.println(consultar);
			while (rs.next())
			{
				txaArticulos.append("Código ID: " + rs.getInt("idArticulo") + " | Descripción: " + rs.getString("descripcionArticulo")
						+ " | Precio: " + String.format("%.2f", rs.getDouble("precioArticulo")) + "€ | Stock: " + rs.getInt("cantidadArticulo") + " uds" + "\n");
			}
		}
		catch (SQLException e)
		{
			System.out.println("Error al consultar artículos");
		}
	}
	
	
	//TICKETS
	
	//Alta de tickets. Devuelve el ID del ticket recién registrado.
	public int crearTicket(double total)
	{
		int idTicket = 0;
		try
		{
			String insertar = "INSERT INTO Tickets VALUES (null, CURDATE(), " + total + ")";
			sentencia.executeUpdate(insertar, Statement.RETURN_GENERATED_KEYS);
			System.out.println(insertar);
			ResultSet rs = sentencia.getGeneratedKeys();
			while (rs.next())
			{
				idTicket = rs.getInt(1);
			}
		}
		catch (SQLException e)
		{
			System.out.println("Error al crear ticket" + e.getMessage());
		}
		
		return idTicket;
	}
	
	//Alta de tickets. Añade las líneas con los artículos al ticket registrado.
	public void lineaTicket(int idTicket, int idArticulo) 
	{
		try
		{
			String insertar = "INSERT INTO lineaticket VALUES (null, " + idTicket + ", " + idArticulo + ")";
			sentencia.executeUpdate(insertar);
			System.out.println(insertar);
		}
		catch (SQLException e)
		{
			System.out.println("Error al crear línea del ticket" + e.getMessage());
		}
	}
	
	//Alta de tickets. Rellenar lista de artículos.
	public void listado(List listArticulos) 
	{
		try
		{
			String consultar = "SELECT * FROM Articulos";
			ResultSet rs = sentencia.executeQuery(consultar);
			while (rs.next())
			{
				listArticulos.add(rs.getInt("idArticulo") + " - " + rs.getString("descripcionArticulo")
						+ " - " + rs.getDouble("precioArticulo") + "€");
			}
		}
		catch (SQLException e)
		{
			System.out.println("Error al consultar artículos");
		}
	}
	
	//Consultar Tickets. Obtiene las líneas del ticket y las añade a un área de texto.
	public void rellenarTextArea(JTextArea textArea, int idTicketFK) 
	{
		try
		{
			String consultar = "SELECT idArticuloFK, descripcionArticulo, precioArticulo FROM lineaticket "
					+ "JOIN articulos ON articulos.idArticulo = lineaticket.idArticuloFK WHERE idTicketFK = " + idTicketFK + ";";
			ResultSet rs = sentencia.executeQuery(consultar);
			while (rs.next())
			{
				textArea.append("ID: " + rs.getInt("idArticuloFK") + " - " + rs.getString("descripcionArticulo")
						+ " - " + String.format("%.2f", rs.getDouble("precioArticulo")) + "€" + "\n");
			}
		}
		catch (SQLException e)
		{
			System.out.println("Error al consultar artículos");
		}
	}
	
	//Consultar Tickets. Obtiene un dato específico del ticket y devuelve su valor.
	public String datosTickets(int idTicket, String campo) 
	{
		String valor = "";
		try
		{
			String consultar = "SELECT " + campo + " FROM tickets WHERE idTicket = " + idTicket + ";";
			ResultSet rs = sentencia.executeQuery(consultar);
			while (rs.next())
			{
				valor = rs.getString(campo);
			}
		}
		catch (SQLException e)
		{
			System.out.println("Error al consultar artículos");
		}
		
		return valor;
	}
	
	//Consultar lista de tickets según intervalo de fechas.
	public void consultarListaTickets(JTextArea txaTickets, String fechaDesde, String fechaHasta)
	{
		try
		{
			String consultar = "SELECT * FROM tickets WHERE fechaTicket BETWEEN '" + fechaMysql(fechaDesde) + "' AND '" + fechaMysql(fechaHasta) + "' ORDER BY fechaTicket;";
			ResultSet rs = sentencia.executeQuery(consultar);
			System.out.println(consultar);
			while (rs.next())
			{
				txaTickets.append("Código ID: " + rs.getInt("idTicket") + " | Fecha: " + fechaEuropea(rs.getString("fechaTicket"))
						+ " | Total: " + rs.getDouble("totalTicket") + " €" + "\n");
			}
		}
		catch (SQLException e)
		{
			System.out.println("Error al consultar tickets");
		}
	}
	
	
	//VARIOS
	
	//Transformar fecha de 2025-04-08 a 08/04/2025
	public String fechaEuropea(String fechaMySQL)
	{
		String[] resultado = fechaMySQL.split("-");
		return resultado[2] + "/" + resultado[1] + "/" + resultado[0];
	}
	
	//Transformar fecha de 08/04/2025 a 2025-04-08
	public String fechaMysql(String fechaEuropea)
	{
		String fechaDevuelta = "";
		try 
		{
			String[] resultado = fechaEuropea.split("/");
			fechaDevuelta = resultado[2] + "-" + resultado[1] + "-" + resultado[0];
		}
		catch(Exception e) 
		{
			System.out.println("Error transformar fechas");
		}
		return fechaDevuelta;
	}
	
	//Rellenar comboBox artículos (Baja y Modificar)
	public void rellenarArticulos(JComboBox<String> cbArticulos) 
	{
		try
		{
			String consultar = "SELECT * FROM Articulos";
			ResultSet rs = sentencia.executeQuery(consultar);
			while (rs.next())
			{
				cbArticulos.addItem(rs.getInt("idArticulo") + " - " + rs.getString("descripcionArticulo")
						+ " - " + rs.getDouble("precioArticulo") + "€ - " + rs.getInt("cantidadArticulo") + " uds");
			}
		}
		catch (SQLException e)
		{
			System.out.println("Error al consultar artículos");
		}
	}
	
	//Rellenar comboBox tickets (Consultar)
	public void rellenarTickets(JComboBox<String> cbTickets) 
	{
		try
		{
			String consultar = "SELECT * FROM Tickets";
			ResultSet rs = sentencia.executeQuery(consultar);
			while (rs.next())
			{
				cbTickets.addItem(rs.getInt("idTicket") + " - " + fechaEuropea(rs.getString("fechaTicket")));
			}
		}
		catch (SQLException e)
		{
			System.out.println("Error al consultar tickets");
		}
	}

}
