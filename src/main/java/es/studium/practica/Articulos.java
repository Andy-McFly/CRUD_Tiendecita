package es.studium.practica;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Articulos 
{
	//VENTANA TIENDECITA: ARTÍCULOS
	public Articulos(JFrame vPrincipal) 
	{
		JFrame vArticulos = new JFrame();
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
		
		//Consultar artículos.
		JButton btnConsArticulos = new JButton("Consultar Artículos");
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
		
		//Modificar artículos.
		JButton btnModArticulo = new JButton("Modificar Artículo");
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
		
		//Alta de artículos.
		JButton btnRegArticulo = new JButton("Registrar Artículo");
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
		
		//Baja de artículos.
		JButton btnBorrarArticulo = new JButton("Borrar Artículo");
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
		
		JButton btnVolverArticulo = new JButton("Volver");
		btnVolverArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
