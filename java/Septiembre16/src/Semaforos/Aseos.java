package Semaforos;

import java.util.concurrent.Semaphore;

public class Aseos {

	private Semaphore c = new Semaphore(1, true);
	private Semaphore l = new Semaphore(1, true);
	private Semaphore mutex = new Semaphore(1, true);
	private Semaphore mutex2 = new Semaphore(1, true);
	private int n = 0;
	private boolean esperando = false;

	public void entroAseo(int id) throws InterruptedException {
		c.acquire();
		if (!esperando) {
			mutex.acquire();
			n++;
			System.out.println("Cliente " + id + " entra al aseo, " + n + " personas dentro.");
			mutex.release();
		} else {
			entroAseo(id);
		}
		c.release();
	}

	/**
	 * Utilizado por el cliente id cuando sale de los aseos
	 * 
	 * @throws InterruptedException
	 *
	 */
	public void salgoAseo(int id) throws InterruptedException {
		mutex.acquire();
		n--;
		System.out.println("-Cliente " + id + " sale del aseo, " + n + " personas dentro.");
		if (n == 0)
			l.release();
		mutex.release();
	}

	/**
	 * Utilizado por el Equipo de Limpieza cuando quiere entrar en los aseos CS:
	 * El equipo de trabajo está solo en los aseos, es decir, espera hasta que
	 * no haya ningún cliente.
	 * 
	 * @throws InterruptedException
	 *
	 */
	public void entraEquipoLimpieza() throws InterruptedException {
		l.acquire();
		if (n > 0) {
			esperando = true;
			entraEquipoLimpieza();
		} else {
			System.out.println("El servicio de limpieza esta en el aseo.");
		}
		l.release();
	}

	/**
	 * Utilizado por el Equipo de Limpieza cuando sale de los aseos
	 * 
	 * @throws InterruptedException
	 */
	public void saleEquipoLimpieza() throws InterruptedException {
		mutex2.acquire();
		System.out.println("El servicio de Limpieza ha acbado.");
		esperando = false;
		mutex2.release();
		c.release();

	}
}
