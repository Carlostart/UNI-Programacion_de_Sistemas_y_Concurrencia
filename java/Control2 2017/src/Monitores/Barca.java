package Monitores;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Barca extends Thread{
	private Lock l = new ReentrantLock();

	private int nIphone = 0;
	private int nAndroid = 0;
	
	private Condition cIphone = l.newCondition();
	private boolean iphone = true;
	private Condition cAndroid = l.newCondition();
	private boolean android = true;
	private Condition cViaje = l.newCondition();
	private boolean viaje = false;
	
	/**
	 * Un estudiante con móvil android llama a este método 
	 * cuando quiere cruzar el río. Debe esperarse a montarse en la
	 * barca de forma segura, y a llegar a la otra orilla del antes de salir del
	 * método
	 * @param id del estudiante android que llama al método
	 * @throws InterruptedException
	 */
	public void android(int id) throws InterruptedException{
		l.lock();
		try {
			while (!android)
				cAndroid.await();

			nAndroid++;
			System.out.println("Android " + id + " sube con Android: " + nAndroid + " iPhone: " + nIphone);
			if (nIphone + nAndroid == 4) {
				android = false;
				iphone = false;
				viaje = true;
				cViaje.signalAll();
			} else {
				if (nIphone == 3 || (nAndroid == 2 && nIphone == 1))
					android = false;
				if (nAndroid == 3 || (nAndroid == 1 && nIphone == 2)) {
					iphone = false;
				}
			}

			while (!viaje)
				cViaje.await();

			System.out.println("Android: " + id + " baja con Android: " + nAndroid + " iPhone: " + nIphone);
			nAndroid--;

			if (nAndroid + nIphone == 0) {
				android = true;
				iphone = true;
				viaje = false;
				cIphone.signalAll();
				cAndroid.signalAll();
				System.out.println("*****************************************");
			}

		} finally {
			l.unlock();
		}
	}
	
	/**
	 * Un estudiante con móvil android llama a este método 
	 * cuando quiere cruzar el río. Debe esperarse a montarse en la
	 * barca de forma segura, y a llegar a la otra orilla del antes de salir del
	 * método
	 * @param id del estudiante android que llama al método
	 * @throws InterruptedException
	 */

	public void iphone(int id) throws InterruptedException{
		l.lock();
		try {
			while (!iphone)
				cIphone.await();

			nIphone++;
			System.out.println("iPhone " + id + " sube con Android: " + nAndroid + " iPhone: " + nIphone);
			if (nIphone + nAndroid == 4) {
				android = false;
				iphone = false;
				viaje = true;
				cViaje.signalAll();
			} else {
				if (nIphone == 3)
					android = false;
				if (nAndroid == 3) {
					iphone = false;
				}
			}

			while (!viaje)
				cViaje.await();

			System.out.println("iPhone: " + id + " baja con Android: " + nAndroid + " iPhone: " + nIphone);
			nIphone--;

			if (nAndroid + nIphone == 0) {
				android = true;
				iphone = true;
				viaje = false;
				cIphone.signalAll();
				cAndroid.signalAll();
				System.out.println("*****************************************");
			}

		} finally {
			l.unlock();
		}
	}
	
	
	
	

}
