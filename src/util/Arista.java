package util;

public class Arista<T> {

	private double peso;
	private T inicio;
	private T fin;

	public Arista(T verticeInicio, T verticeFin, double peso) {
		this.fin = verticeFin;
		this.peso = peso;
		this.inicio = verticeInicio;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public T getInicio() {
		return inicio;
	}

	public void setInicio(T inicio) {
		this.inicio = inicio;
	}

	public T getFin() {
		return fin;
	}

	public void setFin(T fin) {
		this.fin = fin;
	}

}
