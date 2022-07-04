package residencia;

public class Habitacion {
  //Si la cantidad de estudiantes en la habitaci�n es igual o mayor que este l�mite, el decano entra en la habitaci�n
  //El programa debe funcionar correctamente para cualquier valor de este l�mite
  public static final int LIMITE_ESTUDIANTES = 10;
	boolean estaDecano = false;
	int numEst = 0;
	public synchronized void entraEstudiante(int id) throws InterruptedException{
		while (estaDecano)
			wait();
		numEst++;
		System.out.println("Entra estudiante "+id+" hay: "+numEst);
		if (numEst==LIMITE_ESTUDIANTES) notify();
	}
	
    public synchronized void saleEstudiante(int id)  throws InterruptedException{
    		numEst--;
    		System.out.println("Sale estudiante "+id+" hay: "+numEst);
    		if (numEst==0) notifyAll();
    }
    
    
    public synchronized void entraDecano() throws InterruptedException{
    		while (0<numEst && numEst<LIMITE_ESTUDIANTES)
    			wait();
    		System.out.println("*************Entra decano hay: "+numEst);
    		estaDecano = true;
	}
 
    public synchronized void saleDecano() throws InterruptedException{
    		while (numEst!=0)
    			wait();
    		estaDecano = false;
    		System.out.println("*************Sale decano hay: "+numEst);
    		notifyAll();
	}
}
