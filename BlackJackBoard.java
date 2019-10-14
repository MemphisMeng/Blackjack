package blackjack;

import java.util.Scanner;
/**
 * 
 * @author Yicheng Li (U29503597)
 *
 */

class BlackJackBoard {
	public static float NOONE = -2;
	public static float NOTUSE = -3;
	/**
	 * Class BlackJackBoard includes all elements that a game of BlackJack needs 
	 */
	public static float DEALER = -1;
	private CardGroup cardgroup;
	private Player player[];
	private int player_num;
	private int player_stand[];
	/**
	 * Method welcome would create the variables, deliver workplace and the board after first two cards
	 */
	protected void welcome() {
		String name;
		int num = 0;
		int bets = 0;
		Scanner sc = new Scanner(System.in);
		cardgroup = new CardGroup();
		System.out.println("********************************************");
		System.out.println("Welcome to BlackJack1.0");
		System.out.printf("How many player do you have?");
		boolean valid_number = false;
		while (!valid_number || num <= 0 || num > 9) {
			String number; //Make sure the input here is integer
			number = sc.next();
			if (number.matches("[0-9]+")) {
				num = Integer.valueOf(number);
				if (num <= 0 || num > 5) {
					System.out.println("Sorry, the input should a number between 1~5.");
					System.out.println("Please input again:");
				}
				else {
					valid_number = true;
				}
			}
			else {
				System.out.println("Sorry, the input should a number between 1~5.");
				System.out.println("Please input again:");
			}
		}
		this.player_num = num;
		player_stand = new int[2 * num];
		player = new Player[num];
		for(int i = 0; i < num; i++) {
			System.out.println("********************************************");
			System.out.printf("Player %d, please enter your name.",i + 1);
			name = sc.next();
			System.out.printf("%n How many money will you bet?");
			do {
				bets = sc.nextInt();
				if(bets <= 0||bets > 10000)
				{
					System.out.println("Sorry, the number of money should be between 1~10000.");
					System.out.printf("Please input again:");
				}
			}while(bets <= 0||bets > 10000);
			player[i] = new Player(name,bets,(float) i);
		}
	}
	protected void round_start() {
		Scanner sc = new Scanner(System.in);
		for(int i = 0; i < 2 * this.player_num; i = i + 2) {
			this.player_stand[i] = 0;
			this.player_stand[i + 1] = 1;
		}
		for(int i = 0; i < this.player_num;i++) {
			float marker[][] = player[i].show_Marker();
			System.out.println("********************************************");
			System.out.println("New turn begin");
			System.out.printf("Player %s is your turn.%n",player[i].show_Name());
			System.out.println("********************************************");
			cardgroup.start_player(marker[0][0]);
			cardgroup.show_player(marker[0][0]);
			System.out.println("********************************************");
			System.out.print("Please make your bet.");
			while(player[i].bet(marker[0][0], sc.nextInt()) == false) {
				System.out.println("Sorry, you can only use money you have.");
				System.out.print("Please make your bet.");
			}
			if(cardgroup.check_split(marker[0][0])) {
				System.out.println("********************************************");
				System.out.print("Do you want to split?(Y/N)?");
				switch (sc.next().charAt(0)) {
				case 'Y':
					player[i].split(cardgroup.split((float) i));
					this.player_stand[i * 2 + 1] = 0;
					break;
				case 'y':
					player[i].split(cardgroup.split((float) i));
					this.player_stand[i * 2 + 1] = 0;
					break;
				}
			}
		}
		cardgroup.start_dealer();
	}
	/**
	 * Method that show the number of players
	 */
	protected int show_number() {
		return this.player_num;
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
		System.out.println("2.double up");
		System.out.println("3.stand");
		System.out.println("********************************************");
		int choice = 0;
		do {
			choice = sc.nextInt();
			if(choice <1 || choice > 5) {
				System.out.println("Sorry, you can only input integers between 1~5.");
			}
		}while(choice <1 || choice > 5);
		switch(choice) {
		case 1 :
			if(cardgroup.hit_player(marker) == false) {
				cardgroup.show_player(marker);
				System.out.println("Sorry, your card is bust.");
				this.stand(marker);
			}
			else {
				cardgroup.show_player(marker);
				if(cardgroup.show_value(marker) > 21) {
					System.out.println("Sorry, your card is bust.");
					this.stand(marker);
				}
				System.out.print("Please make your bet.");
				while(player[i].bet(marker, sc.nextInt()) == false) {
					System.out.println("Sorry, you can only use money you have.");
					System.out.print("Please make your bet.");
				}
				System.out.println("********************************************");
			}
			break;
		case 2 :
			if(player[i].doubleUp(marker) == false) {
				System.out.println("Sorry, you don't have enough money.");
			}
			else {
				this.stand(marker);
			}
			break;	
		case 3 :
			this.stand(marker);
			break;
		}
		if(player[i].show_Money() == 0) {
			float mark[][] = player[i].show_Marker();
			System.out.println("********************************************");
			System.out.println("Now you can't bet anymore as you run out of your money.");
			if(player_stand[2 * i] == 0) {
				stand(mark[0][0]);
				stand(mark[1][0]);
			}
			else {
				stand(mark[0][0]);
			}
		}
	}
	/**
	 * Add the player to a list that players want to stand
	 * @param marker 
	 */
	protected void stand(float marker) {
		player_stand[(int)(2 * marker)] = 1;
	}
	/**
	 * Check if every player of the game stop playing
	 * @return true when everyone stop
	 */
	protected boolean check_stop() {
		for(int i = 0; i < 2 * this.player_num; i = i + 2) {
			float marker[][] = player[(int)Math.floor((float)i/2)].show_Marker();
			if(this.player_stand[i] == 0 && marker[0][0] != NOTUSE) {
				return false;
			}
			if(this.player_stand[i + 1] == 0 && marker[1][0] != NOTUSE) {
				return false;
			}
		}
		return true;
	}
	/**
	 * The main circle of a round of game and ends when all players stop playing
	 */
	protected void round_player() {
		boolean label = true;
		while(label) {
			for(int i = 0; i < this.show_number(); i++) {
				if(player[i].show_Money() == 0) {
					if(player[i].if_second() == NOTUSE) {
						this.stand((float) i);
					}
					else {
						this.stand((float) i);
						this.stand((float) (i + 0.5));
					}
				}
				if(player[i].if_second() == NOTUSE) {
					if(player_stand[2 * i] == 0) {
						System.out.println("********************************************");
						System.out.printf("Player %s is your turn.%n",player[i].show_Name());
						System.out.printf("Now you have %d dollars.%n",player[i].show_Money());
						System.out.println("********************************************");
						float marker[][]=player[i].show_Marker();
						cardgroup.show_player(marker[0][0]);
						move(i,marker[0][0]);
					}
				}
				else {
					if(player_stand[2 * i] == 0) {
						System.out.println("********************************************");
						System.out.printf("Player %s is your first hand's turn.%n",player[i].show_Name());
						System.out.printf("Now you have %d dollars.%n",player[i].show_Money());
						System.out.println("********************************************");
						float marker[][]=player[i].show_Marker();
						cardgroup.show_player(marker[0][0]);
						move(i,marker[0][0]);
					}
					if(player_stand[2 * i + 1] == 0) {
						System.out.println("********************************************");
						System.out.printf("Player %s is your second hand's turn.%n",player[i].show_Name());
						System.out.printf("Now you have %d dollars.%n",player[i].show_Money());
						System.out.println("********************************************");
						float marker[][]=player[i].show_Marker();
						cardgroup.show_player(marker[1][0]);
						move(i,marker[1][0]);
					}
				}
			}
			if(check_stop() == true) {
				label = false;
			}
		}
		System.out.println("********************************************");
		System.out.println("Player's round ends. Now is dealer's round.");
		System.out.println("********************************************");
		while(cardgroup.show_value(DEALER) < 17) {
			cardgroup.hit_dealer();
		}
		cardgroup.show_dealer();
	}
	/**
	 * The method that find out the winner
	 */
	protected void round_win() {
		int winner_player = 0;
		float winner_marker = 0;
		int max = 0;
		for(int i = 0; i < this.player_num; i++) {
			float marker[][] = player[(int)Math.floor((float)i/2)].show_Marker();
			if(max < cardgroup.show_value(marker[0][0]) && cardgroup.show_value(marker[0][0]) < 22) {
				max = cardgroup.show_value(marker[0][0]);
				winner_marker = marker[0][0];
				winner_player = i;
			}
			if(max < cardgroup.show_value(marker[1][0]) && cardgroup.show_value(marker[1][0]) < 22) {
				max = cardgroup.show_value(marker[1][0]);
				winner_marker = marker[1][0];
				winner_player = i;
			}
		}
		if(max > cardgroup.show_value(DEALER) && cardgroup.show_value(DEALER) < 22) {
			System.out.println("********************************************");
			System.out.printf("The winner is %s.%n",player[winner_player].show_Name());
			System.out.println("********************************************");
			player[winner_player].winMoney(winner_marker);
		}
		else if(max != 0 && cardgroup.show_value(DEALER) > 22) {
			System.out.println("********************************************");
			System.out.printf("The winner is %s.%n",player[winner_player].show_Name());
			System.out.println("********************************************");
			player[winner_player].winMoney(winner_marker);
		}
		else if(max == cardgroup.show_value(DEALER) && cardgroup.show_value(DEALER) < 22) {
			System.out.println("********************************************");
			System.out.println("No winner.");
			System.out.println("********************************************");
			for(int i = 0; i < this.player_num; i++) {
				player[i].moneyBack();
			}
		}
		else if(max < cardgroup.show_value(DEALER) && cardgroup.show_value(DEALER) < 22){
			System.out.println("********************************************");
			System.out.println("The winner is DEALER.%n");
			System.out.println("********************************************");
		}
		else {
			System.out.println("********************************************");
			System.out.println("No winner.");
			System.out.println("********************************************");
			for(int i = 0; i < this.player_num; i++) {
				player[i].moneyBack();
			}
		}
	}
	/**
	 * The method that ends one round
	 */
	protected void round_end() {
		for(int i = 0 ;i < this.player_num; i++) {
			player[i].clearBet();
			System.out.println("********************************************");
			System.out.printf("Player %s",player[i].show_Name());
			System.out.printf(" now you have %d dollars.%n",player[i].show_Money());
		}
		cardgroup.shuffle();
	}
	protected boolean still_play() {
		for(int i = 0; i < this.player_num; i++) {
			if(player[i].show_Money() != 0) {
				return true;
			}
		}
		return false;
	}
	
}
