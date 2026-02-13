package es.studium.practica;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

/**
 * Clase para seleccionar el Artículo a modificar
 * 
 * @author Andrés Martínez Romero
 * @since 1/10/2025
 * @version 1.2
 * 
 */
public class ArticuloModificar 
{
	/**
	 * Objeto de la clase Modelo
	 */
	Modelo modelo = new Modelo();
	/**
	 * Ventana de Modificar Artículos
	 */
	private JFrame vModificarArticulo;
	/**
	 * Desplegable con la lista de Artículos
	 */
	private JComboBox<String> cbArticulos;
	/**
	 * Botón Editar Datos, si hay seleccionado un artículo, abre la ventana para editar sus datos
	 */
	private JButton btnEditarDatos;
	/**
	 * Botón Volver, vuelve a la ventana anterior (Artículos)
	 */
	private JButton btnVolver;
	/**
	 * Código ID del artículo
	 */
	private int idArticulo;
	
	/**
	 * Constructor de la vista con eventos
	 * @param vPrincipal JFrame, Ventana Principal
	 */
	public ArticuloModificar(JFrame vPrincipal) 
	{
		vModificarArticulo = new JFrame();
		vModificarArticulo.setTitle("Modificar Artículo");
		vModificarArticulo.addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{
				vPrincipal.setVisible(true);
				vModificarArticulo.dispose();
			}
		});
		vModificarArticulo.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		vModificarArticulo.getContentPane().setBackground(new Color(255, 204, 204));
		vModificarArticulo.setSize(535,392);
		vModificarArticulo.setLocationRelativeTo(null);
		vModificarArticulo.setResizable(false);
		vModificarArticulo.getContentPane().setLayout(null);
		
		cbArticulos = new JComboBox<>();
		cbArticulos.setModel(new DefaultComboBoxModel<>(new String[] {"Seleccione un artículo..."}));
		modelo.rellenarArticulos(cbArticulos);
		cbArticulos.setFont(new Font("Calibri", Font.PLAIN, 18));
		cbArticulos.setBounds(125, 62, 267, 29);
		vModificarArticulo.getContentPane().add(cbArticulos);
		
		btnEditarDatos = new JButton("Editar Datos");
		btnEditarDatos.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(cbArticulos.getSelectedIndex() != 0) 
				{
					idArticulo = Integer.parseInt(cbArticulos.getSelectedItem().toString().split(" - ")[0]);
					new ArticuloModificarEditar(vPrincipal, idArticulo);
					vModificarArticulo.dispose();
				}
				else 
				{
					cbArticulos.requestFocus();
				}
			}
		});
		btnEditarDatos.setForeground(Color.BLACK);
		btnEditarDatos.setFont(new Font("Arial", Font.BOLD, 18));
		btnEditarDatos.setBackground(new Color(255, 102, 102));
		btnEditarDatos.setBounds(180, 185, 152, 54);
		vModificarArticulo.getContentPane().add(btnEditarDatos);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ev) 
			{
				new Articulos(vPrincipal);
				vModificarArticulo.dispose();
			}
		});
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setFont(new Font("Arial", Font.BOLD, 13));
		btnVolver.setBackground(Color.BLACK);
		btnVolver.setBounds(416, 314, 95, 31);
		vModificarArticulo.getContentPane().add(btnVolver);
		
		vModificarArticulo.setVisible(true);
		vModificarArticulo.requestFocusInWindow();
	}

}
