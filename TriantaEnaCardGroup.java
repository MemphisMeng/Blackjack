package blackjack;

public class TriantaEnaCardGroup extends CardGroup{
	/**
	 * To deliver the first two cards to one player.
	 * @param marker implies the owner of those two cards.
	 */
	protected void start_player(float marker) {
		for(int i = 0;i < 52; i++) {
			if(card[i].get_marker() == NOONE) {
				card[i].set_marker(marker);
				break;
			}
		}
	}
	/**
	 * As the method above, deliver the first two cards to dealer
	 * However, this would show the first card of dealer.
	 */
	protected void start_dealer() {
		int i = 0;
		for(i = 0;i < 52; i++) {
			if(card[i].get_marker() == NOONE) {
				card[i].set_marker(DEALER);
				card[i].getFaced();
				break;
			}
		}
		System.out.print("The first card of Dealer is ");
		System.out.printf(card[i].get_suit());
		if(card[i].show_num() == 11) {
			System.out.printf(" Jack.%n");
		}
		else if(card[i].show_num() == 12) {
			System.out.printf(" Queen.%n");
		}
		else if(card[i].show_num() == 13) {
			System.out.printf(" King.%n");
		}
		else {
			System.out.printf(" %d.%n",card[i].show_num());
		}
	}
	/**
	 * To deliver two cards to one player.
	 * @param marker implies the owner of those two cards.
	 */
	protected void start_two_player(float marker) {
		int j = 0;
		for(int i = 0;i < 52; i++) {
			if(j == 2) {
				break;
			}
			if(card[i].get_marker() == NOONE) {
				j++;
				card[i].set_marker(marker);
			}
		}
	}
}
