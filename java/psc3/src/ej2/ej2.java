package ej2;

public class ej2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		VariableCompartida vc1 = new VariableCompartida(0);
		Hebra h1 = new Hebra(vc1);
		
		h1.start();

		try {
			h1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		System.out.println(vc1.get());

	}

}
