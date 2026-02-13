package es.studium.practica;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Clase para el mensaje de confirmar salir
 * 
 * @author Andrés Martínez Romero
 * @since 1/10/2025
 * @version 1.2
 * 
 */
public class PrincipalSalir 
{
	/**
	 * Ventana del mensaje
	 */
	private JFrame vConfSalir;
	/**
	 * Botón Sí, cierra el programa
	 */
	private JButton btnSalirSi;
	/**
	 * Botón No, vuelve al menú principal
	 */
	private JButton btnSalirNo;
	
	/**
	 * Constructor de la vista con eventos
	 * @param vPrincipal JFrame, Ventana Principal
	 */
	public PrincipalSalir(JFrame vPrincipal) 
	{
		vConfSalir = new JFrame();
		vConfSalir.getContentPane().setBackground(new Color(220, 220, 220));
		vConfSalir.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		vConfSalir.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				vPrincipal.setVisible(true);
				vConfSalir.dispose();
			}
		});
		vConfSalir.setTitle("Confirmar Salir");
		vConfSalir.setSize(535,253);
		vConfSalir.setLocationRelativeTo(null);
		vConfSalir.setResizable(false);
		vConfSalir.getContentPane().setLayout(null);
		
		btnSalirSi = new JButton("Sí");
		btnSalirSi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalirSi.setForeground(new Color(255, 255, 255));
		btnSalirSi.setBackground(new Color(102, 102, 102));
		btnSalirSi.setFont(new Font("Arial", Font.BOLD, 15));
		btnSalirSi.setBounds(116, 136, 93, 33);
		vConfSalir.getContentPane().add(btnSalirSi);
		
		btnSalirNo = new JButton("No");
		btnSalirNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vPrincipal.setVisible(true);
				vConfSalir.dispose();
			}
		});
		btnSalirNo.setForeground(new Color(255, 255, 255));
		btnSalirNo.setBackground(new Color(102, 102, 102));
		btnSalirNo.setFont(new Font("Arial", Font.BOLD, 15));
		btnSalirNo.setBounds(302, 136, 93, 33);
		vConfSalir.getContentPane().add(btnSalirNo);
		
		JLabel lblConfSalir = new JLabel("¿Cerrar el programa y salir?");
		lblConfSalir.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblConfSalir.setBounds(141, 51, 227, 25);
		vConfSalir.getContentPane().add(lblConfSalir);
		
		vConfSalir.setVisible(true);
		vConfSalir.requestFocusInWindow();
	}
}
