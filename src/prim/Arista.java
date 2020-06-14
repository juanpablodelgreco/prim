package prim;

public class Arista implements Comparable<Arista> {
	private int desde;
	private int hasta;
	private int costo;

	public Arista(int desde, int hasta, int costo) {
		this.desde = desde;
		this.hasta = hasta;
		this.costo = costo;
	}

	public int getDesde() {
		return desde;
	}

	public int getHasta() {
		return hasta;
	}

	public int getCosto() {
		return costo;
	}

	@Override
	public int compareTo(Arista a) {
		return (this.costo - a.costo);
	}

}
