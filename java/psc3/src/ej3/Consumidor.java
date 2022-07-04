package ej3;

public class Consumidor extends Thread {
	private VariableCompartida vc;
	// private Productor h2;
	
	public Consumidor(VariableCompartida vc) {
		this.vc = vc;
	}
	
	// public void setProductor(Productor h2) {
	// this.h2=h2;
	// }

	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println(vc.get());
			// try {
			// this.wait();
			// } catch (InterruptedException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			// h2.notify();
		}
	}
}
