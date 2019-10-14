package blackjack;

import java.util.Scanner;

public class TriantaEnaBoard extends BlackJackBoard{
	public static float MAX = 31;
	private TriantaEnaCardGroup TEcardgroup;
	private Player player[];
	private int player_num;
	private int player_stand[];
	private int banker;
	/**
	 * Method welcome would create the variables, deliver workplace and the board after first two cards
	 */
	TriantaEnaBoard() {
		TEcardgroup = new TriantaEnaCardGroup();
	}
	protected void welcome() {
		String name;
		int num = 0;
		int bets = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("********************************************");
		System.out.println("Welcome to Trianta Ena");
		System.out.printf("How many players do you have?");
		boolean valid_number = false;
		while (!valid_number || num <= 0 || num > 9) {
			String number; //Make sure the input here is integer
			number = sc.next();
			if (number.matches("[0-9]+")) {
				num = Integer.valueOf(number);
				if (num < 2 || num > 7) {
					System.out.println("Sorry, the input should a number between 2~7.");
					System.out.println("Please input again:");
				}
				else {
					valid_number = true;
				}
			}
			else {
				System.out.println("Sorry, the input should a number between 2~7.");
				System.out.println("Please input again:");
			}
		}
		this.player_num = num;
		player_stand = new int[num];
		player = new Player[num];
		System.out.printf("%n How much money does every player has?");
		valid_number = false;
		while ((!valid_number) || bets <= 0 || bets > 10000) {
			String number; //Make sure the input here is integer
			number = sc.next();
			if (number.matches("[0-9]+")) {
				bets = Integer.valueOf(number);
				if (bets <= 0 || bets > 10000) {
					System.out.println("Sorry, the number of money should be between 1~10000.");
					System.out.print("Please input again:");
				}
				else {
					valid_number = true;
				}
			}
			else {
				System.out.println("Sorry, the number of money should be between 1~10000.");
				System.out.print("Please input again:");
			}
		}
		for(int i = 0; i < num; i++) {
			System.out.println("********************************************");
			System.out.printf("Player %d, please enter your name.",i + 1);
			name = sc.next();
			if(i == num - 1) {
				System.out.printf("You are the Banker and now you have $%d %n",3 * bets);
				player[i] = new Player(name,3 * bets,(float) i);
				this.banker = i;
				break;
			}
			player[i] = new Player(name,bets,(float) i);
		}
	}
	protected void round_start() {
		Scanner sc = new Scanner(System.in);
		for(int i = 0; i < this.player_num;i++) {
			if(player[i].show_Money() <= 0) {
				this.stand((float) i);
			}
			if(i == this.banker) {
				continue;
			}
			float marker[][] = player[i].show_Marker();
			System.out.println("********************************************");
			System.out.println("New round begins");
			System.out.printf("Player %s is your turn.%n",player[i].show_Name());
			System.out.println("********************************************");
			TEcardgroup.start_player(marker[0][0]);
			TEcardgroup.show_player(marker[0][0]);
			System.out.println("********************************************");
			System.out.print("Please make your bet.");
			while(player[i].bet(marker[0][0], sc.nextInt()) == false) {
				System.out.println("Sorry, you can only use money you have.");
				System.out.print("Please make your bet.");
			}
		}
		TEcardgroup.start_dealer();
		for(int i = 0; i < this.player_num;i++) {
			if(i == this.banker) {
				continue;
			}
			
			// When a gambler runs out the money he can no longer bet.
			// This is similar to that he/she stands.
			if(player[i].show_Money() <= 0) {
				this.stand((float) i);
			}
			float marker[][] = player[i].show_Marker();
			System.out.println("********************************************");
			System.out.println("New turn begins");
			System.out.printf("Player %s, now is your  first turn.%n",player[i].show_Name());
			System.out.println("********************************************");
			TEcardgroup.start_two_player(marker[0][0]);
			TEcardgroup.show_player(marker[0][0]);
			System.out.println("********************************************");
			System.out.print("Please make your bet.");
			boolean valid_bet = false;
			int bet = 0;
			while (!valid_bet) {
				String _bet;//Make sure the input here is integer
				_bet = sc.next();
				if (_bet.matches("[0-9]+")) {
					bet = Integer.valueOf(_bet);
					valid_bet = true;
				}
				else {
					System.out.println("Sorry, you should input a number.");
					System.out.println("Please input again:");
				}
			}
			while(player[i].bet(marker[0][0], bet) == false) {
				System.out.println("Sorry, you can only use money you have.");
				System.out.print("Please make your bet.");
			}
		}
	}
	/**
	 * The main circle of a round of game and ends when all players stop playing
	 */
	protected void round_player() {
		boolean label = true;
		while(label) {
			for(int i = 0; i < this.show_number(); i++) {
				if(i == this.banker) {
					continue;
				}
				if(player[i].show_Money() <= 0) {
					this.stand((float) i);
				}
				if(player_stand[i] == 0) {
					System.out.println("********************************************");
					System.out.printf("Player %s, now is your turn.%n",player[i].show_Name());
					System.out.printf("Now you have $%d.%n",player[i].show_Money());
					System.out.println("********************************************");
					float marker[][]=player[i].show_Marker();
					TEcardgroup.show_player(marker[0][0]);
					move(i,marker[0][0]);
				}
			}
			if(check_stop() == true) {
				label = false;
			}
		}
		System.out.println("********************************************");
		System.out.println("Player's round ends. Now is dealer's round.");
		System.out.println("********************************************");
		while(TEcardgroup.show_value(DEALER) < 27) {
			TEcardgroup.hit_dealer();
		}
		TEcardgroup.show_dealer();
	}
	/**
	 * Method that ask the player what he/she want to do
	 * @param i implies the number of the player
	 * @param marker implies the marker of the player's card
	 */
	protected void move(int i,float marker) {
		Scanner sc = new Scanner(System.in);
		System.out.println("********************************************");
		System.out.printf("Player %s ", player[i].show_Name());
		System.out.println("now make your move.");
		System.out.println("1.hit");
		System.out.println("2.stand");
		System.out.println("********************************************");
		int choice = 0;
		boolean valid_input = false;
		while (!valid_input || choice <1 || choice > 2) {
			String _choice;
			//Make sure the input here is integer
			_choice = sc.next();
			if (_choice.matches("[0-9]+") ) {
				choice = Integer.valueOf(_choice);
				if (choice <1 || choice > 2) {
					System.out.println("Sorry, you can only input integers between 1~2.");
					System.out.print("Please input again:");

				}
				else {
					valid_input = true;
				}
			}
			else {
				System.out.println("Invalid number input! Please type again!");
			}
		}
		switch(choice) {
		case 1 :
			if(TEcardgroup.hit_player(marker) == false) {
				TEcardgroup.show_player(marker);
				System.out.println("Sorry, your card is bust.");
				this.stand(marker);
			}
			else {
				TEcardgroup.show_player(marker);
				System.out.print("Please make your bet.");
				boolean valid_bet = false;
				int bet = 0;
				while (!valid_bet) {
					String _bet;//Make sure the input here is integer
					_bet = sc.next();
					if (_bet.matches("[0-9]+")) {
						bet = Integer.valueOf(_bet);
						valid_bet = true;
					}
					else {
						System.out.println("Sorry, you should input a number.");
						System.out.println("Please input again:");
					}
				}
				while(player[i].bet(marker, bet) == false) {
					System.out.println("Sorry, you can only use money you have.");
					System.out.print("Please make your bet.");
				}
				System.out.println("********************************************");
			}
			break;
		case 2 :
			this.stand(marker);
			break;
		}
		if(player[i].show_Money() == 0) {
			float mark[][] = player[i].show_Marker();
			System.out.println("********************************************");
			System.out.println("Now you can't bet anymore as you run out of your money.");
			stand(mark[0][0]);
		}
	}
	/**
	 * Add the player to a list that players want to stand
	 * @param marker 
	 */
	protected void stand(float marker) {
		player_stand[(int)(marker)] = 1;
	}
	/**
	 * Check if every player of the game stop playing
	 * @return true when everyone stop
	 */
	protected boolean check_stop() {
		for(int i = 0; i <this.player_num; i = i + 1) {
			if(i == this.banker) {
				continue;
			}
			if(this.player_stand[i] <= 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param i the index of the banker in the current round
	 */
	protected void be_banker(int i) {
		this.banker = i;
	}
	
	/**
	 * Here we try to fine the player who has the most money at present and
	 * ask whether or not he/she wants to be the banker
	 */
	protected void select_banker() {
		int max = -1;
		float max_money = 0;
		for(int i = 0; i < this.player_num; i++) {
			float marker[][] = player[i].show_Marker();
			if(marker[0][1] > max_money) {
				max = i;
			}
		}
		System.out.println("********************************************");
		System.out.printf("Player %s now you are the banker.%n",player[max].show_Name());
		this.be_banker(max);
	}
	
	protected void banker_oprate(int op,int money) {
		if(op == 0) {
			for(int i = 0; i < this.player_num; i++) {
				if(i == this.banker) {
					player[i].plu_money(money);
					break;
				}
			}
		}
		else {
			for(int i = 0; i < this.player_num; i++) {
				if(i == this.banker) {
					player[i].min_money(money);
					break;
				}
			}
		}
	}
	
	/**
	 * The method that finds out the winner
	 */
	protected void round_win() {
		int winner_player = 0;
		int banker_value = TEcardgroup.show_value(DEALER);
		
		// When the cards of the dealer are bust, the players whose cards are not bust win
		// But the dealer would still forfeit the bets from those whose cards are bust.
		if(banker_value > MAX) {
			for(int i = 0; i < this.player_num; i++) {
				if(i == this.banker) {
					continue;
				}
				float marker[][] = player[i].show_Marker();
				if(TEcardgroup.show_value(marker[0][0]) <= MAX) {
					System.out.println("********************************************");
					System.out.printf("%s is winner.%n",player[i].show_Name());
					System.out.println("********************************************");
					winner_player++;
					if(this.win(i) == false) {
						break;
					}
				}
				else {
					player[this.banker].plu_money((int)marker[0][1]);
				}
			}
		}
		else {
			for(int i = 0; i < this.player_num; i++) {
				float marker[][] = player[i].show_Marker();
				if(banker_value < TEcardgroup.show_value(marker[0][0]) && TEcardgroup.show_value(marker[0][0]) <= MAX) {
					System.out.println("********************************************");
					System.out.printf("%s is winner.%n",player[i].show_Name());
					System.out.println("********************************************");
					winner_player++;
					if(TEcardgroup.show_value(marker[0][1]) <= MAX) {
						if(this.win(i) == false) {
							break;
						}
					}
				}
				else {
					player[this.banker].plu_money((int)marker[0][1]);
				}
			}
		}
		if(winner_player == 0) {
			System.out.println("********************************************");
			System.out.println("The winner is DEALER.%n");
			System.out.println("********************************************");
		}
	}
	
	/**
	 * The method that ends one round
	 */
	protected void round_end() {
		Scanner sc = new Scanner(System.in);
		for(int i = 0 ;i < this.player_num; i++) {
			if(this.player_stand[i] == 1) {
				this.player_stand[i] = 0;
			}
			player[i].clearBet();
			System.out.println("********************************************");
			System.out.printf("Player %s",player[i].show_Name());
			System.out.printf(" now you have $%d.%n",player[i].show_Money());
		}
		int max = 0;
		int max_money =0;
		for(int i = 0 ;i < this.player_num; i++) {
			if(player[i].show_Money() > max_money) {
				max = i;
				max_money =player[i].show_Money();
			}
		}
		boolean banker_found = false;
		if (max != this.banker) {
			if(!banker_found) {
				System.out.println("********************************************");
				System.out.printf("Player %s do you want to be the banker?",player[max].show_Name());
				String answer = sc.next();
				boolean valid_input = false;
				while (!valid_input) {
					if (answer.equalsIgnoreCase("yes")|| answer.equalsIgnoreCase("no")) {
						valid_input = true;
					}
					else {
						System.out.print("Invalid input! Please type again!");
					}
				}
				if (answer.equalsIgnoreCase("yes")) {
					this.banker = max;
					banker_found = true;
				}
				else {
					max = (max + 1) % this.player_num;
				}
			}
		}
		TEcardgroup.shuffle();
	}
	
	protected boolean win(int i) {
		float marker[][] = player[i].show_Marker();
		if(marker[0][1] > player[this.banker].show_Money()) {
			System.out.println("********************************************");
			System.out.println("The banker is broken.");
			System.out.println("********************************************");
			player[i].plu_money(player[this.banker].show_Money());
			player[this.banker].min_money(player[this.banker].show_Money());
			this.select_banker();
			return false;
		}
		else {
			player[i].winMoney(marker[0][0]);
			player[this.banker].min_money((int)marker[0][1]);
		}
		return true;
	}
	
	protected boolean still_play() {
		for(int i = 0; i < this.player_num; i++) {
			if(player[i].show_Money() != 0) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Method that show the number of players
	 */
	protected int show_number() {
		return this.player_num;
	}
}
