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
 * Clase para el mensaje de Artículo Modificado con éxito
 * 
 * @author Andrés Martínez Romero
 * @since 1/10/2025
 * @version 1.2
 * 
 */
public class ArticuloModificarExito 
{
	/**
	 * Ventana de Mensaje de éxito en la Modificación
	 */
	private JFrame vExitoModificarArticulo;
	/**
	 * Botón Aceptar, vuelve al menú Artículos y cierra el mensaje
	 */
	private JButton btnAceptar;
	
	/**
	 * Constructor de la vista con eventos
	 * @param vPrincipal JFrame, Ventana Principal
	 */
	public ArticuloModificarExito(JFrame vPrincipal) 
	{
		vExitoModificarArticulo = new JFrame();
		vExitoModificarArticulo.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		vExitoModificarArticulo.addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e) {
				new Articulos(vPrincipal);
				vExitoModificarArticulo.dispose();
			}
		});
		vExitoModificarArticulo.setTitle("Modificación completada");
		vExitoModificarArticulo.getContentPane().setBackground(new Color(220, 220, 220));
		vExitoModificarArticulo.getContentPane().setLayout(null);
		
		JLabel lblExitoModArticulo = new JLabel("Datos del Artículo modificados correctamente");
		lblExitoModArticulo.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblExitoModArticulo.setBounds(64, 50, 374, 25);
		vExitoModificarArticulo.getContentPane().add(lblExitoModArticulo);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ev) {
				new Articulos(vPrincipal);
				vExitoModificarArticulo.dispose();
			}
		});
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setFont(new Font("Arial", Font.BOLD, 15));
		btnAceptar.setBackground(new Color(102, 102, 102));
		btnAceptar.setBounds(195, 129, 107, 38);
		vExitoModificarArticulo.getContentPane().add(btnAceptar);
		vExitoModificarArticulo.setSize(514,259);
		vExitoModificarArticulo.setLocationRelativeTo(null);
		vExitoModificarArticulo.setResizable(false);
		
		vExitoModificarArticulo.setVisible(true);
		vExitoModificarArticulo.requestFocusInWindow();
	}

}
