package control;

import TAD_GRAFO_MDA.GrafoMDA;
import tadListaSimple.JeHaSimpleList;
import util.Arista;

public class ensayo {

	private static int[] a;

	public static void main(String[] args) {
		// metodo1GRAFOMDA_DIRIGIDO();
		metodo1GRAFOMDA_NODIRIGIDO();
	}

	public static void metodo1GRAFOMDA_DIRIGIDO() {
		GrafoMDA<Integer> grafito = new GrafoMDA<>(true);
		System.out.println("creo");

		System.out.println("agregando aristas");
		grafito.agregarArista(5, 9, 8);
		System.out.println("agrego");

		grafito.agregarArista(5, 1, 2);
		System.out.println("agrego");

		System.out.println("dos existentes");
		grafito.agregarArista(1, 9, 2);
		System.out.println("agrego");

		System.out.println("forma arista 1-9? = " + grafito.formanArista(1, 9));
		System.out.println("numero de aristas " + grafito.cantidadAristas());
		System.out.println("numero de vertices " + grafito.cantidadVertices());
	}

	public static void metodo1GRAFOMDA_NODIRIGIDO() {
		GrafoMDA<Integer> grafito = new GrafoMDA<>(false);
		System.out.println("creo");

		System.out.println("agregando aristas");
		grafito.agregarArista(5, 9, 8);
		System.out.println("agrego");

		grafito.agregarArista(5, 1, 2);
		System.out.println("agrego");

		System.out.println("dos existentes");
		grafito.agregarArista(1, 9, 2);
		System.out.println("agrego");

		System.out.println("forma arista 1-9? = " + grafito.formanArista(1, 9));
		System.out.println("numero de aristas " + grafito.cantidadAristas());
		System.out.println("numero de vertices " + grafito.cantidadVertices());

		// System.out.println("--------------aciclico---------------");
		// System.out.println(grafito.grafoAciclico()); // debe ser true, por
		// que
		// no tiene ciclos
		// grafito.agregarArista(1, 1, 2);
		// System.out.println("agrego");
		// System.out.println(grafito.grafoAciclico()); // debe ser false, por
		// que
		// // tiene ciclos

		/*
		 * prueba el recorrido en amplitud
		 */
		 System.out.println("--------------amplitud---------------");
		 JeHaSimpleList<Integer> a = grafito.grafoEnAmplitud(5);
		 if (a == null)
		 System.out.println("fail en amplitud");
		 else
		 for (int i = 0; i < a.getSize(); i++) {
		 System.out.println(a.getPosition(i));
		 }

//		System.out.println("--------------profundidad---------------");
//		JeHaSimpleList<Integer> b = grafito.grafoEnProfundidad(5);
//		if (b == null)
//			System.out.println("fail en amplitud");
//		else
//			for (int i = 0; i < b.getSize(); i++) {
//				System.out.println(b.getPosition(i));
//			}

		/*
		 * prueba el resultado de la lista con vertices
		 */
		// System.out.println("lista con vertices");
		// Comparable[] a = grafito.listaConVertices();
		// for (int i = 0; i < grafito.getNumVertices(); i++) {
		// System.out.println(a[i]);
		// }

		/*
		 * prueba el resultado de la lista con aristas
		 */
		// System.out.println("lista con Aristas");
		// JeHaSimpleList<Arista<Integer>> a1 = grafito.listaConAristas();
		// for (int i = 0; i < a1.getSize(); i++) {
		// System.out.println(a1.get(i).getInicio()+" - "+a1.get(i).getFin());
		// }

		/*
		 * prueba el metodo de adyacentes
		 */
		// JeHaSimpleList<Integer> adyacentesA = grafito.verticesAdyacentesA(1);
		// for (int i = 0; i < adyacentesA.getSize(); i++) {
		// System.out.println(adyacentesA.get(i));
		// }

	}

}
