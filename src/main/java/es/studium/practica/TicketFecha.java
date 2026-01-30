package es.studium.practica;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class TicketFecha 
{
	private JTextField txfFechaDesde;
	private JTextField txfFechaHasta;
	
	public TicketFecha(JFrame vPrincipal) 
	{
		JFrame vFecha = new JFrame();
		vFecha.getContentPane().setBackground(new Color(204, 204, 255));
		vFecha.addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{
				vPrincipal.setVisible(true);
				vFecha.dispose();
			}
		});
		vFecha.setTitle("Indicar Fechas");
		vFecha.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		vFecha.setSize(371,287);
		vFecha.setLocationRelativeTo(null);
		vFecha.setResizable(false);
		vFecha.getContentPane().setLayout(null);
		
		JLabel lblFechaDesde = new JLabel("Fecha Desde:");
		lblFechaDesde.setFont(new Font("Calibri", Font.BOLD, 16));
		lblFechaDesde.setBounds(73, 83, 89, 20);
		vFecha.getContentPane().add(lblFechaDesde);
		
		JLabel lblFechaHasta = new JLabel("Fecha Hasta:");
		lblFechaHasta.setFont(new Font("Calibri", Font.BOLD, 16));
		lblFechaHasta.setBounds(73, 131, 89, 20);
		vFecha.getContentPane().add(lblFechaHasta);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String fechaDesde = txfFechaDesde.getText();
				String fechaHasta = txfFechaHasta.getText();
				
				if(!fechaDesde.isBlank() && !fechaHasta.isBlank()) 
				{
					new TicketConsultarLista(vPrincipal, fechaDesde, fechaHasta);
					vFecha.dispose();
				}
			}
		});
		btnAceptar.setFont(new Font("Arial", Font.BOLD, 14));
		btnAceptar.setBounds(130, 174, 103, 38);
		vFecha.getContentPane().add(btnAceptar);
		
		txfFechaDesde = new JTextField();
		txfFechaDesde.setFont(new Font("Calibri", Font.BOLD, 14));
		txfFechaDesde.setBounds(192, 80, 103, 26);
		vFecha.getContentPane().add(txfFechaDesde);
		txfFechaDesde.setColumns(10);
		
		txfFechaHasta = new JTextField();
		txfFechaHasta.setFont(new Font("Calibri", Font.BOLD, 14));
		txfFechaHasta.setBounds(192, 128, 103, 26);
		vFecha.getContentPane().add(txfFechaHasta);
		txfFechaHasta.setColumns(10);
		
		JLabel lblInfoFecha = new JLabel("Utilice formato dd/mm/aaaa");
		lblInfoFecha.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblInfoFecha.setBounds(97, 32, 170, 20);
		vFecha.getContentPane().add(lblInfoFecha);
		
		
		vFecha.setVisible(true);
	}
}
