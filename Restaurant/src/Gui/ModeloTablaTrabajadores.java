package Gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ModeloTablaTrabajadores extends AbstractTableModel {
	private List<Trabajador> listaTrabajadores;
	private String[] textosCabecera;
	private Class<?>[] tiposColumnas;

	public ModeloTablaTrabajadores(List<Trabajador> workers, String[] cabecera, Class<?>[] tiposColum){
		listaTrabajadores = workers;
		textosCabecera = cabecera;
		tiposColumnas = tiposColum;
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listaTrabajadores.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return textosCabecera.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		switch (columnIndex) {
		case 0:
			return listaTrabajadores.get(rowIndex).getWorkerId();
		case 1:
			return listaTrabajadores.get(rowIndex).getName();
		case 2:
			return listaTrabajadores.get(rowIndex).getSurNames();
		case 3:
			return listaTrabajadores.get(rowIndex).getTelephone();
		case 4:
			return listaTrabajadores.get(rowIndex).getDni();
		case 5:
			return listaTrabajadores.get(rowIndex).getSalary();
		case 6:
			return listaTrabajadores.get(rowIndex).getClave();
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

