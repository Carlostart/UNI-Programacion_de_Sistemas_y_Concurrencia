package sushi;

import java.util.concurrent.*;
public class Bar {
  //numero de asientos. El programa debe de funcionar correctamente con cualquier n�mero de asientos
  public static final int NUM_ASIENTOS = 5;
  
  private Semaphore mutex = new Semaphore(1);
  private Semaphore siguiente = new Semaphore(1);
  private Semaphore espera = new Semaphore(0);
  private int numC = 0;
  private boolean esperaGrupo = false;
	/**
	 * Utilizado por el cliente id cuando quiere entrar en el restaurante.
	 * Si hay sitio, se sienta. Si esta lleno, debe esperar a que TODO el grupo
	 * que ocupa el restaurante se haya marchado antes de sentarse.
	 */
	public void pidoMesa(int id) throws InterruptedException{
		siguiente.acquire();
		mutex.acquire();
		if (numC==NUM_ASIENTOS){ //el bar esta lleno
			esperaGrupo = true;
			System.out.println("Cliente "+id+" entra. Bar lleno!!!");
			mutex.release();
			espera.acquire();
			mutex.acquire();
		}
		numC++;
		System.out.println("Cliente "+id+" entra en el bar. Hay "+numC);
		mutex.release();
		siguiente.release();
	}
	
	/**
	 * Utilizado por el cliente id cuando se marcha del restaurante
	 */
	public void meVoy(int id) throws InterruptedException{
		mutex.acquire();
		numC--;
		System.out.println("Cliente "+id+" sale del bar. Hay "+numC);
		if (numC==0 && esperaGrupo){
			esperaGrupo = false;
			System.out.println("Cliente "+id+" sale. Bar vacio!!!");
			espera.release();
		}
		mutex.release();
	}

}
