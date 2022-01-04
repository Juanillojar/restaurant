package Gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

/**
 * @author Juan José Cárdenas
 *	show components in a panel. Used to generate subpanels of panelInsert class
 * Two constructors are defined to show the data of products or Workers (Waiter, cook and delivery man)
 * Son definidos dos constructores para mostrar los datos de productos o Trabajadores (Camarero, cocinero y repartidor)
 */
public class PanelShow extends JPanel {
	NumberFormatter formatoNumero = new NumberFormatter();
	JTextField tfName, tfSurNames, tfPassword;
	JFormattedTextField tfDni, tfSalary, tfTelephone;
	JComboBox<Turno> cbShift;
	JLabel lblCocktail, lblLanguage1, lblLanguage2, lblLanguage3;
	JTextField tfLanguage1, tfLanguage2, tfLanguage3;
	JCheckBox chbCocktail;
	JLabel lblSpeciality, lblWorkExperience, lblKitchenCategory;
	JTextField tfSpeciality;
	JFormattedTextField tfWorkExperience;
	JComboBox<kitchenCategory> cbKitchenCategory;
	JLabel lblDeliveryMode, lblAge, lblMotorcycleLicense, lblOwnVehicle;
	JComboBox<Transport> cbDeliveryMode;
	JFormattedTextField tfAge;
	JCheckBox chbMotorcycleLicense, chbOwnVehicle;
	JTextField tfDenomination, tfIngredients;
	JComboBox cbSection;
	JFormattedTextField tfPrice;
	JCheckBox chbLowPrice;

	/**
	 * Constructor for panel that show components to insert a Productos object
	 * 
	 * @param a not used
	 */
	PanelShow(int a) {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc_MiConstraint = new GridBagConstraints();
		gbc_MiConstraint.gridx = 0; // columna inicial que ocupa
		gbc_MiConstraint.gridy = 0; // fila inicial que ocupa
		gbc_MiConstraint.gridwidth = 1; // Columnas que ocupa
		gbc_MiConstraint.gridheight = 1; // Filas que ocupa
		gbc_MiConstraint.fill = GridBagConstraints.HORIZONTAL; // ajusta el componente a la celda
		gbc_MiConstraint.insets = new Insets(0, 5, 5, 0); // Espacio hasta los bordes del componente en la celda
															// //top,
															// left, botton, right

		JLabel lblDenomination = new JLabel("Denomination:*");
		add(lblDenomination, gbc_MiConstraint);
		gbc_MiConstraint.gridy = 1; // fila inicial que ocupa
		JLabel lblSection = new JLabel("Section:");
		add(lblSection, gbc_MiConstraint);
		gbc_MiConstraint.gridy = 2; // fila inicial que ocupa
		JLabel lblIngredients = new JLabel("Ingredients:");
		add(lblIngredients, gbc_MiConstraint);
		gbc_MiConstraint.gridy = 3; // fila inicial que ocupa
		JLabel lblPrice = new JLabel("Price:*");
		add(lblPrice, gbc_MiConstraint);
		gbc_MiConstraint.gridy = 4; // fila inicial que ocupa
		JLabel lblLowPrice = new JLabel("Low price:");
		add(lblLowPrice, gbc_MiConstraint);
//		try {
			gbc_MiConstraint.gridx = 1; // columna inicial que ocupa
			gbc_MiConstraint.gridy = 0; // fila inicial que ocupa
			tfDenomination = new JTextField(15);
			add(tfDenomination, gbc_MiConstraint);
			gbc_MiConstraint.gridy = 1; // fila inicial que ocupa
			cbSection = new JComboBox<Section>(Section.values());
			add(cbSection, gbc_MiConstraint);
			gbc_MiConstraint.gridy = 2; // fila inicial que ocupa
			tfIngredients = new JTextField(15);
			add(tfIngredients, gbc_MiConstraint);
			gbc_MiConstraint.gridy = 3; // fila inicial que ocupa
			NumberFormat formatoVisualizacionDouble = NumberFormat.getCurrencyInstance();
			NumberFormat formatoEdicionDouble = NumberFormat.getInstance(Locale.ENGLISH);
			NumberFormatter vf = new NumberFormatter(formatoVisualizacionDouble);
			NumberFormatter ef = new NumberFormatter(formatoEdicionDouble);
			DefaultFormatterFactory dff = new DefaultFormatterFactory(vf, vf, ef);
			tfPrice = new JFormattedTextField(dff);
			tfPrice.setValue(10.10);
			add(tfPrice, gbc_MiConstraint);
			gbc_MiConstraint.gridy = 4; // fila inicial que ocupa
			chbLowPrice = new JCheckBox();
			add(chbLowPrice, gbc_MiConstraint);

//		} catch (ParseException ex) {
	//		System.out.println("Formato incorrecto " + ex.getMessage() + ex.getStackTrace());
//			Frame.log.Escritura("Formato incorrecto " + ex.getMessage() + ex.getStackTrace());
	//	}
	}

