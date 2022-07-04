package Semaforos;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Clientes[] id = new Clientes[10];
		Limpieza di;
		Aseos p = new Aseos();

		for (int i = 0; i < id.length; i++) {
			id[i] = new Clientes(i, p);
		}

		di = new Limpieza(p);


		int i;
		for (i = 0; i < 5; i++) {
				id[i].start();
		}

		di.start();

		for (i = 5; i < id.length; i++) {
				id[i].start();
		}
	}

}
