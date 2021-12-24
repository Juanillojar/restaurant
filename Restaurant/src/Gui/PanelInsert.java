package Gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import java.text.*;

public class PanelInsert extends JPanel {

	JPanel panelWokerData, panelDetailWaiterData, panelDetailCookerData, panelDetailDeliveryManData;
	JComboBox cbWorkerType;
	JTextField tfName, tfSurNames, tfPassword;
	JFormattedTextField tfDni, tfSalary, tfTelephone;
	JComboBox cbShift;
	JLabel lblCocktail, lblLanguage1, lblLanguage2, lblLanguage3;
	JTextField tfLanguage1, tfLanguage2, tfLanguage3;
	JCheckBox chbCocktail;
	JLabel lblSpeciality, lblWorkExperience, lblKitchenCategory;
	JTextField tfSpeciality;
	JFormattedTextField tfWorkExperience;
	JComboBox cbKitchenCategory;
	JLabel lblDeliveryMode, lblAge, lblMotorcycleLicense, lblOwnVehicle;
	JComboBox cbDeliveryMode;
	JFormattedTextField tfAge;
	JCheckBox chbMotorcycleLicense, chbOwnVehicle;

	private GestorBotones gestorBotones = new GestorBotones();
	ImageIcon iconAceptar = new ImageIcon("src/gui/images/aceptar2.png", "Aceptar");
	ImageIcon iconBack = new ImageIcon("src/gui/images/Back.png", "Back");
	Font fuenteTitulo = new Font("arial", Font.BOLD, 20);
	Font fuenteDatos = new Font("arial", Font.PLAIN, 12);
	NumberFormatter formatoNumero = new NumberFormatter();
	DataEncryption encriptation = new DataEncryption();

