package Gui;



import java.util.List;
import javax.swing.table.AbstractTableModel;


public class ModeloTablaProductos extends AbstractTableModel {
	private List<Productos> listaProductos;
	private String[] textosCabecera;
	private Class<?>[] tiposColumnas;
	
	public ModeloTablaProductos(List<Productos> products, String[] cabecera, Class<?>[] tiposColum) {
		// TODO Auto-generated constructor stub
		listaProductos = products;
		textosCabecera = cabecera;
		tiposColumnas = tiposColum;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listaProductos.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return textosCabecera.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		switch(columnIndex) {
		case 0:
			return listaProductos.get(rowIndex).getFoodId();
		case 1:
			return listaProductos.get(rowIndex).getDenomination();
		case 2:
			return listaProductos.get(rowIndex).getSection();
		case 3:;
			return listaProductos.get(rowIndex).getIngredients();
		case 4:
			return listaProductos.get(rowIndex).getPrice()+ " €";
		case 5:
			return listaProductos.get(rowIndex).isLowPrice();
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
			default:
				return null;
		}
	}

}