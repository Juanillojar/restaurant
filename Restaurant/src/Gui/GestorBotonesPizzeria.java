package Gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;


public class GestorBotonesPizzeria implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("AbrirPanelValida")) {
			// show valilda panel
			if (FramePizzeria.InstanceFPizzerie.panelValida == null) {
					FramePizzeria.InstanceFPizzerie.panelValida =new PanelValida(FramePizzeria.InstanceFPizzerie.myPizzerie.getWorkers());
					System.out.println("Se crea panel valida");
			}else {
				//Cada botón debe tener el pedido abierto si existe
				System.out.println("No se crea el panel Valida. Ya existe");
			}
			FramePizzeria.InstanceFPizzerie.ActivaPanel(FramePizzeria.InstanceFPizzerie.panelValida);
			FramePizzeria.InstanceFPizzerie.getPanelValida().setVisible(true);
			FramePizzeria.InstanceFPizzerie.getPanelPrincipal().setVisible(false);
		}
		if(e.getActionCommand().equals("Valida clave")) {
			if (validaClave()) {
				// inserted password is correct. Show bar and tables panel
				if (FramePizzeria.InstanceFPizzerie.panelMesas == null) {
						FramePizzeria.InstanceFPizzerie.panelMesas =new PanelPizzeria(FramePizzeria.InstanceFPizzerie.myPizzerie.getBarZones(), FramePizzeria.InstanceFPizzerie.myPizzerie.getDeliveryZones() ,FramePizzeria.InstanceFPizzerie.myPizzerie.getInTables(),FramePizzeria.InstanceFPizzerie.myPizzerie.getOutTables());
						System.out.println("Se crea panel mesas");
				}else {
					//Cada botón debe tener el pedido abierto si existe
					System.out.println("No se crea el panel mesas. Ya existe");
				}
				FramePizzeria.InstanceFPizzerie.setTrabajadorValidado((Trabajador)FramePizzeria.InstanceFPizzerie.getPanelValida().getComboBoxUser().getSelectedItem());
				FramePizzeria.InstanceFPizzerie.ActivaPanel(FramePizzeria.InstanceFPizzerie.panelMesas);
				FramePizzeria.InstanceFPizzerie.getPanelMesas().setVisible(true);
				FramePizzeria.InstanceFPizzerie.getPanelValida().setVisible(false);
			}
			
		}
		if(e.getActionCommand().equals("Salir panel valida")) {
			// back to principal panel
			FramePizzeria.InstanceFPizzerie.ActivaPanel(FramePizzeria.InstanceFPizzerie.panelPrincipal);
			FramePizzeria.InstanceFPizzerie.getPanelPrincipal().setVisible(true);
			FramePizzeria.InstanceFPizzerie.getPanelValida().setVisible(false);
		}

		if (e.getActionCommand().equals("AbrirPanelConfiguracion")) {
		}
		if (e.getActionCommand().equals("AbrirPanelReports")) {
			// show report panel
			if (FramePizzeria.InstanceFPizzerie.panelReport == null) {
				FramePizzeria.InstanceFPizzerie.panelReport = new PanelPizzeria(1);
			}
			FramePizzeria.InstanceFPizzerie.ActivaPanel(FramePizzeria.InstanceFPizzerie.panelReport);
			FramePizzeria.InstanceFPizzerie.getPanelReport().setVisible(true);
			FramePizzeria.InstanceFPizzerie.getPanelPrincipal().setVisible(false);
		}
		if (e.getActionCommand().equals("OpenProductsPanel")) {
			//Se guarda en la variable estática BotonMesa el boton que genera la apertura de panel productos
			PanelPizzeria.setBotonMesa((BotonPizzeriaMesas)e.getSource());
			abrirPanelProductos();
		}
		if (e.getActionCommand().equals("SalirPanelMesas")) {
			FramePizzeria.InstanceFPizzerie.getPanelMesas().setVisible(false);
			FramePizzeria.InstanceFPizzerie.getPanelPrincipal().setVisible(true);
			System.out.println("Sali panel mesas.");
		}		
		if (e.getActionCommand().equals("AddToOrder")) {
			anyadeProducto(e);
		}
		if (e.getActionCommand().equals("OpenTiquePanel")) {
			// completa datos del Pedido y se pasa al panel tiquet
			FramePizzeria.InstanceFPizzerie.panelProductos.upgradeOrderData(PanelPizzeria.getBotonMesa().getPedidoBoton());
			//FramePizzeria.InstanceFPizzerie.panelTique = new PanelPizzeria(PanelPizzeria.getBotonMesa().getPedidoBoton());
			FramePizzeria.InstanceFPizzerie.panelticket = new panelticket(PanelPizzeria.getBotonMesa().getPedidoBoton());
			
			//FramePizzeria.InstanceFPizzerie.ActivaPanel(FramePizzeria.InstanceFPizzerie.panelTique);
			//FramePizzeria.InstanceFPizzerie.getPanelTique().setVisible(true);
			//FramePizzeria.InstanceFPizzerie.getPanelProductos().setVisible(false);
			FramePizzeria.InstanceFPizzerie.ActivaPanel(FramePizzeria.InstanceFPizzerie.panelticket);
			FramePizzeria.InstanceFPizzerie.getPanelticket().setVisible(true);
			FramePizzeria.InstanceFPizzerie.getPanelProductos().setVisible(false);
		}

		if (e.getActionCommand().equals("SalirPanelProductos")) {
			cerrarPanelProductos(e);
		}
		if (e.getActionCommand().equals("SalirPanelTique")) {
			// back to products panel
			FramePizzeria.InstanceFPizzerie.ActivaPanel(FramePizzeria.InstanceFPizzerie.panelProductos);
			FramePizzeria.InstanceFPizzerie.getPanelProductos().setVisible(true);
			//FramePizzeria.InstanceFPizzerie.getPanelTique().setVisible(false);
			FramePizzeria.InstanceFPizzerie.getPanelticket().setVisible(false);
		}
		if (e.getActionCommand().equals("PaidOut")) {
			pagarPedido();
		}		
		
		if (e.getActionCommand().equals("OpenProductsReport")) {
			// show product report
			if (FramePizzeria.InstanceFPizzerie.panelProductsReport == null) {
				FramePizzeria.InstanceFPizzerie.panelProductsReport = new PanelPizzeria(FramePizzeria.InstanceFPizzerie.myPizzerie.getFoods());
			}
			
			FramePizzeria.InstanceFPizzerie.ActivaPanel(FramePizzeria.InstanceFPizzerie.panelProductsReport);
			FramePizzeria.InstanceFPizzerie.getPanelProductsReport().setVisible(true);
			FramePizzeria.InstanceFPizzerie.getPanelReport().setVisible(false);
		}

		if (e.getActionCommand().equals("SalirPanelReports")) {
			// back to main panel
			FramePizzeria.InstanceFPizzerie.getPanelReport().setVisible(false);
			FramePizzeria.InstanceFPizzerie.getPanelPrincipal().setVisible(true);
		}

		if (e.getActionCommand().equals("OpenOrderReport")) {
			// Open order report
			FramePizzeria.InstanceFPizzerie.panelOrderReport = new PanelPizzeria(FramePizzeria.InstanceFPizzerie.myPizzerie.getOrders(),"Informe");
			FramePizzeria.InstanceFPizzerie.ActivaPanel(FramePizzeria.InstanceFPizzerie.panelOrderReport);
			FramePizzeria.InstanceFPizzerie.panelReport.setVisible(false);
		}

		if (e.getActionCommand().equals("BackReportsFromProductsReport")) {
			// back to reports panel
			FramePizzeria.InstanceFPizzerie.ActivaPanel(FramePizzeria.InstanceFPizzerie.panelReport);
			FramePizzeria.InstanceFPizzerie.getPanelReport().setVisible(true);
			FramePizzeria.InstanceFPizzerie.getPanelProductsReport().setVisible(false);
		}

		if (e.getActionCommand().equals("BackReportsFromOrderReport")) {
			// back to reports panel from order report
			FramePizzeria.InstanceFPizzerie.ActivaPanel(FramePizzeria.InstanceFPizzerie.panelReport);
			FramePizzeria.InstanceFPizzerie.getPanelReport().setVisible(true);
			FramePizzeria.InstanceFPizzerie.panelOrderReport.setVisible(false);
			System.out.println("back from order report");
		}
		
	}
	
	public boolean validaClave() {
		if(((Trabajador)FramePizzeria.InstanceFPizzerie.getPanelValida().getComboBoxUser().getSelectedItem()).getClave().equals(FramePizzeria.InstanceFPizzerie.getPanelValida().getPasswordField().getText())) {
			return true;
		}else {
			JOptionPane.showMessageDialog(null, "Clave not match","clave insertion" , JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
	}
	
	public void abrirPanelProductos() {
		if (FramePizzeria.InstanceFPizzerie.panelProductos == null) {
			System.out.println("nuevo panel productos");
			FramePizzeria.InstanceFPizzerie.panelProductos = new PanelPizzeria(FramePizzeria.InstanceFPizzerie.myPizzerie.foods, PanelPizzeria.getBotonMesa());
		}else { // ya existe el panel de productos
			System.out.println("Ya existe panel productos");
			if (FramePizzeria.InstanceFPizzerie.panelMesas.checkForOpenOrder(PanelPizzeria.getBotonMesa().getPedidoBoton())) { //hay un pedido pendiente de la mesa asociada al botón
				System.out.println("Existe pedido en curso de mesa "+ PanelPizzeria.getBotonMesa().getPedidoBoton().getDestination().getDestinationDenomination());
				//carga total pedido en curso en variable estática
				PanelPizzeria.setSubtotal(PanelPizzeria.getBotonMesa().getPedidoBoton().getOrderPriceWithoutTaxes());
				//Actualiza los valores de total pedido en curso y muestra el boton tique
				FramePizzeria.InstanceFPizzerie.getPanelProductos().getLsubtotalOrder().setText("Subtotal Order: " + PanelPizzeria.getBotonMesa().getPedidoBoton().getOrderPriceWithoutTaxes());
				FramePizzeria.InstanceFPizzerie.getPanelProductos().getButtonTique().setVisible(true);
			}	
		}
		//actualiza el texto del label que indica la mesa que se está tratando
		FramePizzeria.InstanceFPizzerie.getPanelProductos().getlDestinoPedidoEnCurso().setText(PanelPizzeria.getBotonMesa().getDestinoBoton().getDestinationDenomination());
		FramePizzeria.InstanceFPizzerie.getPanelProductos().getlDestinoPedidoEnCurso().repaint();
		//Visualiza panel productos y oculta panel mesas
		FramePizzeria.InstanceFPizzerie.ActivaPanel(FramePizzeria.InstanceFPizzerie.panelProductos);
		FramePizzeria.InstanceFPizzerie.getPanelProductos().setVisible(true);
		FramePizzeria.InstanceFPizzerie.getPanelMesas().setVisible(false);
	}
	
	public void cerrarPanelProductos(ActionEvent e){
		if(PanelPizzeria.getBotonMesa().getPedidoBoton() != null) { //Se ha insertado producto/s al pedido	
			//rellena datos del pedido (el operador añade productos sin ver el tique)
			FramePizzeria.InstanceFPizzerie.panelProductos.upgradeOrderData(PanelPizzeria.getBotonMesa().getPedidoBoton());
			PanelPizzeria.getBotonMesa().setBackground(Color.MAGENTA);
			//guarda los datos en el botón del panel de mesas
			((BotonPizzeriaMesas)e.getSource()).setPedidoBoton(PanelPizzeria.getBotonMesa().getPedidoBoton());	
		}
		reseteaVariablesEstaticas();	// back to bar and tables panel
		FramePizzeria.InstanceFPizzerie.ActivaPanel(FramePizzeria.InstanceFPizzerie.panelMesas);
		FramePizzeria.InstanceFPizzerie.getPanelMesas().setVisible(true);
		FramePizzeria.InstanceFPizzerie.getPanelProductos().setVisible(false);		
	}
	
	public void reseteaVariablesEstaticas(){
		PanelPizzeria.setBotonMesa(null);
		FramePizzeria.InstanceFPizzerie.getPanelProductos().IncrementaSutotal(0.0);
		FramePizzeria.InstanceFPizzerie.panelProductos.getButtonTique().setVisible(false);
	}
	
	public void pagarPedido() {
		//store to pizzeria order list and mark as paid
		FramePizzeria.InstanceFPizzerie.getPanelProductos().paidOut(PanelPizzeria.getBotonMesa().getPedidoBoton());
		FramePizzeria.InstanceFPizzerie.myPizzerie.getOrders().add(PanelPizzeria.getBotonMesa().getPedidoBoton());
		PanelPizzeria.getBotonMesa().setBackground(Color.LIGHT_GRAY);
		//reset de variables estáticas
		PanelPizzeria.getBotonMesa().setPedidoBoton(null);//resetea el pedido guardado
		reseteaVariablesEstaticas();
		JOptionPane.showMessageDialog(null, "Tique cobrado","Paid out" , JOptionPane.INFORMATION_MESSAGE);
		//abrir panel de mesas
		//FramePizzeria.InstanceFPizzerie.panelTique.setVisible(false);
		FramePizzeria.InstanceFPizzerie.panelticket.setVisible(false);
		FramePizzeria.InstanceFPizzerie.panelMesas.setVisible(true);	
	}
	
	public void anyadeProducto(ActionEvent e) {
		if (PanelPizzeria.getBotonMesa().getPedidoBoton() == null) {//crear pedido y añadir el producto
			PanelPizzeria.getBotonMesa().setPedidoBoton(new Pedido(PanelPizzeria.getBotonMesa().getDestinoBoton()));
		}
		// Add product to the order object list
		PanelPizzeria.getBotonMesa().getPedidoBoton().getOrderFoods().add(((BotonPizzeria) e.getSource()).getProducto());			
		// incrementa el precio del pedido, lo guarda en el botón y actualiza el label
		PanelPizzeria.getBotonMesa().getPedidoBoton().setOrderPriceWithoutTaxes(FramePizzeria.InstanceFPizzerie.panelProductos.IncrementaSutotal(((BotonPizzeria) e.getSource()).getProducto().getPrice()));
		// make visible Tique button
		FramePizzeria.InstanceFPizzerie.getPanelProductos().getButtonTique().setVisible(true);
		System.out.println("Añade producto al pedido.");
	}
}