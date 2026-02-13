package es.studium.practica;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase Principal del programa (Menú principal)
 * 
 * @author Andrés Martínez Romero
 * @since 1/10/2025
 * @version 1.2
 * 
 */
public class Principal
{
	/**
	 * Ventana del Menú Principal
	 */
	private JFrame vPrincipal;
	/**
	 * Botón Tickets, muestra el menú para la gestión de los Tickets
	 */
	private JButton btnTickets;
	/**
	 * Botón Artículos, muestra el menú para la gestión de los Artículos
	 */
	private JButton btnArticulos;
	
	/**
	 * Constructor de la vista con eventos
	 */
	public Principal() 
	{
		vPrincipal = new JFrame();
		vPrincipal.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		vPrincipal.getContentPane().setBackground(new Color(102, 102, 102));
		vPrincipal.addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{
				new PrincipalSalir(vPrincipal);
				vPrincipal.setVisible(false);
			}
		});
		vPrincipal.setTitle("Tiendecita");
		vPrincipal.setSize(526,334);
		vPrincipal.setLocationRelativeTo(null);
		vPrincipal.setResizable(false);
		vPrincipal.getContentPane().setLayout(null);
		
		JLabel lblMenuPrincipal = new JLabel("Menú Principal");
		lblMenuPrincipal.setForeground(new Color(255, 255, 255));
		lblMenuPrincipal.setFont(new Font("Calibri", Font.BOLD, 24));
		lblMenuPrincipal.setBounds(185, 34, 150, 30);
		vPrincipal.getContentPane().add(lblMenuPrincipal);
		
		btnTickets = new JButton("Tickets");
		btnTickets.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				new Tickets(vPrincipal);
				vPrincipal.setVisible(false);
			}
		});
		btnTickets.setBackground(new Color(204, 204, 255));
		btnTickets.setFont(new Font("Calibri", Font.BOLD, 18));
		btnTickets.setBounds(177, 181, 164, 53);
		vPrincipal.getContentPane().add(btnTickets);
		
		btnArticulos = new JButton("Artículos");
		btnArticulos.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				new Articulos(vPrincipal);
				vPrincipal.setVisible(false);
			}
		});
		btnArticulos.setBackground(new Color(255, 140, 140));
		btnArticulos.setFont(new Font("Calibri", Font.BOLD, 18));
		btnArticulos.setBounds(177, 89, 164, 53);
		vPrincipal.getContentPane().add(btnArticulos);
		
		vPrincipal.setVisible(true);
		vPrincipal.requestFocusInWindow();
	}

	/**
	 * Método principal del programa Tiendecita
	 * @param args
	 */
	public static void main(String[] args) 
	{
		new Principal();
	}
}
