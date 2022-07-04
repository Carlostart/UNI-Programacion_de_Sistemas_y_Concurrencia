package ej3;

public class Productor extends Thread {
	private VariableCompartida vc;
	// private Consumidor h1;

	public Productor(VariableCompartida vc) {
		this.vc = vc;
	}

	// public void setConsumidor(Consumidor h1) {
	// this.h1 = h1;
	// }

	public void run() {
		for (int i = 0; i < 100; i++) {
			vc.set(i);
			// try {
			// this.wait();
			// } catch (InterruptedException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			// h1.notify();
		}
	}
}
