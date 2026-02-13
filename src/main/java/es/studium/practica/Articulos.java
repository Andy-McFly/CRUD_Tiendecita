package es.studium.practica;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Clase para el menú de gestión de Artículos
 * 
 * @author Andrés Martínez Romero
 * @since 1/10/2025
 * @version 1.2
 * 
 */
public class Articulos 
{
	/**
	 * Ventana Artículos
	 */
	private JFrame vArticulos;
	/**
	 * Botón Consultar Artículos, abre la ventana para la consulta de Artículos
	 */
	private JButton btnConsArticulos;
	/**
	 * Botón Modificar Artículo, abre la ventana para seleccionar el Artículo a modificar
	 */
	private JButton btnModArticulo;
	/**
	 * Botón Registrar Artículo, abre la ventana para el alta de Artículos
	 */
	private JButton btnRegArticulo;
	/**
	 * Botón Borrar Artículo, abre la ventana para la baja de Artículos
	 */
	private JButton btnBorrarArticulo;
	/**
	 * Botón Volver, vuelve a la ventana anterior (Principal)
	 */
	private JButton btnVolverArticulo;
	
	/**
	 * Constructor de la vista con eventos
	 * @param vPrincipal JFrame, Ventana Principal
	 */
	public Articulos(JFrame vPrincipal) 
	{
		vArticulos = new JFrame();
		vArticulos.addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{
				vPrincipal.setVisible(true);
				vArticulos.dispose();
			}
		});
		vArticulos.getContentPane().setBackground(new Color(102, 102, 102));
		vArticulos.setTitle("Tiendecita: Artículos");
		vArticulos.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		vArticulos.setSize(535,461);
		vArticulos.setLocationRelativeTo(null);
		vArticulos.setResizable(false);
		vArticulos.getContentPane().setLayout(null);
		
		btnConsArticulos = new JButton("Consultar Artículos");
		btnConsArticulos.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				new ArticuloConsultar(vPrincipal);
				vArticulos.dispose();
			}
		});
		btnConsArticulos.setBackground(new Color(255, 204, 204));
		btnConsArticulos.setFont(new Font("Arial", Font.BOLD, 20));
		btnConsArticulos.setBounds(10, 39, 501, 60);
		vArticulos.getContentPane().add(btnConsArticulos);
		btnModArticulo = new JButton("Modificar Artículo");
		btnModArticulo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				new ArticuloModificar(vPrincipal);
				vArticulos.dispose();
			}
		});
		btnModArticulo.setForeground(new Color(255, 255, 255));
		btnModArticulo.setBackground(new Color(204, 0, 0));
		btnModArticulo.setFont(new Font("Arial", Font.BOLD, 18));
		btnModArticulo.setBounds(155, 203, 214, 50);
		vArticulos.getContentPane().add(btnModArticulo);
		
		btnRegArticulo = new JButton("Registrar Artículo");
		btnRegArticulo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				new ArticuloAlta(vPrincipal);
				vArticulos.dispose();
			}
		});
		btnRegArticulo.setBackground(new Color(255, 204, 204));
		btnRegArticulo.setFont(new Font("Arial", Font.BOLD, 18));
		btnRegArticulo.setBounds(155, 125, 214, 50);
		vArticulos.getContentPane().add(btnRegArticulo);
		
		btnBorrarArticulo = new JButton("Borrar Artículo");
		btnBorrarArticulo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				new ArticuloBaja(vPrincipal);
				vArticulos.dispose();
			}
		});
		btnBorrarArticulo.setForeground(new Color(255, 255, 255));
		btnBorrarArticulo.setBackground(new Color(204, 0, 0));
		btnBorrarArticulo.setFont(new Font("Arial", Font.BOLD, 18));
		btnBorrarArticulo.setBounds(155, 280, 214, 50);
		vArticulos.getContentPane().add(btnBorrarArticulo);
		btnVolverArticulo = new JButton("Volver");
		btnVolverArticulo.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent ev) {
				vPrincipal.setVisible(true);
				vArticulos.dispose();
			}
		});
		btnVolverArticulo.setBackground(new Color(0, 0, 0));
		btnVolverArticulo.setForeground(new Color(255, 255, 255));
		btnVolverArticulo.setFont(new Font("Arial", Font.BOLD, 13));
		btnVolverArticulo.setBounds(416, 383, 95, 31);
		vArticulos.getContentPane().add(btnVolverArticulo);
	
		vArticulos.setVisible(true);
		vArticulos.requestFocusInWindow();
	}
	
}
