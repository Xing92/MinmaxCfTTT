package tictactoe;

import minmax.Move;

public class TicTacToeMove implements Move{

	private int player;
	private int move;
	private int depth;
	
	public TicTacToeMove(int player, int move) {
		this.player = player;
		this.move = move;
	}
	
	public TicTacToeMove(int player, int move, int depth) {
		this(player, move);
		this.depth = depth;
	}

	public int getMove() {
		return move;
	}

	public int getPlayer() {
		return player;
	}
	
	public int getDepth(){
		return depth;
	}
}
