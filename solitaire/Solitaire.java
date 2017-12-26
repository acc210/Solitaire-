package solitaire;

import java.io.IOException;
import java.util.Scanner;
import java.util.Random;


/**
 * This class implements a simplified version of Bruce Schneier's Solitaire Encryption algorithm.
 * 
 * 
 */
public class Solitaire {

	/**
	 * Circular linked list that is the deck of cards for encryption
	 */
	CardNode deckRear;

	/**
	 * Makes a shuffled deck of cards for encryption. The deck is stored in a circular
	 * linked list, whose last node is pointed to by the field deckRear
	 */
	void makeDeck() {
		// start with an array of 1..28 for easy shuffling
		int[] cardValues = new int[28];
		// assign values from 1 to 28
		for (int i=0; i < cardValues.length; i++) {
			cardValues[i] = i+1;
		}

		// shuffle the cards
		Random randgen = new Random();
		for (int i = 0; i < cardValues.length; i++) {
			int other = randgen.nextInt(28);
			int temp = cardValues[i];
			cardValues[i] = cardValues[other];
			cardValues[other] = temp;
		}

		// create a circular linked list from this deck and make deckRear point to its last node
		CardNode cn = new CardNode();
		cn.cardValue = cardValues[0];
		cn.next = cn;
		deckRear = cn;
		for (int i=1; i < cardValues.length; i++) {
			cn = new CardNode();
			cn.cardValue = cardValues[i];
			cn.next = deckRear.next;
			deckRear.next = cn;
			deckRear = cn;
		}
	}

	/**
	 * Makes a circular linked list deck out of values read from scanner.
	 */
	void makeDeck(Scanner scanner) 
			throws IOException {
		CardNode cn = null;
		if (scanner.hasNextInt()) {
			cn = new CardNode();
			cn.cardValue = scanner.nextInt();
			cn.next = cn;
			deckRear = cn;
		}
		while (scanner.hasNextInt()) {
			cn = new CardNode();
			cn.cardValue = scanner.nextInt();
			cn.next = deckRear.next;
			deckRear.next = cn;
			deckRear = cn;
		}
	}

	/**
	 * Implements Step 1 - Joker A - on the deck.
	 */
	void jokerA() { 

		System.out.println("OG");
		printList(deckRear);

		int target = 27;
		CardNode prev = null;
		CardNode pointer = deckRear; 

		while(pointer.cardValue != target){
			prev= pointer;
			pointer = pointer.next;
		}

		CardNode secondL = deckRear;
		int x=0;
		while(x < 27){
			secondL = secondL.next;
			x++;
		}


		if(deckRear.cardValue == 27){ //joker is the last card

			CardNode temp = deckRear;
			CardNode temp2 = deckRear.next.next;
			deckRear=deckRear.next;
			deckRear.next = temp;
			deckRear.next.next =  temp2;
			secondL.next = deckRear;

		}else{
			CardNode temp= pointer.next.next;
			prev.next= pointer.next;
			prev.next.next = pointer;
			pointer.next= temp ;
		}
		System.out.println("jokerA");
		printList(deckRear);
	}

	//Step 2

	void jokerB() {
		int target = 28;
		CardNode prev = null;
		CardNode pointer = deckRear; 

		while(pointer.cardValue != target){
			prev= pointer;
			pointer = pointer.next; 
		}

		CardNode secondL = deckRear;
		int x=0;
		while(x < 27){
			secondL = secondL.next;
			x++;
		}
		CardNode swap = deckRear;
		int y=0;
		while(y< 3){
			swap=swap.next;
			y++;
		}

		if(prev==null){//joker is last 
			CardNode temp = deckRear.next.next;
			CardNode temp2 = deckRear;
			deckRear = deckRear.next;
			deckRear.next = temp;
			deckRear.next.next=temp2;
			deckRear.next.next.next= swap;
			secondL.next= deckRear;
		}else if(secondL.cardValue == 28){//secondtolast
			CardNode temp=deckRear;
			CardNode temp2 = deckRear.next;
			CardNode temp3 = deckRear.next.next;
			deckRear= temp2;
			deckRear.next=pointer;
			deckRear.next.next=temp3;
			prev.next=temp;
			temp.next=deckRear;			
		}else{
			CardNode temp = pointer.next.next.next;
			prev.next = pointer.next; 
			prev.next.next.next = pointer;
			pointer.next= temp; 
		}
		System.out.println("jokerb");
		printList(deckRear);
	}

