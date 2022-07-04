package ej3;

public class ej3 {

	
	public static void main(String[] args) {
		VariableCompartida vc = new VariableCompartida(0);
		Productor h1 = new Productor(vc);
		Consumidor h2 = new Consumidor(vc);
		// h1.setConsumidor(h2);
		// h2.setProductor(h1);
		
		h1.start();
		h2.start();
		
		try {
			h1.join();
			h2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
