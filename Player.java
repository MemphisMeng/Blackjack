package blackjack;
/**
 * @author Anzhe Meng (U50590533)
 */
public class Player{
	public static float NOONE = -2;
	public static float NOTUSE = -3;
	/**
	 * The name of the player.
	 * It is the identity of the class.
	 */
	private String name;
	/**
	 * A container that contains the information of a player's first-hand cards
	 * first_hand[0] contains the marker of these cards.
	 * first_hand[1] contains the cash that is bet on this hand.
	 */
	private float[] first_hand = new float[2];
	/**
	 * Similar to first_hand, it represents the second hand.
	 */
	private float[] second_hand = new float[2];
	/**
	 * the entire cash that a player can gamble with
	 */
	private int money;
	
	protected float if_second() {
		return this.second_hand[0];
	}
	/**
	 * @param name
	 * @param cash initial gambling money
	 * @param num marker of the money
	 */
	public Player(String name, int cash, float num) {
		// Initialization
		this.name = name;

		this.money = cash;


		this.first_hand[0] = num; // marker of the cards in first hand
		this.first_hand[1] = 0.0f; // default bet on the first hand
		/**
		 * Because second hand doesn't appear at the very beginning, setting it as -1 just makes it distinguishable from the positive values.
		 */
		this.second_hand[0] = NOTUSE; 
		this.second_hand[1] = 0.0f; // default bet on the second hand
	}

	/**
	 * Accessor of both the first hand and second hand
	 */
	public float[][] show_Marker() {
		float[][] marker = new float[2][2];
		for (int i = 0; i < 2; i++) {
			marker[0][i] = first_hand[i];
		}

		for (int j = 0;j < 2; j++) {
			marker[1][j] = second_hand[j];
		}

		return marker;
	}
	public void plu_money(int money) {
		this.money  = this.money + money;
	}
	public void min_money(int money) {
		this.money  = this.money - money;
	}

	/**
	 * Accessor of the private variable money
	 * It represents how much the remaining gambling money is.
	 */
	public int show_Money() {
		return this.money;
	}

	/**
	 * Accessor of the private variable name
	 */
	public String show_Name() {
		return this.name;
	}

	/**
	 * the realization of the betting action.
	 * Always check whether the remaining money is enough for the next bet, and which hand should the money is bet on.
	 */
	public boolean bet(float marker, int money) {
		boolean betMade = false;

		if (money <= show_Money()) {
			if (marker == first_hand[0]) {
				first_hand[1] += money;
			}
			else if (marker == second_hand[0]) {
				second_hand[1] += money;
			}
			forfeitMoney(money);
			betMade = true;
		}

		return betMade;
	}

	/**
	 * check the availability of double up.
	 * Likewise, it needs the remaining money is enough.
	 */
	public boolean doubleUp(float marker) {
		boolean doubled = bet(marker, (int)this.first_hand[1]);

		return doubled;
	}

	/**
	 * the realization of the splitting action.
	 * @param marker1 the marker of the original hand cards, which are expected to be split.
	 * @param marker2 the marker of the second hand cards, which we want them to be labeled with
	 * @return the information of the new second hand
	 */
	public float[] split(float marker) {
		if (first_hand[1] <= show_Money()) {
			second_hand[0] = marker; 
			second_hand[1] = first_hand[1];
			forfeitMoney((int)second_hand[1]);
		}

		return this.second_hand;
	}

	/**
	 * In any round, the bet on the cards will be forfeited if the player loses.
	 * @param money the bet on any hand of cards
	 */
	public void forfeitMoney(int money) {
		this.money -= money;
	}

	/**
	 * first_hand[1]=0, second_hand[0], money = money + fist_hand[1] + second_hand
	 * If there is push/draw, the bet will be returned to each side.
	 * Also, the data in first_hand[1] and second_hand[1] (the place that records the bet amount) is reset to default.
	 */
	public void moneyBack() {
		this.money = this.money + (int)this.first_hand[1] + (int)this.second_hand[1];
		this.first_hand[1] = 0.0f;
		this.second_hand[0] = NOTUSE;
		this.second_hand[1] = 0.0f;
	}
	public void winMoney(float marker) {
		if (marker == first_hand[0]) {
			this.money += (int)(first_hand[1] * 2);
			clearBet();
		}
		else if (marker == second_hand[0]) {
			this.money += (int)(second_hand[1] * 2);
			clearBet();
		}
	}
	public void clearBet() {
		this.second_hand[0] = NOTUSE;
		this.first_hand[1] = this.second_hand[1] = 0.0f;
	}
}