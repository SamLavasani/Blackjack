import java.util.NoSuchElementException;
import java.util.Scanner;

public class Game {
	
	private final int blackJack = 21;
	private Dealer dealer;
	private Player player;
    private Cards cards;
    Scanner scan;
	
	public static void main(String[] args) {

		//Initiate user
		new Game();
	}
	
	Game() {
		System.out.println("Welcome to project #6 blackjack game");
		dealer = new Dealer(); //It should always be the same dealer in one game
		this.userInit();
	}
	
    void userInit() {
    	
		//Declare all objects
    		player = new Player();
    		cards = new Cards();
    		
        //Get and set user name
        setUserName();
        
        //Set user money
        setUserMoney();

    }
    
    void setUserName() {
    		System.out.println("Write your name:");
    		scan = new Scanner(System.in);
        player.setName(scan.nextLine());
        System.out.println("Your name is: "+player.getName());
    }
    
    void setUserMoney() {
    		
    		scan = new Scanner(System.in);
    		System.out.println("Enter money amount you want to play with:");
        
    		try {
    			player.setMoneyValue(scan.nextInt());
    			
		} catch (Exception e) {
			
			System.out.println("Please enter numbers instead of text!");
			setUserMoney();
			return;
		}
    		
    		if (player.getMoney() <= 0) {
    			
    			System.out.println("You must enter a amount above 0.");
    			setUserMoney();
    		}
    		
    		System.out.println("Your money value is: " + player.getMoney());
    		askUserToBet();
    }
        
    void askUserToBet() {
    	
    		int userWantToBet = 0;
    		int userTotalMoneyValue = player.getMoney();
    		
    		//Check if player has money, else ask to add more
    		if (userTotalMoneyValue == 0) {
    			System.out.println("Sorry, you need more funds!");
    			setUserMoney();
    		}
    	
    		//Ask user to bet
    		scan = new Scanner(System.in);
    		System.out.println("How much money do you want to bet?");
 
		try {
			userWantToBet = scan.nextInt();
			
		} catch (Exception e) {
			
			System.out.println("Please enter a number!");
			askUserToBet();
		}
		if(userWantToBet <= 0) {
			System.out.println("You must bet over 0.");
			askUserToBet();
		}
		
		//Check if player has enough money to play
		if (userTotalMoneyValue >= userWantToBet) {
			
			//Take away bet money from total
			int newMoneyTotal = userTotalMoneyValue - userWantToBet;
			player.setMoneyValue(newMoneyTotal);
			player.setBetMoneyValue(userWantToBet);
			System.out.println("You betted " +userWantToBet);
			
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Your new total amount is " + newMoneyTotal);
			System.out.println();
			
			//Initiate the game
		    init();
			
		} else {
			
			//Player doesn't have enough money to bet
			System.out.println("You do not have enough money to bet");
			System.out.println();
			System.out.println("Try smaller amount!");
			askUserToBet();
		}
    }
    
    void init() {
    
		//Create and shuffle cards
	    cards.createShuffledDeck();
	    
	    //Update and show game counter
	    dealer.setGameCounter(dealer.getGameCounter() + 1);
	    
	    //Deal first cards and save the result
	    dealer.dealFirstCard(cards, player,dealer);
	
	    //Calculate all values and decide what to do next
	    checkValues();
	}

	void checkValues() {

		//Get players and dealers values
        int playerValue = player.getCardValue();
	    int dealerValue = dealer.getCardValue();

	    //If player or dealer got blackjack or went above, decide who won
	    if (playerValue >= blackJack || dealerValue >= blackJack){
	    		decideWinner();
        }
        else {

        		//If not, choose who's turn it is now
	        if (player.playerStay==true){
	            dealersTurn();
	            
            } else {
	            playersTurn();
            }
        }
    }

    void hit() {
    	
    		//Give player new card, set new value and calculate values
	    dealer.dealPlayerCards(player,cards);
	    try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Your total is now: "+player.getCardValue());
        System.out.println();
        checkValues();
    }

    void stay() {
    	
      	//Dealer turn text
     	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Alright, you choose to stay.");
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Dealers turn.");
        System.out.println();

        //Change flag that user did choose "stay" option
        player.playerStay = true;
        dealersTurn();

    }
    
