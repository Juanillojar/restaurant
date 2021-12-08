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
import static Gui.DataEncryption.*;

public class Test {
	static SecretKeySpec key;
	public static BdConnection conex = new BdConnection();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Se crean los productos (objetos Comida), se crea la comidaPizzeria y se rellena 
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
		
		List<Productos> comidaPizzeria = new ArrayList<Productos>();
		comidaPizzeria.add(plato1);
		comidaPizzeria.add(plato2);
		comidaPizzeria.add(plato3);
		comidaPizzeria.add(plato4);
		comidaPizzeria.add(plato5);
		comidaPizzeria.add(plato6);
		comidaPizzeria.add(plato7);
		comidaPizzeria.add(plato8);
		comidaPizzeria.add(plato9);
		comidaPizzeria.add(plato10);
		comidaPizzeria.add(plato11);
		comidaPizzeria.add(plato12);
		comidaPizzeria.add(plato13);
		comidaPizzeria.add(plato14);
		comidaPizzeria.add(plato15);
		comidaPizzeria.add(plato16);
		comidaPizzeria.add(plato17);
		comidaPizzeria.add(plato18);
		comidaPizzeria.add(plato19);
		comidaPizzeria.add(plato20);
		comidaPizzeria.add(plato21);
		comidaPizzeria.add(plato22);
		comidaPizzeria.add(plato23);
		comidaPizzeria.add(plato24);
		comidaPizzeria.add(plato25);
		comidaPizzeria.add(plato26);
		/**
		 * Sólo usado una vez para insertar el contenido de la lista en la base de datos	
		 *	try {
			conex.insertarEnTablaProductosBD("productos", comidaPizzeria);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
 
		 */
		 
	
		
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
		List<Trabajador> trabajadoresPizzeria= new ArrayList<Trabajador>();
		trabajadoresPizzeria.add(0, cocinero1);
		trabajadoresPizzeria.add(1, cocinero2);
		trabajadoresPizzeria.add(2, camarero1);
		trabajadoresPizzeria.add(3, camarero2);
		trabajadoresPizzeria.add(4, repartidor1);
		trabajadoresPizzeria.add(5, repartidor2);
		conex.insertWorkersBD(trabajadoresPizzeria);
		//Creación de la lista de comidas para pedido1 y el objeto pedido1 y la lista de objetos pedido.
		List<Productos> comidaPedido1 = new ArrayList<Productos>();
		comidaPedido1.add(0, plato3); //0 es la posición en la lista y 1 el id de la "Comida"
		comidaPedido1.add(1, plato5); //1 es la posición en la lista y 2 el id de la "Comida"
		//Creación de un objeto pizzeria asociándole todos los datos creados antes
		Restaurant pizzeria = new Restaurant("Pizzeria Bartolini", "c/Levantina 2 Bajo C.P:04240 Viator(Almeria)", trabajadoresPizzeria, comidaPizzeria,null);
		//Se asigna el número de puestos en barra, mesas en interior, en exterior y repartos
		pizzeria.setBarZones(4);
		pizzeria.setInTables(3);
		pizzeria.setOutTables(2);
		pizzeria.setDeliveryZones(2);
		//insertar los destinos en la base de datos
		//conex.insertDestinationBD(pizzeria);
		//Pedido pedido1 = new Pedido(comidaPedido1, 32, camarero1, destinoPedido1);
		//conex.insertOrderTableBD(pedido1);
		//Creación de la lista de pedidos e inserción del pedido1
		List<Pedido> pedidosPizzeria= new ArrayList<Pedido>();
		pizzeria.setOrders(pedidosPizzeria);
		//pedidosPizzeria.add(0, pedido1);

		System.out.println("\n***************Listado de comida en pizzeria********************");
		System.out.println(comidaPizzeria);
		System.out.println("\n***************Listado de trabajadores en pizzeria**************");
		//pizzeria.setOrders(pedidosPizzeria);
		//pizzeria.VisualizarListaTrabajadores(trabajadoresPizzeria);
		
		//Carga datos de base de datos
			conex.chargeBDData();
	
		//Se lanza frame
		Frame miFrame = new Frame(pizzeria);

		//Generación de listado de pedidos
		System.out.println("\n*************** Listado de pedidos en pizzeria *****************");
		System.out.println(pizzeria.getOrders().toString());	
		
		
	}

	public static SecretKeySpec getKey() {
		return key;
	}

	public static void setKey(SecretKeySpec key) {
		Test.key = key;
	}

}
