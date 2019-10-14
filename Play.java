package blackjack;

class Play {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
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
