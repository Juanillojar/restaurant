package Gui;
//Se refiere al destino del pedido. 
public class DestinoPedido {
	private static int destinos =0;    //número de destinos. Se considera un destino cada una una de las mesas, zonas de barra y si es reparto
	private int destinationId;			//identifica la mesa
	private String destinationDenomination;
	private Zone destinationZone;
	
	
	public DestinoPedido(String destinationDenomination, Zone destinationZone) {
		destinos++;
		destinationId = destinos;
		this.destinationDenomination = destinationDenomination;
		this.destinationZone = destinationZone;
	}
	
	public DestinoPedido() {
		destinos++;
		destinationId = destinos;
		this.destinationDenomination = "";
		this.destinationZone = null;
	}

	public String getDestinationDenomination() {
		return destinationDenomination;
	}

	public Zone getDestinationZone() {
		return destinationZone;
	}

	public void setDestinationDenomination(String destinationDenomination) {
		this.destinationDenomination = destinationDenomination;
	}

	public void setDestinationZone(Zone destinationZone) {
		this.destinationZone = destinationZone;
	}

	
	
}