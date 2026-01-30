package es.studium.practica;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tickets 
{
	public Tickets(JFrame vPrincipal) 
	{
		JFrame vTickets = new JFrame();
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
		
		JButton btnNuevoTicket = new JButton("Nuevo Ticket");
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
		
		JButton btnConsultarTicket = new JButton("Consultar Ticket");
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
		
		JButton btnVolverTicket = new JButton("Volver");
		btnVolverTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vPrincipal.setVisible(true);
				vTickets.dispose();
			}
		});
		btnVolverTicket.setBackground(new Color(0, 0, 0));
		btnVolverTicket.setForeground(new Color(255, 255, 255));
		btnVolverTicket.setFont(new Font("Arial", Font.BOLD, 13));
		btnVolverTicket.setBounds(416, 383, 95, 31);
		vTickets.getContentPane().add(btnVolverTicket);
		
		JButton btnListaTicket = new JButton("Lista de Tickets");
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
