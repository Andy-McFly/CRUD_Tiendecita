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

public class ArticuloModificarConfirmar 
{
	public ArticuloModificarConfirmar(JFrame vPrincipal, int idArticulo, String descripcion, double precio, int stock) 
	{
		Modelo modelo = new Modelo();
		
		JFrame vConfModificarArticulo = new JFrame();
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
		
		//Botón "Aceptar".
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//Procede con la modificación.
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
		
		JButton btnCancelar = new JButton("Cancelar");
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
