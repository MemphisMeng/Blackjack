package blackjack;

class Play {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		TriantaEnaBoard board = new TriantaEnaBoard();
		board.welcome();
		while(board.still_play()) {
			board.round_start();
			board.round_player();
			board.round_win();
			board.round_end();
		}
	}

}
