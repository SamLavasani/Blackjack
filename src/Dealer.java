
public class Dealer {
	
	private int cardValue;
	private int gameCounter;
	private int hiddenCard;
	boolean isHiddenCardOpen = false;
	
	public int getHiddenCard() {
		return hiddenCard;
	}

	public void setHiddenCard(int hiddenCard) {
		this.hiddenCard = hiddenCard;
	}

	public int getCardValue() {
		return cardValue;
	}
	
	public void setCardValue(int cardValue) {
		this.cardValue = cardValue;
	}
	
	public int getGameCounter() {
		return gameCounter;
	}
	
	public void setGameCounter(int gameCounter) {
		this.gameCounter += 1;
	}
	
	void dealFirstCard(Cards deck,Player player, Dealer dealer) {

		//Get first cards from the deck, get total value and set new cards value for a player
		int playerCard1 = deck.getNextCard();
		int playerCard2 = deck.getNextCard();
		int playerValue = playerCard1 + playerCard2;
		player.setCardValue(playerValue);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(player.getName()+ " got a "+playerCard1+" and a "+playerCard2);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(player.getName()+"'s total is: "+player.getCardValue());
		System.out.println();

		//Get first and hidden card for a dealer, save hidden card and show his first card.
		int dealerCardOpen=deck.getNextCard();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Dealer got a "+dealerCardOpen+" and a hidden card.");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("His total is hidden too.");
		System.out.println();
		this.hiddenCard=deck.getNextCard();
		int dealerValue=dealerCardOpen;
		dealer.setCardValue(dealerValue);

	}
	
	void showHiddenCard() {
		
		//Present dealers hidden card and update his cards value 
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Dealer's hidden card was a "+this.hiddenCard);
		int newDealerValue = this.getCardValue() + this.hiddenCard;
		this.setCardValue(newDealerValue);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Dealers total is " + this.getCardValue());
		System.out.println();
		this.isHiddenCardOpen = true;
	}

	void dealPlayerCards(Player player, Cards cards) {
		
		//Get next card and present it
		int hitCardValue = cards.getNextCard();
		System.out.println(player.getName()+" draws a " + hitCardValue);

		//Update players value
		int currentPlayerValue = player.getCardValue();
		int newPlayerValue = currentPlayerValue + hitCardValue;
		player.setCardValue(newPlayerValue);

	}

	//Give the dealer his card and update his value
	void dealDealerCards(Cards deck) {
		
		//Get next card, present it and update dealers cards value
		int dealDealerCard1 = deck.getNextCard();
		int newDealerCardValue = dealDealerCard1 + this.getCardValue();
		this.setCardValue(newDealerCardValue);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Dealer chooses to hit.");
		
		//Small timeouts for better visual effect
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("The dealer got a " + dealDealerCard1 );
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Dealers total value is " + this.getCardValue());
		System.out.println();
	}
}
