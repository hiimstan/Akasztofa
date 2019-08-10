package akasztofa.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;



public class Akasztofa {

	private static final char NULL = 0;
	private Scanner scanner = new Scanner(System.in);
	private List<String> szavakLista = new ArrayList<String>();
	private List<Character> betukLista = new ArrayList<Character>();
	private StringBuilder strb = new StringBuilder();
	private int helyesTalalatok = NULL;
	private int rosszTalalatok = NULL;
	private int elet = 6;
	private String helyesSzo = null;
	
	
	public Akasztofa(String file) {
		try {
			szoBeolvasas(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		helyesSzo = szoValasztas().toLowerCase();
	}
	
	public int randomKeres(int min, int max) {
		
		Random rand = new Random();
		
		int random = rand.nextInt((max - min) + 1) + min;
		
		return random;
	}
	
	public char inputKeres() {
		
		boolean inputKeres = true;
		char karakter = NULL;
		
		while (inputKeres) {
			String tempChar = scanner.nextLine().toLowerCase();
				if (tempChar.length() > 1) {
					System.out.println("Csak egy betűt adhatsz meg!");
				} else if (!(Character.isLetter(tempChar.charAt(0)))) {
					System.out.println("Csak betűt adhatsz meg!");
				} else {
					karakter = tempChar.charAt(0);
					inputKeres = false;
				}
			
			}
		return karakter;
	}
	
	public void szoBeolvasas(String file) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(new File(file)));
		String sor;
		while ((sor = br.readLine()) != null) {
			szavakLista.add(sor);
		}
		
		br.close();
	}
	
	private String szoValasztas() {
		 int max = szavakLista.size();
		 
		String kivalasztottSzo = szavakLista.get(randomKeres(1 , max));
		
		return kivalasztottSzo;
	}
	
	private void SzoEpites() {
		strb.setLength(helyesSzo.length() * 2);
		
		for (int i = 0; i < strb.length(); i += 2) {
			strb.setCharAt(i, '_');
			strb.setCharAt(i+1, ' ');
		}
		
	}
	
	private void BetuTalalgatas(char karakter) {
		boolean talalat = false;
		if (!betukLista.contains(karakter)) {
			betukLista.add(karakter);
			for (int i = 0; i < helyesSzo.length(); i++) {
					if (karakter == helyesSzo.charAt(i)) {
						strb.setCharAt(i * 2, karakter);
						talalat = true;
						JoTalalat();
					}
			}
			if (!talalat) {
				RosszTalalat();
			}
			} else {
				System.out.println("Ezzel a karakterrel már próbálkoztál!");
			}
	}
	
	public boolean JatszikEMeg() {
		System.out.println("Szeretnél mégegyszer játszani? Ha igen írj be egy 'I'-t, ha pedig abbahagynád, akkor írj be egy 'N'-t.");
		while (true) {
			char kar = inputKeres();
			if (kar == 'i') {
				System.out.println(kar);
				return true;
			} else if (kar == 'n') {
				System.out.println(kar);
				return false;
			} else {
				System.out.println(kar);
				System.out.println("Csak 'I' vagy 'N' karaktert fogadok el!");
			}
		}
	}
	
	private void RosszTalalat() {
		
		rosszTalalatok++;
		EmberRajz();
	}
	
	private void EmberRajz() {
			if (rosszTalalatok >= 1) {
				System.out.println("   |");
				System.out.println("   |");
				System.out.println("   O");
			} 
			if (rosszTalalatok >= 4) {
				System.out.println("  \\|/");
			} else if (rosszTalalatok >= 3) {
				System.out.println("  \\|");
			} else if (rosszTalalatok >= 2) {
				System.out.println("   |");
			}
			if (rosszTalalatok >= 6) {
				System.out.println("  / \\");
			} else if (rosszTalalatok >= 5) {
				System.out.println("  /");
			}
	}
	
	private void JoTalalat() {
		helyesTalalatok++;
	}
	
	private void Nyertes() {
		System.out.println("Gratulálok! Sikerült megfejtened a rejtett szót!");
	}
	
	private void Vesztes() {
		System.out.println("Vesztettél! Sajnos nem sikerült kitalálnod a megfejtést. \nA helyes megfejtés: " + helyesSzo);
	}
	
	private void init() {
		betukLista.clear();
		rosszTalalatok = 0;
		helyesTalalatok = 0;
	}
	
	public void jatekCiklus() {
			init();
			SzoEpites();			
		//System.out.println(helyesSzo + "\n");
		System.out.println("\n" + strb + "\n");
	
			while (true) {
				if (helyesTalalatok >= helyesSzo.length()) {
					Nyertes();
					break;
				}
				else if (rosszTalalatok >= elet) {
					Vesztes();
					break;
				}
					char karakter = inputKeres();
					BetuTalalgatas(karakter);
					System.out.println("\n" + strb + "\n");	
			}
	}
}
