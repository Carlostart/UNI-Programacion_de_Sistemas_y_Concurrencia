package ej2;

public class ej2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		NivelAgua nivel = new NivelAgua(4);
		Presa p1 = new Presa(nivel, 0);
		Presa p2 = new Presa(nivel, 1);
		Rio r1 = new Rio(nivel, 2);
		Rio r2 = new Rio(nivel, 3);

		r1.start();
		r2.start();
		p1.start();
		p2.start();

		try {
			r1.join();
			r2.join();
			p1.join();
			p2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Nivel Final del Agua: " + nivel.n);

	}

}
