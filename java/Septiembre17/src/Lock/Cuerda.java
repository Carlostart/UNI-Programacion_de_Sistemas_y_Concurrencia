package Lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cuerda {
	private Lock l = new ReentrantLock();
	private Condition cNS = l.newCondition();
	private Condition cSN = l.newCondition();
	private boolean NS = true;
	private boolean SN = true;
	private int n = 0;

	public  void entraDireccionNS(int id) throws InterruptedException{
		l.lock();
		try {
			while (!NS)
				cNS.await();
			if (n < 3) {
				n++;
				System.out.println("El Babuino " + id + " ha subido a la cuerda direccion Norte-Sur, en la cuerda hay "
						+ n + " Babuinos");
				if (n == 1)
					SN = false;
			} else {
				NS = false;
				entraDireccionNS(id);
			}
		} finally {
			l.unlock();
		}
	}

	public void saleDireccionNS(int id) throws InterruptedException {
		l.lock();
		try {
			n--;
			System.out.println("-El Babuino " + id + " ha bajado de la cuerda direccion Norte-Sur, en la cuerda hay "
					+ n + " Babuinos.");
			if (n == 0) {
				SN = true;
				cSN.signalAll();
				NS = true;
				cNS.signalAll();
			}

		} finally {
			l.unlock();
		}
	}

	public  void entraDireccionSN(int id) throws InterruptedException{
		l.lock();
		try {
			while (!SN)
				cSN.await();
			if (n < 3) {
				n++;
				System.out.println("El Babuino " + id + " ha subido a la cuerda direccion Sur-Norte, en la cuerda hay "
						+ n + " Babuinos");
				if (n == 1)
					NS = false;
			} else {
				SN = false;
				entraDireccionNS(id);
			}
		} finally {
			l.unlock();
		}
	}

	public  void saleDireccionSN(int id) throws InterruptedException{
		l.lock();
		try {
			n--;
			System.out.println("-El Babuino " + id + " ha bajado de la cuerda direccion Sur-Norte, en la cuerda hay "
					+ n + " Babuinos.");
			if (n == 0) {
				NS = true;
				cNS.signalAll();
				SN = true;
				cSN.signalAll();
			}

		} finally {
			l.unlock();
		}
	}	
		
}
