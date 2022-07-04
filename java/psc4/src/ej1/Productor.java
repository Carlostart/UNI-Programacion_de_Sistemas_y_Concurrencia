package ej1;

public class Productor extends Thread {
	private BufferCircular<Integer> buffer;

	public Productor(BufferCircular<Integer> b) {
		buffer = b;
	}

	public void run() {
		int i = 0;
		while (i < buffer.nelem) {
			if (buffer.añadir(i))
				i++;
		}
	}
}
