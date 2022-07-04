package residencia;

import java.util.Random;

public class Decano extends Thread{
	private static Random r = new Random();
	
	private Habitacion h;
	
	public  Decano(Habitacion h){

		this.h = h;
	}

	
	public void run(){
		boolean fin = false;
		while (!this.isInterrupted() && !fin){
			try {
				Thread.sleep(r.nextInt(500));
				h.entraDecano();
		     	Thread.sleep(r.nextInt(200));
				h.saleDecano();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				fin = true;
			}
			
		}
	}

}
