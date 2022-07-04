package monitoresSimpleCompleto;

import java.util.concurrent.locks.*;
import java.util.*;
public class Barca extends Thread{
	private Lock l = new ReentrantLock(true);
	
	
	private int numAndroid = 0;
	private int numIPhone = 0;
	
	private boolean pAndroid = true;
	private Condition esperoAndroid = l.newCondition();
	
	private boolean pIPhone = true;
	private Condition esperoIPhone = l.newCondition();
	
	private boolean finViaje = false;
	private Condition esperoLleno = l.newCondition();
	
	
	
	public void android(int id) throws InterruptedException  {
		l.lock();
		try{
			while (!pAndroid){
				esperoAndroid.await();
			}
			numAndroid++;
			System.out.println("Android "+id+" se ha subido a la barca.  Android: "+numAndroid+" IPhone: "+numIPhone);
			
			if ((numAndroid==1 && numIPhone == 2)|| numAndroid==3){
				pIPhone = false;
			} else if (numAndroid==2 && numIPhone ==1){
				pAndroid = false;
			} 
			if (numAndroid+numIPhone == 4){
				pAndroid = false; pIPhone = false;
				System.out.println("Ya estamos todos!!! Vamos!!!");
				finViaje = true;
				esperoLleno.signalAll();
			} else {
				while (!finViaje)
					esperoLleno.await();
			}
		
			numAndroid--;
			System.out.println("Android "+id+" se ha bajado de la barca. Android: "+numAndroid+" IPhone: "+numIPhone);
			if (numAndroid==0 && numIPhone == 0){
				
				pAndroid = true;
				esperoAndroid.signalAll();
				pIPhone = true;
				esperoIPhone.signalAll();
				finViaje = false;
				System.out.println("**************************");
			}
		}finally{
			l.unlock();
		}
	}
	
	
	
	public void iphone(int id) throws InterruptedException{
		l.lock();
		try{
			while (!pIPhone){
				esperoIPhone.await();
			}
			numIPhone++;
			System.out.println("IPhone "+id+" se ha subido a la barca.  Android: "+numAndroid+" IPhone: "+numIPhone);
			
			if ((numIPhone==1 && numAndroid == 2)|| numIPhone==3){
				pAndroid = false;
			} else if (numIPhone==2 && numAndroid ==1){
				pIPhone = false;
			} 
			if (numAndroid+numIPhone == 4){
				pAndroid = false; pIPhone = false;
				System.out.println("Ya estamos todos!!! Vamos!!!");
				finViaje = true;
				esperoLleno.signalAll();
			} else {
				while (!finViaje)
					esperoLleno.await();
			}

			numIPhone--;
			System.out.println("IPhone "+id+" se ha bajado de la barca.  Android: "+numAndroid+" IPhone: "+numIPhone);
			if (numAndroid==0 && numIPhone == 0){
				pAndroid = true;
				esperoAndroid.signalAll();
				pIPhone = true;
				esperoIPhone.signalAll();
				finViaje = false;
				System.out.println("**************************");
			}
		}finally{
			l.unlock();
		}
	}
	
	
	
	

}
