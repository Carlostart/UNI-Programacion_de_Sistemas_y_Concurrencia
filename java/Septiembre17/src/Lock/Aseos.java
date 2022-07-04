package Lock;

import java.util.Random;
public class Aseos extends Thread{
	
	private Cuerda cuerda;
	private int id;
	private static Random r = new Random();
	
	public Aseos(int id,Cuerda c){
		cuerda = c;
		this.id = id;
	}

	
	public void run(){
		try {
			cuerda.entraDireccionSN(id);
			Thread.sleep(r.nextInt(10));
			cuerda.saleDireccionSN(id);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
