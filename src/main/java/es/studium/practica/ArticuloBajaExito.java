package es.studium.practica;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase para el mensaje de Artículo Eliminado con éxito
 * 
 * @author Andrés Martínez Romero
 * @since 1/10/2025
 * @version 1.2
 * 
 */
public class ArticuloBajaExito 
{
	/**
	 * Ventana de Mensaje
	 */
	private JFrame vExitoBajaArticulo;
	/**
	 * Botón Aceptar, vuelve al menú Artículos y cierra el mensaje
	 */
	private JButton btnAceptar;
	
	/**
	 * Constructor de la vista con eventos
	 * @param vPrincipal JFrame, Ventana Principal
	 */
	public ArticuloBajaExito(JFrame vPrincipal) 
	{
		vExitoBajaArticulo = new JFrame();
		vExitoBajaArticulo.addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e) {
				new Articulos(vPrincipal);
				vExitoBajaArticulo.dispose();
			}
		});
		vExitoBajaArticulo.getContentPane().setBackground(new Color(220, 220, 220));
		vExitoBajaArticulo.getContentPane().setLayout(null);
		
		JLabel lblExitoBajaArticulo = new JLabel("El Artículo ha sido eliminado del registro");
		lblExitoBajaArticulo.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblExitoBajaArticulo.setBounds(84, 49, 330, 25);
		vExitoBajaArticulo.getContentPane().add(lblExitoBajaArticulo);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ev) {
				new Articulos(vPrincipal);
				vExitoBajaArticulo.dispose();
			}
		});
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setFont(new Font("Arial", Font.BOLD, 15));
		btnAceptar.setBackground(new Color(102, 102, 102));
		btnAceptar.setBounds(195, 126, 107, 38);
		vExitoBajaArticulo.getContentPane().add(btnAceptar);
		vExitoBajaArticulo.setTitle("Baja Completada");
		vExitoBajaArticulo.setSize(514,259);
		vExitoBajaArticulo.setLocationRelativeTo(null);
		vExitoBajaArticulo.setResizable(false);
		
		vExitoBajaArticulo.setVisible(true);
		vExitoBajaArticulo.requestFocusInWindow();
	}

}
