package es.studium.practica;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase para introducir el nombre del autor y proceder con la creación del informe
 * 
 * @author Andrés Martínez Romero
 * @since 22/1/2026
 * @version 1.2
 * 
 */
public class NombreAutor 
{
	/**
	 * Ventana
	 */
	private JFrame vNombre;
	/**
	 * Botón Aceptar, crea el informe con su nombre y los parámetros
	 */
	private JButton btnAceptar;
	
	/**
	 * Campo de texto para escribir el nombre
	 */
	private JTextField txfNombre;
	/**
	 * Autor introducido por el usuario
	 */
	private String nombre;
	
	/**
	 * Constructor de la vista con eventos
	 * @param vPrincipal JFrame, Ventana Principal
	 * @param informe Cadena, nombre del informe
	 * @param parametros Lista, listado clave-valor con los parámetros para el informe
	 */
	public NombreAutor(JFrame vPrincipal,String informe,  HashMap<String, Object> parametros) 
	{
		vNombre = new JFrame();
		vNombre.addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{
				vPrincipal.setVisible(true);
				vNombre.dispose();
			}
		});
		vNombre.setTitle("Generar Informe");
		vNombre.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		vNombre.setSize(284,218);
		vNombre.setLocationRelativeTo(null);
		vNombre.setResizable(false);
		vNombre.getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Escribe tu nombre:");
		lblNombre.setFont(new Font("Calibri", Font.BOLD, 14));
		lblNombre.setBounds(78, 33, 114, 18);
		vNombre.getContentPane().add(lblNombre);
		
		txfNombre = new JTextField();
		txfNombre.setFont(new Font("Calibri", Font.BOLD, 13));
		txfNombre.setBounds(62, 61, 147, 30);
		vNombre.getContentPane().add(txfNombre);
		txfNombre.setColumns(10);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				nombre = txfNombre.getText();
				
				if(!nombre.isBlank()) 
				{
					parametros.put("autor", nombre);
					new Jaspersoft(informe, parametros);
					vNombre.dispose();
				}
			}
		});
		btnAceptar.setFont(new Font("Arial", Font.BOLD, 14));
		btnAceptar.setBounds(78, 119, 114, 30);
		vNombre.getContentPane().add(btnAceptar);
		
		vNombre.setVisible(true);
		
	}

}