    void playersTurn() {
        
        String playerChoice;
        
        //Get players choice to hit or stay
        try {
			Thread.sleep(1500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        System.out.println("Would you like to \"hit\" or \"stay\"?");
        scan = new Scanner(System.in);

        //Get user's choice
        try{
            playerChoice = scan.next();
        } catch (NoSuchElementException e){
            playersTurn();
            return;
        }

        //Choose what to do depends on users input. If input fails, repeat this function.
        if (playerChoice.equalsIgnoreCase("hit")){
        	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            hit();
            return;
            
        } else if (playerChoice.equalsIgnoreCase("stay")){
            stay();
            return;
            
        } else {
            System.out.println("You can only choose hit or stay, please type again");
            playersTurn();
            return;
        }
    }

    void dealersTurn(){
    	
    		//Check that dealer has value of 16 or less to make a turn.
        if (dealer.getCardValue() <= 16) {

        		//Check if hidden card is already open. If not, open it first.
            if (dealer.isHiddenCardOpen) {
            	
                dealer.dealDealerCards(cards);
                checkValues();
                
            } else {
                dealer.showHiddenCard();
                checkValues();
            }
            
        } else {
        		//If more then 16 points then choose winner
        	try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        		System.out.println("Dealer stays.");
            decideWinner();
        }
    }

	void decideWinner() {
		
		//Get players and dealers values
		int playersValue = player.getCardValue();
		int dealersValue = dealer.getCardValue();

		if (playersValue == blackJack) {
			
			//Player wins with blackjack
			System.out.println(player.getName()+" got Black Jack!");
			gamePayOut(3);
			this.endGame(true);
			
		} else if (playersValue > dealersValue && playersValue < blackJack) {
			
			//Player wins with higher value than dealer
			gamePayOut(2);
			this.endGame(true);
			
		} else if (playersValue > blackJack) {
			
			//Player loses
			gamePayOut(0);
			System.out.println(player.getName()+" got bust!");
			this.endGame(false);
			
		} else if (playersValue == dealersValue) {
			
			//Player loses
			gamePayOut(0);
			this.endGame(false);
			
		} else if (dealersValue == blackJack) {
			
			//Player loses
			gamePayOut(0);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Dealer got BlackJack!");
			this.endGame(false);
			
		} else if (dealersValue > blackJack) {
			
			//Player wins
			gamePayOut(2);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Dealer got bust!");
			this.endGame(true);
			
		} else if (dealersValue > playersValue && dealersValue < blackJack) {
			//Player loses
			gamePayOut(0);
			this.endGame(false);
		}
	}
	
	void gamePayOut(int multiplier) {
		
		//Get bet and total money
		int betValue = player.getBetMoney();
		int playerTotalMoney = player.getMoney();
		
		//Calculate and set new total amount
		int newTotalMoney = (betValue * multiplier) + playerTotalMoney;
		player.setMoneyValue(newTotalMoney);
	}
	
	void endGame(boolean playerWin) {
		
		//Show game statistics
		System.out.println();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(player.getName()+" has "+player.getCardValue()+".");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Dealer has "+dealer.getCardValue()+".");
		System.out.println();
		
		//Show who won in this game
		if (playerWin) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(player.getName()+" wins!");
			player.updateWinCounter();
			
		} else if (!playerWin) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Dealer wins!");
			player.updateLoseCounte();
		}
		
		//Show player statistics
		System.out.println(player.getName()+" has "+player.getWins()+" wins and "+player.getLosses()+" losses.\n");
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Show user money
		System.out.println("Your new total money value is: " + player.getMoney());
		
		//show dealer gamecount
		System.out.println("This is dealers game nr "+dealer.getGameCounter());
		
		//Ask if user wants to play one more game.
		System.out.println("Would you like to play again as "+player.getName()+"? \"y\"/\"n\"");
		
		//If yes, reset game flags and start again
		scan = new Scanner(System.in);
		String choice = scan.nextLine();
		
		if (choice.equals("y")) {
			player.playerStay=false;
			dealer.isHiddenCardOpen=false;
			askUserToBet();
			
		} else if (choice.equals("n")) {
			
			//If no ask if user want to change player and reset game
			System.out.println("Create new user or quit? \"u\"/\"q\"");
			scan = new Scanner(System.in);
			String choice2 = scan.nextLine();
			
			if (choice2.equals("u")) {
				this.userInit();
				
			} else if(choice2.equals("q")) {
				System.out.println("Thank you for playing!\nGoodbye!");
			}
		}
	}
}
