package semaforosCompleto;

import java.util.*;
public class Pastelero extends Thread{

	private Random r = new Random();
	private Bandeja p;

	public Pastelero(Bandeja p){
		this.p = p;
	}
	
	
	public void run(){
		boolean fin = false;
		while (!this.isInterrupted() && !fin){
			try {
				Thread.sleep(r.nextInt(200));
				p.tarta();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				fin = true;
			}
		}
	}
}
