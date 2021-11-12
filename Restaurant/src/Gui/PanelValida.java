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
	private GestorBotonesPizzeria gestotBotones = new GestorBotonesPizzeria();
	
	/**
	 * Create the panel.
	 */
	public PanelValida(List<Trabajador> trabajadores) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 73, 131, 0, 101, 89, 0};
		gridBagLayout.rowHeights = new int[]{37, 35, 35, 35, 35, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel_2 = new JLabel("Seleccione su usuario");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridwidth = 2;
		gbc_lblNewLabel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 0;
		add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("Operador:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 2;
		add(lblNewLabel, gbc_lblNewLabel);
		
		comboBoxUser = new JComboBox<Trabajador>();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		
		for(Trabajador tr : trabajadores) {
			if(tr.getClass().equals(cam.getClass()) || tr.getClass().equals(rep.getClass())) {
				comboBoxUser.addItem(tr);
			}
		}
		comboBoxUser.getSelectedItem();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 2;
		
		add(comboBoxUser, gbc_comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Clave:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 3;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 3;
		add(passwordField, gbc_passwordField);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setIcon(new ImageIcon(PanelValida.class.getResource("/Gui/images/Aceptar2.png")));
		//btnAceptar.addActionListener(ges)
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 4;
		add(btnAceptar, gbc_btnNewButton_1);
		btnAceptar.setActionCommand("Valida clave");
		btnAceptar.addActionListener(gestotBotones);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.setVerticalAlignment(SwingConstants.TOP);
		btnAtras.setIcon(new ImageIcon(PanelValida.class.getResource("/Gui/images/Back.png")));
		btnAtras.addActionListener(gestotBotones);
		btnAtras.setActionCommand("Salir panel valida");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 4;
		gbc_btnNewButton.gridy = 4;
		add(btnAtras, gbc_btnNewButton);

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
