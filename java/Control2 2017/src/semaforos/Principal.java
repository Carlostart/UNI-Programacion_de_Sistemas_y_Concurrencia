package semaforos;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Bandeja pasteleria = new Bandeja();
		Pastelero pastelero = new Pastelero(pasteleria);
		Ni�o[] ni�o = new Ni�o[30];
		for (int i = 0; i < ni�o.length; i++) {
			ni�o[i] = new Ni�o(pasteleria, i);
		}
		pastelero.start();
		for (int i = 0; i < ni�o.length; i++) {
			ni�o[i].start();
		}
		
		try {
			Thread.sleep(1000);
			pastelero.interrupt();
			pastelero.join();

			for (int i = 0; i < ni�o.length; i++) {
				ni�o[i].interrupt();
				ni�o[i].join();
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
