package Semaforos;

public class Limpieza extends Thread {
	private Aseos as;

	public Limpieza(Aseos as) {
		this.as = as;
	}

	public void run() {

		try {
			as.entraEquipoLimpieza();
			Thread.sleep(1000);
			as.saleEquipoLimpieza();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
