package es.studium.practica;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TicketConsultarDatos 
{
	//VENTANA DATOS TICKET
	public TicketConsultarDatos(JFrame vPrincipal, int idTicket) 
	{
		Modelo modelo = new Modelo();
		
		JFrame vDatosTicket = new JFrame();
		vDatosTicket.addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{
				vPrincipal.setVisible(true);
				vDatosTicket.dispose();
			}
		});
		vDatosTicket.getContentPane().setBackground(new Color(204, 204, 255));
		vDatosTicket.setTitle("Datos Ticket");
		vDatosTicket.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		vDatosTicket.setSize(557,449);
		vDatosTicket.setLocationRelativeTo(null);
		vDatosTicket.setResizable(false);
		vDatosTicket.getContentPane().setLayout(null);
		
		JLabel lblTicketInfo = new JLabel("Ticket:");
		lblTicketInfo.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblTicketInfo.setBounds(216, 48, 49, 20);
		vDatosTicket.getContentPane().add(lblTicketInfo);
		
		JLabel lblTicketID = new JLabel("");
		lblTicketID.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblTicketID.setBounds(275, 48, 122, 20);
		vDatosTicket.getContentPane().add(lblTicketID);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		//Llama al método para rellenar el área de texto con las líneas del ticket.
		modelo.rellenarTextArea(textArea, idTicket);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea.setBounds(125, 78, 302, 178);
		vDatosTicket.getContentPane().add(textArea);
		
		JLabel lblExp = new JLabel("Exp:");
		lblExp.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblExp.setBounds(125, 289, 31, 20);
		vDatosTicket.getContentPane().add(lblExp);
		
		JLabel lblFecha = new JLabel("");
		lblFecha.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblFecha.setBounds(166, 289, 122, 20);
		vDatosTicket.getContentPane().add(lblFecha);
		
		JLabel lblTotalInfo = new JLabel("Total:");
		lblTotalInfo.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblTotalInfo.setBounds(298, 289, 42, 20);
		vDatosTicket.getContentPane().add(lblTotalInfo);
		
		JLabel lblTotal = new JLabel("");
		lblTotal.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblTotal.setBounds(350, 289, 108, 20);
		vDatosTicket.getContentPane().add(lblTotal);
		
		//Consulta los datos del ticket para rellenar las etiquetas correspondientes.
		String fecha = modelo.datosTickets(idTicket, "fechaTicket");
		String total = modelo.datosTickets(idTicket, "totalTicket");
		lblTicketID.setText("ID " + idTicket);
		lblFecha.setText(modelo.fechaEuropea(fecha));
		lblTotal.setText(total + " €");
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				new TicketConsultar(vPrincipal);
				vDatosTicket.dispose();
			}
		});
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setFont(new Font("Arial", Font.BOLD, 13));
		btnVolver.setBackground(Color.BLACK);
		btnVolver.setBounds(438, 371, 95, 31);
		vDatosTicket.getContentPane().add(btnVolver);
		
		
		vDatosTicket.setVisible(true);
		vDatosTicket.requestFocusInWindow();
	}
}
