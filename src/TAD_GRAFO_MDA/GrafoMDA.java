package TAD_GRAFO_MDA;

import java.util.LinkedList;
import java.util.Stack;

import tadCola.Queue;
import tadListaSimple.JeHaSimpleList;
import util.*;

public class GrafoMDA<T extends Comparable> implements IGrafo<T> {

	public final static int MAX_VERTICES = 200;
	public final static boolean DIRIGIDO = true;
	public final static boolean NO_DIRIGIDO = false;
	private int numVertices;
	private int numAristas;
	private boolean dirigido;
	private Arista[][] matriz;
	private T[] vertices;
	private JeHaSimpleList<Arista<T>> listaArista;

	@SuppressWarnings("unchecked")
	public GrafoMDA(boolean dirigido) {
		matriz = new Arista[MAX_VERTICES][MAX_VERTICES];
		vertices = (T[]) new Comparable[MAX_VERTICES];
		this.dirigido = dirigido;
		numVertices = 0;
		numAristas = 0;

		listaArista = new JeHaSimpleList<Arista<T>>();
	}

	@Override
	public int cantidadVertices() {
		return numVertices;
	}

	@Override
	public int cantidadAristas() {
		return numAristas;
	}

	@Override
	public void agregarVertice(int pos, T elemento) {
		vertices[pos] = elemento;
	}

	@Override
	public int verticeEnElGrafo(T vertice) {
		int retorno = -1;
		for (int i = 0; i < numVertices; i++) {
			if (vertice != null)
				if (vertice.compareTo(vertices[i]) == 0) {
					retorno = i;
				}
		}
		return retorno;
	}

	@Override
	public void agregarArista(T inicio, T fin, double peso) {
		/*
		 * Verifica si el vertice de inicio ya esta en el grafo
		 */

		// Asigna el elemento en la posicion sgte de la matriz de adyacencias

		// aumenta el numero de arista y vertices
		if (dirigido) {
			agregarAristaEnGrafoD(inicio, fin, peso);
		} else {
			agregarAristaEnGrafoND(inicio, fin, peso);
		}

		// agrega la informacion de los vertices al contenedor de
		// estos(vertices)

	}

	@SuppressWarnings("unchecked")
	private void agregarAristaEnGrafoND(T verticeInicio, T verticeFin,
			double peso) {
		/*
		 * agrega los vertices al conjunto de vertices del grafo, verificando su
		 * existencia (verticeEnElGrafo retorna -1 si el vertice no esta en el
		 * grafo) Luego re asigna los valores a los cuales fueron agregados,
		 * para modificar la matriz de adyacencias
		 */
		int verticesInicioIs = verticeEnElGrafo(verticeInicio);
		int verticeFinIs = verticeEnElGrafo(verticeFin);

		if (verticesInicioIs == -1 && verticeFinIs == -1) {

			agregarVertice(numVertices++, verticeInicio);

			agregarVertice(numVertices++, verticeFin);

			verticesInicioIs = verticeEnElGrafo(verticeInicio);
			verticeFinIs = verticeEnElGrafo(verticeFin);

			System.out.println("ningun vertice estaba " + verticesInicioIs
					+ " @ " + verticeFinIs + " @ " + numVertices);

		} else if (verticesInicioIs == -1 && verticeFinIs != -1) {

			agregarVertice(numVertices, verticeInicio);
			verticesInicioIs = numVertices;
			numVertices++;

			System.out.println("solo el de fin estaba");
		} else if (verticesInicioIs != -1 && verticeFinIs == -1) {

			agregarVertice(numVertices, verticeFin);
			verticeFinIs = numVertices;
			numVertices++;

			System.out.println("solo el de inicio estaba");
		}

		System.out.println(verticesInicioIs + " @ " + verticeFinIs);

		Arista<T> a1 = new Arista<T>(verticeInicio, verticeFin, peso);
		matriz[verticesInicioIs][verticeFinIs] = a1;

		matriz[verticeFinIs][verticesInicioIs] = a1;
		numAristas += 2;

		listaArista.add(a1);
		System.out.println("luego de agregar arista(en_metodo)");

	}

