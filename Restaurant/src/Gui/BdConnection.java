package Gui;

import java.sql.*;

public class BdConnection {
	private Connection myConnection;
	private Statement myStatement;
	private PreparedStatement myPreparedStatement;
	private ResultSet myResultset;
	
	public BdConnection(){
		try {
			myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdrestaurant", "root", "");		
		    myStatement = myConnection.createStatement();
			myResultset = myStatement.executeQuery("SELECT * FROM productos");
			while(myResultset.next()) {
				System.out.println(myResultset.getString("denomination"));
				
			}
			//String sql = "INSERT INTO productos (foodId,denomination, section, ingredients, price, lowprice, image)"
			//		+ "values (2,'Patatas fritas','STARTERS','Patata, sal, aceite...',3.00, false, null)";
		/*	String sql = "INSERT INTO productos(foodId, denomination, section, ingredients, price, lowprice, image) values(?,?,?,?,?,?,?)";
			myPreparedStatement = myConnection.prepareStatement(sql);
			myPreparedStatement.setInt(1, 4);
			myPreparedStatement.setString(2, "Aros de cebolla");
			myPreparedStatement.setString(3, Section.STARTERS.name());
			myPreparedStatement.setString(4, "Cebolla, aceite, pan rallado");
			myPreparedStatement.setDouble(5, 5.50d);
			myPreparedStatement.setBoolean(6, false);
			myPreparedStatement.setString(7, null);
			System.out.println(sql);
			myPreparedStatement.executeUpdate();
			*/
			String table= "productos";
			//String[] columns= {"foodId", "denomination", "section", "ingredients", "price", "lowprice", "image"};
			//String[] values= {"5","Musaka", "Section.STARTERS.name()", "Berengena, queso, tomate, lecha, harina, sal", "11.5d","0", null};
			//String[] tipos= {"int", "String", "String", "String", "double", "int","String"};
			String[] columns= {"foodId", "denomination"};
			String[] values= {"5","Musaka"};
			String[] tipos= {"int", "String"};

			String sql ="";
			sql="INSER INTO " + table + " (";
			for(int i =0; i<= columns.length-1; i++) {
				sql = sql + columns[i];
				if(i< columns.length-1) {
					sql = sql + ",";
				}
			}
			sql =sql + ") VALUES(";
			for(int i =0; i<= values.length-1; i++) {
				sql = sql + "?";
				if(i< values.length-1) {
					sql = sql + ",";
				}
			}
			sql = sql + ")";
			System.out.println(sql);
			
				myPreparedStatement = myConnection.prepareStatement(sql);
				for(int i =0; i<= tipos.length-1; i++) {
					switch (tipos[i]){
					case "String":
						String cadena = values[i];
						myPreparedStatement.setString(i,cadena);		
						break;
					case "int":
						int entero = Integer.parseInt(values[i]);
						myPreparedStatement.setInt(i,entero);		
						break;
					case "double":
						myPreparedStatement.setDouble(i,Double.parseDouble(values[i]));		
						break;
					case "date":
						myPreparedStatement.setDate(i,Date.valueOf(values[i]));		
						break;
					}
					
				}
				myPreparedStatement.executeUpdate();
		}catch (Exception e){
			System.out.println(e.getMessage() + e.getStackTrace().toString());
			Frame.log.Escritura(e.getMessage() + e.getStackTrace());
		};
	}
	
	public void insertar(String table, String[] columns, String[] values, String[] tipos) {
		String sql ="";
		sql="INSER INTO" + table + " (";
		for(int i =0; i<= columns.length-1; i++) {
			sql = sql + columns[i];
			if(i< columns.length-1) {
				sql = sql + ",";
			}
		}
		sql =sql + ") VALUES(";
		for(int i =0; i<= values.length-1; i++) {
			sql = sql + "?";
			if(i< values.length-1) {
				sql = sql + ",";
			}
		}
		sql = sql + ")";
		System.out.println(sql);
		try {
			myPreparedStatement = myConnection.prepareStatement(sql);
			for(int i =0; i<= tipos.length-1; i++) {
				switch (tipos[i]){
				case "String":
					myPreparedStatement.setString(i,values[i]);		
					break;
				case "int":
					myPreparedStatement.setInt(i,Integer.parseInt(values[i]));		
					break;
				case "double":
					myPreparedStatement.setDouble(i,Double.parseDouble(values[i]));		
					break;
				case "date":
					myPreparedStatement.setDate(i,Date.valueOf(values[i]));		
					break;
				}
				
			}
			myPreparedStatement.executeUpdate();
		}catch (Exception e){
			System.out.println(e.getMessage() + e.getStackTrace().toString());
			Frame.log.Escritura(e.getMessage() + e.getStackTrace());
		}
		
		

		
				
				
		sql = sql + ";";
		

	}

}
