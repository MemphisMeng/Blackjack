package blackjack;

/**
 * @author Anzhe Meng (U50590533)
 */
public class Card{
	public static float NOONE = -2;
	/**
	 * "num" and "rank" are somehow the same, both of them stands for the points of one card.
	 * The reason why we set up two different variables is that in game Blackjack some cards have the same values.
	 * So we handle them the face value and the real value in this game seperately.
	 * We won't change the face value of a card in this game (and neither can we in other card games), so it is safe for the following values to be declared final.
	 */
	private int num;
	private int rank;
	/**
	 * It is used for the index of the array SUITS.
	 */
	/*private final int suit;*/
	private String suit;

	/**
	 * Marker of a card, it reflects to whom the card currently belong.
	 * When marker is equal to NOONE, it belongs to nobody.
	 */
	private float marker;

	public static final int[] NUM = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13}; // For convenience we set the index 1 as the starting position. Likewise in the array RANKS.
	public static final String[] RANKS  = {null, "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
	public static final String[] SUITS = {"Diamonds", "Clubs", "Hearts", "Spades"};
	/**
	 * Track whether the face of a card is up ("faced").
	 * True when it is up or false down.
	 */
	private boolean faced;
	
	/**
	 * It determines which card group this card really belongs to.
	 */
	private int belong;

	/**
	 * A no-argument constructor, simply set all digital values of a card to be zero.
	 */
	public Card() {
		this.num = 0;
		this.rank = 0;
	}

	/**
	 * @param num initialize the value of the card
	 * @param suit initialize the suit of the card
	 * @return Card
	 */
	/*public Card(int num, String suit){
		this.rank = num;
		this.suit = suit;
		if (num <= 10 && num >= 2) {
			this.num = num;
		}
		else if (num > 10 && num <= 13) {
			this.num = 10;
		}
		else if (num == 1) {
			this.num = 11;
		}
		this.num = num;
	}*/
	Card(int num, String suit) {
		this.num = num;
		this.suit = suit;
		this.marker = NOONE;
		this.faced = false;
	}

	/**
	 * Overriden toString()
	 */
	/*public String toString(){
		return RANKS[this.rank] + " of " + SUITS[this.suit];
	}*/

	/**
	 * print out the face of a card
	 */
	/*public static void print() {
		System.out.println(RANKS[this.rank] + " of " + SUITS[this.suit]);
    }*/

	/**
	 * In a standard deck there should be a certain position for every card in the ranking.
	 * As the RANKS & SUITS defined above implicates, we determines that the suit rank from the low to high should be Diamonds, Clubs, Hearts, Spades.
	 * Meanwhile, the number rank should be Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King
	 * @param that the other card that needs to be compared
	 * @return 0 when the cards are totally the same,-1 when this card ranks upper than that one, 1 when the card ranks lower than that one
	 */
	/*public int compareTo(Card that){
		if (this.suit < that.suit){
			return -1;
		}
		if (this.suit > that.suit){
			return 1;
		}
		if(this.rank < that.rank){
			return -1;
		}
		if(this.rank > that.rank){
			return 1;
		}
		return 0;
	}*/

	/**
	 * Accessor of the private variable rank
	 */
	public String getRank(){
		return RANKS[this.rank];
	}

	/**
	 * Accessor of the private variable suit
	 */
	/*public String getSuit(){
		return RANKS[this.suit];
	}*/

	/**
	 * Accessor of the private variable faced
	 */
	public boolean getFaced() {
		return this.faced;
	}
	
	protected String get_suit() {
		return this.suit;
	}

	/**
	 * Accessor of the private variable num
	 */
	/*public int get_num() {
		return this.num;
	}*/
	protected int show_num() {
		return this.num;
	}

	/**
	 * Accessor of the private variable belong
	 */
	public int getBelong() {
		return this.belong;
	}

	/**
	 * Accessor of the private variable marker
	 */
	public float getMarker() {
		return this.marker;
	}

	/**
	 * Mutator of the private variable faced
	 * Because it is boolean, we just simply set it as its opposite.
	 */
	public void setFaced() {
		this.faced = (this.faced == false) ? true : false;
	}

	/**
	 * Mutator of the private variable belong
	 */
	public void set_belong(int belong) {
		this.belong = belong;
	}

	/**
	 * Mutator of the private variable marker
	 */
	public void set_marker(float marker) {
		this.marker = marker;
	}
	
	/**
	 * check whether two cards are identical.
	 */
	/*public boolean sameCard(Card c1, Card c2) {
		return (c1.suit == c2.suit && c1.rank == c2.rank);
	}*/
	protected float get_marker() {
		return this.marker;
	}
}