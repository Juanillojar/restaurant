package Gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
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

	PanelShow panelProduct;
	PanelShow panelWorkerData;
	JLabel lblSelectWorkerType;
	JComboBox cbSelection, cbWorkerType;
	private GestorBotones gestorBotones = new GestorBotones();
	ImageIcon iconAceptar = new ImageIcon("src/gui/images/aceptar2.png", "Aceptar");
	ImageIcon iconBack = new ImageIcon("src/gui/images/Back.png", "Back");
	Font fuenteTitulo = new Font("arial", Font.BOLD, 20);
	Font fuenteDatos = new Font("arial", Font.PLAIN, 12);
	NumberFormatter formatoNumero = new NumberFormatter();
	DataEncryption encriptation = new DataEncryption();

	/**
	 * Constructor for insertion panel
	 */
	public PanelInsert() {
		setLayout(new BorderLayout(10, 0));
		JPanel panelTitle = new JPanel(new GridBagLayout());

		JLabel lblSelection = new Label("Select what want to insert:", fuenteDatos, "CENTER");
		cbSelection = new JComboBox<String>();
		cbSelection.addItem("");
		cbSelection.addItem("Worker");
		cbSelection.addItem("Product");
		cbSelection.addActionListener(gestorBotones);
		cbSelection.setActionCommand("cbSelectionChange");

		JLabel lblTitulo = new Label("Insertion data", fuenteTitulo, "CENTER");
		lblSelectWorkerType = new Label("Select worker type:", fuenteDatos, "RIGHT");
		lblSelectWorkerType.setVisible(false);
		cbWorkerType = new JComboBox<String>();
		cbWorkerType.addItem("Waiter");
		cbWorkerType.addItem("Cooker");
		cbWorkerType.addItem("Delivery man");
		cbWorkerType.setVisible(false);
		cbWorkerType.addActionListener(gestorBotones);
		cbWorkerType.setActionCommand("cbWorkerTypeChange");

		GridBagConstraints gbc_MiConstraint = new GridBagConstraints();
		gbc_MiConstraint.gridx = 0; // columna inicial que ocupa
		gbc_MiConstraint.gridy = 0; // fila inicial que ocupa
		gbc_MiConstraint.gridwidth = 2; // Columnas que ocupa
		gbc_MiConstraint.gridheight = 1; // Filas que ocupa
		gbc_MiConstraint.fill = GridBagConstraints.HORIZONTAL; // ajusta el componente a la celda
		gbc_MiConstraint.insets = new Insets(0, 5, 5, 0); // Espacio hasta los bordes del componente en la celda //top,
															// left, botton, right
		panelTitle.add(lblTitulo, gbc_MiConstraint);
		gbc_MiConstraint.gridx = 0; // columna inicial que ocupa
		gbc_MiConstraint.gridy = 1; // fila inicial que ocupa
		gbc_MiConstraint.gridwidth = 1; // Columnas que ocupa
		panelTitle.add(lblSelection, gbc_MiConstraint);
		gbc_MiConstraint.gridx = 1; // columna inicial que ocupa
		panelTitle.add(cbSelection, gbc_MiConstraint);
		gbc_MiConstraint.gridx = 0; // columna inicial que ocupa
		gbc_MiConstraint.gridy = 2; // fila inicial que ocupa
		panelTitle.add(lblSelectWorkerType, gbc_MiConstraint);
		gbc_MiConstraint.gridx = 1; // columna inicial que ocupa
		gbc_MiConstraint.gridy = 2; // fila inicial que ocupa
		panelTitle.add(cbWorkerType, gbc_MiConstraint);

		add(panelTitle, BorderLayout.NORTH);

		panelProduct = new PanelShow(2); // create panel for product data insertion

		panelWorkerData = new PanelShow("Worker"); // create panel for worker data insertion

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
			if (cmp != null)
				cmp.setVisible(estado);
		}
	}

	/**
	 * Insert a Trabajador Object into a JList. This worker can be a Cocinero,
	 * Camarero or Repartidor object
	 * 
	 * @param workerType a String that specified ther worker type.
	 */
	public Trabajador insertWorker(String workerType) {
		Trabajador worker;
		String clave = "";
		NumberFormat formatoEdicionDouble = NumberFormat.getInstance(Locale.ENGLISH);
		try {
			clave = encriptation.encrypt(panelWorkerData.tfPassword.getText(), Test.key);
		} catch (UnsupportedEncodingException | GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {

			switch (workerType) {
			case "Waiter":
				String languages[] = { panelWorkerData.tfLanguage1.getText(), panelWorkerData.tfLanguage2.getText(),
						panelWorkerData.tfLanguage3.getText() };
				worker = new Camarero(panelWorkerData.tfName.getText(), panelWorkerData.tfSurNames.getText(),
						panelWorkerData.tfDni.getText(),
						(Double) formatoEdicionDouble.parse(panelWorkerData.tfSalary.getValue().toString()),
						(Turno) panelWorkerData.cbShift.getSelectedItem(), panelWorkerData.tfTelephone.getText(), clave,
						languages, panelWorkerData.chbCocktail.isSelected());

				break;
			case "Cooker":
				worker = new Cocinero(panelWorkerData.tfName.getText(), panelWorkerData.tfSurNames.getText(),
						panelWorkerData.tfDni.getText(),
						(Double) formatoEdicionDouble.parse(panelWorkerData.tfSalary.getValue().toString()),
						(Turno) panelWorkerData.cbShift.getSelectedItem(), panelWorkerData.tfTelephone.getText(), clave,
						panelWorkerData.tfSpeciality.getText(),
						Integer.parseInt(panelWorkerData.tfWorkExperience.getText()),
						(kitchenCategory) panelWorkerData.cbKitchenCategory.getSelectedItem());
				break;
			case "Delivery man":
				worker = new Repartidor(panelWorkerData.tfName.getText(), panelWorkerData.tfSurNames.getText(),
						panelWorkerData.tfDni.getText(),
						(Double) formatoEdicionDouble.parse(panelWorkerData.tfSalary.getValue().toString()),
						(Turno) panelWorkerData.cbShift.getSelectedItem(), panelWorkerData.tfTelephone.getText(), clave,
						(Transport) panelWorkerData.cbDeliveryMode.getSelectedItem(),
						Integer.parseInt(panelWorkerData.tfAge.getText()),
						panelWorkerData.chbMotorcycleLicense.isSelected(), panelWorkerData.chbOwnVehicle.isSelected());
				break;
			default:
				worker = null;
			}
			// insert worker on JList (not in database)
			Frame.InstanceFPizzerie.myPizzerie.getWorkers().add(worker);
			JOptionPane.showMessageDialog(null, "Worker inserted sucessfully", "Insertion",
					JOptionPane.INFORMATION_MESSAGE);
			Frame.log.Escritura("Worker '" + panelWorkerData.tfName.getText() + panelWorkerData.tfSurNames.getText()
					+ "' inserted sucessfully");
			System.out.println(worker);
			return worker;
		} catch (ParseException e) {
			System.out.println("Worker insertion. " + e.getMessage() + e.getStackTrace().toString());
			Frame.log.Escritura("Worker insertion. " + e.getMessage() + e.getStackTrace());
			return null;
		}

	}

	/**
	 * Insert a Productos Object into a JList.
	 */
	public Productos insertProduct() {
		NumberFormat formatoEdicionDouble = NumberFormat.getInstance(Locale.ENGLISH);
		Productos product;
		try {
			product = new Productos(panelProduct.tfDenomination.getText(),
					(Section) panelProduct.cbSection.getSelectedItem(), panelProduct.tfIngredients.getText(),
					(Double) formatoEdicionDouble.parse(panelProduct.tfPrice.getValue().toString()),
					panelProduct.chbLowPrice.isSelected());

			// insert product on JList (not in database)
			Frame.InstanceFPizzerie.myPizzerie.getFoods().add(product);
			JOptionPane.showMessageDialog(null, "Product inserted sucessfully", "Insertion",
					JOptionPane.INFORMATION_MESSAGE);
			Frame.log.Escritura("Product '" + panelProduct.tfDenomination.getText() + "' inserted sucessfully");
			System.out.println(product);
			return product;
		} catch (ParseException e) {
			System.out.println("Product insertion. " + e.getMessage() + e.getStackTrace().toString());
			Frame.log.Escritura("Product insertion. " + e.getMessage() + e.getStackTrace());
			return null;
		}
	}


	public Boolean requiredFields(JTextField... componentes) {
		for (JTextField cmp : componentes) {
			if (cmp.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Fields wiht * mark must be filled out", "Insertion",
						JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		}
		return true;
	}
}
