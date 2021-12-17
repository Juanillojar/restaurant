package Gui;
/*
Versión: 1.3.0
Fecha creación:	03/08/2021. Última modificación: 17/08/2021 
Esta clase contiene el método main de una aplicación que intenta gestionar un pizzeria
Se insertan los datos creando los objetos y listas en tiempo de ejecución y no se guardan en disco
Se visualizan los datos y se crea un pedido utilizando los datos insertados
 */

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

import com.mysql.cj.xdevapi.CreateIndexParams;

import static Gui.DataEncryption.*;

public class Test {
	static SecretKeySpec key;
	public static BdConnection conex;
	public static String[] arrayConfig;
	
	public static void main(String[] args) {
		arrayConfig = new String[14];
		//Carga datos de configuración
		XmlDoc myXmlDoc = new XmlDoc("config.xml", "src/Gui/");
		arrayConfig = myXmlDoc.read();
		myXmlDoc = null;  			//liberar memoria
		//creación estructuras de datos para trabajar en memoria
		List<Productos> productsRestaurant = new ArrayList<Productos>();
		List<Trabajador> workers= new ArrayList<Trabajador>();
		List<Pedido> ordersRestaurant = new ArrayList<Pedido>();
		
		//crea el objeto conexión a base de datos
		conex = new BdConnection(arrayConfig);
		if(!conex.isTimeOut()) {		//connected to database motor
			if(!conex.isConnected())    //no connected to database
			{  // no conneted to database
				//if(conex.isConnectedBdMotorButBbNoExist()) 
			//	{  // conected to database motor but database no exist
					if (JOptionPane.showConfirmDialog(null, "Database not found. Create database?", "Database not found", JOptionPane.YES_NO_CANCEL_OPTION) == 0) 
					{//create database and tables	
						
						conex.createDatabase(arrayConfig);
						conex.createTables(arrayConfig);
						conex.setConnected(true);
						JOptionPane.showMessageDialog(null, "Database created successfully", "Information" , JOptionPane.INFORMATION_MESSAGE);
						if(JOptionPane.showConfirmDialog(null, "Do you want to insert exmample data?", "Question", JOptionPane.OK_CANCEL_OPTION) == 0) 
						{//insert example data in database
							//insert data into memory structures
							Frame.log.Escritura("START: Insertion example data in database. ");							
							createProducts(productsRestaurant);
							createWorkers(workers);
							createPedidos(ordersRestaurant, productsRestaurant, workers);
							conex.insertarEnTablaProductosBD("productos", productsRestaurant);
							//insert worker list in database
							conex.insertWorkersBD(workers);
							//insertar los destinos en la base de datos
							conex.createDestinationsBD(Integer.parseInt(arrayConfig[6]),Integer.parseInt(arrayConfig[7]), Integer.parseInt(arrayConfig[8]), Integer.parseInt(arrayConfig[9]));
						}
					}
			//	}
			}else{  //conected to database
				// INCLUIR LA CARGA DE LOS DATOS DE TRABAJADORES PARA PONER EN EL PANEL VALIDA
				
				
				
				
				
				
				
				
				
				
				conex.chargeBDData();	//Carga datos de base de datos
				}	
		}else {  	// no connection with database motor
			if (JOptionPane.showConfirmDialog(null, "Do you want to charge example data?", "No conecction to database motor", JOptionPane.YES_NO_CANCEL_OPTION) == 0) 
			{//insert data into memory structures
				createProducts(productsRestaurant);
				createWorkers(workers);
				createPedidos(ordersRestaurant, productsRestaurant, workers);
			}
		}
		//Creación de un objeto Restaurant
		
		Restaurant myRestaurant= new Restaurant(arrayConfig[10], arrayConfig[11], Integer.parseInt(arrayConfig[12]), Integer.parseInt(arrayConfig[13]), workers, productsRestaurant, ordersRestaurant);
		//Se asigna el número de puestos en barra, mesas en interior, en exterior y repartos extraidas del archivo de configuración
		myRestaurant.setBarZones(Integer.parseInt(arrayConfig[6]));
		myRestaurant.setInTables(Integer.parseInt(arrayConfig[7]));
		myRestaurant.setOutTables(Integer.parseInt(arrayConfig[8]));
		myRestaurant.setDeliveryZones(Integer.parseInt(arrayConfig[9]));
				
		//Se lanza frame
		Frame miFrame = new Frame(myRestaurant);
				
	
		System.out.println("\n***************Listado de comida en pizzeria********************");
		System.out.println(productsRestaurant);
		System.out.println("\n***************Listado de trabajadores en pizzeria**************");
		//myRestaurant.setOrders(pedidosPizzeria);
		//pizzeria.VisualizarListaTrabajadores(trabajadoresPizzeria);
		
		//Generación de listado de pedidos
		System.out.println("\n*************** Listado de pedidos en pizzeria *****************");
		System.out.println(myRestaurant.getOrders().toString());	
	}
	
