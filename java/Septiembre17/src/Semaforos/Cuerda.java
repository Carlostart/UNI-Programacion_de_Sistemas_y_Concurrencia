package Semaforos;

import java.util.concurrent.Semaphore;

public class Cuerda {
	private Semaphore S = new Semaphore(1, true);
	private Semaphore N = new Semaphore(1, true);
	private Semaphore cuerda = new Semaphore(1, true);
	private Semaphore mutex1 = new Semaphore(1, true);
	private Semaphore mutex2 = new Semaphore(1, true);
	private int nN = 0;
	private int nS = 0;

	public  void entraDireccionNS(int id) throws InterruptedException{
		N.acquire();
		if (nN < 3) {
			mutex1.acquire();
			nN++;
			System.out.println("El Babuino " + id + " ha subido a la cuerda direccion Norte-Sur, en la cuerda hay " + nN
					+ " Babuinos");
			if (nN == 1)
				// System.out.println("test");
				cuerda.acquire();
			// System.out.println("test");
			mutex1.release();
			N.release();
		} else {
			// System.out.println("recursiva");
			entraDireccionNS(id);
		}
	}

	public void saleDireccionNS(int id) throws InterruptedException {
		if (nN > 0) {
			mutex1.acquire();
			nN--;
			if (nN == 0) {
				S.release();
				cuerda.release();
			}
			System.out.println("-El Babuino " + id + " ha bajado de la cuerda direccion Norte-Sur, en la cuerda hay "
					+ nN
					+ " Babuinos.");
			mutex1.release();
		}
	}

	public  void entraDireccionSN(int id) throws InterruptedException{
		// System.out.println("test");
		S.acquire();
		if (nS < 3) {
			mutex2.acquire();

			nS++;
			System.out.println("El Babuino " + id + " ha subido a la cuerda direccion Sur-Norte, en la cuerda hay " + nS
					+ " Babuinos");
			if (nS == 1)
				cuerda.acquire();
			// System.out.println("test");
			mutex2.release();
			S.release();
		} else {
			// System.out.println("recursiva");
			entraDireccionSN(id);
		}
	}

	public  void saleDireccionSN(int id) throws InterruptedException{
		if (nS > 0) {
			mutex2.acquire();
			nS--;
			if (nS == 0) {
				N.release();
				cuerda.release();
			}
			System.out.println("-El Babuino " + id + " ha bajado de la cuerda direccion Sur-Norte, en la cuerda hay "
					+ nS
					+ " Babuinos.");
			mutex2.release();
		}
	}	
		
}
