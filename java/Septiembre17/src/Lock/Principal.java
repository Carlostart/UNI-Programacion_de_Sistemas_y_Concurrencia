package Lock;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Aseos[] id = new Aseos[10];
		BabuinoNS[] di = new BabuinoNS[10];
		Cuerda p = new Cuerda();
		
		for (int i = 0; i<id.length; i++){
			id[i] = new Aseos(i,p);
		}
		for (int i = 0; i<di.length; i++){
			di[i] = new BabuinoNS(i,p);
		}
		
		for (int i = 0; i<id.length; i++){
			id[i].start();;
		}
		for (int i = 0; i<di.length; i++){
			di[i].start();
		}
	}

}
