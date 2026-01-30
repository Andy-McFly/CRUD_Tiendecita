package es.studium.practica;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;

public class ArticuloBaja 
{
	//VENTANA BORRAR ARTÍCULO
	public ArticuloBaja(JFrame vPrincipal) 
	{
		Modelo modelo = new Modelo();
		
		JFrame vBajaArticulo = new JFrame();
		vBajaArticulo.getContentPane().setBackground(new Color(255, 204, 204));
		vBajaArticulo.setTitle("Borrar Artículo");
		vBajaArticulo.addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{
				vPrincipal.setVisible(true);
				vBajaArticulo.dispose();
			}
		});
		vBajaArticulo.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		vBajaArticulo.setSize(535,392);
		vBajaArticulo.setLocationRelativeTo(null);
		vBajaArticulo.setResizable(false);
		vBajaArticulo.getContentPane().setLayout(null);
		
		JLabel lblEliminar = new JLabel("Eliminar:");
		lblEliminar.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblEliminar.setBounds(220, 48, 73, 25);
		vBajaArticulo.getContentPane().add(lblEliminar);
		
		JComboBox<String> cbArticulos = new JComboBox<>();
		cbArticulos.setModel(new DefaultComboBoxModel<>(new String[] {"Seleccione un artículo..."}));
		//Llama al método para rellenar el JComboBox con la lista de artículos.
		modelo.rellenarArticulos(cbArticulos);
		cbArticulos.setFont(new Font("Calibri", Font.PLAIN, 18));
		cbArticulos.setBounds(127, 90, 267, 29);
		vBajaArticulo.getContentPane().add(cbArticulos);
		
		//Botón "Borrar".
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//Si hay seleccionado algún artículo, aparecerá el mensaje de confirmación.
				if(cbArticulos.getSelectedIndex() != 0) 
				{
					int idArticulo = Integer.parseInt(cbArticulos.getSelectedItem().toString().split(" - ")[0]);
					new ArticuloBajaConfirmar(vPrincipal, idArticulo);
					vBajaArticulo.dispose();
				}
				else 
				{
					cbArticulos.requestFocus();
				}
			}
		});
		btnBorrar.setForeground(Color.WHITE);
		btnBorrar.setBackground(new Color(204, 0, 0));
		btnBorrar.setFont(new Font("Arial", Font.BOLD, 18));
		btnBorrar.setBounds(195, 213, 128, 54);
		vBajaArticulo.getContentPane().add(btnBorrar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				new Articulos(vPrincipal);
				vBajaArticulo.dispose();
			}
		});
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setBackground(Color.BLACK);
		btnVolver.setFont(new Font("Arial", Font.BOLD, 13));
		btnVolver.setBounds(416, 314, 95, 31);
		vBajaArticulo.getContentPane().add(btnVolver);
		
		
		vBajaArticulo.setVisible(true);
		vBajaArticulo.requestFocusInWindow();
	}
}
