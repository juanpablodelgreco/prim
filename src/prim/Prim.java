package prim;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Prim {
	private String path;
	private int cantNodos;
	private int cantAristas;
	private int aristasEnUso;
	private int costo;
	private PriorityQueue<Arista> cola;
	private List<Integer> noSolucion;
	private List<Integer> solucion;
	private List<Arista> arbolAbarcadorDeCostoMinimo;

	public Prim(String path, int nodoInicial) {
		this.path = path;
		this.aristasEnUso = 0;
		this.costo = 0;
		noSolucion = new ArrayList<Integer>();
		solucion = new ArrayList<Integer>();
		arbolAbarcadorDeCostoMinimo = new ArrayList<Arista>();
		cola = new PriorityQueue<Arista>();
		cargarDatos();
		algoritmo(nodoInicial);
		grabarResultados();
	}

	private void algoritmo(Integer nodoInicial) {
		Arista arista;
		Integer eliminar;
		PriorityQueue<Arista> aux = new PriorityQueue<Arista>();
		noSolucion.remove(nodoInicial);
		solucion.add(nodoInicial);
		while (aristasEnUso != (cantNodos - 1) && !cola.isEmpty()) {
			arista = cola.poll();
			if (solucion.contains(arista.getDesde()) && noSolucion.contains(arista.getHasta())
					|| solucion.contains(arista.getHasta()) && noSolucion.contains(arista.getDesde())) {
				if (solucion.contains(arista.getDesde()) && noSolucion.contains(arista.getHasta())) {
					eliminar = arista.getHasta();
					noSolucion.remove(eliminar);
					solucion.add(eliminar);
				} else {
					eliminar = arista.getDesde();
					noSolucion.remove(eliminar);
					solucion.add(eliminar);
				}
				arbolAbarcadorDeCostoMinimo.add(arista);
				costo += arista.getCosto();
				aristasEnUso++;
				while (!aux.isEmpty())
					cola.add(aux.poll());
			} else
				aux.add(arista);
		}
	}

	private void grabarResultados() {
		try {
			PrintWriter pw = new PrintWriter(new File(path+".out"));
			pw.println("El costo del arbol abarcador minimo es: " + this.costo);
			for (Arista arista : arbolAbarcadorDeCostoMinimo) {
				pw.println(arista.getDesde() + " - " + arista.getHasta() + " = " + arista.getCosto());
			}
			pw.close();
			System.out.println("Archivo de salida generado con exito!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
	public void mostrarArbolYCosto() {
	
		for (Arista arista : arbolAbarcadorDeCostoMinimo) {
			System.out.println(arista.getDesde() + " - " + arista.getHasta() + " = " + arista.getCosto());
		}
	}

	private void cargarDatos() {
		try {
			Scanner sc = new Scanner(new File(path + ".in"));
			this.cantNodos = sc.nextInt();
			this.cantAristas = sc.nextInt();
			for (int i = 0; i < this.cantAristas; i++)
				cola.add(new Arista(sc.nextInt(), sc.nextInt(), sc.nextInt()));
			for (int j = 1; j <= this.cantNodos; j++)
				noSolucion.add(j);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
