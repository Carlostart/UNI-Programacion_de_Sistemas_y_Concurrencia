package sushi;

import java.util.concurrent.Semaphore;

public class Bar {
  //número de asientos. El programa debe de funcionar correctamente con cualquier número de asientos
  public static final int NUM_ASIENTOS = 5;
  private int n = 0;
  private Semaphore s = new Semaphore(1,true);
  private Semaphore mutex = new Semaphore(1,true);
  private boolean espera=false;
	/**
	 * Utilizado por el cliente id cuando quiere entrar en el restaurante.
	 * Si hay sitio, se sienta. Si está lleno, debe esperar a que TODO el grupo
	 * que ocupa el restaurante se haya marchado antes de sentarse.
	 */
	public void pidoMesa(int id) throws InterruptedException{
		s.acquire();
		if(n<NUM_ASIENTOS && !espera) {
			mutex.acquire();
			n++;
	//		System.out.println("id "+ id+ " n "+n);
			System.out.println("El cliente " +id + " entra a la mesa. Actualmente hay "+n+" personas en la mesa.");
			mutex.release();
			s.release();
		} else {
			espera=true;
			pidoMesa(id);
		}
	}
	
	/**
	 * Utilizado por el cliente id cuando se marcha del restaurante
	 */
	public void meVoy(int id) throws InterruptedException{
		mutex.acquire();
		n--;
		if(n==0) 
			espera=false;
		//System.out.println("-id "+ id+ " n "+n);
		System.out.println("El cliente "+id+" abandona la mesa, Actualmente hay "+n+" personas en la mesa.");
		s.release();
		mutex.release();
		
	}

}