	/**
	 * Constructor for insertion panel
	 * 
	 * @throws ParseException
	 */
	public PanelInsert() {
		setLayout(new BorderLayout(10, 0));
		JPanel panelTitle = new JPanel(new GridBagLayout());
		GridBagConstraints gbc_MiConstraint = new GridBagConstraints();
		gbc_MiConstraint.gridx = 0; // columna inicial que ocupa
		gbc_MiConstraint.gridy = 0; // fila inicial que ocupa
		gbc_MiConstraint.gridwidth = 2; // Columnas que ocupa
		gbc_MiConstraint.gridheight = 1; // Filas que ocupa
		gbc_MiConstraint.fill = GridBagConstraints.HORIZONTAL; // ajusta el componente a la celda
		gbc_MiConstraint.insets = new Insets(0, 5, 5, 0); // Espacio hasta los bordes del componente en la celda //top,
															// left, botton, right
		JLabel lblTitulo = new Label("Worker insertion data", fuenteTitulo, "CENTER");
		JLabel lblSelectWorkerType = new Label("Select worker type:", fuenteDatos, "RIGHT");
		cbWorkerType = new JComboBox<String>();
		cbWorkerType.addItem("Waiter");
		cbWorkerType.addItem("Cooker");
		cbWorkerType.addItem("Delivery man");

		cbWorkerType.addActionListener(gestorBotones);
		cbWorkerType.setActionCommand("cbWorkerTypeChange");
		panelTitle.add(lblTitulo, gbc_MiConstraint);
		gbc_MiConstraint.gridx = 0; // columna inicial que ocupa
		gbc_MiConstraint.gridy = 1; // fila inicial que ocupa
		gbc_MiConstraint.gridwidth = 1; // Columnas que ocupa
		panelTitle.add(lblSelectWorkerType, gbc_MiConstraint);
		gbc_MiConstraint.gridx = 1; // columna inicial que ocupa
		panelTitle.add(cbWorkerType, gbc_MiConstraint);

		add(panelTitle, BorderLayout.NORTH);

		try {

			// Create panel for worker data
			panelWokerData = new JPanel(new GridBagLayout());
			gbc_MiConstraint.gridx = 0; // columna inicial que ocupa
			gbc_MiConstraint.gridy = 0; // fila inicial que ocupa
			gbc_MiConstraint.gridwidth = 1; // Columnas que ocupa
			gbc_MiConstraint.gridheight = 1; // Filas que ocupa
			gbc_MiConstraint.fill = GridBagConstraints.HORIZONTAL; // ajusta el componente a la celda
			gbc_MiConstraint.insets = new Insets(0, 5, 5, 0); // Espacio hasta los bordes del componente en la celda
																// //top,
																// left, botton, right
			JLabel lblName = new JLabel("Name:*");
			panelWokerData.add(lblName, gbc_MiConstraint);
			gbc_MiConstraint.gridy = 1; // fila inicial que ocupa
			JLabel lblSurNames = new JLabel("Surnames:");
			panelWokerData.add(lblSurNames, gbc_MiConstraint);
			gbc_MiConstraint.gridy = 2; // fila inicial que ocupa
			JLabel lblDni = new JLabel("Dni:");
			panelWokerData.add(lblDni, gbc_MiConstraint);
			gbc_MiConstraint.gridy = 3; // fila inicial que ocupa
			JLabel lblSalary = new JLabel("Salary:");
			panelWokerData.add(lblSalary, gbc_MiConstraint);
			gbc_MiConstraint.gridy = 4; // fila inicial que ocupa
			JLabel lblShift = new JLabel("Shift:");
			panelWokerData.add(lblShift, gbc_MiConstraint);
			gbc_MiConstraint.gridy = 5; // fila inicial que ocupa
			JLabel lblTelephone = new JLabel("Telephone:");
			panelWokerData.add(lblTelephone, gbc_MiConstraint);
			gbc_MiConstraint.gridy = 6; // fila inicial que ocupa
			JLabel lblPassword = new JLabel("Password:*");
			panelWokerData.add(lblPassword, gbc_MiConstraint);

			gbc_MiConstraint.gridx = 1; // columna inicial que ocupa
			gbc_MiConstraint.gridy = 0; // fila inicial que ocupa
			tfName = new JTextField(15);
			// tfName.setPreferredSize(new Dimension(22, 5));
			panelWokerData.add(tfName, gbc_MiConstraint);
			gbc_MiConstraint.gridy = 1; // fila inicial que ocupa
			tfSurNames = new JTextField(15);
			panelWokerData.add(tfSurNames, gbc_MiConstraint);
			gbc_MiConstraint.gridy = 2; // fila inicial que ocupa
			MaskFormatter mascaraDni = new MaskFormatter("########U");
			tfDni = new JFormattedTextField(mascaraDni);

			panelWokerData.add(tfDni, gbc_MiConstraint);
			gbc_MiConstraint.gridy = 3; // fila inicial que ocupa
			NumberFormat formatoVisualizacionDouble = NumberFormat.getCurrencyInstance();
			NumberFormat formatoEdicionDouble = NumberFormat.getInstance(Locale.ENGLISH);
			NumberFormatter vf = new NumberFormatter(formatoVisualizacionDouble);
			NumberFormatter ef = new NumberFormatter(formatoEdicionDouble);
			DefaultFormatterFactory dff = new DefaultFormatterFactory(vf, vf, ef);
			tfSalary = new JFormattedTextField(dff);
			// tfSalary.setValue(1.00d);
			panelWokerData.add(tfSalary, gbc_MiConstraint);
			gbc_MiConstraint.gridy = 4; // fila inicial que ocupa
			cbShift = new JComboBox<Turno>(Turno.values());
			panelWokerData.add(cbShift, gbc_MiConstraint);
			gbc_MiConstraint.gridy = 5; // fila inicial que ocupa
			MaskFormatter mascaraTlf = new MaskFormatter("+##' #########");
			tfTelephone = new JFormattedTextField(mascaraTlf);
			panelWokerData.add(tfTelephone, gbc_MiConstraint);
			gbc_MiConstraint.gridy = 6; // fila inicial que ocupa
			tfPassword = new JTextField(10);
			panelWokerData.add(tfPassword, gbc_MiConstraint);

			add(panelWokerData, BorderLayout.CENTER);

			// Create components for detail worker (waiter) data
			gbc_MiConstraint.gridx = 0; // columna inicial que ocupa
			gbc_MiConstraint.gridy = 7; // fila inicial que ocupa
										// //top, left, botton, right
			lblCocktail = new JLabel("Cocktail experience:");
			panelWokerData.add(lblCocktail, gbc_MiConstraint);

			lblLanguage1 = new JLabel("Languaje 1:");
			gbc_MiConstraint.gridy = 8; // fila inicial que ocupa
			panelWokerData.add(lblLanguage1, gbc_MiConstraint);

			lblLanguage2 = new JLabel("Languaje 2:");
			gbc_MiConstraint.gridy = 9; // fila inicial que ocupa
			panelWokerData.add(lblLanguage2, gbc_MiConstraint);

			lblLanguage3 = new JLabel("Language3:");
			gbc_MiConstraint.gridy = 10; // fila inicial que ocupa
			panelWokerData.add(lblLanguage3, gbc_MiConstraint);

			chbCocktail = new JCheckBox();
			gbc_MiConstraint.gridx = 1; // columna inicial que ocupa
			gbc_MiConstraint.gridy = 7; // fila inicial que ocupa
			panelWokerData.add(chbCocktail, gbc_MiConstraint);

			tfLanguage1 = new JTextField(10);
			gbc_MiConstraint.gridy = 8; // fila inicial que ocupa
			panelWokerData.add(tfLanguage1, gbc_MiConstraint);

			tfLanguage2 = new JTextField(10);
			gbc_MiConstraint.gridy = 9; // fila inicial que ocupa
			panelWokerData.add(tfLanguage2, gbc_MiConstraint);

			tfLanguage3 = new JTextField(10);
			gbc_MiConstraint.gridy = 10; // fila inicial que ocupa
			panelWokerData.add(tfLanguage3, gbc_MiConstraint);

			// Create components for detail worker (cooker) data
			gbc_MiConstraint.gridx = 0; // columna inicial que ocupa
			gbc_MiConstraint.gridy = 7; // fila inicial que ocupa

			lblSpeciality = new JLabel("Speciality:");
			panelWokerData.add(lblSpeciality, gbc_MiConstraint);
			lblSpeciality.setVisible(false);

			lblWorkExperience = new JLabel("Work experience:");
			gbc_MiConstraint.gridy = 8; // fila inicial que ocupa
			panelWokerData.add(lblWorkExperience, gbc_MiConstraint);
			lblWorkExperience.setVisible(false);

			lblKitchenCategory = new JLabel("Kitchen category:");
			gbc_MiConstraint.gridy = 9; // fila inicial que ocupa
			panelWokerData.add(lblKitchenCategory, gbc_MiConstraint);
			lblKitchenCategory.setVisible(false);

			tfSpeciality = new JTextField(10);
			gbc_MiConstraint.gridx = 1; // columna inicial que ocupa
			gbc_MiConstraint.gridy = 7; // fila inicial que ocupa
			panelWokerData.add(tfSpeciality, gbc_MiConstraint);
			tfSpeciality.setVisible(false);

			tfWorkExperience = new JFormattedTextField(formatoNumero);
			gbc_MiConstraint.gridy = 8; // fila inicial que ocupa
			panelWokerData.add(tfWorkExperience, gbc_MiConstraint);
			tfWorkExperience.setVisible(false);

			cbKitchenCategory = new JComboBox<kitchenCategory>(kitchenCategory.values());
			gbc_MiConstraint.gridy = 9; // fila inicial que ocupa
			panelWokerData.add(cbKitchenCategory, gbc_MiConstraint);
			cbKitchenCategory.setVisible(false);

			// Create components for detail worker (delivery man) data
			gbc_MiConstraint.gridx = 0; // columna inicial que ocupa
			gbc_MiConstraint.gridy = 7; // fila inicial que ocupa
			lblDeliveryMode = new JLabel("Delivery mode:");
			panelWokerData.add(lblDeliveryMode, gbc_MiConstraint);
			lblDeliveryMode.setVisible(false);

			lblAge = new JLabel("Age:");
			gbc_MiConstraint.gridy = 8; // fila inicial que ocupa
			panelWokerData.add(lblAge, gbc_MiConstraint);
			lblAge.setVisible(false);

			lblMotorcycleLicense = new JLabel("Motorcycle license:");
			gbc_MiConstraint.gridy = 9; // fila inicial que ocupa
			panelWokerData.add(lblMotorcycleLicense, gbc_MiConstraint);
			lblMotorcycleLicense.setVisible(false);

			lblOwnVehicle = new JLabel("Own vehicle:");
			gbc_MiConstraint.gridy = 10; // fila inicial que ocupa
			panelWokerData.add(lblOwnVehicle, gbc_MiConstraint);
			lblOwnVehicle.setVisible(false);

			cbDeliveryMode = new JComboBox<Transport>(Transport.values());
			gbc_MiConstraint.gridx = 1; // columna inicial que ocupa
			gbc_MiConstraint.gridy = 7; // fila inicial que ocupa
			panelWokerData.add(cbDeliveryMode, gbc_MiConstraint);
			cbDeliveryMode.setVisible(false);

			tfAge = new JFormattedTextField(formatoNumero);
			gbc_MiConstraint.gridy = 8; // fila inicial que ocupa
			panelWokerData.add(tfAge, gbc_MiConstraint);
			tfAge.setVisible(false);

			chbMotorcycleLicense = new JCheckBox();
			gbc_MiConstraint.gridy = 9; // fila inicial que ocupa
			panelWokerData.add(chbMotorcycleLicense, gbc_MiConstraint);
			chbMotorcycleLicense.setVisible(false);

			chbOwnVehicle = new JCheckBox();
			gbc_MiConstraint.gridy = 10; // fila inicial que ocupa
			panelWokerData.add(chbOwnVehicle, gbc_MiConstraint);
			chbOwnVehicle.setVisible(false);
		} catch (ParseException ex) {
			System.out.println("Formato incorrecto " + ex.getMessage() + ex.getStackTrace());
			Frame.log.Escritura("Formato incorrecto " + ex.getMessage() + ex.getStackTrace());
		}
		;

		JPanel botones = new JPanel();
		Boton buttonSalirConfig = new Boton(iconBack);
		buttonSalirConfig.addActionListener(gestorBotones);
		buttonSalirConfig.setActionCommand("SalirPanelInsert");
		botones.add(buttonSalirConfig);

		Boton buttonApplyConfig = new Boton(iconAceptar);
		buttonApplyConfig.addActionListener(gestorBotones);
		buttonApplyConfig.setActionCommand("ApplyPanelInsert");
		botones.add(buttonApplyConfig);

		add(botones, BorderLayout.SOUTH);
	}

