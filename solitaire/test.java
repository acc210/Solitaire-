package solitaire;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class test {

	public static void main(String[] args) throws IOException {
		
		Solitaire ss = new Solitaire();
		 
	
		Scanner sc = new Scanner(new File("test"));
		ss.makeDeck(sc);
		

		System.out.println("this is the original deck:");
		Solitaire.printList(ss.deckRear);
//		
//		System.out.println("Joker A");
//		ss.jokerA(); 
//		Solitaire.printList(ss.deckRear);
//		
//		System.out.println("Joker B");
//		ss.jokerB();  
//		Solitaire.printList(ss.deckRear);
		
		System.out.println("triple cut");
		ss.tripleCut();
		Solitaire.printList(ss.deckRear);
//		
//		System.out.println("countCut");
//		ss.countCut();
//		Solitaire.printList(ss.deckRear);
//		
//	 
//
//		System.out.println("this is the original deck:");
//		Solitaire.printList(ss.deckRear);
//		
//		System.out.println("Joker A");
//		ss.jokerA(); 
//		Solitaire.printList(ss.deckRear);
//		
//		System.out.println("Joker B");
//		ss.jokerB();  
//		Solitaire.printList(ss.deckRear);
//		
//		System.out.println("triple cut");
//		ss.tripleCut();
//		Solitaire.printList(ss.deckRear);
//		
//		System.out.println("countCut");
//		ss.countCut();
//		Solitaire.printList(ss.deckRear);
//	
//		 
//		System.out.println("this is the original deck:");
//		Solitaire.printList(ss.deckRear);
//		
//		System.out.println("Joker a");
//		ss.jokerA();  
//		Solitaire.printList(ss.deckRear);
	

		
	} 
 
}
