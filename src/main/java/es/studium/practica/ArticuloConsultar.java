package es.studium.practica;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase para la Consulta de Artículos
 * 
 * @author Andrés Martínez Romero
 * @since 1/10/2025
 * @version 1.2
 * 
 */
public class ArticuloConsultar 
{
	/**
	 * Objeto de la clase Modelo
	 */
	Modelo modelo = new Modelo();
	/**
	 * Ventana de Consultar Artículos
	 */
	private JFrame vConsultarArticulos;
	/**
	 * Área de texto que presenta el listado de artículos
	 */
	private JTextArea txaArticulos;
	/**
	 * Botón Volver, vuelve a la ventana anterior (Artículos)
	 */
	private JButton btnVolver;
	/**
	 * Botón Generar Informe, Abre la ventana para introducir el nombre del autor del informe, 
	 * enviándole el nombre del informe y los parámetros
	 */
	private JButton btnInforme;
	/**
	 * Nombre del archivo jrxml que usará la Clase Jaspersoft para generar el informe
	 */
	private String informe;
	/**
	 * Listado clave-valor con los parámetros para el informe
	 */
	HashMap<String, Object> parametros;
	
	/**
	 * Constructor de la vista con eventos
	 * @param vPrincipal JFrame, Ventana Principal
	 */
	public ArticuloConsultar(JFrame vPrincipal) 
	{
		vConsultarArticulos = new JFrame();
		vConsultarArticulos.getContentPane().setBackground(new Color(255, 204, 204));
		vConsultarArticulos.addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{
				vPrincipal.setVisible(true);
				vConsultarArticulos.dispose();
			}
		});
		vConsultarArticulos.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		vConsultarArticulos.setTitle("Consultar Artículos");
		vConsultarArticulos.setSize(684,437);
		vConsultarArticulos.setLocationRelativeTo(null);
		vConsultarArticulos.setResizable(false);
		vConsultarArticulos.getContentPane().setLayout(null);
		
		txaArticulos = new JTextArea();
		txaArticulos.setBackground(new Color(233, 233, 233));
		//Llama al método para rellenar el área de texto con los artículos.
		modelo.consultarArticulos(txaArticulos);
		txaArticulos.setFont(new Font("Monospaced", Font.PLAIN, 13));
		txaArticulos.setEditable(false);
		txaArticulos.setBounds(10, 25, 650, 222);
		vConsultarArticulos.getContentPane().add(txaArticulos);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ev) 
			{
				new Articulos(vPrincipal);
				vConsultarArticulos.dispose();
			}
		});
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setFont(new Font("Arial", Font.BOLD, 13));
		btnVolver.setBackground(Color.BLACK);
		btnVolver.setBounds(565, 359, 95, 31);
		vConsultarArticulos.getContentPane().add(btnVolver);
		
		btnInforme = new JButton("Generar Informe");
		btnInforme.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				informe = "infArticulos";
				parametros = new HashMap<>();
				new NombreAutor(vPrincipal, informe, parametros);
			}
		});
		btnInforme.setFont(new Font("Arial", Font.BOLD, 14));
		btnInforme.setBounds(258, 290, 156, 43);
		vConsultarArticulos.getContentPane().add(btnInforme);
		
		vConsultarArticulos.setVisible(true);
		vConsultarArticulos.requestFocusInWindow();
	}
}
