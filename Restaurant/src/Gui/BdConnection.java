package Gui;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BdConnection {
	private Connection myConnection;
	private Statement myStatement;
	private PreparedStatement myPreparedStatement;
	private ResultSet myResultset;
	private ResultSetMetaData myResultSetMetaData;
	
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
	 * Return the metadata information of a table in the database
	 * @param table name of the table in database
	 * @return Metadata of the table
	 * @throws SQLException
	 */
	public ResultSetMetaData metadataTable(String table) throws SQLException{
		
		ResultSet rs = myStatement.executeQuery("SELECT * FROM " + table);
		return rs.getMetaData();
	}
	
	/**
	 * @param source object from witch we obtain data
	 * @param columns number or data to get
	 * @return array of objects with data. His size is "columns"
	 * Obtain data from source and store it in a array of objects with size "columns"
	 * We need to specify getters from object source for each database table
	 */
	public Object[] valuesToArray(Object source, int columns) {
		Object[] values = new Object[columns];
		if((source.getClass() == Cocinero.class || source.getClass() == Camarero.class || source.getClass() == Repartidor.class) && columns == 7) {
			values[0] = ((Trabajador)source).getWorkerId();
			values[1] = ((Trabajador)source).getName();
			values[2] = ((Trabajador)source).getSurNames();
			values[3] = ((Trabajador)source).getDni();		
			values[4] = ((Trabajador)source).getSalary();		
			values[5] = ((Trabajador)source).getTelephone();
			values[6] = ((Trabajador)source).getClave();
		}else if(source.getClass() == Cocinero.class) {
			values[0] = ((Cocinero)source).getWorkerId();
			values[1] = ((Cocinero)source).getSpeciality();
			values[2] = ((Cocinero)source).getWorkExperience();
			values[3] = ((Cocinero)source).getCategory().name();			
		}else if(source.getClass() == Camarero.class) {
			values[0] = ((Camarero)source).getWorkerId();
			values[1] = ((Camarero)source).isCocktail();
			int orden = 2; //
			// insert values of languages array
			for(int i=0; i< ((Camarero)source).getLanguages().length; i++) {
				values[orden] = ((Camarero)source).getLanguages()[i];
				orden ++;
			}
		}else if(source.getClass() == Repartidor.class) {
			values[0] = ((Repartidor)source).getWorkerId();
			values[1] = ((Repartidor)source).getDeliveyMode().name();
			values[2] = ((Repartidor)source).getAge();
			values[3] = ((Repartidor)source).isMotorcycleLicense();		
			values[4] = ((Repartidor)source).isOwnVehicle();		
		}
		else if(source.getClass() == Pedido.class) {
			values[0] = ((Pedido)source).getOrderId();
			//Para pasar una java.util.Date a java.sql.Date. Primero expresamos java.util.Date en milisegundos desde 01/01/1970 con getTime.
			//java.sal.Date es una fecha en fomato que JDBC entiende, sólo contiene años meses y días
			values[1] = new java.sql.Date(((Pedido)source).getDate().getTime());
			values[2] = ((Pedido)source).getOrderPrice();
			values[3] = ((Pedido)source).getOrderPriceWithoutTaxes();		
			values[4] = ((Pedido)source).getTrabajador().getWorkerId();
			values[5] = ((Pedido)source).getvalorDescuento();
			values[6] = ((Pedido)source).getDestination().getDestinationId();
			values[7] = ((Pedido)source).isPedidoCobrado();
		}
		return values;
	}	
	
	/**
	 * @param values array of objects that contain values to insert 
	 * @param table no of database table wich insert values
	 * @param rsmd ResultSetMetaData of database table
	 * Generate a UPDATE sql command based on metadata of "table" and
	 * data from object array "values' and insert in "table" of database
	 */
	public void insertDataOnTableBd(Object[] values, String table, ResultSetMetaData rsmd) {
		try {
			StringBuilder sql = new StringBuilder();
			int columns = rsmd.getColumnCount();
			sql.append("INSERT INTO " + table + "(");
			for(int columnIndex=1;columnIndex <= columns;columnIndex++){
				sql.append(rsmd.getColumnLabel(columnIndex));
				if(columnIndex <columns) {
					sql.append(", ");
				}
			}
			sql.append(") ");
			sql.append("VALUES(");
			for(int i =1; i<= columns; i++) {
				sql.append("?");
				if(i< columns) {
					sql.append(",");
				}
			}
			sql.append(")");
			//ejecutar los set de preparedstatement
			myPreparedStatement =  myConnection.prepareStatement(sql.toString());
			for(int columnIndex=0;columnIndex < columns;columnIndex++){
				System.out.println(rsmd.getColumnType(columnIndex+1));
				switch (rsmd.getColumnType(columnIndex+1)){
				case -1: //String mysqlType:252 y sqlType -1
					myPreparedStatement.setString(columnIndex+1,(String)values[columnIndex]);		
					break;
				case 1: //enum mysqlType:254, sqlType 1
					myPreparedStatement.setString(columnIndex+1,(String)values[columnIndex]);		
					break;

				case 4:  //int mysqlType:3 y sqlType 4 FIELD_TYPE_INT
					myPreparedStatement.setInt(columnIndex+1,(int)values[columnIndex]);		
					break;
				case 8:  //Double mysqlType:5 y sqlType 8 FIELD_TYPE_DOUBLE
					myPreparedStatement.setDouble(columnIndex+1,(Double)values[columnIndex]);		
					break;
				case 10, 91: //Date mysqlType:91 y sqlType 10 FIELD TYPE_DATE
					myPreparedStatement.setDate(columnIndex+1, (Date)values[columnIndex]);		
					break;
				case -7: //Boolean mysqlType:1 y sqlType -7 FIELD TYPE_BIT
					myPreparedStatement.setBoolean(columnIndex+1, (Boolean)values[columnIndex]);		
					break;
				}
			}
			System.out.println(myPreparedStatement.toString());
			myPreparedStatement.executeUpdate();			
		}catch (Exception e){
			System.out.println("Worker insertion" + e.getMessage() + e.getStackTrace().toString());
			Frame.log.Escritura("Worker insertion" + e.getMessage() + e.getStackTrace());
		};
	}

	/**
	 * @param lWorkers list of objects to insert (cooker, waiters or delivery men)
	 * Insert workers of the List on database with his details.
	 */
	public void insertWorkersBD(List<?> lWorkers) {
		Object[] valuesWorker;
		try {
			ResultSetMetaData rsmdWorker = metadataTable("worker");  //obtain metadata from table worker
			for(int i =0; i<=lWorkers.size()-1;i++) {
				Trabajador w = (Trabajador)lWorkers.get(i);
				valuesWorker = new Object[rsmdWorker.getColumnCount()];
				valuesWorker = valuesToArray(((Trabajador)w), rsmdWorker.getColumnCount());	// get values of a object ant put on a array
				insertDataOnTableBd(valuesWorker,"worker",rsmdWorker); //insert data into database table
			
				if(w.getClass() == Cocinero.class) {
					myResultSetMetaData = metadataTable("cooker");  //obtain metadata from table cooker
					Object[] valuesWorkerDetail = new Object[myResultSetMetaData.getColumnCount()];
					valuesWorkerDetail = valuesToArray(((Cocinero)w), myResultSetMetaData.getColumnCount()); // get values of a object ant put on a array
					insertDataOnTableBd(valuesWorkerDetail,"cooker",myResultSetMetaData);//insert data into database cooker table
				}else if (w.getClass() == Camarero.class) {
					myResultSetMetaData = metadataTable("waiter");  //obtain metadata from table waiter
					Object[] values = new Object[myResultSetMetaData.getColumnCount()];
					values = valuesToArray(((Camarero)w), myResultSetMetaData.getColumnCount()); // get values of a object ant put on a array
					insertDataOnTableBd(values,"waiter",myResultSetMetaData); //insert data into database waiter table
				}else if (w.getClass() == Repartidor.class) {
					myResultSetMetaData = metadataTable("delivery");  //obtain metadata from table delivery
					Object[] values = new Object[myResultSetMetaData.getColumnCount()];
					values = valuesToArray(((Repartidor)w), myResultSetMetaData.getColumnCount()); // get values of a object ant put on a array
					insertDataOnTableBd(values,"delivery",myResultSetMetaData);  //insert data into database delevery table
				}
			}
		}catch (Exception e){
			System.out.println("Worker insertion" + e.getMessage() + e.getStackTrace().toString());
			Frame.log.Escritura("Worker insertion" + e.getMessage() + e.getStackTrace());
		};
	}
	
	/**
	 * @param myRestaurant generic Restaurant object un variables of configuration
	 * Store data in destinopedido table on database. 
	 * Use variables barZones, inTables, outTables and deleveryZones
	 * This data only store one the first time
	 */
	public void createDestinationsBD(Restaurant myRestaurant) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO destinopedido (destinationId, destinationDenomination, destinationZone) ");
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
			System.out.println("Insert on 'destinopedido' table" + e.getMessage() + e.getStackTrace().toString());
			Frame.log.Escritura("Insert on 'destinopedido' table" +e.getMessage() + e.getStackTrace());
		};
	}
	/**
	 * @param order The order to insert y database
	 * This metod insert orderId and foodId in table orders-producto table
	 * Apply the relationship between an order and all products on it. 
	 * Using a mysql relational database. Insert data on table "orderproducts"
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
	 * @return Resultset that contain data from database
	 * Eject a Select sql in a database and obtain data in a ResultSet
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
}
