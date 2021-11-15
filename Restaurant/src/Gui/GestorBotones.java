package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;


public class GestorBotones implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("AbrirPanelValida")) {
			// show valilda panel
			if (Frame.InstanceFPizzerie.panelValida == null) {
					Frame.InstanceFPizzerie.panelValida =new PanelValida(Frame.InstanceFPizzerie.myPizzerie.getWorkers());
					System.out.println("Se crea panel valida");
			}else {
				//Cada botón debe tener el pedido abierto si existe
				System.out.println("No se crea el panel Valida. Ya existe");
			}
			Frame.InstanceFPizzerie.ActivaPanel(Frame.InstanceFPizzerie.panelValida);
			Frame.InstanceFPizzerie.getPanelValida().setVisible(true);
			Frame.InstanceFPizzerie.getPanelPrincipal().setVisible(false);
		}
		if(e.getActionCommand().equals("Valida clave")) {
			if (validaClave()) {
				// inserted password is correct. Show bar and tables panel
				if (Frame.InstanceFPizzerie.panelMesas == null) {
						Frame.InstanceFPizzerie.panelMesas =new Panel(Frame.InstanceFPizzerie.myPizzerie.getBarZones(), Frame.InstanceFPizzerie.myPizzerie.getDeliveryZones() ,Frame.InstanceFPizzerie.myPizzerie.getInTables(),Frame.InstanceFPizzerie.myPizzerie.getOutTables());
						System.out.println("Se crea panel mesas");
				}else {
					//Cada botón debe tener el pedido abierto si existe
					System.out.println("No se crea el panel mesas. Ya existe");
				}
				Frame.InstanceFPizzerie.setTrabajadorValidado((Trabajador)Frame.InstanceFPizzerie.getPanelValida().getComboBoxUser().getSelectedItem());
				Frame.InstanceFPizzerie.ActivaPanel(Frame.InstanceFPizzerie.panelMesas);
				Frame.InstanceFPizzerie.getPanelMesas().setVisible(true);
				Frame.InstanceFPizzerie.getPanelValida().setVisible(false);
			}
			
		}
		if(e.getActionCommand().equals("Salir panel valida")) {
			// back to principal panel
			Frame.InstanceFPizzerie.ActivaPanel(Frame.InstanceFPizzerie.panelPrincipal);
			Frame.InstanceFPizzerie.getPanelPrincipal().setVisible(true);
			Frame.InstanceFPizzerie.getPanelValida().setVisible(false);
		}

		if (e.getActionCommand().equals("AbrirPanelConfiguracion")) {
		}
		if (e.getActionCommand().equals("AbrirPanelReports")) {
			// show report panel
			if (Frame.InstanceFPizzerie.panelReport == null) {
				Frame.InstanceFPizzerie.panelReport = new Panel(1);
			}
			Frame.InstanceFPizzerie.ActivaPanel(Frame.InstanceFPizzerie.panelReport);
			Frame.InstanceFPizzerie.getPanelReport().setVisible(true);
			Frame.InstanceFPizzerie.getPanelPrincipal().setVisible(false);
		}
		if (e.getActionCommand().equals("OpenProductsPanel")) {
			//Se guarda en la variable estática BotonMesa el boton que genera la apertura de panel productos
			Panel.setBotonMesa((BotonPizzeriaMesas)e.getSource());
			abrirPanelProductos();
		}
		if (e.getActionCommand().equals("SalirPanelMesas")) {
			Frame.InstanceFPizzerie.getPanelMesas().setVisible(false);
			Frame.InstanceFPizzerie.getPanelPrincipal().setVisible(true);
			System.out.println("Sali panel mesas.");
		}		
		if (e.getActionCommand().equals("AddToOrder")) {
			anyadeProducto(e);
		}
		if (e.getActionCommand().equals("OpenTiquePanel")) {
			// completa datos del Pedido y se pasa al panel tiquet
			Frame.InstanceFPizzerie.panelProductos.upgradeOrderData(Panel.getBotonMesa().getPedidoBoton());
			//FramePizzeria.InstanceFPizzerie.panelTique = new PanelPizzeria(PanelPizzeria.getBotonMesa().getPedidoBoton());
			Frame.InstanceFPizzerie.panelticket = new panelticket(Panel.getBotonMesa().getPedidoBoton());
			
			
			//FramePizzeria.InstanceFPizzerie.ActivaPanel(FramePizzeria.InstanceFPizzerie.panelTique);
			//FramePizzeria.InstanceFPizzerie.getPanelTique().setVisible(true);
			//FramePizzeria.InstanceFPizzerie.getPanelProductos().setVisible(false);
			Frame.InstanceFPizzerie.ActivaPanel(Frame.InstanceFPizzerie.panelticket);
			Frame.InstanceFPizzerie.getPanelticket().setVisible(true);
			
		//	Frame.InstanceFPizzerie.panelticket = new panelticket();
		//	Frame.InstanceFPizzerie.ActivaPanelSouth(Frame.InstanceFPizzerie.panelticketBotones);
		//	Frame.InstanceFPizzerie.getPanelticketBotones().setVisible(true);
			Frame.InstanceFPizzerie.getPanelProductos().setVisible(false);
		}

		if (e.getActionCommand().equals("SalirPanelProductos")) {
			cerrarPanelProductos(e);
		}
		if (e.getActionCommand().equals("SalirPanelTique")) {
			// back to products panel
			Frame.InstanceFPizzerie.ActivaPanel(Frame.InstanceFPizzerie.panelProductos);
			Frame.InstanceFPizzerie.getPanelProductos().setVisible(true);
			//FramePizzeria.InstanceFPizzerie.getPanelTique().setVisible(false);
			Frame.InstanceFPizzerie.getPanelticket().setVisible(false);
			
		}
		if (e.getActionCommand().equals("Acepta cobro")) {
			Frame.InstanceFPizzerie.ActivaPanel(Frame.InstanceFPizzerie.panelMesas);
			Frame.InstanceFPizzerie.getPanelMesas().setVisible(true);
			//abrir panel de mesas
			Frame.InstanceFPizzerie.panelCobro.setVisible(false);
			JOptionPane.showMessageDialog(null, "Tique cobrado","Paid out" , JOptionPane.INFORMATION_MESSAGE);
		}		
		if (e.getActionCommand().equals("Imprimir")) {
			Frame.InstanceFPizzerie.panelticket.imprimirTicket();
			System.out.println("Gestor botones imprime");
		}		

		if (e.getActionCommand().equals("PaidOut")) {
			pagarPedido();
		}		
		
		if (e.getActionCommand().equals("OpenProductsReport")) {
			// show product report
			if (Frame.InstanceFPizzerie.panelProductsReport == null) {
				Frame.InstanceFPizzerie.panelProductsReport = new Panel(Frame.InstanceFPizzerie.myPizzerie.getFoods());
			}
			
			Frame.InstanceFPizzerie.ActivaPanel(Frame.InstanceFPizzerie.panelProductsReport);
			Frame.InstanceFPizzerie.getPanelProductsReport().setVisible(true);
			Frame.InstanceFPizzerie.getPanelReport().setVisible(false);
		}

		if (e.getActionCommand().equals("SalirPanelReports")) {
			// back to main panel
			Frame.InstanceFPizzerie.getPanelReport().setVisible(false);
			Frame.InstanceFPizzerie.getPanelPrincipal().setVisible(true);
		}

		if (e.getActionCommand().equals("OpenOrderReport")) {
			// Open order report
			Frame.InstanceFPizzerie.panelOrderReport = new Panel(Frame.InstanceFPizzerie.myPizzerie.getOrders(),"Informe");
			Frame.InstanceFPizzerie.ActivaPanel(Frame.InstanceFPizzerie.panelOrderReport);
			Frame.InstanceFPizzerie.panelReport.setVisible(false);
		}

		if (e.getActionCommand().equals("BackReportsFromProductsReport")) {
			// back to reports panel
			Frame.InstanceFPizzerie.ActivaPanel(Frame.InstanceFPizzerie.panelReport);
			Frame.InstanceFPizzerie.getPanelReport().setVisible(true);
			Frame.InstanceFPizzerie.getPanelProductsReport().setVisible(false);
		}

		if (e.getActionCommand().equals("BackReportsFromOrderReport")) {
			// back to reports panel from order report
			Frame.InstanceFPizzerie.ActivaPanel(Frame.InstanceFPizzerie.panelReport);
			Frame.InstanceFPizzerie.getPanelReport().setVisible(true);
			Frame.InstanceFPizzerie.panelOrderReport.setVisible(false);
			System.out.println("back from order report");
		}
		
	}
	
	public boolean validaClave() {
		if(((Trabajador)Frame.InstanceFPizzerie.getPanelValida().getComboBoxUser().getSelectedItem()).getClave().equals(Frame.InstanceFPizzerie.getPanelValida().getPasswordField().getText())) {
			return true;
		}else {
			JOptionPane.showMessageDialog(null, "Clave not match","clave insertion" , JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
	}
	
	public void abrirPanelProductos() {
		if (Frame.InstanceFPizzerie.panelProductos == null) {
			System.out.println("nuevo panel productos");
			Frame.InstanceFPizzerie.panelProductos = new Panel(Frame.InstanceFPizzerie.myPizzerie.foods, Panel.getBotonMesa());
		}else { // ya existe el panel de productos
			System.out.println("Ya existe panel productos");
			if (Frame.InstanceFPizzerie.panelMesas.checkForOpenOrder(Panel.getBotonMesa().getPedidoBoton())) { //hay un pedido pendiente de la mesa asociada al botón
				System.out.println("Existe pedido en curso de mesa "+ Panel.getBotonMesa().getPedidoBoton().getDestination().getDestinationDenomination());
				//carga total pedido en curso en variable estática
				Panel.setSubtotal(Panel.getBotonMesa().getPedidoBoton().getOrderPriceWithoutTaxes());
				//Actualiza los valores de total pedido en curso y muestra el boton tique
				Frame.InstanceFPizzerie.getPanelProductos().getLsubtotalOrder().setText("Subtotal Order: " + Panel.getBotonMesa().getPedidoBoton().getOrderPriceWithoutTaxes());
				Frame.InstanceFPizzerie.getPanelProductos().getButtonTique().setVisible(true);
			}	
		}
		//actualiza el texto del label que indica la mesa que se está tratando
		Frame.InstanceFPizzerie.getPanelProductos().getlDestinoPedidoEnCurso().setText(Panel.getBotonMesa().getDestinoBoton().getDestinationDenomination());
		Frame.InstanceFPizzerie.getPanelProductos().getlDestinoPedidoEnCurso().repaint();
		//Visualiza panel productos y oculta panel mesas
		Frame.InstanceFPizzerie.ActivaPanel(Frame.InstanceFPizzerie.panelProductos);
		Frame.InstanceFPizzerie.getPanelProductos().setVisible(true);
		Frame.InstanceFPizzerie.getPanelMesas().setVisible(false);
	}
	
	public void cerrarPanelProductos(ActionEvent e){
		if(Panel.getBotonMesa().getPedidoBoton() != null) { //Se ha insertado producto/s al pedido	
			//rellena datos del pedido (el operador añade productos sin ver el tique)
			Frame.InstanceFPizzerie.panelProductos.upgradeOrderData(Panel.getBotonMesa().getPedidoBoton());
			Panel.getBotonMesa().setBackground(Color.MAGENTA);
			//guarda los datos en el botón del panel de mesas
			((BotonPizzeriaMesas)e.getSource()).setPedidoBoton(Panel.getBotonMesa().getPedidoBoton());	
		}
		reseteaVariablesEstaticas();	// back to bar and tables panel
		Frame.InstanceFPizzerie.ActivaPanel(Frame.InstanceFPizzerie.panelMesas);
		Frame.InstanceFPizzerie.getPanelMesas().setVisible(true);
		Frame.InstanceFPizzerie.getPanelProductos().setVisible(false);		
	}
	
	public void reseteaVariablesEstaticas(){
		Panel.setBotonMesa(null);
		Frame.InstanceFPizzerie.getPanelProductos().IncrementaSutotal(0.0);
		Frame.InstanceFPizzerie.panelProductos.getButtonTique().setVisible(false);
	}
	
	/**
	 * Añade el pedido a la lista y lo marca como pagado
	 * Muestra panelcobro para insertar el dinero entregado y ofrecer el cambio
	 * muestra botón para imprimir el tique
	 */
	public void pagarPedido() {
		//store to pizzeria order list and mark as paid
		Frame.InstanceFPizzerie.getPanelProductos().paidOut(Panel.getBotonMesa().getPedidoBoton());
		Frame.InstanceFPizzerie.myPizzerie.getOrders().add(Panel.getBotonMesa().getPedidoBoton());
		Panel.getBotonMesa().setBackground(Color.LIGHT_GRAY);

		
		
		//crea panel cobro y lo visualiza
		Frame.InstanceFPizzerie.panelCobro = new Panel(Panel.getBotonMesa().getPedidoBoton().getOrderPrice());
		Frame.InstanceFPizzerie.panelticket.setVisible(false);
		Frame.InstanceFPizzerie.add(Frame.InstanceFPizzerie.panelCobro,BorderLayout.CENTER);
		Frame.InstanceFPizzerie.panelCobro.setVisible(true);
		//reset de variables estáticas
		Panel.getBotonMesa().setPedidoBoton(null);//resetea el pedido guardado
		reseteaVariablesEstaticas();
		
	}
	
	public void anyadeProducto(ActionEvent e) {
		if (Panel.getBotonMesa().getPedidoBoton() == null) {//crear pedido y añadir el producto
			Panel.getBotonMesa().setPedidoBoton(new Pedido(Panel.getBotonMesa().getDestinoBoton()));
		}
		// Add product to the order object list
		Panel.getBotonMesa().getPedidoBoton().getOrderFoods().add(((Boton) e.getSource()).getProducto());			
		// incrementa el precio del pedido, lo guarda en el botón y actualiza el label
		Panel.getBotonMesa().getPedidoBoton().setOrderPriceWithoutTaxes(Frame.InstanceFPizzerie.panelProductos.IncrementaSutotal(((Boton) e.getSource()).getProducto().getPrice()));
		// make visible Tique button
		Frame.InstanceFPizzerie.getPanelProductos().getButtonTique().setVisible(true);
		System.out.println("Añade producto al pedido.");
	}
}