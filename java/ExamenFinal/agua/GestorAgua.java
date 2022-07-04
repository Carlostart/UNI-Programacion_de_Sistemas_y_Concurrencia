package agua;

//import java.util.concurrent.Semaphore;

import java.util.concurrent.*;

public class GestorAgua {

	private Semaphore hidrogeno = new Semaphore(1,true);
	private Semaphore oxigeno = new Semaphore(1,true);
	private Semaphore mutexh = new Semaphore(1,true);
	private Semaphore mutexo = new Semaphore(1,true);
	private int h=0;
	private int o=0;
	
	
	public void hListo(int id) throws InterruptedException{ 
		hidrogeno.acquire();
		mutexh.acquire();
		h++;
		mutexo.acquire();
		if(o>=1 && h>=2) {
			System.out.println("Se genera una molecula de agua. Hidrogeno-> "+ id);
			h--;
			hidrogeno.release();
		}
		mutexo.release();
		mutexh.release();
	}
	
	public void oListo(int id) throws InterruptedException{ 
		oxigeno.acquire();
		mutexo.acquire();
		o++;
		mutexh.acquire();
		if(o>=1 && h>=2) {
			System.out.println("Se genera una molecula de agua. Oxigeno-> "+ id);
			o--;
			oxigeno.release();
		}
		mutexo.release();
		mutexh.release();
	}
}