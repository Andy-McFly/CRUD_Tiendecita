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
 * Clase para confirmar la Baja de Artículos
 * 
 * @author Andrés Martínez Romero
 * @since 1/10/2025
 * @version 1.2
 * 
 */
public class ArticuloBajaConfirmar 
{
	/**
	 * Objeto de la clase Modelo
	 */
	Modelo modelo = new Modelo();
	/**
	 * Ventana de confirmar Baja Artículo
	 */
	private JFrame vConfBajaArticulo;
	/**
	 * Botón Aceptar, procede con la baja y llama al mensaje de éxito o error
	 */
	private JButton btnAceptar;
	/**
	 * Botón Cancelar, cancela la operación de baja y vuelve al menú Baja de Artículos
	 */
	private JButton btnCancelar;
	
	/**
	 * Constructor de la vista con eventos
	 * @param vPrincipal JFrame, Ventana Principal
	 * @param idArticulo Entero, Código ID de Artículo
	 */
	public ArticuloBajaConfirmar(JFrame vPrincipal, int idArticulo) 
	{
		vConfBajaArticulo = new JFrame();
		vConfBajaArticulo.addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{
				new ArticuloBaja(vPrincipal);
				vConfBajaArticulo.dispose();
			}
		});
		vConfBajaArticulo.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		vConfBajaArticulo.getContentPane().setBackground(new Color(220, 220, 220));
		vConfBajaArticulo.setTitle("Confirmar Baja");
		vConfBajaArticulo.setSize(535,281);
		vConfBajaArticulo.setLocationRelativeTo(null);
		vConfBajaArticulo.setResizable(false);
		vConfBajaArticulo.getContentPane().setLayout(null);
		
		JLabel lblConfBaja = new JLabel("¿Seguro que desea eliminar los datos del Artículo?");
		lblConfBaja.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblConfBaja.setBounds(55, 50, 410, 25);
		vConfBajaArticulo.getContentPane().add(lblConfBaja);
		
		JLabel lblConfBaja2 = new JLabel("(Esta acción es permanente)");
		lblConfBaja2.setForeground(new Color(153, 0, 0));
		lblConfBaja2.setFont(new Font("Calibri", Font.BOLD, 20));
		lblConfBaja2.setBounds(140, 101, 234, 25);
		vConfBajaArticulo.getContentPane().add(lblConfBaja2);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(modelo.bajaArticulo(idArticulo)) 
				{
					new ArticuloBajaExito(vPrincipal);
					vConfBajaArticulo.dispose();
				}
				else 
				{
					vConfBajaArticulo.dispose();
					new FalloConexion(vPrincipal);
				}
			}
		});
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setBackground(new Color(102, 102, 102));
		btnAceptar.setFont(new Font("Arial", Font.BOLD, 15));
		btnAceptar.setBounds(96, 167, 116, 45);
		vConfBajaArticulo.getContentPane().add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ev) 
			{
				new ArticuloBaja(vPrincipal);
				vConfBajaArticulo.dispose();
			}
		});
		btnCancelar.setBackground(Color.BLACK);
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setFont(new Font("Arial", Font.BOLD, 15));
		btnCancelar.setBounds(304, 167, 116, 45);
		vConfBajaArticulo.getContentPane().add(btnCancelar);
		
		vConfBajaArticulo.setVisible(true);
		vConfBajaArticulo.requestFocusInWindow();
	}
}
