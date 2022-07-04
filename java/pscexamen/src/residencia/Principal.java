package residencia;



public class Principal {

	public static void main(String[] args) {
		//numero de estudiantes. El programa debe de funcionar correctamente con cualquier n�mero de estudiantes
		final int NUM_ESTUDIANTES = 30;
		//crear recurso compartido y hebras y lanzar hebras
		Habitacion h = new Habitacion();
		Estudiante[]  est = new Estudiante[NUM_ESTUDIANTES];
		Decano d = new Decano(h);
		for (int i=0; i<est.length; i++){
			est[i] = new Estudiante(i,h);
		}
		for (int i=0; i<est.length; i++){
			est[i].start();
		}
		d.start();
		try {
			Thread.sleep(3000);
			
			d.interrupt();
			for (int i=0; i<est.length; i++){
				est[i].interrupt();
			}
			d.join();
			for (int i=0; i<est.length; i++){
				est[i].join();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("fin del programa");
	}

}
