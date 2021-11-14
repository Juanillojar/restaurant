package Gui;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Label extends JLabel{
	//Constructor para JLabel de título
	public Label(String Titulo, Font fuente, String alinea) {
		setText(Titulo);
		setFont(fuente);
		switch (alinea) {
		case "LEFT":
			setHorizontalAlignment(SwingConstants.LEFT);
			break;
		case "RIGHT":
			setHorizontalAlignment(SwingConstants.RIGHT);
			break;
		case "CENTER":
			setHorizontalAlignment(SwingConstants.CENTER);
			break;
		default:
		}
	}
		
	public Label(String Titulo, Font fuente) {
		setText(Titulo);
		setFont(fuente);
		setMaximumSize(new Dimension(25,100));
	
	}
}
