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
 * Clase para el mensaje de Error por falta de datos al registrar artículo
 * 
 * @author Andrés Martínez Romero
 * @since 1/10/2025
 * @version 1.2
 * 
 */
public class ArticuloAltaFallo 
{
	/**
	 * Ventana de Mensaje
	 */
	private JFrame vFalloAltaArticulo;
	/**
	 * Botón Aceptar, vuelve al menú Alta Artículos y cierra el mensaje
	 */
	private JButton btnAceptar;
	
	/**
	 * Constructor de la vista con eventos
	 * @param vPrincipal JFrame, Ventana Principal
	 * @param vAltaArticulo JFrame, Ventana Alta Artículo
	 */
	public ArticuloAltaFallo(JFrame vPrincipal, JFrame vAltaArticulo) 
	{
		vFalloAltaArticulo = new JFrame();
		vFalloAltaArticulo.getContentPane().setBackground(new Color(220, 220, 220));
		vFalloAltaArticulo.addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{
				vAltaArticulo.setVisible(true);
				vFalloAltaArticulo.dispose();
			}
		});
		vFalloAltaArticulo.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		vFalloAltaArticulo.setTitle("Fallo en el Registro");
		vFalloAltaArticulo.setSize(514,259);
		vFalloAltaArticulo.setLocationRelativeTo(null);
		vFalloAltaArticulo.setResizable(false);
		vFalloAltaArticulo.getContentPane().setLayout(null);
		
		JLabel lblErrorRelleneTodos = new JLabel("Error: Rellene todos los datos.");
		lblErrorRelleneTodos.setForeground(new Color(128, 0, 0));
		lblErrorRelleneTodos.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblErrorRelleneTodos.setBounds(134, 50, 245, 25);
		vFalloAltaArticulo.getContentPane().add(lblErrorRelleneTodos);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				vAltaArticulo.setVisible(true);
				vFalloAltaArticulo.dispose();
			}
		});
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setFont(new Font("Arial", Font.BOLD, 15));
		btnAceptar.setBackground(new Color(102, 102, 102));
		btnAceptar.setBounds(200, 131, 107, 38);
		vFalloAltaArticulo.getContentPane().add(btnAceptar);
		
		vFalloAltaArticulo.setVisible(true);
		vFalloAltaArticulo.requestFocusInWindow();
	}
}
