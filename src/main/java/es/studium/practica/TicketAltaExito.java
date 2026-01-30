package es.studium.practica;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TicketAltaExito 
{
	//VENTANA REGISTRO COMPLETADO (Ticket)
	public TicketAltaExito(JFrame vPrincipal) 
	{
		JFrame vExitoAltaTicket = new JFrame();
		vExitoAltaTicket.addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{
				new Tickets(vPrincipal);
				vExitoAltaTicket.dispose();
			}
		});
		vExitoAltaTicket.getContentPane().setBackground(new Color(220, 220, 220));
		vExitoAltaTicket.setTitle("Registro Completado");
		vExitoAltaTicket.setSize(514,259);
		vExitoAltaTicket.setLocationRelativeTo(null);
		vExitoAltaTicket.setResizable(false);
		vExitoAltaTicket.getContentPane().setLayout(null);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				new Tickets(vPrincipal);
				vExitoAltaTicket.dispose();
			}
		});
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setFont(new Font("Arial", Font.BOLD, 15));
		btnAceptar.setBackground(new Color(102, 102, 102));
		btnAceptar.setBounds(194, 124, 107, 38);
		vExitoAltaTicket.getContentPane().add(btnAceptar);
		
		JLabel lblExitoAltaTicket = new JLabel("El Ticket se ha registrado con éxito");
		lblExitoAltaTicket.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblExitoAltaTicket.setBounds(106, 44, 282, 25);
		vExitoAltaTicket.getContentPane().add(lblExitoAltaTicket);
		
		
		vExitoAltaTicket.setVisible(true);
		vExitoAltaTicket.requestFocusInWindow();
	}
}
