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
 * Clase para el mensaje de Error de conexión
 * 
 * @author Andrés Martínez Romero
 * @since 1/10/2025
 * @version 1.2
 * 
 */
public class FalloConexion 
{
	/**
	 * Ventana del mensaje
	 */
	private JFrame vFalloConexion;
	/**
	 * Botón Aceptar, vuelve al menú Principal y cierra el mensaje
	 */
	private JButton btnAceptar;
	
	/**
	 * Constructor de la vista con eventos
	 * @param vPrincipal JFrame, Ventana Principal
	 */
	public FalloConexion(JFrame vPrincipal) 
	{
		vFalloConexion = new JFrame();
		vFalloConexion.getContentPane().setBackground(new Color(220, 220, 220));
		vFalloConexion.addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{
				vPrincipal.setVisible(true);
				vFalloConexion.dispose();
			}
		});
		vFalloConexion.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		vFalloConexion.setTitle("Fallo al Conectar");
		vFalloConexion.setSize(514,259);
		vFalloConexion.setLocationRelativeTo(null);
		vFalloConexion.setResizable(false);
		vFalloConexion.getContentPane().setLayout(null);
		
		JLabel lblErrorRelleneTodos = new JLabel("Error de conexión a la base de datos.");
		lblErrorRelleneTodos.setForeground(new Color(128, 0, 0));
		lblErrorRelleneTodos.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblErrorRelleneTodos.setBounds(103, 50, 300, 25);
		vFalloConexion.getContentPane().add(lblErrorRelleneTodos);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				vPrincipal.setVisible(true);
				vFalloConexion.dispose();
			}
		});
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setFont(new Font("Arial", Font.BOLD, 15));
		btnAceptar.setBackground(new Color(102, 102, 102));
		btnAceptar.setBounds(200, 131, 107, 38);
		vFalloConexion.getContentPane().add(btnAceptar);
		
		vFalloConexion.setVisible(true);
		vFalloConexion.requestFocusInWindow();
	}
}
