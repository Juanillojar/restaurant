package Gui;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


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
			// Inserción de datos en tabla productos especificando los datos 
			//String sql = "INSERT INTO productos (foodId,denomination, section, ingredients, price, lowprice, image)"
			//		+ "values (2,'Patatas fritas','STARTERS','Patata, sal, aceite...',3.00, false, null)";
			
			// inserción de datos en tabla productos insertando los datos como datos de preparedStatement
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
	
		
		}catch (Exception e){
			System.out.println(e.getMessage() + e.getStackTrace().toString());
			Frame.log.Escritura(e.getMessage() + e.getStackTrace());
		};
	}
	
	/**
	 * Charge data from Database. The number of orders...
	 * @throws SQLException 
	 */
	public void chargeBDData() {
		try {
			Statement stm = myConnection.createStatement();
			ResultSet rst = stm.executeQuery("SELECT MAX(orderId) FROM orders");
			rst.next();
			System.out.println("Recogido valor de número de pedidos en BD: "+ rst.getInt("MAX(orderId)"));
			Pedido.setPedidos(rst.getInt(1));			
		}catch(Exception e){
			System.out.println("Lectura de datos BD " + e.getMessage() + e.getStackTrace().toString());
			Frame.log.Escritura("Lectura de datos BD " + e.getMessage() + e.getStackTrace());
		};

	}
	
	/**
	 * @param table nombre de la tabla en la que se insertan
	 * @param lProductos lista de productos a insertar
	 * Se insertan los productos de la lista en la tabla. Usado 
	 */
	public void insertarEnTablaProductosBD(String table, List<Productos> lProductos) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO " + table + " (foodId,denomination, section, ingredients, price, lowprice, image) ");
		sql.append("VALUES(?,?,?,?,?,?,?)");

		try {
			for(Productos p:lProductos) {
				myPreparedStatement = myConnection.prepareStatement(sql.toString());
				myPreparedStatement.setInt(1, p.getFoodId());
				myPreparedStatement.setString(2, p.getDenomination());
				myPreparedStatement.setString(3, p.getSection().toString());
				myPreparedStatement.setString(4, p.getIngredients());
				myPreparedStatement.setDouble(5, p.getPrice());
				myPreparedStatement.setBoolean(6, p.isLowPrice());
				myPreparedStatement.setString(7, null);
				myPreparedStatement.executeLargeUpdate();
			}
		}catch (Exception e){
			System.out.println("Insercion tabla products" + e.getMessage() + e.getStackTrace().toString());
			Frame.log.Escritura("Insercion tabla products" + e.getMessage() + e.getStackTrace());
		};
	}
	/**
	 * @param lWorkers lista de trabajadores a insertar
	 * Se insertan los trabajadores de la lista en la tabla "worker".
	 */
	public void insertWorkersBD(List<Trabajador> lWorkers) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO worker (workerId, name, surNames, dni, salary, telephone, clave) ");
		sql.append("VALUES(?,?,?,?,?,?,?)");
		try {
			for(Trabajador w:lWorkers) {
				myPreparedStatement = myConnection.prepareStatement(sql.toString());
				myPreparedStatement.setInt(1, w.getWorkerId());
				myPreparedStatement.setString(2, w.getName());
				myPreparedStatement.setString(3, w.getSurNames());
				myPreparedStatement.setString(4, w.getDni());
				myPreparedStatement.setDouble(5, w.getSalary());
				myPreparedStatement.setString(6, w.getTelephone());
				myPreparedStatement.setString(7, w.getClave());
				myPreparedStatement.executeLargeUpdate();
			}
		}catch (Exception e){
			System.out.println("Insercion tabla worker" + e.getMessage() + e.getStackTrace().toString());
			Frame.log.Escritura("Insercion tabla worker" + e.getMessage() + e.getStackTrace());
		};
	}
	
	public void insertDestinationBD(Restaurant myRestaurant) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO destinoPedido (destinationId, destinationDenomination, destinationZone) ");
		sql.append("VALUES(?,?,?)");
		int contador = 1;
		try {
			for(int i = 1; i<= myRestaurant.getBarZones();i++) {
				myPreparedStatement = myConnection.prepareStatement(sql.toString());
				myPreparedStatement.setInt(1, contador);
				myPreparedStatement.setString(2, "Zona barra " + i);
				myPreparedStatement.setString(3, Zone.Bar.name());
				myPreparedStatement.executeLargeUpdate();
				contador ++;
			}
			for(int i = 1; i<= myRestaurant.getInTables();i++) {
				myPreparedStatement = myConnection.prepareStatement(sql.toString());
				myPreparedStatement.setInt(1, contador);
				myPreparedStatement.setString(2, "In table " + i);
				myPreparedStatement.setString(3, Zone.IntTable.name());
				myPreparedStatement.executeLargeUpdate();
				contador ++;
			}
			for(int i = 1; i<= myRestaurant.getOutTables();i++) {
				myPreparedStatement = myConnection.prepareStatement(sql.toString());
				myPreparedStatement.setInt(1, contador);
				myPreparedStatement.setString(2, "Out Table " + i);
				myPreparedStatement.setString(3, Zone.ExtTable.name());
				myPreparedStatement.executeLargeUpdate();
				contador ++;
			}
			for(int i = 1; i<= myRestaurant.getDeliveryZones();i++) {
				myPreparedStatement = myConnection.prepareStatement(sql.toString());
				myPreparedStatement.setInt(1, contador);
				myPreparedStatement.setString(2, "Delivery " + i);
				myPreparedStatement.setString(3, Zone.Delivery.name());
				myPreparedStatement.executeLargeUpdate();
				contador ++;
			}

		}catch (Exception e){
			System.out.println("Insercion tabla Destino pedido" + e.getMessage() + e.getStackTrace().toString());
			Frame.log.Escritura("Insercion tabla Destino pedido" +e.getMessage() + e.getStackTrace());
		};
	}

	/**
	 * @param order The order to insert y database
	 * This metod insert a order in the database table "orders"
	 */
	public void insertOrderTableBD(Pedido order) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO Orders (OrderId, date, orderPrice, OrderPriceWithoutTaxes, workerId, valorDescuento, destination, PedidoCobrado) ");
		sql.append("VALUES(?,?,?,?,?,?,?,?)");
		try {
				myPreparedStatement = myConnection.prepareStatement(sql.toString());
				myPreparedStatement.setInt(1, order.getOrderId());
				//Para pasar una java.util.Date a java.sql.Date. Primero expresamos java.util.Date en milisegundos desde 01/01/1970 con getTime.
				//java.sal.Date es una fecha en fomato que JDBC entiende, sólo contiene años meses y días
				myPreparedStatement.setDate(2, new java.sql.Date(order.getDate().getTime()));
				myPreparedStatement.setDouble(3, order.getOrderPrice());
				myPreparedStatement.setDouble(4, order.getOrderPriceWithoutTaxes());
				myPreparedStatement.setInt(5, order.getTrabajador().getWorkerId());
				myPreparedStatement.setDouble(6, order.getvalorDescuento());
				myPreparedStatement.setInt(7, order.getDestination().getDestinationId());
				myPreparedStatement.setBoolean(8, order.isPedidoCobrado());
				System.out.println(myPreparedStatement.toString());
				myPreparedStatement.executeLargeUpdate();

		}catch (Exception e){
			System.out.println("Insercion tabla pedido" + e.getMessage() + e.getStackTrace().toString());
			Frame.log.Escritura("Insercion tabla pedido" + e.getMessage() + e.getStackTrace());
		};
	}
	
	/**
	 * @param order The order to insert y database
	 * This metod insert orderId and foodId in table orders-producto table
	 * Apply the relationship between an order and all products on it. 
	 * Using a mysql relational database 
	 */
	public void insertTableordersProductsBD(Pedido order) {
		PreparedStatement myPreparedStatement;
		StringBuilder sql = new StringBuilder();  
		sql.append("INSERT INTO `ordersproducts`(`orderId`, `foodId`) VALUES (?,?)");
		
		try {
			for(Productos product: order.getOrderFoods()) {
				myPreparedStatement = myConnection.prepareStatement(sql.toString());
				myPreparedStatement.setInt(1, order.getOrderId());
				myPreparedStatement.setInt(2, product.getFoodId());
				System.out.println(myPreparedStatement.toString());
				myPreparedStatement.executeLargeUpdate();				
			}
		}catch (Exception e){
			System.out.println("Insercion tabla pedido" + sql + e.getMessage() + e.getStackTrace().toString());
			Frame.log.Escritura("Insercion tabla pedido" + e.getMessage() + e.getStackTrace());
		};
	}
	
	/**
	 * @param sql sql command to eject in database
	 * @return Resultset that contain data from de database
	 */
	public ResultSet colletSQl(String sql) {		
		try {
			myStatement = myConnection.createStatement();		    
		    myResultset = myStatement.executeQuery(sql);
			return myResultset;
		}catch (Exception e){
			System.out.println("Select " + e.getMessage() + e.getStackTrace().toString());
			Frame.log.Escritura("Select " + e.getMessage() + e.getStackTrace());
		};
		return myResultset;
	}
	/**
	 * @param table nombre de la tabla en la que insertar los datos
	 * @param columns array con los nombres de la columnas
	 * @param values array con los valores convertidos a String
	 * @para tipos array con los tipos de las columnas
	 * Insercion de datos en una tabla genérica. Pretende insertar datos en cualquier tabla de la base de datos pasando los parámetros.
	 * NO FUNCIONA CORRECTAMENTE
	 */
	public void insertarEnTablaBD(String table, String[] columns, String[] values, Class[] tipos) {
		table= "productos";
		//String[] columns= {"foodId", "denomination", "section", "ingredients", "price", "lowprice", "image"};
		//String[] values= {"5","Musaka", "Section.STARTERS.name()", "Berengena, queso, tomate, lecha, harina, sal", "11.5d","0", null};
		//String[] tipos= {"int", "String", "String", "String", "double", "int","String"};
		//Class [] tipos = {java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class,java.lang.Boolean.class, java.lang.String.class};

		//String[] columns= {"foodId", "denomination"};
		//String[] values= {"5","Musaka"};
		//String[] tipos= {"int", "String"};

		
		String sql ="";
		sql="INSER INTO" + table + " (";
		for(int i =0; i<= columns.length-1; i++) {
			sql = sql + columns[i];
			if(i< columns.length-1) {
				sql = sql + ",";
			}
		}
		sql =sql + ") VALUES(";
		for(int i =0; i<= columns.length-1; i++) {
			sql = sql + "?";
			if(i< columns.length-1) {
				sql = sql + ",";
			}
		}
		sql = sql + ")";
		System.out.println(sql);
		try {
			myPreparedStatement = myConnection.prepareStatement(sql);
			for(int i =0; i<= tipos.length-1; i++) {
				switch (tipos[i].toString()){
				case "java.lang.String.class":
					myPreparedStatement.setString(i,values[i]);		
					break;
				case "java.lang.Integer.class":
					myPreparedStatement.setInt(i,Integer.parseInt(values[i]));		
					break;
				case "java.lang.Double.class":
					myPreparedStatement.setDouble(i,Double.parseDouble(values[i]));		
					break;
				case "java.lang.Date.class":
					myPreparedStatement.setDate(i, Date.valueOf(values[i]));		
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
