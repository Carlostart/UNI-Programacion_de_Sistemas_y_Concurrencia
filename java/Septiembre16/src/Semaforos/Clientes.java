package Semaforos;

public class Clientes extends Thread {
	private int id;
	private Aseos as;

	public Clientes(int id, Aseos as) {
		this.id = id;
		this.as = as;
	}

	public void run(){

		try {
			as.entroAseo(id);
			Thread.sleep(100);
			as.salgoAseo(id);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
