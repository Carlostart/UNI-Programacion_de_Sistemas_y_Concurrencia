package ej2;

public class Hebra extends Thread {
	private VariableCompartida vc;
	
	public Hebra(VariableCompartida var) {
		vc=var;
	}
	
	public void run(){
		for(int i=0;i<100;i++) {
			vc.inc();
		}
	}
	
	public VariableCompartida get() {
		return vc;
	}
}