	/**
	 * create products example data in List
	 * @param lista List Productos objects
	 */
	public static void createProducts(List<Productos> lista){
	//Se crean los productos (objetos Comida), se crea la lista y se rellena 
		Productos plato1 = new Productos("Patatas Fritas",Section.STARTERS,"Patata, aceite, sal", 3.00, false);
		Productos plato2 = new Productos("Pollo milanesa",Section.STARTERS,"Pollo, tomate, cebolla, pan rallado, Huevo, pimienta",10.50,false);
		Productos plato3 = new Productos("Aros de cebolla",Section.STARTERS,"Cebolla, aceite, pan rallado",5.50,false);
		Productos plato4 = new Productos("Musaka",Section.STARTERS,"Berengena, queso, tomate, lecha, harina, sal",11.50,false);
		Productos plato5 = new Productos("Tabla patés",Section.STARTERS,"Pate de higado, paté de atún",7.20, true);
		Productos plato6 = new Productos("Macarrones boloñesa", Section.PASTAS, "Pasta, Tomate, Cebolla, champiñones",7.5, false);
		Productos plato7 = new Productos("Pizza Caprichosa", Section.PIZZAS, "Harina, aceite, agua, tomate, Queso, Cebolla, champiñones",10, true);
		Productos plato8 = new Productos("Pizza Margarita", Section.PIZZAS, "Harina, aceite, agua, tomate, Queso" ,9.80, false);
		Productos plato9 = new Productos("Pizza Pepperoni", Section.PIZZAS, "Tomate, Queso, pepperoni, salchicas",8.60, false);
		Productos plato10 = new Productos("Ensalada Tropical", Section.SALADS, "Lechuga, tomate, gauda, piña, manzana, kiwi, salsa rosa",9.20, false);
		Productos plato11 = new Productos("Ensalada Nostra", Section.SALADS, "Lechuga, tomate, zanahoria, atún, maiz, espárragos",8.00, false);
		Productos plato12 = new Productos("Helado", Section.DESSERTS, "Leche, azucar, Fruta según sabor",2.95, false);
		Productos plato13= new Productos("Coolant", Section.DESSERTS, "Leche, harina, chocolate, azucar",3.55, false);
		Productos plato14 = new Productos("Pan rústico", Section.BREAD, "harina, sal, agua",0.90, false);
		Productos plato15 = new Productos("Pan centeno", Section.BREAD, "Centeno, sal, agua",0.90, false);
		Productos plato16 = new Productos("Pan con pasas", Section.BREAD, "Harina, sal, agua, uva",1.20, false);
		Productos plato17 = new Productos("Lomo y Huevos", Section.COMBINADOS, "Cerdo, huevo, patata, aceite, sal",9.90, false);
		Productos plato18 = new Productos("Cazón y Huevos", Section.COMBINADOS, "Cazón, huevo, patata, aceite, sal",9.90, false);
		Productos plato19 = new Productos("Salchichas y pimientos", Section.COMBINADOS, "Cerdo, pimiento, patata, aceite, sal",5.90, false);
		Productos plato20 = new Productos("Hamburguesa casa", Section.OTHERS, "Cerdo, huevo, patata, acerite, sal",9.90, false);
		Productos plato21 = new Productos("Ketchup", Section.OTHERS, "Tomate, azucar, sal...",0.20, false);
		Productos plato22 = new Productos("Mahonesa", Section.OTHERS, "Huevo, aceite, sal",0.20, false);
		Productos plato23 = new Productos("Cerveza",Section.DRINKS,"Cebada, Alcohol",1.5,false);
		Productos plato24 = new Productos("Cerveza sin",Section.DRINKS,"Cebada",1.5,false);
		Productos plato25 = new Productos("Agua grande",Section.DRINKS,"Agua",2.5,false);
		Productos plato26 = new Productos("Agua pequeña",Section.DRINKS,"Agua",1.5,false);
		
		lista.add(plato1);
		lista.add(plato2);
		lista.add(plato3);
		lista.add(plato4);
		lista.add(plato5);
		lista.add(plato6);
		lista.add(plato7);
		lista.add(plato8);
		lista.add(plato9);
		lista.add(plato10);
		lista.add(plato11);
		lista.add(plato12);
		lista.add(plato13);
		lista.add(plato14);
		lista.add(plato15);
		lista.add(plato16);
		lista.add(plato17);
		lista.add(plato18);
		lista.add(plato19);
		lista.add(plato20);
		lista.add(plato21);
		lista.add(plato22);
		lista.add(plato23);
		lista.add(plato24);
		lista.add(plato25);
		lista.add(plato26);
	}
	
	
	/**
	 * Create worker example data
	 * @param workers List of Trabajador objects
	 */
	public static void createWorkers(List<Trabajador> workers) {
		//Generación de claves encriptadas para los usuarios
		byte[] salt = new String("Juanillo").getBytes();  //es como una llave para la encriptación.Clave pública?
		int iteractionCount=40000;						  //iteraciones del algoritmo de emcriptación	
		int KeyLength = 128;  							  //la longitud de la clave?
		String passwordKey = "Restaurante";
		String passwordcocinero1= "coc1";
		String passwordEncriptadacocinero1="";
		String passwordcocinero2= "coc2";
		String passwordEncriptadacocinero2="";
		String passwordCamarero1= "cam1";
		String passwordEncriptadaCamarero1="";
		String passwordCamarero2= "cam2";
		String passwordEncriptadaCamarero2="";
		String passwordRepartidor1= "rep1";
		String passwordEncriptadaRepartidor1="";
		String passwordRepartidor2= "rep2";
		String passwordEncriptadaRepartidor2="";
		try {
			key = createSecretKey(passwordKey.toCharArray(),salt, iteractionCount, KeyLength);
			passwordEncriptadacocinero1 = encrypt(passwordcocinero1, key);
			passwordEncriptadacocinero2 = encrypt(passwordcocinero2, key);
			passwordEncriptadaCamarero1 = encrypt(passwordCamarero1, key);
			passwordEncriptadaCamarero2 = encrypt(passwordCamarero2, key);
			passwordEncriptadaRepartidor1 = encrypt(passwordRepartidor1, key);
			passwordEncriptadaRepartidor2 = encrypt(passwordRepartidor2, key);

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Creación de los arrays con los idiomas que conoce cada camarero, los camareros, repartidores y cocineros
		String[] language1 = {"Español","inglés"};
		String[] language2 = {"Español","inglés, Francés"};
		String[] language3 = {"Español","inglés, Alemán"};
		Cocinero cocinero1 = new Cocinero("Francisco", "Soler Villegas","14253678A", 1200.50, Turno.TARDE, "624582159", passwordEncriptadacocinero1, "Desserts", 3, kitchenCategory.ASSISTANT);
		Cocinero cocinero2 = new Cocinero("Elena", "García Moro","85854545C", 1400.50, Turno.NOCHE, "9834235485", passwordEncriptadacocinero2, "Vanguardia", 5, kitchenCategory.CHEF);
		Camarero camarero1 = new Camarero("Vanesa", "Martin Fierro","85412365B", 1050.50, Turno.NOCHE,"523568974", passwordEncriptadaCamarero1, language1, false);
		Camarero camarero2 = new Camarero("Jonas", "Valverde Schultz","44128369R", 1050.50,Turno.TARDE,"685214792", passwordEncriptadaCamarero2, language2, true);
		Repartidor repartidor1 = new Repartidor("Juan", "Pérez Morales","78451236C", 800.00,Turno.TARDE,"568471236", passwordEncriptadaRepartidor1, Transport.Motorcycle, 19, true, true);
		Repartidor repartidor2 = new Repartidor("Manu", "González Gazquez","45826573J", 900.00,Turno.NOCHE,"631657157", passwordEncriptadaRepartidor2, Transport.Bicycle, 18, false, false);

		//Creación de lista de Trabajadores y se añaden los cocineros, camareros y repartidores

		workers.add(0, cocinero1);
		workers.add(1, cocinero2);
		workers.add(2, camarero1);
		workers.add(3, camarero2);
		workers.add(4, repartidor1);
		workers.add(5, repartidor2);
	}
	
	public static void createPedidos(List<Pedido> orders,List<Productos> productsList, List<Trabajador> workersList ) {
		//Creación de la lista de comidas para pedido1 y el objeto pedido1 y la lista de objetos pedido.
		List<Productos> comidaPedido1 = new ArrayList<Productos>();
		comidaPedido1.add(0, productsList.get(1)); //0 es la posición en la lista y 1 el id de "Productos"
		comidaPedido1.add(1, productsList.get(15)); 
		DestinoPedido destinoPedido1= new DestinoPedido("Bar1",Zone.Bar);
		Pedido pedido1 = new Pedido(comidaPedido1, 32.0, workersList.get(3), destinoPedido1);
		orders.add(pedido1);
	}
	public static SecretKeySpec getKey() {
		return key;
	}

	public static void setKey(SecretKeySpec key) {
		Test.key = key;
	}

}
