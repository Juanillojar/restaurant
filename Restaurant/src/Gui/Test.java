package Gui;
/*
Versi�n: 1.3.0
Fecha creaci�n:	03/08/2021. �ltima modificaci�n: 17/08/2021 
Esta clase contiene el m�todo main de una aplicaci�n que intenta gestionar un pizzeria
Se insertan los datos creando los objetos y listas en tiempo de ejecuci�n y no se guardan en disco
Se visualizan los datos y se crea un pedido utilizando los datos insertados
 */

import java.util.ArrayList;
import java.util.List;

public class Test {
	public static String path= "E:\\Contenido USB Diciembre 2019\\personal\\cursos\\Curso Java 2021\\PracticasJava\\src\\Pizzeria\\pizzeria.log";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FicheroLog log = new FicheroLog(path);
		//Se crean los productos (objetos Comida), se crea la comidaPizzeria y se rellena 
		Productos plato1 = new Productos("Patatas Fritas",Section.STARTERS,"Patata, aceite, sal", 3.00, false);
		Productos plato2 = new Productos("Pollo milanesa",Section.STARTERS,"Pollo, tomate, cebolla, pan rallado, Huevo, pimienta",10.50,false);
		Productos plato3 = new Productos("Aros de cebolla",Section.STARTERS,"Cebolla, aceite, pan rallado",5.50,false);
		Productos plato4 = new Productos("Musaka",Section.STARTERS,"Berengena, queso, tomate, lecha, harina, sal",11.50,false);
		Productos plato5 = new Productos("Tabla pat�s",Section.STARTERS,"Pate de higado, pat� de at�n",7.20, true);
		Productos plato6 = new Productos("Macarrones bolo�esa", Section.PASTAS, "Pasta, Tomate, Cebolla, champi�ones",7.5, false);
		Productos plato7 = new Productos("Pizza Caprichosa", Section.PIZZAS, "Harina, aceite, aguan, tomate, Queso, Cebolla, champi�ones",10, true);
		Productos plato8 = new Productos("Pizza Margarita", Section.PIZZAS, "Harina, aceite, agua, tomate, Queso" ,9.80, false);
		Productos plato9 = new Productos("Pizza Pepperoni", Section.PIZZAS, "Tomate, Queso, pepperoni, salchicas",8.60, false);
		Productos plato10 = new Productos("Ensalada Tropical", Section.SALADS, "Lechuga, tomate, gauda, pi�a, manzana, kiwi, salsa rosa",9.20, false);
		Productos plato11 = new Productos("Ensalada Nostra", Section.SALADS, "Lechuga, tomate, zanahoria, at�n, maiz, esp�rragos",8.00, false);
		Productos plato12 = new Productos("Helado", Section.DESSERTS, "Leche, azucar, Fruta seg�n sabor",2.95, false);
		Productos plato13= new Productos("Coolant", Section.DESSERTS, "Leche, harina, chocolate, azucar",3.55, false);
		Productos plato14 = new Productos("Pan r�stico", Section.BREAD, "harina, sal, agua",0.90, false);
		Productos plato15 = new Productos("Pan centeno", Section.BREAD, "Centeno, sal, agua",0.90, false);
		Productos plato16 = new Productos("Pan con pasas", Section.BREAD, "Harina, sal, agua, uva",1.20, false);
		Productos plato17 = new Productos("Lomo y Huevos", Section.COMBINADOS, "Cerdo, huevo, patata, aceite, sal",9.90, false);
		Productos plato18 = new Productos("Caz�n y Huevos", Section.COMBINADOS, "Caz�n, huevo, patata, aceite, sal",9.90, false);
		Productos plato19 = new Productos("Salchichas y pimientos", Section.COMBINADOS, "Cerdo, pimiento, patata, aceite, sal",5.90, false);
		Productos plato20 = new Productos("Hamburguesa casa", Section.OTHERS, "Cerdo, huevo, patata, acerite, sal",9.90, false);
		Productos plato21 = new Productos("Ketchup", Section.OTHERS, "Tomate, azucar, sal...",0.20, false);
		Productos plato22 = new Productos("Mahonesa", Section.OTHERS, "Huevo, aceite, sal",0.20, false);
		Productos plato23 = new Productos("Cerveza",Section.DRINKS,"Cebada, Alcohol",1.5,false);
		Productos plato24 = new Productos("Cerveza sin",Section.DRINKS,"Cebada",1.5,false);
		Productos plato25 = new Productos("Agua grande",Section.DRINKS,"Agua",2.5,false);
		Productos plato26 = new Productos("Agua peque�a",Section.DRINKS,"Agua",1.5,false);
		
		
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
		
