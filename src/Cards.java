import java.util.Collections;
import java.util.LinkedList;

public class Cards {
	
	private LinkedList<Integer> deck;
	
	void createShuffledDeck() {
		
		deck = new LinkedList<Integer>();
		
		//Add numbers 2-11 to the deck
		for (int i = 2; i <=11; i++) {
			for (int j = 0; j < 4; j++) {
				deck.add(i);
			}
		}
		
		//Shuffle cards
		Collections.shuffle(deck);
	}
	
	//Get the first card
	int getNextCard() {
		int card=deck.get(0);
		//Remove card from deck
		this.removeCard();
		return card;
	}
	
	//Remove card
	void removeCard() {
		deck.remove(0);
	}
}
