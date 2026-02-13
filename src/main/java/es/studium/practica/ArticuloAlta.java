package es.studium.practica;

import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Clase para el alta de Artículos
 * 
 * @author Andrés Martínez Romero
 * @since 1/10/2025
 * @version 1.2
 * 
 */
public class ArticuloAlta 
{
	/**
	 * Objeto de la clase Modelo
	 */
	Modelo modelo = new Modelo();
	/**
	 * Ventana de Alta Artículo
	 */
	private JFrame vAltaArticulo;
	/**
	 * Campo de texto para indicar el Stock en almacén
	 */
	private JTextField txfStock;
	/**
	 * Campo de texto para indicar una descripción o nombre del artículo
	 */
	private JTextField txfDescripcion;
	/**
	 * Campo de texto para indicar el precio del artículo
	 */
	private JTextField txfPrecio;
	/**
	 * Botón Registrar, para dar de alta un artículo
	 */
	private JButton btnRegistrar;
	/**
	 * Botón Volver, vuelve a la ventana Artículos
	 */
	private JButton btnVolver;
	/**
	 * Descripción del artículo introducida por el usuario
	 */
	private String descripcionArticulo;
	/**
	 * Precio del artículo introducido por el usuario
	 */
	private double precioArticulo;
	/**
	 * Stock del artículo introducido por el usuario
	 */
	private int stockArticulo;
	
	/**
	 * Constructor de la vista con eventos
	 * @param vPrincipal JFrame, Ventana Principal
	 */
	public ArticuloAlta(JFrame vPrincipal) 
	{
		vAltaArticulo = new JFrame();
		vAltaArticulo.setTitle("Registrar Artículo");
		vAltaArticulo.getContentPane().setBackground(new Color(255, 204, 204));
		vAltaArticulo.addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{
				vPrincipal.setVisible(true);
				vAltaArticulo.dispose();
			}
		});
		vAltaArticulo.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		vAltaArticulo.setSize(535,461);
		vAltaArticulo.setLocationRelativeTo(null);
		vAltaArticulo.setResizable(false);
		vAltaArticulo.getContentPane().setLayout(null);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//Comprueba si se han rellenado todos los datos y asigna esos valores a las variables.
				if(!txfDescripcion.getText().isBlank() && !txfPrecio.getText().isBlank() && !txfStock.getText().isBlank()) 
				{
					descripcionArticulo = txfDescripcion.getText();
					precioArticulo = 0;
					stockArticulo = 0;
					
					//Procede con el alta. Si hay fallo en el parseo, indicará al usuario que escriba valores válidos.
					try 
					{
						precioArticulo = Double.parseDouble(txfPrecio.getText());
						stockArticulo = Integer.parseInt(txfStock.getText());
						if(modelo.altaArticulo(descripcionArticulo, precioArticulo, stockArticulo)) 
						{
							vAltaArticulo.dispose();
							new ArticuloAltaExito(vPrincipal);
						}
						else 
						{
							vAltaArticulo.dispose();
							new FalloConexion(vPrincipal);
						}
					}
					catch(Exception ex)
					{
						vAltaArticulo.setVisible(false);
						new ArticuloAltaFallo1(vPrincipal, vAltaArticulo);
					}
				}
				else 
				{
					vAltaArticulo.setVisible(false);
					new ArticuloAltaFallo(vPrincipal, vAltaArticulo);
				}
			}
		});
		btnRegistrar.setBackground(new Color(255, 102, 102));
		btnRegistrar.setFont(new Font("Arial", Font.BOLD, 18));
		btnRegistrar.setBounds(182, 270, 153, 69);
		vAltaArticulo.getContentPane().add(btnRegistrar);

		btnVolver = new JButton("Volver");
		btnVolver.setBackground(Color.BLACK);
		btnVolver.setForeground(Color.WHITE);
		btnVolver.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ev) 
			{
				new Articulos(vPrincipal);
				vAltaArticulo.dispose();
			}
		});
		btnVolver.setFont(new Font("Arial", Font.BOLD, 13));
		btnVolver.setBounds(416, 383, 95, 31);
		vAltaArticulo.getContentPane().add(btnVolver);

		JLabel lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblDescripcion.setBounds(138, 50, 101, 25);
		vAltaArticulo.getContentPane().add(lblDescripcion);
		
		txfStock = new JTextField();
		txfStock.setFont(new Font("Calibri", Font.BOLD, 18));
		txfStock.setBounds(249, 200, 66, 31);
		vAltaArticulo.getContentPane().add(txfStock);
		txfStock.setColumns(10);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblPrecio.setBounds(182, 126, 57, 25);
		vAltaArticulo.getContentPane().add(lblPrecio);
		
		txfDescripcion = new JTextField();
		txfDescripcion.setFont(new Font("Calibri", Font.BOLD, 18));
		txfDescripcion.setBounds(249, 47, 137, 31);
		vAltaArticulo.getContentPane().add(txfDescripcion);
		txfDescripcion.setColumns(10);
		
		JLabel lblEuro = new JLabel("€");
		lblEuro.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblEuro.setBounds(325, 127, 10, 23);
		vAltaArticulo.getContentPane().add(lblEuro);
		
		JLabel lblStock = new JLabel("Stock:");
		lblStock.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblStock.setBounds(190, 204, 49, 23);
		vAltaArticulo.getContentPane().add(lblStock);
		
		txfPrecio = new JTextField();
		txfPrecio.setFont(new Font("Calibri", Font.BOLD, 18));
		txfPrecio.setBounds(249, 123, 66, 31);
		vAltaArticulo.getContentPane().add(txfPrecio);
		txfPrecio.setColumns(10);
		
		
		vAltaArticulo.setVisible(true);
		vAltaArticulo.requestFocusInWindow();
	}
}
