package ej1;

import java.util.ArrayList;

public class BufferCircular<T> {
	private ArrayList<T> elem;
	private int p;
	private int c;
	public int nelem;
	
	public BufferCircular(int tam) {
		elem = new ArrayList<T>();
		p = 0;
		c = 0;
		nelem = tam;
	}
	
	public boolean añadir(T n) {
		if (p == c) {
			elem.add(n);
			p++;
			System.out.println("Produce: " + elem.get(p - 1));
			return true;
		} else
		return false;
	}
	
	public boolean consumir() {
		if (c < p) {
			System.out.println("Consume: " + elem.get(c));
			c++;
			return true;
		} else
			return false;
	}

}
