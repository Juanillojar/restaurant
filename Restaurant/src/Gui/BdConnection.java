package Gui;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.CommunicationException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class BdConnection {
	private String cadenaConex;
	private boolean timeOut;						//indicates if there are no connection with database motor
	private boolean connected;						//indicates if database connection is ok
	private Connection myConnection;
	private Statement myStatement;
	private PreparedStatement myPreparedStatement;
	private ResultSet myResultset;
	private ResultSetMetaData myResultSetMetaData;
	
	public BdConnection() {
		this.cadenaConex = null;
		this.timeOut= true;
		this.connected = false;
		this.myConnection = null;
		this.myStatement = null;
		this.myPreparedStatement = null;
		this.myResultset = null;
		this.myResultSetMetaData = null;
	}

	/** Constructor for BdConnection. 
	 * checks if there is connection to the database engine and if the database exists
	 * @param config array with configuration extracted from config.xml file
	 */
	public BdConnection(String[] config){
		timeOut=true;
		connected = false;
		
		try {
			if (DatabaseMotorConnection(config)) {
				cadenaConex =  "jdbc:"+config[0]+"://"+config[2]+":"+config[3]+"/"+config[1];	
			    myConnection = DriverManager.getConnection(cadenaConex.toString(), config[4], config[5]);
			    if(myConnection != null) {
			    	System.out.println("Database commection ok.");
			    	Frame.log.Escritura("Database connection ok");
			    	connected = true;
			    }
		    }
		}catch (SQLException e){
			if (e.getErrorCode() == 0) { 
				System.out.println("Database connection time out.");
				Frame.log.Escritura("Database connection time out.");
				JOptionPane.showMessageDialog(null, "Database connection time out."+ e.getMessage(), "Database connection. Error", JOptionPane.ERROR_MESSAGE);
		} 
			else if(e.getErrorCode() == 1049) {
				System.out.println("Conexión ok. Base de datos no existe");
				Frame.log.Escritura("Conexión ok. Base de datos no existe");
			}
			System.out.println(e.getMessage() + e.getStackTrace().toString());
			Frame.log.Escritura(e.getMessage() + e.getStackTrace());
			}
	}
	
	public void closeConnection() {
		try {
			if (!myConnection.isClosed()) {
				myConnection.close();
			}	
		}catch(SQLException e) {
			System.out.println("Closing dabase connmection. " + e.getMessage() + e.getStackTrace().toString());
			Frame.log.Escritura("Closing dabase connmection. " + e.getMessage() + e.getStackTrace());
		}		
	}
	
	/**
	 * check if there is a connection to the database engine
	 * @param config array of String with "config.xml" configuration file parameters
	 * @return boolean thar return true if there is a connection with database engine
	 */
	public boolean DatabaseMotorConnection(String[] config) {
		String url = "jdbc:"+config[0]+"://"+config[2]+":"+config[3]+"/";  // not inserted database name
		try {
			myConnection = DriverManager.getConnection(url, config[4], config[5]);		
			if(myConnection != null) {
				timeOut = false;
				return true;
			}
		}catch(SQLException e){
			System.out.println("Tiempo de conexion agotado");
			Frame.log.Escritura("Tiempo de conexion agotado");
			JOptionPane.showMessageDialog(null, "Connection time out."+ e.getMessage(), "Commecting to database. Error", JOptionPane.ERROR_MESSAGE);
			timeOut =true;
			return false;
		}
		return true;
	}
	
	/**
	 * Create a database in mysql database engine using "config.xml" configuration file parameters
	 * @param config config array of String with "config.xml" configuration file parameters
	 */
	public void createDatabase(String[] config) {
		StringBuilder sql = new StringBuilder("");
		
		cadenaConex =  "jdbc:"+config[0]+"://"+config[2]+":"+config[3]+"/";	// +config[1]		
		Frame.log.Escritura("START: Database creation. ");
		try {
			myConnection = DriverManager.getConnection(cadenaConex, config[4], config[5]);
			myStatement = myConnection.createStatement();
		
			sql.append("CREATE DATABASE IF NOT EXISTS " + config[1] + " DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci");
			System.out.println(sql);
			myStatement.execute(sql.toString());
			Frame.log.Escritura(sql.toString());
		}catch (SQLException e) {
			System.out.println("Database creation. "  + e.getMessage() + e.getStackTrace().toString());
			Frame.log.Escritura("Database creation. " + e.getMessage() + e.getStackTrace());		
		}
		Frame.log.Escritura("END: Database creation. ");
	}
	
	/**
	 * Create the tables structure in database
	 * @param config array of String containing data for database connection
	 */
	public void createTables(String[] config) {
		StringBuilder sql = new StringBuilder("");
		
		cadenaConex =  "jdbc:"+config[0]+"://"+config[2]+":"+config[3]+"/"+config[1];	
		Frame.log.Escritura("START: Tables creation. ");
		try {
			myConnection = DriverManager.getConnection(cadenaConex.toString(), config[4], config[5]);
			myStatement = myConnection.createStatement();
		
			sql.append("CREATE TABLE IF NOT EXISTS `cooker` (`cookerId` int(11) NOT NULL, "
					+ "`speciality` text COLLATE utf8mb4_unicode_ci NOT NULL,"
					+ " `workExperience` int(11) NOT NULL,`kitchenCategory`"
					+ " enum('CHEF','ASSISTANT') COLLATE utf8mb4_unicode_ci NOT NULL,"
					+ "PRIMARY KEY (`cookerId`)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci");
			System.out.println(sql);
			myStatement.execute(sql.toString());
			Frame.log.Escritura(sql.toString());
			sql.delete(0, sql.length());
			sql.append("CREATE TABLE IF NOT EXISTS `delivery` (\r\n"
					+ "  `deliveryId` int(11) NOT NULL,\r\n"
					+ "  `deliveryMode` enum('On_foot','Motorcycle','Bicycle','Electric_car','Combustion_car') COLLATE utf8mb4_unicode_ci NOT NULL,\r\n"
					+ "  `age` int(11) NOT NULL,\r\n"
					+ "  `motorcycleLicense` tinyint(1) NOT NULL,\r\n"
					+ "  `ownVehicle` tinyint(1) NOT NULL,\r\n"
					+ "  PRIMARY KEY (`deliveryId`)\r\n"
					+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci");
			
			System.out.println(sql);
			myStatement.execute(sql.toString());
			Frame.log.Escritura(sql.toString());
			sql.delete(0, sql.length());
			sql.append("CREATE TABLE IF NOT EXISTS `destinopedido` ("
					+ "  `destinationId` int(11) NOT NULL,"
					+ "  `destinationDenomination` text COLLATE utf8mb4_unicode_ci NOT NULL,"
					+ "  `destinationZone` enum('Bar','IntTable','ExtTable','Delivery') COLLATE utf8mb4_unicode_ci NOT NULL,"
					+ "  PRIMARY KEY (`destinationId`)"
					+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci");
			System.out.println(sql);
			myStatement.execute(sql.toString());
			Frame.log.Escritura(sql.toString());
			sql.delete(0, sql.length());
			sql.append("CREATE TABLE IF NOT EXISTS `orders` ("
					+ "  `orderId` int(11) NOT NULL,"
					+ "  `date` date NOT NULL,"
					+ "  `orderPrice` double NOT NULL,"
					+ "  `OrderPriceWithoutTaxes` double NOT NULL,"
					+ "  `workerId` int(11) NOT NULL,"
					+ "  `valorDescuento` double NOT NULL,"
					+ "  `destination` int(11) NOT NULL,"
					+ "  `pedidoCobrado` tinyint(1) NOT NULL,"
					+ "  PRIMARY KEY (`orderId`),"
					+ "  KEY `destination` (`destination`),"
					+ "  KEY `workerId` (`workerId`) USING BTREE"
					+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci");
			System.out.println(sql);
			myStatement.execute(sql.toString());
			Frame.log.Escritura(sql.toString());
			sql.delete(0, sql.length());
			sql.append("CREATE TABLE IF NOT EXISTS `ordersproducts` ("
					+ "  `orderId` int(11) NOT NULL,"
					+ "  `foodId` int(11) NOT NULL,"
					+ "  KEY `orderId` (`orderId`),"
					+ "  KEY `foodId` (`foodId`)"
					+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci");
			System.out.println(sql);
			myStatement.execute(sql.toString());
			Frame.log.Escritura(sql.toString());
			sql.delete(0, sql.length());
			sql.append("CREATE TABLE IF NOT EXISTS `productos` ("
					+ "  `foodId` int(11) NOT NULL,"
					+ "  `denomination` text COLLATE utf8mb4_unicode_ci NOT NULL,"
					+ "  `section` enum('PIZZAS','STARTERS','PASTAS','COMBINADOS','SALADS','DESSERTS','DRINKS','BREAD','OTHERS') COLLATE utf8mb4_unicode_ci NOT NULL,"
					+ "  `ingredients` text COLLATE utf8mb4_unicode_ci NOT NULL,"
					+ "  `price` double NOT NULL,"
					+ "  `lowprice` tinyint(1) NOT NULL,"
					+ "  `image` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,"
					+ "  PRIMARY KEY (`foodId`)"
					+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci");
			System.out.println(sql);
			myStatement.execute(sql.toString());
			Frame.log.Escritura(sql.toString());
			sql.delete(0, sql.length());
			sql.append("CREATE TABLE IF NOT EXISTS `waiter` ("
					+ "  `waiterId` int(11) NOT NULL,"
					+ "  `cocktail` tinyint(1) NOT NULL,"
					+ "  `languaje1` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,"
					+ "  `languaje2` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,"
					+ "  `languaje3` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,"
					+ "  PRIMARY KEY (`waiterId`)"
					+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci");
			System.out.println(sql);
			myStatement.execute(sql.toString());
			Frame.log.Escritura(sql.toString());
			sql.delete(0, sql.length());
			sql.append("CREATE TABLE IF NOT EXISTS `worker` ("
					+ "  `workerId` int(11) NOT NULL,"
					+ "  `name` text COLLATE utf8mb4_unicode_ci NOT NULL,"
					+ "  `surNames` text COLLATE utf8mb4_unicode_ci NOT NULL,"
					+ "  `dni` text COLLATE utf8mb4_unicode_ci NOT NULL,"
					+ "  `salary` double NOT NULL,"
					+ "  `shift` enum('Morning','Afternoon','Evening') COLLATE utf8mb4_unicode_ci NOT NULL,"
					+ "  `telephone` text COLLATE utf8mb4_unicode_ci NOT NULL,"
					+ "  `clave` text COLLATE utf8mb4_unicode_ci NOT NULL,"
					+ "  PRIMARY KEY (`workerId`),"
					+ "  KEY `workerId` (`workerId`)"
					+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci");
			System.out.println(sql);
			myStatement.execute(sql.toString());
			Frame.log.Escritura(sql.toString());
			sql.delete(0, sql.length());
			sql.append("ALTER TABLE `cooker`"
					+ "  ADD CONSTRAINT `cooker_ibfk_1` FOREIGN KEY (`cookerId`) REFERENCES `worker` (`workerId`) ON DELETE CASCADE ON UPDATE CASCADE");
			System.out.println(sql);
			myStatement.execute(sql.toString());
			Frame.log.Escritura(sql.toString());
			sql.delete(0, sql.length());
			sql.append("ALTER TABLE `delivery`"
					+ "  ADD CONSTRAINT `delivery_ibfk_1` FOREIGN KEY (`deliveryId`) REFERENCES `worker` (`workerId`) ON DELETE CASCADE ON UPDATE CASCADE");
			System.out.println(sql);
			myStatement.execute(sql.toString());
			Frame.log.Escritura(sql.toString());
			sql.delete(0, sql.length());
			sql.append("ALTER TABLE `orders`"
					+ "  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`workerId`) REFERENCES `worker` (`workerId`) ON DELETE CASCADE ON UPDATE CASCADE,"
					+ "  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`destination`) REFERENCES `destinopedido` (`destinationId`) ON DELETE CASCADE ON UPDATE CASCADE");
			System.out.println(sql);
			myStatement.execute(sql.toString());
			Frame.log.Escritura(sql.toString());
			sql.delete(0, sql.length());
			sql.append("ALTER TABLE `ordersproducts`"
					+ "  ADD CONSTRAINT `ordersproducts_ibfk_1` FOREIGN KEY (`foodId`) REFERENCES `productos` (`foodId`) ON DELETE CASCADE ON UPDATE CASCADE,"
					+ "  ADD CONSTRAINT `ordersproducts_ibfk_2` FOREIGN KEY (`orderId`) REFERENCES `orders` (`orderId`) ON DELETE CASCADE ON UPDATE CASCADE");
			System.out.println(sql);
			myStatement.execute(sql.toString());
			Frame.log.Escritura(sql.toString());
			sql.delete(0, sql.length());
			sql.append("ALTER TABLE `waiter`"
					+ "  ADD CONSTRAINT `waiter_ibfk_1` FOREIGN KEY (`waiterId`) REFERENCES `worker` (`workerId`) ON DELETE CASCADE ON UPDATE CASCADE");
			System.out.println(sql);
			myStatement.execute(sql.toString());
			Frame.log.Escritura(sql.toString());
		}catch (SQLException e) {
			System.out.println("Database tables creation. "  + e.getMessage() + e.getStackTrace().toString());
			Frame.log.Escritura("Database tables creation. " + e.getMessage() + e.getStackTrace());
		}
		Frame.log.Escritura("END: Tables creation. ");
	}
	
	public void showProductsList() throws SQLException{
		myResultset = myStatement.executeQuery("SELECT * FROM productos");
		while(myResultset.next()) {
			System.out.println(myResultset.getString("denomination"));		
		}
	}
	
	/**
	 * Charge data from Database. The number of orders...
	 */
	public void chargeBDData(Restaurant myRestaurant) {
		try {
			myStatement = myConnection.createStatement();
			ResultSet rst = myStatement.executeQuery("SELECT MAX(orderId) FROM orders");
			rst.next();
			System.out.println("Recogido valor de número de pedidos en BD: "+ rst.getInt("MAX(orderId)"));
			Pedido.setPedidos(rst.getInt("MAX(orderId)"));
			rst =null;
			//copy users from database to List
			

			rst = myStatement.executeQuery("SELECT MAX(workerId) FROM worker");
			rst.next();
			System.out.println("Recogido valor de número de trabajadores en BD: "+ rst.getInt("MAX(workerId)"));
			Trabajador.setWorkers(rst.getInt("MAX(workerId)"));
			loadWorkersFromBD(myRestaurant.getWorkers(),"worker");
				//	closeConnection();
		}catch(SQLException e){
			System.out.println("Lectura de datos BD " + e.getMessage() + e.getStackTrace().toString());
			Frame.log.Escritura("Lectura de datos BD " + e.getMessage() + e.getStackTrace());
			//closeConnection();
		};
	}
	
	/**
	 * Inserts data of a list of "Productos" in a database table.
	 * @param table table name to insert data
	 * @param lProductos list of "Productod" objects to insert
	 */
/*	public void insertarEnTablaProductosBD(String table, List<Productos> lProductos) {
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
				myPreparedStatement.executeUpdate();
			}
		}catch (Exception e){
			System.out.println("Insercion tabla products" + e.getMessage() + e.getStackTrace().toString());
			Frame.log.Escritura("Insercion tabla products" + e.getMessage() + e.getStackTrace());
		};
	}
	*/
	
	/**
	 * Return the metadata information of a table in the database
	 * @param table name of the table in database
	 * @return Metadata of the table
	 * @throws SQLException
	 */
	public ResultSetMetaData metadataTable(String table) throws SQLException{	
		myStatement = myConnection.createStatement();
		ResultSet rs = myStatement.executeQuery("SELECT * FROM " + table);
		return rs.getMetaData();
	}
	
	/**
	 * Obtain data from source and store it in a array of objects with size "columns"
	 * We need to specify getters from object source for each database table
	 * @param source object from witch we obtain data
	 * @param columns number or data to get
	 * @return array of objects with data. His size is "columns"
	 */
	public Object[] valuesToArray(Object source, int columns) {
		Object[] values = new Object[columns];
		if((source.getClass() == Cocinero.class || source.getClass() == Camarero.class || source.getClass() == Repartidor.class) && columns == 8) {
			values[0] = ((Trabajador)source).getWorkerId();
			values[1] = ((Trabajador)source).getName();
			values[2] = ((Trabajador)source).getSurNames();
			values[3] = ((Trabajador)source).getDni();		
			values[4] = ((Trabajador)source).getSalary();	
			values[5] = ((Trabajador)source).getShift().name();
			values[6] = ((Trabajador)source).getTelephone();
			values[7] = ((Trabajador)source).getClave();
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
		else if(source.getClass() == Productos.class) {
			values[0] = ((Productos)source).getFoodId();
			values[1] = ((Productos)source).getDenomination();
			values[2] = ((Productos)source).getSection().name();
			values[3] = ((Productos)source).getIngredients();		
			values[4] = ((Productos)source).getPrice();
			values[5] = ((Productos)source).isLowPrice();
		}
		return values;
	}	
	
	/**
	 * Generate a INSERT sql command based on metadata of "table" and
	 * data from object array "values' and insert in "table" of database
	 * @param values array of objects that contain values to insert 
	 * @param table no of database table wich insert values
	 * @param rsmd ResultSetMetaData of database table
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
			System.out.println("Insertion in database" + e.getMessage() + e.getStackTrace().toString());
			Frame.log.Escritura("Insertion in database" + e.getMessage() + e.getStackTrace());
		};
	}

	/**
	 * Insert workers of the List on database with his details.
	 * @param lWorkers list of objects to insert (cooker, waiters or delivery men)
	 */
	public void insertWorkersBD(List<?> lWorkers, String table) {
		for(int i =0; i<=lWorkers.size()-1;i++) {
			Trabajador w = (Trabajador)lWorkers.get(i);
			insertWorkerBD(w, table);
		}
	}
	
	/**
	 * Insert a worker on database with his details. Camarero, Cocinero and Repartidor classes are subclasses of Trabajador class
	 * @param w the Trabajador object to insert in database
	 */
	public void insertWorkerBD(Trabajador w, String table){		//(Trabajador w) {
		Object[] valuesWorker;
		try {
			ResultSetMetaData rsmdWorker = metadataTable(table);  //obtain metadata from table worker
				valuesWorker = new Object[rsmdWorker.getColumnCount()];
				valuesWorker = valuesToArray(((Trabajador)w), rsmdWorker.getColumnCount());	// get values of a object ant put on a array
				insertDataOnTableBd(valuesWorker,table,rsmdWorker); //insert data into database table
			
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
		}catch (Exception e){
			System.out.println("Worker insertion in database" + e.getMessage() + e.getStackTrace().toString());
			Frame.log.Escritura("Worker insertion in database" + e.getMessage() + e.getStackTrace());
		};
	}
	
	/**
	 * insert values of a Productos object into a databae
	 * @param product the Productos object to insert
	 */
	public void insertProductBD(Productos product, String table){
		Object[] valuesProduct;
		
		ResultSetMetaData rsmdProduct;
		try {
			rsmdProduct = metadataTable(table); //obtain metadata from table 
			valuesProduct = new Object[rsmdProduct.getColumnCount()];
			valuesProduct = valuesToArray(product, rsmdProduct.getColumnCount());	// get values of a object ant put on a array
			insertDataOnTableBd(valuesProduct,table,rsmdProduct); //insert data into database table
		} catch (SQLException e) {
			System.out.println("Product insertion in database" + e.getMessage() + e.getStackTrace().toString());
			Frame.log.Escritura("Product insertion in database" + e.getMessage() + e.getStackTrace());
		}
	}

	/**
	 * Insert products of the List on database with his details.
	 * @param lProducts list of objects to insert 
	 */
	public void insertProductsBD(List<?> lProducts, String table) {
		for(int i =0; i<=lProducts.size()-1;i++) {
			Productos w = (Productos)lProducts.get(i);
			insertProductBD(w, table);
		}
	}

	/**
	 * insert values of a object into a databae
	 * @param product the object to insert
	 */
/*	public void insertdataInBD(T product , String table){
		Object[] valuesProduct;
		
		ResultSetMetaData rsmdProduct;
		try {
			rsmdProduct = metadataTable(table); //obtain metadata from table 
			valuesProduct = new Object[rsmdProduct.getColumnCount()];
			valuesProduct = valuesToArray(product, rsmdProduct.getColumnCount());	// get values of a object ant put on a array
			insertDataOnTableBd(valuesProduct,table,rsmdProduct); //insert data into database table
		} catch (SQLException e) {
			System.out.println("Product insertion in database" + e.getMessage() + e.getStackTrace().toString());
			Frame.log.Escritura("Product insertion in database" + e.getMessage() + e.getStackTrace());
		}
	}
	*/
	/**
	 * Store data in 'destinopedido' table on database. 
	 * Use variables barZones, inTables, outTables and deleveryZones
	 * This data only store one the first time
	 * @param myRestaurant generic Restaurant object. Use barZones, deliveryZones, inTables and outTables variables
	 */
	public void createDestinationsBD(int barZones, int intTables, int outTables, int deliveryZones) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO destinopedido (destinationId, destinationDenomination, destinationZone) ");
		sql.append("VALUES(?,?,?)");
		int contador = 1;
		try {
			for(int i = 1; i<= barZones;i++) {
				myPreparedStatement = myConnection.prepareStatement(sql.toString());
				myPreparedStatement.setInt(1, contador);
				myPreparedStatement.setString(2, "Zona barra " + i);
				myPreparedStatement.setString(3, Zone.Bar.name());
				myPreparedStatement.executeLargeUpdate();
				contador ++;
			}
			for(int i = 1; i<= intTables;i++) {
				myPreparedStatement = myConnection.prepareStatement(sql.toString());
				myPreparedStatement.setInt(1, contador);
				myPreparedStatement.setString(2, "In table " + i);
				myPreparedStatement.setString(3, Zone.IntTable.name());
				myPreparedStatement.executeLargeUpdate();
				contador ++;
			}
			for(int i = 1; i<= outTables;i++) {
				myPreparedStatement = myConnection.prepareStatement(sql.toString());
				myPreparedStatement.setInt(1, contador);
				myPreparedStatement.setString(2, "Out Table " + i);
				myPreparedStatement.setString(3, Zone.ExtTable.name());
				myPreparedStatement.executeLargeUpdate();
				contador ++;
			}
			for(int i = 1; i<= deliveryZones;i++) {
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
	 * This method insert orderId and foodId in table orders-producto table
	 * Apply the relationship between an order and all products on it. 
	 * Using a mysql relational database. Insert data on table "orderproducts"
	 * @param order The order to insert y database
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
			System.out.println("Table orders insertion " + sql + e.getMessage() + e.getStackTrace().toString());
			Frame.log.Escritura("Table orders insertion " + e.getMessage() + e.getStackTrace());
		};	
	}
	
	public void loadWorkersFromBD(List<Trabajador> workers, String table){
		Trabajador worker;
		String sql = "SELECT * FROM " + table + ", waiter WHERE worker.workerId = waiter.waiterId";
		ResultSet rst = colletSQl(sql);
		ResultSetMetaData metaData;
		try {
			metaData = rst.getMetaData();
 			while(rst.next()) {			
				String[] languages = {rst.getString(11),rst.getString(12),rst.getString(13)};
				worker = new Camarero(
						rst.getString(2),
						rst.getString(3),
						rst.getString(4), 
						(Double)rst.getObject(5),
						Turno.Afternoon,
						rst.getString(7),
						rst.getString(8),
						languages,
						(Boolean)rst.getObject(10));
				
				switch(rst.getString(6)){
				case "Afternoon":
					worker.setShift(Turno.Afternoon);
					break;
				case "Evening":
					worker.setShift(Turno.Evening);
					break;
				case "Morning":
					worker.setShift(Turno.Morning);
					break;
				}				
				workers.add(worker);
			}
		} catch (SQLException e) {
			System.out.println("Load from database " + sql + e.getMessage() + e.getStackTrace().toString());
			Frame.log.Escritura("Load from database " + e.getMessage() + e.getStackTrace());

		}
		
		
		// load cookers
		Cocinero cooker;
		sql = "SELECT * FROM " + table + ", cooker WHERE worker.workerId = cooker.cookerId";
		rst = colletSQl(sql);
		try {
			metaData = rst.getMetaData();
 			while(rst.next()) {			
				cooker = new Cocinero(
						rst.getString(2),
						rst.getString(3),
						rst.getString(4), 
						(Double)rst.getObject(5),
						Turno.Afternoon,
						rst.getString(7),
						rst.getString(8),
						rst.getString(10),
						rst.getInt(11),
						kitchenCategory.ASSISTANT);
				
				switch(rst.getString(6)){
				case "Afternoon":
					cooker.setShift(Turno.Afternoon);
					break;
				case "Evening":
					cooker.setShift(Turno.Evening);
					break;
				case "Morning":
					cooker.setShift(Turno.Morning);
					break;
				}				
				switch(rst.getString(12)){
				case "CHEF":
					cooker.setCategory(kitchenCategory.CHEF);
					break;
				case "ASSISTANT":
					cooker.setCategory(kitchenCategory.ASSISTANT);
					break;
				}				
				workers.add(cooker);
			}
		} catch (SQLException e) {
			System.out.println("Load from database " + sql + e.getMessage() + e.getStackTrace().toString());
			Frame.log.Escritura("Load from database " + e.getMessage() + e.getStackTrace());

		}

		
		//load deliveries
		
		// load cookers
		Repartidor delivery;
		sql = "SELECT * FROM " + table + ", delivery  WHERE worker.workerId = delivery.deliveryId";
		rst = colletSQl(sql);
		try {
			metaData = rst.getMetaData();
 			while(rst.next()) {			
				delivery =  new Repartidor(
						rst.getString(2),
						rst.getString(3),
						rst.getString(4), 
						(Double)rst.getObject(5),
						Turno.Afternoon,
						rst.getString(7),
						rst.getString(8),
						Transport.Bicycle,
						rst.getInt(11),
						(Boolean)rst.getObject(12),
						(Boolean)rst.getObject(13));
				
				switch(rst.getString(6)){
				case "Afternoon":
					delivery.setShift(Turno.Afternoon);
					break;
				case "Evening":
					delivery.setShift(Turno.Evening);
					break;
				case "Morning":
					delivery.setShift(Turno.Morning);
					break;
				}				
				switch(rst.getString(10)){
				case "On_foot":
					delivery.setDeliveyMode(Transport.On_foot);
					break;
				case "Motoycycle":
					delivery.setDeliveyMode(Transport.Motorcycle);
					break;
				case "Bicycle":
					delivery.setDeliveyMode(Transport.Bicycle);
					break;
				case "Electric_car":
					delivery.setDeliveyMode(Transport.Electric_car);
					break;
				case "Combustion_car":
					delivery.setDeliveyMode(Transport.Combustion_car);
					break;

				}
				workers.add(delivery);
			}
		} catch (SQLException e) {
			System.out.println("Load from database " + sql + e.getMessage() + e.getStackTrace().toString());
			Frame.log.Escritura("Load from database " + e.getMessage() + e.getStackTrace());
		}

	}
	
	/**
	 * Eject a Select sql in a database and obtain data in a ResultSet
	 * @param sql sql command to eject in database
	 * @return Resultset that contain data from database
	 */
	public ResultSet colletSQl(String sql) {		
		try {
			myStatement = myConnection.createStatement();		    
		    myResultset = myStatement.executeQuery(sql);
		}catch (Exception e){
			System.out.println("Select " + e.getMessage() + e.getStackTrace().toString());
			Frame.log.Escritura("Select " + e.getMessage() + e.getStackTrace());
		};
		return myResultset;
	}

	// setters and getters
	public boolean isTimeOut() {
		return timeOut;
	}

	public boolean isConnected() {
		return connected;
	}

	public void setTimeOut(boolean timeOut) {
		this.timeOut = timeOut;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}
	
}
