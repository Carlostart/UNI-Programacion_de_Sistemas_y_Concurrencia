package ej1;

public class ej1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int tam = 100;;
		BufferCircular<Integer> bc = new BufferCircular<Integer>(tam);
	
		Productor p = new Productor(bc);
		Consumidor c = new Consumidor(bc);

		p.start();
		c.start();
	}

}
