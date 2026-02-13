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

/**
 * Clase para mostrar el listado de tickets dentro de las fechas establecidas
 * 
 * @author Andrés Martínez Romero
 * @since 22/1/2026
 * @version 1.2
 * 
 */
public class TicketConsultarLista 
{
	/**
	 * Objeto de la Clase Modelo
	 */
	Modelo modelo = new Modelo();
	/**
	 * Ventana para mostrar el listado de tickets
	 */
	private JFrame vLista;
	/**
	 * Área de texto con el listado de tickets
	 */
	private JTextArea textArea;
	/**
	 * Botón Generar Informe, guarda el nombre del informe y sus parámetros, abre la ventana para introducir el autor
	 */
	private JButton btnInforme;
	/**
	 * Botón Volver, vuelve a la ventana anterior (Tickets)
	 */
	private JButton btnVolverTicket;
	/**
	 * Nombre del informe para el archivo .jrxml
	 */
	private String informe;
	/**
	 * Lista de parámetros para el informe
	 */
	HashMap<String, Object> parametros;
	
	/**
	 * Constructor de la vista con eventos
	 * @param vPrincipal JFrame, Ventana Principal
	 * @param fechaDesde Cadena, fecha de inicio
	 * @param fechaHasta Cadena, fecha de fin
	 */
	public TicketConsultarLista(JFrame vPrincipal, String fechaDesde, String fechaHasta) 
	{
		vLista = new JFrame();
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
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.BOLD, 13));
		modelo.consultarListaTickets(textArea, fechaDesde, fechaHasta);
		textArea.setBounds(46, 32, 441, 131);
		vLista.getContentPane().add(textArea);
		
		btnInforme = new JButton("Generar Informe");
		btnInforme.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (!textArea.getText().isBlank()) 
				{
					informe = "infTickets";
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
		
		btnVolverTicket = new JButton("Volver");
		btnVolverTicket.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ev) 
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
