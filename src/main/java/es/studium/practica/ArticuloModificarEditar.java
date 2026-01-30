package es.studium.practica;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ArticuloModificarEditar 
{
	private JTextField txfDescripcion;
	private JTextField txfPrecio;
	private JTextField txfStock;
	public ArticuloModificarEditar(JFrame vPrincipal, int idArticulo) 
	{
		Modelo modelo = new Modelo();
		
		JFrame vEditarArticulo = new JFrame();
		vEditarArticulo.addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{
				vPrincipal.setVisible(true);
				vEditarArticulo.dispose();
			}
		});
		vEditarArticulo.getContentPane().setBackground(new Color(255, 204, 204));
		vEditarArticulo.setTitle("Editar Datos");
		vEditarArticulo.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		vEditarArticulo.setSize(535,461);
		vEditarArticulo.setLocationRelativeTo(null);
		vEditarArticulo.setResizable(false);
		vEditarArticulo.getContentPane().setLayout(null);
		
		txfDescripcion = new JTextField();
		txfDescripcion.setFont(new Font("Calibri", Font.BOLD, 18));
		txfDescripcion.setColumns(10);
		txfDescripcion.setBounds(249, 47, 137, 31);
		vEditarArticulo.getContentPane().add(txfDescripcion);
		
		JLabel lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblDescripcion.setBounds(138, 50, 101, 25);
		vEditarArticulo.getContentPane().add(lblDescripcion);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblPrecio.setBounds(182, 126, 57, 25);
		vEditarArticulo.getContentPane().add(lblPrecio);
		
		txfPrecio = new JTextField();
		txfPrecio.setFont(new Font("Calibri", Font.BOLD, 18));
		txfPrecio.setColumns(10);
		txfPrecio.setBounds(249, 123, 66, 31);
		vEditarArticulo.getContentPane().add(txfPrecio);
		
		JLabel lblEuro = new JLabel("€");
		lblEuro.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblEuro.setBounds(325, 127, 10, 23);
		vEditarArticulo.getContentPane().add(lblEuro);
		
		JLabel lblStock = new JLabel("Stock:");
		lblStock.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblStock.setBounds(190, 204, 49, 23);
		vEditarArticulo.getContentPane().add(lblStock);
		
		txfStock = new JTextField();
		txfStock.setFont(new Font("Calibri", Font.BOLD, 18));
		txfStock.setColumns(10);
		txfStock.setBounds(249, 200, 66, 31);
		vEditarArticulo.getContentPane().add(txfStock);
		
		String descripcion = modelo.datosArticulo(idArticulo, "descripcionArticulo");
		String precio = modelo.datosArticulo(idArticulo, "precioArticulo");
		String stock = modelo.datosArticulo(idArticulo, "cantidadArticulo");
		txfDescripcion.setText(descripcion);
		txfPrecio.setText(precio);
		txfStock.setText(stock);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(!txfDescripcion.getText().isBlank() && !txfPrecio.getText().isBlank() && !txfStock.getText().isBlank()) 
				{
					String descripcionArticulo = txfDescripcion.getText();
					double precioArticulo = 0;
					int stockArticulo = 0;
					try 
					{
						precioArticulo = Double.parseDouble(txfPrecio.getText());
						stockArticulo = Integer.parseInt(txfStock.getText());
						new ArticuloModificarConfirmar(vPrincipal, idArticulo, descripcionArticulo, precioArticulo, stockArticulo);
						vEditarArticulo.dispose();
					}
					catch(Exception ex)
					{
						vEditarArticulo.setVisible(false);
						new ArticuloAltaFallo1(vPrincipal, vEditarArticulo);
					}
				}
				else 
				{
					vEditarArticulo.setVisible(false);
					new ArticuloAltaFallo(vPrincipal, vEditarArticulo);
				}
			}
		});
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setFont(new Font("Arial", Font.BOLD, 18));
		btnModificar.setBackground(new Color(204, 0, 0));
		btnModificar.setBounds(182, 270, 153, 69);
		vEditarArticulo.getContentPane().add(btnModificar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				new ArticuloModificar(vPrincipal);
				vEditarArticulo.dispose();
			}
		});
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setFont(new Font("Arial", Font.BOLD, 13));
		btnVolver.setBackground(Color.BLACK);
		btnVolver.setBounds(416, 383, 95, 31);
		vEditarArticulo.getContentPane().add(btnVolver);
		
		
		vEditarArticulo.setVisible(true);
		vEditarArticulo.requestFocusInWindow();
	}
}
