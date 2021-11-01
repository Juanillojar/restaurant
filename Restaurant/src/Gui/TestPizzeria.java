package Gui;
/*
Versión: 1.3.0
Fecha creación:	03/08/2021. Última modificación: 17/08/2021 
Esta clase contiene el método main de una aplicación que intenta gestionar un pizzeria
Se insertan los datos creando los objetos y listas en tiempo de ejecución y no se guardan en disco
Se visualizan los datos y se crea un pedido utilizando los datos insertados
 */

import java.util.ArrayList;
import java.util.List;

public class TestPizzeria {
	public static String path= "E:\\Contenido USB Diciembre 2019\\personal\\cursos\\Curso Java 2021\\PracticasJava\\src\\Pizzeria\\pizzeria.log";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FicheroLog log = new FicheroLog(path);
		//Se crean los productos (objetos Comida), se crea la comidaPizzeria y se rellena 
		ComidaPizzeria plato1 = new ComidaPizzeria("Patatas Fritas",Section.STARTERS.name(),"Patata, aceite, sal", 3.00, false);
		ComidaPizzeria plato2 = new ComidaPizzeria("Pollo milanesa",Section.STARTERS.name(),"Pollo, tomate, cebolla, pan rallado, Huevo, pimienta",10.50,false);
		ComidaPizzeria plato3 = new ComidaPizzeria("Aros de cebolla",Section.STARTERS.name(),"Cebolla, aceite, pan rallado",5.50,false);
		ComidaPizzeria plato4 = new ComidaPizzeria("Musaka",Section.STARTERS.name(),"Berengena, queso, tomate, lecha, harina, sal",11.50,false);
		ComidaPizzeria plato5 = new ComidaPizzeria("Tabla patés",Section.STARTERS.name(),"Pate de higado, paté de atún",7.20, true);
		ComidaPizzeria plato6 = new ComidaPizzeria("Macarrones boloñesa", Section.PASTAS.name(), "Pasta, Tomate, Cebolla, champiñones",7.5, false);
		ComidaPizzeria plato7 = new ComidaPizzeria("Pizza Caprichosa", Section.PIZZAS.name(), "Harina, aceite, aguan, tomate, Queso, Cebolla, champiñones",10, true);
		ComidaPizzeria plato8 = new ComidaPizzeria("Pizza Margarita", Section.PIZZAS.name(), "Harina, aceite, agua, tomate, Queso" ,9.80, false);
		ComidaPizzeria plato9 = new ComidaPizzeria("Pizza Pepperoni", Section.PIZZAS.name(), "Tomate, Queso, pepperoni, salchicas",8.60, false);
		ComidaPizzeria plato10 = new ComidaPizzeria("Ensalada Tropical", Section.SALADS.name(), "Lechuga, tomate, gauda, piña, manzana, kiwi, salsa rosa",9.20, false);
		ComidaPizzeria plato11 = new ComidaPizzeria("Ensalada Nostra", Section.SALADS.name(), "Lechuga, tomate, zanahoria, atún, maiz, espárragos",8.00, false);
		ComidaPizzeria plato12 = new ComidaPizzeria("Helado", Section.DESSERTS.name(), "Leche, azucar, Fruta según sabor",2.95, false);
		ComidaPizzeria plato13= new ComidaPizzeria("Coolant", Section.DESSERTS.name(), "Leche, harina, chocolate, azucar",3.55, false);
		ComidaPizzeria plato14 = new ComidaPizzeria("Pan rústico", Section.BREAD.name(), "harina, sal, agua",0.90, false);
		ComidaPizzeria plato15 = new ComidaPizzeria("Pan centeno", Section.BREAD.name(), "Centeno, sal, agua",0.90, false);
		ComidaPizzeria plato16 = new ComidaPizzeria("Pan con pasas", Section.BREAD.name(), "Harina, sal, agua, uva",1.20, false);
		ComidaPizzeria plato17 = new ComidaPizzeria("Lomo y Huevos", Section.COMBINADOS.name(), "Cerdo, huevo, patata, aceite, sal",9.90, false);
		ComidaPizzeria plato18 = new ComidaPizzeria("Cazón y Huevos", Section.COMBINADOS.name(), "Cazón, huevo, patata, aceite, sal",9.90, false);
		ComidaPizzeria plato19 = new ComidaPizzeria("Salchichas y pimientos", Section.COMBINADOS.name(), "Cerdo, pimiento, patata, aceite, sal",5.90, false);
		ComidaPizzeria plato20 = new ComidaPizzeria("Hamburguesa casa", Section.OTHERS.name(), "Cerdo, huevo, patata, acerite, sal",9.90, false);
		ComidaPizzeria plato21 = new ComidaPizzeria("Ketchup", Section.OTHERS.name(), "Tomate, azucar, sal...",0.20, false);
		ComidaPizzeria plato22 = new ComidaPizzeria("Mahonesa", Section.OTHERS.name(), "Huevo, aceite, sal",0.20, false);
		ComidaPizzeria plato23 = new ComidaPizzeria("Cerveza",Section.DRINKS.name(),"Cebada, Alcohol",1.5,false);
		ComidaPizzeria plato24 = new ComidaPizzeria("Cerveza sin",Section.DRINKS.name(),"Cebada",1.5,false);
		ComidaPizzeria plato25 = new ComidaPizzeria("Agua grande",Section.DRINKS.name(),"Agua",2.5,false);
		ComidaPizzeria plato26 = new ComidaPizzeria("Agua pequeña",Section.DRINKS.name(),"Agua",1.5,false);
		
		

