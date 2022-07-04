package semaforos;

import java.util.concurrent.Semaphore;

public class Bandeja {
	
	private int R = 0; // numero de raciones por pastel
	private Semaphore pastelero = new Semaphore(0, true);
	private Semaphore ni�o = new Semaphore(1, true);
	private Semaphore mutex = new Semaphore(1, true);

	/**
	 * Un niño que quiere una ración de tarta llama a este método para
	 * cogerla de la bandeja. 
	 * El niño debe esperar si  la bandeja está vacía a que el pastelero
	 * ponga una nueva tarta  
	 * @param id del niño que pide la ración
	 * @throws InterruptedException
	 */
	public void quieroRacion(int id) throws InterruptedException{
		ni�o.acquire();
		if (R < 1) {
			System.out.println(id + " Llama al pastelero");
			pastelero.release();
		} else {
			mutex.acquire();
			R--;
			System.out.println("Porcion Gastada por " + id + " (" + R + ")");
			mutex.release();
			ni�o.release();
		}
	}
	
	/**
	 * El pastelero llama a este método para poner una nueva tarta en
	 * la bandeja. Tiene que esperar a que la bandeja esté vacía para ponerla.
	 * @throws InterruptedException
	 */
	public void tarta() throws InterruptedException{
		mutex.acquire();
		R = 8;
		System.out.println("Tarta repuesta");
		mutex.release();
		pastelero.acquire();
		ni�o.release();
		
	
	}
	
}