	/**
	 * Implements Step 3 - Triple Cut - on the deck.
	 */
	void tripleCut() {
		CardNode prev = null;
		CardNode pointer = deckRear.next; 


		while((pointer.cardValue != 27)&& (pointer.cardValue != 28) ){
			prev = pointer;
			pointer = pointer.next; 
		}


		int target = 0;
		if(pointer.cardValue == 27){
			target = 28;
		}else{
			target= 27;
		}

		CardNode prior = null;
		CardNode pointer2 = deckRear; 

		while(pointer2.cardValue != target){
			prior = pointer2;
			pointer2= pointer2.next;
		}  

		int head = deckRear.next.cardValue;
		int rear = deckRear.cardValue;


		//if head is joker and rear is not
		if((rear != 27 || rear!=28) && (head==27 || head==28)){
			CardNode temp = deckRear;
			deckRear = pointer2;
			deckRear.next= pointer2.next;
			temp.next=pointer;
			prior.next=deckRear;
		}
		if((rear == 27 || rear==28) && (head!=27 || head!=28)){
			CardNode temp= deckRear.next;
			deckRear= prev;
			deckRear.next=pointer;
			pointer2.next=temp;	
		}
		if(rear != 27 && rear!=28){
			if(head!=27 && head!=28){
				CardNode temp= deckRear;
				CardNode temp2 = deckRear.next;
				deckRear=prev;
				deckRear.next=pointer2.next;
				temp.next=pointer;
				pointer2.next=temp2;
			}
		}
		System.out.println("triplecut");
		printList(deckRear);

	}

	/**
	 * Implements Step 4 - Count Cut - on the deck.
	 */
	void countCut() {		
		int value = deckRear.cardValue;
		int i =0;
		CardNode prior = null;
		CardNode pointer = deckRear;


		if(deckRear.cardValue != 28 && deckRear.cardValue != 27){
			while(i< value){
				pointer = pointer.next;
				prior = pointer.next;
				i++;
			}

			//finding the value of the second to last card
			int j =0;
			CardNode pointer2= deckRear;
			while(j < 27){
				pointer2 = pointer2.next; 
				j++;
			}

			CardNode temp = deckRear.next;
			deckRear.next = prior;
			pointer2.next = temp;
			pointer.next = deckRear;
		}	
		System.out.println("countcut");
		printList(deckRear);
	}


	/**
	 * Gets a key. Calls the four steps - Joker A, Joker B, Triple Cut, Count Cut, then
	 * counts down based on the value of the first card and extracts the next card value 
	 * as key. But if that value is 27 or 28, repeats the whole process (Joker A through Count Cut)
	 * on the latest (current) deck, until a value less than or equal to 26 is found, which is then returned.
	 * 
	 * @return Key between 1 and 26
	 */
	int getKey() {


		int key =0;
		do{
			jokerA();
			jokerB();
			tripleCut();
			countCut();

			int head = deckRear.next.cardValue;

			//if 28 change the value of it to 27
			if(head == 28){
				head = 27;
			}

			CardNode pointer= deckRear.next;
			int i = 1;
			while(i< head){
				pointer = pointer.next;
				i++;
			}


			CardNode keyNode = pointer.next;
			key = keyNode.cardValue;


		} while(key > 26);

		// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE

		System.out.println("key" + key); 

		return key;
	}

	/**
	 * Utility method that prints a circular linked list, given its rear pointer
	 * 
	 * @param rear Rear pointer
	 */
	public static void printList(CardNode rear) {
		if (rear == null) { 
			return;
		}
		System.out.print(rear.next.cardValue);
		CardNode ptr = rear.next;
		do {
			ptr = ptr.next;
			System.out.print("," + ptr.cardValue);
		} while (ptr != rear);
		System.out.println("\n");
	}

