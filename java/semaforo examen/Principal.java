package sushi;

public class Principal {
	
	public static void main(String[] args){
		  //número de clientes. El programa debe de funcionar correctamente con cualquier número de clientes
	    final int NUM_CLIENTES = 15;
	    //crear recurso compartido y hebras y lanzar hebras
	    
	    Bar b = new Bar();
	    Cliente[] c = new Cliente[NUM_CLIENTES];
	    for (int id=0;id<NUM_CLIENTES;id++) {
		    c[id] = new Cliente(id,b);
	    }
	    
	    for (int id=0;id<NUM_CLIENTES;id++) {
		    c[id].start();
	    }
	}
}
