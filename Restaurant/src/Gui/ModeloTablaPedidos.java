package Gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ModeloTablaPedidos extends AbstractTableModel {
	private List<Pedido> lista;
	private String[] textosCabecera;
	private Class<?>[] tiposColumnas;

	public ModeloTablaPedidos(List<Pedido> products, String[] cabecera, Class<?>[] tiposColum) {
		// TODO Auto-generated constructor stub
		lista = products;
		textosCabecera = cabecera;
		tiposColumnas = tiposColum;
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return textosCabecera.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		// String [] nombresColumnas = {"id", "Date", "Price", "PriceWhitoutTaxes",
		// "worker", "discount", "Destiny"};

		switch (columnIndex) {
		case 0:
			return lista.get(rowIndex).getOrderId();
		case 1:
			return lista.get(rowIndex).getDate();
		case 2:
			return lista.get(rowIndex).getOrderPrice();
		case 3:
			;
			return lista.get(rowIndex).getOrderPriceWithoutTaxes();
		case 4:
			return lista.get(rowIndex).getTrabajador();
		case 5:
			return lista.get(rowIndex).getvalorDescuento();
		case 6:
			return lista.get(rowIndex).getDestination().getDestinationDenomination();
		default:
			return null;
		}
	}
	
	public String getColumnName(int column) {
		String dato = "";
		switch(column) {
		case 0:
			dato= textosCabecera[0];
			break;
		case 1:
			dato= textosCabecera[1];
			break;
		case 2:
			dato= textosCabecera[2];
			break;
		case 3:
			dato= textosCabecera[3];
			break;
		case 4:
			dato= textosCabecera[4];
			break;
		case 5:
			dato= textosCabecera[5];
			break;
		case 6:
			dato= textosCabecera[6];
			break;
			default:
				return null;
		}
		return dato;
	}
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex) {
		case 0:
			return tiposColumnas[0];
		case 1:
			return tiposColumnas[1];
		case 2:
			return tiposColumnas[2];
		case 3:
			return tiposColumnas[3];
		case 4:
			return tiposColumnas[4];
		case 5:
			return tiposColumnas[5];
		case 6:
			return tiposColumnas[6];
		default:
			return null;
		}
	}

}