package Gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * @author Operador
 *
 */
public class GestorBotones implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("AbrirPanelValida")) {
			// crea valida panel si no existe
			if (Frame.InstanceFRestaurant.panelValida == null) {
				Frame.InstanceFRestaurant.panelValida = new PanelValida(Frame.InstanceFRestaurant.myRestaurant.getWorkers());
				System.out.println("Se crea panel valida");
			}
			Frame.InstanceFRestaurant.cambiaPanel(Frame.InstanceFRestaurant.panelValida,
					Frame.InstanceFRestaurant.panelPrincipal);
		}
		if (e.getActionCommand().equals("Valida clave")) {
			try {
				if (Frame.InstanceFRestaurant.getPanelValida().validaClave()) {
					// inserted password is correct. Show bar and tables panel
					if (Frame.InstanceFRestaurant.panelMesas == null) {
						Frame.InstanceFRestaurant.panelMesas = new Panel(Frame.InstanceFRestaurant.myRestaurant.getBarZones(),
								Frame.InstanceFRestaurant.myRestaurant.getDeliveryZones(),
								Frame.InstanceFRestaurant.myRestaurant.getInTables(),
								Frame.InstanceFRestaurant.myRestaurant.getOutTables());
						System.out.println("Se crea panel mesas");
						Frame.log.Escritura("Se crea panel mesas");
					}

					Frame.InstanceFRestaurant.setTrabajadorValidado(
							(Trabajador) Frame.InstanceFRestaurant.getPanelValida().getComboBoxUser().getSelectedItem());
				}
			} catch (GeneralSecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Frame.InstanceFRestaurant.cambiaPanel(Frame.InstanceFRestaurant.panelMesas,
					Frame.InstanceFRestaurant.panelValida);
		}
		if (e.getActionCommand().equals("Salir panel valida")) {
			Frame.InstanceFRestaurant.cambiaPanel(Frame.InstanceFRestaurant.panelPrincipal,
					Frame.InstanceFRestaurant.panelValida);
		}
		if (e.getActionCommand().equals("AbrirPanelConfiguracion")) {
			if (Frame.InstanceFRestaurant.panelConfig == null) {
				Frame.InstanceFRestaurant.panelConfig = new PanelConfig(Test.arrayConfig);
			}
			Frame.InstanceFRestaurant.cambiaPanel(Frame.InstanceFRestaurant.panelConfig,
					Frame.InstanceFRestaurant.panelPrincipal);

		}
		if (e.getActionCommand().equals("SalirPanelConfig")) {
			Frame.InstanceFRestaurant.cambiaPanel(Frame.InstanceFRestaurant.panelPrincipal,
					Frame.InstanceFRestaurant.panelConfig);
		}

		if (e.getActionCommand().equals("ApplyPanelConfig")) {
			// Store data from config panel
			Frame.InstanceFRestaurant.getPanelConfig().getMyXmlDoc()
					.store(Frame.InstanceFRestaurant.getPanelConfig().getMyXmlDoc().read());
		}

		if (e.getActionCommand().equals("AbrirPanelReports")) {
			// create report panel if not exist
			if (Frame.InstanceFRestaurant.panelReport == null) {
				Frame.InstanceFRestaurant.panelReport = new Panel(1);
			}
			Frame.InstanceFRestaurant.cambiaPanel(Frame.InstanceFRestaurant.panelReport,
					Frame.InstanceFRestaurant.panelPrincipal);
		}
		if (e.getActionCommand().equals("OpenProductsPanel")) {
			// store static variable BotonMesa (button that generate open products panel
			Panel.setBotonMesa((BotonRestauranteMesas) e.getSource());
			Frame.InstanceFRestaurant.panelMesas.abrirPanelProductos();
		}
		if (e.getActionCommand().equals("SalirPanelMesas")) {
			Frame.InstanceFRestaurant.cambiaPanel(Frame.InstanceFRestaurant.panelPrincipal,
					Frame.InstanceFRestaurant.panelMesas);
		}
		if (e.getActionCommand().equals("AddToOrder")) {
			Frame.InstanceFRestaurant.panelProductos.anyadeProducto(e);
		}
		if (e.getActionCommand().equals("OpenTiquePanel")) {
			// completa datos del Pedido y se pasa al panel tiquet
			Frame.InstanceFRestaurant.panelProductos.upgradeOrderData(Panel.getBotonMesa().getPedidoBoton());
			Frame.InstanceFRestaurant.panelticket = new panelticket(Panel.getBotonMesa().getPedidoBoton());
			Frame.InstanceFRestaurant.cambiaPanel(Frame.InstanceFRestaurant.panelticket,
					Frame.InstanceFRestaurant.panelProductos);
		}
		if (e.getActionCommand().equals("SalirPanelProductos")) {
			Frame.InstanceFRestaurant.panelProductos.cerrarPanelProductos(e);
		}
		if (e.getActionCommand().equals("SalirPanelTique")) {
			Frame.InstanceFRestaurant.cambiaPanel(Frame.InstanceFRestaurant.panelProductos,
					Frame.InstanceFRestaurant.panelticket);
		}
		if (e.getActionCommand().equals("Acepta cobro")) {
			Frame.InstanceFRestaurant.cambiaPanel(Frame.InstanceFRestaurant.panelMesas, Frame.InstanceFRestaurant.panelCobro);
			JOptionPane.showMessageDialog(null, "Tique cobrado", "Paid out", JOptionPane.INFORMATION_MESSAGE);
		}
		if (e.getActionCommand().equals("Imprimir")) {
			Frame.InstanceFRestaurant.panelticket.imprimirTicket();
			System.out.println("Gestor botones imprime");
			Frame.log.Escritura("Gestor botones imprime tique");
		}
		if (e.getActionCommand().equals("PaidOut")) {
			Frame.InstanceFRestaurant.panelProductos.pagarPedido();
		}
		if (e.getActionCommand().equals("OpenProductsReport")) {
			// show or create "panelProductsReport" if there is no connetion with database.
			// Show panel using sql if database connection is ok
			if (Test.conex.isConnected()) {
				String sql = "SELECT foodId AS 'Food Number', denomination AS 'Product', section AS 'Section', "
						+ "ingredients AS 'Ingredientes', price AS 'Price (€)', lowprice AS 'Discount' "
						+ "FROM productos";
				try {
					Frame.InstanceFRestaurant.panelReportsData = new Panel(sql);
				} catch (Exception e1) {
					System.out
							.println("Llama panel informe productos" + e1.getMessage() + e1.getStackTrace().toString());
					Frame.log.Escritura("Llama panel informe productos" + e1.getMessage() + e1.getStackTrace());
				}
				Frame.InstanceFRestaurant.cambiaPanel(Frame.InstanceFRestaurant.panelReportsData,
						Frame.InstanceFRestaurant.panelReport);
			} else {
				if (Frame.InstanceFRestaurant.panelProductsReport == null) {
					Frame.InstanceFRestaurant.panelProductsReport = new Panel(
							Frame.InstanceFRestaurant.myRestaurant.getFoods());
				}
				Frame.InstanceFRestaurant.cambiaPanel(Frame.InstanceFRestaurant.panelProductsReport,
						Frame.InstanceFRestaurant.panelReport);
			}
		}
		if (e.getActionCommand().equals("OpenOrdersReport")) {
			// show or create "panelOrdersReport" if there are connection with database.
			// Show panel using sql if database connection is ok
			if (Test.conex.isConnected()) {
				String sql = "SELECT orders.orderId AS 'Order Number', orders.date AS 'Date', "
						+ "orders.OrderPriceWithoutTaxes AS 'Price whitout taxes (€)', "
						+ "orders.orderPrice AS 'Order Price (€)', orders.valorDescuento AS 'Discount', "
						+ "destinopedido.destinationDenomination AS 'Zone', worker.name AS 'Atendido' "
						+ "FROM orders, destinopedido, worker "
						+ "WHERE orders.destination = destinopedido.destinationId AND "
						+ "orders.workerId = worker.workerId;";
				if (Frame.InstanceFRestaurant.panelReportsData == null) {
					try {
						Frame.InstanceFRestaurant.panelReportsData = new Panel(sql);
					} catch (Exception e1) {
						System.out.println(
								"Llama panel informe pedidos" + e1.getMessage() + e1.getStackTrace().toString());
						Frame.log.Escritura("Llama panel informe pedidos" + e1.getMessage() + e1.getStackTrace());
					}
				}
				Frame.InstanceFRestaurant.cambiaPanel(Frame.InstanceFRestaurant.panelReportsData,
						Frame.InstanceFRestaurant.panelReport);
			} else {
				if (Frame.InstanceFRestaurant.panelOrderReport == null) {
					Frame.InstanceFRestaurant.panelOrderReport = new Panel(Frame.InstanceFRestaurant.myRestaurant.getOrders(),
							"Informe");
				}
				Frame.InstanceFRestaurant.cambiaPanel(Frame.InstanceFRestaurant.panelOrderReport,
						Frame.InstanceFRestaurant.panelReport);
			}
		}
		if (e.getActionCommand().equals("OpenWorkersReport")) {
			if (Test.conex.isConnected()) {
				String sql = "SELECT workerId AS 'Worker Number', name AS 'Name', surNames AS 'Surnames', "
						+ "dni AS 'Dni', salary AS 'Salary (€)', telephone AS 'Telephone' " + "FROM worker";
				try {
					Frame.InstanceFRestaurant.panelReportsData = new Panel(sql);
				} catch (Exception e1) {
					System.out.println(
							"Llama panel informe trabajadores" + e1.getMessage() + e1.getStackTrace().toString());
					Frame.log.Escritura("Llama panel informe trabajadores" + e1.getMessage() + e1.getStackTrace());
				}
				Frame.InstanceFRestaurant.cambiaPanel(Frame.InstanceFRestaurant.panelReportsData,
						Frame.InstanceFRestaurant.panelReport);
			} else {
				if (Frame.InstanceFRestaurant.panelWorkersReport == null) {
					Frame.InstanceFRestaurant.panelWorkersReport = new Panel(
							Frame.InstanceFRestaurant.myRestaurant.getWorkers(), "Informe", "Trabajadores");
				}
				Frame.InstanceFRestaurant.cambiaPanel(Frame.InstanceFRestaurant.panelWorkersReport,
						Frame.InstanceFRestaurant.panelReport);
			}
		}
		if (e.getActionCommand().equals("SalirPanelReports")) {
			Frame.InstanceFRestaurant.cambiaPanel(Frame.InstanceFRestaurant.panelPrincipal,
					Frame.InstanceFRestaurant.panelReport);
		}
		if (e.getActionCommand().equals("BackReportsFromProductsReport")) {
			Frame.InstanceFRestaurant.cambiaPanel(Frame.InstanceFRestaurant.panelReport,
					Frame.InstanceFRestaurant.panelProductsReport);
		}
		if (e.getActionCommand().equals("BackReportsFromOrderReport")) {
			Frame.InstanceFRestaurant.cambiaPanel(Frame.InstanceFRestaurant.panelReport,
					Frame.InstanceFRestaurant.panelOrderReport);
		}
		if (e.getActionCommand().equals("BackReportsFromWorkersReport")) {
			Frame.InstanceFRestaurant.cambiaPanel(Frame.InstanceFRestaurant.panelReport,
					Frame.InstanceFRestaurant.panelWorkersReport);
		}
		if (e.getActionCommand().equals("BackReportsFromReportSql")) {
			Frame.InstanceFRestaurant.cambiaPanel(Frame.InstanceFRestaurant.panelReport,
					Frame.InstanceFRestaurant.panelReportsData);
		}
		if (e.getActionCommand().equals("OpenPanelInsert")) {		
			if (Frame.InstanceFRestaurant.panelInsert == null) {
				Frame.InstanceFRestaurant.panelInsert = new PanelInsert();
			}
			Frame.InstanceFRestaurant.cambiaPanel(Frame.InstanceFRestaurant.panelInsert,
			Frame.InstanceFRestaurant.panelPrincipal);
		}
		if (e.getActionCommand().equals("SalirPanelInsert")) {
			Frame.InstanceFRestaurant.cambiaPanel(Frame.InstanceFRestaurant.panelPrincipal,
					Frame.InstanceFRestaurant.panelInsert);
		}
		if (e.getActionCommand().equals("cbSelectionChange")) {
			PanelInsert mypan = Frame.InstanceFRestaurant.panelInsert; // only to use a short description
			if (((JComboBox) e.getSource()).getSelectedItem() == "Worker") {
				if(mypan.panelProduct != null)
					mypan.remove(mypan.panelProduct);
				mypan.add(mypan.panelWorkerData,BorderLayout.CENTER);
				mypan.changeVisibility(true, mypan.lblSelectWorkerType, mypan.cbWorkerType);
			};
			if (((JComboBox) e.getSource()).getSelectedItem() == "Product") {
				if(mypan.panelWorkerData != null)
					mypan.remove(mypan.panelWorkerData);	
				mypan.add(mypan.panelProduct, BorderLayout.CENTER);
				mypan.changeVisibility(false, mypan.lblSelectWorkerType, mypan.cbWorkerType);
			};
			mypan.repaint();
			System.out.println("Gestor botones. cambia tipo seleccion trabajador o producto");
		}

		if (e.getActionCommand().equals("cbWorkerTypeChange")) {
			PanelInsert mypan = Frame.InstanceFRestaurant.panelInsert; // only to use a short
			if (((JComboBox) e.getSource()).getSelectedItem() == "Waiter") {
				mypan.changeVisibility(true, mypan.panelWorkerData.lblCocktail, mypan.panelWorkerData.lblLanguage1, mypan.panelWorkerData.lblLanguage2,
						mypan.panelWorkerData.lblLanguage3, mypan.panelWorkerData.chbCocktail, mypan.panelWorkerData.tfLanguage1, mypan.panelWorkerData.tfLanguage2, mypan.panelWorkerData.tfLanguage3);
				mypan.changeVisibility(false, mypan.panelWorkerData.lblSpeciality, mypan.panelWorkerData.lblWorkExperience, mypan.panelWorkerData.lblKitchenCategory,
						mypan.panelWorkerData.tfSpeciality, mypan.panelWorkerData.tfWorkExperience, mypan.panelWorkerData.cbKitchenCategory);
				mypan.changeVisibility(false, mypan.panelWorkerData.lblDeliveryMode, mypan.panelWorkerData.lblAge, mypan.panelWorkerData.lblMotorcycleLicense,
						mypan.panelWorkerData.lblOwnVehicle, mypan.panelWorkerData.cbDeliveryMode, mypan.panelWorkerData.tfAge, mypan.panelWorkerData.chbMotorcycleLicense,
						mypan.panelWorkerData.chbOwnVehicle);
			};
			if (((JComboBox) e.getSource()).getSelectedItem() == "Cooker") {
				
				mypan.changeVisibility(false, mypan.panelWorkerData.lblCocktail, mypan.panelWorkerData.lblLanguage1, mypan.panelWorkerData.lblLanguage2,
						mypan.panelWorkerData.lblLanguage3, mypan.panelWorkerData.chbCocktail, mypan.panelWorkerData.tfLanguage1, mypan.panelWorkerData.tfLanguage2, mypan.panelWorkerData.tfLanguage3);
				mypan.changeVisibility(true, mypan.panelWorkerData.lblSpeciality, mypan.panelWorkerData.lblWorkExperience, mypan.panelWorkerData.lblKitchenCategory,
						mypan.panelWorkerData.tfSpeciality, mypan.panelWorkerData.tfWorkExperience, mypan.panelWorkerData.cbKitchenCategory);
				mypan.changeVisibility(false, mypan.panelWorkerData.lblDeliveryMode, mypan.panelWorkerData.lblAge, mypan.panelWorkerData.lblMotorcycleLicense,
						mypan.panelWorkerData.lblOwnVehicle, mypan.panelWorkerData.cbDeliveryMode, mypan.panelWorkerData.tfAge, mypan.panelWorkerData.chbMotorcycleLicense,
						mypan.panelWorkerData.chbOwnVehicle);
			};
			if (((JComboBox) e.getSource()).getSelectedItem() == "Delivery man") {
				mypan.changeVisibility(false, mypan.panelWorkerData.lblCocktail, mypan.panelWorkerData.lblLanguage1, mypan.panelWorkerData.lblLanguage2,
						mypan.panelWorkerData.lblLanguage3, mypan.panelWorkerData.chbCocktail, mypan.panelWorkerData.tfLanguage1, mypan.panelWorkerData.tfLanguage2, mypan.panelWorkerData.tfLanguage3);
				mypan.changeVisibility(false, mypan.panelWorkerData.lblSpeciality, mypan.panelWorkerData.lblWorkExperience, mypan.panelWorkerData.lblKitchenCategory,
						mypan.panelWorkerData.tfSpeciality, mypan.panelWorkerData.tfWorkExperience, mypan.panelWorkerData.cbKitchenCategory);
				mypan.changeVisibility(true, mypan.panelWorkerData.lblDeliveryMode, mypan.panelWorkerData.lblAge, mypan.panelWorkerData.lblMotorcycleLicense,
						mypan.panelWorkerData.lblOwnVehicle, mypan.panelWorkerData.cbDeliveryMode, mypan.panelWorkerData.tfAge, mypan.panelWorkerData.chbMotorcycleLicense,
						mypan.panelWorkerData.chbOwnVehicle);
			};
			System.out.println("Gestor botones. cambia tipo trabajador en panel de inserción");
		}
		if (e.getActionCommand().equals("ApplyPanelInsert")) {
			
			PanelInsert mypan = Frame.InstanceFRestaurant.panelInsert;  //only to use shorted sentences		
			switch((String)mypan.cbSelection.getSelectedItem()) {
			case "Product":
				Productos product;
				if(mypan.requiredFields(mypan.panelProduct.tfDenomination, mypan.panelProduct.tfPrice)) {  					
					product = mypan.insertProduct();  //insert product in a List
					if(Test.conex.isConnected()) {
						Test.conex.insertProductBD(product,"productos");
					}				
				}
				break;
			case "Worker":
			Trabajador worker;
				if(mypan.requiredFields(mypan.panelWorkerData.tfName, mypan.panelWorkerData.tfPassword, mypan.panelWorkerData.tfSalary)) {  
					worker = mypan.insertWorker((String)mypan.cbWorkerType.getSelectedItem());
					if(Test.conex.isConnected()) {
						Test.conex.insertWorkerBD(worker,"worker");
					}
				}
				break;
			}
			Frame.InstanceFRestaurant.cambiaPanel(Frame.InstanceFRestaurant.panelPrincipal,
			Frame.InstanceFRestaurant.panelInsert);
			Frame.InstanceFRestaurant.panelInsert = null;					
		}
	}	

}