		//Creaci�n de los arrays con los idiomas que conoce cada camarero, los camareros, repartidores y cocineros
		String[] language1 = {"Espa�ol","ingl�s"};
		String[] language2 = {"Espa�ol","ingl�s, Franc�s"};
		String[] language3 = {"Espa�ol","ingl�s, Alem�n"};
		Cocinero cocinero1 = new Cocinero("Francisco", "Soler Villegas","14253678A", 1200.50, Turno.TARDE.name(), "624582159", "coci1", "Pizzas", 2, false);
		Cocinero cocinero2 = new Cocinero("Elena", "Garc�a Moro","85854545C", 1400.50, Turno.NOCHE.name(), "9834235485", "coci2", "Vanguardia", 5, true);
		Camarero camarero1 = new Camarero("Vanesa", "Martin Fierro","85412365B", 1050.50, Turno.NOCHE.name(),"523568974", "cam1","Barra", language1, false);
		Camarero camarero2 = new Camarero("Jonas", "Valverde Schultz","44128369R", 1050.50,Turno.TARDE.name(),"685214792", "cam2","Terraza", language2, true);
		Repartidor repartidor1 = new Repartidor("Juan", "P�rez Morales","78451236C", 800.00,Turno.TARDE.name(),"568471236", "rep1", Transport.Moto.toString(), 19, true, true);
		Repartidor repartidor2 = new Repartidor("Manu", "Gonz�lez Gazquez","45826573J", 900.00,Turno.NOCHE.name(),"631657157", "rep2", Transport.Bicicleta.toString(), 18, false, false);
		//Creaci�n de lista de Trabajadores y se a�aden los cocineros, camareros y repartidores
		List<Trabajador> trabajadoresPizzeria= new ArrayList<Trabajador>();
		trabajadoresPizzeria.add(0, cocinero1);
		trabajadoresPizzeria.add(1, cocinero2);
		trabajadoresPizzeria.add(2, camarero1);
		trabajadoresPizzeria.add(3, camarero2);
		trabajadoresPizzeria.add(4, repartidor1);
		trabajadoresPizzeria.add(5, repartidor2);
		
		//Creaci�n de la lista de comidas para pedido1 y el objeto pedido1 y la lista de objetos pedido.
		List<Productos> comidaPedido1 = new ArrayList<Productos>();
		comidaPedido1.add(0, plato3); //0 es la posici�n en la lista y 1 el id de la "Comida"
		comidaPedido1.add(1, plato5); //1 es la posici�n en la lista y 2 el id de la "Comida"
		DestinoPedido destinoPedido1 = new DestinoPedido("Bar 1",Zone.Bar);
		Pedido pedido1 = new Pedido(comidaPedido1, 32, camarero1, destinoPedido1);
		//Creaci�n de la lista de pedidos e inserci�n del pedido1
		List<Pedido> pedidosPizzeria= new ArrayList<Pedido>();
		pedidosPizzeria.add(0, pedido1);
		//Creaci�n de un objeto pizzeria asoci�ndole todos los datos creados antes
		Restaurant pizzeria = new Restaurant("Pizzeria Bartolini", "c/Levantina 2 Bajo C.P:04240 Viator(Almeria)", trabajadoresPizzeria, comidaPizzeria,pedidosPizzeria);
		//pedidosPizzeria.indexOf(pedido1);
		//Se asigna el n�mero de puestos en barra, mesas en interior y exterior
		pizzeria.setBarZones(4);
		pizzeria.setInTables(3);
		pizzeria.setOutTables(2);
		System.out.println("\n***************Listado de comida en pizzeria********************");
		System.out.println(comidaPizzeria);
		System.out.println("\n***************Listado de trabajadores en pizzeria**************");
		pizzeria.setOrders(pedidosPizzeria);
		pizzeria.VisualizarListaTrabajadores(trabajadoresPizzeria);
		
		pizzeria.VisualizarListaComidas(comidaPedido1, "Postre");
		//Se lanza frame
		Frame miFrame = new Frame(pizzeria);

		
		//Generaci�n de  un pedido con los datos que inserta el operador y se presenta el ticket
	//	if (pizzeria.seleccionaTurno() !="" ) {
	//		pizzeria.generarPedido(comidaPizzeria, log);
	//	}else System.out.println("Fuera de horario de generaci�n pedidos.");

		//Generaci�n de listado de pedidos
		System.out.println("\n*************** Listado de pedidos en pizzeria *****************");
		System.out.println(pizzeria.getOrders().toString());	
	
		
		
	}
	
}