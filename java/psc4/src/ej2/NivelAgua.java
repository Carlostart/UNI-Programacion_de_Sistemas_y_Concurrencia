package ej2;

public class NivelAgua {
	public int n;
	private int[] turno;
	private boolean[] pidiendoturno;

	public NivelAgua(int N) {
		n = 0;
		turno = new int[N];
		pidiendoturno = new boolean[N];
	}

	public void cogeTurno(int id) {
		pidiendoturno[id] = true;
		int max = 0;
		for (int i = 0; i < turno.length; i++)
			if (max < turno[i])
				max = turno[i];

		turno[id] = max + 1;
		// System.out.println("cogeturno " + turno[id] + " " + id);
		pidiendoturno[id] = false;
	}

	private boolean meToca(int id, int i) {
		if (turno[i] > 0 && turno[i] < turno[id])
			return false;
		else if (turno[i] == turno[id] && i < id)
			return false;
		else
			return true;
	}

	public void esperaTurno(int id) {
		for (int i = 0; i < pidiendoturno.length; i++) {
			while (pidiendoturno[i])
				Thread.yield();
			while (!meToca(id, i))
				Thread.yield();
		}

	}

	public void salir(int id) {
		turno[id] = 0;
	}

	public void bajar() {
		n--;
	}

	public void subir() {
		n++;
	}
}