	/**
	 * Constructor for panel that show components to insert a Trabajador (Cocinero,
	 * Camarero or Repartidor) object
	 * 
	 * @param worker not used
	 */
	public PanelShow(String worker) {
		// Create panel for worker data
		setLayout(new GridBagLayout());
		GridBagConstraints gbc_MiConstraint = new GridBagConstraints();
		gbc_MiConstraint.gridx = 0; // columna inicial que ocupa
		gbc_MiConstraint.gridy = 0; // fila inicial que ocupa
		gbc_MiConstraint.gridwidth = 1; // Columnas que ocupa
		gbc_MiConstraint.gridheight = 1; // Filas que ocupa
		gbc_MiConstraint.fill = GridBagConstraints.HORIZONTAL; // ajusta el componente a la celda
		gbc_MiConstraint.insets = new Insets(0, 5, 5, 0); // Espacio hasta los bordes del componente en la celda
															// top, left, botton, right

		JLabel lblName = new JLabel("Name:*");
		add(lblName, gbc_MiConstraint);
		gbc_MiConstraint.gridy = 1; // fila inicial que ocupa
		JLabel lblSurNames = new JLabel("Surnames:");
		add(lblSurNames, gbc_MiConstraint);
		gbc_MiConstraint.gridy = 2; // fila inicial que ocupa
		JLabel lblDni = new JLabel("Dni:");
		add(lblDni, gbc_MiConstraint);
		gbc_MiConstraint.gridy = 3; // fila inicial que ocupa
		JLabel lblSalary = new JLabel("Salary:*");
		add(lblSalary, gbc_MiConstraint);
		gbc_MiConstraint.gridy = 4; // fila inicial que ocupa
		JLabel lblShift = new JLabel("Shift:");
		add(lblShift, gbc_MiConstraint);
		gbc_MiConstraint.gridy = 5; // fila inicial que ocupa
		JLabel lblTelephone = new JLabel("Telephone:");
		add(lblTelephone, gbc_MiConstraint);
		gbc_MiConstraint.gridy = 6; // fila inicial que ocupa
		JLabel lblPassword = new JLabel("Password:*");
		add(lblPassword, gbc_MiConstraint);
		try {

			gbc_MiConstraint.gridx = 1; // columna inicial que ocupa
			gbc_MiConstraint.gridy = 0; // fila inicial que ocupa
			tfName = new JTextField(15);
			add(tfName, gbc_MiConstraint);
			gbc_MiConstraint.gridy = 1; // fila inicial que ocupa
			tfSurNames = new JTextField(15);
			add(tfSurNames, gbc_MiConstraint);
			gbc_MiConstraint.gridy = 2; // fila inicial que ocupa
			MaskFormatter mascaraDni;
			mascaraDni = new MaskFormatter("########U");
			tfDni = new JFormattedTextField(mascaraDni);
			add(tfDni, gbc_MiConstraint);
			gbc_MiConstraint.gridy = 3; // fila inicial que ocupa
			NumberFormat formatoVisualizacionDouble = NumberFormat.getCurrencyInstance();
			NumberFormat formatoEdicionDouble = NumberFormat.getInstance(Locale.ENGLISH);
			NumberFormatter vf = new NumberFormatter(formatoVisualizacionDouble);
			NumberFormatter ef = new NumberFormatter(formatoEdicionDouble);
			DefaultFormatterFactory dff = new DefaultFormatterFactory(vf, vf, ef);
			tfSalary = new JFormattedTextField(dff);
			tfSalary.setValue(1000.10);
			add(tfSalary, gbc_MiConstraint);
			gbc_MiConstraint.gridy = 4; // fila inicial que ocupa
			cbShift = new JComboBox<Turno>(Turno.values());
			add(cbShift, gbc_MiConstraint);
			gbc_MiConstraint.gridy = 5; // fila inicial que ocupa
			MaskFormatter mascaraTlf = new MaskFormatter("+##' #########");
			tfTelephone = new JFormattedTextField(mascaraTlf);
			add(tfTelephone, gbc_MiConstraint);
			gbc_MiConstraint.gridy = 6; // fila inicial que ocupa
			tfPassword = new JTextField(10);
			add(tfPassword, gbc_MiConstraint);
		} catch (ParseException ex) {
			System.out.println("Formato incorrecto " + ex.getMessage() + ex.getStackTrace());
			Frame.log.Escritura("Formato incorrecto " + ex.getMessage() + ex.getStackTrace());
		}

		// Create components for detail worker (waiter) data
		gbc_MiConstraint.gridx = 0; // columna inicial que ocupa
		gbc_MiConstraint.gridy = 7; // fila inicial que ocupa
									// //top, left, botton, right
		lblCocktail = new JLabel("Cocktail experience:");
		add(lblCocktail, gbc_MiConstraint);
		lblLanguage1 = new JLabel("Languaje 1:");
		gbc_MiConstraint.gridy = 8; // fila inicial que ocupa
		add(lblLanguage1, gbc_MiConstraint);
		lblLanguage2 = new JLabel("Languaje 2:");
		gbc_MiConstraint.gridy = 9; // fila inicial que ocupa
		add(lblLanguage2, gbc_MiConstraint);
		lblLanguage3 = new JLabel("Language3:");
		gbc_MiConstraint.gridy = 10; // fila inicial que ocupa
		add(lblLanguage3, gbc_MiConstraint);

		chbCocktail = new JCheckBox();
		gbc_MiConstraint.gridx = 1; // columna inicial que ocupa
		gbc_MiConstraint.gridy = 7; // fila inicial que ocupa
		add(chbCocktail, gbc_MiConstraint);
		tfLanguage1 = new JTextField(10);
		gbc_MiConstraint.gridy = 8; // fila inicial que ocupa
		add(tfLanguage1, gbc_MiConstraint);
		tfLanguage2 = new JTextField(10);
		gbc_MiConstraint.gridy = 9; // fila inicial que ocupa
		add(tfLanguage2, gbc_MiConstraint);
		tfLanguage3 = new JTextField(10);
		gbc_MiConstraint.gridy = 10; // fila inicial que ocupa
		add(tfLanguage3, gbc_MiConstraint);

		// Create components for detail worker (cooker) data
		gbc_MiConstraint.gridx = 0; // columna inicial que ocupa
		gbc_MiConstraint.gridy = 7; // fila inicial que ocupa
		lblSpeciality = new JLabel("Speciality:");
		add(lblSpeciality, gbc_MiConstraint);
		lblSpeciality.setVisible(false);
		lblWorkExperience = new JLabel("Work experience:");
		gbc_MiConstraint.gridy = 8; // fila inicial que ocupa
		add(lblWorkExperience, gbc_MiConstraint);
		lblWorkExperience.setVisible(false);
		lblKitchenCategory = new JLabel("Kitchen category:");
		gbc_MiConstraint.gridy = 9; // fila inicial que ocupa
		add(lblKitchenCategory, gbc_MiConstraint);
		lblKitchenCategory.setVisible(false);

		tfSpeciality = new JTextField(10);
		gbc_MiConstraint.gridx = 1; // columna inicial que ocupa
		gbc_MiConstraint.gridy = 7; // fila inicial que ocupa
		add(tfSpeciality, gbc_MiConstraint);
		tfSpeciality.setVisible(false);
		tfWorkExperience = new JFormattedTextField(formatoNumero);
		gbc_MiConstraint.gridy = 8; // fila inicial que ocupa
		add(tfWorkExperience, gbc_MiConstraint);
		tfWorkExperience.setVisible(false);
		tfWorkExperience.setValue(0);
		cbKitchenCategory = new JComboBox<kitchenCategory>(kitchenCategory.values());
		gbc_MiConstraint.gridy = 9; // fila inicial que ocupa
		add(cbKitchenCategory, gbc_MiConstraint);
		cbKitchenCategory.setVisible(false);

		// Create components for detail worker (delivery man) data
		gbc_MiConstraint.gridx = 0; // columna inicial que ocupa
		gbc_MiConstraint.gridy = 7; // fila inicial que ocupa
		lblDeliveryMode = new JLabel("Delivery mode:");
		add(lblDeliveryMode, gbc_MiConstraint);
		lblDeliveryMode.setVisible(false);
		lblAge = new JLabel("Age:");
		gbc_MiConstraint.gridy = 8; // fila inicial que ocupa
		add(lblAge, gbc_MiConstraint);
		lblAge.setVisible(false);
		lblMotorcycleLicense = new JLabel("Motorcycle license:");
		gbc_MiConstraint.gridy = 9; // fila inicial que ocupa
		add(lblMotorcycleLicense, gbc_MiConstraint);
		lblMotorcycleLicense.setVisible(false);
		lblOwnVehicle = new JLabel("Own vehicle:");
		gbc_MiConstraint.gridy = 10; // fila inicial que ocupa
		add(lblOwnVehicle, gbc_MiConstraint);
		lblOwnVehicle.setVisible(false);

		cbDeliveryMode = new JComboBox<Transport>(Transport.values());
		gbc_MiConstraint.gridx = 1; // columna inicial que ocupa
		gbc_MiConstraint.gridy = 7; // fila inicial que ocupa
		add(cbDeliveryMode, gbc_MiConstraint);
		cbDeliveryMode.setVisible(false);
		tfAge = new JFormattedTextField(formatoNumero);
		gbc_MiConstraint.gridy = 8; // fila inicial que ocupa
		add(tfAge, gbc_MiConstraint);
		tfAge.setVisible(false);
		chbMotorcycleLicense = new JCheckBox();
		gbc_MiConstraint.gridy = 9; // fila inicial que ocupa
		add(chbMotorcycleLicense, gbc_MiConstraint);
		chbMotorcycleLicense.setVisible(false);
		chbOwnVehicle = new JCheckBox();
		gbc_MiConstraint.gridy = 10; // fila inicial que ocupa
		add(chbOwnVehicle, gbc_MiConstraint);
		chbOwnVehicle.setVisible(false);
	}
}
