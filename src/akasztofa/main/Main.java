package akasztofa.main;

import java.awt.EventQueue;

import akasztofa.display.Display;

public class Main {

	public static void main(String[] args) {
		System.out.println("Akasztófa");
		System.out.println("Adj meg egy betűt");
		
		Akasztofa akasztoFa = new Akasztofa("szavak.txt");
		boolean jatszik = true;
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Display frame = new Display();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		while(jatszik) {
			akasztoFa.jatekCiklus();
			jatszik = akasztoFa.JatszikEMeg();
		}
		
	}

}
