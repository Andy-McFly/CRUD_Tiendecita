package es.studium.practica;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class TicketConsultarLista 
{
	public TicketConsultarLista(JFrame vPrincipal, String fechaDesde, String fechaHasta) 
	{
		Modelo modelo = new Modelo();
		
		JFrame vLista = new JFrame();
		vLista.getContentPane().setBackground(new Color(204, 204, 255));
		vLista.addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{
				vPrincipal.setVisible(true);
				vLista.dispose();
			}
		});
		vLista.setTitle("Lista de Tickets");
		vLista.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		vLista.setSize(548,348);
		vLista.setLocationRelativeTo(null);
		vLista.setResizable(false);
		vLista.getContentPane().setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.BOLD, 13));
		modelo.consultarListaTickets(textArea, fechaDesde, fechaHasta);
		textArea.setBounds(46, 32, 441, 131);
		vLista.getContentPane().add(textArea);
		
		JButton btnInforme = new JButton("Generar Informe");
		btnInforme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if (!textArea.getText().isBlank()) 
				{
					String informe = "infTickets";
					HashMap<String, Object> parametros = new HashMap<>();

					try 
					{
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						Date fechaD = sdf.parse(fechaDesde);
						Date fechaH = sdf.parse(fechaHasta);
						parametros.put("fecha_desde", fechaD);
						parametros.put("fecha_hasta", fechaH);

						new NombreAutor(vPrincipal, informe, parametros);
					} 
					catch (Exception e1) 
					{
						// TODO Auto-generated catch block
						System.out.println("error fechas");
					}
				}
				else 
				{
					textArea.setFont(new Font("Arial", Font.BOLD, 15));
					textArea.setForeground(Color.red);
					textArea.setBackground(Color.lightGray);
					textArea.append("Debe introducir Fechas válidas (dd/mm/aaaa)");
				}
				
			}
		});
		btnInforme.setFont(new Font("Arial", Font.BOLD, 14));
		btnInforme.setBounds(192, 202, 156, 43);
		vLista.getContentPane().add(btnInforme);
		
		JButton btnVolverTicket = new JButton("Volver");
		btnVolverTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new Tickets(vPrincipal);
				vLista.dispose();
			}
		});
		btnVolverTicket.setForeground(Color.WHITE);
		btnVolverTicket.setFont(new Font("Arial", Font.BOLD, 13));
		btnVolverTicket.setBackground(Color.BLACK);
		btnVolverTicket.setBounds(429, 270, 95, 31);
		vLista.getContentPane().add(btnVolverTicket);
		
		
		vLista.setVisible(true);
		
	}
}
