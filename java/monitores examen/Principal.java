package interrupcion;



public class Principal {

	public static void main(String[] args) {
    //número de estudiantes. El programa debe de funcionar correctamente con cualquier número de estudiantes
	  final int NUM_ESTUDIANTES = 30;
    //crear recurso compartido y hebras y lanzar hebras
	  Habitacion hab = new Habitacion();
	  Estudiante[] e = new Estudiante[NUM_ESTUDIANTES];
	  Decano d = new Decano(hab);
	  for(int i=0;i<NUM_ESTUDIANTES;i++) {
		  e[i] = new Estudiante(i,hab);
	  }
	  
	  for(int i=0;i<NUM_ESTUDIANTES;i++) {
		  e[i].start();
	  }
	  d.start();
	  
	  
	  
	  try {
		Thread.sleep(10000);
		  for(int i=0;i<NUM_ESTUDIANTES;i++) {
			  e[i].interrupt();
		  }
		  d.interrupt();
		
		
	} catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	}
}
