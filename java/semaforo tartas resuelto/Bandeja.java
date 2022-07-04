package semaforosCompleto;

import java.util.concurrent.*;
public class Bandeja {
	
	private int TAM = 8; // numero de raciones por pastel
	private Semaphore mutex = new Semaphore(1);
	private Semaphore esperoRacion = new Semaphore(0);//inicialmente no hay
	private Semaphore esperoPeticion = new Semaphore(1);//inicialmente no hay pastel
	private int nRaciones = 0;

	
	public void quieroRacion(int id) throws InterruptedException{
		esperoRacion.acquire();
		mutex.acquire();
		nRaciones--;
		System.out.println("Niño "+id+" come ración. Quedan "+nRaciones);
		if (nRaciones==0){
			System.out.println("Niño "+id+"  ha acabado el pastel. Llamo al pastelero ");
			esperoPeticion.release();
		} else {
			esperoRacion.release();
		}
		mutex.release();
		
	}
	
	
	public void tarta() throws InterruptedException{
		esperoPeticion.acquire();
		mutex.acquire();
		nRaciones=nRaciones + TAM;
		System.out.println("El pastelero ha traído otro  pastel. Quedan "+nRaciones);
		esperoRacion.release();
		mutex.release();
	
	}
	
}
