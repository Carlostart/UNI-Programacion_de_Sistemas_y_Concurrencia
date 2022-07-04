package ej1;

public class Consumidor extends Thread {
	private BufferCircular<Integer> buffer;

	public Consumidor(BufferCircular<Integer> b) {
		buffer = b;
	}

	public void run() {
		int i = 0;
		while (i < buffer.nelem) {
			if (buffer.consumir())
				i++;
		}
	}
}
