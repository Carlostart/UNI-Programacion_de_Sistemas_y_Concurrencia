package ej2;

public class Presa extends Thread {
	private volatile NivelAgua nivel;
	private int id;
	// private Object object = new Object();

	public Presa(NivelAgua n, int id) {
		nivel = n;
		this.id = id;
	}

	public void run() {
		int i = 0;
		while (i < 100) {
			nivel.cogeTurno(id);
			nivel.esperaTurno(id);
			// synchronized (object) {
				if (nivel.n > 0) {
				nivel.bajar();
				System.out.println("El nivel del agua ha bajado 1. Nivel actual: " + nivel.n);
				i++;
				} else {
					System.out.println("El nivel del agua no puede bajas más.");
				}
			nivel.salir(id);

			// }
		}
	}
}
