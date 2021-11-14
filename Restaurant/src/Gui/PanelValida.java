package Gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;


import javax.swing.JPasswordField;

public class PanelValida extends JPanel {
	private JComboBox<Trabajador> comboBoxUser;
	private JPasswordField passwordField;
	private Camarero cam = new Camarero();
	private Repartidor rep = new Repartidor();
	private GestorBotones gestotBotones = new GestorBotones();
	
	/**
	 * Create the panel for user validate.
	 */
	public PanelValida(List<Trabajador> trabajadores) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		//gridBagLayout.columnWidths = new int[]{0, 73, 131, 0, 101, 89, 0};
		//gridBagLayout.rowHeights = new int[]{37, 35, 35, 35, 35, 0};
		//gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		//gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblInfo = new JLabel("Seleccione su usuario");
		lblInfo.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_MiConstraint= new GridBagConstraints();
		gbc_MiConstraint.gridx = 1;		 //columna inicial que ocupa
		gbc_MiConstraint.gridy = 0;		 //fila inicial que ocupa
		gbc_MiConstraint.gridwidth = 3;  //Columnas que ocupa
		gbc_MiConstraint.gridheight = 1; //Filas que ocupa
		gbc_MiConstraint.fill = GridBagConstraints.HORIZONTAL; //ajusta el componente a la celda
		gbc_MiConstraint.insets = new Insets(0, 50, 10, 0); //Espacio hasta los bordes del componente en la celda
														  //top, left, botton, right
		add(lblInfo, gbc_MiConstraint);
		
		JLabel lblOperador = new JLabel("Operador:");
		gbc_MiConstraint.gridx = 1;
		gbc_MiConstraint.gridy = 1;
		gbc_MiConstraint.gridwidth = 1;
		gbc_MiConstraint.fill = GridBagConstraints.NONE;
		//gbc_MiConstraint.anchor = GridBagConstraints.EAST;
		gbc_MiConstraint.insets = new Insets(0, 5, 5, 5);
		add(lblOperador, gbc_MiConstraint);
		
		comboBoxUser = new JComboBox<Trabajador>();
		for(Trabajador tr : trabajadores) {
			if(tr.getClass().equals(cam.getClass()) || tr.getClass().equals(rep.getClass())) {
				comboBoxUser.addItem(tr);
			}
		}
		comboBoxUser.getSelectedItem();
		gbc_MiConstraint.insets = new Insets(0, 0, 5, 0);
		gbc_MiConstraint.gridx = 2;
		gbc_MiConstraint.gridy = 1;
		gbc_MiConstraint.gridwidth = 2;
		gbc_MiConstraint.fill = GridBagConstraints.HORIZONTAL;
		add(comboBoxUser, gbc_MiConstraint);
		
		JLabel lblClave = new JLabel("Clave:");
		gbc_MiConstraint.gridx = 1;
		gbc_MiConstraint.gridy = 2;
		gbc_MiConstraint.gridwidth = 1;
		//gbc_MiConstraint.anchor = GridBagConstraints.EAST;
		gbc_MiConstraint.insets = new Insets(0, 5, 5, 0);
		add(lblClave, gbc_MiConstraint);
		
		passwordField = new JPasswordField("cam1");
		gbc_MiConstraint.gridx = 2;
		gbc_MiConstraint.gridy = 2;
		gbc_MiConstraint.gridwidth = 2;
		gbc_MiConstraint.fill = GridBagConstraints.HORIZONTAL;
		gbc_MiConstraint.insets = new Insets(0, 0, 5, 0);
		add(passwordField, gbc_MiConstraint);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setIcon(new ImageIcon(PanelValida.class.getResource("/Gui/images/Aceptar2.png")));
		gbc_MiConstraint.gridx = 2;
		gbc_MiConstraint.gridy = 3;
		gbc_MiConstraint.gridwidth = 1;
		gbc_MiConstraint.insets = new Insets(0, 0, 0, 5);
		add(btnAceptar, gbc_MiConstraint);
		btnAceptar.setActionCommand("Valida clave");
		btnAceptar.addActionListener(gestotBotones);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.setVerticalAlignment(SwingConstants.TOP);
		btnAtras.setIcon(new ImageIcon(PanelValida.class.getResource("/Gui/images/Back.png")));
		gbc_MiConstraint.gridx = 3;
		gbc_MiConstraint.gridy = 3;
		gbc_MiConstraint.gridwidth = 1;
		gbc_MiConstraint.insets = new Insets(0, 0, 0, 0);
		btnAtras.addActionListener(gestotBotones);
		btnAtras.setActionCommand("Salir panel valida");
		add(btnAtras, gbc_MiConstraint);
	}

	public JComboBox<Trabajador> getComboBoxUser() {
		return comboBoxUser;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setComboBoxUser(JComboBox<Trabajador> comboBox) {
		this.comboBoxUser = comboBox;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

}
