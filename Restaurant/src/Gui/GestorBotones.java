package Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.GeneralSecurityException;
import javax.swing.JOptionPane;

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
					}

					Frame.InstanceFPizzerie.setTrabajadorValidado((Trabajador) Frame.InstanceFPizzerie.getPanelValida().getComboBoxUser().getSelectedItem());
				}
			} catch (GeneralSecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelMesas,	Frame.InstanceFPizzerie.panelValida);
		}
		if (e.getActionCommand().equals("Salir panel valida")) {
			Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelPrincipal,	Frame.InstanceFPizzerie.panelValida);
		}
		if (e.getActionCommand().equals("AbrirPanelConfiguracion")) {
		}
		if (e.getActionCommand().equals("AbrirPanelReports")) {
			// create report panel if not exist
			if (Frame.InstanceFPizzerie.panelReport == null) {
				Frame.InstanceFPizzerie.panelReport = new Panel(1);
			}
			Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelReport, Frame.InstanceFPizzerie.panelPrincipal);
		}
		if (e.getActionCommand().equals("OpenProductsPanel")) {
			// Se guarda en la variable estática BotonMesa el boton que genera la apertura
			// de panel productos
			Panel.setBotonMesa((BotonPizzeriaMesas) e.getSource());
			Frame.InstanceFPizzerie.panelMesas.abrirPanelProductos();
		}
		if (e.getActionCommand().equals("SalirPanelMesas")) {
			Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelPrincipal,	Frame.InstanceFPizzerie.panelMesas);
		}
		if (e.getActionCommand().equals("AddToOrder")) {
			Frame.InstanceFPizzerie.panelProductos.anyadeProducto(e);
		}
		if (e.getActionCommand().equals("OpenTiquePanel")) {
			// completa datos del Pedido y se pasa al panel tiquet
			Frame.InstanceFPizzerie.panelProductos.upgradeOrderData(Panel.getBotonMesa().getPedidoBoton());
			Frame.InstanceFPizzerie.panelticket = new panelticket(Panel.getBotonMesa().getPedidoBoton());
			Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelticket,Frame.InstanceFPizzerie.panelProductos);
		}
		if (e.getActionCommand().equals("SalirPanelProductos")) {
			Frame.InstanceFPizzerie.panelProductos.cerrarPanelProductos(e);
		}
		if (e.getActionCommand().equals("SalirPanelTique")) {
			Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelProductos,	Frame.InstanceFPizzerie.panelticket);
		}
		if (e.getActionCommand().equals("Acepta cobro")) {
			Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelMesas,	Frame.InstanceFPizzerie.panelCobro);
			JOptionPane.showMessageDialog(null, "Tique cobrado", "Paid out", JOptionPane.INFORMATION_MESSAGE);
		}
		if (e.getActionCommand().equals("Imprimir")) {
			Frame.InstanceFPizzerie.panelticket.imprimirTicket();
			System.out.println("Gestor botones imprime");
		}
		if (e.getActionCommand().equals("PaidOut")) {
			Frame.InstanceFPizzerie.panelProductos.pagarPedido();
		}
		if (e.getActionCommand().equals("OpenProductsReport")) {
			// show or create products report panel
			if (Frame.InstanceFPizzerie.panelProductsReport == null) {
				Frame.InstanceFPizzerie.panelProductsReport = new Panel(Frame.InstanceFPizzerie.myPizzerie.getFoods());
			}
			Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelProductsReport,	Frame.InstanceFPizzerie.panelReport);
		}
		if (e.getActionCommand().equals("SalirPanelReports")) {
			Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelPrincipal,	Frame.InstanceFPizzerie.panelReport);
		}
		if (e.getActionCommand().equals("OpenOrderReport")) {
			// Open order report
			if (Frame.InstanceFPizzerie.panelOrderReport == null) {
				Frame.InstanceFPizzerie.panelOrderReport = new Panel(Frame.InstanceFPizzerie.myPizzerie.getOrders(),"Informe");
			}			
			Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelOrderReport,	Frame.InstanceFPizzerie.panelReport);			
		}
		if (e.getActionCommand().equals("OpenWorkersReport")) {
			Frame.InstanceFPizzerie.panelWorkersReport = new Panel(Frame.InstanceFPizzerie.myPizzerie.getWorkers(),"Informe", "Trabajadores");
			Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelWorkersReport,	Frame.InstanceFPizzerie.panelReport);
		}
		if (e.getActionCommand().equals("BackReportsFromProductsReport")) {
			Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelReport,	Frame.InstanceFPizzerie.panelProductsReport);
		}
		if (e.getActionCommand().equals("BackReportsFromOrderReport")) {
			Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelReport,	Frame.InstanceFPizzerie.panelOrderReport);
		}
		if (e.getActionCommand().equals("BackReportsFromWorkersReport")) {
			Frame.InstanceFPizzerie.cambiaPanel(Frame.InstanceFPizzerie.panelReport,	Frame.InstanceFPizzerie.panelWorkersReport);
		}
	}

}