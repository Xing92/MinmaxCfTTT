package tictactoe;

import java.util.ArrayList;
import java.util.List;

import connectfour.ConnectFourMove;
import minmax.Board;
import minmax.Move;

public class TicTacToeBoard implements Board {

	private int board[][];
	private int player;

	private static int BOARD_SIZE = 3;

	public TicTacToeBoard() {
		board = new int[BOARD_SIZE][BOARD_SIZE];
		player = 1;
	}

	public TicTacToeBoard(int[][] board, int player) {
		this.board = board;
		this.player = player;
	}

	@Override
	public int evaluateBoard() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int evaluateBoard(int player) {
		if (checkWin(player)) return 10;
		else if(checkWin(switchPlayer())) return -10;
		return 0;
	}

	@Override
	public boolean isGameFinished() {
		return checkWin(player) || checkWin(switchPlayer()) || checkNoMoreMoves();
	}

	private boolean checkNoMoreMoves() {
		for (int column = 0; column < BOARD_SIZE; column++) {
			for (int row = 0; row < BOARD_SIZE; row++) {
				if (board[row][column] == 0)
					return false;
			}
		}
		return true;
	}

	public boolean checkWin(int player) {

		return checkWinRows(player) || checkWinColumns(player) || checkWinDiagonals(player);
	}

	private boolean checkWinRows(int player) {
		if (player == board[0][0] && player == board[0][1] && player == board[0][2]) {
			return true;
		} else if (player == board[1][0] && player == board[1][1] && player == board[1][2]) {
			return true;
		} else if (player == board[2][0] && player == board[2][1] && player == board[2][2]) {
			return true;
		}
		return false;
	}

	private boolean checkWinColumns(int player) {
		if (player == board[0][0] && player == board[1][0] && player == board[2][0]) {
			return true;
		} else if (player == board[0][1] && player == board[1][1] && player == board[2][1]) {
			return true;
		} else if (player == board[0][2] && player == board[1][2] && player == board[2][2]) {
			return true;
		}
		return false;
	}

	private boolean checkWinDiagonals(int player) {
		if (player == board[0][0] && player == board[1][1] && player == board[2][2]) {
			return true;
		} else if (player == board[0][2] && player == board[1][1] && player == board[2][0]) {
			return true;
		}
		return false;
	}

	@Override
	public Board makeMove(Move move) {
		int newBoardInt[][] = copyBoardValues(board);
		int newPlayer = player;
		if (isMoveDoable(move)) {
			newBoardInt[move.getMove() % BOARD_SIZE][move.getMove() / BOARD_SIZE] = player;
			newPlayer = switchPlayer();
		}
		TicTacToeBoard newBoard = new TicTacToeBoard(newBoardInt, newPlayer);
		if (newBoard.checkWin(player)) {
			System.out.println("Player " + player + " won!");
		}
		return newBoard;
	}

	private int switchPlayer() {
		return (player == 1 ? -1 : 1);
	}

	private boolean isMoveDoable(Move move) {
		return (board[move.getMove() % BOARD_SIZE][move.getMove() / BOARD_SIZE] == 0 ? true : false);
	}

	private int[][] copyBoardValues(int[][] board) {
		int[][] newBoard = new int[BOARD_SIZE][BOARD_SIZE];
		for (int column = 0; column < BOARD_SIZE; column++) {
			for (int row = 0; row < BOARD_SIZE; row++) {
				newBoard[column][row] = board[column][row];
			}
		}
		return newBoard;

	}

	@Override
	public List<Move> generateMoves() {
		if (isGameFinished()) {
			return new ArrayList<Move>();
		}
		List<Move> moveList = new ArrayList<Move>();
		for (int column = 0; column < BOARD_SIZE; column++) {
			for (int row = 0; row < BOARD_SIZE; row++) {
				Move move = new TicTacToeMove(player, column * 3 + row);
				if (isMoveDoable(move)){
					moveList.add(move);
				}
			}
		}
		return moveList;
	}

	@Override
	public void printBoard() {
		// TODO Auto-generated method stub
	}

	@Override
	public int[][] getBoard() {
		return board;
	}

	@Override
	public int getPlayer() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Board getBoardCopy() {
		// TODO Auto-generated method stub
		return null;
	}

}