	private void agregarAristaEnGrafoD(T verticeInicio, T verticeFin,
			double peso) {
		int verticesInicioIs = verticeEnElGrafo(verticeInicio);
		int verticeFinIs = verticeEnElGrafo(verticeFin);

		if (verticesInicioIs == -1 && verticeFinIs == -1) {

			agregarVertice(numVertices++, verticeInicio);

			agregarVertice(numVertices++, verticeFin);

			verticesInicioIs = verticeEnElGrafo(verticeInicio);
			verticeFinIs = verticeEnElGrafo(verticeFin);

			System.out.println("ningun vertice estaba " + verticesInicioIs
					+ " @ " + verticeFinIs + " @ " + numVertices);

		} else if (verticesInicioIs == -1 && verticeFinIs != -1) {

			agregarVertice(numVertices, verticeInicio);
			verticesInicioIs = numVertices;
			numVertices++;

			System.out.println("solo el de fin estaba");
		} else if (verticesInicioIs != -1 && verticeFinIs == -1) {

			agregarVertice(numVertices, verticeFin);
			verticeFinIs = numVertices;
			numVertices++;

			System.out.println("solo el de inicio estaba");
		}

		Arista<T> a1 = new Arista<T>(verticeInicio, verticeFin, peso);
		matriz[verticesInicioIs][verticeFinIs] = a1;

		listaArista.add(a1); // agregada al mundo

		numAristas++;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean formanArista(T ele1, T ele2) {
		boolean retorno = false;
		int posInicio = verticeEnElGrafo(ele1);
		int posFin = verticeEnElGrafo(ele2);
		System.out.println(posInicio + " - " + posFin);
		if (!dirigido) {
			if (posInicio != -1 && posFin != -1) {
				Arista<T> arista = matriz[posInicio][posFin];
				Arista<T> arista1 = matriz[posFin][posInicio];
				if (arista.getInicio().equals(arista1.getInicio())
						&& arista.getFin().equals(arista1.getFin())) {
					retorno = true;
				}
			}
		} else {
			if (posInicio != -1 && posFin != -1) {
				Arista<T> arista = matriz[posInicio][posFin];
				if (arista.getInicio().compareTo(ele1) == 0
						&& arista.getFin().compareTo(ele2) == 0) {
					retorno = true;
				}
			}
		}
		return retorno;
	}

	@Override
	public boolean esDirigido() {
		return dirigido;
	}

	@Override
	public T[] listaConVertices() {
		return vertices;

	}

	@Override
	public JeHaSimpleList<Arista<T>> listaConAristas() {
		return listaArista;
	}

	@Override
	public JeHaSimpleList<Arista<T>> aristasAdyacentes(T vertice) {
		JeHaSimpleList<Arista<T>> ady = new JeHaSimpleList<Arista<T>>();
		for (int i = 0; i < listaArista.getSize(); i++) {
			Arista<T> a = listaArista.get(i);
			if (a.getInicio().equals(vertice))
				ady.add(a);
		}
		return ady;

	}

	public double darPesoArista(T origen, T destino) {
		if (verticeEnElGrafo(origen) != -1 && verticeEnElGrafo(destino) != -1) {
			for (int i = 0; i < listaArista.getSize(); i++) {
				Arista<T> a = listaArista.get(i);
				if (a.getInicio().equals(origen) && a.getFin().equals(destino))
					return a.getPeso();
			}
		}
		return -1;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JeHaSimpleList<T> verticesAdyacentesA(T vertice) {
		int posElemento = verticeEnElGrafo(vertice);
		JeHaSimpleList<T> listaRetorno = new JeHaSimpleList<T>();
		Arista[] adyacentes = matriz[posElemento];
		for (int i = 0; i < numAristas; i++) {
			if (adyacentes[i] != null) {
				if (((T) adyacentes[i].getInicio()).compareTo(vertice) == 0) {
					listaRetorno.add((T) adyacentes[i].getFin());
				} else {
					listaRetorno.add((T) adyacentes[i].getInicio());
				}
			}
		}
		return listaRetorno;
	}

	@Override
	public boolean caminoEntreVertices(T el1, T el2) {

		return false;
	}

	@Override
	public boolean esConexo() {

		return false;
	}

	@Override
	public boolean uniOFullConexo() {

		return false;
	}

	@Override
	public boolean grafoAciclico() {
		boolean retorno = true;
		for (int i = 0; i < numVertices; i++) {
			if (matriz[i][i] != null) {
				retorno = false;
			}
		}
		return retorno;
	}

	public Double[][] generarMatrizPesos() {
		Double[][] matriz = new Double[numVertices][numVertices];
		for (int i = 0; i < vertices.length; i++) {
			for (int j = 0; j < vertices.length; j++) {
				if (i == j)
					matriz[i][j] = -1.0;
				else {

					if (formanArista(vertices[i], vertices[j])) {
						int posI = verticeEnElGrafo(vertices[i]);
						int posF = verticeEnElGrafo(vertices[j]);
						matriz[i][j] = this.matriz[posI][posF].getPeso();
					} else {
						matriz[i][j] = -1.0;
					}
				}
			}
		}

		return matriz;
	}

	@Override
	public JeHaSimpleList<T> grafoEnAmplitud(T inicio) {
		int pos = verticeEnElGrafo(inicio);
		T vertice1 = vertices[pos];
		Double[][] mat = generarMatrizPesos();
		JeHaSimpleList<T> bfs = new JeHaSimpleList<>();
		Queue<T> queue = new Queue();
		queue.enqueue(vertice1);
		bfs.add(vertice1);
		while (!queue.isEmpty()) {
			for (int i = 0; i < mat.length; i++) {
				Double weight = mat[pos][i];
				if (weight != Double.MAX_VALUE && weight != 0.0) {
					T ver = vertices[i];
					queue.enqueue(ver);
					bfs.add(ver);
					// columna0(mat, i);
					mat[i][pos] = 0.0;

				}
			}
			queue.dequeue();
			if (!queue.isEmpty())
				pos = verticeEnElGrafo(queue.getFront());
		}
		return bfs;
	}

	// boolean[] visitadoAnchura = new boolean[numVertices];
	// JeHaSimpleList<T> respuesta = new JeHaSimpleList<T>(), recorridos = new
	// JeHaSimpleList<T>();
	// int pos = verticeEnElGrafo(inicio);
	// visitadoAnchura[pos] = true;
	// Queue<T> cola = new Queue<T>();
	// recorridos.add(inicio);
	// cola.enqueue(inicio);
	// while (!cola.isEmpty()) {
	// T a = cola.getFront();
	// cola.dequeue();
	// if (respuesta.search(a) == null) {
	// recorridos.add(a);
	// respuesta.add(a);
	// JeHaSimpleList<T> adyacentes = verticesAdyacentesA(a);
	// if (!adyacentes.isEmpty()) {
	// for (int j = 0; j < adyacentes.getSize(); j++) {
	// cola.enqueue(adyacentes.get(j));
	// recorridos.add(adyacentes.get(j));
	// }
	// }
	// if (respuesta.getSize() == numVertices) {
	// break;
	// }
	// }
	// }
	//
	// return respuesta;
	// }

	@Override
	public JeHaSimpleList<T> grafoEnProfundidad(T inicio) {
		JeHaSimpleList<T> respuesta = new JeHaSimpleList<T>(), recorridos = new JeHaSimpleList<T>();
		Stack<T> pila = new Stack<T>();
		pila.push(inicio);
		while (!pila.isEmpty()) {
			T elemento = pila.pop();
			if (respuesta.search(elemento) == null) {
				recorridos.add(elemento);
				respuesta.add(elemento);
				JeHaSimpleList<T> adyacentes = verticesAdyacentesA(elemento);
				if (!adyacentes.isEmpty()) {
					for (int j = adyacentes.getSize() - 1; j >= 0; j--) {
						pila.push(adyacentes.get(j));
						recorridos.add(adyacentes.get(j));
					}
				}
				if (respuesta.getSize() == numVertices)
					break;
			}
		}
		return respuesta;
	}

}
