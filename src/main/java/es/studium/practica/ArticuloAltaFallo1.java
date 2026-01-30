package es.studium.practica;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import java.awt.Color;

public class ArticuloAltaFallo1 
{
	//VENTANA FALLO EN EL REGISTRO (VALORES NO VÁLIDOS)
	public ArticuloAltaFallo1(JFrame vPrincipal, JFrame vAltaArticulo) 
	{
		JFrame vFalloAltaArticulo2 = new JFrame();
		vFalloAltaArticulo2.getContentPane().setBackground(new Color(220, 220, 220));
		vFalloAltaArticulo2.addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{
				vAltaArticulo.setVisible(true);
				vFalloAltaArticulo2.dispose();
			}
		});
		vFalloAltaArticulo2.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		vFalloAltaArticulo2.setTitle("Fallo en el Registro");
		vFalloAltaArticulo2.setSize(514,259);
		vFalloAltaArticulo2.setLocationRelativeTo(null);
		vFalloAltaArticulo2.setResizable(false);
		vFalloAltaArticulo2.getContentPane().setLayout(null);
		
		JLabel lblErrorRelleneTodos = new JLabel("Error: Debe introducir valores válidos.");
		lblErrorRelleneTodos.setForeground(new Color(128, 0, 0));
		lblErrorRelleneTodos.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblErrorRelleneTodos.setBounds(98, 50, 309, 25);
		vFalloAltaArticulo2.getContentPane().add(lblErrorRelleneTodos);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				vAltaArticulo.setVisible(true);
				vFalloAltaArticulo2.dispose();
			}
		});
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setFont(new Font("Arial", Font.BOLD, 15));
		btnAceptar.setBackground(new Color(102, 102, 102));
		btnAceptar.setBounds(200, 131, 107, 38);
		vFalloAltaArticulo2.getContentPane().add(btnAceptar);
		
		
		vFalloAltaArticulo2.setVisible(true);
		vFalloAltaArticulo2.requestFocusInWindow();
	}
}
