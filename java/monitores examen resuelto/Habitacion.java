package residenciaLock;

import java.util.concurrent.locks.*;
public class Habitacion {
  //Si la cantidad de estudiantes en la habitaci�n es igual o mayor que este l�mite, el decano entra en la habitaci�n
  //El programa debe funcionar correctamente para cualquier valor de este l�mite
  public static final int LIMITE_ESTUDIANTES = 10;
	private Lock l = new ReentrantLock(true);
	private Condition cDecano = l.newCondition();
	private boolean estaDecano = false;	
	int numEst = 0;
	private Condition cEstudiante = l.newCondition();
	public  void entraEstudiante(int id) throws InterruptedException{
		l.lock();
		try{
			while (estaDecano)
				cEstudiante.await();
			numEst++;
			System.out.println("Entra estudiante "+id+" hay: "+numEst);
			if (numEst==LIMITE_ESTUDIANTES) cDecano.signal();
		}finally{
			l.unlock();
		}
	}
	
    public  void saleEstudiante(int id)  throws InterruptedException{
    		l.lock();
		try{
			numEst--;
    			System.out.println("Sale estudiante "+id+" hay: "+numEst);
    			if (numEst==0) cDecano.signal();
		}finally{
			l.unlock();
		}
    }
    
    
    public  void entraDecano() throws InterruptedException{
    		l.lock();
		try{
			while (0<numEst && numEst<LIMITE_ESTUDIANTES)
    				cDecano.await();
    			System.out.println("*************Entra decano hay: "+numEst);
    			estaDecano = true;
		}finally{
			l.unlock();
		}
	}
 
    public  void saleDecano() throws InterruptedException{
    		l.lock();
		try{
			while (numEst!=0)
    				cDecano.await();
    			estaDecano = false;
    			System.out.println("*************Sale decano hay: "+numEst);
    			cEstudiante.signalAll();
		}finally{
			l.unlock();
		}
	}
}