		List<ComidaPizzeria> comidaPizzeria = new ArrayList<ComidaPizzeria>();
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
		
		//Creación de los arrays con los idiomas que conoce cada camarero, los camareros, repartidores y cocineros
		String[] language1 = {"Español","inglés"};
		String[] language2 = {"Español","inglés, Francés"};
		String[] language3 = {"Español","inglés, Alemán"};
		Cocinero cocinero1 = new Cocinero("Francisco", "Soler Villegas","14253678A", 1200.50, Turno.TARDE.name(), "624582159", "Pizzas", 2, false);
		Cocinero cocinero2 = new Cocinero("Elena", "García Moro","85854545C", 1400.50, Turno.NOCHE.name(), "9834235485", "Vanguardia", 5, true);
		Camarero camarero1 = new Camarero("Vanesa", "Martin Fierro","85412365B", 1050.50, Turno.NOCHE.name(),"523568974","Barra", language1, false);
		Camarero camarero2 = new Camarero("Jonas", "Valverde Schultz","44128369R", 1050.50,Turno.TARDE.name(),"685214792","Terraza", language2, true);
		Repartidor repartidor1 = new Repartidor("Juan", "Pérez Morales","78451236C", 800.00,Turno.TARDE.name(),"568471236", Transport.Moto.toString(), 19, true, true);
		Repartidor repartidor2 = new Repartidor("Manu", "González Gazquez","45826573J", 900.00,Turno.NOCHE.name(),"631657157", Transport.Bicicleta.toString(), 18, false, false);
		//Creación de lista de Trabajadores y se añaden los cocineros, camareros y repartidores
		List<Trabajador> trabajadoresPizzeria= new ArrayList<Trabajador>();
		trabajadoresPizzeria.add(0, cocinero1);
		trabajadoresPizzeria.add(1, cocinero2);
		trabajadoresPizzeria.add(2, camarero1);
		trabajadoresPizzeria.add(3, camarero2);
		trabajadoresPizzeria.add(4, repartidor1);
		trabajadoresPizzeria.add(5, repartidor2);
		
		//Creación de la lista de comidas para pedido1 y el objeto pedido1 y la lista de objetos pedido.
		List<ComidaPizzeria> comidaPedido1 = new ArrayList<ComidaPizzeria>();
		comidaPedido1.add(0, plato3); //0 es la posición en la lista y 1 el id de la "Comida"
		comidaPedido1.add(1, plato5); //1 es la posición en la lista y 2 el id de la "Comida"
		DestinoPedido destinoPedido1 = new DestinoPedido("Bar 1",Zone.BAR);
		Pedido pedido1 = new Pedido(comidaPedido1, 32, camarero1, null, destinoPedido1);
		//Creación de la lista de pedidos e inserción del pedido1
		List<Pedido> pedidosPizzeria= new ArrayList<Pedido>();
		pedidosPizzeria.add(0, pedido1);
		//Creación de un objeto pizzeria asociándole todos los datos creados antes
		Pizzeria pizzeria = new Pizzeria("Pizzeria Bartolini", "c/Levantina 2 Bajo C.P:04240 Viator(Almeria)", trabajadoresPizzeria, comidaPizzeria,pedidosPizzeria);
		//pedidosPizzeria.indexOf(pedido1);
		//Se asigna el número de puestos en barra, mesas en interior y exterior
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
		FramePizzeria miFrame = new FramePizzeria(pizzeria);

		
		//Generación de  un pedido con los datos que inserta el operador y se presenta el ticket
		if (pizzeria.seleccionaTurno() !="" ) {
			pizzeria.generarPedido(comidaPizzeria, log);
		}else System.out.println("Fuera de horario de generación pedidos.");

		//Generación de listado de pedidos
		System.out.println("\n*************** Listado de pedidos en pizzeria *****************");
		System.out.println(pizzeria.getOrders().toString());
	}
	
}
