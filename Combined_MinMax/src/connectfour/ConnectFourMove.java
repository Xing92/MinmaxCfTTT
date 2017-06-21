package connectfour;

import minmax.Move;

public class ConnectFourMove implements Move {

	private int player;
	private int move;
	private int depth;
	
	public ConnectFourMove(int player, int move) {
		this.player = player;
		this.move = move;
	}
	
	public ConnectFourMove(int player, int move, int depth) {
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
