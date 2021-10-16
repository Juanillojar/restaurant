package Gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;

public class FicheroLog {
	public static String path = "";
	public static File fichero;

	public FicheroLog(String path) {
		FicheroLog.path = path;
		Path p1 = Paths.get(path);
		if (Files.notExists(p1)) {
			FileSystem fs = FileSystems.getDefault(); // obtiene el sistema de arhivos predeterminado (windows) y
														// métodos para construir otros
			try {
				fichero = new File(path);
			} catch (Exception ex) {
				System.out.println("Problemas al crear el fichero log" + ex);
			}
		}else fichero = new File(path);
	}

	public void Lectura() {
		
		try {
			FileReader ficheroR = new FileReader(fichero);
			int caracter = ficheroR.read();
			while (caracter != -1) {
				System.out.print((char) caracter);
				caracter = ficheroR.read();
			}
			ficheroR.close();
		}catch (FileNotFoundException ex) {
			System.out.println("No se encuentra el archivo " + ex);
		}catch (IOException ex) {
			System.out.println("No se puede leer el archivo " + ex);
		}finally {
			if (fichero != null) {
			//	ficheroR.close();
			}
		}
	}
	
	public void Escritura(String cadena) {
		char[] arrayChar;
		Date date = new Date();
		DateFormat formatoFechaHora = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
		cadena = "\n " + formatoFechaHora.format(date.getTime()) + cadena;
		try {
			FileWriter ficheroW = new FileWriter(fichero,true);
		
			arrayChar = cadena.toCharArray();
			for (int index =0; index < arrayChar.length; index++) {
				ficheroW.append(arrayChar[index]);
				
			}
			ficheroW.close();
		}catch (FileNotFoundException ex) {
			System.out.println("No se encuentra el archivo " + ex);
		}catch (IOException ex) {
			System.out.println(ex.getMessage());
		}finally {
			if (fichero != null) {
				//ficheroW.close();
			}
		}
		

	}
	// System.out.println(myPath);
	// System.out.format("getFileName: %s%n", p1.getFileName());
	// System.out.format("getParent: %s%n", p1.getParent());
	// System.out.format("getNameCount: %s%n", p1.getNameCount());
	// System.out.format("getRoot: %s%n", p1.getRoot());

}
