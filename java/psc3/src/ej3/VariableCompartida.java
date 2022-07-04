package ej3;

public class VariableCompartida {
	private int v;
	
	public VariableCompartida(int var) {
		v=var;
	}
	
	public int get() {
		return v;
	}
	
	public void set(int var) {
		v=var;
	}
	
	public void inc() {
		v++;
	}
}
