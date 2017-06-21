package minmax;

import java.util.List;

public interface Board {
		
	public int evaluateBoard();
	
	public int evaluateBoard(int player);
	
	public boolean isGameFinished();
	
	public Board makeMove(Move move);
	
	public List<Move> generateMoves();
	
	public void printBoard();

	public int[][] getBoard();
	
	public int getPlayer();
	
	public Board getBoardCopy();

}
