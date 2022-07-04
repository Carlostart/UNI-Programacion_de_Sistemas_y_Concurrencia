package ej1;

public class Hebra extends Thread {
	private char c;
	private int i;
	
	public Hebra(char car,int in) {
		c=car;
		i=in;
	}
	
	public void run() {
		for(int i=0;i<this.i;i++) {
			System.out.print(c);
		}
	}
}