	/**
	 * Encrypts a message, ignores all characters except upper case letters
	 * 
	 * @param message Message to be encrypted
	 * @return Encrypted message, a sequence of upper case letters only
	 */
	public String encrypt(String message) {	

		char [] letters = new char [message.length()];
		String newMessage = "";

		//create an array of characters from message
		for(int i=0; i< message.length(); i++){
			letters[i]= message.charAt(i);
		}

		for(int i=0; i<letters.length; i++){
			if(Character.isLetter(letters[i])){ //check to see if letter
				if(Character.isUpperCase(letters[i])){ //check to see if it is uppercase
					newMessage += letters[i]; //add that character to new string
				}
			}
		}


		//this creates an array with the numbers that are keys 
		int [] numberOfLetters = new int[newMessage.length()];
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		for(int i=0; i< newMessage.length(); i++){
			for(int j=0; j< alphabet.length(); j++){
				if(newMessage.charAt(i) == alphabet.charAt(j)){ //if they have the same letter
					numberOfLetters[i]=j+1;
				}
			}
		}
	

		//creates an array of the keys
		int [] key = new int[numberOfLetters.length];
		int keys;
		for(int i=0; i< numberOfLetters.length; i++){
			keys = getKey();
			key[i] = keys;
		}
	
		//create an array for what we encrypted 
		int [] encryptNum = new int[key.length];
		for(int i=0; i< key.length; i++){
			int value = numberOfLetters[i] + key[i];
			if(value > 26){
				value = value-26;
				encryptNum[i]= value;
			}else{
				encryptNum[i]= value;
			}
		}

		//turn encryption into letters 
		String encrypt = "";
		char [] let = new char [encryptNum.length];

		for(int i=0; i< encryptNum.length; i++){
			int x = encryptNum[i]-1;
			let[i] = alphabet.charAt(x);
		}

		for(int j=0; j< let.length; j++){
			encrypt += let[j];
		}

		// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE

		return encrypt;
	}

	/**
	 * Decrypts a message, which consists of upper case letters only
	 * 
	 * @param message Message to be decrypted
	 * @return Decrypted message, a sequence of upper case letters only
	 */
	public String decrypt(String message) {	
		char [] letters = new char [message.length()];
		String newMessage = "";

		//create an array of characters from message
		for(int i=0; i< message.length(); i++){
			letters[i]= message.charAt(i);
		}

		for(int i=0; i<letters.length; i++){
			if(Character.isLetter(letters[i])){ //check to see if letter
				if(Character.isUpperCase(letters[i])){ //check to see if it is uppercase
					newMessage += letters[i]; //add that character to new string
				}
			}
		}
		//new message is the string that needs to be decrypted now 
		//change the letters into numbers
		int [] decryptNums = new int[newMessage.length()];
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		for(int i=0; i < newMessage.length(); i++){
			char c = newMessage.charAt(i);
			int x = alphabet.indexOf(c) + 1;
			decryptNums[i] = x;
		}


		//generate key from those numbers
		int[] keyNum = new int [decryptNums.length];
		for(int i=0; i<decryptNums.length; i++){
			int x = getKey();
			keyNum[i] = x;
		}

		//subtract letter number from key number
		int[] finalNum = new int [keyNum.length];
		for(int i=0; i<keyNum.length; i++){
			int x= decryptNums[i]-keyNum[i];
			if(keyNum[i] >=decryptNums[i] ){
				x = x+26;
				finalNum[i]=x;
			}else{
				finalNum[i]=x;
			}
		}

		//now re match the alphabet to it 
		char[] finalChar = new char [finalNum.length];

		for(int i=0; i< finalNum.length; i++){
			int x = finalNum[i]-1;
			char c = alphabet.charAt(x);
			finalChar[i]=c;
		}

		String decrypt = "";
		for(int i=0; i< finalChar.length; i++){
			decrypt += finalChar[i];
		}
		
		return decrypt;
	}
}