	/**
	 * change visibility of group of objetcs Component
	 * 
	 * @param estado      indicates value of SetVisible() function
	 * @param componentes the Component objects group. The number of objects is not
	 *                    specified
	 */
	public void changeVisibility(Boolean estado, JComponent... componentes) {
		for (JComponent cmp : componentes) {
			cmp.setVisible(estado);
		}
	}

	public void insertWorker() {
		Trabajador worker;
		String clave = "";
		try {
			clave = encriptation.encrypt(tfPassword.getText(), Test.key);
		} catch (UnsupportedEncodingException | GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		switch ((String) cbWorkerType.getSelectedItem()) {
		case "Waiter":
			String languages[] = { tfLanguage1.getText(), tfLanguage2.getText(), tfLanguage3.getText() };
			worker = new Camarero(tfName.getText(), tfSurNames.getText(), tfDni.getText(),
					((Long) tfSalary.getValue()).doubleValue(), (Turno) cbShift.getSelectedItem(),
					tfTelephone.getText(), clave, languages, chbCocktail.isSelected());
			break;
		case "Cooker":
			worker = new Cocinero(tfName.getText(), tfSurNames.getText(), tfDni.getText(),
					((Long) tfSalary.getValue()).doubleValue(), (Turno) cbShift.getSelectedItem(),
					tfTelephone.getText(), clave, tfSpeciality.getText(), Integer.parseInt(tfWorkExperience.getText()),
					(kitchenCategory) cbKitchenCategory.getSelectedItem());
			break;
		case "Delivery man":
			worker = new Repartidor(tfName.getText(), tfSurNames.getText(), tfDni.getText(),
					((Long) tfSalary.getValue()).doubleValue(), (Turno) cbShift.getSelectedItem(),
					tfTelephone.getText(), clave, (Transport) cbDeliveryMode.getSelectedItem(),
					Integer.parseInt(tfAge.getText()), chbMotorcycleLicense.isSelected(), chbOwnVehicle.isSelected());
			break;
		default:
			worker = null;
		}
		Frame.InstanceFPizzerie.myPizzerie.getWorkers().add(worker);
		JOptionPane.showMessageDialog(null, "Worker inserted sucessfully", "Insertion",
				JOptionPane.INFORMATION_MESSAGE);
		Frame.log.Escritura("Worker '" + tfName.getText() + tfSurNames.getText() + "' inserted sucessfully");
		System.out.println(worker);
		worker = null;
	}

	public Boolean requiredFields(JTextField... componentes) {
		for (JTextField cmp : componentes) {
			if(cmp.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Fields wiht * mark must be filled out", "Insertion",
						JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		}		
		return true;
	}
}
