package es.studium.practica;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase para el menú de gestión de Tickets
 * 
 * @author Andrés Martínez Romero
 * @since 22/1/2026
 * @version 1.2
 * 
 */
public class Tickets 
{
	/**
	 * Ventana Tickets
	 */
	private JFrame vTickets;
	/**
	 * Botón Nuevo Ticket, abre la ventana para el alta de Tickets
	 */
	private JButton btnNuevoTicket;
	/**
	 * Botón Consultar Ticket, abre la ventana para seleccionar un ticket y ver sus datos
	 */
	private JButton btnConsultarTicket;
	/**
	 * Botón Lista de Tickets, abre la ventana para introducir el intervalo de fechas
	 */
	private JButton btnListaTicket;
	/**
	 * Botón Volver, Vuelve a la ventana anterior (Principal)
	 */
	private JButton btnVolverTicket;
	
	
	/**
	 * Constructor de la vista con eventos
	 * @param vPrincipal JFrame, Ventana Principal
	 */
	public Tickets(JFrame vPrincipal) 
	{
		vTickets = new JFrame();
		vTickets.getContentPane().setBackground(new Color(102, 102, 102));
		vTickets.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				vPrincipal.setVisible(true);
				vTickets.dispose();
			}
		});
		vTickets.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		vTickets.setTitle("Tiendecita: Tickets");
		vTickets.setSize(535,461);
		vTickets.setLocationRelativeTo(null);
		vTickets.setResizable(false);
		vTickets.getContentPane().setLayout(null);
		
		btnNuevoTicket = new JButton("Nuevo Ticket");
		btnNuevoTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TicketAlta(vPrincipal);
				vTickets.dispose();
			}
		});
		btnNuevoTicket.setBackground(new Color(51, 51, 255));
		btnNuevoTicket.setForeground(new Color(255, 255, 255));
		btnNuevoTicket.setFont(new Font("Arial", Font.BOLD, 22));
		btnNuevoTicket.setBounds(153, 50, 216, 103);
		vTickets.getContentPane().add(btnNuevoTicket);
		
		btnConsultarTicket = new JButton("Consultar Ticket");
		btnConsultarTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TicketConsultar(vPrincipal);
				vTickets.dispose();
			}
		});
		btnConsultarTicket.setBackground(new Color(204, 204, 255));
		btnConsultarTicket.setFont(new Font("Arial", Font.BOLD, 20));
		btnConsultarTicket.setBounds(153, 192, 216, 61);
		vTickets.getContentPane().add(btnConsultarTicket);
		
		btnVolverTicket = new JButton("Volver");
		btnVolverTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				vPrincipal.setVisible(true);
				vTickets.dispose();
			}
		});
		btnVolverTicket.setBackground(new Color(0, 0, 0));
		btnVolverTicket.setForeground(new Color(255, 255, 255));
		btnVolverTicket.setFont(new Font("Arial", Font.BOLD, 13));
		btnVolverTicket.setBounds(416, 383, 95, 31);
		vTickets.getContentPane().add(btnVolverTicket);
		
		btnListaTicket = new JButton("Lista de Tickets");
		btnListaTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TicketFecha(vPrincipal);
				vTickets.dispose();
			}
		});
		btnListaTicket.setFont(new Font("Arial", Font.BOLD, 20));
		btnListaTicket.setBounds(153, 292, 216, 61);
		vTickets.getContentPane().add(btnListaTicket);
		
		vTickets.setVisible(true);
		vTickets.requestFocusInWindow();
	}
}
