
public class Player {
	private String name;
	private int wins, losses, cardValue, totalMoney, betMoney;
	boolean playerStay = false;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getWins() {
		return wins;
	}
	
	public void setWins(int wins) {
		this.wins = wins;
	}
	
	public int getLosses() {
		return losses;
	}
	
	public void setLosses(int losses) {
		this.losses = losses;
	}
	
	public int getCardValue() {
		return cardValue;
	}
	
	public void setCardValue(int cardValue) {
		this.cardValue = cardValue;
	}
	
	public int getMoney() {
		return totalMoney;
	}
	
	public void setMoneyValue(int moneyValue) {
		this.totalMoney = moneyValue;
	}
	
	public int getBetMoney() {
		return betMoney;
	}
	
	public void setBetMoneyValue(int betMoneyValue) {
		this.betMoney = betMoneyValue;
	}
	
	//Update how many times player have won.
	void updateWinCounter() {
		this.wins += 1;
	}
	
	//Update how many times player have lost. 
	void updateLoseCounte() {
		this.losses += 1;
	}
}
