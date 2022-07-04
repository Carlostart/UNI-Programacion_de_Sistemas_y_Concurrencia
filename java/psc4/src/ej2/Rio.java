package ej2;

public class Rio extends Thread {
	private static NivelAgua nivel;
	private int id;
	// private static Object object = new Object();

	public Rio(NivelAgua n, int id) {
		nivel = n;
		this.id = id;
	}

	public void run() {

		for (int i = 0; i < 100; i++) {
			nivel.cogeTurno(id);
			nivel.esperaTurno(id);
			// synchronized (object) {
			nivel.subir();
			System.out.println("El nivel del agua ha subido 1. Nivel actual: " + nivel.n);
			// }
			nivel.salir(id);
		}
	}

}
