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
			if (Frame.InstanceFPizzerie.panelValida == null) {
				Frame.InstanceFPizzerie.panelValida = new PanelValida(Frame.InstanceFPizzerie.myPizzerie.getWorkers());
				System.out.println("Se crea panel valida");
			}
			Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelValida,
					Frame.InstanceFPizzerie.panelPrincipal);
		}
		if (e.getActionCommand().equals("Valida clave")) {
			try {
				if (Frame.InstanceFPizzerie.getPanelValida().validaClave()) {
					// inserted password is correct. Show bar and tables panel
					if (Frame.InstanceFPizzerie.panelMesas == null) {
						Frame.InstanceFPizzerie.panelMesas = new Panel(Frame.InstanceFPizzerie.myPizzerie.getBarZones(),
								Frame.InstanceFPizzerie.myPizzerie.getDeliveryZones(),
								Frame.InstanceFPizzerie.myPizzerie.getInTables(),
								Frame.InstanceFPizzerie.myPizzerie.getOutTables());
						System.out.println("Se crea panel mesas");
						Frame.log.Escritura("Se crea panel mesas");
					}

					Frame.InstanceFPizzerie.setTrabajadorValidado(
							(Trabajador) Frame.InstanceFPizzerie.getPanelValida().getComboBoxUser().getSelectedItem());
				}
			} catch (GeneralSecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelMesas,
					Frame.InstanceFPizzerie.panelValida);
		}
		if (e.getActionCommand().equals("Salir panel valida")) {
			Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelPrincipal,
					Frame.InstanceFPizzerie.panelValida);
		}
		if (e.getActionCommand().equals("AbrirPanelConfiguracion")) {
			if (Frame.InstanceFPizzerie.panelConfig == null) {
				Frame.InstanceFPizzerie.panelConfig = new PanelConfig(Test.arrayConfig);
			}
			Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelConfig,
					Frame.InstanceFPizzerie.panelPrincipal);

		}
		if (e.getActionCommand().equals("SalirPanelConfig")) {
			Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelPrincipal,
					Frame.InstanceFPizzerie.panelConfig);
		}

		if (e.getActionCommand().equals("ApplyPanelConfig")) {
			// Store data from config panel
			Frame.InstanceFPizzerie.getPanelConfig().getMyXmlDoc()
					.store(Frame.InstanceFPizzerie.getPanelConfig().getMyXmlDoc().read());
		}

		if (e.getActionCommand().equals("AbrirPanelReports")) {
			// create report panel if not exist
			if (Frame.InstanceFPizzerie.panelReport == null) {
				Frame.InstanceFPizzerie.panelReport = new Panel(1);
			}
			Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelReport,
					Frame.InstanceFPizzerie.panelPrincipal);
		}
		if (e.getActionCommand().equals("OpenProductsPanel")) {
			// store static variable BotonMesa (button that generate open products panel
			Panel.setBotonMesa((BotonRestauranteMesas) e.getSource());
			Frame.InstanceFPizzerie.panelMesas.abrirPanelProductos();
		}
		if (e.getActionCommand().equals("SalirPanelMesas")) {
			Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelPrincipal,
					Frame.InstanceFPizzerie.panelMesas);
		}
		if (e.getActionCommand().equals("AddToOrder")) {
			Frame.InstanceFPizzerie.panelProductos.anyadeProducto(e);
		}
		if (e.getActionCommand().equals("OpenTiquePanel")) {
			// completa datos del Pedido y se pasa al panel tiquet
			Frame.InstanceFPizzerie.panelProductos.upgradeOrderData(Panel.getBotonMesa().getPedidoBoton());
			Frame.InstanceFPizzerie.panelticket = new panelticket(Panel.getBotonMesa().getPedidoBoton());
			Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelticket,
					Frame.InstanceFPizzerie.panelProductos);
		}
		if (e.getActionCommand().equals("SalirPanelProductos")) {
			Frame.InstanceFPizzerie.panelProductos.cerrarPanelProductos(e);
		}
		if (e.getActionCommand().equals("SalirPanelTique")) {
			Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelProductos,
					Frame.InstanceFPizzerie.panelticket);
		}
		if (e.getActionCommand().equals("Acepta cobro")) {
			Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelMesas, Frame.InstanceFPizzerie.panelCobro);
			JOptionPane.showMessageDialog(null, "Tique cobrado", "Paid out", JOptionPane.INFORMATION_MESSAGE);
		}
		if (e.getActionCommand().equals("Imprimir")) {
			Frame.InstanceFPizzerie.panelticket.imprimirTicket();
			System.out.println("Gestor botones imprime");
			Frame.log.Escritura("Gestor botones imprime tique");
		}
		if (e.getActionCommand().equals("PaidOut")) {
			Frame.InstanceFPizzerie.panelProductos.pagarPedido();
		}
		if (e.getActionCommand().equals("OpenProductsReport")) {
			// show or create "panelProductsReport" if there is no connetion with database.
			// Show panel using sql if database connection is ok
			if (Test.conex.isConnected()) {
				String sql = "SELECT foodId AS 'Food Number', denomination AS 'Product', section AS 'Section', "
						+ "ingredients AS 'Ingredientes', price AS 'Price (€)', lowprice AS 'Discount' "
						+ "FROM productos";
				try {
					Frame.InstanceFPizzerie.panelReportsData = new Panel(sql);
				} catch (Exception e1) {
					System.out
							.println("Llama panel informe productos" + e1.getMessage() + e1.getStackTrace().toString());
					Frame.log.Escritura("Llama panel informe productos" + e1.getMessage() + e1.getStackTrace());
				}
				Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelReportsData,
						Frame.InstanceFPizzerie.panelReport);
			} else {
				if (Frame.InstanceFPizzerie.panelProductsReport == null) {
					Frame.InstanceFPizzerie.panelProductsReport = new Panel(
							Frame.InstanceFPizzerie.myPizzerie.getFoods());
				}
				Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelProductsReport,
						Frame.InstanceFPizzerie.panelReport);
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
				if (Frame.InstanceFPizzerie.panelReportsData == null) {
					try {
						Frame.InstanceFPizzerie.panelReportsData = new Panel(sql);
					} catch (Exception e1) {
						System.out.println(
								"Llama panel informe pedidos" + e1.getMessage() + e1.getStackTrace().toString());
						Frame.log.Escritura("Llama panel informe pedidos" + e1.getMessage() + e1.getStackTrace());
					}
				}
				Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelReportsData,
						Frame.InstanceFPizzerie.panelReport);
			} else {
				if (Frame.InstanceFPizzerie.panelOrderReport == null) {
					Frame.InstanceFPizzerie.panelOrderReport = new Panel(Frame.InstanceFPizzerie.myPizzerie.getOrders(),
							"Informe");
				}
				Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelOrderReport,
						Frame.InstanceFPizzerie.panelReport);
			}
		}
		if (e.getActionCommand().equals("OpenWorkersReport")) {
			if (Test.conex.isConnected()) {
				String sql = "SELECT workerId AS 'Worker Number', name AS 'Name', surNames AS 'Surnames', "
						+ "dni AS 'Dni', salary AS 'Salary (€)', telephone AS 'Telephone' " + "FROM worker";
				try {
					Frame.InstanceFPizzerie.panelReportsData = new Panel(sql);
				} catch (Exception e1) {
					System.out.println(
							"Llama panel informe trabajadores" + e1.getMessage() + e1.getStackTrace().toString());
					Frame.log.Escritura("Llama panel informe trabajadores" + e1.getMessage() + e1.getStackTrace());
				}
				Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelReportsData,
						Frame.InstanceFPizzerie.panelReport);
			} else {
				if (Frame.InstanceFPizzerie.panelWorkersReport == null) {
					Frame.InstanceFPizzerie.panelWorkersReport = new Panel(
							Frame.InstanceFPizzerie.myPizzerie.getWorkers(), "Informe", "Trabajadores");
				}
				Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelWorkersReport,
						Frame.InstanceFPizzerie.panelReport);
			}
		}
		if (e.getActionCommand().equals("SalirPanelReports")) {
			Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelPrincipal,
					Frame.InstanceFPizzerie.panelReport);
		}
		if (e.getActionCommand().equals("BackReportsFromProductsReport")) {
			Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelReport,
					Frame.InstanceFPizzerie.panelProductsReport);
		}
		if (e.getActionCommand().equals("BackReportsFromOrderReport")) {
			Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelReport,
					Frame.InstanceFPizzerie.panelOrderReport);
		}
		if (e.getActionCommand().equals("BackReportsFromWorkersReport")) {
			Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelReport,
					Frame.InstanceFPizzerie.panelWorkersReport);
		}
		if (e.getActionCommand().equals("BackReportsFromReportSql")) {
			Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelReport,
					Frame.InstanceFPizzerie.panelReportsData);
		}
		if (e.getActionCommand().equals("OpenPanelInsert")) {		
			if (Frame.InstanceFPizzerie.panelInsert == null) {
				Frame.InstanceFPizzerie.panelInsert = new PanelInsert();
			}
			Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelInsert,
			Frame.InstanceFPizzerie.panelPrincipal);
		}
		if (e.getActionCommand().equals("SalirPanelInsert")) {
			Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelPrincipal,
					Frame.InstanceFPizzerie.panelInsert);
		}
		if (e.getActionCommand().equals("cbSelectionChange")) {
			PanelInsert mypan = Frame.InstanceFPizzerie.panelInsert; // only to use a short description
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
			PanelInsert mypan = Frame.InstanceFPizzerie.panelInsert; // only to use a short
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
			
			PanelInsert mypan = Frame.InstanceFPizzerie.panelInsert;  //only to use shorted sentences		
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
			Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelPrincipal,
			Frame.InstanceFPizzerie.panelInsert);
			Frame.InstanceFPizzerie.panelInsert = null;					
		}
	}	

}