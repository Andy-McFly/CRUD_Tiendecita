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

/**
 * Clase Modelo
 * 
 * @author Andrés Martínez Romero
 * @since 1/10/2025
 * @version 1.2
 * 
 */
public class Modelo 
{
	/**
	 * Conexión a la base de datos
	 */
	Connection dbcon;
	/**
	 * Sentencia SQL
	 */
	Statement sentencia;
	/**
	 * Driver para la conexión
	 */
	private String driver;
	/**
	 * Dirección de la base de datos
	 */
	private String sourceURL;
	/**
	 * Usuario de la base de datos
	 */
	private String usuario;
	/**
	 * contraseña de la base de datos
	 */
	private String contraseña;

	/**
	 * Constructor de la clase.
	 * Establece la conexión a la BD accediendo con los datos del fichero externo datos.txt
	 */
	public Modelo() 
	{
		try 
		{
			//Lectura de datos desde un fichero externo.
			FileReader fr = new FileReader("datos.txt");
			BufferedReader br = new BufferedReader(fr);
			driver = br.readLine();
			Class.forName(driver);
			sourceURL = br.readLine();
			usuario = br.readLine();
			contraseña = br.readLine();
			
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
	
	/**
	 * Inserta un nuevo Artículo en la base de datos
	 * @param descripcion Cadena, descripción del artículo
	 * @param precio Decimal, precio por unidad del artículo
	 * @param cantidad Entero, cantidad disponible en stock
	 * @return Boolean, true si el artículo se insertó correctamente, false si ocurrió un error
	 */
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
	
	/**
	 * Elimina un Artículo permanentemente de la base de datos
	 * Es un borrado en cascada, elimina también las líneas de tickets relacionadas con el artículo
	 * @param idArticulo Entero, Código ID del artículo
	 * @return Boolean, true si el artículo se eliminó correctamente, false si ocurrió un error
	 */
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
	
	/**
	 * Elimina las líneas de los tickets que incluyan el artículo recibido
	 * @param idArticuloFK Entero, Código ID del artículo relacionado en la línea
	 */
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
	
	/**
	 * Modifica los campos de un Artículo de la base de datos
	 * @param id Entero, Código ID del artículo
	 * @param descripcion Cadena, descripción del artículo
	 * @param precio Decimal, precio por unidad del artículo
	 * @param stock Entero, cantidad disponible en stock
	 * @return Boolean, true si el artículo se modificó correctamente, false si ocurrió un error
	 */
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
	
	/**
	 * Lee el valor del campo de un artículo desde la base de datos
	 * @param idArticulo Entero, Código ID del artículo
	 * @param campo Cadena, campo del que obtener el valor
	 * @return Cadena, valor del campo
	 */
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
	
	/**
	 * Consulta el listado de artículos en la base de datos y los añade al área de texto
	 * @param txaArticulos Área de texto, muestra el listado de artículos
	 */
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
	
	/**
	 * Inserta un nuevo Ticket en la base de datos
	 * @param total Decimal, precio total del ticket
	 * @return Entero, Código ID del ticket recién insertado
	 */
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
	
	/**
	 * Inserta una nueva línea en el ticket
	 * @param idTicket Entero, Código ID del Ticket
	 * @param idArticulo Entero, Código ID del Artículo
	 */
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
	
	/**
	 * Añade todos los artículos en la base de datos a la lista
	 * @param listArticulos Lista, listado de los artículos
	 */
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
	
	/**
	 * Consulta las líneas de un ticket y las añade a un área de texto
	 * @param textArea Área de texto, muestra las líneas de un ticket
	 * @param idTicketFK Entero, Código ID del ticket
	 */
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
	
	/**
	 * Lee el valor del campo de un ticket desde la base de datos
	 * @param idTicket Entero, Código ID del ticket
	 * @param campo Cadena, campo del que obtener el valor
	 * @return Cadena, valor del campo
	 */
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
	
	/**
	 * Consulta el listado de tickets que estén dentro del intervalo de fechas indicado
	 * @param txaTickets Área de texto, muestra la lista de tickets tras aplicar el filtro
	 * @param fechaDesde Cadena, fecha de inicio del intervalo
	 * @param fechaHasta Cadena, fecha de fin del intervalo
	 */
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
	
	/**
	 * Transforma el formato de la fecha
	 * @param fechaMySQL Cadena, fecha en formato aaaa-mm-dd
	 * @return Cadena, fecha en formato dd/mm/aaaa
	 */
	public String fechaEuropea(String fechaMySQL)
	{
		String[] resultado = fechaMySQL.split("-");
		return resultado[2] + "/" + resultado[1] + "/" + resultado[0];
	}
	
	/**
	 * Transforma el formato de la fecha
	 * @param fechaEuropea Cadena, fecha en formato dd/mm/aaaa
	 * @return Cadena, fecha en formato aaaa-mm-dd
	 */
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
	
	/**
	 * Añade la lista de artículos al desplegable
	 * @param cbArticulos Lista desplegable, contiene los artículos de la base de datos
	 */
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
	
	/**
	 * Añade la lista de tickets al desplegable
	 * @param cbTickets Lista desplegable, contiene los tickets de la base de datos
	 */
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
