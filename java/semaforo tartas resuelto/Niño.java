package semaforosCompleto;

import java.util.Random;

public class Niño extends Thread{

	private Random r = new Random();
	private Bandeja p;
	private int id;
	public Niño(Bandeja p,int id){
		this.p = p;
		this.id = id;
	}
	
	
	public void run(){
		boolean fin = false;
		while (!this.isInterrupted()&&!fin){
			try {	
				p.quieroRacion(id);
				Thread.sleep(r.nextInt(200));
			} catch (InterruptedException e) {
				fin = true;
			}
		}
	}
}
