package es.studium.practica;

import java.awt.Color;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Clase para el alta de Tickets
 * 
 * @author Andrés Martínez Romero
 * @since 1/10/2025
 * @version 1.2
 * 
 */
public class TicketAlta 
{
	/**
	 * Objeto de la Clase Modelo
	 */
	Modelo modelo = new Modelo();
	/**
	 * Ventana de Alta Ticket
	 */
	private JFrame vAltaTicket;
	/**
	 * Botón Crear Ticket, crea el nuevo ticket, registra sus líneas y llama al mensaje de éxito o error
	 */
	private JButton btnCrear;
	/**
	 * Botón Volver, vuelve a la ventana anterior (Tickets)
	 */
	private JButton btnVolver;
	/**
	 * Campo de texto con la fecha actual
	 */
	private JTextField txfFecha;
	/**
	 * Listado de artículos disponibles en la base de datos
	 */
	private List listArticulos;
	/**
	 * Listado de artículos añadidos al comprobante
	 */
	private List listComprobante;
	/**
	 * Precio total del ticket
	 */
	private double precioTotal = 0;
	
	/**
	 * Constructor de la vista con eventos
	 * @param vPrincipal JFrame, Ventana Principal
	 */
	public TicketAlta(JFrame vPrincipal) 
	{
		vAltaTicket = new JFrame();
		vAltaTicket.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		vAltaTicket.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				vPrincipal.setVisible(true);
				vAltaTicket.dispose();
			}
		});
		vAltaTicket.getContentPane().setBackground(new Color(204, 204, 255));
		vAltaTicket.setTitle("Nuevo Ticket");
		vAltaTicket.setSize(812,529);
		vAltaTicket.setLocationRelativeTo(null);
		vAltaTicket.setResizable(false);
		vAltaTicket.getContentPane().setLayout(null);
		
		JLabel lblAnadirArticulo = new JLabel("Añadir artículos:");
		lblAnadirArticulo.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblAnadirArticulo.setBounds(60, 37, 122, 23);
		vAltaTicket.getContentPane().add(lblAnadirArticulo);
		
		JLabel lblComprobante = new JLabel("Comprobante:");
		lblComprobante.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblComprobante.setBounds(465, 37, 107, 23);
		vAltaTicket.getContentPane().add(lblComprobante);
		
		JLabel lblTotalInfo = new JLabel("Total:");
		lblTotalInfo.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblTotalInfo.setBounds(556, 275, 42, 23);
		vAltaTicket.getContentPane().add(lblTotalInfo);
		
		JLabel lblTotal = new JLabel("0€");
		lblTotal.setFont(new Font("Calibri", Font.BOLD, 16));
		lblTotal.setBounds(612, 275, 75, 23);
		vAltaTicket.getContentPane().add(lblTotal);
		
		JLabel lblFecha = new JLabel("Fecha Exp:");
		lblFecha.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblFecha.setBounds(303, 317, 70, 23);
		vAltaTicket.getContentPane().add(lblFecha);
		
		txfFecha = new JTextField();
		txfFecha.setBackground(new Color(218, 218, 218));
		txfFecha.setEditable(false);
		txfFecha.setFocusable(false);
		txfFecha.setForeground(new Color(64, 64, 64));
		//Guarda la fecha actual y rellena el campo automáticamente.
		LocalDate hoy = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        txfFecha.setText(hoy.format(formato));
		txfFecha.setFont(new Font("Calibri", Font.BOLD, 18));
		txfFecha.setBounds(383, 313, 107, 29);
		vAltaTicket.getContentPane().add(txfFecha);
		txfFecha.setColumns(10);
		
		listArticulos = new List();
		listArticulos.setFont(new Font("Calibri", Font.BOLD, 13));
		//Llama al método para rellenar la lista de artículos.
		modelo.listado(listArticulos);
		listArticulos.setBounds(60, 66, 269, 182);
		vAltaTicket.getContentPane().add(listArticulos);
		
		listComprobante = new List();
		listComprobante.setBackground(new Color(254, 255, 198));
		listComprobante.setFont(new Font("Calibri", Font.BOLD, 13));
		listComprobante.setBounds(465, 66, 269, 182);
		vAltaTicket.getContentPane().add(listComprobante);
		
		//Añadir o Eliminar artículos del comprobante.
		listArticulos.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				//Si el usuario hace doble click en un artículo, se añade a la lista del comprobante y actualiza el precio total del ticket.
				if (e.getClickCount() == 2) 
				{
                    String articulo = listArticulos.getSelectedItem();
                    if (articulo != null) 
                    {
                    	listComprobante.add(articulo);
                    	int idArticulo = Integer.parseInt(articulo.split(" - ")[0]);
                    	double precio = Double.parseDouble(modelo.datosArticulo(idArticulo, "precioArticulo"));
                    	precioTotal = precioTotal + precio;
                    	lblTotal.setText(String.format("%.2f", precioTotal) + " €");
                    }
				}
			}
		});
		
		listComprobante.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				//Si el usuario hace doble click en un artículo del comprobante, se elimina y actualiza el precio total del ticket.
				if (e.getClickCount() == 2) 
				{
                    String articulo = listComprobante.getSelectedItem();
                    if (articulo != null) 
                    {
                    	listComprobante.remove(articulo);
                    	int idArticulo = Integer.parseInt(articulo.split(" - ")[0]);
                    	double precio = Double.parseDouble(modelo.datosArticulo(idArticulo, "precioArticulo"));
                    	precioTotal = precioTotal - precio;
                    	lblTotal.setText(String.format("%.2f", precioTotal) + " €");
                    }
				}
			}
		});
		
		btnCrear = new JButton("Crear Ticket");
		btnCrear.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//Si el comprobante no está vacío, se crea el ticket.
				if (listComprobante.getItemCount() != 0) 
				{
					int idTicket = modelo.crearTicket(precioTotal);
					int idArticulo = 0;
					
					//Registra cada línea del ticket.
					for(int i = 0; i < listComprobante.getItemCount(); i++) 
					{
						String articulo = listComprobante.getItem(i);
						idArticulo = Integer.parseInt(articulo.split(" - ")[0]);
						modelo.lineaTicket(idTicket, idArticulo);
					}
				
					new TicketAltaExito(vPrincipal);
					vAltaTicket.dispose();
				}
			}
		});
		btnCrear.setForeground(Color.WHITE);
		btnCrear.setBackground(new Color(51, 102, 255));
		btnCrear.setFont(new Font("Arial", Font.BOLD, 20));
		btnCrear.setBounds(303, 381, 187, 53);
		vAltaTicket.getContentPane().add(btnCrear);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ev) 
			{
				new Tickets(vPrincipal);
				vAltaTicket.dispose();
			}
		});
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setFont(new Font("Arial", Font.BOLD, 13));
		btnVolver.setBackground(Color.BLACK);
		btnVolver.setBounds(693, 451, 95, 31);
		vAltaTicket.getContentPane().add(btnVolver);
		
		vAltaTicket.setVisible(true);
		vAltaTicket.requestFocusInWindow();
	}
}
