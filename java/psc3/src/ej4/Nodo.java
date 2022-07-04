package ej4;

import java.util.ArrayList;
import java.util.List;

public class Nodo extends Thread {
	private ArrayList<Integer> lista;
	private Nodo izq;
	private Nodo der;

	public Nodo(List<Integer> lista) {
		this.lista = (ArrayList<Integer>) lista;
	}

	private void añade(List<Integer> l, int desde, int hasta) {
		// System.out.println("lista" + lista.toString());
		l.clear();
		l.addAll(lista.subList(desde, hasta));
		// System.out.println("div" + l.toString());
	}

	private void mezcla(List<Integer> l1, ArrayList<Integer> l2) {
		lista.clear();
		int i = 0;
		int u = 0;
		while (i < l1.size() && u < l2.size()) {
			if (l1.get(i) < l2.get(u)) {
				lista.add(l1.get(i));
				i++;
			} else {
				lista.add(l2.get(u));
				u++;
			}
		}

		while (i < l1.size()) {
			lista.add(l1.get(i));
			i++;
		}

		while (u < l2.size()) {
			lista.add(l2.get(u));
			u++;
		}
		// System.out.println("mezclarun" + aux.toString());
	}

	public void run() {
		if (lista.size() > 1) {
			// System.out.println("Lista " + lista.toString());
			ArrayList<Integer> izql = new ArrayList<Integer>();
			ArrayList<Integer> derl = new ArrayList<Integer>();
			añade(izql, 0, lista.size() / 2);
			izq = new Nodo(izql);
			// System.out.println("izq" + izq.lista.toString());
			izq.run();
			añade(derl, lista.size() / 2, lista.size());
			der = new Nodo(derl);
			// System.out.println("der" + der.lista.toString());
			der.run();
			// System.out.println("mescla " + izq.lista.toString() + " " +
			// der.lista.toString());
			mezcla(izq.lista, der.lista);

		}
	}
}
