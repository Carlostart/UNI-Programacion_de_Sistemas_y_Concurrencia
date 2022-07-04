package ej5;

public class ej5 {

	public static void main(String[] args) throws Exception {
		try {
			Fib f = new Fib(10);
			f.start();
			f.join();
			System.out.println(f.answer);
		} catch (Exception e) {
			System.out.println("usage: java Fib NUMBER");
		}
	}

}
