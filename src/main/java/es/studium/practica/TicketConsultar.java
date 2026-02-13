package es.studium.practica;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase para seleccionar un Ticket y consultar sus datos
 * 
 * @author Andrés Martínez Romero
 * @since 1/10/2025
 * @version 1.2
 * 
 */
public class TicketConsultar 
{
	/**
	 * Objeto de la Clase Modelo
	 */
	Modelo modelo = new Modelo();
	/**
	 * Ventana de selección de Ticket
	 */
	private JFrame vConsultarTicket;
	/**
	 * Desplegable con la lista de Tickets
	 */
	private JComboBox<String> cbTickets;
	/**
	 * Botón Ver Datos, si hay seleccionado un ticket, abre la ventana para ver sus datos
	 */
	private JButton btnVerDatos;
	/**
	 * Botón Volver, vuelve a la ventana anterior (Tickets)
	 */
	private JButton btnVolver;
	/**
	 * Código ID del ticket seleccionado
	 */
	private int idTicket;
	
	/**
	 * Constructor de la vista con eventos
	 * @param vPrincipal JFrame, Ventana Principal
	 */
	public TicketConsultar(JFrame vPrincipal) 
	{
		vConsultarTicket = new JFrame();
		vConsultarTicket.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		vConsultarTicket.addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{
				vPrincipal.setVisible(true);
				vConsultarTicket.dispose();
			}
		});
		vConsultarTicket.setTitle("Consultar Tickets");
		vConsultarTicket.getContentPane().setBackground(new Color(204, 204, 255));
		vConsultarTicket.getContentPane().setLayout(null);
		
		cbTickets = new JComboBox<>();
		cbTickets.setModel(new DefaultComboBoxModel<>(new String[] {"Seleccione un Ticket..."}));
		//Llama al método para rellenar el JComboBox con la lista de tickets.
		modelo.rellenarTickets(cbTickets);
		cbTickets.setFont(new Font("Calibri", Font.BOLD, 16));
		cbTickets.setBounds(82, 47, 198, 29);
		vConsultarTicket.getContentPane().add(cbTickets);
		
		btnVerDatos = new JButton("Ver Datos");
		btnVerDatos.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//Guarda el ID del ticket seleccionado y lo envía a la ventana para ver sus datos.
				if(cbTickets.getSelectedIndex() != 0) 
				{
					idTicket = Integer.parseInt(cbTickets.getSelectedItem().toString().split(" - ")[0]);
					new TicketConsultarDatos(vPrincipal, idTicket);
					vConsultarTicket.dispose();
				}
				else 
				{
					cbTickets.requestFocus();
				}
			}
		});
		btnVerDatos.setBackground(new Color(217, 217, 217));
		btnVerDatos.setFont(new Font("Arial", Font.BOLD, 15));
		btnVerDatos.setBounds(125, 162, 114, 44);
		vConsultarTicket.getContentPane().add(btnVerDatos);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ev) 
			{
				new Tickets(vPrincipal);
				vConsultarTicket.dispose();
			}
		});
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setFont(new Font("Arial", Font.BOLD, 13));
		btnVolver.setBackground(Color.BLACK);
		btnVolver.setBounds(264, 237, 95, 31);
		vConsultarTicket.getContentPane().add(btnVolver);
		vConsultarTicket.setSize(383,315);
		vConsultarTicket.setLocationRelativeTo(null);
		vConsultarTicket.setResizable(false);
		
		vConsultarTicket.setVisible(true);
		vConsultarTicket.requestFocusInWindow();
	}
}
