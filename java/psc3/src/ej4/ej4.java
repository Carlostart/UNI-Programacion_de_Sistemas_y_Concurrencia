package ej4;

import java.util.ArrayList;
import java.util.Random;

public class ej4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random al = new Random(System.currentTimeMillis());
		ArrayList<Integer> lista = new ArrayList<Integer>();
		int tam = al.nextInt(100);
		for (int i = 0; i < tam; i++) {
			int num = al.nextInt(100);
			lista.add(num);
		}
		Nodo raiz = new Nodo(lista);
		raiz.run();

		System.out.println("Mezcla" + lista.toString());
	}

}
