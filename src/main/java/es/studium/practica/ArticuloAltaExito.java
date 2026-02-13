package es.studium.practica;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase para el mensaje de Artículo Registrado con éxito
 * 
 * @author Andrés Martínez Romero
 * @since 1/10/2025
 * @version 1.2
 * 
 */
public class ArticuloAltaExito 
{
	/**
	 * Ventana de Mensaje de éxito en el Alta
	 */
	private JFrame vExitoAltaArticulo;
	/**
	 * Botón Aceptar, vuelve al menú Artículos y cierra el mensaje
	 */
	private JButton btnAceptar;
	
	/**
	 * Constructor de la vista con eventos
	 * @param vPrincipal JFrame, Ventana Principal
	 */
	public ArticuloAltaExito(JFrame vPrincipal) 
	{
		vExitoAltaArticulo = new JFrame();
		vExitoAltaArticulo.getContentPane().setBackground(new Color(220, 220, 220));
		vExitoAltaArticulo.addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e) {
				new Articulos(vPrincipal);
				vExitoAltaArticulo.dispose();
			}
		});
		vExitoAltaArticulo.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		vExitoAltaArticulo.setTitle("Registro Completado");
		vExitoAltaArticulo.setSize(514,259);
		vExitoAltaArticulo.setLocationRelativeTo(null);
		vExitoAltaArticulo.setResizable(false);
		vExitoAltaArticulo.getContentPane().setLayout(null);
		
		JLabel lblExitoAltaArticulo = new JLabel("El Artículo se ha registrado con éxito");
		lblExitoAltaArticulo.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblExitoAltaArticulo.setBounds(99, 52, 298, 25);
		vExitoAltaArticulo.getContentPane().add(lblExitoAltaArticulo);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				new Articulos(vPrincipal);
				vExitoAltaArticulo.dispose();
			}
		});
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setBackground(new Color(102, 102, 102));
		btnAceptar.setFont(new Font("Arial", Font.BOLD, 15));
		btnAceptar.setBounds(194, 133, 107, 38);
		vExitoAltaArticulo.getContentPane().add(btnAceptar);
		
		vExitoAltaArticulo.setVisible(true);
		vExitoAltaArticulo.requestFocusInWindow();
	}

}
