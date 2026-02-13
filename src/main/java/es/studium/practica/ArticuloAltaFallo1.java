package es.studium.practica;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import java.awt.Color;

/**
 * Clase para el mensaje de Error por introducir valores no válidos al registrar artículo
 * 
 * @author Andrés Martínez Romero
 * @since 1/10/2025
 * @version 1.2
 * 
 */
public class ArticuloAltaFallo1 
{
	/**
	 * Ventana de Mensaje
	 */
	private JFrame vFalloAltaArticulo1;
	/**
	 * Botón Aceptar, vuelve al menú Alta Artículos y cierra el mensaje
	 */
	private JButton btnAceptar;
	
	/**
	 * Constructor de la vista con eventos
	 * @param vPrincipal JFrame, Ventana Principal
	 * @param vAltaArticulo JFrame, Ventana Alta Artículo
	 */
	public ArticuloAltaFallo1(JFrame vPrincipal, JFrame vAltaArticulo) 
	{
		vFalloAltaArticulo1 = new JFrame();
		vFalloAltaArticulo1.getContentPane().setBackground(new Color(220, 220, 220));
		vFalloAltaArticulo1.addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{
				vAltaArticulo.setVisible(true);
				vFalloAltaArticulo1.dispose();
			}
		});
		vFalloAltaArticulo1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		vFalloAltaArticulo1.setTitle("Fallo en el Registro");
		vFalloAltaArticulo1.setSize(514,259);
		vFalloAltaArticulo1.setLocationRelativeTo(null);
		vFalloAltaArticulo1.setResizable(false);
		vFalloAltaArticulo1.getContentPane().setLayout(null);
		
		JLabel lblErrorRelleneTodos = new JLabel("Error: Debe introducir valores válidos.");
		lblErrorRelleneTodos.setForeground(new Color(128, 0, 0));
		lblErrorRelleneTodos.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblErrorRelleneTodos.setBounds(98, 50, 309, 25);
		vFalloAltaArticulo1.getContentPane().add(lblErrorRelleneTodos);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				vAltaArticulo.setVisible(true);
				vFalloAltaArticulo1.dispose();
			}
		});
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setFont(new Font("Arial", Font.BOLD, 15));
		btnAceptar.setBackground(new Color(102, 102, 102));
		btnAceptar.setBounds(200, 131, 107, 38);
		vFalloAltaArticulo1.getContentPane().add(btnAceptar);
		
		vFalloAltaArticulo1.setVisible(true);
		vFalloAltaArticulo1.requestFocusInWindow();
	}
}
