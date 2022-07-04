package interrupcion;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Habitacion {
  //Si la cantidad de estudiantes en la habitación es igual o mayor que este límite, el decano entra en la habitación
  //El programa debe funcionar correctamente para cualquier valor de este límite
  public static final int LIMITE_ESTUDIANTES = 10;
  private int n =0;
  private Lock l = new ReentrantLock();
  private Condition cEstudiante = l.newCondition();
  private boolean estudiante=true;
  private Condition cDecano = l.newCondition();
  private boolean decano = false;
  private Condition sDecano = l.newCondition();
  private boolean sdecano = false;
	
	public void entraEstudiante(int id) throws InterruptedException{
		l.lock();
		try {
			while(!estudiante) 
				cEstudiante.await();
			n++;
		//	System.out.println(n+ " EE "+id);
			System.out.println("Ha entrado un estudiante, altualmente hay "+n+" estudiantes en la habitación.");
			if(n>LIMITE_ESTUDIANTES||n==0) {
				decano=true;
				cDecano.signal();
			}else {
				decano=false;
			}
			
		}finally {
			l.unlock();	
		}
	}
	
    public void saleEstudiante(int id)  throws InterruptedException{
		l.lock();
		try {
			n--;
			if(n==0) {
				sdecano=true;
				sDecano.signal();
			}
			//System.out.println(n+ " SE "+id);
			System.out.println("Ha salido un estudiante, altualmente hay "+n+" estudiantes en la habitación.");	
		}finally {
			l.unlock();	
		}
	}
    
    
    public void entraDecano() throws InterruptedException{
		l.lock();
		try {
			while(!decano) 
				cDecano.await();
			estudiante=false;
		//	System.out.println(n+ " ED-----------------------");
			System.out.println("Ha entrado el decano, altualmente hay "+n+" estudiantes en la habitación.");
			
			
		}finally {
			l.unlock();	
		}
	}
 
    public void saleDecano() throws InterruptedException{
		l.lock();
		try {
			while(!sdecano) {
				sDecano.await();
			}
			sdecano=false;
			decano=false;
			estudiante=true;
			cEstudiante.signalAll();

		//	System.out.println(n+" SD-------------------");
			System.out.println("Ha salido el decano, altualmente hay "+n+" estudiantes en la habitación.");	
		}finally {
			l.unlock();	
		}
	}
}
