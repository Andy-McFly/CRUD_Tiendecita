package es.studium.practica;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase para confirmar la Modificación de Artículos
 * 
 * @author Andrés Martínez Romero
 * @since 1/10/2025
 * @version 1.2
 * 
 */
public class ArticuloModificarConfirmar 
{
	/**
	 * Objeto de la clase Modelo
	 */
	Modelo modelo = new Modelo();
	/**
	 * Ventana de confirmar Modificar Artículo
	 */
	private JFrame vConfModificarArticulo;
	/**
	 * Botón Aceptar, procede con la modificación y llama al mensaje de éxito o error
	 */
	private JButton btnAceptar;
	/**
	 * Botón Cancelar, cancela la operación de modificación y vuelve al menú Modificar Artículos
	 */
	private JButton btnCancelar;
	
	/**
	 * Constructor de la vista con eventos
	 * @param vPrincipal JFrame, Ventana Principal
	 * @param idArticulo Entero, Código ID del Artículo
	 * @param descripcion Cadena, Descripción del artículo
	 * @param precio Decimal, Precio del Artículo
	 * @param stock Entero, Stock del Artículo
	 */
	public ArticuloModificarConfirmar(JFrame vPrincipal, int idArticulo, String descripcion, double precio, int stock) 
	{
		vConfModificarArticulo = new JFrame();
		vConfModificarArticulo.addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{
				new ArticuloModificar(vPrincipal);
				vConfModificarArticulo.dispose();
			}
		});
		vConfModificarArticulo.getContentPane().setBackground(new Color(220, 220, 220));
		vConfModificarArticulo.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		vConfModificarArticulo.setTitle("Confirmar Modificación");
		vConfModificarArticulo.setSize(535,281);
		vConfModificarArticulo.setLocationRelativeTo(null);
		vConfModificarArticulo.setResizable(false);
		vConfModificarArticulo.getContentPane().setLayout(null);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(modelo.modificarArticulo(idArticulo, descripcion, precio, stock)) 
				{
					new ArticuloModificarExito(vPrincipal);
					vConfModificarArticulo.dispose();
				}
				else 
				{
					vConfModificarArticulo.dispose();
					new FalloConexion(vPrincipal);
				}
			}
		});
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setFont(new Font("Arial", Font.BOLD, 15));
		btnAceptar.setBackground(new Color(102, 102, 102));
		btnAceptar.setBounds(95, 160, 116, 45);
		vConfModificarArticulo.getContentPane().add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				new ArticuloModificar(vPrincipal);
				vConfModificarArticulo.dispose();
			}
		});
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setFont(new Font("Arial", Font.BOLD, 15));
		btnCancelar.setBackground(Color.BLACK);
		btnCancelar.setBounds(303, 160, 116, 45);
		vConfModificarArticulo.getContentPane().add(btnCancelar);
		
		JLabel lblConfModificar2 = new JLabel("(Esta acción es permanente)");
		lblConfModificar2.setForeground(new Color(153, 0, 0));
		lblConfModificar2.setFont(new Font("Calibri", Font.BOLD, 20));
		lblConfModificar2.setBounds(139, 94, 234, 25);
		vConfModificarArticulo.getContentPane().add(lblConfModificar2);
		
		JLabel lblConfModificar = new JLabel("¿Seguro que desea modificar los datos del Artículo?");
		lblConfModificar.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblConfModificar.setBounds(49, 43, 422, 25);
		vConfModificarArticulo.getContentPane().add(lblConfModificar);
		
		vConfModificarArticulo.setVisible(true);
		vConfModificarArticulo.requestFocusInWindow();
	}

}
