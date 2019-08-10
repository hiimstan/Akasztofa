package akasztofa.main;

public class Main {

	public static void main(String[] args) {
		System.out.println("Akasztófa");
		System.out.println("Adj meg egy betűt");
		
		Akasztofa akasztoFa = new Akasztofa("szavak.txt");
		boolean jatszik = true;
		
		while(jatszik) {
			akasztoFa.jatekCiklus();
			jatszik = akasztoFa.JatszikEMeg();
		}
		
	}

}
