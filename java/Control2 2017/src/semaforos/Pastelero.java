package semaforos;

import java.util.Random;
public class Pastelero extends Thread{

	private Random r = new Random();
	private Bandeja p;
	public Pastelero(Bandeja p){
		this.p = p;
		
	}
	
	
	public void run(){
		while (!isInterrupted()) {
			try {
				Thread.sleep(r.nextInt(200));
				p.tarta();
				
			} catch (InterruptedException e) {
				interrupt();
			}
		}
	}
}
